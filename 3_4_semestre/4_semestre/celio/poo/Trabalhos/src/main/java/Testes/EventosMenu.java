package Testes;

import javax.swing.JOptionPane;

public final class EventosMenu {

   private ViewMenu menu;
   private AbasCadastro abasCadastro;
    

    
   public EventosMenu(ViewMenu menu, AbasCadastro abasCadastro) {
        this.menu = menu;
        this.abasCadastro = abasCadastro;
        
        itensMenu();
    }

    private void itensMenu() {        
        System.out.println("\n Inicializando Eventos Menu (SUCESSO!)");
        
        try {
            // Cadastro
            menu.getJmiCliente().addActionListener(e -> {
                System.out.println("\n Abrindo Pane CLIENTE");
                abasCadastro.paneCliente();
            });

            menu.getJmiFornecedor().addActionListener(e -> {
                System.out.println("\n Abrindo pane de FORNECEDOR");
                abasCadastro.paneFornecedor();
            });

            menu.getJmiFormaPagamento().addActionListener(e -> {
                System.out.println("\n Abrindo pane de FORMA DE PAGAMENTO");
               abasCadastro.paneFormaPagamento();
            });
            
            menu.getJmiUsuario().addActionListener(e -> {
                System.out.println("\n Abrindo pane de USUARIO");
                abasCadastro.paneUsuario();
            });
            
            
            // Movimentos
//            menu.getJmiVenda().addActionListener(e -> {;
//                System.out.println("Abrindo pane de VENDAS");
//                main.mostrarTela("VENDAS");
//            });

//            menu.getJmiCompra().addActionListener(e -> {
//                System.out.println("Abrindo pane de COMPRAS");
//                main.mostrarTela("COMPRAS");
//            });

            // Sair
            menu.getJmiSair().addActionListener(e -> {
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
