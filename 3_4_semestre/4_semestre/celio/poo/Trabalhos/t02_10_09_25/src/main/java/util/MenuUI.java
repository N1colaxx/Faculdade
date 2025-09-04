package util;

import view.AppView;
import javax.swing.JOptionPane;

public class MenuUI {
    private AppView app;
    
    public MenuUI(AppView app) {
        this.app = app;
        itensMenu();
    }

    private void itensMenu() {
        System.out.println(" MenuUI inicializado ...");
        
        try {
            app.getMenuView().getJmiCliente().addActionListener(e -> {
                app.mostrarTela("Cliente");
                System.out.println(" Abrindo tela de CLIENTE");
            });
            
            app.getMenuView().getJmiFornecedor().addActionListener(e -> {
                app.mostrarTela("Fornecedor");
                System.out.println(" Abrindo tela de FORNECEDOR");
            });            
            
            app.getMenuView().getJmiFormaPagamento().addActionListener(e -> {
                app.mostrarTela("FormaPaga");
                System.out.println(" Abrindo tela de FORMA DE PAGAMENTO");
            });
            
            app.getMenuView().getJmiUsuario().addActionListener(e -> {
                app.mostrarTela("Usuario");
                System.out.println(" Abrindo tela de USUARIO");
            });
            
            // Movimentos
            app.getMenuView().getJmiVenda().addActionListener(e -> {
                app.mostrarTela("Venda");                
                System.out.println(" Abrindo tela de VENDAS");
            });

            app.getMenuView().getJmiCompra().addActionListener(e -> {
                app.mostrarTela("Compra");
                System.out.println(" Abrindo tela de COMPRAS");
            });

            // Sair
            app.getMenuView().getJmiSair().addActionListener(e -> {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION){
                    System.out.println("\n Fechando Aplicacao...\n");
                    System.exit(0);
                }
            });

        } catch (Exception ex){
            System.out.println("\n ERRO! ao tentar abrir um JMenuItem \n" + ex);
        }
    }
}
