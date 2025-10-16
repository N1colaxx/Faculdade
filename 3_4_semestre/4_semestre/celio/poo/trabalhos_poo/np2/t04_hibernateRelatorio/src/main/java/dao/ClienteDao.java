package dao;


import java.time.LocalDate;
import model.ClienteModel;



import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ClienteDao implements GenericDao<ClienteModel> {
    
    private LocalDate dataFiltroTemp;

    public void setDataFiltroTemp(LocalDate dataFiltroTemp) {
        this.dataFiltroTemp = dataFiltroTemp;
    }

    @Override
    public void incluir(ClienteModel objModel) throws Exception {
        System.out.println("\n [ClienteDao] INCLUIR iniciado \n");
        System.out.println(" pes_codigo = " + objModel.getPessoa_Cliente().getPES_CODIGO());
        System.out.println(" cli_codigo = " + objModel.getCLI_CODIGO());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setCLI_CODIGO(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }

    @Override
    public void alterar(ClienteModel c) throws Exception {
        System.out.println("\n [ClienteDao] ALTERAR iniciado");
        // Cliente com dados NOVOS
        Integer cod_new_c = c.getCLI_CODIGO();
        System.out.println(" pes_codigo (cod_new_c) = " + c.getPessoa_Cliente().getPES_CODIGO());
        System.out.println(" cli_codigo (cod_new_c) = " + c.getCLI_CODIGO());
        
        // Cliente com dados ANTIGOS;
        ClienteModel old_c = get(cod_new_c);
        Integer cod_old_c = old_c.getCLI_CODIGO();
        
        if (old_c == null) {
            throw new Exception("Cliente não encontrado no banco para atualização!");
        }
        
        if (cod_new_c != cod_old_c) return;
        
        System.out.println(" pes_codigo (old_c) = " + old_c.getPessoa_Cliente().getPES_CODIGO());
        System.out.println(" cli_codigo (old_c) = " + cod_old_c);
        
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(c); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
    }
    
    
    @Override
    public ArrayList<ClienteModel> consultar(String filtro) {
        System.out.println("\n [ClienteDao] CONSULTAR iniciado \n");
        
        String tabCliente = ClienteModel.class.getName();
        String hql = "FROM " + tabCliente + " c JOIN FETCH c.pessoa"; // Isso carrega a pessoa junto!
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql =   " FROM " + tabCliente + " c JOIN FETCH c.pessoa p "
                  + " WHERE " + filtro;
        }
        
        System.out.println(" [ClienteDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, ClienteModel.class);
            
            if (dataFiltroTemp != null && hql.contains(":dataFiltro")) {
                query.setParameter("dataFiltro", dataFiltroTemp);
                System.out.println(" [ClienteDao] parâmetro dataFiltro = " + dataFiltroTemp);
            }
            // Removida transação desnecessária para operação de leitura
            List<ClienteModel> resultList = query.getResultList();
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
    
    public ClienteModel get(Integer id, Session session) {
        return (ClienteModel) session.getReference(ClienteModel.class, id);
    }
    
    
    
}
