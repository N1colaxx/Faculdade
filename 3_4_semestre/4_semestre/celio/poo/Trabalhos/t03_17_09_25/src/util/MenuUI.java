package util;

import view.AppView;
import javax.swing.*;

public class MenuUI {

    private AppView app;

    public MenuUI(AppView app) {
        this.app = app;
        itensMenu();
    }

    private void itensMenu() {
        System.out.println(" MenuUI inicializado ...");

        try {
            // Cadastro
            addMenuAction(app.getMenuView().getJmiCliente(), "Cliente", "CLIENTE");
            addMenuAction(app.getMenuView().getJmiFornecedor(), "Fornecedor", "FORNECEDOR");
            addMenuAction(app.getMenuView().getJmiFormaPagamento(), "FormaPaga", "FORMA DE PAGAMENTO");
            addMenuAction(app.getMenuView().getJmiUsuario(), "Usuario", "USUARIO");

            // Movimentos
            addMenuAction(app.getMenuView().getJmiVenda(), "Venda", "VENDA");
            addMenuAction(app.getMenuView().getJmiCompra(), "Compra", "COMPRA");

            // Sair (tratamento separado)
            app.getMenuView().getJmiSair().addActionListener(e -> {
                try {
                    int opcao = JOptionPane.showConfirmDialog(null,
                            "Deseja realmente sair?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (opcao == JOptionPane.YES_OPTION) {
                        System.out.println("\n Fechando Aplicacao...\n");
                        System.exit(0);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao tentar sair:\n" + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });

        } catch (Exception ex) {
            System.out.println("\n ERRO! ao configurar os JMenuItem \n" + ex);
        }
    }

    /**
     * Método auxiliar para registrar um JMenuItem com tratamento de exceção.
     */
    private void addMenuAction(JMenuItem item, String tela, String log) {
        item.addActionListener(e -> {
            try {
                app.mostrarTela(tela);
                System.out.println(" Abrindo tela de " + log);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao abrir tela de " + log + ":\n" + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
