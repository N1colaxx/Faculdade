package controller;

import dao.ClienteDao;
import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import relatorios.VendaRelatorio;

import dao.VendaDao;
import dao.VendaProdutoDao;
import dao.VendapagtoDao;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ClienteModel;
import model.UsuarioModel;

import model.VendaModel;
import model.VendaProdutoModel;
import model.VendaPagtoModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class VendaController implements GenericController<VendaModel> {

    private VendaDao vendaDao = null; 
    private VendaProdutoDao vendaProdutoDao  = null;
    private VendapagtoDao vendaPagtoDao  = null;
            
    public VendaController() {
        vendaDao = new VendaDao();
    }

    @Override
    public void incluir(VendaModel obj) throws Exception {
        vendaDao.incluir(obj);
    }

    @Override
    public void alterar(VendaModel obj) throws Exception {
        vendaDao.alterar(obj);
    }

    @Override
    public void excluir(VendaModel obj) throws Exception {
        vendaDao.excluir(obj);
    }

    @Override
    public ArrayList<VendaModel> consultar(String filtro) {
        return vendaDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaModel obj, String operacao) throws Exception {
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
            VendaRelatorio relatorio = new VendaRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    public VendaModel buscarPorCodigo(Integer cod) throws Exception {
        return vendaDao.get(cod); 
    }
    
    public void incluir(int usu_cod, int cli_cod, LocalDate data_v, double valor_v, double desc_v, double total_v, String obs_v, 
            ArrayList<VendaProdutoModel> itens, 
            ArrayList<VendaPagtoModel> pgtos) throws Exception {
        
        System.out.println("\n [VendaController] void inclui(venda, itens, pagtos) iniciou...");
        vendaProdutoDao = new VendaProdutoDao();
        vendaPagtoDao = new VendapagtoDao();

        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transacao = null;

        UsuarioModel usu_v = new UsuarioDao().get(usu_cod, session);
        ClienteModel cli_v = new ClienteDao().get(cli_cod, session);
        
        if (usu_v == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Usuario Invalido!");
            return;
        }
        
        if (cli_v == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Cliente Invalido!");
            return;
        }
        
        try {
            transacao = session.beginTransaction();
            
            VendaModel venda = new VendaModel();
                venda.setUsu_venda(usu_v);
                venda.setCli_venda(cli_v);
                venda.setListItens_venda(itens);
                venda.setListPagtos_venda(pgtos);
                venda.setVda_data(data_v);
                venda.setVda_valor(valor_v);
                venda.setVda_desconto(desc_v);
                venda.setVda_total(total_v);
                venda.setVda_obs(obs_v);
            
            vendaDao.incluir(venda, session); 
            System.out.println("\n [VendaController] Venda salva com ID: " + venda.getVda_codigo());
            
            for (VendaProdutoModel item : venda.getListItens_venda()) {
                item.setVenda_VendaProduto(venda);
                vendaProdutoDao.incluir(item, session);
            }

            for (VendaPagtoModel pagto : venda.getListPagtos_venda()) {
                pagto.setVenda_Vendapagto(venda);
                vendaPagtoDao.incluir(pagto, session);
            }
            
            transacao.commit();
            System.out.println("[VendaController] Transação concluída com sucesso!");
        
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            throw e;
        } finally {
            session.close();
        }
        
        System.out.println("\n [VendaController] void inclui() terminou");;
    }

    
    
    /**
     * Getters
     */
    
    public VendaDao getDao() {
        return vendaDao;
    }
    
}


