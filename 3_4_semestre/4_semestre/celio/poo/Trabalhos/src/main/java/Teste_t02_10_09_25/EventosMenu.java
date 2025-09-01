package Teste_t02_10_09_25;

import javax.swing.JOptionPane;

public class EventosMenu {

    private Main main;
    
    public EventosMenu() {
    }
    
    public EventosMenu(Main main) {
        this.main = main;
        itensMenu();
    }

    private void itensMenu() {
        try {
            // Cadastro
            main.getPaneMenu().getJmiCliente().addActionListener(e -> {
                System.out.println("Abrindo tela de CLIENTE");
                main.mostrarTela("CLIENTE");
            });

            main.getPaneMenu().getJmiUsuario().addActionListener(e -> {
                System.out.println("Abrindo tela de USUARIO");
                main.mostrarTela("USUARIO");
            });

            main.getPaneMenu().getJmiFornecedor().addActionListener(e -> {
                System.out.println("Abrindo tela de FORNECEDOR");
                main.mostrarTela("FORNECEDOR");
            });

            main.getPaneMenu().getJmiFormaPagamento().addActionListener(e -> {
                System.out.println("Abrindo tela de FORMA DE PAGAMENTO");
                main.mostrarTela("FORMA DE PAGAMENTO");
            });

            // Movimentos
            main.getPaneMenu().getJmiVenda().addActionListener(e -> {
                System.out.println("Abrindo tela de VENDAS");
                main.mostrarTela("VENDAS");
            });

            main.getPaneMenu().getJmiCompra().addActionListener(e -> {
                System.out.println("Abrindo tela de COMPRAS");
                main.mostrarTela("COMPRAS");
            });

            // Sair
            main.getPaneMenu().getJmiSair().addActionListener(e -> {
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
