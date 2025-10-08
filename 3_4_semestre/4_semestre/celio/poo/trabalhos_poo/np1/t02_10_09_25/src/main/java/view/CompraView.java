package view;

import util.AppUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CompraView extends JPanel {

    // Título
    private JLabel lblTitulo;
    // Botões
    private JButton btnIncluir, btnConfirmar;

    private JLabel
            // Dados da Compra
            lblCprCodigo,
            lblForCodigo,
            lblCprEmissao,     // data de emissão (nota)
            lblCprValor,       // somente leitura (exibido no form/tabela, calculado)
            lblCprDesconto,
            lblCprTotal,       // somente leitura (calculado)
            lblCprDtentrada,   // data de entrada no estoque
            lblCprObs,
            // Dados do Produto
            lblProCodigo,
            // Forma de Pagamento
            lblFpgNome;

    // Campos do formulário (Compra)
    private JTextField
            //Dados Compra
            edtCprCodigo,
            edtForCodigo,
            edtCprEmissao,
            edtCprValor,
            edtCprDesconto,
            edtCprTotal,
            edtCprDtentrada,
            edtCprObs,
            // Produto
            edtProCodigo;
    private JComboBox<String> jcbFpgNome; // dados de produto



    // Tabela
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    
    // ===== coluna lateral de ações (fora da tabela principal) =====
    private JTable tabelaAcoes;
    private DefaultTableModel modeloAcoes;

    
    public CompraView() {
        setBackground(new Color(245, 250, 255));
        
        instanciar();
        adicionar();
    }

        private void instanciar() {
        // Título
        lblTitulo = new JLabel("Movimentos de Compra", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));
        // Botões
        btnIncluir   = new JButton("Incluir");
        btnConfirmar = new JButton("Confimar");
            btnConfirmar.setBackground(Color.GREEN);
            btnConfirmar.addActionListener(e -> System.out.println("[btnConfirmar] foi clicado em COMPRA para validar (Forma de Pagamento)"));
        // ===== Campos Compra =====
        lblCprCodigo   = new JLabel("Compra código");         edtCprCodigo   = new JTextField(10);
        lblForCodigo   = new JLabel("Fornecedor código");     edtForCodigo   = new JTextField(10);
        lblCprEmissao  = new JLabel("Emissão");               edtCprEmissao  = new JTextField(10);
        lblCprValor = new JLabel("Valor");
        lblCprDesconto = new JLabel("Desconto");              edtCprDesconto = new JTextField(10);
        lblCprTotal = new JLabel("Valor Total");              edtCprTotal = new JTextField(10); edtCprTotal.setEditable(false);
        lblCprDtentrada= new JLabel("Entrada");               edtCprDtentrada= new JTextField(10);
        lblCprObs      = new JLabel("Observação");            edtCprObs      = new JTextField();
        // Forma Pagamento
        lblFpgNome = new JLabel("Escolha");
        
        String[] forma = {"Pix", "Cartão Credito", "Cartão Debito"};
        jcbFpgNome = new JComboBox<>(forma);

        // Tabela
        // Lembrar  de quando for popular ter que colocar um campo para a ultima colunar(Acao)
        String[] colunas = {"Codigo Compra_Produto", "Codigo Compra", "Data Emisao", "Valor Total"};
        
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) {return false;} // tabela toda não editável
        };
        
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true); // ocupa todo a altura disponivel
        tabela.setPreferredScrollableViewportSize(new Dimension(750, 430));
        
    
        // ===== TABELA DE AÇÕES (row header, fora da principal) =====
        modeloAcoes = new DefaultTableModel(new String[]{"Alterar", "Consultar", "Excluir"}, 0) { // o zero"0" indica qtd de linha iniciais 
            @Override public boolean isCellEditable(int row, int column) { return false; } // tabela toda não editável
        };
        tabelaAcoes = new JTable(modeloAcoes);  
        // aparência enxuta
        tabelaAcoes.setTableHeader(null);                 // sem cabeçalho
        tabelaAcoes.setRowHeight(tabela.getRowHeight());  // mesma altura de linha
        tabelaAcoes.setShowGrid(false);
        tabelaAcoes.setIntercellSpacing(new Dimension(0,0));
        // largura compacta
        tabelaAcoes.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabelaAcoes.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabelaAcoes.getColumnModel().getColumn(2).setPreferredWidth(70);
        tabelaAcoes.setPreferredScrollableViewportSize(new Dimension(220, 0));
        

        // Clique nos "botões" (texto clicável)
        tabelaAcoes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int viewRow = tabelaAcoes.rowAtPoint(e.getPoint());  // Descobre o índice da LINHA (na VIEW) onde o mouse foi clicado
                int col     = tabelaAcoes.columnAtPoint(e.getPoint()); //  Descobre o índice da COLUNA (na VIEW) onde o mouse foi clicado
                if (viewRow < 0 || col < 0) return;  // Se clicou fora de uma célula válida (linha/coluna = -1), sai sem fazer nada

                switch (col) {
                    case 0 -> { // Alterar
                        System.out.println("[Ações] Alterar compra "  );
                    }
                    case 1 -> { // Consultar
                        System.out.println("[Ações] Consultar compra "  );
                    }
                    case 2 -> { // Excluir
                        System.out.println("[Ações] Excluir compra " );

                    }
                }
            }
        });
        
        try {
            System.out.println("add dados em Tab Venda");
                    // Exemplo de como popular
            modeloTabela.addRow(new Object[]{1, 101, "2025-09-10", 500.0});
            modeloAcoes.addRow(new Object[]{"Alterar", "Consultar", "Excluir"});

            modeloTabela.addRow(new Object[]{2, 102, "2025-09-11", 300.0});
            modeloAcoes.addRow(new Object[]{"Alterar", "Consultar", "Excluir"});
        } catch (Exception e) {
            System.out.println("\n ERRO! add dados em Tab Venda \n " + e);
        }
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
                          
                // Dados da Compra
                JPanel colunaCompra = new JPanel();
                    colunaCompra.setLayout(new BoxLayout(colunaCompra, BoxLayout.Y_AXIS));
                    colunaCompra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               
                    JLabel lblTituloC = new JLabel("Dados Compra");
                    lblTituloC.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloC.setForeground(Color.WHITE);
                    
                JPanel paneCompra = new JPanel(new GridBagLayout());
                    AppUI.addCampo1(paneCompra, lblCprCodigo,   edtCprCodigo,    gbc);
                    AppUI.addCampo1(paneCompra, lblForCodigo,   edtForCodigo,    gbc);
                    AppUI.addCampo1(paneCompra, lblCprEmissao,  edtCprEmissao,   gbc);
                    AppUI.addCampo1(paneCompra, lblCprDtentrada,edtCprDtentrada, gbc);
                    AppUI.addCampo1(paneCompra, lblCprDesconto, edtCprDesconto,  gbc);
                    AppUI.addCampo1(paneCompra, lblCprObs,      edtCprObs,       gbc);
                    
                colunaCompra.add(lblTituloC);
                colunaCompra.add(paneCompra);

        
                // Dados Forma de Pagamento
                JPanel colunaFormaPaga = new JPanel();
                    colunaFormaPaga.setLayout(new BoxLayout(colunaFormaPaga, BoxLayout.Y_AXIS));
                    colunaFormaPaga.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    JLabel lblTituloP = new JLabel("Forma de Pagamento");
                    lblTituloP.setAlignmentX(Component.LEFT_ALIGNMENT);
                    lblTituloP.setFont(new Font("Arial", Font.BOLD, 14));
                    lblTituloP.setForeground(Color.WHITE);

                    JPanel paneFormaPaga = new JPanel(new GridBagLayout());
                        AppUI.addCampo1(paneFormaPaga, lblFpgNome, jcbFpgNome, gbc);
                        
                    JPanel paneBtnFormaPaga = new JPanel();
                        paneBtnFormaPaga.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
                        paneBtnFormaPaga.setOpaque(true);
                        paneBtnFormaPaga.add(btnConfirmar);
                        
                colunaFormaPaga.add(lblTituloP);
                colunaFormaPaga.add(paneFormaPaga);
                colunaFormaPaga.add(paneBtnFormaPaga);
                
                // e deixe os filhos transparentes, para a parecer o fundo 
                colunaCompra.setOpaque(false);
                colunaFormaPaga.setOpaque(false);
                
            // ADD  Comprae colunaFormaPagao ao PANE de Formulario
            paneFormularioPrincipal.add(colunaCompra);
            paneFormularioPrincipal.add(colunaFormaPaga);

        // ADD o paneFormulario ao panePrincipal
        panePrincipal.add(paneFormularioPrincipal, BorderLayout.CENTER);
        
        
            JPanel coluna2 = new JPanel();
               coluna2.setLayout(new BoxLayout(coluna2, BoxLayout.Y_AXIS));
               
            // colocando a tabela dentro de um panel com SCROLL
            JScrollPane scroll = new JScrollPane(tabela);
            scroll.setRowHeaderView(tabelaAcoes);

            // opcional: dar um “título” para as ações no canto superior da row header
            JPanel headerAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
            headerAcoes.add(new JLabel("............................ Ações ...................................."));
            scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, headerAcoes);
                    
                JPanel paneRodape = new JPanel();
                paneRodape.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
                    edtCprTotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));;
                    paneRodape.add(lblCprTotal);
                    paneRodape.add(edtCprTotal);
                    
            coluna2.add(scroll);
            coluna2.add(paneRodape);
            
        // ADD a tabela com o Scroll ao panePrincipal
        panePrincipal.add(coluna2, BorderLayout.SOUTH);

        // ADD o panePrincipal ao paneVendaView
        add(panePrincipal);
    }
    

}
