package controller;

import model.FornecedorModel;
import model.ProdutoModel;
import model.UsuarioModel;
import model.CompraModel;
import model.CompraProdutoModel;

import dao.FornecedorDao;
import dao.UsuarioDao;
import dao.CompraDao;

import relatorios.CompraRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import relatorios.CompraProdutoRelatorio;
import util.HibernateUtil;


public class CompraController {

    private CompraDao compraDao = null; 
            
    public CompraController() {
        this.compraDao = new CompraDao();
    }
    
    public void incluir(
            int usu_cod, int for_cod, LocalDate emissao, double valor, double desc, double total, LocalDate dtentrada, String obs,
            ArrayList<CompraProdutoModel> itens
        ) throws Exception {
        
        System.out.println("\n [CompraController] void inclui(compra, itens) iniciou...");

        System.out.println(" [CompraController] Verificando User...");
        UsuarioModel usu_c = new UsuarioDao().get(usu_cod);
        if (usu_c == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Usuario Invalido!");
            return;
        }
        System.out.println(" [CompraController] User APROVADO!");

        System.out.println(" [CompraController] Verificando Cliente...");
        FornecedorModel for_c = new FornecedorDao().get(for_cod);
        if (for_c == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Fornecedor Invalido!");
            return;
        }
        System.out.println(" [CompraController] Fornecedor APROVADO!");

        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transacao = null;
        
        try {
            transacao = session.beginTransaction();
            
            // Recarrega os objetos nesta sessão
            usu_c = new UsuarioDao().get(usu_cod, session);
            for_c = new FornecedorDao().get(for_cod, session);

            CompraModel compra = new CompraModel();
                compra.setUsuario_compra(usu_c);
                compra.setFornecedor_compra(for_c);
                compra.setCpr_emissao(emissao);
                compra.setCpr_valor(valor);
                compra.setCpr_desconto(desc);
                compra.setCpr_valor(total);
                compra.setCpr_dtentrada(dtentrada);
                compra.setCpr_obs(obs);
            
            CompraModel c = compraDao.gravar(compra, session);
            System.out.println(" [CompraController] Venda salva com ID(new_v): " + c.getCpr_codigo());

            
            for (CompraProdutoModel item : itens) {
                ProdutoModel p = item.getProduto_compraPro();
                CompraProdutoModel cpp = new CompraProdutoModel();
                
                cpp.setCpp_codigo(null);
                cpp.setCompra_compraPro(compra);
                cpp.setProduto_compraPro(p);
                cpp.setCpr_qtde(item.getCpr_qtde());
                cpp.setCpr_preco(item.getCpr_preco());
                cpp.setCpr_desconto(item.getCpr_desconto());
                cpp.setCpr_total(item.getCpr_total());
                
                compra.addProduto_listProduto(cpp);
            }


            
            compraDao.alterar(compra, session);
            
            transacao.commit();
            System.out.println(" [CompraController] Transação concluída com sucesso!");
        
        } catch (Exception e) {
            if (transacao != null && transacao.isActive()) {
                transacao.rollback();
            }
            
            JOptionPane.showMessageDialog(null, "ERRO ao salvar compra: " + e.getMessage());
            throw e;
        } finally {
            session.close();
        }
        
        System.out.println("\n [CompraController] void inclui() terminou");
    }

    
    public void alterar(
            int cpr_cod, int usu_cod, int for_cod, LocalDate data_emissao, double valor_v, double desc_v,
            double total_v, LocalDate dtentrada, String obs_v,
            ArrayList<CompraProdutoModel> itens
        ) throws Exception {

        System.out.println("\n [CompraController] void ALTERAR...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Recarrega entidades principais na sessão atual
            CompraModel compra = compraDao.get(cpr_cod, session);
            UsuarioModel user = new UsuarioDao().get(usu_cod, session);
            FornecedorModel for_c = new FornecedorDao().get(for_cod, session);

            if (compra == null) throw new Exception("Compra não encontrada!");
            if (user == null) throw new Exception("Usuário inválido!");
            if (for_c == null) throw new Exception("Fornecedor inválido!");

            System.out.println(" [CompraController] Venda, usuário e cliente carregados na sessão.");

            // Atualiza Compra
            compra.setUsuario_compra(user);
            compra.setFornecedor_compra(for_c);
            compra.setCpr_emissao(data_emissao);
            compra.setCpr_valor(valor_v);
            compra.setCpr_desconto(desc_v);
            compra.setCpr_total(total_v);
            compra.setCpr_dtentrada(dtentrada);
            compra.setCpr_obs(obs_v);

            // Limpa listas antigas
            compra.limparListaProduto();
            
            // Reinsere os itens
            for (CompraProdutoModel item : itens) {
                item.setCompra_compraPro(compra);

                if (item.getCpp_codigo() == null) {
                    System.out.println(" [CompraController] Incluindo novo item: " 
                                       + item.getProduto_compraPro().getPRO_NOME());
                } else {
                    System.out.println(" [CompraController] Atualizando item existente ID = " 
                                       + item.getCpp_codigo());
                }

                compra.addProduto_listProduto(item);
            }
            
            // Persiste toda a compra e suas dependências
            session.merge(compra);

            tx.commit();
            System.out.println(" [CompraController] ALTERAR - Sucesso!");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao alterar compra: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
    
    public void excluir(CompraModel obj) throws Exception {
        int resposta = JOptionPane.showConfirmDialog(
            null,
            "Deseja realmente EXCLUIR a Venda ? ID = "+ obj.getCpr_codigo()+ "\n Confirmar a ação?", // mensagem
            "Confirmação", // título da janela
            JOptionPane.YES_NO_OPTION, // opções de botões
            JOptionPane.QUESTION_MESSAGE // ícone
        );
        
        if (resposta == JOptionPane.YES_OPTION) {
            compraDao.excluir(obj);
            System.out.println("Ação confirmada!");
        }
    }


    public ArrayList<CompraModel> consultar(String filtro) {
        return compraDao.consultar(filtro);
    }
    
    
    public Exception imprimir() {
        Exception retorno = null;
        try {
            CompraRelatorio rc = new CompraRelatorio();
            CompraProdutoRelatorio rcp = new CompraProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<CompraModel> dados_compra = consultar("");
            List<CompraProdutoModel> dados_compraProduto = new CompraProdutoController().consultar("");

            rc.gerarRelatorio(dados_compra);
            rcp.gerarRelatorio(dados_compraProduto);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    
    public CompraModel get(Integer cod) throws Exception {
        return compraDao.get(cod); 
    }
    
    public CompraDao getDao() {
        return compraDao;
    }
    
}