package controller;


import model.FornecedorModel;
import dao.FornecedorDao;
import java.time.LocalDate;
import relatorios.FornecedorRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FornecedorController  implements GenericController<FornecedorModel> {

    FornecedorDao fornecedorDao;
    private LocalDate dataFiltroConsulta = null;
    
    
    public FornecedorController() {
        fornecedorDao = new FornecedorDao();
    }

    @Override
    public void incluir(FornecedorModel obj) throws Exception {
        fornecedorDao.incluir(obj);
    }

    @Override
    public void alterar(FornecedorModel obj) throws Exception {
        fornecedorDao.alterar(obj);
    }

    @Override
    public void excluir(FornecedorModel obj) throws Exception {
        fornecedorDao.excluir(obj);
    }

    @Override
    public ArrayList<FornecedorModel> consultar(String filtro) {
        fornecedorDao.setDataFiltroTemp(dataFiltroConsulta);
        return fornecedorDao.consultar(filtro);
    }

    @Override
    public void gravar(FornecedorModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }
    
    public FornecedorModel buscarPorCodigo(Integer cod) throws Exception {
        FornecedorDao dao = new FornecedorDao();
        return dao.get(cod); 
    }


    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            FornecedorRelatorio relatorio = new FornecedorRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<FornecedorModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    
       
    public FornecedorDao getDao() {
        return fornecedorDao;
    }
    
    public void setDataFiltro(LocalDate data) {
        this.dataFiltroConsulta = data;
    }
}

