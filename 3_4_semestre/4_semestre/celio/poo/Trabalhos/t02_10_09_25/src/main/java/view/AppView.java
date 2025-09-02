package view;

import util.MenuItem;

import java.awt.*;
import javax.swing.*;

public class AppView extends JFrame{
//     Util
    private MenuItem eventosMenuItem;
    
//     Views
    private LoginView loginView;
    private MenuView menuView;
    private PessoaView pessoaView;

    // cadastro
    private ClienteView clienteView;
    private FornecedorView fornecedorView;
    private FormaPagaView formaPagaView;
    private UsuarioView usuario1View;
    // movimentos
    private VendaView vendaView;
    private CompraView compraView;

    
    // Variveis
    private JPanel contendPanel;
    private CardLayout cardLayout;


    public AppView() {
        instanciarView();
        criarCardLayout();
        
        add(contendPanel);
        
        instanciarUtil();
        mostrandoLogin();
        cfgFrame();
    }
    
    private void instanciarUtil() {
        try {
            System.out.println("\n Instanciando Util (...)");
            
            eventosMenuItem = new MenuItem(this);
            System.out.println(" Eventos do Menu");
            System.out.println(" Instanciando Util (SUCESSO)");

        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando Util (FALHA) \n" + e);            
        }
    }

    private void instanciarView(){
        try {
            System.out.println("\n Instanciando View (...)");
            
            loginView = new LoginView();
            System.out.println(" Login");
            
            menuView = new MenuView();
            System.out.println(" Menu");
            
            pessoaView = new PessoaView();
            System.out.println(" Pessoa");

            // Cadastro
            clienteView = new ClienteView();
            System.out.println(" Cliente");
            
            fornecedorView = new FornecedorView();
            System.out.println(" Fronecedor");
            
            formaPagaView = new FormaPagaView();
            System.out.println(" FormaPaga");
            
            usuario1View = new UsuarioView();
            System.out.println(" Usuario");
            
            // Movimentos
            
            compraView = new CompraView();
            System.out.println(" Compra");
            
            vendaView = new VendaView();
            System.out.println(" Venda");

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
            
            contendPanel.add(pessoaView, "Pessoa");
            System.out.println(" Pessoa");

            // Cadastro
            contendPanel.add(clienteView, "Cliente");
            System.out.println(" Cliente");
            
            contendPanel.add(fornecedorView, "Fornecedor");
            System.out.println(" Fornecedor");
            
            contendPanel.add(formaPagaView, "FormaPaga");
            System.out.println(" FormaPaga");
            
            contendPanel.add(usuario1View, "Usuario");
            System.out.println(" Usuario");
            
            // Movimentos
            contendPanel.add(vendaView, "Venda");
            System.out.println(" Venda");
            
            contendPanel.add(compraView, "Compra");
            System.out.println(" Compra");
            
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
        loginView.getBtnEntrar().addActionListener(e -> entrarMenu());
    }
    
    private void entrarMenu() {
        System.out.println("\n Entrando em Menu...");
        setJMenuBar(menuView);
        mostrarTela("Cliente");
        System.out.println(" Entrando em Menu(SUCESSO)");

    }
    
    public void mostrarTela(String nome){
        cardLayout.show(contendPanel, nome);
    }
    

    // Getters 
    public MenuView getMenuView(){
        return menuView;
    }
}
