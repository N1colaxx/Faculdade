package t02_10_09_25;

import java.awt.*; 
import javax.swing.*;

public class Main extends JFrame {
    private TelaLogin telaLogin;
    private AbasCadastro abasCadastro;
    private ViewMenu viewMenu;
    private EventosMenu eventosMenu;
    
    public Main() {
        setTitle("Empresa de Gerenciamento de Compra - Venda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        inicializar();
        mostraLogin();
    }
    
    private void inicializar() {
        try {
            System.out.println("\n Inicializando obj class Main... \n");
            
            telaLogin = new TelaLogin();
            System.out.println("Tela Login ... ");
            
            viewMenu = new ViewMenu(this);
            System.out.println("View Menu ... ");
            
            abasCadastro = new AbasCadastro(this);    
            System.out.println("Abas Cadastro ... ");
            
            eventosMenu = new EventosMenu(viewMenu, abasCadastro);
            System.out.println("Eventos do Menu ...");
            
            System.out.println("\n Inicialização comcluida com SUCESSO!");
            
        } catch (Exception e) {
            System.out.println("\n ERRO! falha na inicialização dos OBJETOS. \n " + e);
        }
    };
    
    private void mostraLogin() {
        try {
            System.out.println("\n Inicializando -> Mostrando Tela Login (...)");
            
            getContentPane().removeAll(); //remove o panel atual
            telaLogin.setPreferredSize(new Dimension(600, 300));
            add(telaLogin);
            pack();
            setLocationRelativeTo(null);
            revalidate();
            pack();
            
            try {
                System.out.println("Inicializado -> Evento para mostrar Menu (...) ");
                telaLogin.getBtnEntrar().addActionListener(e -> mostrarMenu());
            } catch (Exception e) {
                System.out.println("\n ERRO! Inicializado -> Evento para mostrar Menu (FALHOU) " + e);
            }

            System.out.println("Inicializando -> Mostrando Tela Login (SUCESSO!)");
        } catch (Exception e) {
            System.out.println("\n ERRO! Falha em: Inicializando -> Mostrando Tela Login (...) " + e);
        }}
 
    
    private void mostrarMenu() {
        try {
            System.out.println("\n Inicializando -> Mostrando Menu");
            
            getContentPane().removeAll(); // remove o pane atual 
            setJMenuBar(viewMenu);  // add o menu
            
            add(abasCadastro);
            abasCadastro.paneCliente();
            
            revalidate();
            repaint();
            pack();
            
        } catch (Exception e) {
            System.out.println("\n ERRO! em: Inicializando -> Mostrando Menu \n" + e);
        }
    }
    
    
    //Define o tamanho do frame dependendo do Panel
    public void defineFrame(int n) {
        try {
            System.out.println("Inicializando -> Void Mostrando Abas");
            setSize(900, 700);
            setLocationRelativeTo(null); // centraliza
            revalidate();
            repaint();
                
        } catch (Exception e) {
            System.out.println("\n ERRO! ao tentar abri Aba: " + n);
        }

    }
        
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
