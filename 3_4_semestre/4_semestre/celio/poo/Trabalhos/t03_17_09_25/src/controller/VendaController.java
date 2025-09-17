package controller;


import dao.VendaDao;
import model.VendaModel;
import model.VendaItemModel;
import model.VendaPagtoModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaController {
    private List<VendaModel> lista;

    //  esse metodo é para: consultar as vendas pelo filtro informado
    public ArrayList<VendaModel> consultar(String filtro) throws SQLException {
        lista = new VendaDao().consultar(filtro);
        return (ArrayList<VendaModel>) lista;
    }

    //  esse metodo é para: excluir uma venda completa
    public void excluir(VendaModel venda) throws SQLException {
        new VendaDao().excluir(venda);
    }

    //  esse metodo é para: gravar (incluir/alterar) a venda + itens + pagamentos
    public void gravar(String operacao,
                       VendaModel venda,
                       ArrayList<VendaItemModel> itens,
                       ArrayList<VendaPagtoModel> pgtos) throws SQLException {
        new VendaDao().gravarTransacao(operacao, venda, itens, pgtos);
    }
}
