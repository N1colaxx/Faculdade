package controller;

import dao.ProdutoDao;
import model.ProdutoModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private List<ProdutoModel> listaProdutos;

    public ArrayList<ProdutoModel> consultar(String filtro) throws SQLException {
        listaProdutos = new ProdutoDao().consultar(filtro);
        return (ArrayList<ProdutoModel>) listaProdutos;
    }

    public void excluir(ProdutoModel produto) throws SQLException {
        ProdutoDao dao = new ProdutoDao();
        dao.excluir(produto);
    }

    public void adicionar(ProdutoModel produto) throws SQLException {
        ProdutoDao dao = new ProdutoDao();
        dao.adicionar(produto);
    }

    public void alterar(ProdutoModel produto) throws SQLException {
        ProdutoDao dao = new ProdutoDao();
        dao.alterar(produto);
    }

    public void gravar(String operacao, ProdutoModel produto) throws SQLException {
        if ("incluir".equalsIgnoreCase(operacao)) {
            adicionar(produto);
        } else if ("alterar".equalsIgnoreCase(operacao)) {
            alterar(produto);
        }
    }
}
