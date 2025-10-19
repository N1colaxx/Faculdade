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

import relatorios.VendaRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import relatorios.VendaPagtoRelatorio;
import relatorios.VendaProdutoRelatorio;
import util.HibernateUtil;


public class VendaController {

    private VendaDao vendaDao = null; 
            
    public VendaController() {
        this.vendaDao = new VendaDao();
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

    
    public void alterar(
            int vda_cod, int usu_cod, int cli_cod, LocalDate data_v, double valor_v, double desc_v, double total_v, String obs_v, 
            ArrayList<VendaProdutoModel> itens, 
            ArrayList<VendaPagtoModel> pgtos) throws Exception {

        System.out.println("\n [VendaController] void ALTERAR...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Recarrega entidades principais na sessão atual
            VendaModel venda = vendaDao.get(vda_cod, session);
            UsuarioModel user = new UsuarioDao().get(usu_cod, session);
            ClienteModel cli = new ClienteDao().get(cli_cod, session);

            if (venda == null) throw new Exception("Venda não encontrada!");
            if (user == null) throw new Exception("Usuário inválido!");
            if (cli == null) throw new Exception("Cliente inválido!");

            System.out.println(" [VendaController] Venda, usuário e cliente carregados na sessão.");

            // Atualiza venda
            venda.setUsu_venda(user);
            venda.setCli_venda(cli);
            venda.setVda_data(data_v);
            venda.setVda_valor(valor_v);
            venda.setVda_desconto(desc_v);
            venda.setVda_total(total_v);
            venda.setVda_obs(obs_v);

            // Limpa listas antigas
            venda.limparItens();
            venda.limparPagtos();

            // Reinsere os itens
            for (VendaProdutoModel item : itens) {
                item.setVenda_VendaProduto(venda);

                if (item.getVep_codigo() == null) {
                    System.out.println(" [VendaController] Incluindo novo item: " 
                                       + item.getProduto_VendaProduto().getPRO_NOME());
                } else {
                    System.out.println(" [VendaController] Atualizando item existente ID = " 
                                       + item.getVep_codigo());
                }

                venda.adicionarVendaProduto(item);
            }

            // Reinsere pagamentos
            for (VendaPagtoModel pg : pgtos) {
                pg.setVenda_VendaPagto(venda);

                if (pg.getVdp_codigo() == null) {
                    System.out.println(" [VendaController] Incluindo novo pagamento.");
                } else {
                    System.out.println(" [VendaController] Atualizando pagamento ID = " + pg.getVdp_codigo());
                }

                venda.adicionarVendaPagto(pg);
            }

            // Persiste toda a venda e suas dependências
            session.merge(venda);

            tx.commit();
            System.out.println(" [VendaController] ALTERAR - Sucesso!");

        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            JOptionPane.showMessageDialog(null, "Erro ao alterar venda: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }
    
    public void excluir(VendaModel obj) throws Exception {
        int resposta = JOptionPane.showConfirmDialog(
            null,
            "Deseja realmente EXCLUIR a Venda ? ID = "+ obj.getVda_codigo() + "\n Confirmar a ação?", // mensagem
            "Confirmação", // título da janela
            JOptionPane.YES_NO_OPTION, // opções de botões
            JOptionPane.QUESTION_MESSAGE // ícone
        );
        
        if (resposta == JOptionPane.YES_OPTION) {
            vendaDao.excluir(obj);
            System.out.println("Ação confirmada!");
        }
    }


    public ArrayList<VendaModel> consultar(String filtro) {
        return vendaDao.consultar(filtro);
    }
    
    
    public Exception imprimir() {
        Exception retorno = null;
        try {
            VendaRelatorio vr = new VendaRelatorio();
            VendaPagtoRelatorio vpr = new VendaPagtoRelatorio();
            VendaProdutoRelatorio vprr = new VendaProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaModel> dados_vr = consultar("");
            List<VendaPagtoModel> dados_vpr = new VendapagtoController().consultar("");
            List<VendaProdutoModel> dados_vprr = new VendaProdutoController().consultar("");

            vr.gerarRelatorio(dados_vr);
            vpr.gerarRelatorio(dados_vpr);
            vprr.gerarRelatorio(dados_vprr);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    
    public VendaModel get(Integer cod) throws Exception {
        return vendaDao.get(cod); 
    }
    

        
    public VendaDao getDao() {
        return vendaDao;
    }
    
}