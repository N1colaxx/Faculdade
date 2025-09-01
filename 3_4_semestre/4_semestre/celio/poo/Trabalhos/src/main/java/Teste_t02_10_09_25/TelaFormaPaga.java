package Teste_t02_10_09_25;

import java.awt.*;
import javax.swing.*;

public class TelaFormaPaga extends JPanel {

    private JLabel lblTitulo, lblNomeFormaPaga;
    private JTextField edtNomeFormaPaga;
    private JButton btnSalvar, btnCancelar;

    public TelaFormaPaga() {
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        // Título
        lblTitulo = new JLabel("Cadastro de Forma de Pagamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Campo Nome
        lblNomeFormaPaga = new JLabel("Nome:");
        edtNomeFormaPaga = new JTextField(20);

        // Botões
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
    }

    private void adicionar() {
        JPanel centro = new JPanel();
        centro.setBackground(getBackground());
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        // Título
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(Box.createVerticalStrut(20));
        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(20));

        // Painel do campo Nome
        JPanel painelNome = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelNome.setBackground(getBackground());
        lblNomeFormaPaga.setPreferredSize(new Dimension(100, 25));
        edtNomeFormaPaga.setPreferredSize(new Dimension(200, 25));
        painelNome.add(lblNomeFormaPaga);
        painelNome.add(edtNomeFormaPaga);
        painelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(painelNome);

        centro.add(Box.createVerticalStrut(20));

        // Painel de botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.setBackground(getBackground());
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);
        botoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(botoes);

        centro.add(Box.createVerticalStrut(20)); // espaço no final

        add(centro, BorderLayout.CENTER);
    }
}
