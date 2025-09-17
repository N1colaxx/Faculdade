package controller;


import dao.CompraDao;
import model.CompraModel;
import model.CompraProdutoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Controller da COMPRA. */
public class CompraController {
    private List<CompraModel> lista;

    public ArrayList<CompraModel> consultar(String filtro) throws SQLException {
        lista = new CompraDao().consultar(filtro);
        return (ArrayList<CompraModel>) lista;
    }

    public void excluir(CompraModel compra) throws SQLException {
        new CompraDao().excluir(compra);
    }

    public void gravar(String operacao, CompraModel compra,
                       ArrayList<CompraProdutoModel> itens) throws SQLException {
        new CompraDao().gravarTransacao(operacao, compra, itens);
    }

    public ResultSet consultarCompraProdutoRS(String filtro) throws SQLException {
        return new CompraDao().consultarCompraProdutoRS(filtro);
    }
}
