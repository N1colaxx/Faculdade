/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */


public abstract class Pessoa implements InterfaceCadastro{
    private int id;
    private String nome;
    
//    Atribuicao de Endereco e Telefone
    private Endereco endereco;
    private Telefone telefone;
    
    
    
// Construtor    
    public Pessoa(){
        
        this.endereco = new Endereco(); 
        this.telefone = new Telefone();
    }
    
    
//  Getters Pessoa
    public  int getID(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public Endereco getEndereco(){
        return endereco;
    }
    
    public Telefone getTelefone(){
        return telefone;
    }
    
    
//  Setters Pessoa
    public void setID(int id){
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    @Override
    public void entrar(){

        
        System.out.print("| Digite seu ID: ");
        id = leia.nextInt();
        leia.nextLine();
        
        System.out.print("| Digite seu nome: ");
        nome = leia.nextLine();
        
//      Chamando o metodo do Endereço
        System.out.println("|-----------------------------------------------|");
        System.out.println("|       Incira os dados do Endereco.            |");
        System.out.println("|-----------------------------------------------|");
        endereco.entrar();
       
//      Chamando o metodo do Telefone
        System.out.println("|-----------------------------------------------|");
        System.out.println("|           Digite Telefone:                    |");
        System.out.println("|-----------------------------------------------|");
        telefone.entrar();

    }
    
    @Override
    public void imprimir() {
        System.out.println("|===============================================|");
        System.out.println("|       Estes dados são de uma Pessoa           |");
        System.out.println("|===============================================|");
        System.out.println("|   ID = " + id);
        System.out.println("|   Nome = " + nome);
        System.out.println("|   " + endereco.toString());
        System.out.println("|   " + telefone.toString());
   }
   
    
    @Override
    public String toString(){
        return
            "|  ID = " + id +
            "\n|    Nome = " + nome +
            "\n|   " + endereco.toString() +
            "\n|   " + telefone.toString();
    }
   
   public static void main (String[] args){
       Funcionario pessoa = new Funcionario();
       
       pessoa.entrar();
       pessoa.imprimir();
   }
}
