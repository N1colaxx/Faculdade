package controller;


import model.VendaModel;
import dao.VendaDao;
import relatorios.VendaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VendaController implements GenericController<VendaModel> {

    VendaDao vendaDao; 
    
    public VendaController() {
        vendaDao = new VendaDao();
    }

    @Override
    public void incluir(VendaModel obj) throws Exception {
        vendaDao.incluir(obj);
    }

    @Override
    public void alterar(VendaModel obj) throws Exception {
        vendaDao.alterar(obj);
    }

    @Override
    public void excluir(VendaModel obj) throws Exception {
        vendaDao.excluir(obj);
    }

    @Override
    public ArrayList<VendaModel> consultar(String filtro) {
        return vendaDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }
    

    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            VendaRelatorio relatorio = new VendaRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    public VendaModel buscarPorCodigo(Integer cod) throws Exception {
        return vendaDao.get(cod); 
    }
    
    public VendaDao getDao() {
        return vendaDao;
    }
    
}


