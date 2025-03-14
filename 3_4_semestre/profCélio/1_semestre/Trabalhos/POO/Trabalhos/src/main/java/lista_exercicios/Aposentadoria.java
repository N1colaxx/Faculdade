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

public class Aposentadoria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lê os dados do empregado
        System.out.print("Digite o código do empregado: ");
        int codigo = scanner.nextInt();
        System.out.print("Digite o ano de nascimento: ");
        int anoNascimento = scanner.nextInt();
        System.out.print("Digite o ano de ingresso na empresa: ");
        int anoIngresso = scanner.nextInt();
        
        // Calcula a idade e tempo de trabalho
        int idade = 2025 - anoNascimento; // Considerando o ano atual como 2025
        int tempoTrabalho = 2025 - anoIngresso;
        
        // Verifica se o empregado pode se aposentar
        if ((idade >= 65 && codigo % 2 == 0) || (idade >= 60 && codigo % 2 != 0) || (codigo % 2 == 0 && tempoTrabalho >= 30) || (codigo % 2 != 0 && tempoTrabalho >= 25)) {
            System.out.println("Requerer aposentadoria");
        } else {
            System.out.println("Não requerer aposentadoria");
        }
    }
}

