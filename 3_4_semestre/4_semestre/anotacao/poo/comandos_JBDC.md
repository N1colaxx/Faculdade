# Comandos SQL para JBDC

https://castelano.com.br/site/aulas/alpoo/Aula%2005%20-%20Introdu%C3%A7%C3%A3o%20ao%20JDBC.pdf

slid -> 1, 2, 

### 🔑 Principais classes e interfaces do JDBC

`DriverManager` → gerencia a conexão com o banco.
    
`Connection`→ representa a conexão aberta.

`Statement` → executa comandos SQL simples (sem parâmetros).

-   **ResultsSet**
    -   
`PreparedStatement` → executa comandos SQL com parâmetros (mais seguro contra SQL Injection).

`ResultSet` → guarda os resultados de um SELECT.





# Retorno de erro na Consulta ex:

### Dao
``` java
package dao;

import model.Pessoa;
import util.ConnectionFactory;

import java.sql.*;
import java.util.*;

public class PessoaDAO {

    public List<Pessoa> buscarTodos() throws SQLException {
        String sql = "SELECT id, nome FROM pessoa";
        List<Pessoa> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pessoa(rs.getInt("id"), rs.getString("nome")));
            }
        } // try-with-resources já fecha tudo
        return lista;
    }

    public void inserir(Pessoa p) throws SQLException {
        String sql = "INSERT INTO pessoa (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.executeUpdate();
        }
    }
}

```

### Controller
``` java 
package controller;

import dao.PessoaDAO;
import model.Pessoa;
import view.PessoaView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PessoaController {

    private final PessoaDAO dao = new PessoaDAO();
    private final PessoaView view = new PessoaView();

    public List<Pessoa> listar() {
        try {
            return dao.buscarTodos();
        } catch (IllegalStateException e) { // driver ausente, vindo da ConnectionFactory
            view.mostrarErro("Falha ao inicializar o driver do banco.\n" + e.getMessage());
            return Collections.emptyList();
        } catch (SQLException e) {
            String msg = traduzirSQLException(e);
            view.mostrarErro(msg);
            return Collections.emptyList();
        }
    }

    public void criar(Pessoa p) {
        try {
            dao.inserir(p);
        } catch (IllegalStateException e) {
            view.mostrarErro("Falha ao inicializar o driver do banco.\n" + e.getMessage());
        } catch (SQLException e) {
            String msg = traduzirSQLException(e);
            view.mostrarErro(msg);
        }
    }

    // Mapeia códigos SQLSTATE do PostgreSQL para mensagens amigáveis
    private String traduzirSQLException(SQLException e) {
        String sqlState = e.getSQLState(); // pode ser null em alguns drivers
        if (sqlState == null) {
            return "Erro de banco de dados.\n" + e.getMessage();
        }
        switch (sqlState) {
            case "23505": // unique_violation
                return "Registro duplicado (violação de chave única).";
            case "23503": // foreign_key_violation
                return "Este registro está vinculado a outros (violação de chave estrangeira).";
            case "23514": // check_violation
                return "Os dados não atendem a uma restrição (CHECK).";
            case "23502": // not_null_violation
                return "Campo obrigatório não pode ser nulo.";
            case "42P01": // undefined_table
                return "Tabela não encontrada.";
            case "42601": // syntax_error
                return "Erro de sintaxe na SQL.";
            case "28P01": // invalid_password
                return "Usuário ou senha do banco inválidos.";
            case "08001": // sqlclient_unable_to_establish_sqlconnection
            case "08006": // connection_failure
                return "Falha de conexão com o banco de dados.";
            default:
                // fallback: mostra código + mensagem técnica (útil pra debug)
                return "Erro SQL (" + sqlState + "): " + e.getMessage();
        }
    }
}


```

### View 

```java 
package view;

import javax.swing.JOptionPane;

public class PessoaView {

    public void mostrarErro(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // ... seus outros métodos de UI (listar na tela, pegar input etc.)
}


```