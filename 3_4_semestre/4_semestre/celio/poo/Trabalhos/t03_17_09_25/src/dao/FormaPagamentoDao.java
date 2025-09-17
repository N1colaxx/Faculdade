package dao;

import conexao.Conexao;
import model.FormapagtoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormaPagamentoDao {

    private Connection conexao = null;

    public FormaPagamentoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void adicionar(FormapagtoModel fpg) throws SQLException {
        String sql = "INSERT INTO formapagto (fpg_nome, fpg_ativo) VALUES (?, ?)";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, fpg.getFPG_NOME());
        stm.setString(2, fpg.getFPG_ATIVO());
        stm.execute();
        stm.close();
    }

    public void alterar(FormapagtoModel fpg) throws SQLException {
        String sql = "UPDATE formapagto SET fpg_nome = ?, fpg_ativo = ? WHERE fpg_codigo = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, fpg.getFPG_NOME());
        stm.setString(2, fpg.getFPG_ATIVO());
        stm.setInt(3, fpg.getFPG_CODIGO());
        stm.execute();
        stm.close();
    }

    public void excluir(FormapagtoModel fpg) throws SQLException {
        String sql = "DELETE FROM formapagto WHERE fpg_codigo = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, fpg.getFPG_CODIGO());
        stm.execute();
        stm.close();
    }

    public ArrayList<FormapagtoModel> consultar(String condicao) throws SQLException {
        ArrayList<FormapagtoModel> lista = new ArrayList<>();
        String sql = "SELECT fpg_codigo, fpg_nome, fpg_ativo FROM formapagto";
        if (condicao != null && !condicao.trim().isEmpty()) {
            sql += " WHERE " + condicao;
        }

        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            FormapagtoModel f = new FormapagtoModel();
            f.setFPG_CODIGO(rs.getInt("fpg_codigo"));
            f.setFPG_NOME(rs.getString("fpg_nome"));
            f.setFPG_ATIVO(rs.getString("fpg_ativo"));
            lista.add(f);
        }
        rs.close();
        stm.close();
        return lista;
    }
}
