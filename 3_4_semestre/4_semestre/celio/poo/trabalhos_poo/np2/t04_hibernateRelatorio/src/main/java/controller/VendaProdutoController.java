package controller;

import model.VendaProdutoModel;
import dao.VendaProdutoDao;
import relatorios.VendaProdutoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendaProdutoController {

    VendaProdutoDao vendaProdutoDao;
    
    public VendaProdutoController() {
        vendaProdutoDao = new VendaProdutoDao();
    }
    
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        return vendaProdutoDao.consultar(filtro);
    }
    
    public Exception imprimir() {
        Exception retorno = null;
        try {
            VendaProdutoRelatorio relatorio = new VendaProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaProdutoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }
    

    

    
    
    public VendaProdutoDao getDao() {
        return vendaProdutoDao;
    }    
}


