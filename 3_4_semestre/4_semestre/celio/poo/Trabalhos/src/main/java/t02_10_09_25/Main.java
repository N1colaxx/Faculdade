package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    // Telas
    private Login paneLogin;
    private Menu paneMenu;
    // Paineis de cadastro
    private TelaCliente paneCliente;
    private TelaUsuarios paneUsuario;
    private TelaFornecedor paneFornecedor;
    private TelaFormaPaga paneFormaPagamento;
    // Paineis de Movimentos
    private TelaVendas paneVendas;
    private TelaCompras paneCompras;
    // Painel de Sair
    // variaveis
    private JPanel contentPanel; //recebe tudo 
    private CardLayout cardLayout; // orquestra todos os paineis
    
    
    
    public Main() {
        cfgFrame();
        instanciar();
        criarCardLayout();

        add(contentPanel);
        
        paneLogin.getBtnEntrar().addActionListener(e -> mostrarMenu());

        // Configura os eventos do menu em uma classe externa
        try {
            new EventosMenu(this);
        } catch (Exception e) {
            System.out.println("\n ERRO! Executar eventos do MENU \n" + e);
        }
    }

    private void cfgFrame() {
        setTitle("Empresa de Genciamento de Compra - Venda");
        setSize(1000, 900); // aumentei para caber tudo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza o frame no meio da tela
    }


    private void instanciar(){

        // Cria telas
        try {            
        paneLogin = new Login();
        paneMenu = new Menu();
        paneCliente = new TelaCliente();
        paneUsuario = new TelaUsuarios();
        paneFornecedor = new TelaFornecedor();
        paneFormaPagamento = new TelaFormaPaga();
        paneCompras = new TelaCompras();
        paneVendas = new TelaVendas();
                
        } catch (Exception e) {
            System.out.println("\nERRO ao Criar as telas(Pane) \n" + e);
        }
    }
    
    private void criarCardLayout(){
        try {
            // CardLayout gerencia telas
            cardLayout = new CardLayout();
            contentPanel = new JPanel(cardLayout);

            // adiciona as telas
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
    
    public void mostrarMenu() {
        setJMenuBar(paneMenu.getMenuBar());
        mostrarTela("CLIENTE"); // abre Cliente como padrão
    }

    public void mostrarTela(String nomeTela) {
        cardLayout.show(contentPanel, nomeTela);
    }
    
    
    //Getterts
    
    public Menu getPaneMenu() {
        return paneMenu;
    }
    




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
