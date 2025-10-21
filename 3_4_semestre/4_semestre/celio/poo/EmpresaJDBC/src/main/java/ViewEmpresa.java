

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ViewEmpresa extends JFrame implements InterfaceFrame, InterfaceCrud {

    private JLabel lblTitulo;
    private JLabel lblEmprId;
    private JLabel lblEmprNome;
    private JLabel lblEmprRamo;
    private JTextField txtEmprId;
    private JTextField txtEmprNome;
    private JTextField txtEmprRamo;
    private JButton btnIncluir;
    private JButton btnAlterar;
    private JButton btnConsultar;
    private JButton btnExcluir;
    private JButton btnSair;

    private ConexaoJDBC conexaojdbc = new ConexaoJDBC();

    public ViewEmpresa() {
        setLayout(null);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(640, 300));
        setTitle("CRUD da tabela Empresa");

        instanciar();
        adicionar();
        posicionar();
        eventos();
        definirFoco();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void instanciar() {
        lblTitulo = new JLabel("Cadastro de Empresas");
        lblEmprId = new JLabel("ID");
        lblEmprNome = new JLabel("Nome");
        lblEmprRamo = new JLabel("Ramo");
        txtEmprId = new JTextField();
        txtEmprNome = new JTextField();
        txtEmprRamo = new JTextField();
        btnIncluir = new JButton("Incluir");
        btnAlterar = new JButton("Alterar");
        btnConsultar = new JButton("Consultar");
        btnExcluir = new JButton("Excluir");
        btnSair = new JButton("Sair");
    }

    @Override
    public void adicionar() {
        add(lblTitulo);
        add(lblEmprId);
        add(lblEmprNome);
        add(lblEmprRamo);
        add(txtEmprId);
        add(txtEmprNome);
        add(txtEmprRamo);
        add(btnIncluir);
        add(btnAlterar);
        add(btnConsultar);
        add(btnExcluir);
        add(btnSair);
    }

    @Override
    public void posicionar() {
        lblTitulo.setBounds(250, 10, 400, 25);
        lblEmprId.setBounds(50, 50, 100, 25);
        lblEmprNome.setBounds(50, 80, 100, 25);
        lblEmprRamo.setBounds(50, 110, 100, 25);
        txtEmprId.setBounds(150, 50, 100, 25);
        txtEmprNome.setBounds(150, 80, 300, 25);
        txtEmprRamo.setBounds(150, 110, 300, 25);
        btnIncluir.setBounds(20, 200, 110, 25);
        btnAlterar.setBounds(140, 200, 110, 25);
        btnConsultar.setBounds(260, 200, 110, 25);
        btnExcluir.setBounds(380, 200, 110, 25);
        btnSair.setBounds(500, 200, 110, 25);
    }

    @Override
    public void eventos() {
        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluir();
                limpar();
            }
        }
        );

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterar();
                limpar();
            }
        }
        );

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
                limpar();
            }
        }
        );

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        }
        );

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
        );
    }

    @Override
    public void definirFoco() {
    //     // Definir ordem de foco com TAB
    //     txtEmprId.setNextFocusableComponent(txtEmprNome);
    //     txtEmprNome.setNextFocusableComponent(txtEmprRamo);
    //     txtEmprRamo.setNextFocusableComponent(btnIncluir);

    //     // Ordem dos botões (opcional)
    //     btnIncluir.setNextFocusableComponent(btnAlterar);
    //     btnAlterar.setNextFocusableComponent(btnConsultar);
    //     btnConsultar.setNextFocusableComponent(btnExcluir);
    //     btnExcluir.setNextFocusableComponent(btnSair);
    //     btnSair.setNextFocusableComponent(txtEmprId); // Ciclo completo
    }

    @Override
    public void incluir() {
        if (validar(1)) {
            String nome = txtEmprNome.getText();
            String ramo = txtEmprRamo.getText();

            String sql = "INSERT INTO EMPRESA (EMPR_NOME, EMPR_RAMO) "
                    + "VALUES ('" + nome + "', '" + ramo + "')";
            conexaojdbc.incluir(sql);
        }
    }

    @Override
    public void alterar() {
        if (validar(2)) {
            String id = txtEmprId.getText();
            String nome = txtEmprNome.getText();
            String ramo = txtEmprRamo.getText();

            String sql = "UPDATE EMPRESA SET EMPR_NOME = '" + nome
                    + "', EMPR_RAMO = '" + ramo + "' WHERE EMPR_ID = " + id;
            conexaojdbc.alterar(sql);
        }
    }

    @Override
    public void consultar() {
        if (validar(3)) {
            String id = txtEmprId.getText();
            String sql = "SELECT * FROM EMPRESA WHERE EMPR_ID = " + id;
            ResultSet rs = conexaojdbc.consultar(sql);
            try {
                rs.next();
                if (rs.getRow() > 0) { // testa se existe registro
                    txtEmprNome.setText(rs.getString("EMPR_NOME"));
                    txtEmprRamo.setText(rs.getString("EMPR_RAMO"));
                } else {
                    JOptionPane.showMessageDialog(null, "Empresa Não Encontrada! \n");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na Consulta SQL. \n" + ex.getMessage());
            }
        }
    }

    @Override
    public void excluir() {
        if (validar(4)) {
            int x = JOptionPane.showConfirmDialog(this, "Confirma Exclusão de Registro?", "Confirmação",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (x == JOptionPane.YES_OPTION) {
                String id = txtEmprId.getText();
                String sql = "DELETE FROM EMPRESA WHERE EMPR_ID = " + id;
                conexaojdbc.excluir(sql);
            }
        }
    }

    @Override
    public boolean validar(int operacao) {
        boolean resultado = false;
        switch (operacao) {
            case 1: // incluir
                resultado = (!txtEmprNome.getText().isEmpty()
                        && !txtEmprRamo.getText().isEmpty());
                break;
            case 2: // alterar
                resultado = (!txtEmprId.getText().isEmpty()
                        && !txtEmprNome.getText().isEmpty()
                        && !txtEmprRamo.getText().isEmpty());
                break;
            default: // consultar ou excluir
                resultado = (!txtEmprId.getText().isEmpty());
                break;
        }

        if (!resultado) {
            JOptionPane.showMessageDialog(null, "Preencha os dados necessários");
        }
        return resultado;
    }

    @Override
    public void limpar() {
        txtEmprId.setText("");
        txtEmprNome.setText("");
        txtEmprRamo.setText("");
    }

}
