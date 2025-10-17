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
    public void alterar(VendaModel v) throws Exception {
        System.out.println("\n [ClienteDao] ALTERAR iniciado");
        // Venda com dados NOVOS
        Integer cod_new_v = v.getVda_codigo();
        System.out.println(" vda_codigo (new_venda) = " + v.getVda_codigo());
        
        // Venda com dados ANTIGOS;
        VendaModel old_v = get(cod_new_v);
        Integer cod_old_v = old_v.getVda_codigo();
        
        if (old_v == null) {
            throw new Exception("Venda não encontrado no banco para atualização!");
        }
        
        if (cod_new_v != cod_old_v) return;
        System.out.println(" vda_codigo (old_venda) = " + v.getVda_codigo());
        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
           session.merge(v); // MERGE vai atualizar tanto pessoa quanto cliente
           tx.commit();
       }catch (Exception ex) {
           if(tx != null) {
                System.out.println(" [VendaDao] ERRO! ao Alterar Venda ID = " + v.getVda_codigo());
                tx.rollback();
           }
       } finally {
               session.close();              
        }
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
        System.out.println("\n [VendaDao] GET(id) foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaModel) session.getReference(VendaModel.class, id);
    }
    
    public VendaModel get(Integer id, Session session) {
        System.out.println("\n [VendaDao] GET(id com Session) foi iniciado...");
        return (VendaModel) session.getReference(VendaModel.class, id);
    }
}

