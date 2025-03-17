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
    private Endereco endereco_cobranca;
    
    
    
//    Construtor
    public Cliente(){};

    public Cliente( int id, String nome, Endereco endereco, Telefone telefone, String cnpj, String inscricao_estadual, String contato, double limite_credito, Endereco endereco_cobranca) {
        super(id, nome, endereco, telefone, cnpj, inscricao_estadual, contato);
        this.limite_credito = limite_credito;
        this.endereco_cobranca = endereco_cobranca;
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
    public void imprimir(){
        super.imprimir();

        System.out.println("|   Limite Credito: R$ " + limite_credito);
        System.out.println("|   Endereço de Cobrança: " + endereco_cobranca);
        System.out.println("|_______________________________________________|");
        System.out.println("|              Que é um CLIENTE                 |");
        System.out.println("|_______________________________________________|");
    }
            
            
    
    public static void main (String[] args){
        // Criando o Endereco de Cobrança
        Endereco endereco_cobranca = new Endereco("Rua das Neves", "345", "Apt 999", "Centro", "Reginópolis", "SP", 17190007);

        // Criando o Endereco PJ
        Endereco endereco = new Endereco("Rua das Flores", "123", "Apt 101", "Centro", "Reginópolis", "SP", 17190005);       
        
//        Criando o Telefone
        Telefone telefone = new Telefone(13, 444444444);
        
//        Criando o Cliente
        Cliente cliente = new Cliente(100, "Iven", endereco, telefone, "10.190.0001-005.", "está VEIA", "email tomatoma@ai.calica.com", 2000.20, endereco_cobranca);
        
        cliente.imprimir();
    }
}
