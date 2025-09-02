package util;

import view.AppView;

import javax.swing.JOptionPane;

public class EventosMenu {

    private AppView app;
    
    public EventosMenu() {
    }
    
    public EventosMenu(AppView app) {
        this.app = app;
        itensMenu();
    }

    private void itensMenu() {
        try {
            // Cadastro
            app.getMenu().getJmiCliente().addActionListener(e -> {
                app.mostrarTela("CLIENTE");
                System.out.println("Abrindo tela de CLIENTE");
                
            });

            app.getMenu().getJmiUsuario().addActionListener(e -> {
                System.out.println("Abrindo tela de USUARIO");
                app.mostrarTela("USUARIO");
            });

            app.getMenu().getJmiFornecedor().addActionListener(e -> {
                System.out.println("Abrindo tela de FORNECEDOR");
                app.mostrarTela("FORNECEDOR");
            });

            app.getMenu().getJmiFormaPagamento().addActionListener(e -> {
                System.out.println("Abrindo tela de FORMA DE PAGAMENTO");
                app.mostrarTela("FORMA DE PAGAMENTO");
            });

            // Movimentos
            app.getMenu().getJmiVenda().addActionListener(e -> {
                System.out.println("Abrindo tela de VENDAS");
                app.mostrarTela("VENDAS");
            });

            app.getMenu().getJmiCompra().addActionListener(e -> {
                System.out.println("Abrindo tela de COMPRAS");
                app.mostrarTela("COMPRAS");
            });

            // Sair
            app.getMenu().getJmiSair().addActionListener(e -> {
                System.out.println("\n Fechando Aplicacao...\n");
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if(opcao == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            });

        } catch (Exception ex){
            System.out.println("\n ERRO! ao tentar abrir um JMenuItem \n" + ex);
        }
    }
}
