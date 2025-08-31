package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class TelaUsuarios extends JPanel {

    private JLabel lblTitulo, lblNomeUser, lblLoginUser, lblSenhaUser;
    private JTextField edtNomeUser, edtLoginUser;
    private JPasswordField edtSenhaUser;
    private JButton btnCadastrar, btnCancelar;

    public TelaUsuarios() {
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("CADASTRO DE USUÁRIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        lblNomeUser = new JLabel("Nome:");
        lblLoginUser = new JLabel("Login (e-mail):");
        lblSenhaUser = new JLabel("Senha:");

        edtNomeUser = new JTextField(20);
        edtLoginUser = new JTextField(20);
        edtSenhaUser = new JPasswordField(20);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(Color.green);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.red);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void adicionar() {
        int xLbl = 50, xField = 220;
        int larguraLbl = 140, larguraField = 220;
        int altura = 28, y = 70, passo = 45;

        // Título
        lblTitulo.setBounds(50, 20, 400, 30);
        add(lblTitulo);

        // Nome
        lblNomeUser.setBounds(xLbl, y, larguraLbl, altura);
        edtNomeUser.setBounds(xField, y, larguraField, altura);
        add(lblNomeUser); add(edtNomeUser);

        // Login
        y += passo;
        lblLoginUser.setBounds(xLbl, y, larguraLbl, altura);
        edtLoginUser.setBounds(xField, y, larguraField, altura);
        add(lblLoginUser); add(edtLoginUser);

        // Senha
        y += passo;
        lblSenhaUser.setBounds(xLbl, y, larguraLbl, altura);
        edtSenhaUser.setBounds(xField, y, larguraField, altura);
        add(lblSenhaUser); add(edtSenhaUser);

        // Botões
        y += passo;
        btnCancelar.setBounds(80, y, 140, altura);
        btnCadastrar.setBounds(250, y, 140, altura);
        add(btnCancelar); add(btnCadastrar);
    }
}
