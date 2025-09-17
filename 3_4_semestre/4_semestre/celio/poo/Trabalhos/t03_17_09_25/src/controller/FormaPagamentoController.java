package controller;


import dao.FormaPagamentoDao;
import model.FormapagtoModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoController {

    private List<FormapagtoModel> lista;

    public ArrayList<FormapagtoModel> consultar(String filtro) throws SQLException {
        lista = new FormaPagamentoDao().consultar(filtro);
        return (ArrayList<FormapagtoModel>) lista;
    }

    public void excluir(FormapagtoModel fpg) throws SQLException {
        FormaPagamentoDao dao = new FormaPagamentoDao();
        dao.excluir(fpg);
    }

    public void adicionar(FormapagtoModel fpg) throws SQLException {
        FormaPagamentoDao dao = new FormaPagamentoDao();
        dao.adicionar(fpg);
    }

    public void alterar(FormapagtoModel fpg) throws SQLException {
        FormaPagamentoDao dao = new FormaPagamentoDao();
        dao.alterar(fpg);
    }

    public void gravar(String operacao, FormapagtoModel fpg) throws SQLException {
        if ("incluir".equalsIgnoreCase(operacao)) {
            adicionar(fpg);
        } else if ("alterar".equalsIgnoreCase(operacao)) {
            alterar(fpg);
        }
    }
}
