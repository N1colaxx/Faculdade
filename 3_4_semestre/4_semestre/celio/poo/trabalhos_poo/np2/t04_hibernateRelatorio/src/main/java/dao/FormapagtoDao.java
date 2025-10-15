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
        System.out.println(" [FormapagtoDao] incluir() foi iniciado... \n");

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
        System.out.println(" [FormapagtoDao] alterar() foi iniciado... \n");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(objModel); // Mudar persist para merge para atualização
            t.commit();
        }
    }

    @Override
    public ArrayList<FormapagtoModel> consultar(String filtro) {
        System.out.println(" [FormapagtoDao] consultar() foi iniciado... \n");

        String tabFormapagto = FormapagtoModel.class.getName();
        String hql = "FROM " + tabFormapagto + " fpg";
         
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WEHER" + filtro;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Removida transação desnecessária para operação de leitura
            List<FormapagtoModel> resultList = session.createQuery(hql, FormapagtoModel.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(FormapagtoModel objModel) throws Exception {
        System.out.println(" [FormapagtoDao] excluir() foi iniciado... \n");

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
        System.out.println(" [FormapagtoDao] get() foi iniciado... \n");

        Session session = HibernateUtil.getSessionFactory().openSession();
        return (FormapagtoModel) session.getReference(FormapagtoModel.class, id);
    }
    

    
//    Retorna uma lista de nomes de formas de pagamento ativas (fpg_ativo = 1).
    public ArrayList<String> listarNomesAtivos() throws Exception {
        System.out.println(" [FormapagtoDao] listarNomesAtivos() foi iniciado... \n");
        
        String tabFormapagto = FormapagtoModel.class.getName();
        String hql = "SELECT f.fpg_nome FROM " + tabFormapagto + " f WHERE f.fpg_ativo = '1'";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, String.class);
            
            System.out.println("\n QUERY SEM EXECUTADA: \n" + hql );

            List<String> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    
    


    public FormapagtoModel obterCodigoPorNome(String nome) throws Exception {
        System.out.println(" [FormapagtoDao] obterCodigoPorNome() foi iniciado...");
        System.out.println(" [FormapagtoDao] nome que esta procurando = " + nome);
        
        String tabFormapagto = FormapagtoModel.class.getName();
        String hql = "FROM  " + tabFormapagto + " f WHERE f.fpg_nome = :nome";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, FormapagtoModel.class);
            
            if(hql.contains(":nome")) {
                query.setParameter("nome", nome);
            }
            
            List<FormapagtoModel> list = query.getResultList();
            return list.getFirst();
        }
    }
    
   
}
    

