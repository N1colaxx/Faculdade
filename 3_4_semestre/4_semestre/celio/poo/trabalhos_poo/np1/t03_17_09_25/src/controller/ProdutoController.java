package controller;


import dao.ProdutoDao;
import model.ProdutoModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ItemCompraModel;
import model.ItemVendaModel;

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
    
    /**
     * PARA COMPRAS
    **/
    
        /** Controller simples de produto para COMPRA. */
        public ItemCompraModel buscarPorCodigoCompra(int cod) throws SQLException {
            return new ProdutoDao().buscarPorCodigoCompra(cod);
        }
    
    /**
     * PARA VENDAS
     */
    
    public ItemVendaModel buscarPorCodigoVenda(int cod) throws SQLException {
        return new ProdutoDao().buscarPorCodigoVenda(cod);
    }
}
