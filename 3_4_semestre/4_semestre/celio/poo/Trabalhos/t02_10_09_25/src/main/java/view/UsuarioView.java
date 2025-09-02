package view;

import javax.swing.*;
import java.awt.*;

public class UsuarioView extends JPanel {

    private JLabel lblTitulo, lblNome, lblLogin, lblSenha;
    private JTextField edtNome, edtLogin;
    private JPasswordField edtSenha;
    private JButton btnCadastrar, btnCancelar;

    public UsuarioView() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Usuário", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30,30,120));

        lblNome = new JLabel("Nome:");
        lblLogin = new JLabel("Login (e-mail):");
        lblSenha = new JLabel("Senha:");

        edtNome = new JTextField(20);
        edtLogin = new JTextField(20);
        edtSenha = new JPasswordField(20);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(0,150,0));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(180,0,0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void adicionar() {
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        addCampo(form, lblNome, edtNome, gbc);
        addCampo(form, lblLogin, edtLogin, gbc);
        addCampo(form, lblSenha, edtSenha, gbc);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnCadastrar);

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(form);
        centro.add(Box.createVerticalStrut(15));
        centro.add(botoes);

        add(centro, BorderLayout.CENTER);
    }

    private void addCampo(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++;
    }
}
