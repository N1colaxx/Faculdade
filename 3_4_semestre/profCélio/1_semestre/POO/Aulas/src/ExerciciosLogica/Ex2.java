/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExerciciosLogica;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Ex2 {
    int num;
    int soma = 1 ;
    int[] fatorial;
    
    public void entrada(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um N°: ");
        num = scanner.nextInt();
        scanner.nextLine();
        
        scanner.close();
    }
    
    
    public void calcular(){
        int y = num;
        int x = 1;
        fatorial = new int[num];
        
        for(int i=0; i < num ; i++){
                soma = soma * y; //
                System.out.println("Soma = " + soma );
                fatorial[i] += y;
                y --;
        }
    }
    
    public void imprimir(){
        System.out.println("Vc digitou o n°: " + num);
        System.out.print("O fatorial !" + num + " é: ");
        for(int i=0; i < fatorial.length; i++){
            if(fatorial[i] != 0){
                System.out.print(" x " + fatorial[i]);
            }
        }
        System.out.println(" = " + soma);
    }

    public static void main(String[] args) {
        Ex2 obj = new Ex2();
        
        obj.entrada();
        obj.calcular();
        obj.imprimir();
        
    }
}
