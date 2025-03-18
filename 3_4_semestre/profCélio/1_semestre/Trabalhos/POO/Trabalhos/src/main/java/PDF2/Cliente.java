/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Cliente {
//    Atributos da Class
    private double limite_credito;
    private Endereco endereco_cobranca;
    
    
    
//    Construtor
    public Cliente(){};


    
      
    
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
    
    
}
