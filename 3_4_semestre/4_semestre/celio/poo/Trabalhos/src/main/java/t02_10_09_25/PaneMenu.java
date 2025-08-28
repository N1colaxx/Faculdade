package t02_10_09_25;

import java.awt.*;
import javax.swing.*;

public class PaneMenu extends JPanel {
    
    private JMenu menuCadastro, menuMovimentos, menuSair;
    private JMenuBar menuBar;
    private JMenuItem 
            jmiCliente, jmiFornecedor, jmiUsuario, jmiFormaPagamento, // Itens do menuCadastro
            jmiVenda, jmiCompra; // itens do menuMovimentos
    
    public PaneMenu(){
        instanciar();
        adicionar();
    }
    
    private void instanciar(){
        
        menuBar = new JMenuBar();
        menuCadastro = new JMenu("Cadastro");
        menuMovimentos = new JMenu("Movimentos");
        menuSair = new JMenu("Sair");
        
        // Itens de Cadastro
        jmiCliente = new JMenuItem("Cliente");
        jmiFornecedor = new JMenuItem("Fornecedor");
        jmiFormaPagamento = new JMenuItem("Forma de Pagamento");
        jmiUsuario = new JMenuItem("Usuario");
        
        // Itens de Movimentos
        jmiVenda = new JMenuItem("Vendas");
        jmiCompra = new JMenuItem("Compras");
        
    }
    
    
    private void adicionar(){
        // Cadastro
        menuCadastro.add(jmiCliente);
        menuCadastro.add(jmiFornecedor);
        menuCadastro.add(jmiFormaPagamento);
        menuCadastro.add(jmiUsuario);
        // Movimentados
        menuCadastro.add(jmiCompra);
        menuCadastro.add(jmiVenda);


        menuMovimentos.add(jmiVenda);
        menuMovimentos.add(jmiCompra);

        menuBar.add(menuCadastro);
        menuBar.add(menuMovimentos);
        menuBar.add(menuSair);
    }
    
    // Getters
    public JMenuBar getMenuBar() { return menuBar; }
    public JMenuItem getJmiCliente() { return jmiCliente; }
    public JMenuItem getJmiUsuario() { return jmiUsuario; }
    public JMenuItem getJmiFornecedor() { return jmiFornecedor; }
    public JMenuItem getJmiFormaPagamento() { return jmiFormaPagamento; }
    public JMenuItem getJmiVenda() { return jmiVenda; }
    public JMenuItem getJmiCompra() { return jmiCompra; }
    
}
