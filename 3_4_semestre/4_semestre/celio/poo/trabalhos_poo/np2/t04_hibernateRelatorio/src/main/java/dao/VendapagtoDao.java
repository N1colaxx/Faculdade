package dao;


import model.VendaPagtoModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class VendaPagtoDao {
    
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
            
            List<VendaPagtoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    
    public VendaPagtoModel get(Integer id) {
        System.out.println(" [VendaPagtoDao] get(id) iniciaou...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaPagtoModel) session.getReference(VendaPagtoModel.class, id);
    }
    
}


