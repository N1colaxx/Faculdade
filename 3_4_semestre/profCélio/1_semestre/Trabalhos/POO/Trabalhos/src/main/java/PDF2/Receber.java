/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Receber {
    private Cliente cliente;
    private String nota_fiscal;
    
    
    public Receber(){};

    
    
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
    
    
}
