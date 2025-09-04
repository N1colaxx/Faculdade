package view;

import util.AppUI;      
import view.PessoaView;
import javax.swing.*;
import java.awt.*;

public class FornecedorView extends JPanel {

    private PessoaView pessoaView;
    private JLabel lblTitulo, lblFantasia, lblFone1, lblFone2;
    private JButton btnCadastrar, btnCancelar;
    private JTextField edtFantasia, edtFone1, edtFone2;

    public FornecedorView() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        pessoaView = new PessoaView();

        lblFantasia = new JLabel("Nome Fantasia:");
        lblFone1 = new JLabel("Fone 1:");
        lblFone2 = new JLabel("Fone 2:");

        edtFantasia = new JTextField(20);
        edtFone1 = new JTextField(15);
        edtFone2 = new JTextField(15);

        // entram alinhados no grid da PessoaView
        pessoaView.addCampoExtra(lblFantasia, edtFantasia);
        pessoaView.addCampoExtra(lblFone1, edtFone1);
        pessoaView.addCampoExtra(lblFone2, edtFone2);

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

        // Centro: PessoaView centralizado 
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
