package controller;

import model.CompraProdutoModel;
import dao.CompraProdutoDao;
import relatorios.CompraProdutoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompraProdutoController {
    
    CompraProdutoDao CompraProdutoDao;
    
    public CompraProdutoController() {
        CompraProdutoDao = new CompraProdutoDao();
    }
    
    public ArrayList<CompraProdutoModel> consultar(String filtro) {
        return CompraProdutoDao.consultar(filtro);
    }
    
    public Exception imprimir() {
        Exception retorno = null;
        try {
            CompraProdutoRelatorio relatorio = new CompraProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<CompraProdutoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }
    
}



