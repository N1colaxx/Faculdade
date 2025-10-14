package dao;

import model.FormapagtoModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class FormapagtoDao implements GenericDao<FormapagtoModel> {

    @Override
    public void incluir(FormapagtoModel objModel) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Garante que o ID seja nulo para nova inserção
            objModel.setFPG_CODIGO(null);

            session.persist(objModel);
            t.commit();
        }
    }

    @Override
    public void alterar(FormapagtoModel objModel) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(objModel); // Mudar persist para merge para atualização
            t.commit();
        }
    }

    @Override
    public ArrayList<FormapagtoModel> consultar(String filtro) {
        String hql = "FROM " + FormapagtoModel.class.getName();
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " " + filtro;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Removida transação desnecessária para operação de leitura
            List<FormapagtoModel> resultList = session.createQuery(hql, FormapagtoModel.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(FormapagtoModel objModel) throws Exception {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction t = session.beginTransaction();
        
        // Para garantir que o objeto está no contexto de persistência
        FormapagtoModel managedEntity = session.merge(objModel);
        session.remove(managedEntity);
        
        t.commit();
    }
    }

    @Override
    public FormapagtoModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (FormapagtoModel) session.getReference(FormapagtoModel.class, id);
    }
    

    /**
     * Retorna uma lista de nomes de formas de pagamento ativas (fpg_ativo = 'S').
     */
    public ArrayList<String> listarNomesAtivos() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT f.fpg_nome FROM FormapagtoModel f " +
                         "WHERE f.fpg_ativo = 1 ORDER BY f.fpg_nome";

            List<String> resultList = session.createQuery(hql, String.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    /**
     * Retorna o código (PK) da forma de pagamento dado o nome.
     * Retorna -1 se não encontrar.
     */
    public int obterCodigoPorNome(String nome) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT f.fpg_codigo FROM FormapagtoModel f WHERE f.fpg_nome = :nome";
            Integer codigo = session.createQuery(hql, Integer.class)
                                    .setParameter("nome", nome)
                                    .uniqueResult();
            return (codigo != null) ? codigo : -1;
        }
    }
    
   
}
    

