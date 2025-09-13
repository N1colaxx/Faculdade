package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.UsuarioModel;

public class UsuarioDao {

    private Connection conexao = null;

    public UsuarioDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void adicionar(UsuarioModel usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (USU_NOME, USU_LOGIN, USU_SENHA, USU_ATIVO)"
                + " VALUES (?, ?, ?, ?)";
        PreparedStatement stm = conexao.prepareStatement(sql);
//        stm.setInt(1, usuario.getUSU_CODIGO());
        stm.setString(1, usuario.getUSU_NOME());
        stm.setString(2, usuario.getUSU_LOGIN());
        stm.setString(3, usuario.getUSU_SENHA());
        stm.setInt(4, usuario.getUSU_ATIVO());
        stm.execute();
        stm.close();
    }

    public void alterar(UsuarioModel usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET USU_NOME = ?, USU_LOGIN = ?, "
                + "USU_SENHA = ?, USU_ATIVO = ? WHERE USU_CODIGO = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, usuario.getUSU_NOME());
        stm.setString(2, usuario.getUSU_LOGIN());
        stm.setString(3, usuario.getUSU_SENHA());
        stm.setInt(4, usuario.getUSU_ATIVO());
        stm.setInt(5, usuario.getUSU_CODIGO());
        stm.execute();
        stm.close();
    }

    public void excluir(UsuarioModel usuario) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE USU_CODIGO = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, usuario.getUSU_CODIGO());
        stm.execute();
        stm.close();
    }

    public ArrayList<UsuarioModel> consultar(String condicao) throws SQLException {
        ArrayList<UsuarioModel> lista = null;
        PreparedStatement stm;
        ResultSet rs;
        String sql = "SELECT * FROM USUARIO";
        if (!condicao.equals("")) {
            sql += " where " + condicao;
        }
        stm = conexao.prepareStatement(sql);
        rs = stm.executeQuery();
        lista = new ArrayList<>();

        while (rs.next()) {
            UsuarioModel obj_usu = new UsuarioModel();
            obj_usu.setUSU_CODIGO(rs.getInt("USU_CODIGO"));
            obj_usu.setUSU_NOME(rs.getString("USU_NOME"));
            obj_usu.setUSU_LOGIN(rs.getString("USU_LOGIN"));
            obj_usu.setUSU_SENHA(rs.getString("USU_SENHA"));
            obj_usu.setUSU_ATIVO(rs.getInt("USU_ATIVO"));
            lista.add(obj_usu);
        }
        rs.close();
        stm.close();
        return lista;
    }
}
