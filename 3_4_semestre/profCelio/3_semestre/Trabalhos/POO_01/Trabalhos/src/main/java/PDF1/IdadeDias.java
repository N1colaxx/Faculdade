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

public class IdadeDias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de dados
        System.out.print("Digite o ano de nascimento: ");
        int anoNascimento = scanner.nextInt();

        System.out.print("Digite o mês de nascimento (1-12): ");
        int mesNascimento = scanner.nextInt();

        System.out.print("Digite o dia de nascimento: ");
        int diaNascimento = scanner.nextInt();

        // Data atual
        int anoAtual = 2025; // Você pode mudar o ano atual manualmente
        int mesAtual = 3;    // Mês atual manual
        int diaAtual = 17;   // Dia atual manual

        // Calculando a idade em dias
        int idadeEmDias = 0;

        // Calculando os dias dos anos
        idadeEmDias += (anoAtual - anoNascimento) * 365;

        // Adicionando os dias dos meses
        idadeEmDias += (mesAtual - mesNascimento) * 30;

        // Adicionando os dias do próprio mês
        idadeEmDias += (diaAtual - diaNascimento);

        // Saída de dados
        System.out.println("Sua idade em dias é: " + idadeEmDias);

        scanner.close();
    }
}


