/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula05;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Cartaos {
    private Cartaos cartao;
    private int cod;
    private String nome_titular;
    private int num_cartao;
    private int data_vencimento;
    
    
    
    public Cartaos(){
    };
    
    public Cartaos(Cartaos cartaos, int cod, int num_cartao, int data_vencimento, String nome_titular){
        this.cod = cod;
        this.data_vencimento = data_vencimento;
        this.num_cartao = num_cartao;
        this.nome_titular = nome_titular;
    }
    

    
    
    public int getCod() {
        return cod;
    }
    
    public void setCod(int cod){
        this.cod = cod;
    }

    
    public String getNome_titular() {
        return nome_titular;
    }
    
    public void setNome_titular(String nome_titular) {
        this.nome_titular = nome_titular;
    }
    
    
    public int getNum_cartao() {
        return num_cartao;
    }
    
    public void setNum_cartao(int num_cartao) {
        this.num_cartao = num_cartao;
    }

    public int getData_vencimento() {
        return data_vencimento;
    }
    
    public void setData_vencimento(int data_vencimento){
        this.data_vencimento = data_vencimento;
    }
    
    
    public void entrar(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o cod CCV: ");
        setCod(scanner.nextInt());
       
        System.out.print("Digite o nome do titular: ");
        setNome_titular(scanner.nextLine());
        scanner.nextLine();

        System.out.print("Digite o número do cartão: ");
        setNum_cartao(scanner.nextInt());

        System.out.print("Digite a data de vencimento (formato AAAAMMDD): ");
        setData_vencimento(scanner.nextInt());
        
        scanner.close();
        
        imprimir();
    }
    
    public void imprimir(){
        System.out.println("Cod: " + cod);
        System.out.println("Nome titular: " + nome_titular);
        System.out.println("Data cencimento: " + data_vencimento);
        System.out.println("Numero cartão: " + num_cartao);
    }
    
    public static void main(String[] args) {
        Cartaos obj = new Cartaos();
        
        obj.entrar();
    }
}
