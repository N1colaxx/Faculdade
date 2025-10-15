package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import relatorios.VendaRelatorio;

import dao.VendaDao;
import dao.VendaProdutoDao;
import dao.VendapagtoDao;

import model.VendaModel;
import model.VendaProdutoModel;
import model.VendapagtoModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class VendaController implements GenericController<VendaModel> {

    private VendaDao vendaDao; 
    private VendaProdutoDao vendaProdutoDao;
    private VendapagtoDao vendapagtoDao;
            
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
    
    public void incluir(VendaModel venda, List<VendaProdutoModel> itens, List<VendapagtoModel> pagtos) throws Exception {
        System.out.println("\n [VendaController] void inclui(venda, itens, pagtos) iniciou...");
        
        vendaProdutoDao = new VendaProdutoDao();
        vendapagtoDao = new VendapagtoDao();
        vendaDao = new VendaDao();

        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transacao = null;

        try {
            transacao = session.beginTransaction();

            vendaDao.incluir(venda, session); // session.merge(venda)
            // Após o merge/save, o Hibernate já preenche o ID
            System.out.println("\n [VendaController] Venda salva com ID: " + venda.getVda_codigo());


            if (itens != null) {
                for (VendaProdutoModel p : itens) {
                    p.setVenda_VendaProduto(venda); // vincula o objeto venda, não só o ID
                    vendaProdutoDao.incluir(p, session);
                }
            }

            // 3️⃣ Grava os pagamentos
            if (pagtos != null) {
                for (VendapagtoModel pg : pagtos) {
                    pg.setVenda_Vendapagto(venda);
                    vendapagtoDao.incluir(pg, session);
                }
            }

            transacao.commit(); //
        } catch (Exception e) {
            if (transacao != null) transacao.rollback();
            throw e;
        } finally {
            session.close(); // fecha só aqui
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


