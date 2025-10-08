package Teste_t02_10_09_25;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewCompra extends JPanel {

    private JTextField edtCodigo, edtProduto, edtQtd, edtPreco, edtFornecedor;
    private JButton btnAdicionar, btnRemover, btnSalvar, btnCancelar;
    private JTable tabela;
    private DefaultTableModel modelo;

    public ViewCompra() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ===== Título =====
        JLabel lblTitulo = new JLabel("Movimento de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));
        add(lblTitulo, BorderLayout.NORTH);

        // ===== Formulário =====
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels e Edits padronizados
        JLabel[] labels = {
            new JLabel("Código:"), new JLabel("Produto:"), new JLabel("Qtd:"),
            new JLabel("Preço:"), new JLabel("Fornecedor:")
        };
        JTextField[] edits = {
            edtCodigo = new JTextField(10),
            edtProduto = new JTextField(20),
            edtQtd = new JTextField(5),
            edtPreco = new JTextField(10),
            edtFornecedor = new JTextField(20)
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            gbc.weightx = 0.2;
            form.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.8;
            form.add(edits[i], gbc);
        }

        // Botões do formulário
        JPanel botoesForm = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        botoesForm.add(btnAdicionar);
        botoesForm.add(btnRemover);

        gbc.gridx = 0; gbc.gridy = labels.length; gbc.gridwidth = 2;
        form.add(botoesForm, gbc);

        add(form, BorderLayout.CENTER);

        // ===== Tabela =====
        modelo = new DefaultTableModel(new String[]{"Código", "Produto", "Qtd", "Preço", "Fornecedor", "Total"}, 0);
        tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.SOUTH);

        // ===== Botões gerais =====
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        botoes.add(btnSalvar);
        botoes.add(btnCancelar);

        add(botoes, BorderLayout.PAGE_END);
    }

    // Getters
    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabela() { return tabela; }
    public JButton getBtnAdicionar() { return btnAdicionar; }
    public JButton getBtnSalvar() { return btnSalvar; }
}
