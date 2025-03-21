package PDF1;

import java.util.Scanner;

public class Vendedor {
    int carrosVendidos;
    double valorVendas;
    double salarioFixo;
    double comissaoPorCarro;
    double comissaoFixa;
    double comissaoVendas;
    double salarioFinal;
    
    public void entrada(){
        Scanner scanner = new Scanner(System.in);

        // Entrada de dados
        System.out.print("Digite o número de carros vendidos: ");
        carrosVendidos = scanner.nextInt();

        System.out.print("Digite o valor total das vendas: ");
        valorVendas = scanner.nextDouble();

        System.out.print("Digite o salário fixo: ");
        salarioFixo = scanner.nextDouble();

        System.out.print("Digite a comissão por carro vendido: ");
        comissaoPorCarro = scanner.nextDouble();

        // Cálculo do salário final
        comissaoFixa = carrosVendidos * comissaoPorCarro;
        comissaoVendas = valorVendas * 0.05;
        salarioFinal = salarioFixo + comissaoFixa + comissaoVendas;

        // Saída de dados
        System.out.println("Salário final do vendedor: R$ " + salarioFinal);

        scanner.close();
    }
    public static void main(String[] args) {
        Vendedor obj = new Vendedor();
        obj.entrada();
    }
}
