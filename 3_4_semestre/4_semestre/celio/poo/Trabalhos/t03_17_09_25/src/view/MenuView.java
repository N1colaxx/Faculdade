package view;

import javax.swing.*;

public class MenuView extends JMenuBar{
    
    private JMenu menuCadastro, menuMovimentos, menuSair;
    private JMenuItem 
            jmiCliente, jmiFornecedor, jmiUsuario, jmiFormaPagamento, // Itens do menuCadastro
            jmiVenda, jmiCompra, // itens do menuMovimentos
            jmiSair; // itens de menuSair
    
    public MenuView(){
        instanciar();
        adicionar();
    }
    
    private void instanciar(){
        
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
        
        // Itens de Sair
        jmiSair = new JMenuItem("Fechar");
    }
    
    
    private void adicionar(){
        // Cadastro
        menuCadastro.add(jmiCliente);
        menuCadastro.add(jmiFornecedor);
        menuCadastro.add(jmiFormaPagamento);
        menuCadastro.add(jmiUsuario);
        // Movimentados
        menuMovimentos.add(jmiCompra);
        menuMovimentos.add(jmiVenda);
        //Sair
        menuSair.add(jmiSair);
        
        add(menuCadastro);
        add(menuMovimentos);
        add(menuSair);
    }
    


    
    // Getters
    public JMenuItem getJmiCliente() { return jmiCliente; }
    public JMenuItem getJmiUsuario() { return jmiUsuario; }
    public JMenuItem getJmiFornecedor() { return jmiFornecedor; }
    public JMenuItem getJmiFormaPagamento() { return jmiFormaPagamento; }
    public JMenuItem getJmiVenda() { return jmiVenda; }
    public JMenuItem getJmiCompra() { return jmiCompra; }
    public JMenuItem getJmiSair() { return jmiSair; }
    
    
    
}
