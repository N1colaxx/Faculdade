package com.mycompany.revisao_np1.conexao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexaoJDBC {
    
    // private final static String DRIVER = "org.postgresql.Driver";
    private final static String BANCO = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "12345";

    private static Connection conexao = null;
    
    
    public static Connection getConexao() {
        
        try {
            if(conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(BANCO, USUARIO, SENHA);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "\n ERRO ao conectar ao banco \n" + e.getMessage());
        }
        
        return conexao;
    }
    
    
    
    
}
