package controller;

import dao.ClienteDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ClienteModel;

public class ClienteController {

    private List<ClienteModel> listaClientes;

    public ArrayList<ClienteModel> consultar(String filtro) throws SQLException {
        listaClientes = new ClienteDao().consultar(filtro);
        return (ArrayList<ClienteModel>) listaClientes;
    }

    public void excluir(ClienteModel cliente) throws SQLException {
        // Remove apenas o vínculo de cliente (mantém pessoa).
        // Se quiser apagar pessoa também, crie um método separado chamando excluirClienteEPessoa(...)
        ClienteDao dao = new ClienteDao();
        dao.excluirPorPesCodigo(cliente.getPES_CODIGO());
    }

    public void adicionar(ClienteModel cliente) throws SQLException {
        ClienteDao dao = new ClienteDao();
        dao.adicionar(cliente);
    }

    public void alterar(ClienteModel cliente) throws SQLException {
        ClienteDao dao = new ClienteDao();
        dao.alterar(cliente);
    }

    public void gravar(String operacao, ClienteModel cliente) throws SQLException {
        if ("incluir".equalsIgnoreCase(operacao)) {
            adicionar(cliente);
        } else if ("alterar".equalsIgnoreCase(operacao)) {
            alterar(cliente);
        }
    }
}
