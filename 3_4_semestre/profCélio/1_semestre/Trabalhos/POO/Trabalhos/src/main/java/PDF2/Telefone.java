/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;


/**
 *
 * @author nicol
 */
public class Telefone implements InterfaceCadastro{
//  Atributos da Class
    private int ddd;
    private long numero;
    
    
//    Getters
    public int getDDD(){
        return ddd;
    }
    
    public long getNumero(){
        return numero;
    }
    
    
//    Setters
    public void setDDD(int ddd) {
            this.ddd = ddd;
        }
    
    public void setNumero(long numero){
            this.numero = numero;
        }
    
    @Override
    public void entrar(){
    
        System.out.print("Digite seu DDD:");
        ddd = leia.nextInt();
        
        System.out.print("Digite seu Numero: ");
        numero = leia.nextLong();
        
    }
    
    @Override
    public void imprimir(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        return "Telefone: (" + ddd +")"+ numero;
    }
    
    
    public static void main (String[] args){
        Telefone telefone = new Telefone();
        
        telefone.entrar();
        telefone.imprimir();
    }
}
