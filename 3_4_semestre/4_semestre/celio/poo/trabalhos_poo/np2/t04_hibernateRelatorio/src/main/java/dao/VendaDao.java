package dao;

import java.time.LocalDate;
import model.VendaModel;

import java.util.ArrayList;
import java.util.List;
import model.VendaProdutoModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class VendaDao implements GenericDao<VendaModel> {
    
    private LocalDate dataFiltroTemp;


    @Override
    public void incluir(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] INCLUIR iniciado \n");
        
        System.out.println(" usu_codigo = " + objModel.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" usu_codigo = " + objModel.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" venda_codigo = " + objModel.getVda_codigo());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setVda_codigo(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }

    @Override
    public void alterar(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] ALTERAR iniciado");
        
        // Venda com dados NOVOS
        Integer new_cod_venda = objModel.getVda_codigo();
        
        // Cliente com dados ANTIGOS;
        VendaModel old_venda = get(new_cod_venda);
        Integer old_cod_venda   = old_venda.getVda_codigo();
        
        if (old_venda == null) {
            throw new Exception("  Venda não encontrada no banco para atualização!");
        }
        
        if (new_cod_venda != old_cod_venda) return;
        
        System.out.println(" |---------------------------------");
        System.out.println(" |      Venda com dados NOOVOS     ");
        System.out.println(" |usu_codigo   = " + objModel.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" |usu_codigo   = " + objModel.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" |venda_codigo = " + objModel.getVda_codigo());
        System.out.println(" |----------------------------------");
        System.out.println(" |      VEnda com dados ANTIGOS     ");
        System.out.println(" |usu_codigo   = " + old_venda.getUsu_venda().getUSU_CODIGO() );
        System.out.println(" |usu_codigo   = " + old_venda.getCli_venda().getCLI_CODIGO() );        
        System.out.println(" |venda_codigo = " + old_venda.getVda_codigo());
        System.out.println(" |----------------------------------\n");
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(objModel); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
    }
    
    
    @Override
    public ArrayList<VendaModel> consultar(String filtro) {
        System.out.println("\n [VendaDao] CONSULTAR iniciado \n");
        
        String tabVenda = VendaModel.class.getName();
        String hql = " FROM " + tabVenda + " v " 
                +    " JOIN FETCH v.usuario "
                +    " JOIN FETCH v.cliente ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql = " FROM " + tabVenda + " v " 
                +    " JOIN FETCH v.usuario "
                +    " JOIN FETCH v.cliente "
                + " WHERE " + filtro;
        }
        
        System.out.println(" [VendaDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaModel.class);
            
            if (dataFiltroTemp != null && hql.contains(":dataFiltro")) {
                query.setParameter("dataFiltro", dataFiltroTemp);
                System.out.println(" [VendaDao] parâmetro dataFiltro = " + dataFiltroTemp);
            }
            // Removida transação desnecessária para operação de leitura
            List<VendaModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(VendaModel objModel) throws Exception {
        System.out.println("\n [VendaDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Para garantir que o objeto está no contexto de persistência
            VendaModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public VendaModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaModel) session.getReference(VendaModel.class, id);
    }
    
    
    public List<VendaProdutoModel> consultarVendaProduto(String filtro) throws Exception {

        String tabVp = VendaProdutoModel.class.getName();
        String hql = " SELECT "
                + "vda_codigo, pro_codigo, vep_qtde, vep_preco, vep_total "
                + "FROM " + tabVp;
        
        if (filtro == null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaProdutoModel.class);
            
            List<VendaProdutoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }    
    }
    
    /**
     * SETTERS
     */
    
    public void setDataFiltroTemp(LocalDate dataFiltroTemp) {
        this.dataFiltroTemp = dataFiltroTemp;
    }
}

