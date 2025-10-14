package view;

import util.AppUI;
import util.MenuUI;

import java.awt.*;
import javax.swing.*;

public class AppView extends JFrame{
//     Util
    private MenuUI eventosMenuItem;
    
//     Views
    private LoginView loginView;
    private MenuView menuView;
    // ItensCadastro
    private ClienteView clienteView;
    private FornecedorView fornecedorView;
    private ProdutoView produtoView;
    private FormapagtoView formapagtoView;
    private UsuarioView usuario1View;
    // ItensMovimentos
    private VendaView vendaView;
//    private CompraView compraView;
    // Variaveis
    private JPanel contendPanel;
    private CardLayout cardLayout;
    
    public AppView() {
        AppUI.setNimbus(); // opcional
        instanciarView();
        tamanhoView();
        criarCardLayout();
      
        add(contendPanel);
        
        instanciarUtil();
        mostrandoLogin();
        cfgFrame();
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    private void instanciarUtil() {
        try {
            System.out.println("\n Instanciando Util (...)");
            
            
            if (eventosMenuItem == null) {
                eventosMenuItem = new MenuUI(this);
            } else {
                System.out.println(" -- MenuUI ja foi inicializado ANTERIORMENTE!");
            }
            
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando Util (FALHA) \n" + e);            
        }
        
        System.out.println(" Instanciando Util (SUCESSO)");
    }
    

    private void instanciarView(){
        try {
            System.out.println("\n Instanciando View (...)");
            
            loginView = new LoginView();
                System.out.println(" Login");
            menuView = new MenuView();
                System.out.println(" Menu");
                
            // Cadastro
            clienteView = new ClienteView();
                System.out.println(" Cliente");
            fornecedorView = new FornecedorView();
                System.out.println(" Fronecedor");
            produtoView = new ProdutoView();
                System.out.println(" Produto");
            formapagtoView = new FormapagtoView();
                System.out.println(" Formapagto");
            usuario1View = new UsuarioView();
                System.out.println(" Usuario");
                
            // Movimentos
//            compraView = new CompraView();
//                System.out.println(" Compra");
            vendaView = new VendaView();
                System.out.println(" Venda");
                               
            System.out.println(" Instanciando View (SUCESSO)");
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando View (FALHA) \n" + e);
        }
    }
    
    private void tamanhoView() {
        try {
            System.out.println("\n Aplicando Tamanho Padrao nas VIEW ...");
            Dimension log, usu, forpag, vda, cop;
            
            log = new Dimension(600, 400);
            loginView.setPreferredSize(log);
            System.out.println(" Login");
                
            AppUI.applyDefaultSize(clienteView);
            System.out.println(" Cliente");
                
            AppUI.applyDefaultSize(fornecedorView);
            System.out.println(" Fornecedor");
                
            AppUI.applyDefaultSize(produtoView);
            System.out.println(" Produto");

            forpag = new Dimension(1000, 850);
            AppUI.applySize(formapagtoView, forpag);
            System.out.println(" Formapagto");
                
            usu = new Dimension(950, 650);
            AppUI.applySize(usuario1View, usu);
            System.out.println(" Usuario");
            
//            AppUI.applyDefaultSize(compraView);
//            System.out.println(" Compra");
//            
            AppUI.applyDefaultSize(vendaView);
            System.out.println(" venda");
                
            System.out.println(" Aplicando Tamanho Padrao nas VIEW (SUCESSO)");
        } catch (Exception e) {
            System.out.println("\n Aplicando Tamanho Padrao nas VIEW (FALHA) \n" + e);
        }
    }
    
    private void criarCardLayout () {
        try {
            System.out.println("\n Criando CardLayout (...)");
            
            cardLayout = new CardLayout();
            // content recebe o CardLayout MAS com as regras de CardPanelView (usa preferred do card visível)
            contendPanel = new CardPanelView(cardLayout);
            
            contendPanel.add(loginView, "Login");
            System.out.println(" Login");

            // Cadastro
            contendPanel.add(clienteView, "Cliente");
            System.out.println(" Cliente");
          
            contendPanel.add(fornecedorView, "Fornecedor");
            System.out.println(" Fornecedor");
          
            contendPanel.add(produtoView, "Produto");
            System.out.println(" Produto");
          
            contendPanel.add(formapagtoView, "Formapagto");
            System.out.println(" Formapagto");
            
            contendPanel.add(usuario1View, "Usuario");
            System.out.println(" Usuario");
            
            // Movimentos
            contendPanel.add(vendaView, "Venda");
            System.out.println(" Venda");
//            
//            contendPanel.add(compraView, "Compra");
//            System.out.println(" Compra");
            
            System.out.println(" Criando CardLayout (SUCESSO)");
            
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Criar CardLayout (FALHA) \n" + e);
        }
    }
    
    private void cfgFrame(){
        setTitle("Empresa de Genciamento de Compra - Venda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
//    private void mostrandoLogin(){
//        mostrarTela("Login");
//        loginView.getBtnEntrar().addActionListener(e -> entrarMenu());
//    }
    
    private void mostrandoLogin(){
        mostrarTela("Login");

        // Usa a validação da view e chama o controller
        loginView.setOnLogin((email, senha) -> {
            controller.UsuarioController ctrl = new controller.UsuarioController();
            boolean ok = ctrl.autenticar(email, senha);
            if (ok) {
                entrarMenu();
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    this, "Usuário ou senha inválidos, ou usuário inativo."
                );
            }
        });
    }

    
    private void entrarMenu() {
        System.out.println("\n Entrando em Menu...");
        
        setJMenuBar(menuView);
        mostrarTela("Usuario");
        System.out.println(" Entrando em Menu(SUCESSO)");
    }
    
    public void mostrarTela(String nome){
        cardLayout.show(contendPanel, nome);
        // empacota respeitando o card visível (CardPanelView)
        AppUI.packToVisibleCard(this, contendPanel);
    }

    // Getters 
    public MenuView getMenuView(){
        return menuView;
    }
}
