package dao;


import conexao.Conexao;
import model.CompraModel;
import model.CompraProdutoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ProdutoModel;

/** DAO de COMPRA: cabeçalho + itens em transação. */
public class CompraDao {
    private final Connection conexao;

    public CompraDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /** Transação para incluir/alterar compra  */
    public void gravarTransacao(
                                String operacao, 
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

    // consulta tab COMPRA
    public ArrayList<model.CompraModel> consultar(String cond) throws SQLException  {
        ArrayList<model.CompraModel> lista = new ArrayList<>();
        
        String sql = "SELECT cpr_codigo, usu_codigo, for_codigo, cpr_emissao, cpr_valor, cpr_desconto, cpr_total, cpr_dtentrada, cpr_obs FROM compra";
        
        if (cond != null && !cond.trim().isEmpty()) {
            sql += " WHERE " + cond;
        }
        
        sql += " ORDER BY cpr_codigo";
        
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

    
    // consulta tab COMPRA_PRODUTO
    public List<Object[]> consultarCompraProduto(String cond) throws SQLException {
        
        List<Object[]> lista = new ArrayList<>();
        
        String sql = "SELECT cpr_codigo, pro_codigo, cpr_qtde, cpr_preco, cpr_total FROM compra_produto";
        
        if (cond != null && !cond.trim().isEmpty()) {
            sql += " WHERE " + cond;
        }
        
        try (PreparedStatement stm = conexao.prepareStatement(sql); ResultSet rs =  stm.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("cpr_codigo"),
                    rs.getInt("pro_codigo"),
                    rs.getBigDecimal("cpr_qtde"),
                    rs.getBigDecimal("cpr_preco"),
                    rs.getBigDecimal("cpr_total")
                });
            }
        }
        
        return lista;
    }
    
    public void excluir(CompraModel c) throws SQLException {
        boolean auto = conexao.getAutoCommit();
        
        String sql = "DELETE FROM compra WHERE cpr_codigo = ?";
        
        try {
            conexao.setAutoCommit(false);
            excluirItens(c.getCPR_CODIGO());
            try (PreparedStatement ps = conexao.prepareStatement(sql)){
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
        } throw new SQLException("Falha ao inserir compra (sem retorno de chave).");
    }

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
        String sql = "DELETE FROM compra_produto WHERE cpr_codigo=?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
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
    
    
    
    /**
     * Metodos para retornar os itens em cada campo de compra
     */
    
    // aba DADOS
    public CompraModel buscarCabecalho(int cpr) throws SQLException {
        System.out.println(" [CompraDao] executou -> buscarCabecalho");

        String sql = "SELECT * FROM compra WHERE cpr_codigo = ?";
        
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, cpr);
            
            try (ResultSet rs = stm.executeQuery()) {
                
                if(!rs.next()) {
                    return null;
                }
                
                CompraModel c = new CompraModel();
                c.setCPR_CODIGO(rs.getInt("cpr_codigo"));
                c.setUSU_CODIGO(rs.getInt("usu_codigo"));
                c.setFOR_CODIGO(rs.getInt("for_codigo"));
                c.setCPR_EMISSAO(rs.getDate("cpr_emissao").toLocalDate());
                c.setCPR_VALOR(rs.getDouble("cpr_valor"));
                c.setCPR_DESCONTO(rs.getDouble("cpr_desconto"));
                c.setCPR_TOTAL(rs.getDouble("cpr_total"));
                c.setCPR_DTENTRADA(rs.getDate("cpr_dtentrada").toLocalDate());
                c.setCPR_OBS(rs.getString("cpr_obs"));
                
                return c;
            }
        } 
        
    }
    
    public List<CompraProdutoModel> listarIntes(int cpr) throws SQLException {
        System.out.println(" [CompraDao] executou -> listarItens");
        
        
        final String sql =
                  "SELECT  cp.pro_codigo, p.pro_nome, p.pro_unidade, cp.cpr_qtde, cp.cpr_preco, cp.cpr_desconto, cp.cpr_total "
                + " FROM compra_produto cp"
                + " JOIN produto p USING (pro_codigo) "
                + " WHERE cp.cpr_codigo = ? "
                + " ORDER BY cp.pro_codigo ";
        
        List<CompraProdutoModel> lista = new ArrayList<>();
        
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, cpr); 
            
            try (ResultSet rs = stm.executeQuery()) { 
                while (rs.next()) {
                    CompraProdutoModel it = new CompraProdutoModel();

                    it.setPRO_CODIGO(rs.getInt("pro_codigo"));
                    it.setPRO_NOME(rs.getString("pro_nome"));
                    it.setPRO_UNIDADE(rs.getString("pro_unidade"));
                    it.setCPR_QTDE(rs.getBigDecimal("cpr_qtde"));
                    it.setCPR_PRECO(rs.getBigDecimal("cpr_preco"));
                    it.setCPR_DESCONTO(rs.getBigDecimal("cpr_desconto"));
                    it.setCPR_TOTAL(rs.getBigDecimal("cpr_total"));
                    
                    lista.add(it);
                }
            }
        }
        
        return lista;
    }
}
