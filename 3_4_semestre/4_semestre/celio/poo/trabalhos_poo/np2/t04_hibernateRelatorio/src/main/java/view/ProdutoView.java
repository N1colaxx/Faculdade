package view;

import controller.ProdutoController;
import model.ProdutoModel;
import tableModel.ProdutoTableModel;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.util.ArrayList;

public class ProdutoView extends JPanel {
    
    private ProdutoController ctrl;

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnImprimir, btnGravar;
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
    private ArrayList<ProdutoModel> lista = new ArrayList<>();

    //  construtor: configura layout base e liga tudo
    public ProdutoView() {
        System.out.println(" [ProdutoView] entrou");
        
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();
        
        ctrl = new ProdutoController();
    }

    //  instancia todos os componentes visuais (labels, inputs, painéis, tabela)
    private void instanciar() {
        
        // Botões topo
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo  = new JButton("Próximo");
        btnUltimo   = new JButton("Último");
        btnNovo     = new JButton("Novo");
        btnAlterar  = new JButton("Alterar");
        btnExcluir  = new JButton("Excluir");
        btnImprimir   = new JButton("Imprimir");
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
        tableModel = new ProdutoTableModel(lista);
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

        for (JPanel p : new JPanel[]{tabDadosProduto, tabConsulta, paneConsultaDados, paneConsultaTabela, paneCabecario}) {;
            p.setBackground(new Color(245, 250, 255));
        }
    }

    //  adiciona todos os componentes aos painéis/abas e ao JPanel principal
    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro); paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);  paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);     paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);  paneCabecario.add(btnImprimir);
        paneCabecario.add(btnGravar);
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

        // Botões de consulta/limpa
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

    }

    //  define posições e tamanhos (bounds) para caber em 1500 x 850
    private void posicionar() {
        // Cabeçalho
        paneCabecario.setBounds(10, 10, 1470, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);
        btnPrimeiro.setBounds(  0, 7, 100, 25);
        btnAnterior.setBounds(105, 7, 100, 25);
        btnProximo .setBounds(210, 7, 100, 25);
        btnUltimo  .setBounds(315, 7, 100, 25);
        btnNovo    .setBounds(520, 7, 100, 25);
        btnAlterar .setBounds(625, 7, 100, 25);
        btnExcluir .setBounds(730, 7, 100, 25);
        btnImprimir  .setBounds(1200, 7, 120, 25);
        btnGravar  .setBounds(1320, 7, 120, 25);

        // Centro
        paneCentro.setBounds(10, 60, 1470, 770);
        lblTitulo.setBounds(0, 0, 1200, 30);

        // Abas
        paneDadosCadastro.setBounds(10, 40, 1470, 260);
        paneDadosConsulta.setBounds(10, 310, 1470, 460);

        // ===== Campos da aba "Dados do Produto" =====
        lblProCodigo.setBounds(10, 15, 60, 25);    edtProCodigo.setBounds(75, 15, 100, 25);
        lblProNome.setBounds(190, 15, 60, 25);     edtProNome.setBounds(250, 15, 520, 25);
        lblProCadastro.setBounds(790, 15, 70, 25); edtProCadastro.setBounds(865, 15, 200, 25);

        lblProEstoque.setBounds(10, 50, 60, 25);   edtProEstoque.setBounds(75, 50, 100, 25);
        lblProUnidade.setBounds(190, 50, 60, 25);  cbxUnidade.setBounds(250, 50, 100, 25);
        lblProPeso.setBounds(360, 50, 50, 25);     edtProPeso.setBounds(410, 50, 80, 25);
        lblAtivo.setBounds(505, 50, 45, 25);       chkAtivo.setBounds(555, 50, 25, 25);

        lblProPreco.setBounds(10, 85, 60, 25);     edtProPreco.setBounds(75, 85, 100, 25);
        lblProCusto.setBounds(190, 85, 60, 25);    edtProCusto.setBounds(250, 85, 100, 25);
        lblProAtacado.setBounds(360, 85, 70, 25);  edtProAtacado.setBounds(430, 85, 100, 25);
        lblProEmbalagem.setBounds(540, 85, 80, 25);edtProEmbalagem.setBounds(625, 85, 100, 25);
        lblProObs.setBounds(740, 85, 40, 25);      edtProObs.setBounds(785, 85, 280, 25);

        lblProMin.setBounds(10, 120, 60, 25);      edtProMin.setBounds(75, 120, 100, 25);
        lblProMax.setBounds(190, 120, 60, 25);     edtProMax.setBounds(250, 120, 100, 25);

        // ===== Aba "Consulta" =====
        tabConsulta.setBounds(0, 0, 1470, 460);

        // Filtros (topo)
        paneConsultaDados.setBounds(10, 10, 1440, 110);

        lblId1.setBounds(10, 10, 20, 25);           edtId1.setBounds(35, 10, 100, 25);
        lblText.setBounds(140, 10, 10, 25);         edtId2.setBounds(155, 10, 100, 25);

        lblProNomeFiltro.setBounds(270, 10, 45, 25);   edtProNomeFiltro.setBounds(320, 10, 220, 25);
        lblProUnidadeFiltro.setBounds(550, 10, 60, 25);edtProUnidadeFiltro.setBounds(615, 10, 80, 25);
        lblProPrecoFiltro.setBounds(710, 10, 45, 25);  edtProPrecoFiltro.setBounds(760, 10, 100, 25);

        lblProMinFiltro.setBounds(870, 10, 65, 25);    edtProMinFiltro.setBounds(935, 10, 80, 25);
        lblProMaxFiltro.setBounds(1025, 10, 65, 25);   edtProMaxFiltro.setBounds(1090, 10, 80, 25);

        btnConsultar.setBounds(1185, 10, 100, 25);
        btnLimpar.setBounds(1295, 10, 100, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 110, 1440, 310);
        scrollTabela.setBounds(0, 0, 1440, 310);
    }

    //  registra listeners dos botões (primeiro, anterior, próximo, etc.)
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
            edtProCadastro.setText(java.time.LocalDate.now().toString()); // yyyy-MM-dd
            setOperacao("incluir");
            chkAtivo.setSelected(true);
            edtProNome.requestFocusInWindow();
        });

        btnAlterar.addActionListener(e -> setOperacao("alterar"));
        
        btnImprimir.addActionListener(e -> {
            Exception retorno = ctrl.imprimir();
            if(retorno != null) {
                JOptionPane.showMessageDialog(null, "Erro no Relatório de Produto /n" + retorno.getMessage());     
            }
        });
        
        btnGravar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Gravação deste Produto ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ProdutoModel p = montarProdutoDosCampos();
                    ctrl.gravar(p, getOperacao());
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
                    "Confirma Exclusão deste Registro ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ProdutoModel p = montarProdutoDosCampos();
                    ctrl.excluir(p);
                    JOptionPane.showMessageDialog(this, "Registro Excluído com Sucesso");
                    consultar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Exclusão de Registro \n" + ex.getMessage());
                }
            }
        });
        
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

    //  retorna a operação atual (incluir/alterar/""), usada pelo controller
    private String getOperacao() {
        return operacao;
    }

    //  define a operação atual e habilita/desabilita o botão Gravar
    private void setOperacao(String operacao) {
        this.operacao = operacao;
        boolean ativar = !operacao.isEmpty();
        btnGravar.setEnabled(ativar);
    }

    //  limpa todos os campos do formulário de cadastro
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
        edtProEstoque.setText(fmtDou(p.getPRO_ESTOQUE()));
        edtProPreco.setText(fmtDou(p.getPRO_PRECO()));
        edtProCusto.setText(fmtDou(p.getPRO_CUSTO()));
        edtProAtacado.setText(fmtDou(p.getPRO_ATACADO()));
        edtProMin.setText(fmtDou(p.getPRO_MIN()));
        edtProMax.setText(fmtDou(p.getPRO_MAX()));
        edtProEmbalagem.setText(fmtDou(p.getPRO_EMBALAGEM()));
        edtProPeso.setText(fmtDou(p.getPRO_PESO()));
        edtProCadastro.setText(fmtDate(p.getPRO_CADASTRO()));
        edtProObs.setText(p.getPRO_OBS());
        cbxUnidade.setSelectedItem(p.getPRO_UNIDADE());
        chkAtivo.setSelected(p.getPRO_ATIVO().equals("1"));
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
        p.setPRO_ESTOQUE(parseDou(edtProEstoque.getText()));
        p.setPRO_PRECO(parseDou(edtProPreco.getText()));
        p.setPRO_CUSTO(parseDou(edtProCusto.getText()));
        p.setPRO_ATACADO(parseDou(edtProAtacado.getText()));
        p.setPRO_MIN(parseDou(edtProMin.getText()));
        p.setPRO_MAX(parseDou(edtProMax.getText()));
        p.setPRO_EMBALAGEM(parseDou(edtProEmbalagem.getText()));
        p.setPRO_PESO(parseDou(edtProPeso.getText()));

        // Data (yyyy-MM-dd)
        p.setPRO_CADASTRO(parseDate(edtProCadastro.getText()));


        p.setPRO_ATIVO(chkAtivo.isSelected() ? "1" : "0");

        return p;
    }

    private String filtroConsulta() {
        String cond = "";

        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(p.pro_codigo >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_codigo <= " + edtId2.getText().trim() + ")";
        }
        if (!edtProNomeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_nome LIKE ('%" + edtProNomeFiltro.getText().trim() + "%'))";
        }
        if (!edtProPrecoFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_preco = " + edtProPrecoFiltro.getText().trim().replace(",", ".") + ")";
        }
        if (!edtProUnidadeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_unidade LIKE ('%" + edtProUnidadeFiltro.getText().trim() + "%'))";
        }
        if (!edtProMinFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_estoque >= " + edtProMinFiltro.getText().trim().replace(",", ".") + ")";
        }
        if (!edtProMaxFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pro_estoque <= " + edtProMaxFiltro.getText().trim().replace(",", ".") + ")";
        }
        return cond;
    }

    private void consultar() {
        try {
            String cond = filtroConsulta();
            
            ctrl = new ProdutoController();
            lista = ctrl.consultar(cond); // retorna ArrayList<ProdutoModel>
            if (lista == null) lista = new ArrayList<>();

            tableModel = new ProdutoTableModel(lista);
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

    //  seleciona e mostra um registro específico da lista/tabela
    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }

    // ===== Utils para Double e Data =====

    // Converte String para Double de modo seguro (retorna 0.0 em erro)
    private static Double parseDou(String s) {
        try {
            if (s == null) return 0.0;
            String t = s.trim().replace(",", ".");
            if (t.isEmpty()) return 0.0;
            return Double.parseDouble(t);
        } catch (Exception e) {
            System.out.println("Falha ao converter Double: '" + s + "' -> " + e.getMessage());
            return 0.0;
        }
    }

    // Formata Double para exibição simples (sem casas desnecessárias)
    private static String fmtDou(Double v) {
        if (v == null) return "0.0";
        if (v == v.longValue()) {
            // Se for número inteiro, remove casas decimais
            return String.format("%d", v.longValue());
        } else {
            // Se tiver casas decimais, mostra no formato normal
            return String.format("%s", v);
        }
    }


    //  converte String (yyyy-MM-dd) para LocalDate (seguro)
    private static final java.time.format.DateTimeFormatter DF =
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //  faz o parse da data do campo (retorna null se inválida)
    private static java.time.LocalDate parseDate(String s) {
        try {
            if (s == null || s.trim().isEmpty()) return null;
            return java.time.LocalDate.parse(s.trim(), DF);
        } catch (Exception e) {
            System.out.println("Falha ao converter LocalDate: '" + s + "' -> " + e.getMessage());
            return null;
        }
    }

    //  formata LocalDate para String yyyy-MM-dd
    private static String fmtDate(java.time.LocalDate d) {
        return d == null ? "" : d.format(DF);
    }
}
