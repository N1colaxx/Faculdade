/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;


/**
 *
 * @author nicol
 */
public class Endereco implements InterfaceCadastro{
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private int cep;

//    Contrutor
    public Endereco(){};
    
    
//    Getters
    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public int getCep() {
        return cep;
    }

//    Setters
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

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
    

    
    @Override
    public void entrar(){

        
        System.out.print("| Digite seu Logradouro: ");
        logradouro = leia.nextLine();
        
        System.out.print("| Digite seu Numero: ");
        numero = leia.nextLine();
        
        System.out.print("| Digite seu Complemento: ");
        complemento = leia.nextLine();
        
        System.out.print("| Digite seu Bairro: ");
        bairro = leia.nextLine();
        
        System.out.print("| Digite sua Cidade: ");
        cidade = leia.nextLine();
        
        System.out.print("|Digite seu Estado: ");
        estado = leia.nextLine();
        
        System.out.print("| Digite seu Cep: ");
        cep = leia.nextInt();

    }
    
    @Override
    public void imprimir(){
        System.out.println("Endereço: " + this.toString());
    }
    
    @Override
    public String toString() {
        return   "Endereço: " + "Logradouro: " + logradouro + ", Numero: " + numero + ", Complemento: " + complemento + ", Bairro: " + bairro + ", Cidade:" + cidade + ", Estado: " + estado + ", CEP: " + cep;
    }
    
    public static void main(String[] args) {
        Endereco endereco = new Endereco();

        endereco.entrar();
        endereco.imprimir();
    }
            
}
