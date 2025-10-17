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
        System.out.println("\n [VendaDao] INCLUIR iniciado \n");
        
        System.out.println(" usu_codigo = " + objModel.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" cli_codigo = " + objModel.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" venda_codigo = " + objModel.getVda_codigo());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setVda_codigo(null);
            session.persist(objModel);
            
            t.commit();
        }   
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
    }
    
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
        System.out.println("\n [VendaDao] CONSULTAR iniciado \n");
        
        String tabVenda = VendaModel.class.getName();
        String hql = " FROM " + tabVenda + " v " 
                +    " JOIN FETCH v.usuario "
                +    " JOIN FETCH v.cliente ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql = " FROM " + tabVenda + " v " 
                +    " JOIN FETCH v.usuario "
                +    " JOIN FETCH v.cliente "
                + " WHERE " + filtro;
        }
        
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
        System.out.println(" [VendaDao] get() foi iniciado... \n");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaModel) session.getReference(VendaModel.class, id);
    }
}

