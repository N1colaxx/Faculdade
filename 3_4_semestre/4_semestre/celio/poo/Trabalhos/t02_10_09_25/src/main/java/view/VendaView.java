package view;
      
import util.AppUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class VendaView extends JPanel implements InterfaceCadastro { 
    // Título
    private JLabel lblTitulo;
    // Botões
    private JButton btnIncluir, btnAlterar, btnConsultar, btnExcluir;
    // Campos
    private JLabel
            // Dados da tab Venda
            lblVdaCodigo,
            lblUsuCodigo, lblCliCodigo, // 1 dos 2 obrigatorio, mas so pode ser 1
            lblVdaData, // deixo para a data automatico e editavel para mudança
            lblVdaValor, // somente leitura
            lblVdaDesconto, 
            lblVdaTotal,// somente leitura
            lblVdaObs, // deixar Opcional
            // Dados do Produto
            lblProCodigo,
            lblProNome,
            lblProPreco,
            lblProUnidade,
            lblProPeso,
            lblProObs;
    private JTextField
            // Dados da tab Venda
            edtVdaCodigo,
            edtUsuCodigo, edtCliCodigo,
            edtVdaData,
            edtVdaDesconto,
            edtVdaObs,
            // Dados do Produto
            edtProCodigo,
            edtProNome,
            edtProPreco,
            edtProPeso,
            edtProObs;
    private JComboBox<String> jcbUnidade; // dados de produto


    // Tabela
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public VendaView() {
        setBackground(new Color(245, 250, 255));
        
        instanciar();
        adicionar();
        vincularAcoes();
    }

    private void instanciar() {
        // Título
        lblTitulo = new JLabel("Movimentos de Venda", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));
        // Botões
        btnIncluir   = new JButton("Incluir");
        btnAlterar   = new JButton("Alterar");
        btnConsultar = new JButton("Consultar");
        btnExcluir   = new JButton("Excluir");
        // Campos Venda
        lblVdaCodigo = new JLabel("Venda codigo");      edtVdaCodigo = new JTextField(10);
        lblUsuCodigo = new JLabel("Usuario codigo");    edtUsuCodigo = new JTextField(10);
        lblCliCodigo = new JLabel("Cliente codigo");    edtCliCodigo = new JTextField(10);
        lblVdaData = new JLabel("Data ");               edtVdaData = new JTextField(10);
        lblVdaValor = new JLabel("Valor ");
        lblVdaDesconto = new JLabel("Desconto ");       edtVdaDesconto = new JTextField(10);
        lblVdaTotal = new JLabel("Valor Total ");
        lblVdaObs = new JLabel("Observacao ");          edtVdaObs = new JTextField(); 
        // Capompos Produto
        lblProCodigo = new JLabel("Produto Codigo");    edtProCodigo = new JTextField(10); 
        lblProNome = new JLabel("Nome ");               edtProNome = new JTextField(25); 
        lblProPreco = new JLabel("Proço ");             edtProPreco = new JTextField(10);
        lblProUnidade = new JLabel("Unidade ");
        lblProPeso = new JLabel("Peso ");               edtProPeso = new JTextField(20);
        lblProObs = new JLabel("Observacao ");          edtProObs = new JTextField(); 
        
        String[] unidade = {"Kg", "UN"};
        jcbUnidade = new JComboBox<>(unidade);
        
        // Tabela
        String[] colunas = {"Codigo Venda", "Data Venda", "Codigo Produto", "Nome Produto", "Preco", "Unidade", "Peso", "Valor", "Desconto", "Valor Total"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; } // impede de alguem clicar em uma celuda e mudar os dados manualmente
        };
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true); // ocupa todo a altura disponivel
        tabela.setPreferredScrollableViewportSize(new Dimension(1000, 400));
    }

    private void adicionar() {
        JPanel panePrincipal = new JPanel(new BorderLayout(10, 10)); // Y | X  espaço entre as regiões 
        panePrincipal.setOpaque(true);


        // Coluna1 => recebe o Titulo da Aba e os BTN
            JPanel coluna1 = new JPanel();
                coluna1.setLayout(new BoxLayout(coluna1, BoxLayout.Y_AXIS)); // crio o pane de columa, depois seto o layout como um Box alinhando em Y
                // Título
                lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
                // Linha de botões
                JPanel paneBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                    paneBotoes.setOpaque(false);
                    paneBotoes.add(btnIncluir);
                    paneBotoes.add(btnAlterar);
                    paneBotoes.add(btnConsultar);
                    paneBotoes.add(btnExcluir);

            coluna1.add(lblTitulo);
            coluna1.add(paneBotoes);
        panePrincipal.add(coluna1, BorderLayout.NORTH);
        
            // Formulário, contem os campos par incerir dados
            JPanel paneFormularioPrincipal = new JPanel(new GridLayout(1, 2, 0, 0)); // y, x
                paneFormularioPrincipal.setOpaque(true);
                paneFormularioPrincipal.setBackground(Color.BLACK);
            
                // Regras pra os componetes do Grid
                GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 8, 5, 8);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridy = 0;
                          
                // Dados da venda
                JPanel colunaVenda = new JPanel();
                    colunaVenda.setLayout(new BoxLayout(colunaVenda, BoxLayout.Y_AXIS));
                    colunaVenda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               
                    JLabel lblTituloV = new JLabel("Dados Venda");
                    lblTituloV.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloV.setForeground(Color.WHITE);
                    
                    JPanel paneVenda = new JPanel(new GridBagLayout());
                        AppUI.addCampo(paneVenda, lblVdaCodigo, edtVdaCodigo, gbc);
                        AppUI.addCampo(paneVenda, lblUsuCodigo, edtUsuCodigo, gbc);
                        AppUI.addCampo(paneVenda, lblCliCodigo, edtCliCodigo, gbc);
                        AppUI.addCampo(paneVenda, lblVdaData, edtVdaData, gbc);
                        AppUI.addCampo(paneVenda, lblVdaDesconto, edtVdaDesconto, gbc);
                        AppUI.addCampo(paneVenda, lblVdaObs, edtVdaObs, gbc);
                colunaVenda.add(lblTituloV);
                colunaVenda.add(paneVenda);
        
                // Dados do Produto
                JPanel colunaProduto = new JPanel();
                    colunaProduto.setLayout(new BoxLayout(colunaProduto, BoxLayout.Y_AXIS));
                    colunaProduto.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel lblTituloP = new JLabel("Dados Produto");
                    lblTituloP.setAlignmentX(Component.LEFT_ALIGNMENT);
                    lblTituloP.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloP.setForeground(Color.WHITE);

                    JPanel paneProduto = new JPanel(new GridBagLayout());
                        AppUI.addCampo(paneProduto, lblProCodigo,edtProCodigo , gbc);
                        AppUI.addCampo(paneProduto, lblProNome,edtProNome , gbc);
                        AppUI.addCampo(paneProduto, lblProPreco,edtProPreco , gbc);
                        AppUI.addCampo(paneProduto, lblProUnidade, jcbUnidade, gbc);
                        AppUI.addCampo(paneProduto, lblProPeso,edtProPeso , gbc);
                        AppUI.addCampo(paneProduto, lblProObs,edtProObs , gbc);
                colunaProduto.add(lblTituloP);
                colunaProduto.add(paneProduto);
                
                // e deixe os filhos transparentes, para a parecer o fundo 
                colunaVenda.setOpaque(false);
                colunaProduto.setOpaque(false);
                
            // ADD colunaVenda e colunaProdutoo ao PANE de Formulario
            paneFormularioPrincipal.add(colunaVenda);
            paneFormularioPrincipal.add(colunaProduto);

        // ADD o paneFormulario ao panePrincipal
        panePrincipal.add(paneFormularioPrincipal, BorderLayout.CENTER);

            // colocando a tabela dentro de um panel com SCROLL
            JScrollPane scroll = new JScrollPane(tabela); 
                scroll.setOpaque(true);
                scroll.setBackground(Color.LIGHT_GRAY);
               
        // ADD a tabela com o Scroll ao panePrincipal
        panePrincipal.add(scroll, BorderLayout.SOUTH);
        
        // ADD o panePrincipal ao paneVendaView
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
        System.out.println(" [Venda] Incluir");
    }

    @Override
    public void alterar() {
        System.out.println(" [Venda] Alterar");
    }

    @Override
    public void consultar() {
        System.out.println(" [Venda] Consultar");
    }

    @Override
    public void excluir() {
        System.out.println(" [Venda] Excluir");
    }


    @Override
    public boolean validar(int operacao) {
        return true; // coloque validações reais se necessário
    }


    @Override
    public void limparCampos() {
        System.out.println("Executiu -> void limparCampos() ");
    }
}