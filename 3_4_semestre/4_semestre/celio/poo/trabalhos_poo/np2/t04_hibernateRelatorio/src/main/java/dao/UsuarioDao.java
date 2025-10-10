package dao;

import java.util.ArrayList;
import java.util.List;
import model.UsuarioModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UsuarioDao implements GenericDao<UsuarioModel> {

    @Override
    public void incluir(UsuarioModel objModel) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Garante que o ID seja nulo para nova inserção
            objModel.setUSU_CODIGO(null);

            session.persist(objModel);
            t.commit();
        }
    }

    @Override
    public void alterar(UsuarioModel objModel) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.merge(objModel); // Mudar persist para merge para atualização
            t.commit();
        }
    }

    @Override
    public ArrayList<UsuarioModel> consultar(String filtro) {
        String hql = "from " + UsuarioModel.class.getName();
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " " + filtro;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Removida transação desnecessária para operação de leitura
            List<UsuarioModel> resultList = session.createQuery(hql, UsuarioModel.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(UsuarioModel objModel) throws Exception {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction t = session.beginTransaction();
        
        // Para garantir que o objeto está no contexto de persistência
        UsuarioModel managedEntity = session.merge(objModel);
        session.remove(managedEntity);
        
        t.commit();
    }
    }

    @Override
    public UsuarioModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (UsuarioModel) session.getReference(UsuarioModel.class, id);
    }
}
