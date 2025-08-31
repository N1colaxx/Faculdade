package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class TelaCliente extends JPanel {

    private JLabel lblTitulo;
    private ViewPessoa panePessoa;
    private JButton btnSalvar, btnCancelar;

    public TelaCliente() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        panePessoa = new ViewPessoa();

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

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));
        centro.add(panePessoa);
        centro.add(Box.createVerticalStrut(15));
        centro.add(botoes);

        add(centro, BorderLayout.CENTER);
    }
}
