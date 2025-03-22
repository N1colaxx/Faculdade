package aula06;

import java.util.Scanner;

public class CaixaEletronicoEntrada {
//    Guarda o total de cédulas usadas na operação.
    int qtd_total_cedula;
//    Array com os valores das notas disponíveis.
    int[] cedulaCaixa = {100, 50, 20, 10, 5, 2, 1}; // Notas disponíveis
//    Array que armazena quantas cédulas de cada tipo foram usadas.
    int[] qtd_cedula; // Armazena a distribuição de cédulas

    public void entrada() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n  Carregado com Sucesso!!!");

        int valor = obterValor(scanner);
        int[] qtd_total = {0}; // Array para armazenar o total de cada cédula
        qtd_cedula = calcularCedula(valor, qtd_total);

        qtd_total_cedula = qtd_total[0];
    }

    public void imprimir() {
        System.out.println("\nDistribuicao de cedulas:");

        for (int i = 0; i < qtd_cedula.length; i++) {
            if (qtd_cedula[i] > 0) {
                System.out.println("Nota de R$" + cedulaCaixa[i] + ": " + qtd_cedula[i] + " unidade(s)");
            }
        }

        System.out.println("Total de cesdulas entregues: " + qtd_total_cedula);
    }

    private static int obterValor(Scanner scanner) {
        boolean sair = false;
        int valor = 0;

        do {
            System.out.print("\n  Digite um valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine();
                sair = true;
            } else {
                System.out.println("\n ERRO: Valor INVALIDO!!! Digite novamente...");
                scanner.nextLine(); // Limpa entrada inválida
            }
        } while (!sair);

        return valor;
    }

    private int[] calcularCedula(int valor, int[] qtd_total) {
        int[] qtd_cedula = new int[cedulaCaixa.length];

        for (int i = 0; i < cedulaCaixa.length; i++) {
            qtd_cedula[i] = valor / cedulaCaixa[i];
            valor = valor % cedulaCaixa[i];
            qtd_total[0] += qtd_cedula[i]; // Soma a quantidade total de cédulas usadas
        }
        return qtd_cedula;
    }
}
