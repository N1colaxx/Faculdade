package view;

import controller.FormapagtoController;
import controller.ProdutoController;
import controller.VendaController;
import controller.VendaProdutoController;
import controller.VendapagtoController;

import model.VendaModel;
import model.VendaProdutoModel;
import model.VendaPagtoModel;
import model.SessionModel;
import model.UsuarioModel;
import model.ProdutoModel;
import model.FormapagtoModel;
import tableModel.VendaTableModel;
import tableModel.VendaProdutoTableModel;
import tableModel.VendaPagtoTableModel;

import util.UtilsUI;

import javax.swing.*;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class VendaView extends JPanel {

    private JLabel lblTitulo;

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnIncluir, btnAlterar, btnExcluir, btnImprimir, btnGravar;

    // Abas / painéis
    private JPanel paneCabecario;
    private JPanel paneCentro;
    private JTabbedPane tabs;

    // ====== ABA CONSULTA = Venda ======
    private JPanel tabConsulta, paneConsultaDados, paneConsultaTabela;
    private JLabel lblId1, lblATxt, lblId2, lblValorConsulta1, lblValorConsulta2;
    private JTextField edtId1, edtId2, edtValorConsulta1, edtValorConsulta2;
    private JButton btnConsultar, btnLimpar;
    private JTable tabelaConsulta;
    private JScrollPane scrollConsulta;
    private VendaTableModel consultaModel;

    // ====== ABA DADOS = Vendas ======
    private JPanel tabDados;
    private JLabel lblVdaCodigo, lblUsuCodigo, lblCliCodigo, lblData, lblObs;
    private JTextField edtVdaCodigo, edtUsuCodigo, edtCliCodigo, edtData;
    private JTextArea edtObs;
    private JScrollPane spObs;
    private JLabel lblValorVenda, lblDescontoVenda, lblTotalVenda;
    private JTextField edtValorVenda, edtDescontoVenda, edtTotalVenda;
    private UsuarioModel userLogado;

    // ====== ABA ITENS = VendaProduto ======
    private JPanel tabItens, paneItemEditor, paneItemTabela;
    private JLabel lblProCod, lblProNome, lblProUn, lblProQtde, lblProPreco, lblProDesconto, lblProTotal;
    private JTextField edtProCod, edtProNome, edtProUn, edtProQtde, edtProPreco, edtProDesconto, edtProTotal;
    private JButton btnAddItem, btnDelItem, btnUpdItem;
    private JTable tabItensGrid;
    private VendaProdutoTableModel itensModel;
    private JScrollPane spItens;

    // ====== ABA PAGAMENTOS = Vendapagto ======
    private JPanel tabPgtos, panePgEditor, panePgTabela;
    private JLabel lblFpgNome, lblValorPagamento;
    private JComboBox<String> cbFpgNome;
    private JTextField edtValorPagamento;
    private JButton btnAddPg, btnUpdPg, btnDelPg;
    private JTable tabPgtosGrid;
    private VendaPagtoTableModel pgtosModel;
    private JScrollPane spPg;

    // Estado (listas em memória)
    private String operacao = "";
    private final ArrayList<VendaProdutoModel> listaItens = new ArrayList<>();
    private final ArrayList<VendaPagtoModel> listaPgtos = new ArrayList<>();
    private final ArrayList<VendaModel> listaVendas = new ArrayList<>();

    public VendaView() {
        System.out.println(" [VendaView] entrou");

        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();

        limparTudo();
    }

    private void instanciar() {

        // BTN Cabeçalho
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");
        btnUltimo = new JButton("Último");
        btnIncluir = new JButton("Incluir");
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
        lblTitulo = new JLabel("Vendas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // ===== Consulta =====
        tabConsulta = new JPanel(null);
        paneConsultaDados = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        lblId1 = new JLabel("cod V");
        edtId1 = new JTextField();

        lblATxt = new JLabel("até");

        lblId2 = new JLabel("cod V");
        edtId2 = new JTextField();

        lblValorConsulta1 = new JLabel("valor >=");
        edtValorConsulta1 = new JTextField();

        lblValorConsulta2 = new JLabel("valor <=");
        edtValorConsulta2 = new JTextField();

        btnConsultar = new JButton("Consulta");
        btnLimpar = new JButton("Limpa");

        consultaModel = new VendaTableModel(listaVendas);
        tabelaConsulta = new JTable(consultaModel);
        scrollConsulta = new JScrollPane(tabelaConsulta);

        /**
         * Dados
         */
        tabDados = new JPanel(null);

        lblVdaCodigo = new JLabel("Cód Venda:");
        edtVdaCodigo = new JTextField("");
        edtVdaCodigo.setEditable(false);   // não permite digitar
        edtVdaCodigo.setFocusable(false);  // nem foco  

        lblUsuCodigo = new JLabel("Usuário:");
        edtUsuCodigo = new JTextField();
        edtUsuCodigo.setFocusable(false);

        lblCliCodigo = new JLabel("Cliente:");
        edtCliCodigo = new JTextField();
        edtCliCodigo.setFocusable(false);

        lblData = new JLabel("Data (yyyy-MM-dd):");
        edtData = new JTextField(LocalDate.now().toString());
        edtData.setFocusable(false);

        lblObs = new JLabel("Obs.:");
        edtObs = new JTextArea();
        edtObs.setLineWrap(true);
        edtObs.setWrapStyleWord(true);
        edtObs.setFocusable(false);

        spObs = new JScrollPane(edtObs);

        lblValorVenda = new JLabel("Valor:");
        edtValorVenda = new JTextField("0.00");
        edtValorVenda.setEditable(false);

        lblDescontoVenda = new JLabel("Desconto:");
        edtDescontoVenda = new JTextField("0"); // desconto total da venda (editável)
        edtDescontoVenda.setFocusable(false);

        lblTotalVenda = new JLabel("Total:");
        edtTotalVenda = new JTextField("0.00");
        edtTotalVenda.setEditable(false);

        // ===== Itens =====
        tabItens = new JPanel(null);
        paneItemEditor = new JPanel(null);
        paneItemTabela = new JPanel(null);

        lblProCod = new JLabel("Prod Cód:");
        edtProCod = new JTextField();
        edtProCod.setFocusable(false);

        lblProNome = new JLabel("Nome:");
        edtProNome = new JTextField();
        edtProNome.setEditable(false);

        lblProUn = new JLabel("Und:");
        edtProUn = new JTextField();
        edtProUn.setEditable(false);

        lblProQtde = new JLabel("Qtde:");
        edtProQtde = new JTextField();
        edtProQtde.setFocusable(false);

        lblProPreco = new JLabel("Preço:");
        edtProPreco = new JTextField();
        edtProPreco.setEditable(false);

        lblProDesconto = new JLabel("Desconto");
        edtProDesconto = new JTextField("0"); // desconto do produto
        edtProDesconto.setFocusable(false);

        lblProTotal = new JLabel("Subtotal:");
        edtProTotal = new JTextField();
        edtProTotal.setEditable(false);

        btnAddItem = new JButton("Adicionar");
        btnDelItem = new JButton("Remover");
        btnUpdItem = new JButton("Update");

        itensModel = new VendaProdutoTableModel(listaItens);
        tabItensGrid = new JTable(itensModel);
        tabItensGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        spItens = null;

        // ===== Pagamentos =====
        tabPgtos = new JPanel(null);
        panePgEditor = new JPanel(null);
        panePgTabela = new JPanel(null);

        lblFpgNome = new JLabel("Forma:");
        cbFpgNome = new JComboBox<>();

        lblValorPagamento = new JLabel("Valor:");
        edtValorPagamento = new JTextField();
        edtValorPagamento.setFocusable(false);

        btnAddPg = new JButton("Adicionar");
        btnUpdPg = new JButton("Atualizar");
        btnDelPg = new JButton("Remover");

        pgtosModel = new VendaPagtoTableModel(listaPgtos);
        tabPgtosGrid = new JTable(pgtosModel);
        tabPgtosGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        spPg = null;

    }

    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro);
        paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);
        paneCabecario.add(btnUltimo);
        paneCabecario.add(btnIncluir);
        paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);
        paneCabecario.add(btnImprimir);
        paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        add(paneCentro);
        paneCentro.add(lblTitulo);
        paneCentro.add(tabs);

        // Aba Consulta
        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);

        paneConsultaDados.add(lblId1);
        paneConsultaDados.add(edtId1);

        paneConsultaDados.add(lblATxt);

        paneConsultaDados.add(lblId2);
        paneConsultaDados.add(edtId2);

        paneConsultaDados.add(lblValorConsulta1);
        paneConsultaDados.add(edtValorConsulta1);

        paneConsultaDados.add(lblValorConsulta2);
        paneConsultaDados.add(edtValorConsulta2);

        paneConsultaDados.add(btnConsultar);
        paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollConsulta);

        tabs.addTab("Consulta", tabConsulta);

        // Aba Dados
        tabDados.add(lblVdaCodigo);
        tabDados.add(edtVdaCodigo);

        tabDados.add(lblUsuCodigo);
        tabDados.add(edtUsuCodigo);

        tabDados.add(lblCliCodigo);
        tabDados.add(edtCliCodigo);

        tabDados.add(lblData);
        tabDados.add(edtData);

        tabDados.add(lblObs);
        tabDados.add(spObs);

        tabDados.add(lblValorVenda);
        tabDados.add(edtValorVenda);

        tabDados.add(lblDescontoVenda);
        tabDados.add(edtDescontoVenda);

        tabDados.add(lblTotalVenda);
        tabDados.add(edtTotalVenda);

        tabs.addTab("Dados", tabDados);

        // Aba Itens
        tabItens.add(paneItemEditor);
        tabItens.add(paneItemTabela);

        paneItemEditor.add(lblProCod);
        paneItemEditor.add(edtProCod);

        paneItemEditor.add(lblProNome);
        paneItemEditor.add(edtProNome);

        paneItemEditor.add(lblProUn);
        paneItemEditor.add(edtProUn);

        paneItemEditor.add(lblProQtde);
        paneItemEditor.add(edtProQtde);

        paneItemEditor.add(lblProPreco);
        paneItemEditor.add(edtProPreco);

        paneItemEditor.add(lblProTotal);
        paneItemEditor.add(edtProTotal);

        paneItemEditor.add(lblProDesconto);
        paneItemEditor.add(edtProDesconto);

        paneItemEditor.add(btnAddItem);
        paneItemEditor.add(btnDelItem);
        paneItemEditor.add(btnUpdItem);

        JScrollPane spItensLocal = new JScrollPane(tabItensGrid);
        paneItemTabela.add(spItensLocal);

        tabs.addTab("Itens (Produtos)", tabItens);

        // Aba Pagamentos
        tabPgtos.add(panePgEditor);
        tabPgtos.add(panePgTabela);
        panePgEditor.add(lblFpgNome);
        panePgEditor.add(cbFpgNome);
        panePgEditor.add(lblValorPagamento);
        panePgEditor.add(edtValorPagamento);
        panePgEditor.add(btnAddPg);
        panePgEditor.add(btnUpdPg);
        panePgEditor.add(btnDelPg);

        JScrollPane spPgLocal = new JScrollPane(tabPgtosGrid);
        panePgTabela.add(spPgLocal);

        tabs.addTab("Pagamentos", tabPgtos);
    }

    private void posicionar() {
        paneCabecario.setBounds(10, 10, 1470, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);

        btnPrimeiro.setBounds(0, 7, 100, 25);
        btnAnterior.setBounds(105, 7, 100, 25);
        btnProximo.setBounds(210, 7, 100, 25);
        btnUltimo.setBounds(315, 7, 100, 25);
        btnIncluir.setBounds(520, 7, 100, 25);
        btnAlterar.setBounds(625, 7, 100, 25);
        btnExcluir.setBounds(730, 7, 100, 25);
        btnImprimir.setBounds(1190, 7, 120, 25);
        btnGravar.setBounds(1320, 7, 120, 25);

        paneCentro.setBounds(10, 60, 1470, 770);

        lblTitulo.setBounds(0, 0, 1450, 30);

        tabs.setBounds(10, 10, 1450, 750);

        // ===== Dados =====
        lblVdaCodigo.setBounds(10, 15, 70, 25);
        edtVdaCodigo.setBounds(75, 15, 100, 25);

        lblUsuCodigo.setBounds(190, 15, 60, 25);
        edtUsuCodigo.setBounds(255, 15, 80, 25);

        lblCliCodigo.setBounds(345, 15, 60, 25);
        edtCliCodigo.setBounds(410, 15, 80, 25);

        lblData.setBounds(500, 15, 140, 25);
        edtData.setBounds(640, 15, 120, 25);

        lblObs.setBounds(10, 55, 40, 25);
        spObs.setBounds(55, 55, 1050, 120);

        lblValorVenda.setBounds(10, 185, 60, 25);
        edtValorVenda.setBounds(70, 185, 120, 25);

        lblDescontoVenda.setBounds(200, 185, 70, 25);
        edtDescontoVenda.setBounds(270, 185, 120, 25);

        lblTotalVenda.setBounds(400, 185, 60, 25);
        edtTotalVenda.setBounds(460, 185, 120, 25);

        // ===== Itens =====
        tabItens.setBounds(0, 0, 1450, 750);
        paneItemEditor.setBounds(10, 10, 1425, 120);
        paneItemTabela.setBounds(10, 140, 1425, 560);

        lblProCod.setBounds(10, 10, 80, 25);
        edtProCod.setBounds(90, 10, 80, 25);

        lblProNome.setBounds(180, 10, 45, 25);
        edtProNome.setBounds(230, 10, 320, 25);

        lblProUn.setBounds(560, 10, 35, 25);
        edtProUn.setBounds(600, 10, 50, 25);

        lblProQtde.setBounds(10, 45, 50, 25);
        edtProQtde.setBounds(60, 45, 80, 25);

        lblProPreco.setBounds(150, 45, 45, 25);
        edtProPreco.setBounds(200, 45, 100, 25);

        lblProDesconto.setBounds(310, 45, 60, 25);
        edtProDesconto.setBounds(370, 45, 120, 25);

        lblProTotal.setBounds(500, 45, 60, 25);
        edtProTotal.setBounds(570, 45, 100, 25);

        btnAddItem.setBounds(900, 35, 110, 25);
        btnDelItem.setBounds(1020, 35, 110, 25);
        btnUpdItem.setBounds(1140, 35, 110, 25);

        spItens = (JScrollPane) paneItemTabela.getComponent(0);
        spItens.setBounds(0, 0, 1425, 560);

        // ===== Pagamentos =====
        tabPgtos.setBounds(0, 0, 1450, 750);

        panePgEditor.setBounds(10, 10, 1425, 90);
        panePgTabela.setBounds(10, 110, 1425, 590);

        lblFpgNome.setBounds(10, 10, 50, 25);
        cbFpgNome.setBounds(65, 10, 300, 25);

        lblValorPagamento.setBounds(375, 10, 40, 25);
        edtValorPagamento.setBounds(420, 10, 120, 25);

        btnAddPg.setBounds(560, 10, 110, 25);
        btnUpdPg.setBounds(680, 10, 110, 25);

        btnDelPg.setBounds(800, 10, 110, 25);

        spPg = (JScrollPane) panePgTabela.getComponent(0);
        spPg.setBounds(0, 0, 1425, 590);

        // ===== Consulta =====
        tabConsulta.setBounds(0, 0, 1450, 750);

        paneConsultaDados.setBounds(10, 10, 1425, 60);
        paneConsultaTabela.setBounds(10, 60, 1425, 650);

        lblId1.setBounds(10, 10, 30, 25);
        edtId1.setBounds(50, 10, 80, 25);

        lblATxt.setBounds(140, 10, 30, 25);

        lblId2.setBounds(180, 10, 30, 25);
        edtId2.setBounds(220, 10, 80, 25);

        lblValorConsulta1.setBounds(310, 10, 70, 25);
        edtValorConsulta1.setBounds(390, 10, 100, 25);

        lblValorConsulta2.setBounds(500, 10, 70, 25);
        edtValorConsulta2.setBounds(580, 10, 100, 25);

        btnConsultar.setBounds(690, 10, 110, 25);
        btnLimpar.setBounds(810, 10, 110, 25);

        scrollConsulta.setBounds(0, 0, 1425, 650);
    }

    private void configurarAcoes() {
        // Cabeçalho
        btnIncluir.addActionListener(e -> {
            System.out.println("\n [VendaView] btnIncluir clicado");

            userLogado = SessionModel.getCurrentUser();
            incluir();
        });

        btnAlterar.addActionListener(e -> alterar());
        btnGravar.addActionListener(e -> gravar());
        btnExcluir.addActionListener(e -> excluirSelecionada());

        btnImprimir.addActionListener(e -> {
            System.out.println("\n [VendaView] btnImprimir clicado \n ");

            VendaController ctrl = new VendaController();
            Exception retorno = ctrl.imprimir();
            if (retorno != null) {
                JOptionPane.showMessageDialog(null, "Erro no Relatório de Venda /n" + retorno.getMessage());
            }

            System.out.println(" [VendaView] btnImprimir terminou");
        });

        btnPrimeiro.addActionListener(e -> selecionarIndice(0));
        btnAnterior.addActionListener(e -> navegar(-1));
        btnProximo.addActionListener(e -> navegar(+1));
        btnUltimo.addActionListener(e -> selecionarIndice(listaVendas.size() - 1));

        // Consulta
        btnConsultar.addActionListener(e -> consultarVenda());

        btnLimpar.addActionListener(e -> {
            edtId1.setText("");
            edtId2.setText("");
            edtValorConsulta1.setText("");
            edtValorConsulta2.setText("");
            limparTabelaConsulta();
        });

        // Produto: preencher ao sair do código
        edtProCod.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                preencherProduto();
            }
        });
        // Subtotal ao sair da qtde
        edtProQtde.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                recalcProTotal();
            }
        });

        // Itens
        btnAddItem.addActionListener(e -> {
            System.out.println("\n [VendaView] btnAddItem clicado");

            try {
                adicionarItem();
            } catch (Exception ex) {
                System.out.println("\n [VendView] ERRO! ao executar btrAddItem \n" + ex);
            }
        });

        btnDelItem.addActionListener(e -> removerItemSelecionado());
        btnUpdItem.addActionListener(e -> atualizarProdutoSelecionado());

        // Pagamentos
        btnAddPg.addActionListener(e -> {
            System.out.println("\n [VendaView] btnAddPg clicado");
            adicionarPagamento();
        });

        btnUpdPg.addActionListener(e -> atualizarPagamentoSelecionado());
        btnDelPg.addActionListener(e -> removerPagamentoSelecionado());

        /**
         * Seleção nas tabelas ====
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

        // Itens: ao selecionar, carrega no editor de itens
        tabItensGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int viewSel = tabItensGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabItensGrid.convertRowIndexToModel(viewSel);
                VendaProdutoModel it = itensModel.getItem(modelSel);
                mostrarItem(it);
            }
        });

        // Pagamentos: ao selecionar, carrega no editor de pagamentos
        tabPgtosGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int viewSel = tabPgtosGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabPgtosGrid.convertRowIndexToModel(viewSel);
                VendaPagtoModel pg = pgtosModel.get(modelSel);
                mostrarPagamento(pg);
            }
        });
    }

    /**
     * ===== Consulta =====
     */
    private String filtroConsultaVenda() {
        String cond = "";

        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(v.vda_codigo >= " + edtId1.getText().trim() + ")";
        }

        if (!edtId2.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(v.vda_codigo <= " + edtId2.getText().trim() + ")";
        }

        if (!edtValorConsulta1.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(v.vda_total >= " + edtValorConsulta1.getText().trim() + ")";
        }

        if (!edtValorConsulta2.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(v.vda_total <= " + edtValorConsulta2.getText().trim() + ")";
        }

        System.out.println(" [VendaView] filtro da consulta = " + cond);

        return cond;
    }

    private void consultarVenda() {
        System.out.println("\n [VendaView] void consultarVenda() iniciado...");
        try {
            // Limpa a tabela
            limparTabelaConsulta();

            // Busca as vendas do banco (retorna lista de VendaModel)
            ArrayList<VendaModel> lista = new VendaController().consultar(filtroConsultaVenda());

            if (lista == null) {
                lista = new ArrayList<>();
            }

            // Cria um novo model com os dados
            consultaModel = new VendaTableModel(lista);

            // Atualiza o JTable com o novo modelo
            tabelaConsulta.setModel(consultaModel);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na consulta: " + ex.getMessage());
        }
    }

    private void limparTabelaConsulta() {
        System.out.println(" [VendaView] limpando tabela.");
        consultaModel = new VendaTableModel(new ArrayList<>());
        tabelaConsulta.setModel(consultaModel);
    }

    private void selecionarLinhaConsulta() {
        System.out.println("\n [VendaView] void selecionarLinhaConsulta() iniciou...");
        int viewSel = tabelaConsulta.getSelectedRow();

        if (viewSel < 0) {
            return;
        }

        int modelSel = tabelaConsulta.convertRowIndexToModel(viewSel);
        int vda = Integer.parseInt(String.valueOf(consultaModel.getValueAt(modelSel, 0))); // vda_codigo

        try {
            String cond = "(v.vda_codigo = " + vda + ")";
            VendaModel venda = new VendaController().get(vda);
            ArrayList<VendaProdutoModel> vendaProduto = new VendaProdutoController().consultar(cond);
            ArrayList<VendaPagtoModel> vendaFormapagto = new VendapagtoController().consultar(cond);

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
            for (VendaPagtoModel pg : vendaFormapagto) {
                pgtosModel.add(pg);
            }

            recomputarTotais();

            // ir para a aba "Dados" 
            tabs.setSelectedComponent(tabDados);

            setOperacao("incluir");
            System.out.println("\n [VendaView] void selecionarLinhaConsulta() terminou.");
            System.out.println(" [VendaView] Operacao = " + getOperacao());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar venda: " + ex.getMessage());
        }
    }

    private void selecionarIndice(int idx) {
        if (idx < 0 || idx >= listaVendas.size()) {
            return;
        }
        VendaModel v = listaVendas.get(idx);
        mostrarDados(v);
    }

    private void navegar(int delta) {
        if (listaVendas.isEmpty()) {
            return;
        }
        int atual = -1;
        int cod = parseInt(edtVdaCodigo.getText());
        for (int i = 0; i < listaVendas.size(); i++) {
            if (listaVendas.get(i).getVda_codigo() == cod) {
                atual = i;
                break;
            }
        }
        int prox = (atual < 0 ? 0 : atual + delta);
        if (prox < 0) {
            prox = 0;
        }
        if (prox >= listaVendas.size()) {
            prox = listaVendas.size() - 1;
        }
        selecionarIndice(prox);
    }

    /**
     * ===== Itens =====
     */
    private void preencherProduto() {
        System.out.println("\n [VendaView] void preencherProduto() iniciado...");
        try {
            int cod = parseInt(edtProCod.getText());
            if (cod <= 0) {
                limparCamposProduto();
                return;
            }

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
        double qt = parseDouble(edtProQtde.getText());
        double pr = parseDouble(edtProPreco.getText());
        double desc = parseDouble(edtProDesconto.getText());
        edtProTotal.setText(fmt((qt * pr) - desc));
    }

    private void adicionarItem() throws Exception {
        System.out.println(" [VendaView] void adicionarItem() iniciado...");

        Integer pro_cod = parseInt(edtProCod.getText());
        double qt = parseDouble(edtProQtde.getText());
        double pr = parseDouble(edtProPreco.getText());
        double desc = parseDouble(edtProDesconto.getText());

        if (pro_cod <= 0) {
            JOptionPane.showMessageDialog(this, "Informe o código do produto.");
            return;
        }

        if (qt <= 0 || pr <= 0) {
            JOptionPane.showMessageDialog(this, "Qtde e Preço precisam ser > 0.");
            return;
        }

        ProdutoModel p = new ProdutoModel();
        VendaModel v = new VendaModel();
        v.setVda_codigo(0);

        try {
            p = new ProdutoController().get(pro_cod);
        } catch (Exception e) {
            System.out.println(" [VendaView] ERRO ao CRIAR O PRODUTO da tabela" + e);
            throw e;
        }

        VendaProdutoModel it = new VendaProdutoModel();
        it.setVep_codigo(null);
        it.setVenda_VendaProduto(v);
        it.setProduto_VendaProduto(p);
        it.setVep_qtde(qt);
        it.setVep_preco(pr);
        it.setVep_desconto(desc);
        double total = ((qt * pr) - desc);
        it.setVep_total(total);

        itensModel.addItem(it);

        limparCamposProduto();
        recomputarTotais();
    }

    private void removerItemSelecionado() {
        int row = tabItensGrid.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um item.");
            return;
        }
        int modelRow = tabItensGrid.convertRowIndexToModel(row);
        itensModel.removeItem(modelRow);
        recomputarTotais();
    }

    private void limparCamposProduto() {
        edtProCod.setText("");
        edtProNome.setText("");
        edtProUn.setText("");
        edtProQtde.setText("");
        edtProDesconto.setText("");
        edtProPreco.setText("");
        edtProTotal.setText("");
    }

    private void atualizarProdutoSelecionado() {
        System.out.println("\n [VendaView] void atualizarProdutoSelecionado() iniciado...");

        int row = tabItensGrid.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para atualizar.");
            return;
        }

        int modelRow = tabItensGrid.convertRowIndexToModel(row);

        try {
            // Obtém o item atual
            VendaProdutoModel it = itensModel.getItem(modelRow);
            if (it == null) {
                JOptionPane.showMessageDialog(this, "Item inválido ou não encontrado.");
                return;
            }

            // Lê os novos valores dos campos
            int pro_cod = parseInt(edtProCod.getText());
            double qt = parseDouble(edtProQtde.getText());
            double preco = parseDouble(edtProPreco.getText());
            double desc = parseDouble(edtProDesconto.getText());
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
            it.getProduto_VendaProduto().setPRO_CODIGO(pro_cod);
            it.getProduto_VendaProduto().setPRO_NOME(edtProNome.getText());
            it.getProduto_VendaProduto().setPRO_UNIDADE(edtProUn.getText());
            it.setVep_qtde(qt);
            it.setVep_preco(preco);
            it.setVep_desconto(desc);
            it.setVep_total(total);

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
     * ===== Pagamentos =====
     */
    private void recomputarTotais() {
        double soma = 0.0;

        double descV = parseDouble(edtDescontoVenda.getText());

        /**
         * Total da VENDA -> op = 3
         */
        for (VendaProdutoModel it : itensModel.getLinhas()) {
            soma += it.getVep_total();
        }

        if (descV < 0) {
            JOptionPane.showMessageDialog(this, "Desconto precisa ser >= 0.");
            return;
        }

        if (descV > soma) {
            JOptionPane.showMessageDialog(this, "Desconto NÂO pode ser > que Valor Venda.");
            return;
        }

        edtValorVenda.setText(fmt(soma));
        edtDescontoVenda.setText(fmt(descV));
        edtTotalVenda.setText(fmt(soma - descV));

        /**
         * Total da Formapagto -> op = 2
         */
        // se não há pagamentos, sugere o total no campo de valor
        if (pgtosModel.getLinhas().isEmpty()) {
            edtValorPagamento.setText(edtTotalVenda.getText());
        }

        if (pgtosModel.getRowCount() == 1) {
            // mantém 1ª formapag = total atual
            VendaPagtoModel vpd = pgtosModel.get(0);
            vpd.setVdp_valor(parseDouble(edtTotalVenda.getText()));
            pgtosModel.set(0, vpd);
        }

        if (pgtosModel.getRowCount() == 2) {
            // reajusta a 1ª para (total - 2ªformapag)
            VendaPagtoModel vdp2 = pgtosModel.get(1);

            double total = parseDouble(edtTotalVenda.getText());
            double val2 = vdp2.getVdp_valor();

            if (val2 <= 0 || val2 >= total) {
                val2 = Math.max(0, Math.min(total, val2));
                vdp2.setVdp_valor(val2);
                pgtosModel.set(1, vdp2);
            }

            VendaPagtoModel pg1 = pgtosModel.get(0);
            pg1.setVdp_valor(total - val2);
            pgtosModel.set(0, pg1);
        }

    }

    private void carregarFormasPagamento() {
        try {
            System.out.println("\n [VendaView] carregarFormasPagamento() iniciado...");

            ArrayList<String> nomes = new FormapagtoController().listarNomesAtivos();
            cbFpgNome.removeAllItems();
            for (String n : nomes) {
                cbFpgNome.addItem(n);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar formas de pagamento: " + ex.getMessage());
        }
    }

    private void adicionarPagamento() {
        System.out.println(" [VendaView] void adicionarPagamento() iniciado");

        String nomeFpg = (String) cbFpgNome.getSelectedItem();
        double valorInformado = parseDouble(edtValorPagamento.getText().trim());
        double totalVenda = parseDouble(edtTotalVenda.getText().trim());

        FormapagtoController fpgCtrl = new FormapagtoController();
        VendaModel vendaTemp = new VendaModel(); // temporário
        vendaTemp.setVda_codigo(0);

        try {
            if (nomeFpg == null || nomeFpg.isBlank()) {
                JOptionPane.showMessageDialog(this, "Selecione a forma de pagamento.");
                return;
            }

            if (totalVenda <= 0) {
                JOptionPane.showMessageDialog(this, "Total da venda está zerado. Add um item");
                return;
            }

            FormapagtoModel fpg = fpgCtrl.obterCodigoPorNome(nomeFpg);
            if (fpg == null) {
                JOptionPane.showMessageDialog(this, "Forma de pagamento inválida.");
                return;
            }

            // --- Regras de quantidade de pagamentos ---
            int qtd = pgtosModel.getRowCount();

            if (qtd >= 2) {
                JOptionPane.showMessageDialog(this, "Máximo de 2 formas de pagamento permitido.");
                return;
            }

            // --- Adiciona o 1º pagamento ---
            if (qtd == 0) {
                double valor1 = valorInformado > 0 ? valorInformado : totalVenda;

                VendaPagtoModel pg = new VendaPagtoModel();
                pg.setVdp_codigo(null);
                pg.setVenda_VendaPagto(vendaTemp);
                pg.setFormapagto_Vendapagto(fpg);
                pg.setVdp_valor(valor1);

                pgtosModel.add(pg);
                System.out.println(" [VendaView] Adicionada 1ª forma de pagamento: " + fpg.getFPG_NOME() + " = " + valor1);
                edtValorPagamento.setText("");
                return;
            }

            // --- Adiciona o 2º pagamento ---
            if (qtd == 1) {
                if (valorInformado <= 0 || valorInformado >= totalVenda) {
                    JOptionPane.showMessageDialog(this, "Valor da 2ª forma deve ser > 0 e < total.");
                    return;
                }

                double valor2 = valorInformado;
                double valor1 = totalVenda - valor2;

                // Atualiza 1º pagamento
                VendaPagtoModel pg1 = pgtosModel.get(0);
                pg1.setVdp_valor(valor1);
                pgtosModel.set(0, pg1);

                // Cria o 2º pagamento
                VendaPagtoModel pg2 = new VendaPagtoModel();
                pg2.setVdp_codigo(null);
                pg2.setVenda_VendaPagto(vendaTemp);
                pg2.setFormapagto_Vendapagto(fpg);
                pg2.setVdp_valor(valor2);
                pgtosModel.add(pg2);

                System.out.println(" [VendaView] Adicionada 2ª forma de pagamento: " + fpg.getFPG_NOME() + " = " + valor2);
                edtValorPagamento.setText("");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar pagamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void atualizarPagamentoSelecionado() {
        int row = tabPgtosGrid.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um pagamento para atualizar.");
            return;
        }

        int qtd = pgtosModel.getRowCount();
        if (qtd < 2 || row == 0) {
            JOptionPane.showMessageDialog(this, "A atualização é permitida apenas na 2ª forma (quando existir).");
            return;
        }

        try {
            String nomeFpg = (String) cbFpgNome.getSelectedItem();
            double totalVenda = parseDouble(edtTotalVenda.getText());
            double valor2 = parseDouble(edtValorPagamento.getText());

            if (valor2 <= 0 || valor2 >= totalVenda) {
                JOptionPane.showMessageDialog(this, "Valor da 2ª forma deve ser > 0 e < total.");
                return;
            }

            FormapagtoModel fpg = new FormapagtoController().obterCodigoPorNome(nomeFpg);
            if (fpg == null) {
                JOptionPane.showMessageDialog(this, "Forma de pagamento inválida.");
                return;
            }

            // Atualiza segunda forma
            VendaPagtoModel segunda = pgtosModel.get(row);
            segunda.getFormapagto_VendaPagto().setFPG_CODIGO(fpg.getFPG_CODIGO());
            segunda.getFormapagto_VendaPagto().setFPG_NOME(nomeFpg);
            segunda.setVdp_valor(valor2);
            pgtosModel.set(row, segunda);

            // Recalcula primeira
            VendaPagtoModel primeira = pgtosModel.get(0);
            primeira.setVdp_valor(totalVenda - valor2);
            pgtosModel.set(0, primeira);

            System.out.println(" [VendaView] Pagamento atualizado. 1ª forma = "
                    + primeira.getVdp_valor() + ", 2ª = " + valor2);

            edtValorPagamento.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar pagamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void removerPagamentoSelecionado() {
        int row = tabPgtosGrid.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um pagamento.");
            return;
        }

        int modelRow = tabPgtosGrid.convertRowIndexToModel(row);
        pgtosModel.remove(modelRow);

        if (pgtosModel.getRowCount() == 1) {
            VendaPagtoModel pg1 = pgtosModel.get(0);
            pg1.setVdp_valor(parseDouble(edtTotalVenda.getText()));
            pgtosModel.set(0, pg1);
        }
    }

    /**
     * ===== Cabeçalho / Gravação =====
     */
    private void incluir() {
        System.out.println(" [VendaView] void incluir() iniciado");

        setOperacao("nova_venda");
        limparTudo();

        edtData.setText(LocalDate.now().toString());
        tabs.setSelectedComponent(tabDados);
        btnGravar.setFocusable(true);

        // btn DADOS
        edtVdaCodigo.setText("0");                 // mostra 0
        edtVdaCodigo.setFocusable(false);
        edtValorVenda.setEditable(false);

        userLogado = SessionModel.getCurrentUser();
        String cod_UserLogado = String.valueOf(userLogado.getUSU_CODIGO());
        System.out.println(" [VendaView] void novo() -> Codigo user logado = " + cod_UserLogado);

        edtUsuCodigo.setText(cod_UserLogado);
        edtUsuCodigo.setFocusable(true);

        edtCliCodigo.setFocusable(true);
        edtData.setFocusable(true);
        edtObs.setFocusable(true);
        edtDescontoVenda.setFocusable(true);

        // btn ITENS
        edtProCod.setFocusable(true);
        edtProQtde.setFocusable(true);
        edtProDesconto.setFocusable(true);

        //btn Pagtos
        edtValorPagamento.setFocusable(true);

        carregarFormasPagamento();
    }

    private void incluir_venda() {
        System.out.println("\n [VendaView] void gerar_venda() iniciado...");

        try {
            int usu_cod = parseInt(edtUsuCodigo.getText().trim());
            int cli_cod = parseInt(edtCliCodigo.getText().trim());
            LocalDate data_v = (LocalDate.parse(edtData.getText()));
            double valor_v = (parseDouble(edtValorVenda.getText()));
            double desc_v = (parseDouble(edtDescontoVenda.getText()));
            double total_v = (parseDouble(edtTotalVenda.getText()));
            String obs_v = (edtObs.getText());

            ArrayList<VendaProdutoModel> itens = (itensModel.getLinhas());
            ArrayList<VendaPagtoModel> pgtos = (pgtosModel.getLinhas());

            // validações mínimas 
            if (usu_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o usuário.");
                return;
            }
            if (cli_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o cliente.");
                return;
            }

            if (itens == null || itens.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos de Itens");
                return;
            }

            if (pgtos == null || pgtos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos de Pagamentos");
                return;
            }

            // Gravando a Venda
            new VendaController().incluir(usu_cod, cli_cod, data_v, valor_v, desc_v, total_v, obs_v, itens, pgtos);

            JOptionPane.showMessageDialog(this, "Venda gravada.");

            tabs.setSelectedComponent(tabConsulta);
            consultarVenda();

            limparTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
        }
    }

    private void alterar() {
        System.out.println("\n [VendaView] btnAlterar clicado.");

        setOperacao("alterar_gravar");

        // btn DADOS
        edtVdaCodigo.setFocusable(true);
        edtUsuCodigo.setFocusable(true);
        edtCliCodigo.setFocusable(true);
        edtData.setFocusable(true);
        edtObs.setFocusable(true);
        edtDescontoVenda.setFocusable(true);
        edtValorVenda.setEditable(true);

        // btn ITENS
        edtProCod.setFocusable(true);
        edtProQtde.setFocusable(true);
        edtProDesconto.setFocusable(true);

        //btn Pagtos
        edtValorPagamento.setFocusable(true);

        carregarFormasPagamento();
    }

    private void alterar_gravar() {
        System.out.println(" [VendaView] void alterar_gravar() iniciado...");
        System.out.println(" [VendaView] Operacoa atual = " + getOperacao());

        try {
            int vda_cod = parseInt(edtVdaCodigo.getText().trim());
            int usu_cod = parseInt(edtUsuCodigo.getText().trim());
            int cli_cod = parseInt(edtCliCodigo.getText().trim());
            LocalDate data_v = (LocalDate.parse(edtData.getText()));
            double valor_v = (parseDouble(edtValorVenda.getText()));
            double desc_v = (parseDouble(edtDescontoVenda.getText()));
            double total_v = (parseDouble(edtTotalVenda.getText()));
            String obs_v = (edtObs.getText());

            ArrayList<VendaProdutoModel> itens = (itensModel.getLinhas());
            ArrayList<VendaPagtoModel> pgtos = (pgtosModel.getLinhas());

            // validações mínimas 
            if (vda_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o venda.");
                return;
            }
            if (usu_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o usuário.");
                return;
            }
            if (cli_cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o cliente.");
                return;
            }

            if (itens == null || itens.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos de Itens");
                return;
            }

            if (pgtos == null || pgtos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos de Pagamentos");
                return;
            }

            // Gravando a Venda
            new VendaController().alterar(vda_cod, usu_cod, cli_cod, data_v, valor_v, desc_v, total_v, obs_v, itens, pgtos);

            JOptionPane.showMessageDialog(this, "Venda alterada.");

            tabs.setSelectedComponent(tabConsulta);
            consultarVenda();

            limparTudo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
        }
    }

    private void excluirSelecionada() {
        System.out.println("\n [VendaView] void excluir() iniciado");

        try {
            int cod = parseInt(edtVdaCodigo.getText());
            if (cod <= 0) {
                JOptionPane.showMessageDialog(this, "Informe o Código da venda.");
                return;
            }
            VendaModel v = new VendaModel();
            v.setVda_codigo(cod);
            new VendaController().excluir(v);

            JOptionPane.showMessageDialog(this, "Venda excluída.");

            tabs.setSelectedComponent(tabConsulta);
            consultarVenda();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Falha ao excluir: " + ex.getMessage());
        }
    }

    private void gravar() {
        System.out.println("\n [VendaView] btnGravar clicado");
        System.out.println(" [VendaView] void GRAVAR iniciado com Operacao = " + getOperacao());

        if (operacao.isEmpty() || operacao.equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione uma Operação! exempo: NOVO");
        }

        if (operacao.equals("nova_venda")) {
            incluir_venda();
        }

        if (operacao.equals("alterar_gravar")) {
            alterar_gravar();
        }

    }

    /**
     * ==== Seleção e editores ====
     */
    // Preenche os campos do editor de DADOS;
    private void mostrarDados(VendaModel v) {
        edtVdaCodigo.setText(String.valueOf(v.getVda_codigo()));
        edtUsuCodigo.setText(String.valueOf(v.getUsu_venda().getUSU_CODIGO()));
        edtCliCodigo.setText(String.valueOf(v.getCli_venda().getCLI_CODIGO()));
        edtData.setText(v.getVda_data() == null ? "" : v.getVda_data().toString());
        edtObs.setText(v.getVda_obs() == null ? "" : v.getVda_obs());
        edtValorVenda.setText(fmt(v.getVda_valor()));
        edtDescontoVenda.setText(fmt(v.getVda_desconto()));
        edtTotalVenda.setText(fmt(v.getVda_total()));
    }

    // Preenche os campos do editor de ITENS com base no modelo
    private void mostrarItem(VendaProdutoModel it) {
        if (it == null) {
            return;
        }
        edtProCod.setText(String.valueOf(it.getProduto_VendaProduto().getPRO_CODIGO()));
        edtProNome.setText(it.getProduto_VendaProduto().getPRO_NOME());
        edtProUn.setText(it.getProduto_VendaProduto().getPRO_UNIDADE());
        edtProQtde.setText(fmt(it.getVep_qtde()));
        edtProPreco.setText(fmt(it.getVep_preco()));
        edtProTotal.setText(fmt(it.getVep_total()));
    }

    // Preenche os campos do editor de PAGAMENTOS
    private void mostrarPagamento(VendaPagtoModel pg) {
        if (pg == null) {
            return;
        }

        // Seleciona o nome no combo (se existir na lista)
        cbFpgNome.setSelectedItem(pg.getFormapagto_VendaPagto().getFPG_NOME());
        edtValorPagamento.setText(fmt(pg.getVdp_valor()));
    }

    private void limparTudo() {
        edtVdaCodigo.setText("");
        edtUsuCodigo.setText("");
        edtCliCodigo.setText("");
        edtData.setText(LocalDate.now().toString());
        edtObs.setText("");
        edtValorVenda.setText("");
        edtDescontoVenda.setText("");
        edtTotalVenda.setText("");
        // limpa listas
        while (itensModel.getRowCount() > 0) {
            itensModel.removeItem(0);
        }
        while (pgtosModel.getRowCount() > 0) {
            pgtosModel.remove(0);
        }
        edtValorPagamento.setText("");
    }

    /**
     * ===== Helpers numéricos =====
     */
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
     * Setters
     */
    private void setOperacao(String op) {
        this.operacao = op == null ? "" : op;
    }

    private String getOperacao() {
        return operacao;
    }
}
