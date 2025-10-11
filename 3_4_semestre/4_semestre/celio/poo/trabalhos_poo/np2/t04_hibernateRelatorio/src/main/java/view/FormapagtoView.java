package view;

import controller.FormapagtoController;
import model.FormapagtoModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.util.ArrayList;

public class FormapagtoView extends JPanel {
    
    private FormapagtoController ctrl;
    
    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnImprimir, btnGravar;
    private JButton btnConsultar, btnLimpar;

    // Campos – Dados do Cadastro
    private JLabel lblCodigo, lblNome, lblAtivo;
    private JTextField edtCodigo, edtNome;
    private JCheckBox chkAtivo;

    // Consulta (filtros)
    private JLabel lblId1, lblText, lblNomeFiltro;
    private JTextField edtId1, edtId2, edtNomeFiltro;

    // Painéis
    private JPanel paneCabecario;      // botões
    private JPanel paneCentro;         // título + abas
    private JTabbedPane paneDados;     // aba "Dados"
    private JTabbedPane paneConsulta;  // aba "Consulta" (única)
    private JPanel tabDados;           // conteúdo da aba "Dados"
    private JPanel tabConsulta;        // conteúdo da aba "Consulta"
    private JPanel paneConsultaDados;  // filtros
    private JPanel paneConsultaTabela; // tabela

    // Título
    private JLabel lblTitulo;

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;
    private FormapagtoTableModel tableModel;

    // Estado / dados
    private String operacao = "";
    private ArrayList<FormapagtoModel> lista = new ArrayList<>();

    public FormapagtoView() {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1000, 850));

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
        btnImprimir = new JButton("Imprimir");
        btnGravar   = new JButton("Gravar");

        // Título
        lblTitulo = new JLabel("Cadastro de Formas de Pagamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30,30,120));

        // Dados
        lblCodigo = new JLabel("Código:");
        lblNome   = new JLabel("Nome:");
        lblAtivo  = new JLabel("Ativo:");

        edtCodigo = new JTextField();
        edtNome   = new JTextField();
        chkAtivo  = new JCheckBox();
        chkAtivo.setBackground(new Color(245,250,255));

        // Consulta (filtros)
        lblId1        = new JLabel("ID");
        lblText       = new JLabel("à");
        lblNomeFiltro = new JLabel("Nome");

        edtId1        = new JTextField();
        edtId2        = new JTextField();
        edtNomeFiltro = new JTextField();

        btnConsultar  = new JButton("Consulta");
        btnLimpar     = new JButton("Limpa");

        // Tabela
        tableModel = new FormapagtoTableModel(lista);
        tabela = new JTable(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabela = new JScrollPane(tabela);

        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int viewSel = tabela.getSelectedRow();
            if (viewSel >= 0 && viewSel < lista.size()) {
                int sel = tabela.convertRowIndexToModel(viewSel);
                mostrar(lista.get(sel));
            }
        });

        // Contêineres
        paneCabecario      = new JPanel(null);
        paneCentro         = new JPanel(null);
        paneCentro.setBackground(new Color(245,250,255));
        paneDados          = new JTabbedPane();
        paneConsulta       = new JTabbedPane();
        tabDados           = new JPanel(null);
        tabConsulta        = new JPanel(null);
        paneConsultaDados  = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        for (JPanel p : new JPanel[]{tabDados, tabConsulta, paneConsultaDados, paneConsultaTabela, paneCabecario})
            p.setBackground(new Color(245,250,255));
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

        // Abas: Dados
        tabDados.add(lblCodigo); tabDados.add(edtCodigo);
        tabDados.add(lblNome);   tabDados.add(edtNome);
        tabDados.add(lblAtivo);  tabDados.add(chkAtivo);
        paneDados.addTab("Dados", tabDados);
        paneCentro.add(paneDados);

        // Abas: Consulta
        paneConsultaDados.add(lblId1);        paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);       paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblNomeFiltro); paneConsultaDados.add(edtNomeFiltro);
        paneConsultaDados.add(btnConsultar);  paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollTabela);

        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneConsulta.addTab("Consulta", tabConsulta);
        paneCentro.add(paneConsulta);
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
        btnImprimir  .setBounds(845, 7, 90, 25);
        btnGravar  .setBounds(835, 7, 90, 25);

        // Centro
        paneCentro.setBounds(10, 60, 970, 770);
        lblTitulo.setBounds(0, 0, 780, 30);

        // Abas
        paneDados.setBounds(10, 40, 960, 140);
        paneConsulta.setBounds(10, 190, 960, 470);

        // ===== Campos da aba "Dados" =====
        lblCodigo.setBounds(10, 15, 60, 25);   edtCodigo.setBounds(75, 15, 100, 25);
        lblNome.setBounds(190, 15, 60, 25);    edtNome.setBounds(250, 15, 300, 25);
        lblAtivo.setBounds(560, 15, 60, 25);   chkAtivo.setBounds(615, 15, 25, 25);

        // ===== Aba "Consulta" =====
        tabConsulta.setBounds(0, 0, 960, 470);

        // Filtros
        paneConsultaDados.setBounds(10, 10, 930, 100);

        lblId1.setBounds(10, 10, 20, 25);         edtId1.setBounds(35, 10, 80, 25);
        lblText.setBounds(120, 10, 10, 25);       edtId2.setBounds(135, 10, 80, 25);
        lblNomeFiltro.setBounds(230, 10, 45, 25); edtNomeFiltro.setBounds(280, 10, 200, 25);

        btnConsultar.setBounds(500, 10, 90, 25);
        btnLimpar.setBounds(595, 10, 90, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 120, 930, 330);
        scrollTabela.setBounds(0, 0, 930, 330);
    }

    private void configurarAcoes() {
        
        btnPrimeiro.addActionListener(e -> {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existe Formas de Pagamento Cadastradas !");
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
                JOptionPane.showMessageDialog(this, "Não Existe Formas de Pagamento Cadastradas !");
                return;
            }
            mostrarRegistro(lista.size() - 1);
        });
        
        btnNovo.addActionListener(e -> {
            limparCampos();
            setOperacao("incluir");
            chkAtivo.setSelected(true);
            edtNome.requestFocusInWindow();
        });

        btnAlterar.addActionListener(e -> setOperacao("alterar"));

        btnImprimir.addActionListener(e -> {
            Exception retorno = ctrl.imprimir();
            if(retorno != null) {
                JOptionPane.showMessageDialog(null, "Erro no Relatório de Forma de Pagamento /n" + retorno.getMessage());     
            }
        });
        
        btnGravar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Gravação desta Forma de Pagamento ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    FormapagtoModel f = montarFormapagtoDosCampos();
                    ctrl = new FormapagtoController();
                    ctrl.gravar(f, getOperacao());
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
                    "Confirma Exclusão desta Forma de Pagamento ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    FormapagtoModel f = montarFormapagtoDosCampos();
                    ctrl = new FormapagtoController();
                    ctrl.excluir(f);
                    JOptionPane.showMessageDialog(this, "Registro Excluído com Sucesso");
                    consultar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Exclusão \n" + ex.getMessage());
                }
            }
        });

        btnConsultar.addActionListener(e -> consultar());
        
        btnLimpar.addActionListener(e -> {
            edtId1.setText("");
            edtId2.setText("");
            edtNomeFiltro.setText("");
        });
    }

    private String getOperacao() { return operacao; }

    private void setOperacao(String operacao) {
        this.operacao = operacao;
        btnGravar.setEnabled(!operacao.isEmpty());
    }

    private void limparCampos() {
        edtCodigo.setText("0");
        edtNome.setText("");
        chkAtivo.setSelected(false);
    }

    //  esse metodo é para: preencher os campos da tela com os dados do objeto selecionado.
    private void mostrar(FormapagtoModel f) {
        edtCodigo.setText(String.valueOf(f.getFPG_CODIGO()));
        edtNome.setText(f.getFPG_NOME());
        chkAtivo.setSelected(f.getFPG_ATIVO() == 1);
    }

    private FormapagtoModel montarFormapagtoDosCampos() {
        FormapagtoModel f = new FormapagtoModel();
        int cod = 0;
        try { 
            cod = Integer.parseInt(edtCodigo.getText().trim()); 
        } catch (NumberFormatException ignored) {
        }
        f.setFPG_CODIGO(cod);
        f.setFPG_NOME(edtNome.getText().trim());
        f.setFPG_ATIVO(chkAtivo.isSelected() ? 1 : 0);
        return f;
    }

    private String filtroConsulta() {
        String cond = "";
        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(fpg_codigo >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(fpg_codigo <= " + edtId2.getText().trim() + ")";
        }
        if (!edtNomeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(fpg_nome LIKE ('%" + edtNomeFiltro.getText().trim() + "%'))";
        }
        return cond;
    }

    //  esse metodo é para: consultar o banco via controller e carregar a tabela; também posiciona o primeiro registro.
    private void consultar() {
        try {
            String cond = filtroConsulta();
            ctrl = new FormapagtoController();
            lista = ctrl.consultar(cond);
            if (lista == null) lista = new ArrayList<>();

            tableModel = new FormapagtoTableModel(lista);
            tabela.setModel(tableModel);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            if (!lista.isEmpty()) {
                mostrarRegistro(0);
            } else {
                JOptionPane.showMessageDialog(this, "Não Existem Formas de Pagamento Cadastradas !");
                limparCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na Consulta \n" + ex.getMessage());
        }
    }

    //  esse metodo é para: exibir um registro específico (por índice) e selecionar a linha correspondente na tabela.
    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }
}
