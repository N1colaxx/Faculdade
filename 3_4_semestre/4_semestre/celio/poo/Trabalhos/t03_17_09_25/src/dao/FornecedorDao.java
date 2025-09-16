package dao;

import conexao.Conexao;
import model.FornecedorModel;

import java.sql.*;
import java.util.ArrayList;

public class FornecedorDao {

    private final Connection conexao;

    public FornecedorDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void adicionar(FornecedorModel forn) throws SQLException {
        boolean oldAutoCommit = conexao.getAutoCommit();
        conexao.setAutoCommit(false);

        final String sqlPessoa =
                "INSERT INTO pessoa (" +
                "  pes_nome, pes_fantasia, pes_fisica, pes_cpfcnpj, pes_rgie, pes_cadastro, " +
                "  pes_endereco, pes_numero, pes_complemento, pes_bairro, " +
                "  pes_cidade, pes_uf, pes_cep, pes_fone1, pes_fone2, pes_celular, " +
                "  pes_site, pes_email, pes_ativo" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING pes_codigo";

        final String sqlFornecedor =
                "INSERT INTO fornecedor (pes_codigo, for_contato) VALUES (?, ?)";

        try (PreparedStatement psPessoa = conexao.prepareStatement(sqlPessoa)) {
            psPessoa.setString(1, forn.getPES_NOME());
            psPessoa.setString(2, forn.getPES_FANTAZIA());
            psPessoa.setString(3, forn.getPES_FISICA());
            psPessoa.setString(4, forn.getPES_CPFCNPJ());
            psPessoa.setString(5, forn.getPES_RGIE());

            if (forn.getPES_CADASTRO() != null) {
                psPessoa.setDate(6, Date.valueOf(forn.getPES_CADASTRO()));
            } else {
                psPessoa.setDate(6, null);
            }

            psPessoa.setString(7, forn.getPES_ENDERECO());
            psPessoa.setString(8, forn.getPES_NUMERO());
            psPessoa.setString(9, forn.getPES_COMPLEMENTO());
            psPessoa.setString(10, forn.getPES_BAIRRO());
            psPessoa.setString(11, forn.getPES_CIDADE());
            psPessoa.setString(12, forn.getPES_UF());
            psPessoa.setString(13, forn.getPES_CEP());
            psPessoa.setString(14, forn.getPES_FONE1());
            psPessoa.setString(15, forn.getPES_FONE2());
            psPessoa.setString(16, forn.getPES_CELULAR());
            psPessoa.setString(17, forn.getPES_SITE());
            psPessoa.setString(18, forn.getPES_EMAIL());
            psPessoa.setString(19, forn.getPES_ATIVO());

            int pesCodigoGerado;
            try (ResultSet rs = psPessoa.executeQuery()) {
                if (!rs.next()) throw new SQLException("Falha ao obter pes_codigo.");
                pesCodigoGerado = rs.getInt("pes_codigo");
            }

            try (PreparedStatement psForn = conexao.prepareStatement(sqlFornecedor)) {
                psForn.setInt(1, pesCodigoGerado);
                psForn.setString(2, forn.getFOR_CONTATO());
                int rows = psForn.executeUpdate();
                if (rows != 1) throw new SQLException("Insert fornecedor falhou.");
            }

            conexao.commit();
        } catch (SQLException e) {
            try { conexao.rollback(); } catch (SQLException ignore) {}
            throw e;
        } finally {
            try { conexao.setAutoCommit(oldAutoCommit); } catch (SQLException ignore) {}
        }
    }

    public void alterar(FornecedorModel forn) throws SQLException {
        boolean old = conexao.getAutoCommit();
        conexao.setAutoCommit(false);

        final String sqlPessoa =
                "UPDATE pessoa SET " +
                "pes_nome=?, pes_fantasia=?, pes_fisica=?, pes_cpfcnpj=?, pes_rgie=?, pes_cadastro=?, " +
                "pes_endereco=?, pes_numero=?, pes_complemento=?, pes_bairro=?, " +
                "pes_cidade=?, pes_uf=?, pes_cep=?, pes_fone1=?, pes_fone2=?, pes_celular=?, " +
                "pes_site=?, pes_email=?, pes_ativo=? WHERE pes_codigo=?";

        final String sqlFornecedor =
                "UPDATE fornecedor SET for_contato=? WHERE pes_codigo=?";

        try (PreparedStatement psPessoa = conexao.prepareStatement(sqlPessoa);
             PreparedStatement psForn = conexao.prepareStatement(sqlFornecedor)) {

            psPessoa.setString(1, forn.getPES_NOME());
            psPessoa.setString(2, forn.getPES_FANTAZIA());
            psPessoa.setString(3, forn.getPES_FISICA());
            psPessoa.setString(4, forn.getPES_CPFCNPJ());
            psPessoa.setString(5, forn.getPES_RGIE());
            if (forn.getPES_CADASTRO() != null) {
                psPessoa.setDate(6, Date.valueOf(forn.getPES_CADASTRO()));
            } else {
                psPessoa.setDate(6, null);
            }
            psPessoa.setString(7, forn.getPES_ENDERECO());
            psPessoa.setString(8, forn.getPES_NUMERO());
            psPessoa.setString(9, forn.getPES_COMPLEMENTO());
            psPessoa.setString(10, forn.getPES_BAIRRO());
            psPessoa.setString(11, forn.getPES_CIDADE());
            psPessoa.setString(12, forn.getPES_UF());
            psPessoa.setString(13, forn.getPES_CEP());
            psPessoa.setString(14, forn.getPES_FONE1());
            psPessoa.setString(15, forn.getPES_FONE2());
            psPessoa.setString(16, forn.getPES_CELULAR());
            psPessoa.setString(17, forn.getPES_SITE());
            psPessoa.setString(18, forn.getPES_EMAIL());
            psPessoa.setString(19, forn.getPES_ATIVO());
            psPessoa.setInt(20, forn.getPES_CODIGO());

            int r1 = psPessoa.executeUpdate();
            if (r1 != 1) throw new SQLException("Update pessoa falhou.");

            psForn.setString(1, forn.getFOR_CONTATO());
            psForn.setInt(2, forn.getPES_CODIGO());
            int r2 = psForn.executeUpdate();
            if (r2 != 1) throw new SQLException("Update fornecedor falhou.");

            conexao.commit();
        } catch (SQLException e) {
            try { conexao.rollback(); } catch (SQLException ignore) {}
            throw e;
        } finally {
            try { conexao.setAutoCommit(old); } catch (SQLException ignore) {}
        }
    }

    public ArrayList<FornecedorModel> consultar(String condicao) throws SQLException {
        ArrayList<FornecedorModel> lista = new ArrayList<>();
        String sql = "SELECT p.*, f.for_codigo, f.for_contato " +
                     "FROM fornecedor f JOIN pessoa p ON p.pes_codigo=f.pes_codigo ";
        if (condicao != null && !condicao.trim().isEmpty()) {
            sql += "WHERE " + condicao;
        }
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapFornecedor(rs));
            }
        }
        return lista;
    }

    public void excluirPorPesCodigo(int pesCodigo) throws SQLException {
        final String sql = "DELETE FROM fornecedor WHERE pes_codigo=?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, pesCodigo);
            int r = ps.executeUpdate();
            if (r != 1) throw new SQLException("Delete fornecedor falhou.");
        }
    }

    private FornecedorModel mapFornecedor(ResultSet rs) throws SQLException {
        FornecedorModel f = new FornecedorModel();
        f.setPES_CODIGO(rs.getInt("pes_codigo"));
        f.setPES_NOME(rs.getString("pes_nome"));
        f.setPES_FANTAZIA(rs.getString("pes_fantasia"));
        f.setPES_FISICA(rs.getString("pes_fisica"));
        f.setPES_CPFCNPJ(rs.getString("pes_cpfcnpj"));
        f.setPES_RGIE(rs.getString("pes_rgie"));
        Date d = rs.getDate("pes_cadastro");
        f.setPES_CADASTRO(d==null?null:d.toLocalDate());
        f.setPES_ENDERECO(rs.getString("pes_endereco"));
        f.setPES_NUMERO(rs.getString("pes_numero"));
        f.setPES_COMPLEMENTO(rs.getString("pes_complemento"));
        f.setPES_BAIRRO(rs.getString("pes_bairro"));
        f.setPES_CIDADE(rs.getString("pes_cidade"));
        f.setPES_UF(rs.getString("pes_uf"));
        f.setPES_CEP(rs.getString("pes_cep"));
        f.setPES_FONE1(rs.getString("pes_fone1"));
        f.setPES_FONE2(rs.getString("pes_fone2"));
        f.setPES_CELULAR(rs.getString("pes_celular"));
        f.setPES_SITE(rs.getString("pes_site"));
        f.setPES_EMAIL(rs.getString("pes_email"));
        f.setPES_ATIVO(rs.getString("pes_ativo"));
        f.setFOR_CODIGO(rs.getInt("for_codigo"));
        f.setFOR_CONTATO(rs.getString("for_contato"));
        return f;
    }
}
