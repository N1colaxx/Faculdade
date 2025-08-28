package t02_10_09_25;

import java.awt.*;
import javax.swing.*;

public class PaneLogin extends JPanel {

    private JLabel lblTitulo, lblEmail, lblSenha, lblEsqueci;
    private JTextField edtEmail;
    private JPasswordField edtSenha;
    private JButton btnEntrar;
    private GridBagConstraints gbc;

    public PaneLogin() {
        setLayout(new GridBagLayout()); // <- CORREÇÃO
        instanciar();
        cfgPane();
        adicionar();
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
    
    // Getters 
    public JButton getBtnEntrar(){
        return btnEntrar;
    }
}
