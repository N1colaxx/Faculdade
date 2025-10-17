package dao;

import model.ProdutoModel;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ProdutoDao implements GenericDao<ProdutoModel> {

    @Override
    public void incluir(ProdutoModel objModel) throws Exception {
        System.out.println(" [ProdutoDao] incluir() foi iniciado... \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Garante que o ID seja nulo para nova inserção
            objModel.setPRO_CODIGO(null);

            session.persist(objModel);
            t.commit();
        }
    }

    @Override
    public void alterar(ProdutoModel objModel) throws Exception {
        System.out.println(" [ProdutoDao] alterar() foi iniciado... \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(objModel); // Mudar persist para merge para atualização
            t.commit();
        }
    }

    @Override
    public ArrayList<ProdutoModel> consultar(String filtro) {
        System.out.println(" [ProdutoDao] consultar() foi iniciado... \n");
        
        String hql = "from " + ProdutoModel.class.getName();
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " " + filtro;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Removida transação desnecessária para operação de leitura
            List<ProdutoModel> resultList = session.createQuery(hql, ProdutoModel.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(ProdutoModel objModel) throws Exception {
        System.out.println(" [ProdutoDao] excluir() foi iniciado... \n");

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction t = session.beginTransaction();
        
        // Para garantir que o objeto está no contexto de persistência
        ProdutoModel managedEntity = session.merge(objModel);
        session.remove(managedEntity);
        
        t.commit();
    }
    }

    @Override
    public ProdutoModel get(Integer id) {
        System.out.println(" [ProdutoDao] get() foi iniciado...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (ProdutoModel) session.getReference(ProdutoModel.class, id);
    }
}
