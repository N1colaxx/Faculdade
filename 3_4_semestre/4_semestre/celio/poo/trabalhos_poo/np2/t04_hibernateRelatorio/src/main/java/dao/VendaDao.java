package dao;

import model.VendaModel;
import model.ClienteModel;
import model.UsuarioModel;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VendaDao implements GenericDao<VendaModel> {

    
    @Override
    public void incluir(VendaModel objModel) throws Exception { 
    }
    
 
    public VendaModel incluir(VendaModel objModel, Session session) throws Exception {
        System.out.println("\n [VendaDao] INCLUIR iniciado");
        System.out.println(" venda_codigo = " + objModel.getVda_codigo());
        System.out.println(" usu_codigo = " + objModel.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" cli_codigo = " + objModel.getCli_venda().getCLI_CODIGO() );
        
        try {
            session.persist(objModel);
        } catch (Exception e) {
            if(objModel.getVda_codigo() == null) {
                System.out.println(" [VendaDao] ERRO! ao gravar a venda");
                JOptionPane.showMessageDialog(null, "ERRO! ao gravar Venda, ID = " + objModel.getVda_codigo());
                return  null;
            }
        }

        System.out.println(" [VendaDao] Sucesso! Venda salva com (objModel) ID =  " + objModel.getVda_codigo());
        return objModel;
    }
    
       @Override
    public void alterar(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] ALTERAR iniciado...");
        System.out.println(" [VendaDao] alterando Venda ID = " + objModel.getVda_codigo());
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
            session.merge(objModel);
            tx.commit();
        } catch(Exception ex) {
            if(tx != null) {
                System.out.println(" [VendaDao] ERRO! ao Alterar a Venda");
                JOptionPane.showMessageDialog(null, "[VendaDao] ERRO! ao Alterar a Venda");
                tx.rollback();
            }
        } finally {
            session.close();
            System.out.println(" [VendaDao] SESSION fechada.");
        }
        
        System.out.println(" [VendaDao] Sucesso! ao Alterar a Venda = " + objModel.getVda_codigo());
    }
    
    // Usado para incluir uma venda
    public void atualizar(VendaModel objModel, Session session) {
        try {
            session.merge(objModel);
        } catch(Exception ex) {
            if(objModel.getVda_codigo() == null) {
                System.out.println(" [VendaDao] ERRO! ao Atualizar a Venda");
            }
        }
        
        System.out.println(" [VendaDao] Sucesso! ao Atualizar a Venda = " + objModel.getVda_codigo());
    }
    
    @Override
    public ArrayList<VendaModel> consultar(String filtro) {
        System.out.println("\n [VendaDao] CONSULTAR iniciado...");
        
        String tabVenda = VendaModel.class.getName();
        String hql = " FROM " + tabVenda + " v " 
                +    " JOIN FETCH v.usuario u "
                +    " JOIN FETCH v.cliente c ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        
        hql +=  "ORDER BY v.vda_codigo";
        
        System.out.println(" [VendaDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaModel.class);
            
            List<VendaModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] EXCLUIR iniciado \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

           VendaModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public VendaModel get(Integer id) {
        System.out.println("\n [VendaDao] GET() foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaModel) session.getReference(VendaModel.class, id);
    }
}

