/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExerciciosLogica;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 * 
 * 1. Soma dos Números Pares
 *  Enunciado:
 *   Escreva um programa que peça ao usuário um número inteiro N e exiba a soma de todos os números pares de 1 até N.
 *
 *   Exemplo:
 *   Entrada: 10
 *   Saída: 30 (2 + 4 + 6 + 8 + 10)
 */

public class Ex1 {
    int num;
    int soma;
    int [] pares;
    
    public void entrada(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um N°: ");
        num = scanner.nextInt();
        scanner.nextLine();
        
        scanner.close();
    }
    
    
    public void calcular(){
        int Y = num;
        int x;
        pares = new int[num];
        
        for(int i=0; i < num; i++){
            x = Y % 2;
            if(x == 0){
                System.out.println("Num = "+ Y);
                soma += Y;
                pares[i] += Y;
            }
            
            Y --;
        }
    }
    
    public void imprimir(){
        System.out.println("Vc digitou o n°: " + num);
        System.out.println("A soma de todos os deu pares é: " + soma);
        System.out.print("Esses são os numeros: ");
        for(int i=0; i < pares.length; i++){
            if(pares[i] != 0){
                System.out.print(pares[i] + " ");
            }
        }
    }
    public static void main(String[] args) {
        Ex1 obj = new Ex1();
        
        obj.entrada();
        obj.calcular();
        obj.imprimir();
        
    }
}
