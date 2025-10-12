package dao;

import java.time.LocalDate;
import model.VendaProdutoModel;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VemdaProdutoDao implements GenericDao<VendaProdutoModel> {
    
    private LocalDate dataFiltroTemp;


    @Override
    public void incluir(VendaProdutoModel objModel) throws Exception {
        System.out.println("\n [VendaProdutoDao] INCLUIR iniciado \n");
        
        System.out.println(" vep_codigo = " + objModel.getVep_codigo());
        System.out.println(" vda_codigo = " + objModel.getVenda_VendaProduto().getVda_codigo());        
        System.out.println(" pro_codigo = " + objModel.getProduto_VendaProduto().getPRO_CODIGO());
         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            
            // Garante que o ID seja nulo para nova inserção
            objModel.setVep_codigo(null);
            session.persist(objModel);
            
            t.commit();
        }   
    }

    @Override
    public void alterar(VendaProdutoModel objModel) throws Exception {
        System.out.println("\n [VendaProdutoDao] ALTERAR iniciado");
        
        // VendaProduto com dados NOVOS
        Integer new_cod_vendaPro = objModel.getVep_codigo();
        
        // VendaProduto com dados ANTIGOS;
        VendaProdutoModel old_vendaPro = get(new_cod_vendaPro);
        Integer old_cod_vendaPro   = old_vendaPro.getVep_codigo();
        
        if (old_vendaPro == null) {
            throw new Exception("  VendaProduto não encontrada no banco para atualização!");
        }
        
        if (new_cod_vendaPro != old_cod_vendaPro) return;
        
        System.out.println(" |---------------------------------");
        System.out.println(" |      Venda com dados NOOVOS     ");
        System.out.println(" |vep_codigo   = " + objModel.getVep_codigo());
        System.out.println(" |vda_codigo   = " + objModel.getVenda_VendaProduto().getVda_codigo());        
        System.out.println(" |pro_codigo = " + objModel.getProduto_VendaProduto().getPRO_CODIGO());
        System.out.println(" |----------------------------------");
        System.out.println(" |      VEnda com dados ANTIGOS     ");
        System.out.println(" |vep_codigo   = " + old_vendaPro.getVep_codigo());
        System.out.println(" |vda_codigo   = " + old_vendaPro.getVenda_VendaProduto().getVda_codigo());        
        System.out.println(" |pro_codigo   = " + old_vendaPro.getProduto_VendaProduto().getPRO_CODIGO());
        System.out.println(" |----------------------------------\n");
        
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction t = session.beginTransaction();

           session.merge(objModel); // MERGE vai atualizar tanto pessoa quanto cliente
           t.commit();
       }
    }
    
    
    @Override
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        System.out.println("\n [VendaProdutoDao] CONSULTAR iniciado \n");
        
        String tabVendaPro = VendaProdutoModel.class.getName();
        String hql = " FROM " + tabVendaPro + " vp " 
                +    " JOIN FETCH vda.compra "
                +    " JOIN FETCH pro.produto ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql = " FROM " + tabVendaPro + " vp " 
                + " JOIN FETCH vda.compra "
                + " JOIN FETCH pro.produto "
                + " WHERE " + filtro;
        }
        
        System.out.println(" [VendaProdutoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaProdutoModel.class);
            
            if (dataFiltroTemp != null && hql.contains(":dataFiltro")) {
                query.setParameter("dataFiltro", dataFiltroTemp);
                System.out.println(" [VendaProdutoDao] parâmetro dataFiltro = " + dataFiltroTemp);
            }
            // Removida transação desnecessária para operação de leitura
            List<VendaProdutoModel> resultList = query.getResultList();
            return new ArrayList<>(resultList);
        }
    }

    @Override
    public void excluir(VendaProdutoModel objModel) throws Exception {
        System.out.println("\n [VendaProdutoDao] EXCLUIR iniciado \n");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            // Para garantir que o objeto está no contexto de persistência
            VendaProdutoModel managedEntity = session.merge(objModel);
            session.remove(managedEntity);

            t.commit();
        }
    }

    @Override
    public VendaProdutoModel get(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }
    
    /**
     * SETTERS
     */
    
    public void setDataFiltroTemp(LocalDate dataFiltroTemp) {
        this.dataFiltroTemp = dataFiltroTemp;
    }
}


