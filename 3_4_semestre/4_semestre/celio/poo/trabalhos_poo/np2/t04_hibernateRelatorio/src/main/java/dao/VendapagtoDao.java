package dao;


import model.VendaPagtoModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VendapagtoDao implements GenericDao<VendaPagtoModel> {
    
    private String operacao;
    
    public VendapagtoDao() {
        operacao = null;
    }
    
    @Override
    public void incluir(VendaPagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] INCLUIR iniciado \n"); 
        System.out.println(" vdp_codigo = " + objModel.getVdp_codigo() );
        System.out.println(" vda_codigo = " + objModel.getVenda_VendaPagto().getVda_codigo());        
        System.out.println(" pfg_codigo = " + objModel.getFormapagto_VendaPagto().getFPG_CODIGO());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setVdp_codigo(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }
    
    public void incluir(VendaPagtoModel objModel, Session session) throws Exception {
        System.out.println("\n [VendaPagtoDao] INCLUIR iniciado");
        System.out.println(" vdp_codigo = " + objModel.getVdp_codigo() );        
        System.out.println(" vda_codigo = " + objModel.getVenda_VendaPagto().getVda_codigo());
        System.out.println(" fpg_codigo = " + objModel.getFormapagto_VendaPagto().getFPG_CODIGO());
        
        Transaction tx = session.beginTransaction();
        try{
            session.persist(objModel);
        }catch (Exception ex) {
            if(objModel.getVdp_codigo() == null) {
                System.out.println(" [VendaPagtoDao] ERRO! ao incluir Venda_Pagto");
                JOptionPane.showMessageDialog(null, "ERRO! ao gravar Venda_Pagto, ID = " + objModel.getVdp_codigo()); 
                tx.rollback();
            }
        }
        
        tx.commit();
        System.out.println(" [VendaPagtoDao] Sucesso! Venda_Pagto INCLUIDA ID = " + objModel.getVdp_codigo());
    }

    @Override
    public void alterar(VendaPagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] ALTERAR iniciado");
        
        // VendaPagto com dados NOVOS
        Integer new_cod_vdp = objModel.getVdp_codigo();
        System.out.println(" vdp_codigo (new_venda) = " + objModel.getVdp_codigo());

        // VendaPagto com dados ANTIGOS;
        VendaPagtoModel old_vdp = get(new_cod_vdp);
        Integer old_cod_vdp   = old_vdp.getVdp_codigo();
        
        if (old_vdp == null) {
            throw new Exception("  Vendapagto não encontrada no banco para atualização!");
        }
        
        if (new_cod_vdp != old_cod_vdp) return;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try{
           session.merge(objModel); // MERGE vai atualizar tanto pessoa quanto cliente
           tx.commit();
        }catch (Exception ex) {
           if(tx != null) {
                System.out.println(" [VendaPagtoDao] ERRO! ao Alterar Venda_Pagto ID = " + objModel.getVdp_codigo());
                tx.rollback();
           }
       } finally {
               session.close();              
        }
    }
    
    
    @Override
    public ArrayList<VendaPagtoModel> consultar(String filtro) {
        System.out.println("\n [VendapagtoDao] CONSULTAR iniciado");
        
        String tabVendapagto = VendaPagtoModel.class.getName();
        String hql = " FROM " + tabVendapagto + " vpg " 
                +    " JOIN FETCH vpg.venda_vendaPagto v "
                +    " JOIN FETCH vpg.formapagto f";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        
        hql += " ORDER BY v.vda_codigo";
        
        System.out.println(" [VendapagtoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaPagtoModel.class);
            
            if(filtro != null && filtro.contains(":vda_codigo")) {
                query.setParameter("vda_codigo", operacao);
                System.out.println(" [VendaProdutoDao] parâmetro da consulta = " + operacao);
            }
                        
            List<VendaPagtoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(VendaPagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

           VendaPagtoModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public VendaPagtoModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaPagtoModel) session.getReference(VendaPagtoModel.class, id);
    }
    
    
    // Pesquisa por VDA_CODIGO
    public List<VendaPagtoModel> consultarPorVenda(Integer VDA_CODIGO) throws Exception {
        String tabVendapagto = VendaPagtoModel.class.getName();
        
        String hql = " FROM " + tabVendapagto + " vpg " 
                +    " JOIN FETCH venpag.venda v "
                +    " JOIN FETCH venpag.formapagto fpg"
                +    " WHERE vda_codigo = :vda_codigo";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaPagtoModel.class);
            
            if (VDA_CODIGO != null && hql.contains(":vda_codigo")) {
                query.setParameter(":vda_codigo", hql);
            }

            List<VendaPagtoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }
    
     public void inserirPgtos(int vdaCodigo, ArrayList<VendaPagtoModel> pgtos) throws Exception {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            for (VendaPagtoModel pagto : pgtos) {
                // garante que a venda esteja associada
                pagto.getVenda_VendaPagto().setVda_codigo(vdaCodigo);

                // persiste o pagamento
                session.persist(pagto);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Erro ao inserir pagamentos da venda: " + e.getMessage(), e);
        }
    }
    
}


