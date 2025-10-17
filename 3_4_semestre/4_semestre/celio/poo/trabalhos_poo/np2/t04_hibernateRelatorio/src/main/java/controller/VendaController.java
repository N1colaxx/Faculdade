package controller;

import model.ClienteModel;
import model.ProdutoModel;
import model.UsuarioModel;
import model.VendaModel;
import model.VendaProdutoModel;
import model.VendaPagtoModel;

import dao.ClienteDao;
import dao.UsuarioDao;
import dao.VendaDao;
import dao.VendaProdutoDao;
import dao.VendapagtoDao;

import relatorios.VendaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class VendaController implements GenericController<VendaModel> {

    private VendaDao vendaDao = null; 
            
    public VendaController() {
        this.vendaDao = new VendaDao();
    }

    @Override
    public void incluir(VendaModel obj) throws Exception {
        vendaDao.incluir(obj);
    }

    public void incluir(int usu_cod, int cli_cod, LocalDate data_v, double valor_v, double desc_v, double total_v, String obs_v, 
        ArrayList<VendaProdutoModel> itens, 
        ArrayList<VendaPagtoModel> pgtos) throws Exception {
        
        System.out.println("\n [VendaController] void inclui(venda, itens, pagtos) iniciou...");

        System.out.println(" [VendaController] Verificando User...");
        UsuarioModel usu_v = new UsuarioDao().get(usu_cod);
        if (usu_v == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Usuario Invalido!");
            return;
        }
        System.out.println(" [VendaController] User APROVADO!");

        System.out.println(" [VendaController] Verificando Cliente...");
        ClienteModel cli_v = new ClienteDao().get(cli_cod);
        if (cli_v == null) {
            JOptionPane.showMessageDialog(null, "ERRO! Cliente Invalido!");
            return;
        }
        System.out.println(" [VendaController] Cliente APROVADO!");

        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transacao = null;
        
        try {
            transacao = session.beginTransaction();
            
            // Recarrega os objetos nesta sessão
            usu_v = new UsuarioDao().get(usu_cod, session);
            cli_v = new ClienteDao().get(cli_cod, session);

            VendaModel venda = new VendaModel();
                venda.setUsu_venda(usu_v);
                venda.setCli_venda(cli_v);
                venda.setVda_data(data_v);
                venda.setVda_valor(valor_v);
                venda.setVda_desconto(desc_v);
                venda.setVda_total(total_v);
                venda.setVda_obs(obs_v);
            
            VendaModel v = vendaDao.incluir(venda, session);
            System.out.println(" [VendaController] Venda salva com ID(new_v): " + v.getVda_codigo());

            
            for (VendaProdutoModel item : itens) {
                ProdutoModel p = item.getProduto_VendaProduto();
                VendaProdutoModel vp = new VendaProdutoModel();
                
                vp.setVep_codigo(null);
                vp.setVenda_VendaProduto(venda);
                vp.setProduto_VendaProduto(p);
                vp.getProduto_VendaProduto().setPRO_NOME(p.getPRO_NOME() );
                vp.getProduto_VendaProduto().setPRO_UNIDADE(p.getPRO_UNIDADE());
                vp.setVep_qtde(item.getVep_qtde());
                vp.setVep_preco(item.getVep_preco());
                vp.setVep_desconto(item.getVep_desconto());
                vp.setVep_total(item.getVep_total());
                
                venda.adicionarVendaProduto(vp);
            }

            for (VendaPagtoModel pagto : pgtos) {
                
                VendaPagtoModel vg = new VendaPagtoModel();
                vg.setVdp_codigo(null);
                vg.setVenda_VendaPagto(venda);
                vg.setFormapagto_Vendapagto(pagto.getFormapagto_VendaPagto());
                vg.setVdp_valor(pagto.getVdp_valor());
                
                venda.adicionarVendaPagto(vg);
           }
            
            vendaDao.atualizar(venda, session);
            
            transacao.commit();
            System.out.println(" [VendaController] Transação concluída com sucesso!");
        
        } catch (Exception e) {
            if (transacao != null && transacao.isActive()) {
                transacao.rollback();
            }
            
            JOptionPane.showMessageDialog(null, "ERRO ao salvar venda: " + e.getMessage());
            throw e;
        } finally {
            session.close();
        }
        
        System.out.println("\n [VendaController] void inclui() terminou");
    }
    
    @Override
    public void alterar(VendaModel objec) throws Exception {
        
    }
    
    
    public void alterar (
        int vda_cod, int usu_cod, int cli_cod, LocalDate data_v, double valor_v, double desc_v, double total_v, String obs_v, 
        ArrayList<VendaProdutoModel> itens, 
        ArrayList<VendaPagtoModel> pgtos) throws Exception {
        
        System.out.println("\n [VendaController] void ALTERAR...");

        VendaProdutoController vp_ctrl = new VendaProdutoController();
        VendapagtoController pg_ctrl = new VendapagtoController();
        
        System.out.println(" [VendaController] Verificando Venda...");
        VendaModel new_venda = vendaDao.get(vda_cod);
        if (new_venda == null) { JOptionPane.showMessageDialog(null, "ERRO! Venda Invalido!");
            return;
        }
        System.out.println(" [VendaController] VendaAPROVADO!");

        
        System.out.println(" [VendaController] Verificando User...");
        UsuarioModel new_user_v = new UsuarioDao().get(usu_cod);
        if (new_user_v == null) { JOptionPane.showMessageDialog(null, "ERRO! Usuario Invalido!");
            return;
        }
        System.out.println(" [VendaController] User APROVADO!");

        
        System.out.println(" [VendaController] Verificando Cliente...");
        ClienteModel new_cli_v = new ClienteDao().get(cli_cod);
        if (new_cli_v == null) {JOptionPane.showMessageDialog(null, "ERRO! Cliente Invalido!");
            return;
        }
        System.out.println(" [VendaController] Cliente APROVADO!");

        System.out.println(" [VendaController] Verificando Codigo da Venda dos Itens...");
        for(int i =0; i < 0; i++){
            for (VendaProdutoModel item : itens) {
                item = vp_ctrl.get(item.getVep_codigo());
            }
        }
        System.out.println(" [VendaController] Item APROVADO!");

        System.out.println(" [VendaController] Verificando Codigo da Venda das Formas de Pagamento...");
        for(int i =0; i < 0; i++){
            for (VendaPagtoModel pagto : pgtos) {
                pagto = pg_ctrl.get(pagto.getVdp_codigo());
            }
        }
        System.out.println(" [VendaController] Item APROVADO!");

        
        
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Transaction transacao = null;
        
        try {
            transacao = session.beginTransaction();
            
            // Recarrega os objetos nesta sessão
            new_venda = vendaDao.get(vda_cod, session);
            new_user_v = new UsuarioDao().get(usu_cod, session);
            new_cli_v = new ClienteDao().get(cli_cod, session);

            for(int i =0; i < 0; i++){
                for (VendaProdutoModel item : itens) {
                    item = vp_ctrl.get(item.getVep_codigo());
                }
            }
            
            for(int i =0; i < 0; i++){
                for (VendaPagtoModel pagto : pgtos) {
                    pagto = pg_ctrl.get(pagto.getVdp_codigo());
                }
            }

            
            new_venda.setVda_codigo(vda_cod);
            new_venda.setUsu_venda(new_user_v);
            new_venda.setCli_venda(new_cli_v);
            new_venda.setVda_data(data_v);
            new_venda.setVda_valor(valor_v);
            new_venda.setVda_desconto(desc_v);
            new_venda.setVda_total(total_v);
            new_venda.setVda_obs(obs_v);
            
            vendaDao.alterar(new_venda);
            System.out.println(" [VendaController] Venda antiga com ID(vda_cod): " + vda_cod);
            System.out.println(" [VendaController] Venda Alterada com ID(new_v): " + new_venda.getVda_codigo());

            
            for (VendaProdutoModel item : itens) {
                ProdutoModel p = item.getProduto_VendaProduto();
                VendaProdutoModel vp = new VendaProdutoModel();
                
                vp.setVep_codigo(item.getVep_codigo());
                vp.setVenda_VendaProduto(new_venda);
                vp.setProduto_VendaProduto(p);
                vp.getProduto_VendaProduto().setPRO_NOME(p.getPRO_NOME() );
                vp.getProduto_VendaProduto().setPRO_UNIDADE(p.getPRO_UNIDADE());
                vp.setVep_qtde(item.getVep_qtde());
                vp.setVep_preco(item.getVep_preco());
                vp.setVep_desconto(item.getVep_desconto());
                vp.setVep_total(item.getVep_total());
                
                vp_ctrl.alterar(vp);
            }

            for (VendaPagtoModel pagto : pgtos) {
                
                VendaPagtoModel vg = new VendaPagtoModel();
                vg.setVdp_codigo(pagto.getVdp_codigo());
                vg.setVenda_VendaPagto(new_venda);
                vg.setFormapagto_Vendapagto(pagto.getFormapagto_VendaPagto());
                vg.setVdp_valor(pagto.getVdp_valor());
                
                pg_ctrl.alterar(vg);
           }
            
            
            transacao.commit();
            System.out.println(" [VendaController] Transação concluída com sucesso!");
        
        } catch (Exception e) {
            if (transacao != null && transacao.isActive()) {
                transacao.rollback();
            }
            
            JOptionPane.showMessageDialog(null, "ERRO ao salvar venda: " + e.getMessage());
            throw e;
        } finally {
            session.close();
        }
        
        System.out.println("\n [VendaController] void inclui() terminou");
    }

    @Override
    public void excluir(VendaModel obj) throws Exception {
        int resposta = JOptionPane.showConfirmDialog(
            null,
            "Deseja realmente EXCLUIR a Venda ? confirmar a ação?", // mensagem
            "Confirmação", // título da janela
            JOptionPane.YES_NO_OPTION, // opções de botões
            JOptionPane.QUESTION_MESSAGE // ícone
        );
        
        if (resposta == JOptionPane.YES_OPTION) {
            vendaDao.excluir(obj);
            System.out.println("Ação confirmada!");
        }
    }

    @Override
    public ArrayList<VendaModel> consultar(String filtro) {
        return vendaDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }
    
    
    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            VendaRelatorio relatorio = new VendaRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    public VendaModel buscarPorCodigo(Integer cod) throws Exception {
        return vendaDao.get(cod); 
    }
    

    
    /**
     * Getters
     */
    
    public VendaDao getDao() {
        return vendaDao;
    }
    
}


