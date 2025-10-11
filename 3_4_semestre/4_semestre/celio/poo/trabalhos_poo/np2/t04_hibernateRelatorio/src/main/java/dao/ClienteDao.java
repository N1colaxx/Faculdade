package dao;


import model.ClienteModel;
import model.PessoaModel;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ClienteDao implements GenericDao<ClienteModel> {

    @Override
    public void incluir(ClienteModel objModel) throws Exception {
        System.out.println("\n [ClienteDao] INCLUIR iniciado \n");
        
        Integer cod_pessoa = objModel.getPessoa_Cliente().getPES_CODIGO();
        System.out.println(" [ClienteDao] void INCLUIR pes_codigo = " + cod_pessoa + " \n");
            
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setCLI_CODIGO(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }

    @Override
    public void alterar(ClienteModel objModel) throws Exception {
        System.out.println("\n [ClienteDao] ALTERAR iniciado");
        
        Integer cod_pessoa = objModel.getPessoa_Cliente().getPES_CODIGO();
        System.out.println(" [ClienteDao] void INCLUIR pes_codigo = " + cod_pessoa + " \n");
            
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            session.merge(objModel);
            t.commit();
        }
    }

    @Override
    public ArrayList<ClienteModel> consultar(String filtro) {
        System.out.println("\n [ClienteDao] CONSULTAR iniciado \n");
        String tabCliente = ClienteModel.class.getName();
        String hql = "FROM " + tabCliente + " c JOIN FETCH c.pessoa"; // Isso carrega a pessoa junto!
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " " + filtro;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Removida transação desnecessária para operação de leitura
            List<ClienteModel> resultList = session.createQuery(hql, ClienteModel.class).getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(ClienteModel objModel) throws Exception {
        System.out.println("\n [ClienteDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Para garantir que o objeto está no contexto de persistência
            ClienteModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public ClienteModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (ClienteModel) session.getReference(ClienteModel.class, id);
    }
    
    
    
}
