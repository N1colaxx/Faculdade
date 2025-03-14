/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

public class Salario_Vendedor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lê os dados do vendedor
        System.out.print("Digite o salário fixo: ");
        double salarioFixo = scanner.nextDouble();
        System.out.print("Digite o número de carros vendidos: ");
        int carrosVendidos = scanner.nextInt();
        System.out.print("Digite a comissão por carro vendido: ");
        double comissaoPorCarro = scanner.nextDouble();
        System.out.print("Digite o valor total das vendas: ");
        double totalVendas = scanner.nextDouble();
        
        // Calcula o salário final
        double salarioFinal = salarioFixo + (carrosVendidos * comissaoPorCarro) + (0.05 * totalVendas);
        System.out.println("O salário final do vendedor é: R$ " + salarioFinal);
    }
}
