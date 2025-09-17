package controller;


import dao.CompraProdutoDao;
import model.ProtoCompraProdutoModel;

import java.sql.SQLException;

/** Controller simples de produto para COMPRA. */
public class CompraProdutoController {
    public ProtoCompraProdutoModel buscarPorCodigo(int cod) throws SQLException {
        return new CompraProdutoDao().buscarPorCodigo(cod);
    }
}
