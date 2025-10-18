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
    public void alterar(VendaProdutoModel vp) throws Exception {
        System.out.println("\n [VendaProdutoDao] ALTERAR iniciado");
        
        // Venda_Produto com dados NOVOS
        Integer cod_new_vp = vp.getVep_codigo();
        System.out.println(" [VendaProdutoDao] vep_codigo (new_venda) = " + vp.getVep_codigo());
        
        // Venda_Produto com dados ANTIGOS;
        VendaProdutoModel old_vp = get(cod_new_vp);
        Integer cod_old_vp = old_vp.getVep_codigo();
        
        if (cod_old_vp == null) {
            throw new Exception(" [VendaProdutoDao] Venda não encontrado no banco para atualização!");
        }
        
        System.out.println(" vda_codigo (old_venda) = " + cod_old_vp);
        if (cod_new_vp != cod_old_vp) {
            System.out.println(" [VendaProdutoDao] ERRO! vda_codigo (old_venda) diferenda da venda atual = " + old_vp.getVep_codigo());    
            return;
        }
        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        try {
           session.merge(vp); 
           tx.commit();
           
            System.out.println(" [VendaProdutoDao] Sucesso! ao Alterar Venda_Produto ID = " + vp.getVep_codigo());

       }catch (Exception ex) {
           if(tx != null) {
                System.out.println(" [VendaProdutoDao] ERRO! ao Alterar Venda_Produto ID = " + vp.getVep_codigo());
                tx.rollback();
           }
       } finally {
               session.close();              
        }
    }
    
    public void alterar(VendaProdutoModel vp, Session session) throws Exception {
        System.out.println("\n [VendaProdutoDao] ALTERAR iniciado (mesma session)");

        session.merge(vp); 
        System.out.println(" [VendaProdutoDao] Sucesso! ao Alterar Venda_Produto ID = " + vp.getVep_codigo());
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
        System.out.println(" [VendaProdutoDao] get(id) foi iniciado...");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }

    public VendaProdutoModel get(int id, Session session) {
        System.out.println(" [VendaProdutoDao] get(id com session) foi iniciado...");
        return (VendaProdutoModel) session.getReference(VendaProdutoModel.class, id);
    }

}


