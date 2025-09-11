package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {

    private final static String DRIVER = "org.postgresql.Driver";
    private final static String BANCO = "jdbc:postgresql://localhost:5432/compravenda";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "unip";

    private static Connection conexao;
    private Statement stm;

    public Conexao() {
        try {
            conexao = DriverManager.getConnection(BANCO, USUARIO, SENHA); //cria conexão
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // configura o Statement
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar no banco. \n" + ex.getMessage());
        }
    }

    public static Connection getConexao() {
        return conexao;
    }

    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Falha ao fechar conexao.\n" + ex.getMessage());
        }
    }
}
