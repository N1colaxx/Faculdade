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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Gravando Pessoa Primeiro 
            PessoaModel pessoa = objModel.getPessoa_Cliente();
            
            if( pessoa != null) {
                if(pessoa.getPES_CODIGO() == 0) {
                    session.persist(pessoa);
                }else {
                    pessoa = session.merge(pessoa); // Atualiza pessoa existente
                    objModel.setPessoa_Cliente(pessoa);
                }
                
            } else {
                System.out.println("\n ERRO! ao paser INSERT de PESSOA de Cliente, pes_codigo = null ");
            }
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setCLI_CODIGO(null);

            session.persist(objModel);
            t.commit();
        }
    }

    @Override
    public void alterar(ClienteModel objModel) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Atualizar a PESSOA primeiro
            PessoaModel pessoa = objModel.getPessoa_Cliente();
            if (pessoa != null) {
                pessoa = session.merge(pessoa);
                objModel.setPessoa_Cliente(pessoa);
            }

            session.merge(objModel); // Mudar persist para merge para atualização
            t.commit();
        }
    }

    @Override
    public ArrayList<ClienteModel> consultar(String filtro) {
        String tabCliente = ClienteModel.class.getName();
        String hql = "FROM " + tabCliente + " c " +
                     "JOIN FETCH c.pessoa"; // Isso carrega a pessoa junto!
        
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String tabCliente = ClienteModel.class.getName();
            String hql = "FROM " + tabCliente + " c " +
                        "JOIN FETCH c.pessoa " +
                        "WHERE c.cli_codigo = :id";
            
            return session.createQuery(hql, ClienteModel.class)
                         .setParameter("id", id)
                         .uniqueResult();
        }
    }
    
    
}
