    package controller;

import dao.VendaDao;
import model.VendaCompletaModel;
import model.VendaModel;
import model.VendaProdutoModel;
import model.VendaPagtoModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Controller de Venda: orquestra chamadas ao DAO. */
public class VendaController {
    private List<VendaModel> lista;

    public ArrayList<VendaModel> consultar(String filtro) throws SQLException {
        lista = new VendaDao().consultar(filtro);
        return (ArrayList<VendaModel>) lista;
    }

    public void excluir(VendaModel venda) throws SQLException {
        new VendaDao().excluir(venda);
    }

    public void gravar(String operacao, VendaModel venda,
                       ArrayList<VendaProdutoModel> itens,
                       ArrayList<VendaPagtoModel> pgtos) throws SQLException {
        new VendaDao().gravarTransacao(operacao, venda, itens, pgtos);
    }

        /** Para a aba Consulta (venda_produto). */
    public List<Object[]> consultarVendaProduto(String filtro) throws SQLException {
        return new VendaDao().consultarVendaProduto(filtro);
    }
    
    public VendaCompletaModel buscarVendaCompleta(int vda) throws SQLException {
        System.out.println(" [VendaController] executou -> buscarVendaCompleta");
        
        VendaDao dao = new VendaDao();
        VendaCompletaModel dto = new VendaCompletaModel();
        dto.cabecalho = dao.buscarCabecalho(vda);
        dto.itens     = dao.listarItens(vda);
        dto.pgtos     = dao.listarPgtos(vda);
        return dto; 
    }
}
