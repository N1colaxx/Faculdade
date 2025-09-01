package Teste_t02_10_09_25;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaVendas extends JPanel {

    private JLabel lblTitulo;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public TelaVendas() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Movimentos de Venda", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        String[] colunas = {"Código", "Venda", "Produto", "Quantidade", "Preço", "Desconto", "Total"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setFillsViewportHeight(true);
        tabelaProdutos.setPreferredScrollableViewportSize(new Dimension(700, 250));
    }

    private void adicionar() {
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));

        JScrollPane scroll = new JScrollPane(tabelaProdutos);
        centro.add(scroll);

        add(centro, BorderLayout.CENTER);
    }

    public void adicionarProduto(int codigo, int venda, int produto, double qtde, double preco, double desconto) {
        double total = qtde * preco - desconto;
        modeloTabela.addRow(new Object[]{codigo, venda, produto, qtde, preco, desconto, total});
    }
}

