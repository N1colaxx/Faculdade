package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneCliente extends JPanel {

    private PanePessoa panePessoa;
    private JLabel lblTitulo;
    private JButton btnSalvar, btnCancelar;

    public PaneCliente() {
        setBackground(new Color(245, 250, 255)); // fundo suave;;;

        setLayout(new BorderLayout());

        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Cliente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        panePessoa = new PanePessoa();

        btnSalvar = new JButton("Salvar Cliente");
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

        // Painel para botões
        JPanel botoes = new JPanel();
        botoes.setBackground(getBackground());
        botoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);


        centro.add(Box.createVerticalStrut(20)); // espaço no topo
        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(panePessoa);
        centro.add(Box.createVerticalStrut(10));
        centro.add(botoes);
        centro.add(Box.createVerticalStrut(20)); // espaço no fim

        add(centro, BorderLayout.CENTER);
    }
}
