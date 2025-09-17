package dao;


import conexao.Conexao;
import model.CompraModel;
import model.CompraProdutoModel;

import java.sql.*;
import java.util.ArrayList;

/** DAO de COMPRA: cabeçalho + itens em transação. */
public class CompraDao {
    private final Connection conexao;

    public CompraDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /** Transação para incluir/alterar compra e seus itens. */
    public void gravarTransacao(String operacao,
                                CompraModel compra,
                                ArrayList<CompraProdutoModel> itens) throws SQLException {
        boolean auto = conexao.getAutoCommit();
        try {
            conexao.setAutoCommit(false);

            if ("incluir".equalsIgnoreCase(operacao)) {
                int cprCodigo = inserirCompra(compra);
                compra.setCPR_CODIGO(cprCodigo);
            } else if ("alterar".equalsIgnoreCase(operacao)) {
                alterarCompra(compra);
                excluirItens(compra.getCPR_CODIGO());
            } else {
                throw new SQLException("Operação inválida: " + operacao);
            }

            inserirItens(compra.getCPR_CODIGO(), itens);

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(auto);
        }
    }

    /** INSERT cabeçalho; retorna PK (PostgreSQL). */
    private int inserirCompra(CompraModel c) throws SQLException {
        String sql = "INSERT INTO compra (usu_codigo, for_codigo, cpr_emissao, cpr_valor, cpr_desconto, cpr_total, cpr_dtentrada, cpr_obs) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING cpr_codigo";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, c.getUSU_CODIGO());
            ps.setInt(2, c.getFOR_CODIGO());
            ps.setDate(3, c.getCPR_EMISSAO()==null?null:Date.valueOf(c.getCPR_EMISSAO()));
            ps.setDouble(4, c.getCPR_VALOR());
            ps.setDouble(5, c.getCPR_DESCONTO());
            ps.setDouble(6, c.getCPR_TOTAL());
            ps.setDate(7, c.getCPR_DTENTRADA()==null?null:Date.valueOf(c.getCPR_DTENTRADA()));
            ps.setString(8, c.getCPR_OBS());
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Falha ao inserir compra (sem retorno de chave).");
    }

    /** UPDATE cabeçalho. */
    private void alterarCompra(CompraModel c) throws SQLException {
        String sql = "UPDATE compra SET usu_codigo=?, for_codigo=?, cpr_emissao=?, " +
                     "cpr_valor=?, cpr_desconto=?, cpr_total=?, cpr_dtentrada=?, cpr_obs=? " +
                     "WHERE cpr_codigo=?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, c.getUSU_CODIGO());
            ps.setInt(2, c.getFOR_CODIGO());
            ps.setDate(3, c.getCPR_EMISSAO()==null?null:Date.valueOf(c.getCPR_EMISSAO()));
            ps.setDouble(4, c.getCPR_VALOR());
            ps.setDouble(5, c.getCPR_DESCONTO());
            ps.setDouble(6, c.getCPR_TOTAL());
            ps.setDate(7, c.getCPR_DTENTRADA()==null?null:Date.valueOf(c.getCPR_DTENTRADA()));
            ps.setString(8, c.getCPR_OBS());
            ps.setInt(9, c.getCPR_CODIGO());
            ps.executeUpdate();
        }
    }

    private void excluirItens(int cprCodigo) throws SQLException {
        try (PreparedStatement ps = conexao.prepareStatement(
                "DELETE FROM compra_produto WHERE cpr_codigo=?")) {
            ps.setInt(1, cprCodigo);
            ps.executeUpdate();
        }
    }

    private void inserirItens(int cprCodigo, ArrayList<CompraProdutoModel> itens) throws SQLException {
        String sql = "INSERT INTO compra_produto (cpr_codigo, pro_codigo, cpr_qtde, cpr_preco, cpr_desconto, cpr_total) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            for (CompraProdutoModel it : itens) {
                ps.setInt(1, cprCodigo);
                ps.setInt(2, it.getPRO_CODIGO());
                ps.setBigDecimal(3, it.getCPR_QTDE());
                ps.setBigDecimal(4, it.getCPR_PRECO());
                ps.setBigDecimal(5, it.getCPR_DESCONTO()==null?java.math.BigDecimal.ZERO:it.getCPR_DESCONTO());
                ps.setBigDecimal(6, it.getCPR_TOTAL());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    /** Consulta cabeçalhos (se quiser usar semelhante a vendas). */
    public ArrayList<model.CompraModel> consultar(String cond) throws SQLException {
        ArrayList<model.CompraModel> lista = new ArrayList<>();
        String sql = "SELECT cpr_codigo, usu_codigo, for_codigo, cpr_emissao, cpr_valor, cpr_desconto, cpr_total, cpr_dtentrada, cpr_obs FROM compra";
        if (cond != null && !cond.trim().isEmpty()) sql += " WHERE " + cond;
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                model.CompraModel c = new model.CompraModel();
                c.setCPR_CODIGO(rs.getInt("cpr_codigo"));
                c.setUSU_CODIGO(rs.getInt("usu_codigo"));
                c.setFOR_CODIGO(rs.getInt("for_codigo"));
                Date e = rs.getDate("cpr_emissao");
                c.setCPR_EMISSAO(e==null?null:e.toLocalDate());
                c.setCPR_VALOR(rs.getDouble("cpr_valor"));
                c.setCPR_DESCONTO(rs.getDouble("cpr_desconto"));
                c.setCPR_TOTAL(rs.getDouble("cpr_total"));
                Date dte = rs.getDate("cpr_dtentrada");
                c.setCPR_DTENTRADA(dte==null?null:dte.toLocalDate());
                c.setCPR_OBS(rs.getString("cpr_obs"));
                lista.add(c);
            }
        }
        return lista;
    }

    /** ResultSet para aba Consulta: compra_produto (fechar no chamador). */
    public ResultSet consultarCompraProdutoRS(String cond) throws SQLException {
        String sql = "SELECT cpr_codigo, pro_codigo, cpr_qtde, cpr_preco, cpr_total FROM compra_produto";
        if (cond != null && !cond.trim().isEmpty()) sql += " WHERE " + cond;
        PreparedStatement ps = conexao.prepareStatement(sql);
        return ps.executeQuery();
    }

    /** Exclusão completa (cabeçalho + itens). */
    public void excluir(CompraModel c) throws SQLException {
        boolean auto = conexao.getAutoCommit();
        try {
            conexao.setAutoCommit(false);
            excluirItens(c.getCPR_CODIGO());
            try (PreparedStatement ps = conexao.prepareStatement("DELETE FROM compra WHERE cpr_codigo=?")){
                ps.setInt(1, c.getCPR_CODIGO());
                ps.executeUpdate();
            }
            conexao.commit();
        } catch (SQLException e){
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(auto);
        }
    }
}
