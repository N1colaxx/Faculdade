/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Receber extends Financeiro {

    private Cliente cliente;
    private String nota_fiscal;

    public Receber() {
        cliente = new Cliente();
    }

//    Gettees
    public Cliente getCliente() {
        return cliente;
    }

    public String getNota_fiscal() {
        return nota_fiscal;
    }

//    Setters
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNota_fiscal(String nota_fiscal) {
        this.nota_fiscal = nota_fiscal;
    }

    @Override
    public void entrar() {
        cliente.entrar();
        System.out.println("|-----------------------------------------------|");
        System.out.println("|   Insira os dados do Financeiro               |");
        super.entrar();
        System.out.print("| Digite a nota fiscal: ");
        nota_fiscal = leia.nextLine();
    }

    @Override
    public void imprimir() {
        System.out.println("|===============================================|");
        System.out.println("|               Conta a Receber                 |");
        System.out.println("|===============================================|");
        System.out.println("|           Dados do cliente                    \n|");
        System.out.println("|   " + cliente.toString());
        System.out.println("|-----------------------------------------------\n|");
        System.out.println("|          Dados Financeiro do Cliente          |");
        super.imprimir();
        System.out.println("|   Nota fiscal: " + nota_fiscal);
        System.out.println("|===============================================|");
    }
}
