package controller;

import dao.ProdutoVendaDao;
import model.ProdutoVendaModel;
import java.sql.SQLException;

/** Controller de Produto (para a tela de venda). */
public class ProdutoVendaController {
    public ProdutoVendaModel buscarPorCodigo(int cod) throws SQLException {
        return new ProdutoVendaDao().buscarPorCodigo(cod);
    }
}
