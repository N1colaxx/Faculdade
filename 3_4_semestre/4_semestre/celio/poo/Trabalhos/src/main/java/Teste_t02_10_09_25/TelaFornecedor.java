package Teste_t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class TelaFornecedor extends JPanel {

    private JLabel lblTitulo;
    private ViewPessoa panePessoa;
    private JButton btnSalvar, btnCancelar;
    private JTextField edtFantasia, edtFone1, edtFone2;

    public TelaFornecedor() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        panePessoa = new ViewPessoa();

        edtFantasia = new JTextField(20);
        edtFone1 = new JTextField(15);
        edtFone2 = new JTextField(15);

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
        panePessoa.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel extras = new JPanel(new GridBagLayout());
        extras.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        addCampo(extras, "Nome Fantasia:", edtFantasia, gbc);
        addCampo(extras, "Fone 1:", edtFone1, gbc);
        addCampo(extras, "Fone 2:", edtFone2, gbc);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(panePessoa);
        centro.add(Box.createVerticalStrut(15));
        centro.add(extras);
        centro.add(Box.createVerticalStrut(15));
        centro.add(botoes);

        add(centro, BorderLayout.CENTER);
    }

    private void addCampo(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++;
    }
}
