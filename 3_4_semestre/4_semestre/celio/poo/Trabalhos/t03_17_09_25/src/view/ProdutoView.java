package view;

import controller.ProdutoController;
import model.ProdutoModel;
import util.ProdutoTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.util.ArrayList;

public class ProdutoView extends JPanel {

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;
    private JButton btnConsultar, btnLimpar;

    // Dados do Cadastro
    private JLabel
            lblProCodigo, lblProNome, lblProEstoque, lblProPreco, lblProCusto, lblProAtacado, lblProMin,
            lblProMax, lblProEmbalagem, lblProPeso, lblProCadastro, lblProUnidade, lblProObs, lblAtivo;

    private JTextField
            edtProCodigo, edtProNome, edtProEstoque, edtProPreco, edtProCusto, edtProAtacado,
            edtProMin, edtProMax, edtProEmbalagem, edtProPeso, edtProCadastro, edtProObs;

    private JComboBox<String> cbxUnidade;
    private JCheckBox chkAtivo;

    // Dados da Consulta
    private JLabel
            lblId1, lblText, lblProNomeFiltro, lblProPrecoFiltro,
            lblProUnidadeFiltro, lblProMinFiltro, lblProMaxFiltro;

    private JTextField
            edtId1, edtId2, edtProNomeFiltro, edtProPrecoFiltro,
            edtProUnidadeFiltro, edtProMinFiltro, edtProMaxFiltro;

    // Título
    private JLabel lblTitulo;

    // Painéis
    private JPanel paneCabecario;      // botões
    private JPanel paneCentro;         // título + abas
    private JTabbedPane paneDadosCadastro;   // aba "Dados do Produto"
    private JTabbedPane paneDadosConsulta;   // aba "Consulta" (única)
    private JPanel tabDadosProduto;          // conteúdo da aba "Dados do Produto"
    private JPanel tabConsulta;              // conteúdo da aba "Consulta"
    private JPanel paneConsultaDados;        // filtros (dentro da aba Consulta)
    private JPanel paneConsultaTabela;       // tabela (dentro da aba Consulta)

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;
    private ProdutoTableModel tableModel;

    // Estado / Dados
    private String operacao = "";
    private final String[] colunas = {
            "Código", "Nome", "Estoque", "Unidade", "Preço", "Custo", "Atacado",
            "Mín", "Máx", "Embalagem", "Peso", "Cadastro", "Obs", "Ativo"
    };
    private ArrayList<ProdutoModel> lista = new ArrayList<>();

    public ProdutoView() {
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();

        configurarAcoes();
    }


    private void instanciar() {
        // Botões topo
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo  = new JButton("Próximo");
        btnUltimo   = new JButton("Último");
        btnNovo     = new JButton("Novo");
        btnAlterar  = new JButton("Alterar");
        btnExcluir  = new JButton("Excluir");
        btnGravar   = new JButton("Gravar");

        // Título
        lblTitulo = new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Dados do Produto (labels)
        lblProCodigo    = new JLabel("Código:");
        lblProNome      = new JLabel("Nome:");
        lblProEstoque   = new JLabel("Estoque:");
        lblProPreco     = new JLabel("Preço:");
        lblProCusto     = new JLabel("Custo:");
        lblProAtacado   = new JLabel("Atacado:");
        lblProMin       = new JLabel("Mín.:");
        lblProMax       = new JLabel("Máx.:");
        lblProEmbalagem = new JLabel("Embalagem:");
        lblProPeso      = new JLabel("Peso:");
        lblProCadastro  = new JLabel("Cadastro:");
        lblProUnidade   = new JLabel("Unidade:");
        lblProObs       = new JLabel("Obs.:");
        lblAtivo        = new JLabel("Ativo:");

        // Dados do Produto (inputs)
        edtProCodigo    = new JTextField();
        edtProNome      = new JTextField();
        edtProEstoque   = new JTextField();
        edtProPreco     = new JTextField();
        edtProCusto     = new JTextField();
        edtProAtacado   = new JTextField();
        edtProMin       = new JTextField();
        edtProMax       = new JTextField();
        edtProEmbalagem = new JTextField();
        edtProPeso      = new JTextField();
        edtProCadastro  = new JTextField();
        edtProObs       = new JTextField();

        cbxUnidade      = new JComboBox<>(new String[]{"UN", "KG", "LT", "CX", "PC"});
        chkAtivo        = new JCheckBox();
        chkAtivo.setBackground(new Color(245, 250, 255));

        // Consulta (filtros)
        lblId1              = new JLabel("ID");
        lblText             = new JLabel("à");
        lblProNomeFiltro    = new JLabel("Nome");
        lblProPrecoFiltro   = new JLabel("Preço");
        lblProUnidadeFiltro = new JLabel("Unidade");
        lblProMinFiltro     = new JLabel("Estoque ≥");
        lblProMaxFiltro     = new JLabel("Estoque ≤");

        edtId1              = new JTextField();
        edtId2              = new JTextField();
        edtProNomeFiltro    = new JTextField();
        edtProPrecoFiltro   = new JTextField();
        edtProUnidadeFiltro = new JTextField();
        edtProMinFiltro     = new JTextField();
        edtProMaxFiltro     = new JTextField();

        // Tabela
        tableModel = new ProdutoTableModel(lista, colunas);
        tabela = new JTable(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabela = new JScrollPane(tabela);

        // Sincroniza seleção -> campos
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                int viewSel = tabela.getSelectedRow();
                if (viewSel >= 0 && viewSel < lista.size()) {
                    int sel = tabela.convertRowIndexToModel(viewSel);
                    mostrar(lista.get(sel));
                }
            }
        });

        // Contêineres
        paneCabecario      = new JPanel(null);
        paneCentro         = new JPanel(null);
        paneCentro.setBackground(new Color(245, 250, 255));

        paneDadosCadastro  = new JTabbedPane();
        paneDadosConsulta  = new JTabbedPane();

        tabDadosProduto    = new JPanel(null);
        tabConsulta        = new JPanel(null);
        paneConsultaDados  = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        for (JPanel p : new JPanel[]{tabDadosProduto, tabConsulta, paneConsultaDados, paneConsultaTabela, paneCabecario}) {
            p.setBackground(new Color(245, 250, 255));
        }
    }


    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro); paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);  paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);     paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);  paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        paneCentro.add(lblTitulo);
        add(paneCentro);

        // Aba: Dados do Produto
        tabDadosProduto.add(lblProCodigo);    tabDadosProduto.add(edtProCodigo);
        tabDadosProduto.add(lblProNome);      tabDadosProduto.add(edtProNome);
        tabDadosProduto.add(lblProEstoque);   tabDadosProduto.add(edtProEstoque);
        tabDadosProduto.add(lblProPreco);     tabDadosProduto.add(edtProPreco);
        tabDadosProduto.add(lblProCusto);     tabDadosProduto.add(edtProCusto);
        tabDadosProduto.add(lblProAtacado);   tabDadosProduto.add(edtProAtacado);
        tabDadosProduto.add(lblProMin);       tabDadosProduto.add(edtProMin);
        tabDadosProduto.add(lblProMax);       tabDadosProduto.add(edtProMax);
        tabDadosProduto.add(lblProEmbalagem); tabDadosProduto.add(edtProEmbalagem);
        tabDadosProduto.add(lblProPeso);      tabDadosProduto.add(edtProPeso);
        tabDadosProduto.add(lblProCadastro);  tabDadosProduto.add(edtProCadastro);
        tabDadosProduto.add(lblProUnidade);   tabDadosProduto.add(cbxUnidade);
        tabDadosProduto.add(lblProObs);       tabDadosProduto.add(edtProObs);
        tabDadosProduto.add(lblAtivo);        tabDadosProduto.add(chkAtivo);

        paneDadosCadastro.addTab("Dados do Produto", tabDadosProduto);
        paneCentro.add(paneDadosCadastro);

        // Aba: Consulta
        paneConsultaDados.add(lblId1);              paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);             paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblProNomeFiltro);    paneConsultaDados.add(edtProNomeFiltro);
        paneConsultaDados.add(lblProPrecoFiltro);   paneConsultaDados.add(edtProPrecoFiltro);
        paneConsultaDados.add(lblProUnidadeFiltro); paneConsultaDados.add(edtProUnidadeFiltro);
        paneConsultaDados.add(lblProMinFiltro);     paneConsultaDados.add(edtProMinFiltro);
        paneConsultaDados.add(lblProMaxFiltro);     paneConsultaDados.add(edtProMaxFiltro);

        // Botões de consulta/limpa (iguais ao UsuarioView)
        btnConsultar = new JButton("Consulta");
        btnLimpar    = new JButton("Limpa");
        paneConsultaDados.add(btnConsultar);
        paneConsultaDados.add(btnLimpar);

        // Tabela
        paneConsultaTabela.add(scrollTabela);

        // Monta a aba "Consulta"
        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneDadosConsulta.addTab("Consulta", tabConsulta);
        paneCentro.add(paneDadosConsulta);

        // Actions dos botões de consulta/limpar
        btnConsultar.addActionListener(e -> consultar());
        btnLimpar.addActionListener(e -> {
            edtId1.setText("");
            edtId2.setText("");
            edtProNomeFiltro.setText("");
            edtProPrecoFiltro.setText("");
            edtProUnidadeFiltro.setText("");
            edtProMinFiltro.setText("");
            edtProMaxFiltro.setText("");
        });
    }


    private void posicionar() {
        // Cabeçalho
        paneCabecario.setBounds(10, 10, 970, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);
        btnPrimeiro.setBounds(  0, 7, 90, 25);
        btnAnterior.setBounds( 95, 7, 90, 25);
        btnProximo .setBounds(190, 7, 90, 25);
        btnUltimo  .setBounds(285, 7, 90, 25);
        btnNovo    .setBounds(430, 7, 90, 25);
        btnAlterar .setBounds(520, 7, 90, 25);
        btnExcluir .setBounds(610, 7, 90, 25);
        btnGravar  .setBounds(835, 7, 90, 25);

        // Centro
        paneCentro.setBounds(10, 60, 970, 770);
        lblTitulo.setBounds(0, 0, 780, 30);

        // Abas
        paneDadosCadastro.setBounds(10, 40, 960, 240);
        paneDadosConsulta.setBounds(10, 290, 960, 470);

        // ===== Campos da aba "Dados do Produto" =====

        lblProCodigo.setBounds(10, 15, 60, 25);    edtProCodigo.setBounds(75, 15, 100, 25);
        lblProNome.setBounds(190, 15, 60, 25);     edtProNome.setBounds(250, 15, 400, 25);
        lblProCadastro.setBounds(660, 15, 70, 25); edtProCadastro.setBounds(730, 15, 200, 25);

        lblProEstoque.setBounds(10, 50, 60, 25);   edtProEstoque.setBounds(75, 50, 100, 25);
        lblProUnidade.setBounds(190, 50, 60, 25);  cbxUnidade.setBounds(250, 50, 100, 25);
        lblProPeso.setBounds(360, 50, 50, 25);     edtProPeso.setBounds(410, 50, 80, 25);
        lblAtivo.setBounds(505, 50, 45, 25);       chkAtivo.setBounds(555, 50, 25, 25);

        lblProPreco.setBounds(10, 85, 60, 25);     edtProPreco.setBounds(75, 85, 100, 25);
        lblProCusto.setBounds(190, 85, 60, 25);    edtProCusto.setBounds(250, 85, 100, 25);
        lblProAtacado.setBounds(360, 85, 70, 25);  edtProAtacado.setBounds(430, 85, 100, 25);
        lblProEmbalagem.setBounds(540, 85, 80, 25);edtProEmbalagem.setBounds(625, 85, 80, 25);
        lblProObs.setBounds(720, 85, 40, 25);      edtProObs.setBounds(760, 85, 170, 25);

        lblProMin.setBounds(10, 120, 60, 25);      edtProMin.setBounds(75, 120, 100, 25);
        lblProMax.setBounds(190, 120, 60, 25);     edtProMax.setBounds(250, 120, 100, 25);

        lblProNome.setLabelFor(edtProNome); // acessibilidade (apenas exemplo)

        // ===== Aba "Consulta" =====
        tabConsulta.setBounds(0, 0, 960, 470);

        // Filtros (topo)
        paneConsultaDados.setBounds(10, 10, 930, 100);

        lblId1.setBounds(10, 10, 20, 25);           edtId1.setBounds(35, 10, 80, 25);
        lblText.setBounds(120, 10, 10, 25);         edtId2.setBounds(135, 10, 80, 25);

        lblProNomeFiltro.setBounds(230, 10, 45, 25);   edtProNomeFiltro.setBounds(280, 10, 160, 25);
        lblProUnidadeFiltro.setBounds(450, 10, 60, 25);edtProUnidadeFiltro.setBounds(515, 10, 80, 25);
        lblProPrecoFiltro.setBounds(605, 10, 45, 25);  edtProPrecoFiltro.setBounds(655, 10, 80, 25);

        lblProMinFiltro.setBounds(745, 10, 65, 25);    edtProMinFiltro.setBounds(810, 10, 60, 25);
        lblProMaxFiltro.setBounds(745, 45, 65, 25);    edtProMaxFiltro.setBounds(810, 45, 60, 25);


        btnConsultar.setBounds(280, 45, 90, 25);
        btnLimpar.setBounds(375, 45, 90, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 120, 930, 330);
        scrollTabela.setBounds(0, 0, 930, 330);
    }


    private void configurarAcoes() {
        btnPrimeiro.addActionListener(e -> {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Produtos Cadastrados !");
                return;
            }
            mostrarRegistro(0);
        });

        btnAnterior.addActionListener(e -> {
            int viewSel = tabela.getSelectedRow();
            int sel = (viewSel < 0 ? 0 : tabela.convertRowIndexToModel(viewSel)) - 1;
            mostrarRegistro(sel);
        });

        btnProximo.addActionListener(e -> {
            int viewSel = tabela.getSelectedRow();
            int sel = (viewSel < 0 ? -1 : tabela.convertRowIndexToModel(viewSel)) + 1;
            mostrarRegistro(sel);
        });

        btnUltimo.addActionListener(e -> {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Produtos Cadastrados !");
                return;
            }
            mostrarRegistro(lista.size() - 1);
        });

        btnNovo.addActionListener(e -> {
            limparCampos();
            setOperacao("incluir");
            chkAtivo.setSelected(true);
            edtProNome.requestFocusInWindow();
        });

        btnAlterar.addActionListener(e -> setOperacao("alterar"));

        btnGravar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Gravação deste Produto ?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ProdutoModel p = montarProdutoDosCampos();
                    ProdutoController ctrl = new ProdutoController();
                    ctrl.gravar(getOperacao(), p);
                    JOptionPane.showMessageDialog(this, "Dados Gravados com Sucesso");
                    consultar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Gravação \n" + ex.getMessage());
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            setOperacao("");
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Exclusão deste Registro ?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ProdutoModel p = montarProdutoDosCampos();
                    ProdutoController ctrl = new ProdutoController();
                    ctrl.excluir(p);
                    JOptionPane.showMessageDialog(this, "Registro Excluído com Sucesso");
                    consultar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Exclusão de Registro \n" + ex.getMessage());
                }
            }
        });
    }


    private String getOperacao() {
        return operacao;
    }

    private void setOperacao(String operacao) {
        this.operacao = operacao;
        boolean ativar = !operacao.isEmpty();
        btnGravar.setEnabled(ativar);
    }


    private void limparCampos() {
        edtProCodigo.setText("0");
        edtProNome.setText("");
        edtProEstoque.setText("");
        edtProPreco.setText("");
        edtProCusto.setText("");
        edtProAtacado.setText("");
        edtProMin.setText("");
        edtProMax.setText("");
        edtProEmbalagem.setText("");
        edtProPeso.setText("");
        edtProCadastro.setText(""); // formato esperado: yyyy-MM-dd
        edtProObs.setText("");
        cbxUnidade.setSelectedIndex(0);
        chkAtivo.setSelected(false); // mapeia para "N"
    }



    private void mostrar(ProdutoModel p) {
        edtProCodigo.setText(String.valueOf(p.getPRO_CODIGO()));
        edtProNome.setText(p.getPRO_NOME());
        edtProEstoque.setText(fmtBig(p.getPRO_ESTOQUE()));
        edtProPreco.setText(fmtBig(p.getPRO_PRECO()));
        edtProCusto.setText(fmtBig(p.getPRO_CUSTO()));
        edtProAtacado.setText(fmtBig(p.getPRO_ATACADO()));
        edtProMin.setText(fmtBig(p.getPRO_MIN()));
        edtProMax.setText(fmtBig(p.getPRO_MAX()));
        edtProEmbalagem.setText(fmtBig(p.getPRO_EMBALAGEM()));
        edtProPeso.setText(fmtBig(p.getPRO_PESO()));
        edtProCadastro.setText(fmtDate(p.getPRO_CADASTRO()));
        edtProObs.setText(p.getPRO_OBS());
        cbxUnidade.setSelectedItem(p.getPRO_UNIDADE());

        // PRO_ATIVO é String (ex.: "S" / "N")
        String ativo = p.getPRO_ATIVO();
        chkAtivo.setSelected(ativo != null && ativo.trim().equalsIgnoreCase("S"));
    }


    private ProdutoModel montarProdutoDosCampos() {
        ProdutoModel p = new ProdutoModel();

        // Código
        try {
            p.setPRO_CODIGO(Integer.parseInt(edtProCodigo.getText().trim()));
        } catch (NumberFormatException e) {
            System.out.println("\nERRO ao converter PRO_CODIGO: " + e.getMessage());
            p.setPRO_CODIGO(0);
        }

        // Strings
        p.setPRO_NOME(edtProNome.getText().trim());
        p.setPRO_UNIDADE((String) cbxUnidade.getSelectedItem());
        p.setPRO_OBS(edtProObs.getText().trim());

        // BigDecimals
        p.setPRO_ESTOQUE(parseBig(edtProEstoque.getText()));
        p.setPRO_PRECO(parseBig(edtProPreco.getText()));
        p.setPRO_CUSTO(parseBig(edtProCusto.getText()));
        p.setPRO_ATACADO(parseBig(edtProAtacado.getText()));
        p.setPRO_MIN(parseBig(edtProMin.getText()));
        p.setPRO_MAX(parseBig(edtProMax.getText()));
        p.setPRO_EMBALAGEM(parseBig(edtProEmbalagem.getText()));
        p.setPRO_PESO(parseBig(edtProPeso.getText()));

        // Data (yyyy-MM-dd)
        p.setPRO_CADASTRO(parseDate(edtProCadastro.getText()));

        // Ativo -> "S" / "N"
        p.setPRO_ATIVO(chkAtivo.isSelected() ? "S" : "N");

        return p;
    }


    private String filtroConsulta() {
        String cond = "";

        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(PRO_CODIGO >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_CODIGO <= " + edtId2.getText().trim() + ")";
        }
        if (!edtProNomeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_NOME LIKE ('%" + edtProNomeFiltro.getText().trim() + "%'))";
        }
        if (!edtProPrecoFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_PRECO = " + edtProPrecoFiltro.getText().trim().replace(",", ".") + ")";
        }
        if (!edtProUnidadeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_UNIDADE LIKE ('%" + edtProUnidadeFiltro.getText().trim() + "%'))";
        }
        if (!edtProMinFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_ESTOQUE >= " + edtProMinFiltro.getText().trim().replace(",", ".") + ")";
        }
        if (!edtProMaxFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(PRO_ESTOQUE <= " + edtProMaxFiltro.getText().trim().replace(",", ".") + ")";
        }
        return cond;
    }


    private void consultar() {
        try {
            String cond = filtroConsulta();
            ProdutoController ctrl = new ProdutoController();
            lista = ctrl.consultar(cond); // retorna ArrayList<ProdutoModel>
            if (lista == null) lista = new ArrayList<>();

            tableModel = new ProdutoTableModel(lista, colunas);
            tabela.setModel(tableModel);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            if (!lista.isEmpty()) {
                mostrarRegistro(0);
            } else {
                JOptionPane.showMessageDialog(this, "Não Existem Produtos Cadastrados !");
                limparCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na Consulta de Produto \n" + ex.getMessage());
        }
    }


    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }
    
    
    
    // ===== Utils para BigDecimal e Data =====
    private static java.math.BigDecimal parseBig(String s) {
        try {
            if (s == null) return java.math.BigDecimal.ZERO;
            String t = s.trim().replace(",", ".");
            if (t.isEmpty()) return java.math.BigDecimal.ZERO;
            return new java.math.BigDecimal(t);
        } catch (Exception e) {
            System.out.println("Falha ao converter BigDecimal: '" + s + "' -> " + e.getMessage());
            return java.math.BigDecimal.ZERO;
        }
    }

    private static String fmtBig(java.math.BigDecimal v) {
        if (v == null) return "";
        // Exibe com escala natural (sem forçar casas), usando ponto; se preferir vírgula visual, troque aqui:
        String s = v.stripTrailingZeros().toPlainString();
        return s;
    }

    private static final java.time.format.DateTimeFormatter DF = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static java.time.LocalDate parseDate(String s) {
        try {
            if (s == null || s.trim().isEmpty()) return null;
            return java.time.LocalDate.parse(s.trim(), DF);
        } catch (Exception e) {
            System.out.println("Falha ao converter LocalDate: '" + s + "' -> " + e.getMessage());
            return null;
        }
    }

    private static String fmtDate(java.time.LocalDate d) {
        return d == null ? "" : d.format(DF);
    }



}
