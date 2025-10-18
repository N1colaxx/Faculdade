package view;

import controller.FornecedorController;
import model.FornecedorModel;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import model.PessoaModel;
import util.UtilsUI;

public class FornecedorView extends JPanel {
    private FornecedorController ctrl = null;
    private FornecedorModel fornecedorAtual;
    
    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnImprimir, btnGravar;
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
    private ArrayList<FornecedorModel> lista = new ArrayList<>();


    public FornecedorView() {
        System.out.println(" [FornecedorView] entrou");
        
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
        
        ctrl = new FornecedorController();
        
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
        btnImprimir  = new JButton("Imprimir");
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
        lblId1          = new JLabel("Cod 1");
        lblText         = new JLabel("até");
        lblId2          = new JLabel(); // placeholder
        lblNomeFiltro   = new JLabel("Nome");
        lblContatoFiltro= new JLabel("Contato");
        lblUFFiltro     = new JLabel("UF");

        edtId1          = new JTextField();
        edtId2          = new JTextField();
        edtNomeFiltro   = new JTextField();
        edtContatoFiltro= new JTextField();

        cbUFFiltro      = new JComboBox<>(new String[]{
            "", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
            "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
        });

        btnConsultar    = new JButton("Consulta");
        btnLimpar       = new JButton("Limpa");

        // Tabela
        tableModel = new FornecedorTableModel(lista);
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
    }

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
        btnImprimir  .setBounds(1190, 7, 120, 25);
        btnGravar  .setBounds(1320, 7, 120, 25);

        // Centro
        paneCentro.setBounds(10, 60, 1470, 780);
        lblTitulo.setBounds(0, 0, 1200, 30);

        // Abas
        paneDadosForn.setBounds(10, 40, 1470, 260);
        paneConsulta.setBounds(10, 340, 1470, 435);

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
        tabConsulta.setBounds(0, 0, 1470, 460);

        // Filtros (topo)
        paneConsultaDados.setBounds(10, 10, 1440, 110);

        lblId1.setBounds(10, 10, 50, 25);            edtId1.setBounds(70, 10, 100, 25);
        
        lblText.setBounds(180, 10, 30, 25);
        
        edtId2.setBounds(220, 10, 100, 25);

        lblNomeFiltro.setBounds(10, 50, 50, 25);    edtNomeFiltro.setBounds(60, 50, 220, 25);
        
        lblContatoFiltro.setBounds(290, 50, 60, 25); edtContatoFiltro.setBounds(360, 50, 200, 25);
        
        lblUFFiltro.setBounds(340, 10, 25, 25);      cbUFFiltro.setBounds(380, 10, 70, 25);

        btnConsultar.setBounds(480, 10, 100, 25);
        btnLimpar.setBounds(580, 10, 100, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 120, 1440, 300);
        scrollTabela.setBounds(0, 0, 1440, 300);
        paneConsultaTabela.add(scrollTabela);
    }

    private void configurarAcoes() {        
         btnPrimeiro.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnPrimeiro clicado \n ");
             if (lista == null || lista.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Não Existem Clientes Cadastrados !");
                 return;
             }
             mostrarRegistro(0);
         });

         btnAnterior.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnAnterior clicado \n ");
             int viewSel = tabela.getSelectedRow();
             int sel = (viewSel < 0 ? 0 : tabela.convertRowIndexToModel(viewSel)) - 1;
             mostrarRegistro(sel);
         });

         btnProximo.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnProximo clicado \n ");
             int viewSel = tabela.getSelectedRow();
             int sel = (viewSel < 0 ? -1 : tabela.convertRowIndexToModel(viewSel)) + 1;
             mostrarRegistro(sel);
         });

         btnUltimo.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnUltimo clicado \n ");
             if (lista == null || lista.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Não Existem Clientes Cadastrados !");
                 return;
             }
             mostrarRegistro(lista.size() - 1);
         });

         btnNovo.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnNovo clicado \n ");
             limparCampos();
             edtDataCadastro.setText(java.time.LocalDate.now().toString()); // yyyy-MM-dd
             setOperacao("incluir");
             chkAtivo.setSelected(true);
             edtNome.requestFocusInWindow();

         });

         btnAlterar.addActionListener(e -> {
                 System.out.println("\n [FornecedorView] btnAlterar clicado \n ");
                 setOperacao("alterar");
         });

         btnImprimir.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnImprimir clicado \n ");

             Exception retorno = ctrl.imprimir();
             if(retorno != null) {
                 JOptionPane.showMessageDialog(null, "Erro no Relatório de Cliente /n" + retorno.getMessage());     
             }
         });

         btnGravar.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnGravar clicado \n ");

             if(getOperacao().equals("")) {
                 JOptionPane.showConfirmDialog(this,
                         "Selecione uma OPERAÇÂO antes de gravar! ex: NOVO"
                 );
             }
             if (JOptionPane.showConfirmDialog(this,
                     "Confirma Gravação deste Cliente ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 try {

                     FornecedorModel c;
                     if (getOperacao().equals("incluir")) {
                         c = montarFornecedorDosCampos();
                         ctrl.gravar(c, getOperacao());
                     } 

                     if (getOperacao().equals("alterar")) {
                         c =  montarFornecedorDosCampos();
                         ctrl.alterar(c);
                     }

                     JOptionPane.showMessageDialog(this, "Dados Gravados com Sucesso");
                     consultar();
                     setOperacao("");
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(this, "Erro na Gravação \n" + ex.getMessage());
                 }
             }
         });

         btnExcluir.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnExcluir clicado \n ");
             setOperacao("excluir");

             if (JOptionPane.showConfirmDialog(this,
                     "Confirma Exclusão deste Cliente ? (somente vínculo cliente)", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 try {
                     FornecedorModel c = montarFornecedorDosCampos();
                     ctrl.excluir(c);
                     JOptionPane.showMessageDialog(this, "Registro Excluído com Sucesso");
                     consultar();
                     setOperacao("");
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(this, "Erro na Exclusão de Registro \n" + ex.getMessage());
                 }
             }
         });

         btnConsultar.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnConsultar clicado \n ");
             consultar(); 
         });

         btnLimpar.addActionListener(e -> {
             System.out.println("\n [FornecedorView] btnLimpar clicado \n ");
             edtId1.setText("");
             edtId2.setText("");
             edtNomeFiltro.setText("");
             cbUFFiltro.setSelectedIndex(0);
         });
     }

    private String getOperacao() { return operacao; }

    private void setOperacao(String operacao) {
        this.operacao = operacao;
        btnGravar.setEnabled(!operacao.isEmpty());
    }

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

    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }

    private void mostrar(FornecedorModel f) {
        fornecedorAtual = f;
        
        System.out.println(" Cliente atual -> pes_cod = " + fornecedorAtual.getPESSOA_FORNECEDOR().getPES_CODIGO());
        System.out.println(" Cliente atual -> cli_cod = " + fornecedorAtual.getFOR_CODIGO());

        
        PessoaModel pessoa = f.getPESSOA_FORNECEDOR();
        
        edtForCodigo.setText(String.valueOf(f.getFOR_CODIGO()));
        edtNome.setText(pessoa.getPES_NOME());
        chkPesFisica.setSelected("1".equalsIgnoreCase(pessoa.getPES_FISICA()));
        edtCPFCNPJ.setText(pessoa.getPES_CPFCNPJ());
        edtRgie.setText(pessoa.getPES_RGIE());
        edtDataCadastro.setText(UtilsUI.fmtDate(f.getPESSOA_FORNECEDOR().getPES_CADASTRO()));
        edtEndereco.setText(pessoa.getPES_ENDERECO());
        edtNumero.setText(pessoa.getPES_NUMERO());
        edtComplemento.setText(pessoa.getPES_COMPLEMENTO());
        edtBairro.setText(pessoa.getPES_BAIRRO());
        edtCidade.setText(pessoa.getPES_CIDADE());
        cbUF.setSelectedItem(pessoa.getPES_UF());
        edtCEP.setText(pessoa.getPES_CEP());
        edtCelular.setText(pessoa.getPES_CELULAR());
        edtSite.setText(pessoa.getPES_SITE());
        edtEmail.setText(pessoa.getPES_EMAIL());
        chkAtivo.setSelected(pessoa.getPES_ATIVO() != null && f.getPESSOA_FORNECEDOR().getPES_ATIVO() == 1);

        edtContato.setText(f.getFOR_CONTATO() == null ? "Sem contato" : f.getFOR_CONTATO());
    }

    private FornecedorModel montarFornecedorDosCampos() throws Exception {
        PessoaModel pessoa = null;
        FornecedorModel f = null;
        Integer cod = null; // Código (pes_codigo)

        if(getOperacao().equals("incluir")) {
            pessoa = new PessoaModel();
            f = new FornecedorModel();
            cod = null;
        } 
        
        if (getOperacao().equals("alterar") || getOperacao().equals("excluir")){
            if (fornecedorAtual == null) {
                throw new Exception (" Fornecedor esta NULL");
            }
            
            f = fornecedorAtual;
            pessoa = f.getPESSOA_FORNECEDOR();
            cod = pessoa.getPES_CODIGO();
        }
        
        System.out.println("\n [FornecedorView] void montarFornecedorDosCampos inciado com Operacao = " + getOperacao());
        System.out.println(" PES_CODIGO = " + cod);
        
        if (pessoa == null){
            throw new Exception (" Pessoa de fornecedor esta NULL");
        }
        
        // Pessoa
        pessoa.setPES_CODIGO(cod);
        pessoa.setPES_NOME(edtNome.getText().trim());
        pessoa.setPES_FISICA(chkPesFisica.isSelected() ? "1" : "0");  
        pessoa.setPES_CPFCNPJ(edtCPFCNPJ.getText().trim());
        pessoa.setPES_RGIE(edtRgie.getText().trim());
        pessoa.setPES_CADASTRO(UtilsUI.parseDate(edtDataCadastro.getText().trim()));
        pessoa.setPES_ENDERECO(edtEndereco.getText().trim());
        pessoa.setPES_NUMERO(edtNumero.getText().trim());
        pessoa.setPES_COMPLEMENTO(edtComplemento.getText().trim());
        pessoa.setPES_BAIRRO(edtBairro.getText().trim());
        pessoa.setPES_CIDADE(edtCidade.getText().trim());
        pessoa.setPES_UF((String) cbUF.getSelectedItem());
        pessoa.setPES_CEP(edtCEP.getText().trim());
        pessoa.setPES_CELULAR(edtCelular.getText().trim());
        pessoa.setPES_SITE(edtSite.getText().trim());
        pessoa.setPES_EMAIL(edtEmail.getText().trim());
        pessoa.setPES_ATIVO(chkAtivo.isSelected() ? 1 : 0);

        // Cliente
        f.setPESSOA_FORNECEDOR(pessoa);
        f.setFOR_CONTATO(edtContato.getText().trim());

        return f;
    }

    private String filtroConsulta() {
        String cond = "";

        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(f.for_codigo >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(f.for_codigo <= " + edtId2.getText().trim() + ")";
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
        
        System.out.println(" [FornecedorView] filtroConsulta() valor final da COND: " + cond);
        return cond;
    }

    private void consultar() {
        try {
            String cond = filtroConsulta();
            
            ctrl = new FornecedorController();
            
            lista = ctrl.consultar(cond); // retorna ArrayList<FornecedorModel>
            if (lista == null) lista = new ArrayList<>();

            tableModel = new FornecedorTableModel(lista);
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
}
