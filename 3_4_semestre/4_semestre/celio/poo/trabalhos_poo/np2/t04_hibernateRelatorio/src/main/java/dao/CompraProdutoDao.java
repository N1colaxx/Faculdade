package dao;



import java.time.LocalDate;
import model.CompraProdutoModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CompraProdutoDao  {
    
    
    public ArrayList<CompraProdutoModel> consultar(String filtro) {
        System.out.println("\n [CompraProdutoDao] CONSULTAR iniciado");
        
        String tabVendaPro = CompraProdutoModel.class.getName();
        String hql = " FROM " + tabVendaPro + " vep " 
                +    " JOIN FETCH vep.venda_vendaPro v "
                +    " JOIN FETCH vep.produto_vendaPro p ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        hql += " ORDER BY v.vda_codigo";
        
        System.out.println(" [CompraProdutoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, CompraProdutoModel.class);
            
            // Removida transação desnecessária para operação de leitura
            List<CompraProdutoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

  
    public CompraProdutoModel get(Integer id) {
        System.out.println(" [CompraProdutoDao] get(id) foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (CompraProdutoModel) session.getReference(CompraProdutoModel.class, id);
    }

    public CompraProdutoModel get(int id, Session session) {
        System.out.println(" [CompraProdutoDao] get(id com session) foi iniciado...");
        return (CompraProdutoModel) session.getReference(CompraProdutoModel.class, id);
    }

}



