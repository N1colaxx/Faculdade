package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CompraView extends JPanel implements InterfaceCadastro {

    // Título
    private JLabel lblTitulo;
    private JButton btnIncluir, btnAlterar, btnConsultar, btnExcluir;

    // Campos do formulário
    private JTextField edtCodigo, edtCompra, edtProduto, edtQtde, edtPreco, edtDesconto;

    // Tabela
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public CompraView() {
        setBackground(new Color(245, 250, 255));
        setLayout(new BorderLayout());

        instanciar();
        adicionar();
        vincularAcoes();
    }

    private void instanciar() {
        // Título (mantido no mesmo formato)
        lblTitulo = new JLabel("Movimentos de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Botões
        btnIncluir    = new JButton("Incluir");
        btnAlterar    = new JButton("Alterar");
        btnConsultar  = new JButton("Consultar");
        btnExcluir    = new JButton("Excluir");

        // Campos
        edtCodigo   = new JTextField(12);
        edtCompra   = new JTextField(12);
        edtProduto  = new JTextField(12);
        edtQtde     = new JTextField(12);
        edtPreco    = new JTextField(12);
        edtDesconto = new JTextField(12);

        // Tabela
        String[] colunas = {"Código", "Compra", "Produto", "Quantidade", "Preço", "Desconto", "Total"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setFillsViewportHeight(true);
        tabelaProdutos.setPreferredScrollableViewportSize(new Dimension(760, 250));
    }

    private void adicionar() {
        // Coluna principal
        JPanel coluna = new JPanel();
        coluna.setOpaque(false);
        coluna.setLayout(new BoxLayout(coluna, BoxLayout.Y_AXIS));
        coluna.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        coluna.add(lblTitulo);
        coluna.add(Box.createVerticalStrut(12));

        // Linha de botões (lado a lado)
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botoes.setOpaque(false);
        botoes.add(btnIncluir);
        botoes.add(btnAlterar);
        botoes.add(btnConsultar);
        botoes.add(btnExcluir);
        coluna.add(botoes);
        coluna.add(Box.createVerticalStrut(12));

        // Formulário centralizado (GridBag)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        addCampo(form, new JLabel("Código:"),   edtCodigo,   gbc);
        addCampo(form, new JLabel("Compra:"),   edtCompra,   gbc);
        addCampo(form, new JLabel("Produto:"),  edtProduto,  gbc);
        addCampo(form, new JLabel("Quantidade:"), edtQtde,   gbc);
        addCampo(form, new JLabel("Preço:"),    edtPreco,    gbc);
        addCampo(form, new JLabel("Desconto:"), edtDesconto, gbc);

        coluna.add(form);
        coluna.add(Box.createVerticalStrut(12));

        // Tabela
        JScrollPane scroll = new JScrollPane(tabelaProdutos);
        scroll.getViewport().setBackground(getBackground());
        coluna.add(scroll);

        add(coluna, BorderLayout.CENTER);
    }

    private void addCampo(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        // Coluna do rótulo
        gbc.gridx = 0;
        gbc.weightx = 0;
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(110, 25)); // largura consistente
        panel.add(label, gbc);

        // Coluna do campo
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);

        // Próxima linha
        gbc.gridy++;
    }

    private void vincularAcoes() {
        btnIncluir.addActionListener(e -> incluir());
        btnAlterar.addActionListener(e -> alterar());
        btnConsultar.addActionListener(e -> consultar());
        btnExcluir.addActionListener(e -> excluir());
    }

    @Override
    public void incluir() {
        limparCampos();
        edtCodigo.requestFocus();
        System.out.println(" [Compra] Incluir");
    }

    @Override
    public void alterar() {

    }

    @Override
    public void consultar() {
        System.out.println(" [Compra] Consultar");
    }

    @Override
    public void excluir() {
        System.out.println(" [Compra] Excluir");
    }



    @Override
    public boolean validar(int operacao) {
        // coloque validações reais aqui (ex.: campos obrigatórios)
        return true;
    }


    @Override
    public void limparCampos() {
        edtCodigo.setText("");
        edtCompra.setText("");
        edtProduto.setText("");
        edtQtde.setText("");
        edtPreco.setText("");
        edtDesconto.setText("");
    }
}
