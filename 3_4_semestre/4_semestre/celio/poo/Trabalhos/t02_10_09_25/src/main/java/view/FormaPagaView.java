package view;

import javax.swing.*;
import java.awt.*;

public class FormaPagaView extends JPanel {

    private JLabel lblTitulo, lblNomeFormaPaga;
    private JTextField edtNomeFormaPaga;
    private JButton btnCadastrar, btnCancelar;

    public FormaPagaView() {
        setBackground(new Color(245, 250, 255)); // igual UsuarioView
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Forma de Pagamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        lblNomeFormaPaga = new JLabel("Forma de Pagamento nome:");
        edtNomeFormaPaga = new JTextField(20); // largura definida por colunas, não por weightx

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
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mesmo grid da UsuarioView
        JPanel form = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        addCampo(form, lblNomeFormaPaga, edtNomeFormaPaga, gbc);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.setBackground(getBackground());
        botoes.add(btnCancelar);
        botoes.add(btnCadastrar);

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(form);
        centro.add(Box.createVerticalStrut(15));
        centro.add(botoes);

        add(centro, BorderLayout.CENTER);
    }

    // exatamente como na UsuarioView
    private void addCampo(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++;
    }

}
