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

public class IdadeDias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lê a idade em anos, meses e dias
        System.out.print("Digite sua idade em anos: ");
        int anos = scanner.nextInt();
        System.out.print("Digite sua idade em meses: ");
        int meses = scanner.nextInt();
        System.out.print("Digite sua idade em dias: ");
        int dias = scanner.nextInt();
        
        // Calcula a idade total em dias
        int idadeEmDias = (anos * 365) + (meses * 30) + dias;
        System.out.println("Sua idade em dias é: " + idadeEmDias);
    }
}

