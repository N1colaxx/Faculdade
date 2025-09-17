package dao;

import conexao.Conexao;
import model.ProdutoVendaModel;
import java.sql.*;

/** Busca produto por código para preencher Nome/Un/Preço. */
public class ProdutoVendaDao {
    private final Connection conexao;

    public ProdutoVendaDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /** Retorna produto ativo pelo código; null se não existir ou inativo. */
    public ProdutoVendaModel buscarPorCodigo(int proCodigo) throws SQLException {
        String sql = "SELECT pro_codigo, pro_nome, pro_unidade, pro_preco, pro_ativo, pro_cadastro " +
                     "FROM produto WHERE pro_codigo=?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, proCodigo);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    if (!"S".equalsIgnoreCase(rs.getString("pro_ativo"))) return null;
                    ProdutoVendaModel p = new ProdutoVendaModel();
                    p.setPRO_CODIGO(rs.getInt("pro_codigo"));
                    p.setPRO_NOME(rs.getString("pro_nome"));
                    p.setPRO_UNIDADE(rs.getString("pro_unidade"));
                    p.setPRO_PRECO(rs.getDouble("pro_preco"));
                    Date d = rs.getDate("pro_cadastro");
                    p.setPRO_CADASTRO(d == null ? null : d.toLocalDate());
                    return p;
                }
            }
        }
        return null;
    }
}
