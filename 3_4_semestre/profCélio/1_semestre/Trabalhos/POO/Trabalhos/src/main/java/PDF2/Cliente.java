/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */

public class Cliente extends PJ{
//    Atributos da Class
    private double limite_credito;
    private Endereco endereco_cobranca;
    

//    Construtor
 
    public Cliente(){
        endereco_cobranca = new Endereco();
    }
   
    
//    Getter de Cliente
    public double getLimiteCredito(){
        return limite_credito;
    }
    
    public Endereco getEnderecoCobranca(){
        return endereco_cobranca;
    }
    
//  Setter de Cliente
    public void setLimiteCredito(double limite_credito){
        this.limite_credito = limite_credito;
    }
    
    public void setEnderecoCobranca(Endereco endereco_cobranca){
        this.endereco_cobranca = endereco_cobranca;
    }
    
    @Override
    public void entrar(){
        System.out.print("| Digite o ID: ");
        setID(leia.nextInt());
        leia.nextLine();
        
        System.out.print("| Digite o Limite de Credito: ");
        limite_credito = leia.nextDouble();
        leia.nextLine();
        
        System.out.println("|_______________________________________________|");
        System.out.println("|       Insira o Endereco de Cobrança           |");
        endereco_cobranca.entrar();
    }
    
    public void imprimir(){
        System.out.println("|===============================================|");
        System.out.println("|                  Cliente                      |");
        System.out.println("|===============================================|");
        System.out.println("|   ID do cliente: " + getID());
        System.out.println("|   Limite de credito: " + limite_credito);
        System.out.println("|   Endereco de comnbança: " + endereco_cobranca);
    }
    
    public static void main(String[] agrs){
        Cliente cliente = new  Cliente();
        
        cliente.entrar();
        cliente.imprimir();
    }
    
}
