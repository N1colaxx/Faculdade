package view;

import util.AppUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CompraView extends JPanel implements InterfaceCadastro {

    // Título
    private JLabel lblTitulo;
    // Botões
    private JButton btnIncluir, btnAlterar, btnConsultar, btnExcluir;

    // Labels (Compra)
    private JLabel
            // Dados da Compra
            lblCprCodigo,
            lblUsuCodigo,
            lblForCodigo,
            lblCprEmissao,     // data de emissão (nota)
            lblCprValor,       // somente leitura (exibido no form/tabela, calculado)
            lblCprDesconto,
            lblCprTotal,       // somente leitura (calculado)
            lblCprDtentrada,   // data de entrada no estoque
            lblCprObs,
            // Dados do Produto
            lblProCodigo,
            lblProNome,
            lblProPreco,
            lblProUnidade,
            lblProPeso,
            lblProObs;

    // Campos do formulário (Compra)
    private JTextField
            edtCprCodigo,
            edtUsuCodigo,
            edtForCodigo,
            edtCprEmissao,
            edtCprDesconto,
            edtCprDtentrada,
            edtCprObs,
            // Produto
            edtProCodigo,
            edtProNome,
            edtProPreco,
            edtProPeso,
            edtProObs;

    private JComboBox<String> jcbUnidade;

    // Tabela
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public CompraView() {
        setBackground(new Color(245, 250, 255));
        instanciar();
        adicionar();
        vincularAcoes();
    }

    private void instanciar() {
        // Título
        lblTitulo = new JLabel("Movimentos de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Botões
        btnIncluir   = new JButton("Incluir");
        btnAlterar   = new JButton("Alterar");
        btnConsultar = new JButton("Consultar");
        btnExcluir   = new JButton("Excluir");

        // ===== Campos Compra =====
        lblCprCodigo   = new JLabel("Compra código");         edtCprCodigo   = new JTextField(10);
        lblUsuCodigo   = new JLabel("Usuário código");        edtUsuCodigo   = new JTextField(10);
        lblForCodigo   = new JLabel("Fornecedor código");     edtForCodigo   = new JTextField(10);
        lblCprEmissao  = new JLabel("Emissão");               edtCprEmissao  = new JTextField(10);
        lblCprDesconto = new JLabel("Desconto");              edtCprDesconto = new JTextField(10);
        lblCprDtentrada= new JLabel("Entrada");               edtCprDtentrada= new JTextField(10);
        lblCprObs      = new JLabel("Observação");            edtCprObs      = new JTextField();

        // Somente leitura (se quiser exibir em campos, pode criar JTextField e setEditable(false))
        lblCprValor = new JLabel("Valor");
        lblCprTotal = new JLabel("Valor Total");

        // ===== Campos Produto =====
        lblProCodigo  = new JLabel("Produto código");   edtProCodigo = new JTextField(10);
        lblProNome    = new JLabel("Nome");             edtProNome   = new JTextField(25);
        lblProPreco   = new JLabel("Preço");            edtProPreco  = new JTextField(10);
        lblProUnidade = new JLabel("Unidade");
        lblProPeso    = new JLabel("Peso");             edtProPeso   = new JTextField(20);
        lblProObs     = new JLabel("Observação");       edtProObs    = new JTextField();

        String[] unidade = {"Kg", "UN"};
        jcbUnidade = new JComboBox<>(unidade);

        // ===== Tabela =====
        String[] colunas = {
                "Código Compra", "Emissão", "Entrada",
                "Código Produto", "Nome Produto", "Preço", "Unidade", "Peso",
                "Valor", "Desconto", "Valor Total"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true);
        tabela.setPreferredScrollableViewportSize(new Dimension(1000, 400));
    }

    private void adicionar() {
        JPanel panePrincipal = new JPanel(new BorderLayout(10, 10));
            panePrincipal.setOpaque(true);

            // (título + botões)
            JPanel coluna1 = new JPanel();
                coluna1.setLayout(new BoxLayout(coluna1, BoxLayout.Y_AXIS)); 

                lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

                JPanel paneBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                paneBotoes.setOpaque(false);
                paneBotoes.add(btnIncluir);
                paneBotoes.add(btnAlterar);
                paneBotoes.add(btnConsultar);
                paneBotoes.add(btnExcluir);

            coluna1.add(lblTitulo);
            coluna1.add(paneBotoes);
        panePrincipal.add(coluna1, BorderLayout.NORTH);

            // ===== Formulário ( Compra e Produto) =====
            JPanel paneFormularioPrincipal = new JPanel(new GridLayout(1, 2, 0, 0));
                paneFormularioPrincipal.setOpaque(true);
                paneFormularioPrincipal.setBackground(Color.BLACK);

                GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 8, 5, 8);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridy = 0;

                // ---- Dados da Compra ----
                JPanel colunaCompra = new JPanel();
                    colunaCompra.setLayout(new BoxLayout(colunaCompra, BoxLayout.Y_AXIS));
                    colunaCompra.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel lblTituloC = new JLabel("Dados Compra");
                    lblTituloC.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloC.setForeground(Color.WHITE);

                JPanel paneCompra = new JPanel(new GridBagLayout());
                    AppUI.addCampo(paneCompra, lblCprCodigo,   edtCprCodigo,    gbc);
                    AppUI.addCampo(paneCompra, lblUsuCodigo,   edtUsuCodigo,    gbc);
                    AppUI.addCampo(paneCompra, lblForCodigo,   edtForCodigo,    gbc);
                    AppUI.addCampo(paneCompra, lblCprEmissao,  edtCprEmissao,   gbc);
                    AppUI.addCampo(paneCompra, lblCprDtentrada,edtCprDtentrada, gbc);
                    AppUI.addCampo(paneCompra, lblCprDesconto, edtCprDesconto,  gbc);
                    AppUI.addCampo(paneCompra, lblCprObs,      edtCprObs,       gbc);
                    // Observação: lblCprValor e lblCprTotal são de "somente leitura".

                colunaCompra.add(lblTituloC);
                colunaCompra.add(paneCompra);

                // ---- Dados Produto ----
                JPanel colunaProduto = new JPanel();
                    colunaProduto.setLayout(new BoxLayout(colunaProduto, BoxLayout.Y_AXIS));
                    colunaProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel lblTituloP = new JLabel("Dados Produto");
                    lblTituloP.setAlignmentX(Component.LEFT_ALIGNMENT);
                    lblTituloP.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloP.setForeground(Color.WHITE);

                    JPanel paneProduto = new JPanel(new GridBagLayout());
                        AppUI.addCampo(paneProduto, lblProCodigo,  edtProCodigo, gbc);
                        AppUI.addCampo(paneProduto, lblProNome,    edtProNome,   gbc);
                        AppUI.addCampo(paneProduto, lblProPreco,   edtProPreco,  gbc);
                        AppUI.addCampo(paneProduto, lblProUnidade, jcbUnidade,   gbc);
                        AppUI.addCampo(paneProduto, lblProPeso,    edtProPeso,   gbc);
                        AppUI.addCampo(paneProduto, lblProObs,     edtProObs,    gbc);

                    colunaProduto.add(lblTituloP);
                    colunaProduto.add(paneProduto);

                    // deixar filhos transparentes para aparecer o fundo
                    colunaCompra.setOpaque(false);
                    colunaProduto.setOpaque(false);

                paneFormularioPrincipal.add(colunaCompra);
                paneFormularioPrincipal.add(colunaProduto);

        panePrincipal.add(paneFormularioPrincipal, BorderLayout.CENTER);

            // ===== Tabela com scroll =====
            JScrollPane scroll = new JScrollPane(tabela);
            scroll.setOpaque(true);
            scroll.setBackground(Color.LIGHT_GRAY);
            panePrincipal.add(scroll, BorderLayout.SOUTH);

        add(panePrincipal);
    }

    private void vincularAcoes() {
        btnIncluir.addActionListener(e -> incluir());
        btnAlterar.addActionListener(e -> alterar());
        btnConsultar.addActionListener(e -> consultar());
        btnExcluir.addActionListener(e -> excluir());
    }

    @Override
    public void incluir() {
        System.out.println(" [Compra] Incluir");
    }

    @Override
    public void alterar() {
        System.out.println(" [Compra] Alterar");
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
        return true; // coloque validações reais se necessário
    }

    @Override
    public void limparCampos() {
        System.out.println("Executou -> void limparCampos() [Compra]");
    }
}
