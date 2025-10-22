package view;

import model.ClienteModel;
import model.PessoaModel;

import tableModel.ClienteTableModel;

import controller.ClienteController;

import util.UtilsUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteView extends JPanel {
    
    private ClienteController ctrl = null;
    private ClienteModel clienteAtual;
    private LocalDate dataFiltroConsulta;
    
    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnImprimir, btnGravar;

    // Campos – Dados do Cadastro Cliente (Pessoa + Cliente)
    private JLabel
            lblCliCodigo, lblNome, lblPesFisica, lblCPFCNPJ, lblRgie, lblDataCadastro,
            lblEndereco, lblNumero, lblComplemento, lblBairro, lblCidade, lblUF, lblCEP,
            lblCelular, lblSite, lblEmail, lblAtivo, lblLimiteCred;

    private JTextField
            edtCliCodigo, edtNome, edtCPFCNPJ, edtRgie, edtDataCadastro,
            edtEndereco, edtNumero, edtComplemento, edtBairro, edtCidade, edtCEP,
            edtCelular, edtSite, edtEmail, edtLimiteCred;

    private JCheckBox chkPesFisica, chkAtivo; 
    private JComboBox<String> cbUF;

    // Consulta (filtros)
    private JLabel lblId1, lblText, lblId2, lblNomeFiltro, lblDataCadastroFiltro, lblLimiteCredFiltro, lblUFFiltro;
    private JTextField edtId1, edtId2, edtNomeFiltro, edtDataCadastroFiltro, edtLimiteCredFiltro;
    private JComboBox<String> cbUFFiltro;
    private JButton btnConsultar, btnLimpar;

    // Painéis
    private JPanel paneCabecario;      // botões
    private JPanel paneCentro;         // título + abas
    private JTabbedPane paneDadosCliente;   // aba "Dados do Cliente"
    private JTabbedPane paneDadosConsulta;  // aba "Consulta"
    private JPanel tabDadosCliente;         // conteúdo da aba "Dados do Cliente"
    private JPanel tabConsulta;             // conteúdo da aba "Consulta"
    private JPanel paneConsultaDados;       // filtros
    private JPanel paneConsultaTabela;      // tabela

    // Título
    private JLabel lblTitulo;

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;
    private ClienteTableModel tableModel;

    // Estado / dados
    private String operacao = "";
    private ArrayList<ClienteModel> lista = new ArrayList<>();


    public ClienteView() {
        System.out.println(" [ClienteView] entrou");
        
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
               
        setOperacao("");
        ctrl = new ClienteController();
        clienteAtual = null;
        dataFiltroConsulta = null;
        
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
        btnImprimir   = new JButton("Imprimir");
        btnGravar   = new JButton("Gravar");

        // Título
        lblTitulo = new JLabel("Cadastro de Clientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30,30,120));

        // Dados do Cliente (Pessoa + Cliente)
        lblCliCodigo     = new JLabel("Código:");       edtCliCodigo     = new JTextField();        
        lblNome          = new JLabel("Nome:");         edtNome          = new JTextField(30);
        
        lblPesFisica     = new JLabel("Pessoa Física?");        
        chkPesFisica     = new JCheckBox();  
        chkPesFisica.setBackground(new Color(245,250,255));
        
        lblCPFCNPJ       = new JLabel("CPF/CNPJ:");     edtCPFCNPJ       = new JTextField(20);
        lblRgie          = new JLabel("RG/IE:");        edtRgie          = new JTextField(20);
        
        lblDataCadastro  = new JLabel("Cadastro (yyyy-MM-dd):");       
        edtDataCadastro = new JTextField(12);       
        edtDataCadastro.setText(java.time.LocalDate.now().toString()); // yyyy-MM-dd
        
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

        lblCEP           = new JLabel("CEP:");      edtCEP      = new JTextField(10);
        lblCelular       = new JLabel("Celular:");  edtCelular  = new JTextField(16);
        lblSite          = new JLabel("Site:");     edtSite     = new JTextField(30);
        lblEmail         = new JLabel("E-mail:");   edtEmail    = new JTextField(30);

        lblAtivo         = new JLabel("Ativo:");    
        chkAtivo         = new JCheckBox(); 
        chkAtivo.setBackground(new Color(245,250,255));

        lblLimiteCred    = new JLabel("Limite Crédito:");   edtLimiteCred = new JTextField(12);
        
        // Consulta (filtros)
        lblId1                = new JLabel("Cod 1");
        lblText               = new JLabel("até");
        lblId2                = new JLabel("Cod 2"); // apenas placeholder para manter a estrutura
        lblNomeFiltro         = new JLabel("Nome");
        lblDataCadastroFiltro = new JLabel("Data de Cadastro ≥");
        lblLimiteCredFiltro   = new JLabel("Limite Credito ≥");
        lblUFFiltro           = new JLabel("UF");

        edtId1                = new JTextField();
        edtId2                = new JTextField();
        edtNomeFiltro         = new JTextField();
        edtDataCadastroFiltro = new JTextField(""); // yyyy-MM-dd
        edtLimiteCredFiltro   = new JTextField();
        
        cbUFFiltro            = new JComboBox<>(new String[]{
            "", "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
            "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
        });

        btnConsultar          = new JButton("Consulta");
        btnLimpar             = new JButton("Limpa");

        // Tabela
        tableModel = new ClienteTableModel(lista);
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
        paneDadosCliente   = new JTabbedPane();
        paneDadosConsulta  = new JTabbedPane();
        tabDadosCliente    = new JPanel(null);
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

        // Aba: Dados do Cliente
        tabDadosCliente.add(lblCliCodigo);     tabDadosCliente.add(edtCliCodigo);
        tabDadosCliente.add(lblNome);          tabDadosCliente.add(edtNome);
        tabDadosCliente.add(lblPesFisica);     tabDadosCliente.add(chkPesFisica);
        tabDadosCliente.add(lblCPFCNPJ);       tabDadosCliente.add(edtCPFCNPJ);
        tabDadosCliente.add(lblRgie);          tabDadosCliente.add(edtRgie);
        tabDadosCliente.add(lblDataCadastro);  tabDadosCliente.add(edtDataCadastro);

        tabDadosCliente.add(lblEndereco);      tabDadosCliente.add(edtEndereco);
        tabDadosCliente.add(lblNumero);        tabDadosCliente.add(edtNumero);
        tabDadosCliente.add(lblComplemento);   tabDadosCliente.add(edtComplemento);
        tabDadosCliente.add(lblBairro);        tabDadosCliente.add(edtBairro);
        tabDadosCliente.add(lblCidade);        tabDadosCliente.add(edtCidade);
        tabDadosCliente.add(lblUF);            tabDadosCliente.add(cbUF);
        tabDadosCliente.add(lblCEP);           tabDadosCliente.add(edtCEP);

        tabDadosCliente.add(lblCelular);       tabDadosCliente.add(edtCelular);
        tabDadosCliente.add(lblSite);          tabDadosCliente.add(edtSite);
        tabDadosCliente.add(lblEmail);         tabDadosCliente.add(edtEmail);
        tabDadosCliente.add(lblAtivo);         tabDadosCliente.add(chkAtivo);
        tabDadosCliente.add(lblLimiteCred);    tabDadosCliente.add(edtLimiteCred);

        paneDadosCliente.addTab("Dados do Cliente", tabDadosCliente);
        paneCentro.add(paneDadosCliente);

        // Aba: Consulta
        paneConsultaDados.add(lblId1);                paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);               paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblNomeFiltro);         paneConsultaDados.add(edtNomeFiltro);
        paneConsultaDados.add(lblDataCadastroFiltro); paneConsultaDados.add(edtDataCadastroFiltro);
        paneConsultaDados.add(lblLimiteCredFiltro);   paneConsultaDados.add(edtLimiteCredFiltro);
        paneConsultaDados.add(lblUFFiltro);           paneConsultaDados.add(cbUFFiltro);
        paneConsultaDados.add(btnConsultar);          paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollTabela);

        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneDadosConsulta.addTab("Consulta", tabConsulta);
        paneCentro.add(paneDadosConsulta);
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
        btnImprimir.setBounds(1200, 7, 120, 25);
        btnGravar  .setBounds(1320, 7, 120, 25);

        // Centro
        paneCentro.setBounds(10, 60, 1470, 770);
        lblTitulo.setBounds(0, 0, 1200, 30);

        // Abas
        paneDadosCliente.setBounds(10, 40, 1470, 320);
        paneDadosConsulta.setBounds(10, 370, 1470, 460);

        // ===== Campos da aba "Dados do Cliente" =====
        // Linha 1
        lblCliCodigo.setBounds(10, 15, 60, 25);  edtCliCodigo.setBounds(75, 15, 100, 25);
        lblNome.setBounds(190, 15, 60, 25);      edtNome.setBounds(250, 15, 420, 25);
        lblPesFisica.setBounds(680, 15, 120, 25);chkPesFisica.setBounds(800, 15, 25, 25);
        lblAtivo.setBounds(840, 15, 50, 25);     chkAtivo.setBounds(890, 15, 25, 25);
        lblDataCadastro.setBounds(940, 15, 150, 25); edtDataCadastro.setBounds(1095, 15, 120, 25);

        // Linha 2
        lblCPFCNPJ.setBounds(10, 50, 80, 25);    edtCPFCNPJ.setBounds(90, 50, 180, 25);
        lblRgie.setBounds(280, 50, 60, 25);      edtRgie.setBounds(340, 50, 160, 25);
        lblLimiteCred.setBounds(510, 50, 100, 25); edtLimiteCred.setBounds(615, 50, 120, 25);

        // Linha 3
        lblEndereco.setBounds(10, 85, 70, 25);   edtEndereco.setBounds(80, 85, 350, 25);
        lblNumero.setBounds(440, 85, 60, 25);    edtNumero.setBounds(500, 85, 80, 25);
        lblComplemento.setBounds(590, 85, 100, 25); edtComplemento.setBounds(695, 85, 200, 25);

        // Linha 4
        lblBairro.setBounds(10, 120, 60, 25);    edtBairro.setBounds(70, 120, 200, 25);
        lblCidade.setBounds(280, 120, 60, 25);   edtCidade.setBounds(340, 120, 200, 25);
        lblUF.setBounds(550, 120, 30, 25);       cbUF.setBounds(585, 120, 60, 25);
        lblCEP.setBounds(655, 120, 40, 25);      edtCEP.setBounds(700, 120, 120, 25);

        // Linha 5
        lblCelular.setBounds(10, 155, 60, 25);   edtCelular.setBounds(70, 155, 160, 25);
        lblSite.setBounds(240, 155, 40, 25);     edtSite.setBounds(285, 155, 260, 25);
        lblEmail.setBounds(550, 155, 60, 25);    edtEmail.setBounds(610, 155, 285, 25);

        // ===== Aba "Consulta" =====
        tabConsulta.setBounds(0, 0, 1470, 460);

        // Filtros (topo)
        paneConsultaDados.setBounds(10, 10, 1440, 110);

        lblId1.setBounds(10, 10, 40, 25);               edtId1.setBounds(60, 10, 100, 25);
        
        lblText.setBounds(170, 10, 30, 25);             
        
        edtId2.setBounds(210, 10, 100, 25);

        lblNomeFiltro.setBounds(10, 50, 40, 25);        edtNomeFiltro.setBounds(60, 50, 200, 25);
        
        lblUFFiltro.setBounds(330, 10, 30, 25);          cbUFFiltro.setBounds(370, 10, 70, 25);
        
        lblDataCadastroFiltro.setBounds(460, 10, 150, 25); edtDataCadastroFiltro.setBounds(620, 10, 120, 25);
        
        lblLimiteCredFiltro.setBounds(460, 50, 150, 25);  edtLimiteCredFiltro.setBounds(620, 50, 120, 25);

        btnConsultar.setBounds(780, 10, 100, 25);
        btnLimpar.setBounds(890, 10, 100, 25);

        // Tabela
        paneConsultaTabela.setBounds(10, 120, 1440, 245);
        scrollTabela.setBounds(0, 0, 1440, 245);
        paneConsultaTabela.add(scrollTabela);

    }

    private void configurarAcoes() {        
        btnPrimeiro.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnPrimeiro clicado \n ");
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Clientes Cadastrados !");
                return;
            }
            mostrarRegistro(0);
        });

        btnAnterior.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnAnterior clicado \n ");
            int viewSel = tabela.getSelectedRow();
            int sel = (viewSel < 0 ? 0 : tabela.convertRowIndexToModel(viewSel)) - 1;
            mostrarRegistro(sel);
        });

        btnProximo.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnProximo clicado \n ");
            int viewSel = tabela.getSelectedRow();
            int sel = (viewSel < 0 ? -1 : tabela.convertRowIndexToModel(viewSel)) + 1;
            mostrarRegistro(sel);
        });

        btnUltimo.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnUltimo clicado \n ");
            if (lista == null || lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Clientes Cadastrados !");
                return;
            }
            mostrarRegistro(lista.size() - 1);
        });

        btnNovo.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnNovo clicado \n ");
            limparCampos();
            edtDataCadastro.setText(java.time.LocalDate.now().toString()); // yyyy-MM-dd
            setOperacao("incluir");
            chkAtivo.setSelected(true);
            edtNome.requestFocusInWindow();
   
        });

        btnAlterar.addActionListener(e -> {
                System.out.println("\n [ClienteView] btnAlterar clicado \n ");
                setOperacao("alterar");
        });
        
        btnImprimir.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnImprimir clicado \n ");

            Exception retorno = ctrl.imprimir();
            if(retorno != null) {
                JOptionPane.showMessageDialog(null, "Erro no Relatório de Cliente /n" + retorno.getMessage());     
            }
        });
        
        btnGravar.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnGravar clicado \n ");

            if(getOperacao().equals("")) {
                JOptionPane.showConfirmDialog(this,
                        "Selecione uma OPERAÇÂO antes de gravar! ex: NOVO"
                );
            }
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Gravação deste Cliente ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    
                    ClienteModel c;
                    if (getOperacao().equals("incluir")) {
                        c = montarClienteDosCampos();
                        ctrl.gravar(c, getOperacao());
                    } 
                    
                    if (getOperacao().equals("alterar")) {
                        c =  montarClienteDosCampos();
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
            System.out.println("\n [ClienteView] btnExcluir clicado \n ");
            setOperacao("excluir");
            
            if (JOptionPane.showConfirmDialog(this,
                    "Confirma Exclusão deste Cliente ? (somente vínculo cliente)", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ClienteModel c = montarClienteDosCampos();
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
            System.out.println("\n [ClienteView] btnConsultar clicado \n ");
            consultar(); 
        });
        
        btnLimpar.addActionListener(e -> {
            System.out.println("\n [ClienteView] btnLimpar clicado \n ");
            edtId1.setText("");
            edtId2.setText("");
            edtNomeFiltro.setText("");
            edtDataCadastroFiltro.setText("");
            edtLimiteCredFiltro.setText("");
            cbUFFiltro.setSelectedIndex(0);
        });
    }

    private void limparCampos() {
        
        edtCliCodigo.setText("0");                
        edtNome.setText("");
        chkPesFisica.setSelected(true);
        edtCPFCNPJ.setText("");
        edtRgie.setText("");
        edtDataCadastro.setText("");
        edtEndereco.setText("");
        edtNumero.setText("");
        edtComplemento.setText("");
        edtBairro.setText("");
        edtCidade.setText("");
        cbUF.setSelectedItem("SP");
        edtCEP.setText("");
        edtCelular.setText("");
        edtSite.setText("");
        edtEmail.setText("");
        chkAtivo.setSelected(false);
        edtLimiteCred.setText("");
    }

     //  esse metodo é para: navegar e exibir um registro específico da lista, sincronizando a seleção da tabela (view x model).
    private void mostrarRegistro(int registro) {
        System.out.println("\n [ClienteView] void MOSTRAR iniciado");
        setOperacao("");
        if (lista == null || lista.isEmpty()) return;
        if (registro < 0 || registro >= lista.size()) return;

        mostrar(lista.get(registro));

        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }
    
    private void mostrar(ClienteModel c) {
        clienteAtual = c;
        
        System.out.println(" Cliente atual -> pes_cod = " + clienteAtual.getPessoa_Cliente().getPES_CODIGO());
        System.out.println(" Cliente atual -> cli_cod = " + clienteAtual.getCLI_CODIGO());

        
        PessoaModel pessoa = c.getPessoa_Cliente();
        
        edtCliCodigo.setText(String.valueOf(c.getCLI_CODIGO()));
        edtNome.setText(pessoa.getPES_NOME());
        chkPesFisica.setSelected("1".equalsIgnoreCase(pessoa.getPES_FISICA()));
        edtCPFCNPJ.setText(pessoa.getPES_CPFCNPJ());
        edtRgie.setText(pessoa.getPES_RGIE());
        edtDataCadastro.setText(UtilsUI.fmtDate(c.getPessoa_Cliente().getPES_CADASTRO()));
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
        chkAtivo.setSelected(pessoa.getPES_ATIVO() != null && pessoa.getPES_ATIVO().equals("1"));

        edtLimiteCred.setText(c.getCLI_LIMITECRED() == 0.0 ? String.valueOf("0.0") : String.valueOf(c.getCLI_LIMITECRED()));
    }

    private ClienteModel montarClienteDosCampos() throws Exception {
        PessoaModel pessoa = null;
        ClienteModel c = null;
        Integer cod = null; // Código (pes_codigo)

        if(getOperacao().equals("incluir")) {
            pessoa = new PessoaModel();
            c = new ClienteModel();
            cod = null;
        } 
        
        if (getOperacao().equals("alterar") || getOperacao().equals("excluir")){
            if (clienteAtual == null) {
                throw new Exception (" ClienteAtual esta NULL");
            }
            
            c = clienteAtual;
            pessoa = c.getPessoa_Cliente();
            cod = pessoa.getPES_CODIGO();
        }
        
        System.out.println("\n [ClienteView] void montarClienteDosCampos inciado com Operacao = " + getOperacao());
        System.out.println(" PES_CODIGO = " + cod);
        
        if (pessoa == null){
            throw new Exception (" ClienteAtual esta NULL");
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
        pessoa.setPES_ATIVO(chkAtivo.isSelected() ? "1" : "0");

        // Cliente
        c.setPessoa_Cliente(pessoa);
        c.setCLI_LIMITECRED(UtilsUI.parseDouble(edtLimiteCred.getText().trim()));

        return c;
    }

    private String filtroConsulta() {
        String cond = "";
        String s = "";

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
        if (!edtDataCadastroFiltro.getText().trim().isEmpty()) {
            java.time.LocalDate data = UtilsUI.parseDate(edtDataCadastroFiltro.getText().trim());
           
            if (data != null) {
                dataFiltroConsulta = data;

                if (!cond.isEmpty()) cond += " AND ";
                cond += "(p.pes_dtcadastro >= :dataFiltro)"; //  parametro nomeado

                System.out.println(" [ClienteView] filtroConsulta() valor da edtDataCliente = " + data + " , tipo = "+ data.getClass().getName());
            } else {
                dataFiltroConsulta = null;
                s = "null";
            }
            
            System.out.println(" [ClienteView] filtroConsulta() valor da COND em if(data) = " + cond);
        }
        if (!edtLimiteCredFiltro.getText().trim().isEmpty()) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(c.cli_limitecred >= " + edtLimiteCredFiltro.getText().trim().replace(",", ".") + ")";
        }
        if (cbUFFiltro.getSelectedItem() != null && !"".equals(cbUFFiltro.getSelectedItem())) {
            if (!cond.isEmpty()) cond += " AND ";
            cond += "(p.pes_uf = '" + cbUFFiltro.getSelectedItem() + "')";
        }
        
        if (cond.isEmpty() || cond.equals("")) s = "NULL";
        System.out.println(" [ClienteView] filtroConsulta() valor final da COND: " + s);
        System.out.println(  cond);

        return cond;
    }

    private void consultar() {
        try {            
            String cond = filtroConsulta();
            
            if (dataFiltroConsulta != null ) {
                ctrl.setDataFiltro(dataFiltroConsulta);
                System.out.println(" [ClienteView] consultar() valor da dataFiltroConsulta = " + dataFiltroConsulta + " , tipo = "+ dataFiltroConsulta.getClass().getName());
            }
            
            lista = ctrl.consultar(cond); // retorna ArrayList<ClienteModel>
            if (lista == null) lista = new ArrayList<>();

            tableModel = new ClienteTableModel(lista);
            tabela.setModel(tableModel);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            if (!lista.isEmpty()) {
                mostrarRegistro(0);
            } else {
                JOptionPane.showMessageDialog(this, "Não Existem Clientes Cadastrados !");
                limparCampos();
            }
            
            setOperacao("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na Consulta do Cliente \n" + ex.getMessage());
        }
    }
    
       
    /**
     * Getters
     */
    private String getOperacao() { return operacao; }

        
    public LocalDate getDataFiltroConsulta() {
        return dataFiltroConsulta;
    }
    
    /**
     * Setters
     */
    
    private void setOperacao(String operacao) {
        this.operacao = operacao;
        btnGravar.setEnabled(!operacao.isEmpty());
    }

    
}

