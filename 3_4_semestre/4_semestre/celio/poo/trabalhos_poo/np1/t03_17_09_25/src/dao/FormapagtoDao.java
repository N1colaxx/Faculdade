package dao;

import conexao.Conexao;
import model.FormaPagtoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormapagtoDao {

    private Connection conexao = null;

    public FormapagtoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void adicionar(FormaPagtoModel fpg) throws SQLException {
        String sql = "INSERT INTO formapagto (fpg_nome, fpg_ativo) VALUES (?, ?)";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, fpg.getFPG_NOME());
        stm.setString(2, fpg.getFPG_ATIVO());
        stm.execute();
        stm.close();
    }

    public void alterar(FormaPagtoModel fpg) throws SQLException {
        String sql = "UPDATE formapagto SET fpg_nome = ?, fpg_ativo = ? WHERE fpg_codigo = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, fpg.getFPG_NOME());
        stm.setString(2, fpg.getFPG_ATIVO());
        stm.setInt(3, fpg.getFPG_CODIGO());
        stm.execute();
        stm.close();
    }

    public void excluir(FormaPagtoModel fpg) throws SQLException {
        String sql = "DELETE FROM formapagto WHERE fpg_codigo = ?";
        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setInt(1, fpg.getFPG_CODIGO());
        stm.execute();
        stm.close();
    }

    public ArrayList<FormaPagtoModel> consultar(String condicao) throws SQLException {
        ArrayList<FormaPagtoModel> lista = new ArrayList<>();
        String sql = "SELECT fpg_codigo, fpg_nome, fpg_ativo FROM formapagto";
        if (condicao != null && !condicao.trim().isEmpty()) {
            sql += " WHERE " + condicao;
        }

        PreparedStatement stm = conexao.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            FormaPagtoModel f = new FormaPagtoModel();
            f.setFPG_CODIGO(rs.getInt("fpg_codigo"));
            f.setFPG_NOME(rs.getString("fpg_nome"));
            f.setFPG_ATIVO(rs.getString("fpg_ativo"));
            lista.add(f);
        }
        rs.close();
        stm.close();
        return lista;
    }
    
    
        /** 
         * ISSO PARA VENDAS
         * 
         * Lista nomes das formas de pagamento ativas.  */
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
