package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "12345";

    private ConnectionFactory() { }
    
    public static Connection getConnection() throws SQLException{
        try {
            Class.forName(DRIVER); // carrega o driver
            System.out.println("\n DRIVER carregado (SUCESSO)");
            
        } catch (ClassNotFoundException ex) {
           throw new IllegalAccessError ("ERRO! Driver JBDC não encontrado. \n" + DRIVER + ex);
        }
        
        System.out.println("");        
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
