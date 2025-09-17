package dao;


import conexao.Conexao;
import model.ProdutoCompraModel;

import java.sql.*;

/** DAO simples p/ buscar produto para COMPRA (usa custo como preço). */
public class CompraProdutoDao {
    private final Connection conexao;

    public CompraProdutoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    /** Busca produto por código; retorna null se não achar ou ativo != 'S'. */
    public ProdutoCompraModel buscarPorCodigo(int proCodigo) throws SQLException {
        String sql = "SELECT pro_codigo, pro_nome, pro_unidade, pro_custo, pro_ativo, pro_cadastro " +
                     "FROM produto WHERE pro_codigo = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, proCodigo);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    ProdutoCompraModel p = new ProdutoCompraModel();
                    p.setPRO_CODIGO(rs.getInt("pro_codigo"));
                    p.setPRO_NOME(rs.getString("pro_nome"));
                    p.setPRO_UNIDADE(rs.getString("pro_unidade"));
                    p.setPRO_CUSTO(rs.getDouble("pro_custo"));  // <-- preço de COMPRA sugerido
                    p.setPRO_ATIVO(rs.getString("pro_ativo"));
                    Date d = rs.getDate("pro_cadastro");
                    p.setPRO_CADASTRO(d==null?null:d.toLocalDate());

                    if ("S".equalsIgnoreCase(p.getPRO_ATIVO())) return p;
                }
            }
        }
        return null;
    }
}
