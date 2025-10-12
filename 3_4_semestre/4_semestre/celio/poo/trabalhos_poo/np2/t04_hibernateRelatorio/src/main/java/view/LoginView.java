package view;

import java.awt.*;
import javax.swing.*;

public class LoginView extends JPanel {

    private JLabel lblTitulo, lblEmail, lblSenha, lblEsqueci;
    private JTextField edtEmail;
    private JPasswordField edtSenha;
    private JButton btnEntrar;
    private GridBagConstraints gbc;
    private String emailLogado;
    private String senhaLogado;


    public LoginView() {
        setLayout(new GridBagLayout());
        instanciar();
        cfgPane();
        adicionar();
        
        emailLogado = null;
    }

    private void instanciar() {
        lblTitulo = new JLabel("Tela de Login");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        lblEmail = new JLabel("Digite seu email:");
        edtEmail = new JTextField(20);

        lblSenha = new JLabel("Digite sua senha:");
        edtSenha = new JPasswordField(20);

        lblEsqueci = new JLabel("<html><u>Esqueci minha senha</u></html>");
        lblEsqueci.setForeground(Color.BLUE);

        btnEntrar = new JButton("Entrar");
    }

    private void cfgPane() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // espaçamento
        gbc.fill = GridBagConstraints.HORIZONTAL; // ocupa largura quando possível
    }

    // Posiciono + Adiciono 
    private void adicionar() {
        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(edtEmail, gbc);

        // Senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        add(lblSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(edtSenha, gbc);

        // Esqueci senha
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        add(lblEsqueci, gbc);

        // Botão Entrar
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnEntrar, gbc);
    }
    
    // lógica de login 
    public void setOnLogin(java.util.function.BiConsumer<String,String> handler) {
        btnEntrar.addActionListener(e -> {
            // Validação simples no formulário
            String email = getEmail();
            String senha = getSenha();

            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe e-mail e senha.");
                return;
            }
            // Formato básico de e-mail (bem simples)
            if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                JOptionPane.showMessageDialog(this, "E-mail inválido.");
                return;
            }
            // Regras mínimas de senha (opcional)
            if (senha.length() < 4) {
                JOptionPane.showMessageDialog(this, "Senha muito curta.");
                return;
            }

            emailLogado = email;
            // Se passou na validação da View, chama o handler (controller)
            handler.accept(email, senha);
        });
    }



    // Getters 
    public JButton getBtnEntrar(){ return btnEntrar; }
    public String getEmail() { return edtEmail.getText().trim(); }
    public String getSenha() { return new String(edtSenha.getPassword()); }
    public String gerUserLogado () { return emailLogado; }
}
