package PDF1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

public class MenorNumeroDecimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lê os três números com decimais
        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();
        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();
        System.out.print("Digite o terceiro número: ");
        double num3 = scanner.nextDouble();
        
        // Calcula e exibe o menor número
        double menor = Math.min(num1, Math.min(num2, num3));
        System.out.println("O menor número é: " + menor);
    }
}

