package controller;


import dao.CompraProdutoDao;
import model.ProdutoCompraModel;

import java.sql.SQLException;

/** Controller simples de produto para COMPRA. */
public class ProdutoCompraController {
    public ProdutoCompraModel buscarPorCodigo(int cod) throws SQLException {
        return new CompraProdutoDao().buscarPorCodigo(cod);
    }
}
