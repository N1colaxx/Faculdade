package view;

import controller.FornecedorController;
import model.FornecedorModel;
import util.FornecedorTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FornecedorView extends JPanel {

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;
    private JButton btnConsultar, btnLimpar;

    // Campos – Dados do Cadastro (Pessoa + Fornecedor)
    private JLabel
            lblForCodigo, lblNome, lblFantasia, lblPesFisica, lblCPFCNPJ, lblRgie, lblDataCadastro,
            lblEndereco, lblNumero, lblComplemento, lblBairro, lblCidade, lblUF, lblCEP,
            lblFone1, lblFone2, lblCelular, lblSite, lblEmail, lblAtivo, lblContato;

    private JTextField
            edtForCodigo, edtNome, edtFantasia, edtCPFCNPJ, edtRgie, edtDataCadastro,
            edtEndereco, edtNumero, edtComplemento, edtBairro, edtCidade, edtCEP,
            edtFone1, edtFone2, edtCelular, edtSite, edtEmail, edtContato;

    private JCheckBox chkPesFisica, chkAtivo; // mapear para 'F'/'J' e 'S'/'N'
    private JComboBox<String> cbUF;

    // Consulta (filtros)
    private JLabel lblId1, lblText, lblId2, lblNomeFiltro, lblContatoFiltro, lblUFFiltro;
    private JTextField edtId1, edtId2, edtNomeFiltro, edtContatoFiltro;
    private JComboBox<String> cbUFFiltro;

    // Painéis
    private JPanel paneCabecario;      // botões
    private JPanel paneCentro;         // título + abas
    private JTabbedPane paneDadosForn; // aba "Dados do Fornecedor"
    private JTabbedPane paneConsulta;  // aba "Consulta"
    private JPanel tabDadosForn;       // conteúdo da aba "Dados do Fornecedor"
    private JPanel tabConsulta;        // conteúdo da aba "Consulta"
    private JPanel paneConsultaDados;  // filtros
    private JPanel paneConsultaTabela; // tabela

    // Título
    private JLabel lblTitulo;

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;
    private FornecedorTableModel tableModel;

    // Estado / dados
    private String operacao = "";
    private final String[] colunas = {"Código", "Nome", "CPF/CNPJ", "Contato", "Ativo"};
    private ArrayList<FornecedorModel> lista = new ArrayList<>();

    // Utils data
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //  esse metodo é para: configurar o painel principal, definir tamanho/preferências e disparar a construção da UI.
    public FornecedorView() {
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1500, 850));

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();
    }

    //  esse metodo é para: instanciar todos os componentes visuais (labels, campos, combos, tabela, painéis e botões).
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
        lblTitulo = new JLabel("Cadastro de Fornecedores", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30,30,120));

        // Dados do Fornecedor (Pessoa + Fornecedor)
        lblForCodigo     = new JLabel("Código:");   edtForCodigo     = new JTextField();
        lblNome          = new JLabel("Nome:");     edtNome          = new JTextField(30);
        lblFantasia      = new JLabel("Nome Fantasia:"); edtFantasia      = new JTextField(30);
        
        lblPesFisica     = new JLabel("Pessoa Física?");    
        chkPesFisica     = new JCheckBox();  
        chkPesFisica.setBackground(new Color(245,250,255));
        
        lblCPFCNPJ       = new JLabel("CPF/CNPJ:"); edtCPFCNPJ       = new JTextField(20);
        lblRgie          = new JLabel("RG/IE:");    edtRgie          = new JTextField(20);
        
        lblDataCadastro  = new JLabel("Cadastro (yyyy-MM-dd):");
        edtDataCadastro  = new JTextField(12);
        edtDataCadastro.setText(LocalDate.now().toString()); // yyyy-MM-dd

        lblEndereco      = new JLabel("Endereço:");     edtEndereco      = new JTextField(30);
        lblNumero        = new JLabel("Número:");       edtNumero        = new JTextField(8);
        lblComplemento   = new JLabel("Complemento:");  edtComplemento   = new JTextField(20);
        lblBairro        = new JLabel("Bairro:");       edtBairro        = new JTextField(20);
        lblCidade        = new JLabel("Cidade:");       edtCidade        = new JTextField(20);
        
        lblUF            = new JLabel("UF:");
        cbUF             = new JComboBox<>(new String[]{
                "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
                "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
        });

        lblCEP           = new JLabel("CEP:");      edtCEP           = new JTextField(10);
        lblFone1         = new JLabel("Fone 1:");   edtFone1         = new JTextField(16);
        lblFone2         = new JLabel("Fone 2:");   edtFone2         = new JTextField(16);
        lblCelular       = new JLabel("Celular:");  edtCelular       = new JTextField(16);
        lblSite          = new JLabel("Site:");     edtSite          = new JTextField(30);
        lblEmail         = new JLabel("E-mail:");   edtEmail         = new JTextField(30);
        
        lblAtivo         = new JLabel("Ativo:");
        chkAtivo         = new JCheckBox(); 
        chkAtivo.setBackground(new Color(245,250,255));
        
        lblContato       = new JLabel("Contato:");edtContato       = new JTextField(30);
        // Consulta (filtros)
        lblId1          = new JLabel("ID");
        lblText         = new JLabel("à");
        lblId2          = new JLabel(); // placeholder
        lblNomeFiltro   = new JLabel("Nome");
        lblContatoFiltro= new JLabel("Contato");
        lblUFFiltro     = new JLabel("UF");

        edtId1          = new JTextField();
        edtId2          = new JTextField();
        edtNomeFiltro   = new JTextField();
        edtContatoFiltro= new JTextField();

        cbUFFiltro      = new JComboBox<>(new String[]{"", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
                "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"});

        btnConsultar    = new JButton("Consulta");
        btnLimpar       = new JButton("Limpa");

        // Tabela
        tableModel = new FornecedorTableModel(lista, colunas);
        tabela = new JTable(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabela = new JScrollPane(tabela);

        tabela.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getValueIsAdjusting()) return;
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
        paneDadosForn      = new JTabbedPane();
        paneConsulta       = new JTabbedPane();
        tabDadosForn       = new JPanel(null);
        tabConsulta        = new JPanel(null);
        paneConsultaDados  = new JPanel(null);
        paneConsultaTabela = new JPanel(null);
    }

    //  esse metodo é para: adicionar os componentes instanciados aos painéis e montar as abas de dados e consulta.
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

        // Aba: Dados do Fornecedor
        tabDadosForn.add(lblForCodigo);     tabDadosForn.add(edtForCodigo);
        tabDadosForn.add(lblNome);          tabDadosForn.add(edtNome);
        tabDadosForn.add(lblFantasia);      tabDadosForn.add(edtFantasia);
        tabDadosForn.add(lblPesFisica);     tabDadosForn.add(chkPesFisica);
        tabDadosForn.add(lblCPFCNPJ);       tabDadosForn.add(edtCPFCNPJ);
        tabDadosForn.add(lblRgie);          tabDadosForn.add(edtRgie);
        tabDadosForn.add(lblDataCadastro);  tabDadosForn.add(edtDataCadastro);

        tabDadosForn.add(lblEndereco);      tabDadosForn.add(edtEndereco);
        tabDadosForn.add(lblNumero);        tabDadosForn.add(edtNumero);
        tabDadosForn.add(lblComplemento);   tabDadosForn.add(edtComplemento);
        tabDadosForn.add(lblBairro);        tabDadosForn.add(edtBairro);
        tabDadosForn.add(lblCidade);        tabDadosForn.add(edtCidade);
        tabDadosForn.add(lblUF);            tabDadosForn.add(cbUF);
        tabDadosForn.add(lblCEP);           tabDadosForn.add(edtCEP);

        tabDadosForn.add(lblFone1);         tabDadosForn.add(edtFone1);
        tabDadosForn.add(lblFone2);         tabDadosForn.add(edtFone2);
        tabDadosForn.add(lblCelular);       tabDadosForn.add(edtCelular);
        tabDadosForn.add(lblSite);          tabDadosForn.add(edtSite);
        tabDadosForn.add(lblEmail);         tabDadosForn.add(edtEmail);
        tabDadosForn.add(lblAtivo);         tabDadosForn.add(chkAtivo);
        tabDadosForn.add(lblContato);       tabDadosForn.add(edtContato);

        paneDadosForn.addTab("Dados do Fornecedor", tabDadosForn);
        paneCentro.add(paneDadosForn);

        // Aba: Consulta
        paneConsultaDados.add(lblId1);            paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);           paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblNomeFiltro);     paneConsultaDados.add(edtNomeFiltro);
        paneConsultaDados.add(lblContatoFiltro);  paneConsultaDados.add(edtContatoFiltro);
        paneConsultaDados.add(lblUFFiltro);       paneConsultaDados.add(cbUFFiltro);
        paneConsultaDados.add(btnConsultar);      paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollTabela);

        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneConsulta.addTab("Consulta", tabConsulta);
        paneCentro.add(paneConsulta);

        // Ações dos botões consulta/limpar
        btnConsultar.addActionListener(e -> consultar());
        btnLimpar.addActionListener(e -> {
            edtId1.setText("");
            edtId2.setText("");
            edtNomeFiltro.setText("");
            edtContatoFiltro.setText("");
            cbUFFiltro.setSelectedIndex(0);
        });
    }

    //  esse metodo é para: posicionar todos os componentes com coordenadas absolutas (layout fixo).
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
        btnGravar  .setBounds(1320, 7, 120, 25);

        // Centro
        paneCentro.setBounds(10, 60, 1470, 770);
        lblTitulo.setBounds(0, 0, 1200, 30);

        // Abas
        paneDadosForn.setBounds(10, 40, 1470, 340);
        paneConsulta.setBounds(10, 390, 1470, 440);

        // ===== Campos da aba "Dados do Fornecedor" =====
        // Linha 1
        lblForCodigo.setBounds(10, 15, 60, 25);   edtForCodigo.setBounds(75, 15, 100, 25);
        lblNome.setBounds(190, 15, 60, 25);       edtNome.setBounds(250, 15, 350, 25);
        lblFantasia.setBounds(610, 15, 70, 25);   edtFantasia.setBounds(685, 15, 250, 25);
        lblPesFisica.setBounds(945, 15, 110, 25); chkPesFisica.setBounds(1060, 15, 25, 25);
        lblAtivo.setBounds(1095, 15, 50, 25);     chkAtivo.setBounds(1145, 15, 25, 25);
        lblDataCadastro.setBounds(1180, 15, 150, 25); edtDataCadastro.setBounds(1335, 15, 110, 25);

        // Linha 2
        lblCPFCNPJ.setBounds(10, 50, 80, 25);     edtCPFCNPJ.setBounds(90, 50, 180, 25);
        lblRgie.setBounds(280, 50, 60, 25);       edtRgie.setBounds(340, 50, 160, 25);
        lblContato.setBounds(510, 50, 60, 25);    edtContato.setBounds(575, 50, 250, 25);

        // Linha 3
        lblEndereco.setBounds(10, 85, 70, 25);    edtEndereco.setBounds(80, 85, 350, 25);
        lblNumero.setBounds(440, 85, 60, 25);     edtNumero.setBounds(500, 85, 80, 25);
        lblComplemento.setBounds(590, 85, 100, 25); edtComplemento.setBounds(695, 85, 220, 25);

        // Linha 4
        lblBairro.setBounds(10, 120, 60, 25);     edtBairro.setBounds(70, 120, 200, 25);
        lblCidade.setBounds(280, 120, 60, 25);    edtCidade.setBounds(340, 120, 200, 25);
        lblUF.setBounds(550, 120, 30, 25);        cbUF.setBounds(585, 120, 60, 25);
        lblCEP.setBounds(655, 120, 40, 25);       edtCEP.setBounds(700, 120, 120, 25);

        // Linha 5
        lblFone1.setBounds(10, 155, 60, 25);      edtFone1.setBounds(70, 155, 160, 25);
        lblFone2.setBounds(240, 155, 60, 25);     edtFone2.setBounds(300, 155, 160, 25);
        lblCelular.setBounds(470, 155, 60, 25);   edtCelular.setBounds(530, 155, 160, 25);
        lblSite.setBounds(700, 155, 40, 25);      edtSite.setBounds(745, 155, 260, 25);
        lblEmail.setBounds(1010, 155, 60, 25);    edtEmail.setBounds(1070, 155, 250, 25);

        // ===== Aba "Consulta" =====
        tabConsulta.setBounds(0, 0, 1470, 440);

        // Filtros (topo)
        paneConsultaDados.setBounds(10, 10, 1440, 110);

        lblId1.setBounds(10, 10, 20, 25);            edtId1.setBounds(35, 10, 100, 25);
        lblText.setBounds(140, 10, 10, 25);          edtId2.setBounds(155, 10, 100, 25);

        lblNomeFiltro.setBounds(270, 10, 40, 25);    edtNomeFiltro.setBounds(315, 10, 220, 25);
        lblContatoFiltro.setBounds(545, 10, 60, 25); edtContatoFiltro.setBounds(610, 10, 200, 25);
        lblUFFiltro.setBounds(820, 10, 25, 25);      cbUFFiltro.setBounds(850, 10, 70, 25);

        btnConsultar.setBounds(940, 10, 100, 25);
        btnLimpar.setBounds(1050, 10, 100, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 130, 1440, 290);
        scrollTabela.setBounds(0, 0, 1440, 290);
        paneConsultaTabela.add(scrollTabela);
    }

    //  esse metodo é para: registrar todas as ações dos botões de navegação, CRUD e consulta.
    private void configurarAcoes() {
        btnPrimeiro.addActionListener(e -> {
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Fornecedores Cadastrados !");
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
                JOptionPane.showMessageDialog(this, "Não Existem Fornecedores Cadastrados !");
                return;
            }
            mostrarRegistro(lista.size() - 1);
        });

        btnNovo.addActionListener(e -> {
            limparCampos();
            edtDataCadastro.setText(LocalDate.now().toString()); // yyyy-MM-dd
            setOperacao("incluir");
            chkAtivo.setSelected(true);
            edtNome.requestFocusInWindow();
        });

        btnAlterar.addActionListener(e -> setOperacao("alterar"));

        btnGravar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Gravação deste Fornecedor ?",
                    "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    FornecedorModel f = montarFornecedorDosCampos();
                    FornecedorController ctrl = new FornecedorController();
                    ctrl.gravar(getOperacao(), f);
                    JOptionPane.showMessageDialog(this, "Dados Gravados com Sucesso");
                    consultar(); // recarrega tabela
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Gravação \n" + ex.getMessage());
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            setOperacao("");
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Exclusão deste Fornecedor ? (somente vínculo fornecedor)",
                    "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    FornecedorModel f = montarFornecedorDosCampos();
                    FornecedorController ctrl = new FornecedorController();
                    ctrl.excluir(f);
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
            edtNomeFiltro.setText("");
            edtContatoFiltro.setText("");
            cbUFFiltro.setSelectedIndex(0);
        });
    }

    //  esse metodo é para: obter a operação atual (incluir/alterar/""), usada na gravação.
    private String getOperacao() { return operacao; }

    //  esse metodo é para: definir a operação e habilitar/desabilitar o botão Gravar conforme necessário.
    private void setOperacao(String operacao) {
        this.operacao = operacao;
        btnGravar.setEnabled(!operacao.isEmpty());
    }

    //  esse metodo é para: limpar todos os campos da tela e restaurar valores padrão.
    private void limparCampos() {
        edtForCodigo.setText("0");
        edtNome.setText("");
        edtFantasia.setText("");
        chkPesFisica.setSelected(true);
        edtCPFCNPJ.setText("");
        edtRgie.setText("");
        edtDataCadastro.setText(""); // yyyy-MM-dd
        edtEndereco.setText("");
        edtNumero.setText("");
        edtComplemento.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        cbUF.setSelectedItem("SP");
        edtCEP.setText("");
        edtFone1.setText("");
        edtFone2.setText("");
        edtCelular.setText("");
        edtSite.setText("");
        edtEmail.setText("");
        chkAtivo.setSelected(false);
        edtContato.setText("");
    }

    //  esse metodo é para: preencher os campos da tela com os dados do fornecedor selecionado na tabela.
    private void mostrar(FornecedorModel f) {
        edtForCodigo.setText(String.valueOf(f.getPES_CODIGO())); // chave da pessoa
        edtNome.setText(f.getPES_NOME());
        edtFantasia.setText(f.getPES_FANTAZIA());
        chkPesFisica.setSelected("F".equalsIgnoreCase(f.getPES_FISICA()));
        edtCPFCNPJ.setText(f.getPES_CPFCNPJ());
        edtRgie.setText(f.getPES_RGIE());
        edtDataCadastro.setText(fmtDate(f.getPES_CADASTRO()));
        edtEndereco.setText(f.getPES_ENDERECO());
        edtNumero.setText(f.getPES_NUMERO());
        edtComplemento.setText(f.getPES_COMPLEMENTO());
        edtBairro.setText(f.getPES_BAIRRO());
        edtCidade.setText(f.getPES_CIDADE());
        cbUF.setSelectedItem(f.getPES_UF());
        edtCEP.setText(f.getPES_CEP());
        edtFone1.setText(f.getPES_FONE1());
        edtFone2.setText(f.getPES_FONE2());
        edtCelular.setText(f.getPES_CELULAR());
        edtSite.setText(f.getPES_SITE());
        edtEmail.setText(f.getPES_EMAIL());
        chkAtivo.setSelected("S".equalsIgnoreCase(f.getPES_ATIVO()));
        edtContato.setText(f.getFOR_CONTATO());
    }

    //  esse metodo é para: montar um FornecedorModel a partir dos valores digitados nos campos (para gravar/alterar/excluir).
    private FornecedorModel montarFornecedorDosCampos() {
        FornecedorModel f = new FornecedorModel();

        int cod = 0;
        try { cod = Integer.parseInt(edtForCodigo.getText().trim()); } catch (Exception ignored) {}
        f.setPES_CODIGO(cod);

        // Pessoa
        f.setPES_NOME(edtNome.getText().trim());
        f.setPES_FANTAZIA(edtFantasia.getText().trim());
        f.setPES_FISICA(chkPesFisica.isSelected() ? "F" : "J"); // F/J
        f.setPES_CPFCNPJ(edtCPFCNPJ.getText().trim());
        f.setPES_RGIE(edtRgie.getText().trim());
        f.setPES_CADASTRO(parseDate(edtDataCadastro.getText().trim()));
        f.setPES_ENDERECO(edtEndereco.getText().trim());
        f.setPES_NUMERO(edtNumero.getText().trim());
        f.setPES_COMPLEMENTO(edtComplemento.getText().trim());
        f.setPES_BAIRRO(edtBairro.getText().trim());
        f.setPES_CIDADE(edtCidade.getText().trim());
        f.setPES_UF((String) cbUF.getSelectedItem());
        f.setPES_CEP(edtCEP.getText().trim());
        f.setPES_FONE1(edtFone1.getText().trim());
        f.setPES_FONE2(edtFone2.getText().trim());
        f.setPES_CELULAR(edtCelular.getText().trim());
        f.setPES_SITE(edtSite.getText().trim());
        f.setPES_EMAIL(edtEmail.getText().trim());
        f.setPES_ATIVO(chkAtivo.isSelected() ? "S" : "N");

        // Fornecedor
        f.setFOR_CONTATO(edtContato.getText().trim());

        return f;
    }

    //  esse metodo é para: construir a cláusula WHERE da consulta com base nos filtros digitados.
    private String filtroConsulta() {
        String cond = "";

        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(p.pes_codigo >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pes_codigo <= " + edtId2.getText().trim() + ")";
        }
        if (!edtNomeFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pes_nome LIKE ('%" + edtNomeFiltro.getText().trim() + "%'))";
        }
        if (!edtContatoFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(f.for_contato LIKE ('%" + edtContatoFiltro.getText().trim() + "%'))";
        }
        if (cbUFFiltro.getSelectedItem() != null && !"".equals(cbUFFiltro.getSelectedItem())) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pes_uf = '" + cbUFFiltro.getSelectedItem() + "')";
        }
        return cond;
    }

    //  esse metodo é para: chamar o controller, carregar a lista na tabela e posicionar no primeiro registro (ou limpar se vazio).
    private void consultar() {
        try {
            String cond = filtroConsulta();
            FornecedorController ctrl = new FornecedorController();
            lista = ctrl.consultar(cond); // retorna ArrayList<FornecedorModel>
            if (lista == null) lista = new ArrayList<>();

            tableModel = new FornecedorTableModel(lista, colunas);
            tabela.setModel(tableModel);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            if (!lista.isEmpty()) {
                mostrarRegistro(0);
            } else {
                JOptionPane.showMessageDialog(this, "Não Existem Fornecedores Cadastrados !");
                limparCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na Consulta do Fornecedor \n" + ex.getMessage());
        }
    }

    //  esse metodo é para: exibir um registro específico da lista e sincronizar a seleção da tabela (índice de view x model).
    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }

    // ===== Utils =====

    //  esse metodo é para: converter String yyyy-MM-dd em LocalDate (retorna null se inválida).
    private static LocalDate parseDate(String s) {
        try {
            if (s == null || s.isEmpty()) return null;
            return LocalDate.parse(s, DF);
        } catch (Exception e) {
            System.out.println("Data inválida: '" + s + "'. Esperado yyyy-MM-dd.");
            return null;
        }
    }

    //  esse metodo é para: formatar LocalDate em yyyy-MM-dd (ou vazio se null).
    private static String fmtDate(LocalDate d) {
        return d == null ? "" : d.format(DF);
    }
}
