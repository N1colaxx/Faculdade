package controller;

import dao.ProdutoDao;
import model.ProdutoModel;
import relatorios.ProdutoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoController implements GenericController<ProdutoModel> {

    ProdutoDao produtoDao;

    public ProdutoController() {
        produtoDao = new ProdutoDao();
    }

    @Override
    public void incluir(ProdutoModel obj) throws Exception {
        produtoDao.incluir(obj);
    }

    @Override
    public void alterar(ProdutoModel obj) throws Exception {
        produtoDao.alterar(obj);
    }

    @Override
    public void excluir(ProdutoModel obj) throws Exception {
        produtoDao.excluir(obj);
    }

    @Override
    public ArrayList<ProdutoModel> consultar(String filtro) {
        return produtoDao.consultar(filtro);
    }
    

    @Override
    public void gravar(ProdutoModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }
    
    public ProdutoModel get(Integer cod) throws Exception {
        return produtoDao.get(cod); 
    }

    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            ProdutoRelatorio relatorio = new ProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<ProdutoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }
    

}
