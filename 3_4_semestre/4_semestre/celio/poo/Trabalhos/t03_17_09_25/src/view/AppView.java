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
    private PessoaView pessoaView;
    // ItensCadastro
    private ClienteView clienteView;
    private FornecedorView fornecedorView;
    private FormaPagaView formaPagaView;
    private UsuarioView usuario1View;
    // ItensMovimentos
    private VendaView vendaView;
    private CompraView compraView;
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
                               
            System.out.println(" Instanciando View (SUCESSO)");
        } catch (Exception e) {
            System.out.println("\n ERRO! ao Instanciando View (FALHA) \n" + e);
        }
    }
    
    private void tamanhoView() {
        try {
            System.out.println("\n Aplicando Tamanho Padrao nas VIEW ...");
            Dimension log, usu, vda, cop;
            
            // Login com tamanho diferente
            log = new Dimension(600, 400);
            loginView.setPreferredSize(log);
                System.out.println(" Login");

            // Demais com tamanho padrão (via AppUI)
            
            AppUI.applyDefaultSize(clienteView);
                System.out.println(" Cliente");
            AppUI.applyDefaultSize(fornecedorView);
                System.out.println(" Fornecedor");
            AppUI.applyDefaultSize(formaPagaView);
                System.out.println(" FormaPaga");
                
            usu = new Dimension(950, 650);
            AppUI.applySize(usuario1View, usu);
                System.out.println(" Usuario");
                
            
            cop = new Dimension(1000, 850);
            AppUI.applySize(compraView, cop);
                System.out.println(" Compra");
                
            vda = new Dimension(1000, 800);
            AppUI.applySize(vendaView, vda);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        // empacota respeitando o card visível (CardPanelView)
        AppUI.packToVisibleCard(this, contendPanel);
    }

    // Getters 
    public MenuView getMenuView(){
        return menuView;
    }
}
