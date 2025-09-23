package dao;

import conexao.Conexao;
import model.VendaModel;
import model.VendaProdutoModel;
import model.VendaPagtoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO da Venda: cabeçalho + itens + pagamentos em transação.
 */
public class VendaDao {

    private final Connection conexao;

    public VendaDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /**
     * Grava (incluir/alterar) venda completa em transação.
     */
    public void gravarTransacao(
            String operacao,
            VendaModel venda,
            ArrayList<VendaProdutoModel> itens,
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

    /**
     * INSERT do cabeçalho; retorna PK gerada.
     */
    private int inserirVenda(VendaModel v) throws SQLException {
        String sql = "INSERT INTO venda (usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total, vda_obs) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING vda_codigo";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, v.getUSU_CODIGO());
            ps.setInt(2, v.getCLI_CODIGO());
            ps.setDate(3, v.getVDA_DATA() == null ? null : java.sql.Date.valueOf(v.getVDA_DATA()));
            ps.setDouble(4, v.getVDA_VALOR());
            ps.setDouble(5, v.getVDA_DESCONTO());
            ps.setDouble(6, v.getVDA_TOTAL());
            ps.setString(7, v.getVDA_OBS());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // retorna PK gerada
                }
            }
        }
        throw new SQLException("Falha ao inserir venda (RETURNING não retornou chave).");

    }

    /**
     * UPDATE do cabeçalho.
     */
    private void alterarVenda(VendaModel v) throws SQLException {
        String sql = "UPDATE venda SET usu_codigo=?, cli_codigo=?, vda_data=?, "
                + "vda_valor=?, vda_desconto=?, vda_total=?, vda_obs=? WHERE vda_codigo=?";
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

    private void excluirItens(int vdaCodigo) throws SQLException {
        try (PreparedStatement ps = conexao.prepareStatement(
                "DELETE FROM venda_produto WHERE vda_codigo = ?")) {
            ps.setInt(1, vdaCodigo);
            ps.executeUpdate();
        }
    }

    private void excluirPgtos(int vdaCodigo) throws SQLException {
        try (PreparedStatement ps = conexao.prepareStatement(
                "DELETE FROM venda_pagto WHERE vda_codigo = ?")) {
            ps.setInt(1, vdaCodigo);
            ps.executeUpdate();
        }
    }

    private void inserirItens(int vdaCodigo, ArrayList<VendaProdutoModel> itens) throws SQLException {
        String sql = "INSERT INTO venda_produto (vda_codigo, pro_codigo, vep_qtde, vep_preco, vep_desconto, vep_total) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            for (VendaProdutoModel it : itens) {
                ps.setInt(1, vdaCodigo);
                ps.setInt(2, it.getPRO_CODIGO());
                ps.setDouble(3, it.getVEP_QTDE());
                ps.setDouble(4, it.getVEP_PRECO());
                ps.setDouble(5, 0.0); // desconto por item não usado 
                ps.setDouble(6, it.getVEP_TOTAL());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

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

    /**
     * Consulta cabeçalhos por condição livre.
     */
    public ArrayList<VendaModel> consultar(String cond) throws SQLException {
        ArrayList<VendaModel> lista = new ArrayList<>();
        String sql = "SELECT vda_codigo, usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total, vda_obs FROM venda";
        if (cond != null && !cond.trim().isEmpty()) {
            sql += " WHERE " + cond;
        }

        try (PreparedStatement ps = conexao.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
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

    /**
     * Consulta para aba "Consulta": RETORNA ResultSet de venda_produto.
     */
    public List<Object[]> consultarVendaProduto(String cond) throws SQLException {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT vda_codigo, pro_codigo, vep_qtde, vep_preco, vep_total FROM venda_produto";
        if (cond != null && !cond.trim().isEmpty()) {
            sql += " WHERE " + cond;
        }
        try (PreparedStatement ps = conexao.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("vda_codigo"),
                    rs.getInt("pro_codigo"),
                    rs.getDouble("vep_qtde"),
                    rs.getDouble("vep_preco"),
                    rs.getDouble("vep_total")
                });
            }
        }
        return lista;
    }

    /**
     * Exclui venda inteira (cabeçalho + itens + pgtos).
     */
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
        } catch (SQLException e) {
            conexao.rollback();
            throw e;
        } finally {
            conexao.setAutoCommit(auto);
        }
    }

    /**
     * para retornas os itens em aba consulta
     */
    public VendaModel buscarCabecalho(int vda) throws SQLException {
        String sql = "SELECT vda_codigo, usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total, vda_obs "
                + "FROM venda WHERE vda_codigo = ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, vda);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                VendaModel v = new VendaModel();
                v.setVDA_CODIGO(rs.getInt("vda_codigo"));
                v.setUSU_CODIGO(rs.getInt("usu_codigo"));
                v.setCLI_CODIGO(rs.getInt("cli_codigo"));
                v.setVDA_DATA(rs.getDate("vda_data").toLocalDate());
                v.setVDA_VALOR(rs.getDouble("vda_valor"));
                v.setVDA_DESCONTO(rs.getDouble("vda_desconto"));
                v.setVDA_TOTAL(rs.getDouble("vda_total"));
                v.setVDA_OBS(rs.getString("vda_obs"));

                return v;
            }
        }
    }

    public java.util.List<VendaProdutoModel> listarItens(int vda) throws SQLException {
        String sql
                = "SELECT vp.pro_codigo, p.pro_nome, p.pro_unidade, vp.vep_qtde, vp.vep_preco, vp.vep_desconto, vp.vep_total "
                + "FROM venda_produto vp JOIN produto p USING (pro_codigo) WHERE vp.vda_codigo = ? ORDER BY vp.pro_codigo";
        java.util.List<VendaProdutoModel> lista = new java.util.ArrayList<>();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, vda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VendaProdutoModel it = new VendaProdutoModel();
                    it.setPRO_CODIGO(rs.getInt("pro_codigo"));
                    it.setPRO_NOME(rs.getString("pro_nome"));
                    it.setPRO_UNIDADE(rs.getString("pro_unidade"));
                    it.setVEP_QTDE(rs.getDouble("vep_qtde"));
                    it.setVEP_PRECO(rs.getDouble("vep_preco"));
                    it.setVEP_DESCONTO(rs.getDouble("vep_desconto"));
                    it.setVEP_TOTAL(rs.getDouble("vep_total"));
                    lista.add(it);
                }
            }
        }
        return lista;
    }

    public java.util.List<VendaPagtoModel> listarPgtos(int vda) throws SQLException {
        String sql
                = "SELECT vpg.fpg_codigo, f.fpg_nome, vpg.vdp_valor "
                + "FROM venda_pagto vpg JOIN formapagto f USING (fpg_codigo) WHERE vpg.vda_codigo = ? ORDER BY vpg.fpg_codigo";
        java.util.List<VendaPagtoModel> lista = new java.util.ArrayList<>();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, vda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VendaPagtoModel pg = new VendaPagtoModel();
                    pg.setFPG_CODIGO(rs.getInt("fpg_codigo"));
                    pg.setFPG_NOME(rs.getString("fpg_nome"));
                    pg.setVDP_VALOR(rs.getDouble("vdp_valor"));
                    lista.add(pg);
                }
            }
        }
        return lista;
    }

}
