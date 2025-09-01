package Teste_t02_10_09_25;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaCompras extends JPanel {

    private JLabel lblTitulo;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public TelaCompras() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblTitulo = new JLabel("Movimentos de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Colunas baseadas na tabela compra_produto
        String[] colunas = {"Código", "Compra", "Produto", "Quantidade", "Preço", "Desconto", "Total"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Apenas visualização
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

    // Método para carregar os dados futuramente
    public void adicionarProduto(int codigo, int compra, int produto, double qtde, double preco, double desconto) {
        double total = qtde * preco - desconto;
        modeloTabela.addRow(new Object[]{codigo, compra, produto, qtde, preco, desconto, total});
    }
}
