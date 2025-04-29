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

public class Aposentadoria {
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita e lê o número do empregado (código)
        System.out.print("Digite o número do empregado: ");
        int codigo = scanner.nextInt();

        // Solicita e lê o ano de nascimento do empregado
        System.out.print("Digite o ano de nascimento do empregado: ");
        int anoNascimento = scanner.nextInt();

        // Solicita e lê o ano de ingresso na empresa
        System.out.print("Digite o ano de ingresso na empresa: ");
        int anoIngresso = scanner.nextInt();

        // Solicita e lê o sexo do empregado (M para masculino, F para feminino)
        System.out.print("Digite o sexo do empregado (M/F): ");
        char sexo = scanner.next().charAt(0);

        // Calcula a idade do empregado (considerando o ano atual como 2023)
        int anoAtual = 2025;
        int idade = anoAtual - anoNascimento;

        // Calcula o tempo de trabalho do empregado
        int tempoTrabalho = anoAtual - anoIngresso;

        // Exibe a idade e o tempo de trabalho do empregado
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Tempo de trabalho: " + tempoTrabalho + " anos");

        // Verifica se o empregado está qualificado para a aposentadoria
       if ((sexo == 'M' || sexo == 'm') && idade >= 65 || (sexo == 'F' || sexo == 'f') && idade >= 60) {
            System.out.println("Requerer aposentadoria");
        } else if ((sexo == 'M' && tempoTrabalho >= 30) || (sexo == 'F' && tempoTrabalho >= 25)) {
            System.out.println("Requerer aposentadoria");
        } else {
            System.out.println("Não requerer aposentadoria");
        }

        // Fecha o scanner para liberar recursos
        scanner.close();
    }
}

