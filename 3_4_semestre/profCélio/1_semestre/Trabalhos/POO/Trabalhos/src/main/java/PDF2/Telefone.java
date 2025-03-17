/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Telefone {
//  Atributos da Class
    private int ddd;
    private long numero;
    
    
//    Construtor
    public Telefone(int ddd, long numero){
        this.ddd = ddd;
        this.numero = numero;
    }
    
    
//    Getters
    public int getDDD(){
        return ddd;
    }
    
    public long getNumero(){
        return numero;
    }
    
    
//    Setters
    public void setDDD(int ddd){
        this.ddd = ddd;
    }
    
    public void setNumero(long numero){
        this.numero = numero;
    }

    
}
