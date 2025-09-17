package dao;

import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;

/** DAO de Forma de Pagamento: lista nomes ativos e obtém código pelo nome. */
public class FormapagtoDao {
    private final Connection conexao;

    public FormapagtoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /** Lista nomes das formas de pagamento ativas. */
    public ArrayList<String> listarNomesAtivos() throws SQLException {
        ArrayList<String> nomes = new ArrayList<>();
        String sql = "SELECT fpg_nome FROM formapagto WHERE COALESCE(fpg_ativo,'S')='S' ORDER BY fpg_nome";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                nomes.add(rs.getString(1));
            }
        }
        return nomes;
    }

    /** Obtém o código (PK) pelo nome. Retorna -1 se não encontrar. */
    public int obterCodigoPorNome(String nome) throws SQLException {
        String sql = "SELECT fpg_codigo FROM formapagto WHERE fpg_nome = ? LIMIT 1";
        try (PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}
