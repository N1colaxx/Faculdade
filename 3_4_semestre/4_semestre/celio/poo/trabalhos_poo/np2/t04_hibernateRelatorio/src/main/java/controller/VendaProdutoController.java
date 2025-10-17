package controller;

import model.VendaProdutoModel;
import dao.VendaProdutoDao;
import relatorios.VendaProdutoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VendaProdutoController implements GenericController<VendaProdutoModel> {

    VendaProdutoDao vendaProdutoDao; 
    
    private String operacao;
    
    public VendaProdutoController() {
        vendaProdutoDao = new VendaProdutoDao();
        operacao = null;
    }

    @Override
    public void incluir(VendaProdutoModel obj) throws Exception {
        vendaProdutoDao.incluir(obj);
    }

    @Override
    public void alterar(VendaProdutoModel vp) throws Exception {
        vendaProdutoDao.alterar(vp);
    }

    
    
    @Override
    public void excluir(VendaProdutoModel obj) throws Exception {
        vendaProdutoDao.excluir(obj);
    }

    @Override
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        return vendaProdutoDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaProdutoModel obj, String operacao) throws Exception {
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
    
    public VendaProdutoModel get(int cod) throws Exception {
        return vendaProdutoDao.get(cod);
    }
    
        
    public VendaProdutoModel get(int id, Session session) throws Exception {
        System.out.println(" [VendaProdutoController] validar_VendaProduto() iniciado...");
        
        VendaProdutoModel vp_valido = vendaProdutoDao.get(id, session);
        if(vp_valido.getVenda_VendaProduto().getVda_codigo() == null  ||
            vp_valido.getVenda_VendaProduto().getVda_codigo() < 0) {
            JOptionPane.showMessageDialog(null, "ERRO! Não encontramos Venda de produtos com esse Codigo da Venda.");
            return null;
        }
        
        return vp_valido;
    }
    
    /**
     * Getters
     */
    
    public VendaProdutoDao getDao() {
        return vendaProdutoDao;
    }
    
    public String getOperacao() {
        return operacao;
    }
    
}


