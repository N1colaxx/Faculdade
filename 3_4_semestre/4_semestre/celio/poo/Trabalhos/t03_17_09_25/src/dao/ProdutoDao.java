package dao;

import conexao.Conexao;
import model.ProdutoModel;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDao {

    private Connection conexao = null;

    public ProdutoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    // ===== Helpers para null-safety =====
    private void setBig(PreparedStatement stm, int idx, BigDecimal v) throws SQLException {
        if (v == null) stm.setNull(idx, Types.DECIMAL);
        else stm.setBigDecimal(idx, v);
    }

    private void setDate(PreparedStatement stm, int idx, LocalDate d) throws SQLException {
        if (d == null) stm.setNull(idx, Types.DATE);
        else stm.setDate(idx, Date.valueOf(d));
    }

    // ===== CRUD =====
    public void adicionar(ProdutoModel p) throws SQLException {
        String sql = "INSERT INTO PRODUTO (" +
                "PRO_NOME, PRO_ESTOQUE, PRO_PRECO, PRO_CUSTO, PRO_ATACADO, PRO_MIN, PRO_MAX, " +
                "PRO_EMBALAGEM, PRO_PESO, PRO_CADASTRO, PRO_UNIDADE, PRO_OBS, PRO_ATIVO" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stm = conexao.prepareStatement(sql);

        // 1 - PRO_NOME
        stm.setString(1, p.getPRO_NOME());
        // 2..9 BigDecimals
        setBig(stm, 2, p.getPRO_ESTOQUE());
        setBig(stm, 3, p.getPRO_PRECO());
        setBig(stm, 4, p.getPRO_CUSTO());
        setBig(stm, 5, p.getPRO_ATACADO());
        setBig(stm, 6, p.getPRO_MIN());
        setBig(stm, 7, p.getPRO_MAX());
        setBig(stm, 8, p.getPRO_EMBALAGEM());
        setBig(stm, 9, p.getPRO_PESO());
        // 10 - PRO_CADASTRO (DATE)
        setDate(stm, 10, p.getPRO_CADASTRO());
        // 11..13 Strings
        stm.setString(11, p.getPRO_UNIDADE());
        stm.setString(12, p.getPRO_OBS());
        stm.setString(13, p.getPRO_ATIVO()); // "S" / "N"

        stm.execute();
        stm.close();
    }

    public void alterar(ProdutoModel p) throws SQLException {
        String sql = "UPDATE PRODUTO SET " +
                "PRO_NOME = ?, PRO_ESTOQUE = ?, PRO_PRECO = ?, PRO_CUSTO = ?, PRO_ATACADO = ?, " +
                "PRO_MIN = ?, PRO_MAX = ?, PRO_EMBALAGEM = ?, PRO_PESO = ?, PRO_CADASTRO = ?, " +
                "PRO_UNIDADE = ?, PRO_OBS = ?, PRO_ATIVO = ? " +
                "WHERE PRO_CODIGO = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        
        stm.setString(1, p.getPRO_NOME());
        setBig(stm, 2, p.getPRO_ESTOQUE());
        setBig(stm, 3, p.getPRO_PRECO());
        setBig(stm, 4, p.getPRO_CUSTO());
        setBig(stm, 5, p.getPRO_ATACADO());
        setBig(stm, 6, p.getPRO_MIN());
        setBig(stm, 7, p.getPRO_MAX());
        setBig(stm, 8, p.getPRO_EMBALAGEM());
        setBig(stm, 9, p.getPRO_PESO());
        // PRO_CADASTRO (DATE)
        setDate(stm, 10, p.getPRO_CADASTRO());
        stm.setString(11, p.getPRO_UNIDADE());
        stm.setString(12, p.getPRO_OBS());
        stm.setString(13, p.getPRO_ATIVO());
        stm.setInt(14, p.getPRO_CODIGO());

        stm.execute();
        stm.close();
    }

    public void excluir(ProdutoModel p) throws SQLException {
        String sql = "DELETE FROM PRODUTO WHERE PRO_CODIGO = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, p.getPRO_CODIGO());
        stm.execute();
        stm.close();
    }

    public ArrayList<ProdutoModel> consultar(String condicao) throws SQLException {
        ArrayList<ProdutoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTO";
        if (condicao != null && !condicao.trim().isEmpty()) {
            sql += " WHERE " + condicao;
        }

        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            ProdutoModel p = new ProdutoModel();
            p.setPRO_CODIGO(rs.getInt("PRO_CODIGO"));
            p.setPRO_NOME(rs.getString("PRO_NOME"));

            // BigDecimals
            p.setPRO_ESTOQUE(rs.getBigDecimal("PRO_ESTOQUE"));
            p.setPRO_PRECO(rs.getBigDecimal("PRO_PRECO"));
            p.setPRO_CUSTO(rs.getBigDecimal("PRO_CUSTO"));
            p.setPRO_ATACADO(rs.getBigDecimal("PRO_ATACADO"));
            p.setPRO_MIN(rs.getBigDecimal("PRO_MIN"));
            p.setPRO_MAX(rs.getBigDecimal("PRO_MAX"));
            p.setPRO_EMBALAGEM(rs.getBigDecimal("PRO_EMBALAGEM"));
            p.setPRO_PESO(rs.getBigDecimal("PRO_PESO"));

            // Data
            Date d = rs.getDate("PRO_CADASTRO");
            p.setPRO_CADASTRO(d == null ? null : d.toLocalDate());

            // Strings
            p.setPRO_UNIDADE(rs.getString("PRO_UNIDADE"));
            p.setPRO_OBS(rs.getString("PRO_OBS"));
            p.setPRO_ATIVO(rs.getString("PRO_ATIVO"));

            lista.add(p);
        }

        rs.close();
        stm.close();
        return lista;
    }
}
