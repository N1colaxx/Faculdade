/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula06;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class CaixaEletronicoSaldo {
//    Atribustos da class
    private double saldo_caixa;
    private Scanner scanner;
    
    private int[] cedulas ={100, 50, 20, 10, 5, 2, 1};
//    Armazena a quantidade de cada cedula usada 
    private int[] qtd_cedulas;
    
    public CaixaEletronicoSaldo(){
        scanner = new Scanner(System.in);
    }
    
    public void menu(){
        System.out.println("\n  Carregado com Sucesso!!...\n");
        System.out.println("|===============================================|");
        System.out.println("|           Caixa Eletronico                    |");
        System.out.println("|===============================================|");
        System.out.println("""
        |   Escolha uma Opcao:
        |   [1] Alimentar Caixa
        |   [2] Mostrar Saldo e Cedulas
        |   [3} Saque
        |   
                           """);
    }
    
    public static void main(String[] args) {
        CaixaEletronicoSaldo obj = new CaixaEletronicoSaldo();;
        
        obj.menu();  
    }
}
