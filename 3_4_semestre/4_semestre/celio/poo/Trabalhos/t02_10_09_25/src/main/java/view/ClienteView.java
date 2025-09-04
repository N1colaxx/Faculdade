package view;

import javax.swing.*;
import java.awt.*;

public class ClienteView extends JPanel {

    private PessoaView pessoaView;
    private JLabel lblTitulo, lblLimiteCredito;
    private JTextField edtLimiteCredito;
    private JButton btnCadastrar, btnCancelar;

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

        // Campo extra alinhado dentro do grid da PessoaView
        lblLimiteCredito = new JLabel("Limite de Crédito:");
        edtLimiteCredito = new JTextField(10);
        pessoaView.addCampoExtra(lblLimiteCredito, edtLimiteCredito);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(0, 150, 0));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(180, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void adicionar() {
        // Título no topo
        add(lblTitulo, BorderLayout.NORTH);

        // Centro: pessoaView centralizado
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                
        centro.setOpaque(false);
        centro.add(pessoaView);
        add(centro, BorderLayout.CENTER);

        // Rodapé: botões centralizados
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoes.add(btnCancelar);
        botoes.add(btnCadastrar);
        add(botoes, BorderLayout.SOUTH);
    }

}
