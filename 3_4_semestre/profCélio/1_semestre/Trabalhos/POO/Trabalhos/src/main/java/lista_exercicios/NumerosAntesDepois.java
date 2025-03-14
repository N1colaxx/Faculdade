package lista_exercicios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

public class NumerosAntesDepois {
        
    double num;
    
    

        
        public void entrada(){
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Digite um número: ");
             num = scanner.nextDouble();

            // Imprime os 50 números anteriores
            System.out.println("50 números anteriores:");
            for (double i = num - 50; i < num; i++) {
                System.out.print(i + " ");
            }

            System.out.println(); // Quebra de linha para melhorar a saída

            // Imprime os 50 números posteriores
            System.out.println("50 números posteriores:");
            for (double i = num + 1; i <= num + 50; i++) {
                System.out.print(i + " ");
            }

            // Fecha o scanner
            scanner.close();
        }
    
    public static void main(String[] args) {
        NumerosAntesDepois obj = new NumerosAntesDepois();
        obj.entrada();
        //obj.anteriores();
        //obj.posterior();
    }
}

