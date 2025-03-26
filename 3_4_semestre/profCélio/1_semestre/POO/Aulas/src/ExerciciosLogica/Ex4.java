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
public class Ex4 {
    static double num;
    static String res;

    
    public static void entrada(Scanner scanner){
        System.out.println("Digite um numero: ");
        num = scanner.nextDouble();
        scanner.nextLine();
    }
    
    public static void calculando(double n){
        double y = n;
        double t = 0;
        for(int i=0; i < y; i++ ){
            System.out.println("indicie i: " + i);
            if(n % i == 0){
                t ++;
            }
            
            if(t > 2){
                res = "Nao e primo";
            }else{
                res = "Sim e primo";
            }
        }
    }
    
    
    public static void imprimindo(double n, String r){
        System.out.println("\nEsse numero: " + n);
        System.out.println("Ele " + r);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        entrada(scanner);
        
        calculando(num);
        
        imprimindo(num, res);
    }
}
