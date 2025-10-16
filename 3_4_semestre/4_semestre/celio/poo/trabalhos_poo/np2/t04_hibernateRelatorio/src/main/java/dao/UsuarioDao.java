package dao;

import model.UsuarioModel;

import java.util.ArrayList;
import java.util.List;
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

    
//         String sql = "SELECT USU_CODIGO, USU_NOME, USU_LOGIN, USU_ATIVO " +
//                     "FROM USUARIO WHERE USU_LOGIN = ? AND USU_SENHA = ?";   
    
    @Override
    public ArrayList<UsuarioModel> consultar(String filtro) {
        String hql = "from " + UsuarioModel.class.getName();
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
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
    
    public UsuarioModel get(int id, Session session) {
        return (UsuarioModel) session.getReference(UsuarioModel.class, id);
    }
   
    
    public UsuarioModel buscarPorEmailSenha(String email, String senha) {
            System.out.println(" [UsuarioDao] buscarPorEmailSenha iniciado... \n");
            
            String tabUsuario = UsuarioModel.class.getName();            
            String hql = "FROM " + tabUsuario + " u WHERE u.USU_LOGIN = :email AND u.USU_SENHA = :senha";
            
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, UsuarioModel.class);
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            
            System.out.println(" [UsuarioDao] query HQL executada:");
            System.out.println(   hql + " \n");

            List<UsuarioModel> result = query.getResultList();
            if (result.isEmpty()) {
                return null;
            }

            return result.get(0);

        } catch (Exception e) {
            System.out.println("[UsuarioDao] Erro ao buscar usuário: " + e.getMessage());
            throw e; // repassa o erro pro controller
        }
    }
        
}
