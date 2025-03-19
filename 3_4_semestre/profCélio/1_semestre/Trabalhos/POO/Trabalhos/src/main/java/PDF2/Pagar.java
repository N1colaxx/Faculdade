/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Pagar extends Financeiro {

    private Fornecedor fornecedor;
    private String boleto;

    public Pagar() {
        fornecedor = new Fornecedor();
    }

    // Getters
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public String getBoleto() {
        return boleto;
    }

    // Setters 
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }

    @Override
    public void entrar() {
        fornecedor.entrar();
        System.out.println("|-----------------------------------------------|");

        System.out.print("| Digite o Boleto: ");
        boleto = leia.nextLine();
    }

    @Override
    public void imprimir() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("|              Contas a Pagar                   |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("|   " + fornecedor.toString());
        System.out.println("|   Boleto: " + boleto);

    }
}
