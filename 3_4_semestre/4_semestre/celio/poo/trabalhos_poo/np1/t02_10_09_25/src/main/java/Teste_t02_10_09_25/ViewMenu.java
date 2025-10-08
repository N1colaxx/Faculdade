package Teste_t02_10_09_25;

import javax.swing.*;

public class ViewMenu extends JMenuBar {
    private JMenu menuCadastros, menuMovimentos, menuSair;
    private JMenuItem
            jmiCliente, jmiFornecedor, jmiUsuario, jmiFormaPagamento, // Itens do menuCadastros
            jmiVenda, jmiCompra, // itens do menuMovimentos
            jmiSair; // itens de menuSair:    private JMenu menuCadastros, menuMovimentos, menuSair;

    public ViewMenu(){
        
    }
    
    public ViewMenu(JFrame parent){
        criandoMenu();
    }
    
    private void criandoMenu() {
        menuCadastros = new JMenu("Cadastros");
            jmiCliente = new JMenuItem("Cliente");
            jmiFornecedor = new JMenuItem("Fornecedor");
            jmiFormaPagamento = new JMenuItem("Forma de Pagamento");
            jmiUsuario = new JMenuItem("Usuario");
            // add itens de Cadastros
            menuCadastros.add(jmiCliente);
            menuCadastros.add(jmiFornecedor);
            menuCadastros.add(jmiFormaPagamento);
            menuCadastros.add(jmiUsuario);
            
        menuMovimentos = new JMenu("Movimentos");
            jmiVenda = new JMenuItem("Vendas");
            jmiCompra = new JMenuItem("Compras");
            // add itens de Movimentos
            menuMovimentos.add(jmiVenda);
            menuMovimentos.add(jmiCompra);
            
        menuSair = new JMenu("Sair");
            jmiSair = new JMenuItem("Fechar");
            // add itens de Sair
            menuSair.add(jmiSair);
            
            
        // add os Menu a minha MenuBar
        add(menuCadastros);
        add(menuMovimentos);
        add(menuSair);
    };   
    
    // Getters
    
    public JMenuItem getJmiCliente() { return jmiCliente; }
    public JMenuItem getJmiUsuario() { return jmiUsuario; }
    public JMenuItem getJmiFornecedor() { return jmiFornecedor; }
    public JMenuItem getJmiFormaPagamento() { return jmiFormaPagamento; }
    public JMenuItem getJmiVenda() { return jmiVenda; }
    public JMenuItem getJmiCompra() { return jmiCompra; }
    public JMenuItem getJmiSair() { return jmiSair; }
}
