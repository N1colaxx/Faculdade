package view;

import javax.swing.*;
import java.awt.*;

public class ClienteView extends JPanel {

    private JLabel lblTitulo;
    private PessoaView pessoaView;
    private JTextField edtLimiteCredito;
    private JButton btnSalvar, btnCancelar;

    public ClienteView() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        pessoaView = new PessoaView();

        edtLimiteCredito = new JTextField(10);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(0, 150, 0));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(180, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void adicionar() {
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pessoaView.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de limite de crédito
        JPanel painelCredito = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCredito.add(new JLabel("Limite de Crédito:"));
        painelCredito.add(edtLimiteCredito);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(pessoaView);
        centro.add(Box.createVerticalStrut(10));
        centro.add(painelCredito);
        centro.add(Box.createVerticalStrut(15));
        centro.add(botoes);

        add(centro, BorderLayout.CENTER);
    }

    // Getters
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public PessoaView getPessoaView() { return pessoaView; }
    public JTextField getEdtLimiteCredito() { return edtLimiteCredito; }
}
