package controller;

import dao.VendaProdutoDao;
import model.VendaProdutoModel;
import java.sql.SQLException;

/** Controller de Produto (para a tela de venda). */
public class VendaProdutoController {
    public VendaProdutoModel buscarPorCodigo(int cod) throws SQLException {
        return new VendaProdutoDao().buscarPorCodigo(cod);
    }
}
