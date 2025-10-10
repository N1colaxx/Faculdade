package view;

import javax.swing.*;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.util.ArrayList;

import controller.UsuarioController;
import model.UsuarioModel;

public class UsuarioView extends JPanel {

    // Botões
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;

    // Campos – Dados do Cadastro
    private JLabel lblCodigo, lblNome, lblLogin, lblSenha, lblAtivo;
    private JTextField edtCodigo, edtNome, edtLogin;
    private JPasswordField edtSenha;
    private JCheckBox chkAtivo;

    // Campos – Consulta
    private JLabel lblId1, lblText, lblLoginFiltro, lblNomeFiltro;
    private JTextField edtId1, edtId2, edtLoginFiltro, edtNomeFiltro;
    private JButton btnConsultar, btnLimpar;

    // Painéis
    private JPanel paneCabecario, paneCentro;
    private JTabbedPane paneDadosUsuario, paneConsulta;
    private JPanel tabDadosUsuario, tabConsulta;
    private JPanel paneConsultaDados, paneConsultaTabela;

    // Título
    private JLabel lblTitulo;

    // Tabela
    private JTable tabela;
    private JScrollPane scrollTabela;
    private UsuarioTableModel tableModel;

    // Estado / dados
    private String operacao = "";
    private ArrayList<UsuarioModel> lista = new ArrayList<>();

    public UsuarioView() {
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
        configurarAcoes();

        consultar();
    }

    private void instanciar() {
        // Botões
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo = new JButton("Próximo");
        btnUltimo = new JButton("Último");
        btnNovo = new JButton("Novo");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnGravar = new JButton("Gravar");

        // Título
        lblTitulo = new JLabel("Cadastro de Usuários", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Campos dados do usuário
        lblCodigo = new JLabel("Código:");
        lblNome = new JLabel("Nome:");
        lblLogin = new JLabel("Login:");
        lblSenha = new JLabel("Senha:");
        lblAtivo = new JLabel("Ativo:");

        edtCodigo = new JTextField();
        edtNome = new JTextField();
        edtLogin = new JTextField();
        edtSenha = new JPasswordField();
        chkAtivo = new JCheckBox();
        chkAtivo.setBackground(new Color(245, 250, 255));

        // Campos consulta
        lblId1 = new JLabel("ID");
        lblText = new JLabel("à");
        lblLoginFiltro = new JLabel("Login");
        lblNomeFiltro = new JLabel("Nome");

        edtId1 = new JTextField();
        edtId2 = new JTextField();
        edtLoginFiltro = new JTextField();
        edtNomeFiltro = new JTextField();

        btnConsultar = new JButton("Consulta");
        btnLimpar = new JButton("Limpa");

        // Tabela
        tableModel = new UsuarioTableModel(lista);
        tabela = new JTable(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setAutoCreateRowSorter(true);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollTabela = new JScrollPane(tabela);

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int viewSel = tabela.getSelectedRow();
            if (viewSel >= 0 && viewSel < lista.size()) {
                int sel = tabela.convertRowIndexToModel(viewSel);
                mostrar(lista.get(sel));
            }
        });

        // Painéis
        paneCabecario = new JPanel(null);
        paneCentro = new JPanel(null);
        paneCentro.setBackground(new Color(245, 250, 255));

        paneDadosUsuario = new JTabbedPane();
        paneConsulta = new JTabbedPane();

        tabDadosUsuario = new JPanel(null);
        tabConsulta = new JPanel(null);
        paneConsultaDados = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        for (JPanel p : new JPanel[]{tabDadosUsuario, tabConsulta, paneConsultaDados, paneConsultaTabela, paneCabecario}) {
            p.setBackground(new Color(245, 250, 255));
        }
    }

    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro);
        paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);
        paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);
        paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);
        paneCabecario.add(btnGravar);
        add(paneCabecario);

        // Centro
        paneCentro.add(lblTitulo);
        add(paneCentro);

        // Aba Dados do Usuário
        tabDadosUsuario.add(lblCodigo);
        tabDadosUsuario.add(edtCodigo);
        tabDadosUsuario.add(lblNome);
        tabDadosUsuario.add(edtNome);
        tabDadosUsuario.add(lblLogin);
        tabDadosUsuario.add(edtLogin);
        tabDadosUsuario.add(lblSenha);
        tabDadosUsuario.add(edtSenha);
        tabDadosUsuario.add(lblAtivo);
        tabDadosUsuario.add(chkAtivo);
        paneDadosUsuario.addTab("Dados do Usuário", tabDadosUsuario);
        paneCentro.add(paneDadosUsuario);

        // Aba Consulta
        paneConsultaDados.add(lblId1);
        paneConsultaDados.add(edtId1);
        paneConsultaDados.add(lblText);
        paneConsultaDados.add(edtId2);
        paneConsultaDados.add(lblLoginFiltro);
        paneConsultaDados.add(edtLoginFiltro);
        paneConsultaDados.add(lblNomeFiltro);
        paneConsultaDados.add(edtNomeFiltro);
        paneConsultaDados.add(btnConsultar);
        paneConsultaDados.add(btnLimpar);

        paneConsultaTabela.add(scrollTabela);

        tabConsulta.add(paneConsultaDados);
        tabConsulta.add(paneConsultaTabela);
        paneConsulta.addTab("Consulta", tabConsulta);
        paneCentro.add(paneConsulta);
    }

    private void posicionar() {
        // Cabeçalho
        paneCabecario.setBounds(10, 10, 930, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);
        btnPrimeiro.setBounds(0, 7, 90, 25);
        btnAnterior.setBounds(95, 7, 90, 25);
        btnProximo.setBounds(190, 7, 90, 25);
        btnUltimo.setBounds(285, 7, 90, 25);
        btnNovo.setBounds(430, 7, 90, 25);
        btnAlterar.setBounds(520, 7, 90, 25);
        btnExcluir.setBounds(610, 7, 90, 25);
        btnGravar.setBounds(835, 7, 90, 25);

        // Centro
        paneCentro.setBounds(10, 60, 930, 580);
        lblTitulo.setBounds(0, 0, 780, 30);

        // Aba Dados do Usuário
        paneDadosUsuario.setBounds(10, 40, 930, 140);
        lblCodigo.setBounds(10, 15, 60, 25);
        edtCodigo.setBounds(75, 15, 100, 25);
        lblNome.setBounds(10, 50, 60, 25);
        edtNome.setBounds(75, 50, 300, 25);
        lblLogin.setBounds(390, 50, 50, 25);
        edtLogin.setBounds(445, 50, 180, 25);
        lblSenha.setBounds(10, 85, 60, 25);
        edtSenha.setBounds(75, 85, 120, 25);
        lblAtivo.setBounds(210, 85, 60, 25);
        chkAtivo.setBounds(260, 85, 25, 25);

        // Aba Consulta
        paneConsulta.setBounds(10, 190, 930, 330);
        tabConsulta.setBounds(0, 0, 930, 330);

        paneConsultaDados.setBounds(10, 10, 770, 80);
        lblId1.setBounds(10, 10, 20, 25);
        edtId1.setBounds(35, 10, 80, 25);
        lblText.setBounds(120, 10, 10, 25);
        edtId2.setBounds(135, 10, 80, 25);
        lblLoginFiltro.setBounds(230, 10, 40, 25);
        edtLoginFiltro.setBounds(275, 10, 160, 25);
        lblNomeFiltro.setBounds(445, 10, 40, 25);
        edtNomeFiltro.setBounds(490, 10, 160, 25);
        btnConsultar.setBounds(660, 10, 90, 25);
        btnLimpar.setBounds(660, 45, 90, 25);

        paneConsultaTabela.setBounds(10, 100, 740, 220);
        scrollTabela.setBounds(0, 0, 740, 220);
    }

    private void configurarAcoes() {
        btnPrimeiro.addActionListener(e -> {
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Usuários Cadastrados !");
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
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Não Existem Usuários Cadastrados !");
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

        btnGravar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Confirma Gravação deste Usuário ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    UsuarioModel u = montarUsuarioDosCampos();
                    UsuarioController ctrl = new UsuarioController();
                    ctrl.gravar(u, getOperacao());
                    JOptionPane.showMessageDialog(this, "Dados Gravados com Sucesso");
                    consultar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro na Gravação \n" + ex.getMessage());
                }
            }
        });

        btnExcluir.addActionListener(e -> {
            setOperacao("");
            if (JOptionPane.showConfirmDialog(this, "Confirma Exclusão deste Registro ?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    UsuarioModel u = montarUsuarioDosCampos();
                    UsuarioController ctrl = new UsuarioController();
                    ctrl.excluir(u);
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
            edtLoginFiltro.setText("");
        });
    }

    private String getOperacao() {
        return operacao;
    }

    private void setOperacao(String operacao) {
        this.operacao = operacao;
        btnGravar.setEnabled(!operacao.isEmpty());
    }

    private void limparCampos() {
        edtCodigo.setText("0");
        edtNome.setText("");
        edtLogin.setText("");
        edtSenha.setText("");
        chkAtivo.setSelected(false);
    }

    private void mostrar(UsuarioModel u) {
        edtCodigo.setText(String.valueOf(u.getUSU_CODIGO()));
        edtNome.setText(u.getUSU_NOME());
        edtLogin.setText(u.getUSU_LOGIN());
        edtSenha.setText(u.getUSU_SENHA());
        chkAtivo.setSelected(u.getUSU_ATIVO() == 1);
    }

    private UsuarioModel montarUsuarioDosCampos() {
        UsuarioModel u = new UsuarioModel();
        int cod = 0;
        try {
            cod = Integer.parseInt(edtCodigo.getText().trim());
        } catch (NumberFormatException e) {
        }
        u.setUSU_CODIGO(cod);
        u.setUSU_NOME(edtNome.getText());
        u.setUSU_LOGIN(edtLogin.getText());
        u.setUSU_SENHA(new String(edtSenha.getPassword()));
        u.setUSU_ATIVO(chkAtivo.isSelected() ? 1 : 0);
        return u;
    }

    private String filtroConsulta() {
        String cond = "";
        if (!edtId1.getText().trim().isEmpty()) {
            cond += "(USU_CODIGO >= " + edtId1.getText().trim() + ")";
        }
        if (!edtId2.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(USU_CODIGO <= " + edtId2.getText().trim() + ")";
        }
        if (!edtNomeFiltro.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(USU_NOME LIKE '%" + edtNomeFiltro.getText().trim() + "%')";
        }
        if (!edtLoginFiltro.getText().trim().isEmpty()) {
            cond += (!cond.isEmpty() ? " AND " : "") + "(USU_LOGIN LIKE '%" + edtLoginFiltro.getText().trim() + "%')";
        }
        return cond;
    }

    private void consultar() {
        try {
            UsuarioController ctrl = new UsuarioController();
            lista = ctrl.consultar(filtroConsulta());
            if (lista == null) {
                lista = new ArrayList<>();
            }

            tableModel = new UsuarioTableModel(lista);
            tabela.setModel(tableModel);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            if (!lista.isEmpty()) {
                mostrarRegistro(0);
            } else {
                JOptionPane.showMessageDialog(this, "Não Existem Usuários Cadastrados !");
                limparCampos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na Consulta do Usuário \n" + ex.getMessage());
        }
    }

    private void mostrarRegistro(int registro) {
        if (lista == null || lista.isEmpty()) {
            return;
        }
        if (registro < 0 || registro >= lista.size()) {
            return;
        }

        mostrar(lista.get(registro));
        int viewIndex = tabela.convertRowIndexToView(registro);
        tabela.changeSelection(viewIndex, 0, false, false);
    }
}
