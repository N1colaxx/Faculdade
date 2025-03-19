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
    
    public Receber(){
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
    public void entrar(){
        cliente.entrar();
        System.out.println("|-----------------------------------------------|");
        leia.nextLine();
        
        System.out.print("Digite a nota fiscal: ");
        nota_fiscal = leia.nextLine();
    }
    
    
    @Override
    public void imprimir(){
        System.out.println("|===============================================|");
        System.out.println("|                   Receber                     |");
        System.out.println("|===============================================|");
        System.out.println("|           Dados do cliente                    \n|");
        System.out.println("|   " + cliente);
        System.out.println("|   Nota fiscal: " + nota_fiscal);
    }
    
    public static void main (String[] args){
        Receber obj = new Receber();
         
        obj.entrar();
        obj.imprimir();
    }
}
