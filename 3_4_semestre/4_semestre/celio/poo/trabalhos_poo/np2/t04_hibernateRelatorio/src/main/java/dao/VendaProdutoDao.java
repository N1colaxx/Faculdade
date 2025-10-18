package dao;

import java.time.LocalDate;
import model.VendaProdutoModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

public class VendaProdutoDao  {
    
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        System.out.println("\n [VendaProdutoDao] CONSULTAR iniciado");
        
        String tabVendaPro = VendaProdutoModel.class.getName();
        String hql = " FROM " + tabVendaPro + " vep " 
                +    " JOIN FETCH vep.venda_vendaPro v "
                +    " JOIN FETCH vep.produto_vendaPro p ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        hql += " ORDER BY v.vda_codigo";
        
        System.out.println(" [VendaProdutoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaProdutoModel.class);
            
            // Removida transação desnecessária para operação de leitura
            List<VendaProdutoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }
    
    public VendaProdutoModel get(Integer id) {
        System.out.println(" [VendaProdutoDao] get(id) foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }

    public VendaProdutoModel get(int id, Session session) {
        System.out.println(" [VendaProdutoDao] get(id com session) foi iniciado...");
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }

}


