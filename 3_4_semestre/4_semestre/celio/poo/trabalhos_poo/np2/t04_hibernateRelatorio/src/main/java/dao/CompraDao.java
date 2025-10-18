package dao;

import model.CompraModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CompraDao {
    
    
    public CompraModel gravar(CompraModel c, Session session) throws Exception{
        System.out.println(" [CompraDao] void gravar() iniciado...");
        
        try {
            session.persist(c);
        } catch (Exception ex) {
            if(c.getCpr_codigo() == null){
                System.out.println(" [CompraDao] ERRO! ao gravar compra! ID = " + c.getCpr_codigo());
                JOptionPane.showConfirmDialog(null, "ERRO! ao gravar compra!");
                return null;
            }
        }
        
        System.out.println(" [CompraDao] Sucesso! ao gravar compra! ID = " + c.getCpr_codigo());
        return c;
    }
    
    public void alterar(CompraModel c, Session session) throws Exception {
        try {
            session.merge(c);
        } catch(Exception ex) {
            if(c.getCpr_codigo() == null) {
                System.out.println(" [CompraDao] ERRO! ao Atualizar a Compra");
            }
        }
        
        System.out.println(" [CompraDao] Sucesso! ao Atualizar a Venda = " + c.getCpr_codigo());
    }
    
    public ArrayList<CompraModel> consultar(String filtro) {
        System.out.println("\n [CompraDao] CONSULTAR iniciado...");
        
        String tabCompra = CompraModel.class.getName();
        String hql = " FROM " + tabCompra+ " c " 
                +    " JOIN FETCH v.usuario_compra u "
                +    " JOIN FETCH v.fornecedor_compra f ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        
        hql +=  "ORDER BY c.cpr_codigo";
        
        System.out.println(" [CompraDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, CompraModel.class);
            
            List<CompraModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    public void excluir(CompraModel c) throws Exception {
        System.out.println("\n [CompraDao] EXCLUIR iniciado \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

           CompraModel managedEntity = session.merge(c);
            session.remove(managedEntity);

            t.commit();
        }
    }

    public CompraModel get(Integer id) {
        System.out.println("\n [CompraDao] GET(id) foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (CompraModel) session.getReference(CompraModel.class, id);
    }
    
    public CompraModel get(Integer id, Session session) {
        System.out.println("\n [CompraDao] GET(id com Session) foi iniciado...");
        return (CompraModel) session.getReference(CompraModel.class, id);
    }



}
