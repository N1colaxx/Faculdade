package view;


import java.awt.*;
import javax.swing.*;

public class AppView extends JFrame{
    // Views
    private LoginView loginView;
    private MenuView menuView;
    private ClienteView clienteView;
    private CompraView compraView;
    private FormaPagaView formaPagaView;
    private PessoaView pessoaView;
    private UsuarioView usuario1View;
    private VendaView vendaView;
    
    // Variveis
    private JPanel contendPanel;
    private CardLayout cardLayout;


    public AppView() {
        instanciarUtil();
        instanciarView();
        criarCardLayout();
        
        add(contendPanel);
        
        mostrandoLogin();
                

        cfgFrame();
    }
    
    private void instanciarUtil() {
        try {
            System.out.println("\n Instanciando Util (...)");
            
            menuView = new MenuView();
            System.out.println(" Eventos do Menu");
            System.out.println("\n Instanciando Util (SUCESSO)");

        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando Util (FALHA) \n" + e);            
        }
    }

    private void instanciarView(){
        try {
            System.out.println("\n Instanciando View (...)");
            
            loginView = new LoginView();
            System.out.println(" Login");            

            clienteView = new ClienteView();
            System.out.println(" Cliente");
            
            compraView = new CompraView();
            System.out.println(" Compra");
            
            formaPagaView = new FormaPagaView();
            System.out.println(" FormaPaga");
            
            pessoaView = new PessoaView();
            System.out.println(" Pessoa");
            
            usuario1View = new UsuarioView();
            System.out.println(" Usuario");
            
            vendaView = new VendaView();
            System.out.println(" Venda");
            
            menuView = new MenuView();
            System.out.println(" Menu");

            System.out.println("Instanciando View (SUCESSO)");
            
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando View (FALHA) \n" + e);
        }
    }
    
    private void criarCardLayout() {
        try {
            System.out.println("\n Criando CardLayout (...)");
            
            cardLayout = new CardLayout();
            contendPanel = new JPanel(cardLayout);
            
            contendPanel.add(loginView, "Login");
            System.out.println(" Login");

            // Cadastro
            contendPanel.add(clienteView, "Cliente");
            System.out.println(" Cliente");
            
            contendPanel.add(compraView, "Compra");
            System.out.println(" Compra");
            
            contendPanel.add(formaPagaView, "FormaPaga");
            System.out.println(" FormaPaga");
            
            contendPanel.add(pessoaView, "Pessoa");
            System.out.println(" Pessoa");
            
            contendPanel.add(usuario1View, "Usuario");
            System.out.println(" Usuario");
            
            // Movimentos
            contendPanel.add(vendaView, "Venda");
            contendPanel.add(compraView, "Compra");
            
            System.out.println(" Criando CardLayout (SUCESSO)");
            
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Criar CardLayout (FALHA) \n" + e);
        }
    }
    
    private void cfgFrame(){
        setTitle("Empresa de Genciamento de Compra - Venda");
        setPreferredSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    private void mostrandoLogin(){
        mostrarTela("Login");
        loginView.getBtnEntrar().addActionListener(e -> mostrarMenu());
    }
    
    private void mostrarMenu() {
        mostrarTela("Cliente");
    }
    public void mostrarTela(String nomePane){
        cardLayout.show(contendPanel, nomePane);
    }
    public MenuView getMenu(){
        return menuView;
    }
}
