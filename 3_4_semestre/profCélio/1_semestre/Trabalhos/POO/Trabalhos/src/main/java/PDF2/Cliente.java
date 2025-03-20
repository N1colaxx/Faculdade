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
        super.entrar();
        leia.nextLine();
        System.out.print("| Digite o Limite de Credito: ");
        limite_credito = leia.nextDouble();
        leia.nextLine();
        
        System.out.println("|_______________________________________________|");
        System.out.println("|       Insira o Endereco de Cobrança           |");
        endereco_cobranca.entrar();
    }
    
    
    @Override
    public void imprimir(){
        System.out.println("|===============================================|");
        System.out.println("|  Informacao Cliente                           |");
        System.out.println("|===============================================|");
        super.imprimir();
        System.out.println("|   Limite de credito: " + limite_credito);
        System.out.println("|   Endereco de combrança: " + endereco_cobranca);
    } 
    
    
    @Override
    public String toString(){
        return
                "|   ID = " + getID() + "\n"
                + "|   Nome = " + getNome() + "\n"
                + "|   " + getEndereco() + "\n"
                + "|   " + getTelefone() + "\n"
                + "|   CNPJ: " + getCNPJ() + "\n"
                + "|   Inscricao Estadual: " + getInscricaoEstadual() + "\n"
                + "|   Meio de Contato: " + getContato() + "\n"
                + "|   Limite de credito: " + limite_credito + "\n"
                +"|   Endereco de combrança: " + endereco_cobranca;
    }
}
