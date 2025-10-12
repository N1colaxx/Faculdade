package controller;

import model.ClienteModel;
import dao.ClienteDao;
import java.time.LocalDate;
import relatorios.ClienteRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClienteController implements GenericController<ClienteModel> {

    ClienteDao clienteDao;
    private LocalDate dataFiltroConsulta = null;
    
    
    public ClienteController() {
        clienteDao = new ClienteDao();
    }

    @Override
    public void incluir(ClienteModel obj) throws Exception {
        clienteDao.incluir(obj);
    }

    @Override
    public void alterar(ClienteModel obj) throws Exception {
        clienteDao.alterar(obj);
    }

    @Override
    public void excluir(ClienteModel obj) throws Exception {
        clienteDao.excluir(obj);
    }

    @Override
    public ArrayList<ClienteModel> consultar(String filtro) {
        clienteDao.setDataFiltroTemp(dataFiltroConsulta);
        return clienteDao.consultar(filtro);
    }

    @Override
    public void gravar(ClienteModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }
    
    public ClienteModel buscarPorCodigo(Integer cod) throws Exception {
        ClienteDao dao = new ClienteDao();
        return dao.get(cod); 
    }


    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            ClienteRelatorio relatorio = new ClienteRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<ClienteModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    
       
    public ClienteDao getDao() {
        return clienteDao;
    }
    
    public void setDataFiltro(LocalDate data) {
        this.dataFiltroConsulta = data;
    }
}

