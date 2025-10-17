package dao;

import java.time.LocalDate;
import model.VendaProdutoModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VendaProdutoDao implements GenericDao<VendaProdutoModel> {
    
    private String operacao;
    
    

    public VendaProdutoDao() {
        operacao = null;
    }
    
    @Override
    public void incluir(VendaProdutoModel objModel) throws Exception {
        System.out.println("\n [VendaProdutoDao] INCLUIR iniciado");
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
    
    public void incluir(VendaProdutoModel objModel, Session session) throws Exception {
        System.out.println("\n [VendaProdutoDao] INCLUIR iniciado");
        System.out.println(" vep_codigo = " + objModel.getVep_codigo() );        
        System.out.println(" vda_codigo = " + objModel.getVenda_VendaProduto().getVda_codigo());
        System.out.println(" pro_codigo = " + objModel.getProduto_VendaProduto().getPRO_CODIGO());
        
        Transaction tx = session. beginTransaction();
        try{
            session.persist(objModel);
        } catch(Exception ex) {
            if(objModel.getVep_codigo() == null) {
                System.out.println(" [VendaProdutoDao] ERRO! ao gravar VendaProduto!");
                JOptionPane.showMessageDialog(null, "ERRO! ao gravar Venda_Produto, ID = " + objModel.getVep_codigo());
                tx.rollback();
                return;
            }
        }

        tx.commit();
        System.out.println(" [VendaProdutoDao] Sucesso! Venda_Produto INCLUIDA ID = " + objModel.getVep_codigo());
    }
    
    @Override
    public void alterar(VendaProdutoModel objModel) throws Exception {
    }
    
    @Override
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        System.out.println("\n [VendaProdutoDao] CONSULTAR iniciado");
        
        String tabVendaPro = VendaProdutoModel.class.getName();
        String hql = " FROM " + tabVendaPro + " vep " 
                +    " JOIN FETCH vep.venda_vendaPro v "
                +    " JOIN FETCH vep.produto_vendaPro p ";
        
        if (filtro != null && !filtro.trim().isEmpty()) {
            hql += " WHERE " + filtro;
        }
        hql += " ORDER BY v.vda_codigo";
        
        System.out.println(" [VendaProdutoDao] query HQL executada:");
        System.out.println(   hql + " \n");
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var query = session.createQuery(hql, VendaProdutoModel.class);
            
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
        System.out.println(" [VendaProdutoDao] get() foi iniciado... \n");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }
    
    
    public void inserirItens(int vdaCodigo, ArrayList<VendaProdutoModel> itens) throws Exception {
        System.out.println(" [ProdutoDao] inserrirItens() foi iniciado... ");
        System.out.println(" vda_codigo = " + vdaCodigo);
        System.out.println(" ---------------------------");
        System.out.println("    ITENS ");
        System.out.println("");
        
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            for (VendaProdutoModel item : itens) {
                // vincula o código da venda ao item
                item.getVenda_VendaProduto().setVda_codigo(vdaCodigo);

                // persiste o item
                session.persist(item);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Erro ao inserir itens da venda: " + e.getMessage(), e);
        }
    }

}


