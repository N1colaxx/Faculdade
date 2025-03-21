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
    
    private void validaID(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("| Digite seu ID: ");
                id = leia.nextInt();
                leia.nextLine();
                
                // Verifica se o CEP tem 8 dígitos
                if (id > 0) {
                    valido = true;  // CEP válido
                } else {
                    System.out.println("\n ERRO: ID inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } // O cat capitura o erro se o user digitar algo q n seja um Numero . Usando a def (Exception e) ela captura qualquer erro.
            catch (Exception e) {
                System.out.println("\n ERRO: ID inválido!! Digite um número inteiro . Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
    
    @Override
    public void entrar(){    
        validaID();
        
        System.out.print("| Digite seu nome: ");
        nome = leia.nextLine();
        
//      Chamando o metodo do Endereço
        System.out.println("\n|-----------------------------------------------|");
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
        System.out.println("|   ID pessoa = " + id);
        System.out.println("|   Nome pessoa = " + nome);
        System.out.println("|   " + endereco.toString());
        System.out.println("|   " + telefone.toString());
   }
}
