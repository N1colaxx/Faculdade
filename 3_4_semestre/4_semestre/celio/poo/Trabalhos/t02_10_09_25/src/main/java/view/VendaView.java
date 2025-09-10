package view;
      
import util.AppUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class VendaView extends JPanel implements InterfaceCadastro { 
    // Título
    private JLabel lblTitulo;
    // Botões
    private JButton btnIncluir, btnAlterar, btnConsultar, btnExcluir, btnConfirmar;
    // Campos
    private JLabel
            // Dados da Venda
            lblVdaCodigo,
            lblCliCodigo, // 1 dos 2 obrigatorio, mas so pode ser 1
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
            lblProObs,
            // Forma de Pagamento
            lblFpgNome;

    private JTextField
            // Dados da Venda
            edtVdaCodigo,
            edtUsuCodigo, edtCliCodigo,
            edtVdaData,
            edtVdaTotal, //soemente leitura
            edtVdaDesconto,
            edtVdaObs,
            edtProCodigo; // Dados produto
    
    private JComboBox<String> jcbFpgNome; // dados de produto


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
        btnConfirmar = new JButton("Confimar");
        // Campos Venda
        lblVdaCodigo = new JLabel("Venda codigo");      edtVdaCodigo = new JTextField(10);
        lblCliCodigo = new JLabel("Cliente codigo");    edtCliCodigo = new JTextField(10);
        lblVdaData = new JLabel("Data ");               edtVdaData = new JTextField(10);
        lblVdaValor = new JLabel("Valor ");             
        lblVdaDesconto = new JLabel("Desconto ");       edtVdaDesconto = new JTextField(10);
        lblVdaTotal = new JLabel("Valor Total ");       edtVdaTotal = new JTextField(10); edtVdaTotal.setEditable(false);
        lblVdaObs = new JLabel("Observacao ");          edtVdaObs = new JTextField(); 
        // Campo Produto
        lblProCodigo = new JLabel("Produto Codigo");    edtProCodigo = new JTextField(10);
        
        // Forma Pagamento
        lblFpgNome = new JLabel("Escolha");
        
        String[] forma = {"Pix", "Cartão Credito", "Cartão Debito"};
        jcbFpgNome = new JComboBox<>(forma);
        
        // Tabela
        String[] colunas = {"Codigo Venda", "Data Venda", "Codigo Produto", "Nome Produto", "Preco", "Unidade", "Peso", "Total"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; } // impede de alguem clicar em uma celuda e mudar os dados manualmente
        };
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true); // ocupa todo a altura disponivel
        tabela.setPreferredScrollableViewportSize(new Dimension(1000, 300));
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
                        AppUI.addCampo1(paneVenda, lblProCodigo, edtProCodigo, gbc);
                        AppUI.addCampo1(paneVenda, lblCliCodigo, edtCliCodigo, gbc);
                        AppUI.addCampo1(paneVenda, lblVdaData, edtVdaData, gbc);
                        AppUI.addCampo1(paneVenda, lblVdaDesconto, edtVdaDesconto, gbc);
                        AppUI.addCampo1(paneVenda, lblVdaObs, edtVdaObs, gbc);
                colunaVenda.add(lblTituloV);
                colunaVenda.add(paneVenda);
        
                // Forma de Pagamento
                JPanel colunaFormaPaga = new JPanel();
                    colunaFormaPaga.setLayout(new BoxLayout(colunaFormaPaga, BoxLayout.Y_AXIS));
                    colunaFormaPaga.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel lblTituloP = new JLabel("Forma de Pagamento");
                    lblTituloP.setAlignmentX(Component.LEFT_ALIGNMENT);
                    lblTituloP.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloP.setForeground(Color.WHITE);

                    JPanel paneFormaPaga = new JPanel(new GridBagLayout());
                        AppUI.addCampo1(paneFormaPaga, lblFpgNome, jcbFpgNome, gbc);
                        
                colunaFormaPaga.add(lblTituloP);
                colunaFormaPaga.add(paneFormaPaga);
                colunaFormaPaga.add(btnConfirmar);
                
                // e deixe os filhos transparentes, para a parecer o fundo 
                colunaVenda.setOpaque(false);
                colunaFormaPaga.setOpaque(false);
                
            // ADD colunaVenda e colunaFormaPagao ao PANE de Formulario
            paneFormularioPrincipal.add(colunaVenda);
            paneFormularioPrincipal.add(colunaFormaPaga);

        // ADD o paneFormulario ao panePrincipal
        panePrincipal.add(paneFormularioPrincipal, BorderLayout.CENTER);
        
        
            JPanel coluna2 = new JPanel();
               coluna2.setLayout(new BoxLayout(coluna2, BoxLayout.Y_AXIS));
               
                // colocando a tabela dentro de um panel com SCROLL
                JScrollPane scroll = new JScrollPane(tabela); 
                    scroll.setOpaque(true);
                    scroll.setBackground(Color.LIGHT_GRAY);
                    
                JPanel paneRodape = new JPanel();
                paneRodape.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                    paneRodape.add(lblVdaTotal);
                    paneRodape.add(edtVdaTotal);
            coluna2.add(scroll);
            coluna2.add(paneRodape);
            
            
        // ADD a tabela com o Scroll ao panePrincipal
        panePrincipal.add(coluna2, BorderLayout.SOUTH);
        

        
        
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