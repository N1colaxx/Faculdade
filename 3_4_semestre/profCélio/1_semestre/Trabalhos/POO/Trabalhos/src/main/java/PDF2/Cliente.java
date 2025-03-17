/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Cliente extends PessoaJuridica{
//    Atributos da Class
    private double limite_credito;
    
    
    
//    Construtor
    public Cliente(){};
    
    public Cliente(int id, String nome, Endereco endereco_cobranca, Telefone telefone, double limite_credito){
        super(id, nome, endereco_cobranca, telefone);
        this.limite_credito = limite_credito;

    }
            
    
//    Getter de Cliente
    public double getLimiteCredito(){
        return limite_credito;
    }
//  Setter de Cliente
    public void setLimiteCredito(double limite_credito){
        this.limite_credito = limite_credito;
    }
    
    
    @Override
    public void imprimir(){
        super.imprimir();
        if (limite_credito >= 0){
            System.out.println("Limite Credito: R$ " + limite_credito);
        } else {
            System.out.println("ERRO: Linite de Credito NULO ou Invalido!");
        }
        System.out.println("|_________________________________________|");
        System.out.println("|   Estas Informações são de um CLIENTE   |");
    }
            
            
    
    public static void main (String[] args){
//        Criando o Endereco de Cobranca
        Endereco endereco_cobanca = new Endereco("Rua das Neves", "345", "Apt 999", "Centro", "Reginópolis", "SP", 17190007);
        
//        Criando o Telefone
        Telefone telefone = new Telefone(13, 444444444);
        
//        Criando o Cliente
        Cliente cliente = new Cliente(100, "Iven", endereco_cobanca, telefone, 2000.20);
        
        cliente.imprimir();
    }
}
