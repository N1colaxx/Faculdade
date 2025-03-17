/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Endereco {
//  Atributos da class
    private String logadouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int cep;
    
//    criando o Construtor

    public Endereco(String logadouro, String numero, String complemento, String bairro, String cidade, String estado, int cep){
        this.logadouro = logadouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
    
//    Criando o GETTERS
    public String getLogadouro(){
        return logadouro;
    }
    
    public String getNumero(){
        return numero;
    }
    
    public String getComplemento(){
        return complemento;
    }
    
    public String getBairro(){
        return bairro;
    }
      
    public String getCidade(){
        return cidade;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public int getCep(){
        return cep;
    }
    

//    Criando os SETTER
    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

// primeiro usar .matches que é um método da classe String em Java.
// depois uso a epreçaõ REGEX
    public void setNumero(String numero) {
            this.numero = numero; 
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(int cep) {
            this.cep = cep;        
    }
    
}


