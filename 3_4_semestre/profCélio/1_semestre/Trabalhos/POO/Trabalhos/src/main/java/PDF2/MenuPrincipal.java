/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */

public class MenuPrincipal {
    private Cliente cliente;
    private Fornecedor fornecedor;
    private Funcionario funcionario;
    private Receber receber;
    private Pagar pagar;
    private Scanner scanner;
    
    int opc;
    
    //Construtor
    public MenuPrincipal(){
        cliente = new Cliente();
        fornecedor = new Fornecedor();
        funcionario = new Funcionario();
        receber = new Receber();
        pagar = new Pagar();
        scanner = new Scanner(System.in);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Receber getReceber() {
        return receber;
    }

    public Pagar getPagar() {
        return pagar;
    }
    
    //Setters 

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setReceber(Receber receber) {
        this.receber = receber;
    }

    public void setPagar(Pagar pagar) {
        this.pagar = pagar;
    }
    
    public void menu(){
        System.out.println("Escolha um opcao a baixo.");
        System.out.println("""
            1 - Cadastro Funcionario
            2 - Cadastro Cliente
            3 - Cadastro Fornecedores
            4 - Contas a Receber
            5 - Contas a Pagar
            6 - Fluxo de caixa
            7 - Sair
        """);
        
        do {            
            switch (opc) {
                case 1:
                    System.out.println("");
                case 2 - m hjldcyuiç.; sout  
   
                default:
                    throw new AssertionError();
            }
        } while (true);
    }
    
    public static void main(String[] args){
        MenuPrincipal obj = new MenuPrincipal();
        
        obj.menu();
        //obj.subMenu();
    }
}
