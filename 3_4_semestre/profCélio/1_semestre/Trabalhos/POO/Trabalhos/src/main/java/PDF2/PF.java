/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class PF extends Pessoa{
    private String cpf;
    private String rg;
    private String emissor_rg;
    
    
    // Construtor Pessoa Fisica
    public PF(){};


//    Getters Pessoa Fisica
    public String getCPF(){
        return cpf;
    }
    
    public String getRG(){
        return rg;
    }
    
    public String getEmissorRG(){
        return emissor_rg;
    }
    
//    Setters Pessoa Fisica   
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public void setRG(String rg){
        this.rg = rg;
    }
    
    public void setEmissorRG(String emissor_rg){
        this.emissor_rg = emissor_rg;
    }

    @Override
    public void entrar(){
        super.entrar();
        leia.nextLine();
        
        System.out.print("Digite seu CPF: ");
        cpf = leia.nextLine();
        
        System.out.print("Digite seu Numero do RG: ");
        rg = leia.nextLine();
        
        System.out.print("Digite o Emissor do RG: ");
        emissor_rg = leia.nextLine();
    }
    
    @Override
    public void imprimir(){
        System.out.println("|======================================================|");
        System.out.println("|               Pessoa Fisica                          |");
        System.out.println("|======================================================|");

        // Imprime os dados herdados de Pessoa
        System.out.println("|   ID = " + getID());
        System.out.println("|   Nome = " + getNome());
        System.out.println("|   " + getEndereco());
        System.out.println("|   " + getTelefone());
        System.out.println("|   CPF: " + cpf);
        System.out.println("|   Numero RG: " + rg);
        System.out.println("|   Emissor RG: " + emissor_rg);
    }

    
    public static void  main(String[] args){
        PF pessoa_fisica = new PF();
        
        pessoa_fisica.entrar();
        pessoa_fisica.imprimir();
    }

}




