package dao;

import model.FornecedorModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.time.LocalDate;


public class FornecedorDao implements GenericDao<FornecedorModel> {
    
    private LocalDate dataFiltroTemp;

    public void setDataFiltroTemp(LocalDate dataFiltroTemp) {
        this.dataFiltroTemp = dataFiltroTemp;
    }

    @Override
    public void incluir(FornecedorModel objModel) throws Exception {
        System.out.println("\n [FornecedorDao] INCLUIR iniciado \n");
        System.out.println(" pes_codigo = " + objModel.getPESSOA_FORNECEDOR().getPES_CODIGO());
        System.out.println(" for_codigo = " + objModel.getFOR_CODIGO());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setFOR_CODIGO(null);
            session.persist(objModel);
            session.flush();
            t.commit();
        }   
    }

    @Override
    public void alterar(FornecedorModel c) throws Exception {
        System.out.println("\n [FornecedorDao] ALTERAR iniciado");
        // Cliente com dados NOVOS
        Integer cod_new_f = c.getFOR_CODIGO();
        System.out.println(" pes_codigo (cod_new_f) = " + c.getPESSOA_FORNECEDOR().getPES_CODIGO());
        System.out.println(" for_codigo (cod_new_f) = " + c.getFOR_CODIGO());
        
        // Cliente com dados ANTIGOS;
        FornecedorModel old_f = get(cod_new_f);
        Integer cod_old_f = old_f.getFOR_CODIGO();
        
        if (old_f == null) {
            throw new Exception("Fornecedor não encontrado no banco para atualização!");
        }
        
        if (cod_new_f != cod_old_f) return;
        
        System.out.println(" pes_codigo (old_c) = " + old_f.getPESSOA_FORNECEDOR().getPES_CODIGO());
        System.out.println(" for_codigo (old_c) = " + cod_old_f);
        
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(c); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
    }
    
    
    @Override
    public ArrayList<FornecedorModel> consultar(String filtro) {
        System.out.println("\n [FornecedorDao] CONSULTAR iniciado \n");
        
        String tabFornecedor = FornecedorModel.class.getName();
        String hql = "FROM " + tabFornecedor + " f JOIN FETCH f.pessoa"; // Isso carrega a pessoa junto!
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql =   " FROM " + tabFornecedor + " f JOIN FETCH f.pessoa p "
                  + " WHERE " + filtro;
        }
        
        System.out.println(" [FornecedorDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, FornecedorModel.class);
            
            if (dataFiltroTemp != null && hql.contains(":dataFiltro")) {
                query.setParameter("dataFiltro", dataFiltroTemp);
                System.out.println(" [FornecedorDao] parâmetro dataFiltro = " + dataFiltroTemp);
            }
            
            List<FornecedorModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(FornecedorModel objModel) throws Exception {
        System.out.println("\n [FornecedorDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

           
            FornecedorModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public FornecedorModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (FornecedorModel) session.getReference(FornecedorModel.class, id);
    }

    public FornecedorModel get(Integer id, Session session ) {
        return (FornecedorModel) session.getReference(FornecedorModel.class, id);
    }
        
    
    
}
