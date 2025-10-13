package view;

import controller.FormapagtoController;
import controller.VendaController;
import controller.VendaProdutoController;
import controller.VendapagtoController;
        
import model.VendaModel;
import model.VendaProdutoModel;
import model.VendapagtoModel;

import util.UtilsUI;


import javax.swing.*;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Tela de Cadastro de Vendas (MVC+DAO), seguindo seu TXT:
 * - Dados: mantém visual atual
 * - Itens: sem desconto por item; add/remover; subtotal = qtde*preço
 * - Pagamentos: até 2; combo com formas ativas; regra de rateio
 * - Consulta: filtros (id1..id2, valor>=, valor<=) em venda_produto
 */

public class VendaView extends JPanel {
    
    private JLabel lblTitulo;
    
    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;

    // Abas / painéis
    private JPanel paneCabecario;
    private JPanel paneCentro;
    private JTabbedPane tabs;

    // ====== ABA DADOS ======
    private JPanel tabDados;
    private JLabel lblVdaCodigo, lblUsuCodigo, lblCliCodigo, lblData, lblObs;
    private JTextField edtVdaCodigo, edtUsuCodigo, edtCliCodigo, edtData;
    private JTextArea edtObs;
    private JLabel lblValor, lblDesc, lblTotal;
    private JTextField edtValor, edtDesc, edtTotal;

    // ====== ABA ITENS ======
    private JPanel tabItens, paneItemEditor, paneItemTabela;
    private JLabel lblProCod, lblProNome, lblUn, lblQtde, lblPreco, lblSubTotal;
    private JTextField edtProCod, edtProNome, edtUn, edtQtde, edtPreco, edtSubTotal;
    private JButton btnAddItem, btnDelItem;
    private JTable tabItensGrid;
    private VendaProdutoTableModel itensModel;

    // ====== ABA PAGAMENTOS ======
    private JPanel tabPgtos, panePgEditor, panePgTabela;
    private JLabel lblFpgNome, lblPgValor;
    private JComboBox<String> cbFpgNome;
    private JTextField edtPgValor;
    private JButton btnAddPg, btnUpdPg, btnDelPg;
    private JTable tabPgtosGrid;
    private VendapagtoTableModel pgtosModel;

    // ====== ABA CONSULTA ======
    private JPanel tabConsulta, paneConsultaDados, paneConsultaTabela;
    private JLabel lblId1, lblATxt, lblId2, lblValorGe, lblValorLe;
    private JTextField edtId1, edtId2, edtValorGe, edtValorLe;
    private JButton btnConsultar, btnLimpar;
    private JTable tabelaConsulta;
    private JScrollPane scrollConsulta;
    private VendaTableModel consultaModel;

    // Estado (listas em memória)
    private String operacao = "";
    private final ArrayList<VendaProdutoModel> listaItens = new ArrayList<>();
    private final ArrayList<VendapagtoModel> listaPgtos = new ArrayList<>();
    private final ArrayList<VendaModel> listaVendas = new ArrayList<>();

    public VendaView() {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1500, 850));

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();
        carregarFormasPagamento();
        limparTudo();
    }

    private void instanciar() {
        // Cabeçalho
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo  = new JButton("Próximo");
        btnUltimo   = new JButton("Último");
        btnNovo     = new JButton("Novo");
        btnAlterar  = new JButton("Alterar");
        btnExcluir  = new JButton("Excluir");
        btnGravar   = new JButton("Gravar");

        paneCabecario = new JPanel(null);
        paneCentro    = new JPanel(null);
        paneCentro.setBackground(new Color(245,250,255));
        tabs = new JTabbedPane();
        
            // ===== TÍTULO =====
        lblTitulo = new JLabel("Vendas", SwingConstants.CENTER);   // << ADICIONE
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));       // idem
        lblTitulo.setForeground(new Color(30,30,120));             // idem


        // ===== Dados =====
        tabDados = new JPanel(null);
        lblVdaCodigo = new JLabel("Código:");
        edtVdaCodigo = new JTextField();
        edtVdaCodigo = new JTextField("0");
        edtVdaCodigo.setEditable(false);   // não permite digitar
        edtVdaCodigo.setFocusable(false);  // nem foco

        lblUsuCodigo = new JLabel("Usuário:");
        edtUsuCodigo = new JTextField();
        lblCliCodigo = new JLabel("Cliente:");
        edtCliCodigo = new JTextField();
        lblData = new JLabel("Data (yyyy-MM-dd):");
        edtData = new JTextField(LocalDate.now().toString());
        lblObs = new JLabel("Obs.:");
        edtObs = new JTextArea(); edtObs.setLineWrap(true); edtObs.setWrapStyleWord(true);

        lblValor = new JLabel("Valor:");
        edtValor = new JTextField(); edtValor.setEditable(false);
        lblDesc = new JLabel("Desconto:");
        edtDesc = new JTextField("0"); // desconto total da venda (editável)
        lblTotal = new JLabel("Total:");
        edtTotal = new JTextField(); edtTotal.setEditable(false);

        // ===== Itens =====
        tabItens = new JPanel(null);
        paneItemEditor = new JPanel(null);
        paneItemTabela = new JPanel(null);

        lblProCod = new JLabel("Prod Cód:");
        edtProCod = new JTextField();
        lblProNome = new JLabel("Nome:");
        edtProNome = new JTextField(); edtProNome.setEditable(false);
        lblUn = new JLabel("Und:");
        edtUn = new JTextField(); edtUn.setEditable(false);
        lblQtde = new JLabel("Qtde:");
        edtQtde = new JTextField();
        lblPreco = new JLabel("Preço:");
        edtPreco = new JTextField(); edtPreco.setEditable(false);
        lblSubTotal = new JLabel("Subtotal:");
        edtSubTotal = new JTextField(); edtSubTotal.setEditable(false);

        btnAddItem = new JButton("Adicionar");
        btnDelItem = new JButton("Remover");

        itensModel = new VendaProdutoTableModel(listaItens);
        tabItensGrid = new JTable(itensModel);
        tabItensGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ===== Pagamentos =====
        tabPgtos = new JPanel(null);
        panePgEditor = new JPanel(null);
        panePgTabela = new JPanel(null);

        lblFpgNome = new JLabel("Forma:");
        cbFpgNome = new JComboBox<>();
        lblPgValor = new JLabel("Valor:");
        edtPgValor = new JTextField();

        btnAddPg = new JButton("Adicionar");
        btnUpdPg = new JButton("Atualizar");
        btnDelPg = new JButton("Remover");

        pgtosModel = new VendapagtoTableModel(listaPgtos);
        tabPgtosGrid = new JTable(pgtosModel);
        tabPgtosGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ===== Consulta =====
        tabConsulta = new JPanel(null);
        paneConsultaDados = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        lblId1 = new JLabel("Venda cod");
        edtId1 = new JTextField();
        lblATxt = new JLabel("à");
        lblId2 = new JLabel("Venda cod");
        edtId2 = new JTextField();
        lblValorGe = new JLabel("valor >=");
        edtValorGe = new JTextField();
        lblValorLe = new JLabel("valor <=");
        edtValorLe = new JTextField();

        btnConsultar = new JButton("Consulta");
        btnLimpar = new JButton("Limpa");

        consultaModel = new VendaTableModel (listaVendas);
        tabelaConsulta = new JTable(consultaModel);
        scrollConsulta = new JScrollPane(tabelaConsulta);
    }

    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro); paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);  paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);     paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);  paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        add(paneCentro);
         paneCentro.add(lblTitulo);   // << ADICIONE
        paneCentro.add(tabs);

        

        // Aba Consulta
        tabConsulta.add(paneConsultaDados); tabConsulta.add(paneConsultaTabela);
        paneConsultaDados.add(lblId1); paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblATxt); paneConsultaDados.add(lblId2); paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblValorGe); paneConsultaDados.add(edtValorGe);
        paneConsultaDados.add(lblValorLe); paneConsultaDados.add(edtValorLe);
        paneConsultaDados.add(btnConsultar); paneConsultaDados.add(btnLimpar);
        paneConsultaTabela.add(scrollConsulta);
        tabs.addTab("Consulta", tabConsulta);
        
        // Aba Dados
        tabDados.add(lblVdaCodigo); tabDados.add(edtVdaCodigo);
        tabDados.add(lblUsuCodigo); tabDados.add(edtUsuCodigo);
        tabDados.add(lblCliCodigo); tabDados.add(edtCliCodigo);
        tabDados.add(lblData);      tabDados.add(edtData);
        tabDados.add(lblObs);       tabDados.add(edtObs);
        tabDados.add(lblValor);     tabDados.add(edtValor);
        tabDados.add(lblDesc);      tabDados.add(edtDesc);
        tabDados.add(lblTotal);     tabDados.add(edtTotal);
        tabs.addTab("Dados", tabDados);

        // Aba Itens
        tabItens.add(paneItemEditor); tabItens.add(paneItemTabela);
        paneItemEditor.add(lblProCod); paneItemEditor.add(edtProCod);
        paneItemEditor.add(lblProNome); paneItemEditor.add(edtProNome);
        paneItemEditor.add(lblUn); paneItemEditor.add(edtUn);
        paneItemEditor.add(lblQtde); paneItemEditor.add(edtQtde);
        paneItemEditor.add(lblPreco); paneItemEditor.add(edtPreco);
        paneItemEditor.add(lblSubTotal); paneItemEditor.add(edtSubTotal);
        paneItemEditor.add(btnAddItem); paneItemEditor.add(btnDelItem);
        paneItemTabela.add(new JScrollPane(tabItensGrid));
        tabs.addTab("Itens (Produtos)", tabItens);

        // Aba Pagamentos
        tabPgtos.add(panePgEditor); tabPgtos.add(panePgTabela);
        panePgEditor.add(lblFpgNome); panePgEditor.add(cbFpgNome);
        panePgEditor.add(lblPgValor); panePgEditor.add(edtPgValor);
        panePgEditor.add(btnAddPg); panePgEditor.add(btnUpdPg); panePgEditor.add(btnDelPg);
        panePgTabela.add(new JScrollPane(tabPgtosGrid));
        tabs.addTab("Pagamentos", tabPgtos);
    }
    
    private void posicionar() {
        paneCabecario.setBounds(10, 10, 1470, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);

        btnPrimeiro.setBounds(  0, 7, 100, 25);
        btnAnterior.setBounds(105, 7, 100, 25);
        btnProximo .setBounds(210, 7, 100, 25);
        btnUltimo  .setBounds(315, 7, 100, 25);
        btnNovo    .setBounds(520, 7, 100, 25);
        btnAlterar .setBounds(625, 7, 100, 25);
        btnExcluir .setBounds(730, 7, 100, 25);
        btnGravar  .setBounds(1320, 7, 120, 25);

        paneCentro.setBounds(10, 60, 1470, 770);
        lblTitulo.setBounds(0, 0, 1450, 30);
        tabs.setBounds(10, 10, 1450, 750);

        // ===== Dados =====
        lblVdaCodigo.setBounds(10, 15, 60, 25);  edtVdaCodigo.setBounds(75, 15, 100, 25);
        lblUsuCodigo.setBounds(190, 15, 60, 25); edtUsuCodigo.setBounds(255, 15, 80, 25);
        lblCliCodigo.setBounds(345, 15, 60, 25); edtCliCodigo.setBounds(410, 15, 80, 25);
        lblData.setBounds(500, 15, 140, 25);     edtData.setBounds(640, 15, 120, 25);

        lblObs.setBounds(10, 55, 40, 25);        edtObs.setBounds(55, 55, 1050, 120);

        lblValor.setBounds(10, 185, 60, 25);     edtValor.setBounds(70, 185, 120, 25);
        lblDesc.setBounds(200, 185, 70, 25);     edtDesc.setBounds(270, 185, 120, 25);
        lblTotal.setBounds(400, 185, 60, 25);    edtTotal.setBounds(460, 185, 120, 25);

        // ===== Itens =====
        tabItens.setBounds(0,0,1450,750);
        paneItemEditor.setBounds(10, 10, 1425, 120);
        paneItemTabela.setBounds(10, 140, 1425, 560);

        lblProCod.setBounds(10, 10, 80, 25);     edtProCod.setBounds(90, 10, 80, 25);
        lblProNome.setBounds(180, 10, 45, 25);   edtProNome.setBounds(230, 10, 320, 25);
        lblUn.setBounds(560, 10, 35, 25);        edtUn.setBounds(600, 10, 50, 25);

        lblQtde.setBounds(10, 45, 50, 25);       edtQtde.setBounds(60, 45, 80, 25);
        lblPreco.setBounds(150, 45, 45, 25);     edtPreco.setBounds(200, 45, 100, 25);
        lblSubTotal.setBounds(310, 45, 60, 25);  edtSubTotal.setBounds(375, 45, 120, 25);

        btnAddItem.setBounds(520, 45, 110, 25);
        btnDelItem.setBounds(640, 45, 110, 25);

        JScrollPane spItens = (JScrollPane) paneItemTabela.getComponent(0);
        spItens.setBounds(0, 0, 1425, 560);

        // ===== Pagamentos =====
        tabPgtos.setBounds(0,0,1450,750);
        panePgEditor.setBounds(10, 10, 1425, 90);
        panePgTabela.setBounds(10, 110, 1425, 590);

        lblFpgNome.setBounds(10, 10, 50, 25);    cbFpgNome.setBounds(65, 10, 300, 25);
        lblPgValor.setBounds(375, 10, 40, 25);   edtPgValor.setBounds(420, 10, 120, 25);

        btnAddPg.setBounds(560, 10, 110, 25);
        btnUpdPg.setBounds(680, 10, 110, 25);
        btnDelPg.setBounds(800, 10, 110, 25);

        JScrollPane spPg = (JScrollPane) panePgTabela.getComponent(0);
        spPg.setBounds(0, 0, 1425, 590);

        // ===== Consulta =====
        tabConsulta.setBounds(0,0,1450,750);
        paneConsultaDados.setBounds(10, 10, 1425, 60);
        paneConsultaTabela.setBounds(10, 80, 1425, 650);

        lblId1.setBounds(10, 10, 30, 25);       edtId1.setBounds(45, 10, 80, 25);
        lblATxt.setBounds(130, 10, 10, 25);
        lblId2.setBounds(150, 10, 30, 25);      edtId2.setBounds(185, 10, 80, 25);
        lblValorGe.setBounds(275, 10, 70, 25);  edtValorGe.setBounds(345, 10, 100, 25);
        lblValorLe.setBounds(455, 10, 70, 25);  edtValorLe.setBounds(525, 10, 100, 25);

        btnConsultar.setBounds(640, 10, 110, 25);
        btnLimpar.setBounds(760, 10, 110, 25);

        scrollConsulta.setBounds(0, 0, 1425, 650);
    }

    private void configurarAcoes() {
        // Produto: preencher ao sair do código
        edtProCod.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { preencherProduto(); }
        });
        // Subtotal ao sair da qtde
        edtQtde.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { recalcSubtotal(); }
        });

        // Itens
        btnAddItem.addActionListener(e -> adicionarItem());
        btnDelItem.addActionListener(e -> removerItemSelecionado());

        // Pagamentos
        btnAddPg.addActionListener(e -> adicionarPagamento());
        btnUpdPg.addActionListener(e -> atualizarPagamentoSelecionado());
        btnDelPg.addActionListener(e -> removerPagamentoSelecionado());

        // Consulta
        btnConsultar.addActionListener(e -> consultarVendaProduto());
        btnLimpar.addActionListener(e -> {
            edtId1.setText(""); edtId2.setText("");
            edtValorGe.setText(""); edtValorLe.setText("");
            limparTabelaConsulta();
        });

        // Cabeçalho
        btnNovo.addActionListener(e -> novo());
        btnAlterar.addActionListener(e -> setOperacao("alterar"));
        btnGravar.addActionListener(e -> gravar());
        btnExcluir.addActionListener(e -> excluirSelecionada());
        
        btnPrimeiro.addActionListener(e -> selecionarIndice(0));
        btnAnterior.addActionListener(e -> navegar(-1));
        btnProximo.addActionListener(e -> navegar(+1));
        btnUltimo.addActionListener(e -> selecionarIndice(listaVendas.size()-1));
        
        
        // ==== Seleção nas tabelas ====

        // Itens: ao selecionar, carrega no editor de itens
        tabItensGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int viewSel = tabItensGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabItensGrid.convertRowIndexToModel(viewSel);
                VendaProdutoModel it = itensModel.getItem(modelSel);
                mostrarItem(it);
            }
        });

        // Pagamentos: ao selecionar, carrega no editor de pagamentos
        tabPgtosGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int viewSel = tabPgtosGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabPgtosGrid.convertRowIndexToModel(viewSel);
                VendapagtoModel pg = pgtosModel.get(modelSel);
                mostrarPagamento(pg);
            }
        });

        // Consulta: ao selecionar linha, joga para filtros/editores
        tabelaConsulta.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }            
            if (!e.getValueIsAdjusting()) {
                selecionarLinhaConsulta();
            }
        });

    }

    
    
    private void carregarFormasPagamento() {
        try {
            ArrayList<String> nomes = new FormapagtoController().listarNomesAtivos();
            cbFpgNome.removeAllItems();
            for (String n : nomes) {
                cbFpgNome.addItem(n);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar formas de pagamento: " + ex.getMessage());
        }
    }


    // ===== Itens =====

    private void preencherProduto() {
        try {
            int cod = parseInt(edtProCod.getText());
            if (cod <= 0) { limparCamposProduto(); return; }
            VendaProdutoModel p = new VendaProdutoController().buscarPrimeiroPorVenda(cod);

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado/ativo.");
                limparCamposProduto();
                return;
            }
            edtProNome.setText(p.getProduto_VendaProduto().getPRO_NOME());
            edtUn.setText(p.getProduto_VendaProduto().getPRO_UNIDADE());
            edtPreco.setText(fmt(p.getProduto_VendaProduto().getPRO_PRECO())); // ftm -> converte double em string
            recalcSubtotal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao buscar produto: " + ex.getMessage());
        }
    }

    private void recalcSubtotal() {
        double qt = parseDouble(edtQtde.getText());
        double pr = parseDouble(edtPreco.getText());
        edtSubTotal.setText(fmt(qt * pr));
    }

    private void adicionarItem() {
        int pro = parseInt(edtProCod.getText());
        if (pro <= 0) { JOptionPane.showMessageDialog(this, "Informe o código do produto."); return; }
        double qt = parseDouble(edtQtde.getText());
        double pr = parseDouble(edtPreco.getText());
        if (qt <= 0 || pr <= 0) { JOptionPane.showMessageDialog(this, "Qtde e Preço precisam ser > 0."); return; }

        VendaProdutoModel it = new VendaProdutoModel();
        it.getProduto_VendaProduto().setPRO_CODIGO(pro);
        it.getProduto_VendaProduto().setPRO_NOME(edtProNome.getText().trim());
        it.getProduto_VendaProduto().setPRO_UNIDADE(edtUn.getText().trim());
        it.setVep_qtde(qt);
        it.setVep_preco(pr);
        it.setVep_desconto(0.0);
        double total = UtilsUI.recalc(it.getVep_qtde(), it.getVep_preco(), it.getVep_desconto());
        it.setVep_total(total);

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

    private void limparCamposProduto() {
        edtProCod.setText("");
        edtProNome.setText("");
        edtUn.setText("");
        edtQtde.setText("");
        edtPreco.setText("");
        edtSubTotal.setText("");
    }

    private void recomputarTotais() {
        double soma = 0.0;
        for (VendaProdutoModel it : itensModel.getLinhas()) soma += it.getVep_total();
        edtValor.setText(fmt(soma));
        double desc = parseDouble(edtDesc.getText());
        if (desc < 0) desc = 0;
        if (desc > soma) desc = soma;
        edtDesc.setText(fmt(desc));
        edtTotal.setText(fmt(soma - desc));

        // se não há pagamentos, sugere o total no campo de valor
        if (pgtosModel.getLinhas().isEmpty())
            edtPgValor.setText(edtTotal.getText());
        else if (pgtosModel.getRowCount() == 1) {
            // mantém 1ª forma = total atual
            VendapagtoModel pg1 = pgtosModel.get(0);
            pg1.setVdp_valor(parseDouble(edtTotal.getText()));
            pgtosModel.set(0, pg1);
        } else if (pgtosModel.getRowCount() == 2) {
            // reajusta a 1ª para (total - 2ª)
            VendapagtoModel pg2 = pgtosModel.get(1);
            double total = parseDouble(edtTotal.getText());
            double val2 = pg2.getVdp_valor();
            if (val2 <= 0 || val2 >= total) {
                val2 = Math.max(0, Math.min(total, val2));
                pg2.setVdp_valor(val2);
                pgtosModel.set(1, pg2);
            }
            VendapagtoModel pg1 = pgtosModel.get(0);
            pg1.setVdp_valor(total - val2);
            pgtosModel.set(0, pg1);
        }
    }

    // ===== Pagamentos =====

    private void adicionarPagamento() {
        try {
            if (pgtosModel.getRowCount() >= 2) {
                JOptionPane.showMessageDialog(this, "MAX de formas de pagamentos inseridas = 2.");
                return;
            }
            String nome = (String) cbFpgNome.getSelectedItem();
            if (nome == null || nome.isBlank()) { JOptionPane.showMessageDialog(this, "Selecione a forma."); return; }

            int fpgCodigo = new FormapagtoController().obterCodigoPorNome(nome);
            if (fpgCodigo <= 0) { JOptionPane.showMessageDialog(this, "Forma inválida."); return; }

            double total = parseDouble(edtTotal.getText());
            if (total <= 0) { JOptionPane.showMessageDialog(this, "Total da venda está 0."); return; }

            if (pgtosModel.getRowCount() == 0) {
                double valor1 = total;
                if (!edtPgValor.getText().isBlank()) {
                    valor1 = parseDouble(edtPgValor.getText());
                    if (valor1 <= 0) valor1 = total;
                    if (valor1 > total) valor1 = total;
                }
                VendapagtoModel  pg = new VendapagtoModel();
                pg.getFormapagto_Vendapagto().setFPG_CODIGO(fpgCodigo);
                pg.getFormapagto_Vendapagto().setFPG_NOME(nome);
                pg.setVdp_valor(valor1);
                pgtosModel.add(pg);
            } else {
                double valor2 = parseDouble(edtPgValor.getText());
                if (valor2 <= 0 || valor2 >= total) {
                    JOptionPane.showMessageDialog(this, "Para 2ª forma informe valor > 0 e < total.");
                    return;
                }
                VendapagtoModel  pg2 = new VendapagtoModel();
                pg2.getFormapagto_Vendapagto().setFPG_CODIGO(fpgCodigo);
                pg2.getFormapagto_Vendapagto().setFPG_NOME(nome);
                pg2.setVdp_valor(valor2);
                pgtosModel.add(pg2);

                VendapagtoModel pg1 = pgtosModel.get(0);
                pg1.setVdp_valor(total - valor2);
                pgtosModel.set(0, pg1);
            }

            edtPgValor.setText("");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Erro ao adicionar pagamento: " + ex.getMessage());
        }
    }

    /** Atualiza somente a 2ª forma (valor/forma) e rateia o restante na 1ª. */
    private void atualizarPagamentoSelecionado() {
        int row = tabPgtosGrid.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um pagamento."); return; }
        int modelRow = tabPgtosGrid.convertRowIndexToModel(row);

        if (pgtosModel.getRowCount() == 1 || modelRow == 0) {
            JOptionPane.showMessageDialog(this, "Atualização permitida apenas na 2ª forma (quando existir).");
            return;
        }

        try {
            String nome = (String) cbFpgNome.getSelectedItem();
            int fpgCod = new FormapagtoController().obterCodigoPorNome(nome);
            if (fpgCod <= 0) { JOptionPane.showMessageDialog(this, "Forma inválida."); return; }

            double total = parseDouble(edtTotal.getText());
            double val2 = parseDouble(edtPgValor.getText());
            if (val2 <= 0 || val2 >= total) {
                JOptionPane.showMessageDialog(this, "Valor da 2ª forma deve ser > 0 e < total.");
                return;
            }

            VendapagtoModel sel = pgtosModel.get(modelRow);
            sel.getFormapagto_Vendapagto().setFPG_CODIGO(fpgCod);
            sel.getFormapagto_Vendapagto().setFPG_NOME(nome);
            sel.setVdp_valor(val2);
            pgtosModel.set(modelRow, sel);

            int other = modelRow == 0 ? 1 : 0;
            VendapagtoModel o = pgtosModel.get(other);
            o.setVdp_valor(total - val2);
            pgtosModel.set(other, o);

            edtPgValor.setText("");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Erro ao atualizar pagamento: " + ex.getMessage());
        }
    }

    private void removerPagamentoSelecionado() {
        int row = tabPgtosGrid.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um pagamento."); return; }
        int modelRow = tabPgtosGrid.convertRowIndexToModel(row);
        pgtosModel.remove(modelRow);

        if (pgtosModel.getRowCount() == 1){
            VendapagtoModel pg1 = pgtosModel.get(0);
            pg1.setVdp_valor(parseDouble(edtTotal.getText()));
            pgtosModel.set(0, pg1);
        }
    }

    // ===== Consulta =====

    private String filtroConsultaVendaProduto() {
        StringBuilder cond = new StringBuilder();
        String id1 = edtId1.getText().trim();
        String id2 = edtId2.getText().trim();
        String ge  = edtValorGe.getText().trim().replace(",", ".");
        String le  = edtValorLe.getText().trim().replace(",", ".");

        if (!id1.isEmpty()) cond.append("(vda_codigo >= ").append(id1).append(")");
        
        if (!id2.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(vda_codigo <= ").append(id2).append(")");
        }
        if (!ge.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(vep_total >= ").append(ge).append(")");
        }
        if (!le.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(vep_total <= ").append(le).append(")");
        }
        return cond.toString();
    }

    private void consultarVendaProduto() {
        try {
            // Limpa a tabela
            limparTabelaConsulta();

            // Busca as vendas do banco (retorna lista de VendaModel)
            ArrayList<VendaModel> lista = new VendaController().consultar(filtroConsultaVendaProduto());

            if (lista == null) lista = new ArrayList<>();

            // Cria um novo model com os dados
            consultaModel = new VendaTableModel(lista);

            // Atualiza o JTable com o novo modelo
            tabelaConsulta.setModel(consultaModel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na consulta: " + ex.getMessage());
        }
    }


    
    private void limparTabelaConsulta() {
        consultaModel = new VendaTableModel(new ArrayList<>());
        tabelaConsulta.setModel(consultaModel);
    }

    private void selecionarLinhaConsulta() {
        int viewSel = tabelaConsulta.getSelectedRow();
        
        if (viewSel < 0) return;
        
        int modelSel = tabelaConsulta.convertRowIndexToModel(viewSel);
        int vda = Integer.parseInt(String.valueOf(consultaModel.getValueAt(modelSel, 0))); // vda_codigo

        try {
            System.out.println("\n [selecionarLinhaConsulta] foi chamado em VendaView");
            VendaModel venda = new VendaController().buscarPorCodigo(vda);
            ArrayList<VendaProdutoModel> vendaProduto = new VendaProdutoController().buscarPorCodigoVenda(vda);
            ArrayList<VendapagtoModel> vendaFormapagto = new VendapagtoController().buscarPorCodigoVenda(vda);
                    
            if (venda == null) {
                JOptionPane.showMessageDialog(this, "Venda não encontrada.");
                return;
            }
            
            while (itensModel.getRowCount() > 0) {
                itensModel.removeItem(0);
            }
            while (pgtosModel.getRowCount() > 0) {
                pgtosModel.remove(0);
            }

            mostrarDados(venda);

            for (VendaProdutoModel it : vendaProduto) {
                itensModel.addItem(it);
            }
            for (VendapagtoModel pg : vendaFormapagto) {
                pgtosModel.add(pg);
            }
            
            recomputarTotais();

            // ir para a aba "Dados" 
            tabs.setSelectedComponent(tabDados);

            /** Marca que estamos olhando um registro existente
            *   só troca para "alterar" quando clicar no botão Alterar
            */
            setOperacao("");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar venda: " + ex.getMessage());
        }
    }
    

    // ===== Cabeçalho / Gravação =====

    private void novo() {
        setOperacao("incluir");
        limparTudo();
        edtVdaCodigo.setText("0");                 // mostra 0
        edtData.setText(LocalDate.now().toString());
        tabs.setSelectedComponent(tabDados);
    }


    private void setOperacao(String op){
        this.operacao = op == null ? "" : op;
    }

    
    private void gravar() {
    try {
        if (itensModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Inclua ao menos 1 item.");
            limparTudo();
            return;
        }
        VendaModel v = new VendaModel();

        // inclusão: força 0 para deixar o DB gerar; alteração: exige > 0
        String op = operacao.isEmpty() ? "incluir" : operacao;
        int codTela = parseInt(edtVdaCodigo.getText());
        if ("incluir".equals(op)) {
            v.setVda_codigo(0);
        } else { // alterar
            if (codTela <= 0) {
                JOptionPane.showMessageDialog(this, "Código inválido para alteração.");
                return;
            }
            v.setVda_codigo(codTela);
        }

        // restante dos campos
        v.getUsu_venda().setUSU_CODIGO(parseInt(edtUsuCodigo.getText()));
        v.getCli_venda().setCLI_CODIGO(parseInt(edtCliCodigo.getText()));
        v.setVda_data(LocalDate.parse(edtData.getText()));
        v.setVda_valor(parseDouble(edtValor.getText()));
        v.setVda_desconto(parseDouble(edtDesc.getText()));
        v.setVda_total(parseDouble(edtTotal.getText()));
        v.setVda_obs(edtObs.getText());

        // validações mínimas
        if (v.getUsu_venda().getUSU_CODIGO() <= 0) { JOptionPane.showMessageDialog(this,"Informe o usuário."); return; }
        if (v.getCli_venda().getCLI_CODIGO() <= 0) { JOptionPane.showMessageDialog(this,"Informe o cliente."); return; }

        // VDA_CODIGO nos itens/pgtos (preenche na gravação se incluir)
        ArrayList<VendaProdutoModel> itens = new ArrayList<>(itensModel.getLinhas());
        ArrayList<VendapagtoModel> pgtos = new ArrayList<>(pgtosModel.getLinhas());

        int vda_cod = v.getVda_codigo();
        
        new VendaController().incluir(v);
        new VendaProdutoController().inserirItens(vda_cod, itens);
        
        new VendapagtoController().inserirPgtos(vda_cod, pgtos);


        // DB gerou o código: o DAO preencheu em v.setVDA_CODIGO(...)
        edtVdaCodigo.setText(String.valueOf(v.getVda_codigo()));
        JOptionPane.showMessageDialog(this, "Venda gravada.");
        setOperacao("");
        limparTudo();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
    }
}

    private void excluirSelecionada() {
        try {
            int cod = parseInt(edtVdaCodigo.getText());
            if (cod <= 0) { JOptionPane.showMessageDialog(this, "Informe o Código da venda."); return; }
            VendaModel v = new VendaModel();
            v.setVda_codigo(cod);
            new VendaController().excluir(v);
            JOptionPane.showMessageDialog(this, "Venda excluída.");
            novo();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Falha ao excluir: " + ex.getMessage());
        }
    }

    private void selecionarIndice(int idx){
        if (idx < 0 || idx >= listaVendas.size()) return;
        VendaModel v = listaVendas.get(idx);
        mostrarDados(v);
    }

    private void navegar(int delta){
        if (listaVendas.isEmpty()) return;
        int atual = -1;
        int cod = parseInt(edtVdaCodigo.getText());
        for (int i=0;i<listaVendas.size();i++){
            if (listaVendas.get(i).getVda_codigo()== cod) { atual = i; break; }
        }
        int prox = (atual < 0 ? 0 : atual + delta);
        if (prox < 0) prox = 0;
        if (prox >= listaVendas.size()) prox = listaVendas.size()-1;
        selecionarIndice(prox);
    }


    // ===== Helpers numéricos =====
    private int parseInt(String s){
        try { return Integer.parseInt(s.trim()); } catch (Exception e){ return 0; }
    }
    
    private double parseDouble(String s){
        try { return Double.parseDouble(s.trim().replace(",", ".")); } catch (Exception e){ return 0.0; }
    }
    private String fmt(double d){
        return String.format(java.util.Locale.US, "%.2f", d);
    }

    
    
    
    // ==== Seleção e editores ====

    // Preenche os campos do editor de DADOS;
    private void mostrarDados(VendaModel v){
        edtVdaCodigo.setText(String.valueOf(v.getVda_codigo()));
        edtUsuCodigo.setText(String.valueOf(v.getUsu_venda().getUSU_CODIGO()));
        edtCliCodigo.setText(String.valueOf(v.getCli_venda().getCLI_CODIGO()));
        edtData.setText(v.getVda_data()== null ? "" : v.getVda_data().toString());
        edtObs.setText(v.getVda_obs() == null ? "" : v.getVda_obs());
        edtValor.setText(fmt(v.getVda_valor()));
        edtDesc.setText(fmt(v.getVda_desconto()));
        edtTotal.setText(fmt(v.getVda_total()));
    }

    // Preenche os campos do editor de ITENS com base no modelo
    private void mostrarItem(VendaProdutoModel it){
        if (it == null) return;
        edtProCod.setText(String.valueOf(it.getProduto_VendaProduto().getPRO_CODIGO()));
        edtProNome.setText(it.getProduto_VendaProduto().getPRO_NOME());
        edtUn.setText(it.getProduto_VendaProduto().getPRO_UNIDADE());
        edtQtde.setText(fmt(it.getVep_qtde()));
        edtPreco.setText(fmt(it.getVep_preco()));
        edtSubTotal.setText(fmt(it.getVep_total()));
    }

    // Preenche os campos do editor de PAGAMENTOS
    private void mostrarPagamento(VendapagtoModel pg){
        if (pg == null) return;
        // Seleciona o nome no combo (se existir na lista)
        cbFpgNome.setSelectedItem(pg.getFormapagto_Vendapagto().getFPG_NOME());
        edtPgValor.setText(fmt(pg.getVdp_valor()));
    }
    
    private void limparTudo(){
        edtVdaCodigo.setText("0");
        edtUsuCodigo.setText("");
        edtCliCodigo.setText("");
        edtData.setText(LocalDate.now().toString());
        edtObs.setText("");
        edtValor.setText("0");
        edtDesc.setText("0");
        edtTotal.setText("0");
        // limpa listas
        while (itensModel.getRowCount() > 0) itensModel.removeItem(0);
        while (pgtosModel.getRowCount() > 0) pgtosModel.remove(0);
        edtPgValor.setText("");
    }

}
