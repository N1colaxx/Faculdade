/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula03;
import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class ex3_tabuadaWHILE {
    
    int user_numero;
    
    public void calculatTabuada(){
         int i = 0;
         System.out.println("\nTabuada do " + user_numero + " usando o While:");
         while (i++ < 10) {            
                int numero_tab = user_numero * i;
                System.out.println(user_numero + " x " + i + " = " + numero_tab);
         }
        }
        
    public void entradaDados() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite um numero: ");
        user_numero = scanner.nextInt();
        
        calculatTabuada();
    }
    public static void main(String[] args) {
        
        ex3_tabuadaWHILE  tabuada = new ex3_tabuadaWHILE();
        
        tabuada.entradaDados();
    }
}