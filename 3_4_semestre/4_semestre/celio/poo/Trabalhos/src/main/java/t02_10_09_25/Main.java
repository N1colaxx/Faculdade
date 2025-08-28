package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    
    private PaneLogin paneLogin;
    private PaneMenu paneMenu;
    // Telas de cadastro
    private PaneCliente paneCliente;
    private PaneUsuarios paneUsuario;
    private PaneFornecedor paneFornecedor;
    private PaneFormaPagamento paneFormaPagamento;
    // Telas de Movimentos
    private PaneVendas paneVendas;
    private PaneCompras paneCompras;
    // CardLayout
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    
    
    public Main() {
        cfgFrame();

        instanciar();
        criarCardLayout();

        add(contentPanel);

        // Quando clicar em Entrar → mostra menu
        paneLogin.getBtnEntrar().addActionListener(e -> mostrarMenu());

        // Quando clicar nos menus
        paneMenu.getJmiCliente().addActionListener(e -> mostrarTela("CLIENTE"));
        paneMenu.getJmiUsuario().addActionListener(e -> mostrarTela("USUARIO"));
        paneMenu.getJmiFornecedor().addActionListener(e -> mostrarTela("FORNECEDOR"));
        paneMenu.getJmiFormaPagamento().addActionListener(e -> mostrarTela("FORMA DE PAGAMENTO"));
        paneMenu.getJmiVenda().addActionListener(e -> mostrarTela("VENDAS"));
        paneMenu.getJmiCompra().addActionListener(e -> mostrarTela("COMPRAS"));
    }

    private void cfgFrame() {
        setTitle("Empresa de Genciamento de Compra - Venda");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void instanciar(){

        // Cria telas
        try {            
        paneLogin = new PaneLogin();
        paneMenu = new PaneMenu();
        paneCliente = new PaneCliente();
        paneUsuario = new PaneUsuarios();
        paneFornecedor = new PaneFornecedor();
        paneFormaPagamento = new PaneFormaPagamento();
        paneCompras = new PaneCompras();
        paneVendas = new PaneVendas();

        } catch (Exception e) {
            System.out.println("\nERRO ao Criar as telas(Pane) \n" + e);
        }
    }
    
    private void criarCardLayout(){
        try {
            // CardLayout gerencia telas
            cardLayout = new CardLayout();
            contentPanel = new JPanel(cardLayout);

            // adiciona as telas com "nomes"
            // Cadastro
            contentPanel.add(paneLogin, "LOGIN");
            contentPanel.add(paneCliente, "CLIENTE");
            contentPanel.add(paneUsuario, "USUARIO");
            contentPanel.add(paneFornecedor, "FORNECEDOR");
            contentPanel.add(paneFormaPagamento, "FORMA DE PAGAMENTO");
            // Movimentados
            contentPanel.add(paneVendas, "VENDAS");
            contentPanel.add(paneCompras, "COMPRAS");
            
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Criar o CardLayout \n" + e);
        }
    }
    
    private void mostrarMenu() {
        setJMenuBar(paneMenu.getMenuBar());
        mostrarTela("CLIENTE"); // abre Cliente como padrão
    }

    private void mostrarTela(String nomeTela) {
        cardLayout.show(contentPanel, nomeTela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
