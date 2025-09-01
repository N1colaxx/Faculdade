
package t02_10_09_25;

import javax.swing.*;

import java.awt.*;
import javax.swing.table.DefaultTableModel;


public final class ItensMenu extends JPanel{
    
    private ViewPessoa viewPessoa;
    private Main classMain;
    private JLabel lblTitulo, lblNomeFormaPaga, lblNome, lblLogin, lblSenha;
    private JTextField edtNomeFormaPaga, edtNome, edtLogin, edtSenha;
    private JButton btnCadastrar, btnCancelar;
    
    
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    

    public ItensMenu(Main classMain) {
        this.classMain = classMain;
    }
    
    private void inicializarViewPessoa() {
        try {
            System.out.println("\n Inicializando -> View Pessoa (...)");
            viewPessoa = new ViewPessoa();
            System.out.println(" Inicializando -> View Pessoa (SUCESSO!)");
        } catch (Exception e) {
            System.out.println("\n ERRO class AbasCadastro! Inicializando -> View Pessoa (FALHOU!) \n " + e);
        }
    }
    
    
private JPanel paneLefth(JLabel lblTitulo) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout()); // divide em Norte / Centro / Sul

    // TÍTULO centralizado
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblTitulo, BorderLayout.NORTH);

    // BOTÕES lado a lado
    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10)); 
    // 15 = espaço horizontal | 10 = espaço vertical

    btnCadastrar = new JButton("Salvar");
    btnCadastrar.setBackground(new Color(0, 150, 0));
    btnCadastrar.setForeground(Color.WHITE);
    btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));

    btnCancelar = new JButton("Cancelar");
    btnCancelar.setBackground(new Color(180, 0, 0));
    btnCancelar.setForeground(Color.WHITE);
    btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));

    botoes.add(btnCadastrar);
    botoes.add(btnCancelar);

    panel.add(botoes, BorderLayout.SOUTH);

    return panel;
}

    
    
    public void paneCliente() {
        System.out.println("pane de Cliente ABERTO.");
        removeAll(); // limpa tudo antes

        setLayout(new GridLayout(1, 2, 10, 0));  
        // 1 linha, 2 colunas, espaçamento horizontal de 10px

        // Painel ESQUERDA
        lblTitulo = new JLabel("Cadastro de Cliente");
        JPanel esquerda = paneLefth(lblTitulo);

        // Painel DIREITA
        inicializarViewPessoa(); // inicializa o form
        JPanel direita = new JPanel(new BorderLayout());
        direita.add(viewPessoa, BorderLayout.CENTER);

        add(esquerda);
        add(direita);

        classMain.defineFrame(1);


    }

    
    public void paneFornecedor() {
        System.out.println("pane de Fornecedor ABERTO.");
        removeAll();                     

        lblTitulo = new JLabel("Cadastro de Fornecedor", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30, 30, 120));
        
        inicializarViewPessoa();
        viewPessoa.adicionarExtras();
        
        btnCadastrar = new JButton("Salvar");
        btnCadastrar.setBackground(new Color(0, 150, 0));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(180, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        
        add(lblTitulo);
        add(viewPessoa);
        add(btnCadastrar);
        add(btnCancelar);
        
        
        classMain.defineFrame(1);
        
    }
    
    public void paneFormaPagamento() {
        removeAll();
        System.out.println("pane de Forma de Pagamento ABERTO.");

        lblTitulo = new JLabel("Cadastro de Forma de Pagamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 120));

        // Campo Nome
        lblNomeFormaPaga = new JLabel("Nome:");
        edtNomeFormaPaga = new JTextField(20);
                
        add(lblTitulo);
        add(lblNomeFormaPaga); add(edtNomeFormaPaga);
        add(btnCadastrar);
        add(btnCancelar);
                                        
        classMain.defineFrame(1);
    }
    
    public void paneUsuario() {
        removeAll();
        System.out.println("pane de USUARIOS ABERTO.");

        lblTitulo = new JLabel("Cadastro de Usuário", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(30,30,120));

        lblNome = new JLabel("Nome:"); edtNome = new JTextField(20);
        lblLogin = new JLabel("Login (e-mail):"); edtLogin = new JTextField(20);
        lblSenha = new JLabel("Senha:"); edtSenha = new JPasswordField(20);

               
        add(lblTitulo);
        add(lblNome); add(edtNome);
        add(lblLogin); add(edtLogin);
        add(lblSenha); add(edtSenha);
        add(btnCadastrar);
        add(btnCancelar);
                                        
        classMain.defineFrame(1);

    }
    
    
    public void paneVendas() {
        removeAll();                     
        System.out.println("pane de VENDAS ABERTO.");
        JOptionPane.showMessageDialog(null, "OLA ABRIU TELA DE VENDAS");

        lblTitulo = new JLabel("Movimentos de Venda");
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

    
    public void paneCompras() {
        removeAll();
        JOptionPane.showMessageDialog(null, "OLA ABRIU TELA DE COMPRAS");
        System.out.println("pane de COMPRAS ABERTO.");

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


}