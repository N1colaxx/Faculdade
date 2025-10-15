package dao;


import model.VendapagtoModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VendapagtoDao implements GenericDao<VendapagtoModel> {
    
    private String operacao;
    
    public VendapagtoDao() {
        operacao = null;
    }
    
    @Override
    public void incluir(VendapagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] INCLUIR iniciado \n"); 
        System.out.println(" vdp_codigo = " + objModel.getVdp_codigo() );
        System.out.println(" vda_codigo = " + objModel.getVenda_Vendapagto().getVda_codigo());        
        System.out.println(" pfg_codigo = " + objModel.getFormapagto_Vendapagto().getFPG_CODIGO());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setVdp_codigo(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }
    
    public void incluir(VendapagtoModel objModel, Session session) throws Exception {
        System.out.println("\n [VendapagtoDao] INCLUIR iniciado"); 
        System.out.println(" vdp_codigo = " + objModel.getVdp_codigo() );
        System.out.println(" vda_codigo = " + objModel.getVenda_Vendapagto().getVda_codigo());        
        System.out.println(" pfg_codigo = " + objModel.getFormapagto_Vendapagto().getFPG_CODIGO());

        objModel.setVdp_codigo(null);
        session.merge(objModel); 
    }

    @Override
    public void alterar(VendapagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] ALTERAR iniciado");
        
        // VendaPagto com dados NOVOS
        Integer new_cod_vdp = objModel.getVdp_codigo();
        
        // Cliente com dados ANTIGOS;
        VendapagtoModel old_vdp = get(new_cod_vdp);
        Integer old_cod_vdp   = old_vdp.getVdp_codigo();
        
        if (old_vdp == null) {
            throw new Exception("  Vendapagto não encontrada no banco para atualização!");
        }
        
        if (new_cod_vdp != old_cod_vdp) return;
        
        System.out.println(" |---------------------------------");
        System.out.println(" |  Vendapagto com dados NOVOS     ");
        System.out.println(" vdp_codigo = " + objModel.getVdp_codigo() );
        System.out.println(" vda_codigo = " + objModel.getVenda_Vendapagto().getVda_codigo());        
        System.out.println(" pfg_codigo = " + objModel.getFormapagto_Vendapagto().getFPG_CODIGO());
        System.out.println(" |----------------------------------");
        System.out.println(" | Vendapagto com dados ANTIGOS     ");
        System.out.println(" vdp_codigo = " + old_vdp.getVdp_codigo() );
        System.out.println(" vda_codigo = " + old_vdp.getVenda_Vendapagto().getVda_codigo());        
        System.out.println(" pfg_codigo = " + old_vdp.getFormapagto_Vendapagto().getFPG_CODIGO());
        System.out.println(" |----------------------------------\n");
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(objModel); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
    }
    
    
    @Override
    public ArrayList<VendapagtoModel> consultar(String filtro) {
        System.out.println("\n [VendapagtoDao] CONSULTAR iniciado \n");
        
        String tabVendapagto = VendapagtoModel.class.getName();
        String hql = " FROM " + tabVendapagto + " vpg " 
                +    " JOIN FETCH venpag.venda v "
                +    " JOIN FETCH venpag.formapagto fpg";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql =  " FROM " + tabVendapagto + " vpg " 
                +  " JOIN FETCH venpag.venda v "
                +  " JOIN FETCH venpag.formapagto fpg"
                +  " WHERE " + filtro;
        }
        
        System.out.println(" [VendapagtoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendapagtoModel.class);
            
            if(operacao.equals("consultaPorVdaCodigo") && hql.contains(":vda_codigo")) {
                query.setParameter("vda_codigo", operacao);
                System.out.println(" [VendaProdutoDao] parâmetro da consulta (consultaPorVdaCodigo) = tem que compara por VDA_CODIGO");
            }
                        
            List<VendapagtoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(VendapagtoModel objModel) throws Exception {
        System.out.println("\n [VendapagtoDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

           VendapagtoModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public VendapagtoModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendapagtoModel) session.getReference(VendapagtoModel.class, id);
    }
    
    
    // Pesquisa por VDA_CODIGO
    public List<VendapagtoModel> consultarPorVenda(Integer VDA_CODIGO) throws Exception {
        String tabVendapagto = VendapagtoModel.class.getName();
        
        String hql = " FROM " + tabVendapagto + " vpg " 
                +    " JOIN FETCH venpag.venda v "
                +    " JOIN FETCH venpag.formapagto fpg"
                +    " WHERE vda_codigo = :vda_codigo";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendapagtoModel.class);
            
            if (VDA_CODIGO != null && hql.contains(":vda_codigo")) {
                query.setParameter(":vda_codigo", hql);
            }

            List<VendapagtoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }
    
     public void inserirPgtos(int vdaCodigo, ArrayList<VendapagtoModel> pgtos) throws Exception {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            for (VendapagtoModel pagto : pgtos) {
                // garante que a venda esteja associada
                pagto.getVenda_Vendapagto().setVda_codigo(vdaCodigo);

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


