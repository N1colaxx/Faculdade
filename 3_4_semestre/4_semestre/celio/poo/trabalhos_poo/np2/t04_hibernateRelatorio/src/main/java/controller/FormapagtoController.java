package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.FormapagtoDao;
import model.FormapagtoModel;
import relatorios.FormapagtoRelatorio;


public class FormapagtoController implements GenericController<FormapagtoModel> {

    FormapagtoDao formapagtoDao;

    public FormapagtoController() {
        formapagtoDao = new FormapagtoDao();
    }

    @Override
    public void incluir(FormapagtoModel obj) throws Exception {
        formapagtoDao.incluir(obj);
    }

    @Override
    public void alterar(FormapagtoModel obj) throws Exception {
        formapagtoDao.alterar(obj);
    }

    @Override
    public void excluir(FormapagtoModel obj) throws Exception {
        formapagtoDao.excluir(obj);
    }

    @Override
    public ArrayList<FormapagtoModel> consultar(String filtro) {
        return formapagtoDao.consultar(filtro);
    }

    @Override
    public void gravar(FormapagtoModel obj, String operacao) throws Exception {
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
            FormapagtoRelatorio relatorio = new FormapagtoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<FormapagtoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

}
