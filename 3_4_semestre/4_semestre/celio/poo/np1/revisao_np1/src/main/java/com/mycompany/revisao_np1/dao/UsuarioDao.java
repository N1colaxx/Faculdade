package com.mycompany.revisao_np1.dao;

import com.mycompany.revisao_np1.conexao.ConexaoJDBC;
import com.mycompany.revisao_np1.model.UsuarioModel;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {

    private Connection conexao = null;
    
    public UsuarioDao() {
        this.conexao = ConexaoJDBC.getConexao();
    }
    

    public void adicionar(UsuarioModel usuario) throws SQLException {
        
        String sql = "INSERT INTO usuario (USU_NOME, USO_LOGIN, USU_SENHA, USU_ATIVO)" + " VALUES (?, ?, ?, ?)"; 
        
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            
            stm.setString(1, usuario.getUSU_NOME());
            stm.setString(2, usuario.getUSU_LOGIN());
            stm.setString(3, usuario.getUSU_SENHA());
            stm.setInt(4, usuario.getUSU_ATIVO());
        } throw new SQLException(" Falha ao GRAVAR usuario");
    }
    
    public void alterar (UsuarioModel usuario) throws SQLException {
        String sql =    "UPDATE usuario SET" + 
                        " USU_NOME = ?, USU_LOGIN = ?, USU_SENHA = ?, USU_ATIVO = ?" +
                        " WHERE USU_CODIGO = ?";
        
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            
            stm.setString(1, usuario.getUSU_NOME());
            stm.setString(2, usuario.getUSU_LOGIN());
            stm.setString(3, usuario.getUSU_SENHA());
            stm.setInt(4, usuario.getUSU_ATIVO());
        } throw new SQLException(" Falha ao ALTERAR usuario.");
    }
    
    public void excluir (UsuarioModel usuario) throws SQLException {
        
        String sql = "DELETE FROM usuario WHERE USU_CODIGO = ? "; 
        
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, usuario.getUSU_CODIGO());
        } throw new SQLException(" Falha ao EXCLUIR usuario");
        
    }
    
    public ArrayList<UsuarioModel> consultar (String condicao) throws SQLException { 
        
        ArrayList<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        
        if(!condicao.equals("")) { sql += " WHERE " + condicao; }
        
        sql += " ORDER BY USU_ATIVO DESC, USU_NOME ";
        
        try ( PreparedStatement stm = conexao.prepareStatement(sql); ResultSet rs = stm.executeQuery(); ) {
            
            while (rs.next()) {    
                UsuarioModel obj_usu = new UsuarioModel();
                obj_usu.setUSU_CODIGO(rs.getInt("USU_CODIGO"));
                obj_usu.setUSU_NOME(rs.getString("USU_NOME"));
                obj_usu.setUSU_LOGIN(rs.getString("USU_LOGIN"));
                obj_usu.setUSU_SENHA(rs.getString("USU_SENHA"));
                obj_usu.setUSU_ATIVO(rs.getInt("USU_ATIVO"));

                lista.add(obj_usu);
            };
        }
        
        return lista;
    }
    
}
