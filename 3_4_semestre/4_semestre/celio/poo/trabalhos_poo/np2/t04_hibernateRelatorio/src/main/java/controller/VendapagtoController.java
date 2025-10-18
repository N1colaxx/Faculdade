package controller;

import model.VendaPagtoModel;
import dao.VendaPagtoDao;
import relatorios.VendaPagtoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendapagtoController {

    VendaPagtoDao vendapagtoDao; 

    
    public VendapagtoController() {
        vendapagtoDao = new VendaPagtoDao();
    }
    
    public ArrayList<VendaPagtoModel> consultar(String filtro) {
        return vendapagtoDao.consultar(filtro);
    }
    
    public Exception imprimir() {
        Exception retorno = null;
        try {
            VendaPagtoRelatorio relatorio = new VendaPagtoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaPagtoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }


    
    public VendaPagtoDao getDao() {
        return vendapagtoDao;
    }
    

    
}



