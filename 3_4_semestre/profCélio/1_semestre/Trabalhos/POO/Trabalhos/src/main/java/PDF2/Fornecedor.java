/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
import java.util.Scanner;

public class Fornecedor extends PessoaJuridica{
// Atributos da class
    private double lime_compra;
    private String data_cadastro;
    private String site;

//    Construtor 
    public Fornecedor(){};
    
    public Fornecedor(int id, String nome, double lime_compra, String data_cadastro, String site, Endereco endereco, Telefone telefone){
        super(id, nome, endereco, telefone);
        this.lime_compra = lime_compra;
        this.data_cadastro = data_cadastro;
        this.site = site;
    }

//    Getters
    public double getLime_compra() {
        return lime_compra;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public String getSite() {
        return site;
    }

//    Setters
    public void setLime_compra(double lime_compra) {
        this.lime_compra = lime_compra;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    
    public void entrada(){
       Scanner scanner = new Scanner(System.in);

        // Entrada de dados básicos da classe Fornecedor
        System.out.print("Digite o ID: ");
        setID(scanner.nextInt());
        scanner.nextLine();  // Consome a linha extra

        System.out.print("Digite o nome do fornecedor: ");
        setNome(scanner.nextLine());


        // Entrada dos dados específicos de Fornecedor
        System.out.print("Digite o limite de compra: ");
        setLime_compra(scanner.nextDouble());

        scanner.nextLine();  // Consome a linha extra

        System.out.print("Digite a data de cadastro: ");
        setData_cadastro(scanner.nextLine());

        System.out.print("Digite o site: ");
        setSite(scanner.nextLine());
        
//      Entrada dos dados do Endereco
        System.out.print("Digite o logradouro do endereço: ");
        String logradouro = scanner.nextLine();
        
        System.out.print("Digite o número do endereço: ");
        String numero = scanner.nextLine();
        
        System.out.print("Digite o complemento do endereço: ");
        String complemento = scanner.nextLine();
        
        System.out.print("Digite o bairro do endereço: ");
        String bairro = scanner.nextLine();
        
        System.out.print("Digite a cidade do endereço: ");
        String cidade = scanner.nextLine();
        
        System.out.print("Digite o estado do endereço: ");
        String estado = scanner.nextLine();
        
        System.out.print("Digite o CEP do endereço: ");
        int cep = scanner.nextInt();
        scanner.nextLine();  
        
//        criando o obj Endereco
        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, estado, cep);
        setEndereco(endereco);
        
//      Entrada dos dados do Telefone
        System.out.print("Digite o DDD do telefone: ");
        int ddd = scanner.nextInt();
        
        System.out.print("Digite o número do telefone: ");
        long numeroTelefone = scanner.nextLong();
        scanner.nextLine();  // Consome a linha extra
        
//        Criando obj Telefone
        Telefone telefone = new Telefone(ddd, numeroTelefone);
        setTelefone(telefone);
    }

    
    public void imprimir() {
        super.imprimir();

        System.out.println("------ Dados do Fornecedor ------");
        System.out.println("Limite de Compra: R$ " + getLime_compra());
        System.out.println("Data de Cadastro: " + getData_cadastro());
        System.out.println("Site: " + getSite());
        System.out.println("---------------------------------");
    } 
   
   
    public static void main(String[] args) {   
        Fornecedor obj = new Fornecedor();
        
        obj.entrada();
        obj.imprimir();
    }
    
}
