package dao;

import model.VendaModel;
import model.ClienteModel;
import model.UsuarioModel;


import java.util.ArrayList;
import java.util.List;
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
        
        // Garante que o ID seja nulo para nova inserção
        session.persist(objModel);
        session.flush();   // força o insert AGORA
        session.refresh(objModel); // agora recarregar do banco

        System.out.println("Venda salva com ID: " + objModel.getVda_codigo());
        return objModel;
    }

    @Override
    public void alterar(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] ALTERAR iniciado");
        
        // Venda com dados NOVOS
        Integer new_cod_venda = objModel.getVda_codigo();
        
        // Cliente com dados ANTIGOS;
        VendaModel old_venda = get(new_cod_venda);
        Integer old_cod_venda   = old_venda.getVda_codigo();
        
        if (old_venda == null) {
            throw new Exception("  Venda não encontrada no banco para atualização!");
        }
        
        if (new_cod_venda != old_cod_venda) return;
        
        System.out.println(" |---------------------------------");
        System.out.println(" |      Venda com dados NOOVOS     ");
        System.out.println(" |usu_codigo   = " + objModel.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" |usu_codigo   = " + objModel.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" |venda_codigo = " + objModel.getVda_codigo());
        System.out.println(" |----------------------------------");
        System.out.println(" |      VEnda com dados ANTIGOS     ");
        System.out.println(" |usu_codigo   = " + old_venda.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" |usu_codigo   = " + old_venda.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" |venda_codigo = " + old_venda.getVda_codigo());
        System.out.println(" |----------------------------------\n");
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(objModel); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
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

