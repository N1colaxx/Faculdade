/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Pessoa {
//    Atributos da class
    private int id;
    private String nome;
    
//    Atribuicao de Endereco e Telefone
    private Endereco endereco;
    private Telefone telefone;
    
    
    
//  Construtores
    public Pessoa(){}; // construtor vazio// construtor vazio
    
    public Pessoa(int id, String nome, Endereco endereco, Telefone telefone){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco; 
        this.telefone = telefone;
    }
    
    
//  Setters para Endereco e Telefone pois  estão em outra class e NÂO È HERDADO(EXTENDS)
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
//  Getters Pessoa
    public  int getID(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
//  Setters Pessoa
    public void setID(int id){
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    

   public void imprimir() {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("ID = " + id);
            System.out.println("Nome = " + nome);
           System.out.println("Telefone = (" + telefone.getDDD() + ")" + telefone.getNumero());
            System.out.println("Logradouro: " + endereco.getLogadouro());
            System.out.println("Número: " + endereco.getNumero());
            System.out.println("Complemento: " + endereco.getComplemento());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getCidade());
            System.out.println("Estado: " + endereco.getEstado());
            System.out.println("CEP: " + endereco.getCep());
   }
   

//    Método principal para testes
    public static void main(String[] args) {
//      Criando um Endereco  
        Endereco endereco1 = new Endereco("Rua das Flores", "123", "Apt 101", "Centro", "Reginópolis", "SP", 17190005);
//        Endereco endereco2 = new Endereco("Rua Boa VIsta", "234", "Apt 202", "Centro", "Reginópolis", "SP", 17190006);

//      Criando um Telefone
        Telefone t1 = new Telefone(10,111111111);
//        Telefone t2 = new Telefone(14,222222222);
        
//      Criando uma Pessoa
        Pessoa p1 = new Pessoa(1, "Nicolas", endereco1, t1);
//        Pessoa p2 = new Pessoa(3, "Anto", endereco2, t2);
        
        
        p1.imprimir();
//        p2.imprimir();
    }
}
