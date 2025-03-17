/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Receber extends Cliente{
    private Cliente cliente;
    private String nota_fiscal;
    
    
    public Receber(){};

    public Receber(int id, String nome, Endereco endereco, Telefone telefone, String cnpj, String inscricao_estadual, String contato, double limite_credito, Endereco endereco_cobranca, Cliente cliente, String nota_fiscal) {
        super(id, nome, endereco, telefone, cnpj, inscricao_estadual, contato, limite_credito, endereco_cobranca);
        this.cliente = cliente;
        this.nota_fiscal = nota_fiscal;
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
    
    
}
