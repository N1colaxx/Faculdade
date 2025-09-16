package controller;


import dao.FornecedorDao;
import model.FornecedorModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorController {

    private List<FornecedorModel> listaFornecedores;

    public ArrayList<FornecedorModel> consultar(String filtro) throws SQLException {
        listaFornecedores = new FornecedorDao().consultar(filtro);
        return (ArrayList<FornecedorModel>) listaFornecedores;
    }

    public void excluir(FornecedorModel f) throws SQLException {
        new FornecedorDao().excluirPorPesCodigo(f.getPES_CODIGO());
    }

    public void adicionar(FornecedorModel f) throws SQLException {
        new FornecedorDao().adicionar(f);
    }

    public void alterar(FornecedorModel f) throws SQLException {
        new FornecedorDao().alterar(f);
    }

    public void gravar(String operacao, FornecedorModel f) throws SQLException {
        if ("incluir".equalsIgnoreCase(operacao)) adicionar(f);
        else if ("alterar".equalsIgnoreCase(operacao)) alterar(f);
    }
}
