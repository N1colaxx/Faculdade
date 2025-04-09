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
public class CaixaEletronicoCedula {
    private int[] cedulas_caixa = {100, 50, 20, 10, 5, 2, 1};
    private int[] cedula_user;
    private int saldo_user;
    
    private final Scanner scanner;
    
//    Armazena a quantidade de cada cedula usada 
    
    public CaixaEletronicoCedula(){
        this.cedula_user = new int[cedulas_caixa.length];
        scanner = new Scanner(System.in);
    }
    
    public void entrar(){
        int cedula;
        
        System.out.println("\n  Digite a quantidade de notas que vc possui: ");
        for(int i=0; i < cedulas_caixa.length; i++){
            System.out.print("      quantas notas de R$" + cedulas_caixa[i] + ": ");
            cedula = scanner.nextInt();
            
            cedula_user[i] = cedula;
        }
        calcular();
    }
    
    public void imprimir(){
        System.out.println("\n O saldo do User é de: R$"+ saldo_user);
    }
    
    private void calcular(){
        for(int i=0; i < cedula_user.length; i++){
            saldo_user += cedula_user[i] * cedulas_caixa[i];
        }
    }

}
