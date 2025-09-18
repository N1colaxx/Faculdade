package controller;


import dao.FormapagtoDao;
import model.FormaPagtoModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormapagtoController {

    private List<FormaPagtoModel> lista;

    public ArrayList<FormaPagtoModel> consultar(String filtro) throws SQLException {
        lista = new FormapagtoDao().consultar(filtro);
        return (ArrayList<FormaPagtoModel>) lista;
    }

    public void excluir(FormaPagtoModel fpg) throws SQLException {
        FormapagtoDao dao = new FormapagtoDao();
        dao.excluir(fpg);
    }

    public void adicionar(FormaPagtoModel fpg) throws SQLException {
        FormapagtoDao dao = new FormapagtoDao();
        dao.adicionar(fpg);
    }

    public void alterar(FormaPagtoModel fpg) throws SQLException {
        FormapagtoDao dao = new FormapagtoDao();
        dao.alterar(fpg);
    }

    public void gravar(String operacao, FormaPagtoModel fpg) throws SQLException {
        if ("incluir".equalsIgnoreCase(operacao)) {
            adicionar(fpg);
        } else if ("alterar".equalsIgnoreCase(operacao)) {
            alterar(fpg);
        }
    }
    
    /* PARA VENDAS
    * **/
    
    public ArrayList<String> listarNomesAtivos() throws SQLException {
        return new FormapagtoDao().listarNomesAtivos();
    }
    public int codigoPorNome(String nome) throws SQLException {
        return new FormapagtoDao().obterCodigoPorNome(nome);
    }
}
