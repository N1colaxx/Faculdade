package view;

import controller.CompraController;
import controller.CompraProdutoController;
import controller.ProdutoController;

import model.ProdutoModel;
import model.CompraModel;
import model.CompraProdutoModel;

import tableModel.CompraTableModel;
import tableModel.CompraProdutoTableModel;

import javax.swing.*;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import model.SessionModel;
import model.UsuarioModel;

/**
 * Tela de Cadastro de COMPRAS ( sem Pagamentos).
 */
public class CompraView extends JPanel {
    
    private UsuarioModel userLogado;

    private JLabel lblTitulo;

    // Cabeçalho
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnImprimir, btnGravar;

    // Abas
    private JPanel paneCabecario;
    private JPanel paneCentro;
    private JTabbedPane tabs;

    // ===== Consulta =====
    private JPanel tabConsulta, paneConsultaDados, paneConsultaTabela;
    private JLabel lblId1, lblATxt, lblId2, lblValorGe, lblValorLe;
    private JTextField edtId1, edtId2, edtValorGe, edtValorLe;
    private JButton btnConsultar, btnLimpar;
    private JTable tabelaConsulta;
    private JScrollPane scrollConsulta;
    private CompraTableModel consultaModel;

    // ===== Dados =====
    private JPanel tabDados;
    private JLabel lblCprCodigo, lblUsuCodigo, lblForCodigo, lblCprEmissao,
            lblCprValor, lblCprDesconto, lblCprTotal, lblCprDtEntrada, lblCprObs;
    private JTextField edtCprCodigo, edtUsuCodigo, edtForCodigo, edtCprEmissao,
            edtCprValor, edtCprDesconto, edtCprTotal, edtCprDtEntrada;
    private JTextArea edtCprObs;

    // ===== Itens =====
    private JPanel tabItens, paneItemEditor, paneItemTabela;
    private JLabel lblProCod, lblProNome, lblProUn, lblProPreco; // Produto
    private JTextField edtProCod, edtProNome, edtProUn, edtProPreco;
    private JLabel lblCppQtde, lblCppPreco, lblCppDesconto, lblCppTotal; // Compra_Produto, no der mostra que aqui é os campos da compra = CPR, mas deixei assim pra eu n ficar comfuso
    private JTextField edtCppQtde, edtCppPreco, edtCppDesconto, edtCppTotal;
    private JButton btnAddItem, btnDelItem, btnUpItem;
    private JTable tabItensGrid;
    private CompraProdutoTableModel itensModel;

    // Estado
    private String operacao = "";
    private final ArrayList<CompraProdutoModel> listaItens = new ArrayList<>();
    private final ArrayList<CompraModel> listaCompras = new ArrayList<>();

    public CompraView() {
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
        
        configurarAcoes();
        
        limparTudo();
    }

    private void instanciar() {

        /**
         * Cabeçalho
         */
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");
        btnUltimo = new JButton("Último");
        btnNovo = new JButton("Novo");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnImprimir = new JButton("Imprimir");
        btnGravar = new JButton("Gravar");
            btnGravar.setFocusable(false);
            

        paneCabecario = new JPanel(null);
        paneCentro = new JPanel(null);
        paneCentro.setBackground(new Color(245, 250, 255));
        tabs = new JTabbedPane();

        // ===== TÍTULO =====
        lblTitulo = new JLabel("Compras", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
            lblTitulo.setForeground(new Color(30, 30, 120));

        /**
         * Consulta
         */
        tabConsulta = new JPanel(null);
        paneConsultaDados = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        lblId1 = new JLabel("COD 1");
        edtId1 = new JTextField();

        lblATxt = new JLabel("à");

        lblId2 = new JLabel("COD 2");
        edtId2 = new JTextField();

        lblValorGe = new JLabel("valor >=");
        edtValorGe = new JTextField();

        lblValorLe = new JLabel("valor <=");
        edtValorLe = new JTextField();

        btnConsultar = new JButton("Consulta");
        btnLimpar = new JButton("Limpa");

        consultaModel = new CompraTableModel(listaCompras);
        tabelaConsulta = new JTable(consultaModel);

        scrollConsulta = new JScrollPane(tabelaConsulta);

        
        
        /**
         * Dados
         */
        tabDados = new JPanel(null);

        lblCprCodigo = new JLabel("Código:");
        edtCprCodigo = new JTextField("");
            edtCprCodigo.setEditable(false);
            edtCprCodigo.setFocusable(false);
            
        lblUsuCodigo = new JLabel("Usuário:");
        edtUsuCodigo = new JTextField();
            edtUsuCodigo.setEditable(false);
            edtUsuCodigo.setFocusable(false);

        lblForCodigo = new JLabel("Fornecedor:");
        edtForCodigo = new JTextField();
            edtForCodigo.setEditable(false);
            edtForCodigo.setFocusable(false);

        lblCprEmissao = new JLabel("Emissão (yyyy-MM-dd):");
        edtCprEmissao = new JTextField(LocalDate.now().toString());
            edtCprEmissao.setEditable(false);
            edtCprEmissao.setFocusable(false);

        lblCprDtEntrada = new JLabel("Entrada (yyyy-MM-dd):");
        edtCprDtEntrada = new JTextField(LocalDate.now().toString());
            edtCprDtEntrada.setEditable(false);
            edtCprDtEntrada.setFocusable(false);

        lblCprObs = new JLabel("Obs.:");
        edtCprObs = new JTextArea();
            edtCprObs.setLineWrap(true);
            edtCprObs.setWrapStyleWord(true);
            edtCprObs.setEditable(false);
            edtCprObs.setFocusable(false);

        lblCprValor = new JLabel("Valor:");
        edtCprValor = new JTextField();
            edtCprValor.setEditable(false);
            edtCprValor.setFocusable(false);

        lblCprDesconto = new JLabel("Desconto:");
        edtCprDesconto = new JTextField("");
            edtCprDesconto.setEditable(false);
            edtCprDesconto.setFocusable(false);

        lblCprTotal = new JLabel("Total:");
        edtCprTotal = new JTextField();
            edtCprTotal.setEditable(false);
            edtCprTotal.setFocusable(false);

        /**
         * ITENS
         */
        tabItens = new JPanel(null);
        paneItemEditor = new JPanel(null);
        paneItemTabela = new JPanel(null);

        // produto
        lblProCod = new JLabel("Prod Cód:");
        edtProCod = new JTextField();
            edtProCod.setEditable(false);
            edtProCod.setFocusable(false);

        lblProNome = new JLabel("Nome:");
        edtProNome = new JTextField();
            edtProNome.setEditable(false);
            edtProNome.setFocusable(false);
            
        lblProUn = new JLabel("Und:");
        edtProUn = new JTextField();
            edtProUn.setEditable(false);
            edtProUn.setFocusable(false);

        lblProPreco = new JLabel("Preco Produto");
        edtProPreco = new JTextField();
            edtProPreco.setEditable(false);
            edtProPreco.setFocusable(false);

        //compra_produto = cpp
        lblCppQtde = new JLabel("Qtde:");
        edtCppQtde = new JTextField();
            edtCppQtde.setEditable(false);
            edtCppQtde.setFocusable(false);

        lblCppPreco = new JLabel("Preco:");
        edtCppPreco = new JTextField();
            edtCppPreco.setEditable(false);
            edtCppPreco.setFocusable(false);

        lblCppDesconto = new JLabel("Desconto:");
        edtCppDesconto = new JTextField();
            edtCppDesconto.setEditable(false);
            edtCppDesconto.setFocusable(false);

        lblCppTotal = new JLabel("Total:");
        edtCppTotal = new JTextField();
            edtCppTotal.setEditable(false);
            edtCppTotal.setFocusable(false);

        btnAddItem = new JButton("Adicionar");
            btnAddItem.setFocusable(false);
        btnDelItem = new JButton("Remover");
            btnDelItem.setFocusable(false);
        btnUpItem = new JButton("Update");
            btnUpItem.setFocusable(false);


        itensModel = new CompraProdutoTableModel(listaItens);
        tabItensGrid = new JTable(itensModel);
        tabItensGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro);
        paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);
        paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);
        paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);
        paneCabecario.add(btnImprimir);
        paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        add(paneCentro);
        paneCentro.add(lblTitulo);
        paneCentro.add(tabs);

        /**
         * Aba Consulta
         */
        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);

        paneConsultaDados.add(lblId1);
        paneConsultaDados.add(edtId1);

        paneConsultaDados.add(lblATxt);

        paneConsultaDados.add(lblId2);
        paneConsultaDados.add(edtId2);

        paneConsultaDados.add(lblValorGe);
        paneConsultaDados.add(edtValorGe);

        paneConsultaDados.add(lblValorLe);
        paneConsultaDados.add(edtValorLe);

        paneConsultaDados.add(btnConsultar);
        paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollConsulta);
        tabs.addTab("Consulta", tabConsulta);

        /**
         * Aba Dados
         */
        tabDados.add(lblCprCodigo);
        tabDados.add(edtCprCodigo);

        tabDados.add(lblUsuCodigo);
        tabDados.add(edtUsuCodigo);

        tabDados.add(lblForCodigo);
        tabDados.add(edtForCodigo);
        
        tabDados.add(lblCprEmissao);
        tabDados.add(edtCprEmissao);
        
        tabDados.add(lblCprValor);
        tabDados.add(edtCprValor);
        
        tabDados.add(lblCprDesconto);
        tabDados.add(edtCprDesconto);
        
        tabDados.add(lblCprTotal);
        tabDados.add(edtCprTotal);
        
        tabDados.add(lblCprDtEntrada);
        tabDados.add(edtCprDtEntrada);
        
        tabDados.add(lblCprObs);
        tabDados.add(edtCprObs);

        tabs.addTab("Dados", tabDados);

        /**
         * Aba Itens
         */
        tabItens.add(paneItemEditor);
        tabItens.add(paneItemTabela);
        
        //produto
        paneItemEditor.add(lblProCod);
        paneItemEditor.add(edtProCod);
        
        paneItemEditor.add(lblProNome);
        paneItemEditor.add(edtProNome);

        paneItemEditor.add(lblProUn);
        paneItemEditor.add(edtProUn);
        
        // compra_produto
        paneItemEditor.add(lblCppQtde);
        paneItemEditor.add(edtCppQtde);
        
        paneItemEditor.add(lblCppPreco);
        paneItemEditor.add(edtCppPreco);
        
        paneItemEditor.add(lblCppDesconto);
        paneItemEditor.add(edtCppDesconto);
        
        paneItemEditor.add(lblCppTotal);
        paneItemEditor.add(edtCppTotal);
        
        paneItemEditor.add(btnAddItem);
        paneItemEditor.add(btnDelItem);
        paneItemEditor.add(btnUpItem);
        
        paneItemTabela.add(new JScrollPane(tabItensGrid));
        tabs.addTab("Itens (Produtos)", tabItens);

    }

    private void posicionar() {
        paneCabecario.setBounds(10, 10, 1470, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);

        btnPrimeiro.setBounds(0, 7, 100, 25);
        btnAnterior.setBounds(105, 7, 100, 25);
        btnProximo.setBounds(210, 7, 100, 25);
        btnUltimo.setBounds(315, 7, 100, 25);
        btnNovo.setBounds(520, 7, 100, 25);
        btnAlterar.setBounds(625, 7, 100, 25);
        btnExcluir.setBounds(730, 7, 100, 25);
        btnImprimir.setBounds(1320, 7, 120, 25);
        btnGravar.setBounds(1190, 7, 120, 25);

        paneCentro.setBounds(10, 60, 1470, 770);
        lblTitulo.setBounds(0, 0, 1450, 30);
        tabs.setBounds(10, 10, 1450, 750);

        /**
         * Consulta
         */ 
        tabConsulta.setBounds(0, 0, 1450, 750);
        paneConsultaDados.setBounds(10, 10, 1425, 60);
        paneConsultaTabela.setBounds(10, 80, 1425, 650);

        lblId1.setBounds(10, 10, 30, 25);
        edtId1.setBounds(45, 10, 80, 25);
        lblATxt.setBounds(130, 10, 10, 25);
        lblId2.setBounds(150, 10, 30, 25);
        edtId2.setBounds(185, 10, 80, 25);
        lblValorGe.setBounds(275, 10, 70, 25);
        edtValorGe.setBounds(345, 10, 100, 25);
        lblValorLe.setBounds(455, 10, 70, 25);
        edtValorLe.setBounds(525, 10, 100, 25);

        btnConsultar.setBounds(640, 10, 110, 25);
        btnLimpar.setBounds(760, 10, 110, 25);

        scrollConsulta.setBounds(0, 0, 1425, 650);
        
        
        
        /**
         * Dados
         */
        lblCprCodigo.setBounds(10, 15, 60, 25);
        edtCprCodigo.setBounds(75, 15, 100, 25);
        
        lblUsuCodigo.setBounds(190, 15, 60, 25);
        edtUsuCodigo.setBounds(255, 15, 80, 25);
        
        lblForCodigo.setBounds(345, 15, 70, 25);
        edtForCodigo.setBounds(420, 15, 80, 25);
        
        lblCprEmissao.setBounds(510, 15, 160, 25);
        edtCprEmissao.setBounds(670, 15, 120, 25);
        
        lblCprDtEntrada.setBounds(800, 15, 160, 25);
        edtCprDtEntrada.setBounds(960, 15, 120, 25);

        lblCprObs.setBounds(10, 55, 40, 25);
        edtCprObs.setBounds(55, 55, 1050, 120);

        lblCprValor.setBounds(10, 185, 60, 25);
        edtCprValor.setBounds(70, 185, 120, 25);
        
        lblCprDesconto.setBounds(200, 185, 70, 25);
        edtCprDesconto.setBounds(270, 185, 120, 25);
        
        lblCprTotal.setBounds(400, 185, 60, 25);
        edtCprTotal.setBounds(460, 185, 120, 25);

        /**
         * Itens
         */
        tabItens.setBounds(0, 0, 1450, 750);
        paneItemEditor.setBounds(10, 10, 1425, 120);
        paneItemTabela.setBounds(10, 140, 1425, 560);

        //produto
        lblProCod.setBounds(10, 10, 80, 25);
        edtProCod.setBounds(90, 10, 80, 25);
        
        lblProNome.setBounds(180, 10, 45, 25);
        edtProNome.setBounds(230, 10, 320, 25);
        
        lblProUn.setBounds(560, 10, 35, 25);
        edtProUn.setBounds(600, 10, 50, 25);
 
        lblProPreco.setBounds(605, 10, 35, 25);
        edtProPreco.setBounds(645, 10, 50, 25);

        //compra_produto
        lblCppQtde.setBounds(10, 45, 50, 25);
        edtCppQtde.setBounds(60, 45, 80, 25);
        
        lblCppPreco.setBounds(150, 45, 45, 25);
        edtCppPreco.setBounds(200, 45, 100, 25);
        
        lblCppDesconto.setBounds(300, 45, 45, 25);
        edtCppDesconto.setBounds(350, 45, 100, 25);
        
        lblCppTotal.setBounds(460, 45, 60, 25);
        edtCppTotal.setBounds(530, 45, 120, 25);

        btnAddItem.setBounds(660, 45, 110, 25);
        btnDelItem.setBounds(780, 45, 110, 25);
        btnUpItem.setBounds(900, 45, 110, 25);

        JScrollPane spItens = (JScrollPane) paneItemTabela.getComponent(0);
        spItens.setBounds(0, 0, 1425, 560);

        
    }
    
    private void configurarAcoes() {
        // Cabeçalho
        btnNovo.addActionListener(e -> incluir());
        btnAlterar.addActionListener(e -> alterar());
        btnGravar.addActionListener(e -> gravar());
        btnExcluir.addActionListener(e -> excluirSelecionada());
        
        btnImprimir.addActionListener(e -> {
            System.out.println("\n [CompraView] btnImprimir clicado \n ");
            
            CompraController ctrl = new CompraController();
            Exception retorno = ctrl.imprimir();
            if(retorno != null) {
                JOptionPane.showMessageDialog(null, "Erro no Relatório de Compra /n" + retorno.getMessage());     
            }
            
            System.out.println(" [CompraView] btnImprimir terminou");
        });
        
        btnPrimeiro.addActionListener(e -> selecionarIndice(0));
        btnAnterior.addActionListener(e -> navegar(-1));
        btnProximo.addActionListener(e -> navegar(+1));
        btnUltimo.addActionListener(e -> selecionarIndice(listaCompras.size() - 1));

        /**
         * Consulta
         */
        btnConsultar.addActionListener(e -> consultarCompra());
        btnLimpar.addActionListener(e -> {
            edtId1.setText("");
            edtId2.setText("");
            edtValorGe.setText("");
            edtValorLe.setText("");
            limparTabelaConsulta();
        });
        
        /**
         * Dados
         */
        
        edtCprDesconto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                recomputarTotais();
            }
        });
        
        
        
        /**
         *  Itens
         */
        
        // produto ao sair do código
        edtProCod.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                preencherProduto();
            }
        });
        // subtotal ao sair da qtde/preço
        edtCppQtde.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                recomputarTotais();
            }
        });
        
        edtCppDesconto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                recomputarTotais();
            }
        });
        
        btnAddItem.addActionListener(e -> {
            System.out.println("\n [CompraView] btnAddItem clicado");
            
            try {
                adicionarItem();
            } catch (Exception ex) {
                System.out.println("\n [CompraView] ERRO! ao executar btrAddItem \n" + ex);
            }
        });
        
        btnDelItem.addActionListener(e -> removerItemSelecionado());
        btnUpItem.addActionListener(e -> atualizarProdutoSelecionado());
        
        /**
         * seleção nas tabelas
         */
        
        // Consulta: ao selecionar linha, joga para filtros/editores
        tabelaConsulta.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            if (!e.getValueIsAdjusting()) {
                selecionarLinhaConsulta();
            }

        });
        tabItensGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int viewSel = tabItensGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabItensGrid.convertRowIndexToModel(viewSel);
                CompraProdutoModel it = itensModel.getItem(modelSel);
                mostrarItem(it);
            }
        });
    }
   
    
    /**
     * Consulta 
     */
    private String filtroConsulta() {
        StringBuilder cond = new StringBuilder();
        String id1 = edtId1.getText().trim();
        String id2 = edtId2.getText().trim();
        String ge = edtValorGe.getText().trim().replace(",", ".");
        String le = edtValorLe.getText().trim().replace(",", ".");

        if (!id1.isEmpty()) {
            cond.append("(cpr_codigo >= ").append(id1).append(")");
        }
        if (!id2.isEmpty()) {
            if (cond.length() > 0) {
                cond.append(" AND ");
            }
            cond.append("(cpr_codigo <= ").append(id2).append(")");
        }
        if (!ge.isEmpty()) {
            if (cond.length() > 0) {
                cond.append(" AND ");
            }
            cond.append("(cpr_total >= ").append(ge).append(")");
        }
        if (!le.isEmpty()) {
            if (cond.length() > 0) {
                cond.append(" AND ");
            }
            cond.append("(cpr_total <= ").append(le).append(")");
        }
        return cond.toString();
    }
    
    private void consultarCompra() {
          System.out.println("\n [CompraView] void consultarCompra() iniciado...");
          try {
              limparTabelaConsulta();

              ArrayList<CompraModel> lista = new CompraController().consultar(filtroConsulta());

              if (lista == null) lista = new ArrayList<>();

              consultaModel = new CompraTableModel(lista);

              tabelaConsulta.setModel(consultaModel);

          } catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Erro na consulta: " + ex.getMessage());
          }
      }
    
    private void limparTabelaConsulta() {
        System.out.println(" [CompraView] limpando tabela.");
        consultaModel = new CompraTableModel(new ArrayList<>());
        tabelaConsulta.setModel(consultaModel);
    }
    
    private void selecionarLinhaConsulta() {
        System.out.println("\n [CompraView] void selecionarLinhaConsulta() iniciou...");
        int viewSel = tabelaConsulta.getSelectedRow();
        
        if (viewSel < 0) return;
        
        int modelSel = tabelaConsulta.convertRowIndexToModel(viewSel);
        int cpr = Integer.parseInt(String.valueOf(consultaModel.getValueAt(modelSel, 0))); // vda_codigo

        try {
            String cond = "(c.cpr_codigo = " + cpr + ")";
            CompraModel compra = new CompraController().get(cpr);
            ArrayList<CompraProdutoModel> vendaProduto = new CompraProdutoController().consultar(cond);
            
            if (compra == null) {
                JOptionPane.showMessageDialog(this, "Compra não encontrada.");
                return;
            }
            
            while (itensModel.getRowCount() > 0) {
                itensModel.removeItem(0);
            }

            mostrarDados(compra);

            for (CompraProdutoModel it : vendaProduto) {
                itensModel.addItem(it);
            }
            
            recomputarTotais();
            
            // ir para a aba "Dados" 
            tabs.setSelectedComponent(tabDados);

            setOperacao("incluir");
            System.out.println("\n [CompraView] void selecionarLinhaConsulta() terminou.");
            System.out.println(" [CompraView] Operacao = " + getOperacao());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar venda: " + ex.getMessage());
        }
    }
        
    private void selecionarIndice(int idx) {
        if (idx < 0 || idx >= listaCompras.size()) return;
        CompraModel c = listaCompras.get(idx);
        mostrarDados(c);
    }

    private void navegar(int delta){
        if (listaCompras.isEmpty()) return;
        int atual = -1;
        int cod = parseInt(edtCprCodigo.getText());
        for (int i=0;i<listaCompras.size();i++){
            if (listaCompras.get(i).getCpr_codigo()== cod) { atual = i; break; }
        }
        int prox = (atual < 0 ? 0 : atual + delta);
        if (prox < 0) prox = 0;
        if (prox >= listaCompras.size()) prox = listaCompras.size()-1;
        selecionarIndice(prox);
    }

    
    
    // ==== Itens ====
    private void preencherProduto() {
        System.out.println("\n [CompraView] void preencherProduto() iniciado...");
        try {  
            int cod = parseInt(edtProCod.getText());
            if (cod <= 0) { limparCamposProduto(); return; }
            
            ProdutoModel p = new ProdutoController().get(cod);

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado/ativo.");
                limparCamposProduto();
                return;
            }
            edtProNome.setText(p.getPRO_NOME());
            edtProUn.setText(p.getPRO_UNIDADE());
//            edtProQtde -> o user que seta 
            edtProPreco.setText(fmt(p.getPRO_PRECO())); // ftm -> converte double em string
//            edtProDesconto -> o user que seta
            recalcProTotal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao buscar produto: " + ex.getMessage());
        }
    }

    private void recalcProTotal() {
        double qt = parseDouble(edtCppQtde.getText());
        double pr = parseDouble(edtProPreco.getText());
        double desc = parseDouble(edtCppDesconto.getText());
        edtCppTotal.setText(fmt((qt * pr) - desc));
    }
    
    private void adicionarItem() throws Exception{
        System.out.println(" [CompraView] void adicionarItem() iniciado...");
        
        Integer pro_cod = parseInt(edtProCod.getText());
        double qt = parseDouble(edtCppQtde.getText());
        double pr = parseDouble(edtProPreco.getText());
        double desc = parseDouble(edtCppDesconto.getText());
    
        if (pro_cod <= 0) { JOptionPane.showMessageDialog(this, "Informe o código do produto."); return; }
        
        if (qt <= 0 || pr <= 0) { JOptionPane.showMessageDialog(this, "Qtde e Preço precisam ser > 0."); return; }
        
        ProdutoModel p = new ProdutoModel();
        CompraModel c = new CompraModel();
        c.setCpr_codigo(0);
        
        try {
            p =  new ProdutoController().get(pro_cod);
        } catch (Exception e) {
            System.out.println(" [CompraView] ERRO ao CRIAR O PRODUTO da tabela" + e);
            throw e;
        }
            


        CompraProdutoModel it = new CompraProdutoModel();
            it.setCpp_codigo(null);
            it.setCompra_compraPro(c);
            it.setProduto_compraPro(p);
            it.getProduto_compraPro().setPRO_NOME(edtProNome.getText().trim());
            it.setCpp_qtde(qt);
            it.setCpp_preco(pr);
            it.setCpp_desconto(desc);
            double total = ((qt * pr) - desc);
            it.setCpp_total(total);

        itensModel.addItem(it);
        
        limparCamposProduto();
        recomputarTotais();
    }

    private void removerItemSelecionado() {
        int row = tabItensGrid.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um item."); return; }
        int modelRow = tabItensGrid.convertRowIndexToModel(row);
        itensModel.removeItem(modelRow);
        recomputarTotais();
    }
    
    private void recomputarTotais() {
        double valor_itens = itensModel.somaTotais();
        edtCprValor.setText(fmt(valor_itens));
        double desc_compra = parseDouble(edtCprDesconto.getText());
        
        if (desc_compra < 0) desc_compra = 0;
        
        if (desc_compra > valor_itens) {
            JOptionPane.showMessageDialog(this, "ERRO! Desconto da Compra não pode ser maior que o Valor Total da Compra");
            return;
        }
        
        double total = (valor_itens - desc_compra);
        edtCprTotal.setText(fmt(total));
    }
    
    private void limparCamposProduto() {
        edtProCod.setText("");
        edtProNome.setText("");
        edtProUn.setText("");
        edtProPreco.setText("");
        //compra_produto
        edtCppQtde.setText("");
        edtCppPreco.setText("");
        edtCppDesconto.setText("");
        edtCppTotal.setText("");
    }

    private void atualizarProdutoSelecionado() {
        System.out.println("\n [CompraView] void atualizarProdutoSelecionado() iniciado...");

        int row = tabItensGrid.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para atualizar.");
            return;
        }

        int modelRow = tabItensGrid.convertRowIndexToModel(row);

        try {
            // Obtém o item atual
            CompraProdutoModel it = itensModel.getItem(modelRow);
            if (it == null) {
                JOptionPane.showMessageDialog(this, "Item inválido ou não encontrado.");
                return;
            }

            // Lê os novos valores dos campos
            int pro_cod = parseInt(edtProCod.getText());
            double qt = parseDouble(edtCppQtde.getText());
            double preco = parseDouble(edtProPreco.getText());
            double desc = parseDouble(edtCppDesconto.getText());
            double total = (qt * preco) - desc;

            if (pro_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe um código de produto válido.");
                return;
            }

            if (qt <= 0 || preco <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade e preço devem ser maiores que zero.");
                return;
            }

            // Atualiza os dados no objeto
            it.getProduto_compraPro().setPRO_CODIGO(pro_cod);
            it.getProduto_compraPro().setPRO_NOME(edtProNome.getText());
            it.setCpp_qtde(qt);
            it.setCpp_preco(preco);
            it.setCpp_desconto(desc);
            it.setCpp_total(total);

            // Atualiza a tabela
            itensModel.setItem(modelRow, it);

            // Atualiza totais da venda
            recalcProTotal();
            recomputarTotais();

            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar item: " + ex.getMessage());
            ex.printStackTrace();
        }
    }



    
    /**
    * ==== Seleção e editores ====
    */

    // Preenche os campos do editor de DADOS;
    private void mostrarDados(CompraModel c){
        edtCprCodigo.setText(String.valueOf(c.getCpr_codigo()));
        edtUsuCodigo.setText(String.valueOf(c.getUsuario_compra().getUSU_CODIGO()));
        edtForCodigo.setText(String.valueOf(c.getFornecedor_compra().getFOR_CODIGO() ));
        edtCprEmissao.setText(c.getCpr_emissao()== null ? "" : c.getCpr_emissao().toString());
        edtCprDtEntrada.setText(c.getCpr_dtentrada()== null ? "" : c.getCpr_dtentrada().toString());
        edtCprObs.setText(c.getCpr_obs()== null ? "" : c.getCpr_obs());
        edtCprValor.setText(fmt(c.getCpr_valor() ));
        edtCprDesconto.setText(fmt(c.getCpr_desconto() ));
        edtCprTotal.setText(fmt(c.getCpr_total() ));
    }

    // Preenche os campos do editor de ITENS com base no modelo
    private void mostrarItem(CompraProdutoModel it){
        if (it == null) return;
        edtProCod.setText(String.valueOf(it.getProduto_compraPro().getPRO_CODIGO()));
        edtProNome.setText(it.getProduto_compraPro().getPRO_NOME());
        edtProUn.setText(it.getProduto_compraPro().getPRO_UNIDADE());
        edtProPreco.setText(String.valueOf(it.getProduto_compraPro().getPRO_PRECO() ));
        edtCppQtde.setText(fmt(it.getCpp_qtde() ));
        edtProPreco.setText(fmt(it.getCpp_preco() ));
        edtCppTotal.setText(fmt(it.getCpp_total() ));
    }

    private void limparTudo(){
        //dados
        edtCprCodigo.setText("");
        edtCprDesconto.setText("");
        edtCprDtEntrada.setText("");
        edtCprEmissao.setText("");
        edtCprObs.setText("");
        edtCprTotal.setText("");
        edtCprValor.setText("");
        //itens
        edtProCod.setText("");
        edtProNome.setText("");
        edtProUn.setText("");
        edtProPreco.setText("");
        //compra_produto
        edtCppQtde.setText("");
        edtCppPreco.setText("");
        edtCppDesconto.setText("");
        edtCppTotal.setText("");
    }
    
        
    /**
     * Voids dos BTN
     */


    private void incluir() {
        System.out.println(" [CompraView] void incluir() iniciado");
        
        setOperacao("nova_compra");
        limparTudo();
        
        tabs.setSelectedComponent(tabDados);
        edtCprEmissao.setText(LocalDate.now().toString());
        edtCprDtEntrada.setText(LocalDate.now().toString());
        
        btnGravar.setFocusable(true);
        
        btnAddItem.setFocusable(true);
        btnDelItem.setFocusable(true);
        btnUpItem.setFocusable(true);
        
        // edt DADOS
        edtCprCodigo.setText("0");
            edtCprCodigo.setFocusable(false);
            edtCprValor.setEditable(false);
        
        userLogado = SessionModel.getCurrentUser();
        String cod_UserLogado = String.valueOf(userLogado.getUSU_CODIGO());
        System.out.println(" [CompraView] void novo() -> Codigo user logado = " + cod_UserLogado);
        edtUsuCodigo.setText(cod_UserLogado);
        
	edtUsuCodigo.setEditable(true);
	edtUsuCodigo.setFocusable(true);
        
	edtForCodigo.setEditable(true);
	edtForCodigo.setFocusable(true);
        
	edtCprEmissao.setEditable(true);
	edtCprEmissao.setFocusable(true);
        
	edtCprDtEntrada.setEditable(true);
	edtCprDtEntrada.setFocusable(true);
        
	edtCprObs.setEditable(true);
	edtCprObs.setFocusable(true);
        
        edtCprDesconto.setEditable(true);
        edtCprDesconto.setFocusable(true);
            
        // edt ITENS
        edtProCod.setEditable(true);
        edtProCod.setFocusable(true);
        
	edtCppQtde.setEditable(true);
	edtCppQtde.setFocusable(true);
        
	edtCppDesconto.setEditable(true); 
	edtCppDesconto.setFocusable(true); 
    }

    
    private void incluir_compra(){
        System.out.println("\n [CompraView] void gerar_compra() iniciado...");
        
        try {
            int usu_cod = parseInt(edtUsuCodigo.getText().trim());
            int for_cod = parseInt(edtForCodigo.getText().trim());
            LocalDate emissao = (LocalDate.parse(edtCprEmissao.getText()));
            LocalDate dtentrada = (LocalDate.parse(edtCprDtEntrada.getText()));
            double valor_v = (parseDouble(edtCprValor.getText()));
            double desc_v = (parseDouble(edtCprDesconto.getText()));
            double total_v = (parseDouble(edtCprTotal.getText()));
            String obs_v = (edtCprObs.getText());

            ArrayList<CompraProdutoModel> itens = (itensModel.getLinhas());
    
            // validações mínimas 
            if (usu_cod <= 0) { JOptionPane.showMessageDialog(this,"Informe o usuário."); return; }
            if (for_cod <= 0) { JOptionPane.showMessageDialog(this,"Informe o fornecedor."); return; }

            if(itens == null || itens.isEmpty()) { JOptionPane.showMessageDialog(this, "Preencha os campos de Itens");
                return;
            }
            
            // Gravando a Venda
            new CompraController().incluir(usu_cod, for_cod, emissao, valor_v, desc_v, total_v, dtentrada, obs_v, itens);

            JOptionPane.showMessageDialog(this, "Compra gravada.");
            
            tabs.setSelectedComponent(tabConsulta);
            consultarCompra();
            
            limparTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
        }
    }
    
    private void alterar(){
        System.out.println("\n [CompraView] btnAlterar clicado.");
        
        setOperacao("alterar_gravar");
        
        limparTudo();
        
        tabs.setSelectedComponent(tabDados);
        edtCprEmissao.setText(LocalDate.now().toString());
        edtCprDtEntrada.setText(LocalDate.now().toString());

        btnGravar.setFocusable(true);
        
        btnAddItem.setFocusable(true);
        btnDelItem.setFocusable(true);
        btnUpItem.setFocusable(true);
        
        // edt DADOS
        edtCprCodigo.setFocusable(true);
        edtCprValor.setEditable(true);
        
	edtUsuCodigo.setEditable(true);
	edtUsuCodigo.setFocusable(true);
        
	edtForCodigo.setEditable(true);
	edtForCodigo.setFocusable(true);
        
	edtCprEmissao.setEditable(true);
	edtCprEmissao.setFocusable(true);
        
	edtCprDtEntrada.setEditable(true);
	edtCprDtEntrada.setFocusable(true);
        
	edtCprObs.setEditable(true);
	edtCprObs.setFocusable(true);
        
        edtCprDesconto.setEditable(true);
        edtCprDesconto.setFocusable(true);
            
        // edt ITENS
        edtProCod.setEditable(true);
        edtProCod.setFocusable(true);
        
	edtCppQtde.setEditable(true);
	edtCppQtde.setFocusable(true);
        
	edtCppDesconto.setEditable(true); 
	edtCppDesconto.setFocusable(true); 
    }
    
    private void alterar_gravar(){
        System.out.println(" [CompraView] void alterar_gravar() iniciado...");
        System.out.println(" [CompraView] Operacoa atual = " + getOperacao());
        
        try {
            int cpr_cod = parseInt(edtCprCodigo.getText().trim());
            int usu_cod = parseInt(edtUsuCodigo.getText().trim());
            int for_cod = parseInt(edtForCodigo.getText().trim());
            LocalDate emissao = (LocalDate.parse(edtCprEmissao.getText()));
            double valor_v = (parseDouble(edtCprValor.getText()));
            double desc_v = (parseDouble(edtCprDesconto.getText()));
            double total_v = (parseDouble(edtCprTotal.getText()));
            LocalDate dtentrada = (LocalDate.parse(edtCprDtEntrada.getText()));
            String obs_v = (edtCprObs.getText());

            ArrayList<CompraProdutoModel> itens = (itensModel.getLinhas());
            
            // validações mínimas 
            if (cpr_cod <= 0) { JOptionPane.showMessageDialog(this,"Informe o compra."); return; }
            if (usu_cod <= 0) { JOptionPane.showMessageDialog(this,"Informe o usuário."); return; }
            if (for_cod <= 0) { JOptionPane.showMessageDialog(this,"Informe o fornecedor."); return; }

            if(itens == null || itens.isEmpty()) { JOptionPane.showMessageDialog(this, "Preencha os campos de Itens");
                return;
            }
            
            // Gravando a Venda
            new CompraController().alterar(cpr_cod, usu_cod, for_cod, emissao, valor_v, desc_v, total_v, dtentrada, obs_v, itens);

            JOptionPane.showMessageDialog(this, "Compra alterada.");
            
            tabs.setSelectedComponent(tabConsulta);
            consultarCompra();
            
            limparTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
        }
    }
    
    private void gravar() { 
        System.out.println("\n [CompraView] btnGravar clicado");
        System.out.println(" [CompraView] void GRAVAR iniciado com Operacao = " + getOperacao());

        if (operacao.isEmpty() || operacao.equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione uma Operação! exempo: NOVO");
        }
        
        if (operacao.equals("nova_compra")) {
            incluir_compra();
        }
        
        if (operacao.equals("alterar_gravar")) {
            alterar_gravar();
        }
        
        
    }    

    private void excluirSelecionada() {
        System.out.println("\n [CompraView] void excluir() iniciado");

        try {
            int cod = parseInt(edtCprCodigo.getText());
            if (cod <= 0) { JOptionPane.showMessageDialog(this, "Informe o Código da Compra."); return; }
            CompraModel v = new CompraModel();
            v.setCpr_codigo(cod);
            new CompraController().excluir(v);
           
            JOptionPane.showMessageDialog(this, "Venda excluída.");
            
            tabs.setSelectedComponent(tabConsulta);
            consultarCompra();
           
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Falha ao excluir: " + ex.getMessage());
        }
    }


  
    

  
    
    // ==== helpers ====
    private int parseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim().replace(",", "."));
        } catch (Exception e) {
            return 0.0;
        }
    }

    private String fmt(double d) {
        return String.format(java.util.Locale.US, "%.2f", d);
    }
    
    
    
    /**
     * Getters
     */
    private void setOperacao(String op) {
        this.operacao = op == null ? "" : op;
    }
    
    /**
     * Setters
     */
    private String getOperacao(){
        return operacao;
    }

}