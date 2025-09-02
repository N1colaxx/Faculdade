package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexaoJDBC {

    private final static String DRIVER = "org.postgresql.Driver";
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "12345";

    private Connection conexao;
    private Statement stm;

    public ConexaoJDBC() {
        try {
            Class.forName(DRIVER);  //define o driver do banco
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA); //cria conexão
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // configura o Statement
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Classe de Conexão  do Banco. \n" + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco. \n" + ex.getMessage());
        }
    }

    public void incluir(String sql) {
        try {
            stm = conexao.createStatement();
            int resultado = stm.executeUpdate(sql);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na Inclusão");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Inclusão SQL. \n" + ex.getMessage());
        }
    }

    public void alterar(String sql) {
        try {
            stm = conexao.createStatement();
            int resultado = stm.executeUpdate(sql);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Alteração Efetuada com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro na Alteração");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Alteração SQL. \n" + ex.getMessage());
        }
    }

    public void excluir(String sql) {
        try {
            stm = conexao.createStatement();
            int resultado = stm.executeUpdate(sql);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Exclusão Efetuada com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro na Exclusão");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Exclusão SQL. \n" + ex.getMessage());
        }
    }

    public ResultSet consultar(String sql) {
        ResultSet rs = null;
        try {
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //executa SQL
            rs = stm.executeQuery(sql); //obtem dados da consulta SQL 

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Consulta SQL. \n" + ex.getMessage());
        }
        return rs;
    }
}

