package dao;

import conexao.Conexao;
import model.ClienteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteDao {

    private final Connection conexao;

    public ClienteDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /**
     * Cadastra um cliente (pessoa + cliente) na mesma transação.
     *  cliente objeto com dados herdados de pessoa + dados específicos de cliente
     *  SQLException se ocorrer erro em qualquer etapa (faz rollback)
     */
    public void adicionar(ClienteModel cliente) throws SQLException {
        // Vamos controlar a transação manualmente: pessoa + cliente devem ser atômicos
        boolean oldAutoCommit = conexao.getAutoCommit();
        conexao.setAutoCommit(false);

        // 1) INSERT pessoa com RETURNING pes_codigo
        final String sqlPessoa =
                "INSERT INTO pessoa (" +
                "  pes_nome, pes_fisica, pes_cpfcnpj, pes_rgie, pes_cadastro, " +
                "  pes_endereco, pes_numero, pes_complemento, pes_bairro, " +
                "  pes_cidade, pes_uf, pes_cep, pes_celular, " +
                "  pes_site, pes_email, pes_ativo" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "RETURNING pes_codigo";

        // 2) INSERT em cliente
        final String sqlCliente =
                "INSERT INTO cliente (pes_codigo, cli_limitecred) VALUES (?, ?)";
        
        try (
            PreparedStatement psPessoa = conexao.prepareStatement(sqlPessoa);
        ) {
            // ===== PESSOA =====
            psPessoa.setString(1,  cliente.getPES_NOME());
            psPessoa.setString(2,  cliente.getPES_FISICA()); // 'S'/'N'
            psPessoa.setString(3,  cliente.getPES_CPFCNPJ());
            psPessoa.setString(4,  cliente.getPES_RGIE());

            // LocalDate -> DATE 
            if (cliente.getPES_CADASTRO() != null) {
                psPessoa.setDate(5, java.sql.Date.valueOf(cliente.getPES_CADASTRO()));
            } else {
                psPessoa.setDate(5, null);
            }

            psPessoa.setString(6,  cliente.getPES_ENDERECO());
            psPessoa.setString(7,  cliente.getPES_NUMERO());
            psPessoa.setString(8,  cliente.getPES_COMPLEMENTO());
            psPessoa.setString(9,  cliente.getPES_BAIRRO());
            psPessoa.setString(10, cliente.getPES_CIDADE());
            psPessoa.setString(11, cliente.getPES_UF());
            psPessoa.setString(12, cliente.getPES_CEP());
            psPessoa.setString(13, cliente.getPES_CELULAR()); // ATENÇÃO: coluna é pes_celular (sem 'c' extra)
            psPessoa.setString(14, cliente.getPES_SITE());
            psPessoa.setString(15, cliente.getPES_EMAIL());
            psPessoa.setString(16, cliente.getPES_ATIVO());   // 'S'/'N'

            int pesCodigoGerado;
            try (ResultSet rs = psPessoa.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Falha ao obter pes_codigo gerado (RETURNING).");
                }
                
                pesCodigoGerado = rs.getInt("pes_codigo");
            }

            // ===== INSERT em CLIENTE =====
            try (PreparedStatement psCliente = conexao.prepareStatement(sqlCliente)) {
                
                psCliente.setInt(1, pesCodigoGerado);
                psCliente.setDouble(2, cliente.getCLI_LIMITECRED());

                int rows = psCliente.executeUpdate();
                if (rows != 1) {
                    throw new SQLException("Insert em cliente não afetou 1 linha (rows = " + rows + ").");
                }
            }

            // Tudo ok -> COMMIT
            conexao.commit();

        } catch (SQLException e) {
            // Deu ruim em qualquer ponto -> ROLLBACK
            try {
                conexao.rollback();
                System.out.println("\n ROLLBACK executado em cliente... OP insert cliente (FALHOU)");
            } catch (SQLException ex) {
                System.out.println("\n ERRO! ao executar ROLLBACK em CLIENTE \n " + ex);

            }
            
        } finally {
            // Restaura o autocommit original
            try {
                conexao.setAutoCommit(oldAutoCommit);
            } catch (SQLException ignore) { }
        }
    }

    public void alterar(ClienteModel cliente) throws SQLException {
        boolean old = conexao.getAutoCommit();
        conexao.setAutoCommit(false);

        final String sqlPessoa =
            "UPDATE pessoa SET " +
            "  pes_nome=?, pes_fisica=?, pes_cpfcnpj=?, pes_rgie=?, pes_cadastro=?, " +
            "  pes_endereco=?, pes_numero=?, pes_complemento=?, pes_bairro=?, " +
            "  pes_cidade=?, pes_uf=?, pes_cep=?, pes_celular=?, " +
            "  pes_site=?, pes_email=?, pes_ativo=? " +
            "WHERE pes_codigo=?";

        final String sqlCliente =
            "UPDATE cliente SET cli_limitecred=? WHERE pes_codigo=?";

        try (
            PreparedStatement psPessoa = conexao.prepareStatement(sqlPessoa);
            PreparedStatement psCliente = conexao.prepareStatement(sqlCliente)
        ) {
            // pessoa
            psPessoa.setString(1,  cliente.getPES_NOME());
            psPessoa.setString(2,  cliente.getPES_FISICA());
            psPessoa.setString(3,  cliente.getPES_CPFCNPJ());
            psPessoa.setString(4,  cliente.getPES_RGIE());
            if (cliente.getPES_CADASTRO() != null) {
                psPessoa.setDate(5, java.sql.Date.valueOf(cliente.getPES_CADASTRO()));
            } else {
                psPessoa.setDate(5, null);
            }
            psPessoa.setString(6,  cliente.getPES_ENDERECO());
            psPessoa.setString(7,  cliente.getPES_NUMERO());
            psPessoa.setString(8,  cliente.getPES_COMPLEMENTO());
            psPessoa.setString(9,  cliente.getPES_BAIRRO());
            psPessoa.setString(10, cliente.getPES_CIDADE());
            psPessoa.setString(11, cliente.getPES_UF());
            psPessoa.setString(12, cliente.getPES_CEP());
            psPessoa.setString(13, cliente.getPES_CELULAR());
            psPessoa.setString(14, cliente.getPES_SITE());
            psPessoa.setString(15, cliente.getPES_EMAIL());
            psPessoa.setString(16, cliente.getPES_ATIVO());
            psPessoa.setInt(17, cliente.getPES_CODIGO());

            int r1 = psPessoa.executeUpdate();
            if (r1 != 1) throw new SQLException("Update pessoa afetou " + r1 + " linhas.");

            // cliente
            psCliente.setDouble(1, cliente.getCLI_LIMITECRED());
            psCliente.setInt(2, cliente.getPES_CODIGO());

            int r2 = psCliente.executeUpdate();
            if (r2 != 1) throw new SQLException("Update cliente afetou " + r2 + " linhas.");

            conexao.commit();
        } catch (SQLException e) {
            try { conexao.rollback(); } catch (SQLException ignore) {}
            throw e;
        } finally {
            try { conexao.setAutoCommit(old); } catch (SQLException ignore) {}
        }
    }

    
    public java.util.ArrayList<ClienteModel> consultar(String condicao) throws SQLException {
        java.util.ArrayList<ClienteModel> lista = new java.util.ArrayList<>();

        String sql =
            "SELECT " +
            "  p.pes_codigo, p.pes_nome, p.pes_fisica, p.pes_cpfcnpj, p.pes_rgie, p.pes_cadastro, " +
            "  p.pes_endereco, p.pes_numero, p.pes_complemento, p.pes_bairro, " +
            "  p.pes_cidade, p.pes_uf, p.pes_cep, p.pes_celular, " +
            "  p.pes_site, p.pes_email, p.pes_ativo, " +
            "  c.cli_codigo, c.cli_limitecred " +
            "FROM cliente c " +
            "JOIN pessoa p ON p.pes_codigo = c.pes_codigo ";

        if (condicao != null && !condicao.trim().isEmpty()) {
            sql += "WHERE " + condicao;  // cuidado: preferir parâmetros (?) p/ evitar SQL injection
        }

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapCliente(rs));
            }
        }
        return lista;
    }


    
    public void excluirPorPesCodigo(int pesCodigo) throws SQLException {
        final String sql = "DELETE FROM cliente WHERE pes_codigo=?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, pesCodigo);
            int r = ps.executeUpdate();
            if (r != 1) throw new SQLException("Delete cliente por pes_codigo afetou " + r + " linhas.");
        }
    }

    
        private ClienteModel mapCliente(ResultSet rs) throws SQLException {
        ClienteModel c = new ClienteModel();

        // Pessoa
        c.setPES_CODIGO(rs.getInt("pes_codigo"));
        c.setPES_NOME(rs.getString("pes_nome"));
        c.setPES_FISICA(rs.getString("pes_fisica"));
        c.setPES_CPFCNPJ(rs.getString("pes_cpfcnpj"));
        c.setPES_RGIE(rs.getString("pes_rgie"));

        java.sql.Date d = rs.getDate("pes_cadastro");
        c.setPES_CADASTRO(d == null ? null : d.toLocalDate());

        c.setPES_ENDERECO(rs.getString("pes_endereco"));
        c.setPES_NUMERO(rs.getString("pes_numero"));
        c.setPES_COMPLEMENTO(rs.getString("pes_complemento"));
        c.setPES_BAIRRO(rs.getString("pes_bairro"));
        c.setPES_CIDADE(rs.getString("pes_cidade"));
        c.setPES_UF(rs.getString("pes_uf"));
        c.setPES_CEP(rs.getString("pes_cep"));
        c.setPES_CELULAR(rs.getString("pes_celular"));
        c.setPES_SITE(rs.getString("pes_site"));
        c.setPES_EMAIL(rs.getString("pes_email"));
        c.setPES_ATIVO(rs.getString("pes_ativo"));

        // Cliente
        c.setCLI_CODIGO(rs.getInt("cli_codigo"));
        c.setCLI_LIMITECRED(rs.getDouble("cli_limitecred")); // se voltar a BigDecimal, use getBigDecimal

        return c;
    }

}
