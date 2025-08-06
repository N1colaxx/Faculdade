package util;

import java.util.Scanner;

public class Validacoes {

    private static final Scanner scanner = new Scanner(System.in); // único Scanner reutilizado

    public Validacoes() {
        // Construtor pode continuar vazio
    }

    public static String lerOpcaoLetraE(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.isEmpty()) continue;

            if (entrada.length() == 1 && entrada.matches("[A-E]")) {
                return entrada;
            }

            System.out.println("Por favor, digite apenas uma letra entre A e E.");
        }
    }

    public static String lerString(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Por favor, preencha o campo. O campo está vazio.");
            } else {
                return entrada;
            }
        }
    }

    public static double lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");

            if (entrada.matches("-?\\d+(\\.\\d+)?")) {
                return Double.parseDouble(entrada);
            } else {
                System.out.println("Erro: \"" + entrada + "\" não é um número válido.");
            }
        }
    }

    public static int lerNumeroInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            if (entrada.matches("-?\\d+")) {
                return Integer.parseInt(entrada);
            } else {
                System.out.println("Erro: \"" + entrada + "\" não é um número inteiro válido.");
            }
        }
    }

    public static int lerInteiroPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            if (!scanner.hasNextInt()) {
                System.out.println("\nERRO: você não digitou um número.");
                scanner.next(); // consome a entrada inválida
                continue;
            }

            int entrada = scanner.nextInt();
            scanner.nextLine(); // consome o '\n' após o número

            if (entrada > 0) {
                return entrada;
            } else {
                System.out.println("Erro: \"" + entrada + "\" não é um número inteiro positivo.");
            }
        }
    }
}
