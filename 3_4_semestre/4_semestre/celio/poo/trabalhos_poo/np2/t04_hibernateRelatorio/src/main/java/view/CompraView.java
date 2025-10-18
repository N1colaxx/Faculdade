package view;

import controller.CompraController;
import controller.ProdutoController;

import model.CompraModel;
import model.CompraProdutoModel;

import javax.swing.*;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Tela de Cadastro de COMPRAS (espelho da VendaView, sem Pagamentos).
 */
public class CompraView extends JPanel {
    
    private JLabel lblTitulo;

    // Cabeçalho
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;

    // Abas
    private JPanel paneCabecario;
    private JPanel paneCentro;
    private JTabbedPane tabs;

    // ===== Dados =====
    private JPanel tabDados;
    private JLabel lblCprCodigo, lblUsuCodigo, lblForCodigo, lblEmissao, lblDtEntrada, lblObs;
    private JTextField edtCprCodigo, edtUsuCodigo, edtForCodigo, edtEmissao, edtDtEntrada;
    private JTextArea edtObs;
    private JLabel lblValor, lblDesc, lblTotal;
    private JTextField edtValor, edtDesc, edtTotal;

    // ===== Itens =====
    private JPanel tabItens, paneItemEditor, paneItemTabela;
    private JLabel lblProCod, lblProNome, lblUn, lblQtde, lblPreco, lblSubTotal;
    private JTextField edtProCod, edtProNome, edtUn, edtQtde, edtPreco, edtSubTotal;
    private JButton btnAddItem, btnDelItem;
    private JTable tabItensGrid;
    private CompraProdutoTableModel itensModel;

    // ===== Consulta =====
    private JPanel tabConsulta, paneConsultaDados, paneConsultaTabela;
    private JLabel lblId1, lblATxt, lblId2, lblValorGe, lblValorLe;
    private JTextField edtId1, edtId2, edtValorGe, edtValorLe;
    private JButton btnConsultar, btnLimpar;
    private JTable tabelaConsulta;
    private JScrollPane scrollConsulta;
    private DefaultTableModel consultaModel;

    // Estado
    private String operacao = "";
    private final ArrayList<CompraProdutoModel> listaItens = new ArrayList<>();
    private final ArrayList<CompraModel> listaCompras = new ArrayList<>();

    public CompraView() {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1500, 850));

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();
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
        lblTitulo = new JLabel("Compras", SwingConstants.CENTER);  // << ADICIONE
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));       // idem
        lblTitulo.setForeground(new Color(30,30,120));             // idem


        // Dados
        tabDados = new JPanel(null);
        lblCprCodigo = new JLabel("Código:");
        edtCprCodigo = new JTextField("0");
        edtCprCodigo.setEditable(false);
        edtCprCodigo.setFocusable(false);

        lblUsuCodigo = new JLabel("Usuário:");
        edtUsuCodigo = new JTextField();
        lblForCodigo = new JLabel("Fornecedor:");
        edtForCodigo = new JTextField();

        lblEmissao = new JLabel("Emissão (yyyy-MM-dd):");
        edtEmissao = new JTextField(LocalDate.now().toString());
        lblDtEntrada = new JLabel("Entrada (yyyy-MM-dd):");
        edtDtEntrada = new JTextField(LocalDate.now().toString());

        lblObs = new JLabel("Obs.:");
        edtObs = new JTextArea(); edtObs.setLineWrap(true); edtObs.setWrapStyleWord(true);

        lblValor = new JLabel("Valor:");
        edtValor = new JTextField(); edtValor.setEditable(false);
        lblDesc = new JLabel("Desconto:");
        edtDesc = new JTextField("0");
        lblTotal = new JLabel("Total:");
        edtTotal = new JTextField(); edtTotal.setEditable(false);

        // Itens
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
        edtPreco = new JTextField(); // editável (pode ajustar custo)
        lblSubTotal = new JLabel("Subtotal:");
        edtSubTotal = new JTextField(); edtSubTotal.setEditable(false);

        btnAddItem = new JButton("Adicionar");
        btnDelItem = new JButton("Remover");

        itensModel = new ItemCompraTableModel(listaItens);
        tabItensGrid = new JTable(itensModel);
        tabItensGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Consulta
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

        consultaModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Compra", "Prod", "Qtde", "Preço", "Total"}
        ){
            public boolean isCellEditable(int r, int c){ return false; }
        };
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
        paneCentro.add(lblTitulo);
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
        tabDados.add(lblCprCodigo); tabDados.add(edtCprCodigo);
        tabDados.add(lblUsuCodigo); tabDados.add(edtUsuCodigo);
        tabDados.add(lblForCodigo); tabDados.add(edtForCodigo);
        tabDados.add(lblEmissao);   tabDados.add(edtEmissao);
        tabDados.add(lblDtEntrada); tabDados.add(edtDtEntrada);
        tabDados.add(lblObs);       tabDados.add(edtObs);
        tabDados.add(lblValor);     tabDados.add(edtValor);
        tabDados.add(lblDesc);      tabDados.add(edtDesc);
        tabDados.add(lblTotal);     tabDados.add(edtTotal);
        tabs.addTab("Dados", tabDados);

        // Aba Itens
        tabItens.add(paneItemEditor); tabItens.add(paneItemTabela);
        paneItemEditor.add(lblProCod);     paneItemEditor.add(edtProCod);
        paneItemEditor.add(lblProNome);    paneItemEditor.add(edtProNome);
        paneItemEditor.add(lblUn);         paneItemEditor.add(edtUn);
        paneItemEditor.add(lblQtde);       paneItemEditor.add(edtQtde);
        paneItemEditor.add(lblPreco);      paneItemEditor.add(edtPreco);
        paneItemEditor.add(lblSubTotal);   paneItemEditor.add(edtSubTotal);
        paneItemEditor.add(btnAddItem);    paneItemEditor.add(btnDelItem);
        paneItemTabela.add(new JScrollPane(tabItensGrid));
        tabs.addTab("Itens (Produtos)", tabItens);

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

        // Dados
        lblCprCodigo.setBounds(10, 15, 60, 25);  edtCprCodigo.setBounds(75, 15, 100, 25);
        lblUsuCodigo.setBounds(190, 15, 60, 25); edtUsuCodigo.setBounds(255, 15, 80, 25);
        lblForCodigo.setBounds(345, 15, 70, 25); edtForCodigo.setBounds(420, 15, 80, 25);
        lblEmissao.setBounds(510, 15, 160, 25);  edtEmissao.setBounds(670, 15, 120, 25);
        lblDtEntrada.setBounds(800, 15, 160, 25);edtDtEntrada.setBounds(960, 15, 120, 25);

        lblObs.setBounds(10, 55, 40, 25);        edtObs.setBounds(55, 55, 1050, 120);

        lblValor.setBounds(10, 185, 60, 25);     edtValor.setBounds(70, 185, 120, 25);
        lblDesc.setBounds(200, 185, 70, 25);     edtDesc.setBounds(270, 185, 120, 25);
        lblTotal.setBounds(400, 185, 60, 25);    edtTotal.setBounds(460, 185, 120, 25);

        // Itens
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

        // Consulta
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
        // produto ao sair do código
        edtProCod.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { preencherProduto(); }
        });
        // subtotal ao sair da qtde/preço
        edtQtde.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { recalcSubtotal(); }
        });
        edtPreco.addFocusListener(new FocusAdapter() {
            @Override public void focusLost(FocusEvent e) { recalcSubtotal(); }
        });

        // Itens
        btnAddItem.addActionListener(e -> adicionarItem());
        btnDelItem.addActionListener(e -> removerItemSelecionado());

        // Consulta
        btnConsultar.addActionListener(e -> consultarCompraProduto());
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
        btnUltimo.addActionListener(e -> selecionarIndice(listaCompras.size()-1));

        // seleção nas tabelas
        tabItensGrid.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int viewSel = tabItensGrid.getSelectedRow();
            if (viewSel >= 0) {
                int modelSel = tabItensGrid.convertRowIndexToModel(viewSel);
                mostrarItem(itensModel.getItem(modelSel));
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

    // ==== Itens ====

    private void preencherProduto() {
        try {
            int cod = parseInt(edtProCod.getText());
            if (cod <= 0) { 
                limparCamposProduto(); 
                return; 
            }
            
            ItemCompraModel p = new ProdutoController().buscarPorCodigoCompra(cod);
           
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado/ativo.");
                limparCamposProduto();
            
                return;
            }
            
            edtProNome.setText(p.getPRO_NOME());
            edtUn.setText(p.getPRO_UNIDADE());
            edtPreco.setText(fmt(p.getPRO_CUSTO())); // custo sugerido
            
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

        CompraProdutoModel it = new CompraProdutoModel();
        it.setCPR_CODIGO(parseInt(edtCprCodigo.getText())); // só exibicional (0 na inclusão)
        it.setPRO_CODIGO(pro);
        it.setCPR_QTDE(bd(qt));
        it.setCPR_PRECO(bd(pr));
        it.setCPR_DESCONTO(bd(0));
        it.setCPR_TOTAL(bd(qt*pr));

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
        BigDecimal soma = itensModel.somaTotais();
        edtValor.setText(fmt(soma.doubleValue()));
        double desc = parseDouble(edtDesc.getText());
        double s = soma.doubleValue();
        if (desc < 0) desc = 0;
        if (desc > s) desc = s;
        edtDesc.setText(fmt(desc));
        edtTotal.setText(fmt(s - desc));
    }

    
    
    
    // ==== Consulta ====
    private String filtroConsultaCompraProduto() {
        StringBuilder cond = new StringBuilder();
        String id1 = edtId1.getText().trim();
        String id2 = edtId2.getText().trim();
        String ge  = edtValorGe.getText().trim().replace(",", ".");
        String le  = edtValorLe.getText().trim().replace(",", ".");

        if (!id1.isEmpty()) cond.append("(cpr_codigo >= ").append(id1).append(")");
        if (!id2.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(cpr_codigo <= ").append(id2).append(")");
        }
        if (!ge.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(cpr_total >= ").append(ge).append(")");
        }
        if (!le.isEmpty()){
            if (cond.length()>0) cond.append(" AND ");
            cond.append("(cpr_total <= ").append(le).append(")");
        }
        return cond.toString();
    }

    private void consultarCompraProduto() {
        limparTabelaConsulta();
        
        try{
            consultaModel.setRowCount(0);
            for(Object[] row : new CompraController().consultarCompraProduto(filtroConsultaCompraProduto())) {
                consultaModel.addRow(row);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Erro na consulta: " + ex.getMessage());
        }
    }

    private void limparTabelaConsulta(){
        while (consultaModel.getRowCount() > 0) consultaModel.removeRow(0);
    }

    private void mostrarItem(CompraProdutoModel it){
        if (it == null) return;
        edtProCod.setText(String.valueOf(it.getPRO_CODIGO()));
        edtProNome.setText(""); 
        edtUn.setText("");
        edtQtde.setText(fmtBD(it.getCPR_QTDE()));
        edtPreco.setText(fmtBD(it.getCPR_PRECO()));
        edtSubTotal.setText(fmtBD(it.getCPR_TOTAL()));
    }
    
    private void selecionarLinhaConsulta() {
        int viewSel = tabelaConsulta.getSelectedRow();
        
        if (viewSel < 0) return;
        
        int modelSel = tabelaConsulta.convertRowIndexToModel(viewSel);
        int cpr = Integer.parseInt(String.valueOf(consultaModel.getValueAt(modelSel, 0))); // cpr_codigo

        try {
            System.out.println("\n [CompraCompletaModel] foi chamado em VendaView");
            CompraCompletaModel ccm = new CompraController().buscarCompraCompleta(cpr);

            if (ccm == null || ccm.cabecalho == null) {
                JOptionPane.showMessageDialog(this, "Compra não encontrada.");
                return;
            }
            
            while (itensModel.getRowCount() > 0) {
                itensModel.removeItem(0);
            }
   
            preencherCampos(ccm.cabecalho);
            
            
            for (CompraProdutoModel it : ccm.itens) {
                itensModel.addItem(it);
            }
            
            recomputarTotais();

            // ir para a aba "Dados" 
            tabs.setSelectedComponent(tabDados);

            /** Marca que estamos olhando um registro existente 
            *  só troca para "alterar" quando clicar no botão Alterar
            */
            setOperacao(""); 
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar venda: " + ex.getMessage());
        }
    }
    
        
    // ==== Cabeçalho / Gravação ====

    private void novo() {
        setOperacao("incluir");
        limparTudo();
        edtCprCodigo.setText("0");       
        edtEmissao.setText(LocalDate.now().toString());
        edtDtEntrada.setText(LocalDate.now().toString());
        tabs.setSelectedComponent(tabDados);

    }

    private void setOperacao(String op){ this.operacao = op==null?"":op; }

    private void gravar() {
        try {
            if (itensModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Inclua ao menos 1 item.");
                return;
            }

            CompraModel c = new CompraModel();
            
            // inclusão força 0; alteração exige >0
            String op = operacao.isEmpty() ? "incluir" : operacao;
            int codTela = parseInt(edtCprCodigo.getText());
            if ("incluir".equals(op)){
                c.setCPR_CODIGO(0);
            } else {
                if (codTela <= 0) {
                    JOptionPane.showMessageDialog(this,"Código inválido para alteração.");
                    return; 
                }
                c.setCPR_CODIGO(codTela);
            }

            c.setUSU_CODIGO(parseInt(edtUsuCodigo.getText()));
            c.setFOR_CODIGO(parseInt(edtForCodigo.getText()));
            c.setCPR_EMISSAO(LocalDate.parse(edtEmissao.getText()));
            c.setCPR_DTENTRADA(LocalDate.parse(edtDtEntrada.getText()));
            c.setCPR_VALOR(parseDouble(edtValor.getText()));
            c.setCPR_DESCONTO(parseDouble(edtDesc.getText()));
            c.setCPR_TOTAL(parseDouble(edtTotal.getText()));
            c.setCPR_OBS(edtObs.getText());

            if (c.getUSU_CODIGO()<=0) { JOptionPane.showMessageDialog(this,"Informe Usuário."); return; }
            if (c.getFOR_CODIGO()<=0){ JOptionPane.showMessageDialog(this,"Informe Fornecedor."); return; }

            ArrayList<CompraProdutoModel> itens = new ArrayList<>(itensModel.getLinhas());
            
            new CompraController().gravar(op, c, itens);

            edtCprCodigo.setText(String.valueOf(c.getCPR_CODIGO()));
            JOptionPane.showMessageDialog(this, "Compra gravada.");
            setOperacao("");
            limparTudo();
                
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Falha ao gravar: " + ex.getMessage());
        }
    }

    private void excluirSelecionada() {
        try {
            int cod = parseInt(edtCprCodigo.getText());
            if (cod <= 0) { JOptionPane.showMessageDialog(this, "Informe o Código da compra."); return; }
            CompraModel c = new CompraModel();
            c.setCPR_CODIGO(cod);
            new CompraController().excluir(c);
            JOptionPane.showMessageDialog(this, "Compra excluída.");
            novo();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Falha ao excluir: " + ex.getMessage());
        }
    }

    private void selecionarIndice(int idx){
        if (idx < 0 || idx >= listaCompras.size()) return;
        CompraModel c = listaCompras.get(idx);
        
        preencherCampos(c);
    }

    private void navegar(int delta){
        if (listaCompras.isEmpty()) return;
        int atual = -1;
        int cod = parseInt(edtCprCodigo.getText());
        for (int i=0;i<listaCompras.size();i++){
            if (listaCompras.get(i).getCPR_CODIGO() == cod) { atual = i; break; }
        }
        int prox = (atual < 0 ? 0 : atual + delta);
        if (prox < 0) prox = 0;
        if (prox >= listaCompras.size()) prox = listaCompras.size()-1;
        selecionarIndice(prox);
    }

    private void preencherCampos(CompraModel c){
        edtCprCodigo.setText(String.valueOf(c.getCPR_CODIGO()));
        edtUsuCodigo.setText(String.valueOf(c.getUSU_CODIGO()));
        edtForCodigo.setText(String.valueOf(c.getFOR_CODIGO()));
        edtEmissao.setText(c.getCPR_EMISSAO() == null ? "" : c.getCPR_EMISSAO().toString());
        edtDtEntrada.setText(c.getCPR_DTENTRADA()==null ? "" : c.getCPR_DTENTRADA().toString());
        edtObs.setText(c.getCPR_OBS()==null?"":c.getCPR_OBS());
        edtValor.setText(fmt(c.getCPR_VALOR()));
        edtDesc.setText(fmt(c.getCPR_DESCONTO()));
        edtTotal.setText(fmt(c.getCPR_TOTAL()));
    }
    
    private void limparTudo(){
        edtCprCodigo.setText("0");
        edtUsuCodigo.setText("");
        edtForCodigo.setText("");
        edtEmissao.setText(LocalDate.now().toString());
        edtDtEntrada.setText(LocalDate.now().toString());
        edtObs.setText("");
        edtValor.setText("0");
        edtDesc.setText("0");
        edtTotal.setText("0");
        // limpa itens
        while (itensModel.getRowCount() > 0) itensModel.removeItem(0);
    }

    // ==== helpers ====
    private int parseInt(String s){ try { return Integer.parseInt(s.trim()); } catch(Exception e){ return 0; } }
    private double parseDouble(String s){ try { return Double.parseDouble(s.trim().replace(",", ".")); } catch(Exception e){ return 0.0; } }
    private String fmt(double d){ return String.format(java.util.Locale.US, "%.2f", d); }
    private String fmtBD(BigDecimal b){ return b==null?"0.00":fmt(b.doubleValue()); }
    private BigDecimal bd(double d){ return new BigDecimal(String.format(java.util.Locale.US,"%.4f", d)); }
}
