package aula06;

import java.util.Scanner;

public class CaixaEletronicoEntrada {
    private final Scanner scanner;
    private final int[] cedulaCaixa = {100, 50, 20, 10, 5, 2, 1}; //    Array com os valores das notas disponíveis.
    private final int[] qtd_cedula = new int[cedulaCaixa.length]; //    Array que armazena quantas cédulas de cada tipo foram usadas.
    private int total_cedula_usada; //    Guarda o total de cédulas usadas na operação.    
    private int valor;

//  Construtor
    public CaixaEletronicoEntrada(){
        scanner = new Scanner(System.in);
    }
    
    public void entrada() {
        System.out.println("\n  Carregado com Sucesso!!!");

        obterValor();// valor recebe o metodo para obter o valor do user 
        
        calcularCedula();
    }

    
    public void imprimir() {
        System.out.println("\nDistribuicao de cedulas:");

        for (int i = 0; i < qtd_cedula.length; i++) {
            if (qtd_cedula[i] > 0) {
                System.out.println("Nota de R$" + cedulaCaixa[i] + ": " + qtd_cedula[i] + " unidade(s)");
            }
        }

        System.out.println("Total de cesdulas entregues: " + total_cedula_usada);
    }

    
    private void obterValor() {
        boolean sair = false;
        do {
            System.out.print("\n  Digite um valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                sair = true;
            } else {
                System.out.println("\n ERRO: Valor INVALIDO!!! Digite novamente...");
                scanner.nextLine(); // Limpa entrada inválida
            }
        } while (!sair);
    }
    
    
    private void calcularCedula() {
        for (int i = 0; i < cedulaCaixa.length; i++) {
            qtd_cedula[i] = valor / cedulaCaixa[i];
            valor = valor % cedulaCaixa[i]; // para eu conseguir fazer o valor zerar, tenho q ficar dividindo ele então tenho q atribuir seu resto a ele mesmo para conseguir fgazer isso.
            total_cedula_usada += qtd_cedula[i];
        }
    }
}
