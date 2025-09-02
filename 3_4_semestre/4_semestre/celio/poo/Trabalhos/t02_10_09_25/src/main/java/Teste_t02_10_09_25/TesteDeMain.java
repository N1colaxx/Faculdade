///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Teste_t02_10_09_25;
//
///**
// *
// * @author nicol
// */
//public class TesteDeMain {
//    
//package main;
//
//
//import util.EventosMenu;
//import view.LoginView;
//import view.MenuView;
//import view.FornecedorView;
//import view.UsuariosView;
//import view.VendasView;
//import model.FormaPaga;
//import view.ComprasView;
//import model.Cliente;
//import javax.swing.*;
//import java.awt.*;
//
//public class Main extends JFrame {
//    // Telas
//    private LoginView paneLogin;
//    private MenuView paneMenu;
//    // Paineis de cadastro
//    private Cliente paneCliente;
//    private UsuariosView paneUsuario;
//    private FornecedorView paneFornecedor;
//    private FormaPaga paneFormaPagamento;
//    // Paineis de Movimentos
//    private VendasView paneVendas;
//    private ComprasView paneCompras;
//    // Painel de Sair
//    // variaveis
//    private JPanel contentPanel; //recebe tudo 
//    private CardLayout cardLayout; // orquestra todos os paineis
//    
//    
//    
//    public Main() {
//        cfgFrame();
//        instanciar();
//        criarCardLayout();
//
//        add(contentPanel);
//        
//        paneLogin.getBtnEntrar().addActionListener(e -> mostrarMenu());
//
//        // Configura os eventos do menu em uma classe externa
//        try {
//            new EventosMenu(this);
//        } catch (Exception e) {
//            System.out.println("\n ERRO! Executar eventos do MENU \n" + e);
//        }
//    }
//
//    private void cfgFrame() {
//        setTitle("Empresa de Genciamento de Compra - Venda");
//        setSize(1000, 900); // aumentei para caber tudo
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null); // centraliza o frame no meio da tela
//    }
//
//
//    private void instanciar(){
//
//        // Cria telas
//        try {            
//        paneLogin = new LoginView();
//        paneMenu = new MenuView();
//        paneCliente = new Cliente();
//        paneUsuario = new UsuariosView();
//        paneFornecedor = new FornecedorView();
//        paneFormaPagamento = new FormaPaga();
//        paneCompras = new ComprasView();
//        paneVendas = new VendasView();
//                
//        } catch (Exception e) {
//            System.out.println("\nERRO ao Criar as telas(Pane) \n" + e);
//        }
//    }
//    
//    private void criarCardLayout(){
//        try {
//            // CardLayout gerencia telas
//            cardLayout = new CardLayout();
//            contentPanel = new JPanel(cardLayout);
//
//            // adiciona as telas
//            // Cadastro
//            contentPanel.add(paneLogin, "LOGIN");
//            contentPanel.add(paneCliente, "CLIENTE");
//            contentPanel.add(paneUsuario, "USUARIO");
//            contentPanel.add(paneFornecedor, "FORNECEDOR");
//            contentPanel.add(paneFormaPagamento, "FORMA DE PAGAMENTO");
//            // Movimentados
//            contentPanel.add(paneVendas, "VENDAS");
//            contentPanel.add(paneCompras, "COMPRAS");
//
//            
//        } catch (Exception e) {
//            System.out.println("\n ERRO! ao Criar o CardLayout \n" + e);
//        }
//    }
//    
//    public void mostrarMenu() {
//        setJMenuBar(paneMenu.getMenuBar());
//        mostrarTela("CLIENTE"); // abre Cliente como padrão
//    }
//
//    public void mostrarTela(String nomeTela) {
//        cardLayout.show(contentPanel, nomeTela);
//    }
//    
//    
//    //Getterts
//    
//    public MenuView getPaneMenu() {
//        return paneMenu;
//    }
//    
//
//
//
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
//    }
//}
