package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneFornecedor extends JPanel {

    private PanePessoa panePessoa;
    private JLabel lblTitulo, lblFantasia, lblFone1, lblFone2;
    private JTextField edtFantasia, edtFone1, edtFone2;
    private JButton btnSalvar, btnCancelar;

    public PaneFornecedor() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());

        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        panePessoa = new PanePessoa();

        lblFantasia = new JLabel("Nome Fantasia:");
        edtFantasia = new JTextField(20);

        lblFone1 = new JLabel("Fone 1:");
        edtFone1 = new JTextField(15);

        lblFone2 = new JLabel("Fone 2:");
        edtFone2 = new JTextField(15);

        btnSalvar = new JButton("Salvar Fornecedor");
        btnSalvar.setBackground(new Color(0, 200, 0));
        btnSalvar.setForeground(Color.white);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(200, 0, 0));
        btnCancelar.setForeground(Color.white);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void adicionar() {
        // Painel central com BoxLayout vertical
        JPanel centro = new JPanel();
        centro.setBackground(getBackground());
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panePessoa.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel para campos adicionais (Fantasia, Fones)
        JPanel campos = new JPanel();
        campos.setBackground(getBackground());
        campos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        addCampo(campos, lblFantasia, edtFantasia, gbc);
        addCampo(campos, lblFone1, edtFone1, gbc);
        addCampo(campos, lblFone2, edtFone2, gbc);

        // Painel para botões
        JPanel botoes = new JPanel();
        botoes.setBackground(getBackground());
        botoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        // Adiciona ao painel central
        centro.add(Box.createVerticalStrut(20));
        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(panePessoa);
        centro.add(Box.createVerticalStrut(10));
        centro.add(campos);
        centro.add(Box.createVerticalStrut(10));
        centro.add(botoes);
        centro.add(Box.createVerticalStrut(20));

        add(centro, BorderLayout.CENTER);
    }

    private void addCampo(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        // lbl
        gbc.gridx = 0;
        panel.add(label, gbc);
        // edt
        gbc.gridx = 1;
        panel.add(field, gbc);
        gbc.gridy++; // proxima coluna
    }
}
