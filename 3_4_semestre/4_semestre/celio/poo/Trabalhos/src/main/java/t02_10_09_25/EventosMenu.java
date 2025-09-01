package t02_10_09_25;

import javax.swing.JOptionPane;

public final class EventosMenu {

   private ViewMenu menu;
   private ItensMenu abasCadastro;
    

    
   public EventosMenu(ViewMenu menu, ItensMenu abasCadastro) {
        this.menu = menu;
        this.abasCadastro = abasCadastro;
        
        itensMenu();
    }

    private void itensMenu() {        
        System.out.println("\n Inicializando Eventos Menu (SUCESSO!)");
        
        try {
            // Cadastro
            menu.getJmiCliente().addActionListener(e -> {
                abasCadastro.paneCliente();
                System.out.println("\n Abrindo Pane CLIENTE");
            });

            menu.getJmiFornecedor().addActionListener(e -> {
                abasCadastro.paneFornecedor();                
                System.out.println("\n Abrindo pane de FORNECEDOR");
            });

            menu.getJmiFormaPagamento().addActionListener(e -> {
               abasCadastro.paneFormaPagamento();                
                System.out.println("\n Abrindo pane de FORMA DE PAGAMENTO");
            });
            
            menu.getJmiUsuario().addActionListener(e -> {
                abasCadastro.paneUsuario();                
                System.out.println("\n Abrindo pane de USUARIO");
            });
            
            
            // Movimentos
            menu.getJmiCompra().addActionListener(e -> {
               abasCadastro.paneCompras();
               System.out.println("\n Abrindo pane de COMPRAS");

            });
            
            menu.getJmiVenda().addActionListener(e -> {
                abasCadastro.paneVendas();
                System.out.println("\n Abrindo pane de VENDAS");
            });

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
