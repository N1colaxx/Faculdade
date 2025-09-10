//package Teste_t02_10_09_25;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
//import Teste_t02_10_09_25.ConnectionFactory;
//
//
//public class ConexaoJDBC {
//    private Statement stm;
//
//    public void incluir(String sql) {
//        try {
//            Connection conexao = ConnectionFactory.getConnection(); // Conecta com o JDBC
//            stm = conexao.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE, // Permite o user pesquisar pelo result no DB
//                    ResultSet.CONCUR_READ_ONLY // Não deixa o user manipular o DB pelo results
//            );
//            int resultado = stm.executeUpdate(sql); 
//
//            if (resultado == 1) {
//                JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Problemas na Inclusão");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro na Inclusão SQL. \n" + ex.getMessage());
//        }
//    }
//
//    public void alterar(String sql) {
//        try {
//            Connection conexao = ConnectionFactory.getConnection();
//            
//            stm = conexao.createStatement(
//                    ResultSet.TYPE_SCROLL_SENSITIVE, // Permite o user pesquisar pelo result no DB
//                    ResultSet.CONCUR_READ_ONLY // Não deixa o user manipular o DB pelo results
//            );
//            
//            
//            int resultado = stm.executeUpdate(sql);
//
//            if (resultado == 1) {
//                JOptionPane.showMessageDialog(null, "Alteração Efetuada com Sucesso!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Erro na Alteração");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro na Alteração SQL. \n" + ex.getMessage());
//        }
//    }
//
//    public void excluir(String sql) {
//        try {
//            Connection conexao = ConnectionFactory.getConnection();
//            stm = conexao.createStatement();
//            int resultado = stm.executeUpdate(sql);
//
//            if (resultado == 1) {
//                JOptionPane.showMessageDialog(null, "Exclusão Efetuada com Sucesso!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Erro na Exclusão");
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro na Exclusão SQL. \n" + ex.getMessage());
//        }
//    }
//
//    public ResultSet consultar(String sql) {
//        ResultSet rs = null;
//        try {
//            Connection conexao = ConnectionFactory.getConnection();
//            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //executa SQL
//            rs = stm.executeQuery(sql); //obtem dados da consulta SQL 
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro na Consulta SQL. \n" + ex.getMessage());
//        }
//        return rs;
//    }
//}
//
