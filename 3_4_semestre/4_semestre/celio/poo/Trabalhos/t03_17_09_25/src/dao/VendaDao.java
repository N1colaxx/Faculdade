package dao;


import conexao.Conexao;
import model.VendaModel;
import model.VendaItemModel;
import model.VendaPagtoModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VendaDao {
    private final Connection conexao;

    //  esse metodo é para: abrir a conexão (usando sua classe Conexao)
    public VendaDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    //  esse metodo é para: inserir/alterar venda + itens + pagtos em transação
    public void gravarTransacao(String operacao,
                                VendaModel venda,
                                ArrayList<VendaItemModel> itens,
                                ArrayList<VendaPagtoModel> pgtos) throws SQLException {
        boolean auto = conexao.getAutoCommit();
        try {
            conexao.setAutoCommit(false);

            if ("incluir".equalsIgnoreCase(operacao)) {
                int vdaCodigo = inserirVenda(venda);
                venda.setVDA_CODIGO(vdaCodigo);
            } else if ("alterar".equalsIgnoreCase(operacao)) {
                alterarVenda(venda);
                excluirItens(venda.getVDA_CODIGO());
                excluirPgtos(venda.getVDA_CODIGO());
            } else {
                throw new SQLException("Operação inválida: " + operacao);
            }

            inserirItens(venda.getVDA_CODIGO(), itens);
            inserirPgtos(venda.getVDA_CODIGO(), pgtos);

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(auto);
        }
    }

    //  esse metodo é para: inserir a venda (cabeçalho) e retornar PK gerada
    private int inserirVenda(VendaModel v) throws SQLException {
        String sql = "INSERT INTO venda (usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total, vda_obs) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING vda_codigo";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, v.getUSU_CODIGO());
            ps.setInt(2, v.getCLI_CODIGO());
            ps.setDate(3, v.getVDA_DATA() == null ? null : Date.valueOf(v.getVDA_DATA()));
            ps.setDouble(4, v.getVDA_VALOR());
            ps.setDouble(5, v.getVDA_DESCONTO());
            ps.setDouble(6, v.getVDA_TOTAL());
            ps.setString(7, v.getVDA_OBS());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Falha ao inserir venda (sem retorno de chave).");
    }

    //  esse metodo é para: atualizar os campos do cabeçalho da venda
    private void alterarVenda(VendaModel v) throws SQLException {
        String sql = "UPDATE venda SET usu_codigo=?, cli_codigo=?, vda_data=?, " +
                     "vda_valor=?, vda_desconto=?, vda_total=?, vda_obs=? " +
                     "WHERE vda_codigo=?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, v.getUSU_CODIGO());
            ps.setInt(2, v.getCLI_CODIGO());
            ps.setDate(3, v.getVDA_DATA() == null ? null : Date.valueOf(v.getVDA_DATA()));
            ps.setDouble(4, v.getVDA_VALOR());
            ps.setDouble(5, v.getVDA_DESCONTO());
            ps.setDouble(6, v.getVDA_TOTAL());
            ps.setString(7, v.getVDA_OBS());
            ps.setInt(8, v.getVDA_CODIGO());
            ps.executeUpdate();
        }
    }

    //  esse metodo é para: excluir itens de uma venda (antes de regravar)
    private void excluirItens(int vdaCodigo) throws SQLException {
        try (PreparedStatement ps = conexao.prepareStatement("DELETE FROM venda_produto WHERE vda_codigo = ?")) {
            ps.setInt(1, vdaCodigo);
            ps.executeUpdate();
        }
    }

    //  esse metodo é para: excluir pagamentos de uma venda (antes de regravar)
    private void excluirPgtos(int vdaCodigo) throws SQLException {
        try (PreparedStatement ps = conexao.prepareStatement("DELETE FROM venda_pagto WHERE vda_codigo = ?")) {
            ps.setInt(1, vdaCodigo);
            ps.executeUpdate();
        }
    }

    //  esse metodo é para: inserir todos os itens na tabela venda_produto
    private void inserirItens(int vdaCodigo, ArrayList<VendaItemModel> itens) throws SQLException {
        String sql = "INSERT INTO venda_produto (vda_codigo, pro_codigo, vep_qtde, vep_preco, vep_desconto, vep_total) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            for (VendaItemModel it : itens) {
                ps.setInt(1, vdaCodigo);
                ps.setInt(2, it.getPRO_CODIGO());
                ps.setDouble(3, it.getVEP_QTDE());
                ps.setDouble(4, it.getVEP_PRECO());
                ps.setDouble(5, it.getVEP_DESCONTO());
                ps.setDouble(6, it.getVEP_TOTAL());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    //  esse metodo é para: inserir os pagamentos na tabela venda_pagto
    private void inserirPgtos(int vdaCodigo, ArrayList<VendaPagtoModel> pgtos) throws SQLException {
        String sql = "INSERT INTO venda_pagto (vda_codigo, fpg_codigo, vdp_valor) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            for (VendaPagtoModel p : pgtos) {
                ps.setInt(1, vdaCodigo);
                ps.setInt(2, p.getFPG_CODIGO());
                ps.setDouble(3, p.getVDP_VALOR());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    //  esse metodo é para: consultar vendas (somente cabeçalho) por condição livre
    public ArrayList<VendaModel> consultar(String cond) throws SQLException {
        ArrayList<VendaModel> lista = new ArrayList<>();
        String sql = "SELECT vda_codigo, usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total, vda_obs FROM venda";
        if (cond != null && !cond.trim().isEmpty()) sql += " WHERE " + cond;

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VendaModel v = new VendaModel();
                v.setVDA_CODIGO(rs.getInt("vda_codigo"));
                v.setUSU_CODIGO(rs.getInt("usu_codigo"));
                v.setCLI_CODIGO(rs.getInt("cli_codigo"));
                Date d = rs.getDate("vda_data");
                v.setVDA_DATA(d == null ? null : d.toLocalDate());
                v.setVDA_VALOR(rs.getDouble("vda_valor"));
                v.setVDA_DESCONTO(rs.getDouble("vda_desconto"));
                v.setVDA_TOTAL(rs.getDouble("vda_total"));
                v.setVDA_OBS(rs.getString("vda_obs"));
                lista.add(v);
            }
        }
        return lista;
    }

    //  esse metodo é para: excluir a venda inteira (cabeçalho + itens + pgtos)
    public void excluir(VendaModel v) throws SQLException {
        boolean auto = conexao.getAutoCommit();
        try {
            conexao.setAutoCommit(false);
            excluirItens(v.getVDA_CODIGO());
            excluirPgtos(v.getVDA_CODIGO());
            try (PreparedStatement ps = conexao.prepareStatement("DELETE FROM venda WHERE vda_codigo=?")) {
                ps.setInt(1, v.getVDA_CODIGO());
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
