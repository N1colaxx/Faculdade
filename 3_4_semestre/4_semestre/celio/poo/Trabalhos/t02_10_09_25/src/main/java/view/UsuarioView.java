package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsuarioView extends JPanel {

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;

    // Campos – Dados do Usuário
    private JLabel lblCodigo, lblNome, lblLogin, lblSenha, lblAtivo;
    private JTextField edtCodigo, edtNome, edtLogin;
    private JPasswordField edtSenha;
    private JCheckBox chkAtivo;

    // Campos – Consulta (filtros)
    private JLabel lblId1, lblText, lblLoginFiltro, lblNomeFiltro;
    private JTextField edtId1, edtId2, edtLoginFiltro, edtNomeFiltro;
    private JButton btnConsultar, btnLimpar;

    // Painéis
    private JPanel paneCabecario;      // botões
    private JPanel paneCentro;         // título + abas
    private JTabbedPane paneDadosUsuario;   // aba "Dados do Usuário"
    private JTabbedPane paneConsulta;       // aba "Consulta" (única)
    private JPanel tabDadosUsuario;         // conteúdo da aba "Dados do Usuário"
    private JPanel tabConsulta;             // conteúdo da aba "Consulta"
    private JPanel paneConsultaDados;       // filtros (dentro da aba Consulta)
    private JPanel paneConsultaTabela;      // tabela (dentro da aba Consulta)

    // Título
    private JLabel lblTitulo;

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;

    public UsuarioView() {
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
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
        lblTitulo = new JLabel("Cadastro de Usuários com MVC e DAO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30,30,120));

        // Dados do Usuário
        lblCodigo = new JLabel("Código:");
        lblNome   = new JLabel("Nome:");
        lblLogin  = new JLabel("Login:");
        lblSenha  = new JLabel("Senha:");
        lblAtivo  = new JLabel("Ativo:");

        edtCodigo = new JTextField();
        edtNome   = new JTextField();
        edtLogin  = new JTextField();
        edtSenha  = new JPasswordField();
        chkAtivo  = new JCheckBox();
        chkAtivo.setBackground(new Color(245,250,255));

        // Consulta (filtros)
        lblId1         = new JLabel("ID");
        lblText        = new JLabel("à");
        lblLoginFiltro = new JLabel("Login");
        lblNomeFiltro  = new JLabel("Nome");

        edtId1         = new JTextField();
        edtId2         = new JTextField();
        edtLoginFiltro = new JTextField();
        edtNomeFiltro  = new JTextField();

        btnConsultar   = new JButton("Consulta");
        btnLimpar      = new JButton("Limpa");

       

        // Contêineres
        paneCabecario = new JPanel(null);
        paneCentro    = new JPanel(null);
        paneCentro.setBackground(new Color(245,250,255));

        paneDadosUsuario = new JTabbedPane();
        paneConsulta     = new JTabbedPane();

        tabDadosUsuario    = new JPanel(null);
        tabConsulta        = new JPanel(null);        // raiz da aba "Consulta"
        paneConsultaDados  = new JPanel(null);        // filtros
        paneConsultaTabela = new JPanel(null);        // tabela

        for (JPanel p : new JPanel[]{tabDadosUsuario, tabConsulta, paneConsultaDados, paneConsultaTabela, paneCabecario})
            p.setBackground(new Color(245,250,255));
    }

    private void adicionar() {
        // Cabeçalho (botões)
        paneCabecario.add(btnPrimeiro); paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);  paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);     paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);  paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        paneCentro.add(lblTitulo);
        add(paneCentro);

        // Abas: Dados do Usuário
        tabDadosUsuario.add(lblCodigo); tabDadosUsuario.add(edtCodigo);
        tabDadosUsuario.add(lblNome);   tabDadosUsuario.add(edtNome);
        tabDadosUsuario.add(lblLogin);  tabDadosUsuario.add(edtLogin);
        tabDadosUsuario.add(lblSenha);  tabDadosUsuario.add(edtSenha);
        tabDadosUsuario.add(lblAtivo);  tabDadosUsuario.add(chkAtivo);
        paneDadosUsuario.addTab("Dados do Usuário", tabDadosUsuario);
        paneCentro.add(paneDadosUsuario);

        // Abas: Consulta (apenas UMA aba “Consulta”)
        paneConsultaDados.add(lblId1);          paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);         paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblLoginFiltro);  paneConsultaDados.add(edtLoginFiltro);
        paneConsultaDados.add(lblNomeFiltro);   paneConsultaDados.add(edtNomeFiltro);
        paneConsultaDados.add(btnConsultar);    paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollTabela);

        // Monta a aba "Consulta" com filtros em cima e tabela embaixo
        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneConsulta.addTab("Consulta", tabConsulta);

        paneCentro.add(paneConsulta);
    }

    private void posicionar() {
        // Cabeçalho
        paneCabecario.setBounds(10, 10, 930, 40);
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
        paneCentro.setBounds(10, 60, 930, 580);
        lblTitulo.setBounds(0, 0, 780, 30);

        // TabbedPane: Dados do Usuário
        paneDadosUsuario.setBounds(10, 40, 760, 140);
        lblCodigo.setBounds(10, 15, 60, 25);   edtCodigo.setBounds(75, 15, 100, 25);
        lblNome.setBounds(10, 50, 60, 25);     edtNome.setBounds(75, 50, 300, 25);
        lblLogin.setBounds(390, 50, 50, 25);   edtLogin.setBounds(445, 50, 180, 25);
        lblSenha.setBounds(10, 85, 60, 25);    edtSenha.setBounds(75, 85, 120, 25);
        lblAtivo.setBounds(210, 85, 60, 25);   chkAtivo.setBounds(260, 85, 25, 25);

        // TabbedPane: Consulta (uma aba)
        paneConsulta.setBounds(10, 190, 930, 330);
       
        // Dentro da aba "Consulta"
        tabConsulta.setBounds(0, 0, 930, 330);
        
        // Filtros (topo da aba)
        paneConsultaDados.setBounds(10, 10, 770, 80);
        
        lblId1.setBounds(10, 10, 20, 25);         edtId1.setBounds(35, 10, 80, 25);
        lblText.setBounds(120, 10, 10, 25);       edtId2.setBounds(135, 10, 80, 25);
        lblLoginFiltro.setBounds(230, 10, 40, 25);edtLoginFiltro.setBounds(275, 10, 160, 25);
        lblNomeFiltro.setBounds(445, 10, 40, 25); edtNomeFiltro.setBounds(490, 10, 160, 25);
        btnConsultar.setBounds(660, 10, 90, 25);  btnLimpar.setBounds(660, 45, 90, 25);

        // Tabela (logo abaixo)
        paneConsultaTabela.setBounds(10, 100, 740, 220);
        scrollTabela.setBounds(0, 0, 740, 220);
    }
}
