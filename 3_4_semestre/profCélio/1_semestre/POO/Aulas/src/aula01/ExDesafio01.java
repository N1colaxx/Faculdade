/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula01;

/**
 *
 * @author Nicolas
 */

/**
    Tabela de Inss e Irrs  de 2024

INSS
    Faixa Salarial (R$)         Alíquota (%)	Parcela a Deduzir (R$)
    Até 1.412,00                7,5                 -
    De 1.412,01 até 2.666,68	9                   21,18
    De 2.666,69 até 4.000,03	12                  101,18
    De 4.000,04 até 7.786,02	14                  181,18

    Para calcular a contribuição, aplica-se a alíquota correspondente à faixa salarial e 
    subtrai-se a parcela a deduzir.
      

IRRF
    Base de Cálculo (R$)	Alíquota (%)	Parcela a Deduzir (R$)
    Até 2.259,20                Isento          -
    De 2.259,21 até 2.826,65	7,5             169,44
    De 2.826,66 até 3.751,05	15              381,44
    De 3.751,06 até 4.664,68	22,5            662,77
    Acima de 4.664,68           27,5            896,00
    
    Para calcular o IRRF, subtrai-se do salário bruto as deduções permitidas (como INSS, dependentes, entre outras), 
    aplica-se a alíquota correspondente e subtrai-se a parcela a deduzir.
**/
import java.util.Scanner;

public class ExDesafio01 {
    public static void main(String[] args) {
        // Solicita o salário ao usuário
        /*
        Scanner é uma classe de Java usada para capturar entradas do usuário.
        System.in indica que os dados virão do teclado.
        */
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite seu salário: R$ ");
            
            /*Aguarda o usuário digitar um número (salário).*/
            double salario = scanner.nextDouble();
            double inss = calcularINSS(salario);
            
            // Exibe o resultado
            System.out.printf("O desconto do INSS será de: R$ %.2f\n", inss);
        }
    }

    public static double calcularINSS(double salario) {
        double desconto;

        if (salario <= 1412.00) {
            desconto = salario * 0.075;
        } else if (salario <= 2666.68) {
            desconto = (salario * 0.09) - 21.18;
        } else if (salario <= 4000.03) {
            desconto = (salario * 0.12) - 101.18;
        } else if (salario <= 7786.02) {
            desconto = (salario * 0.14) - 181.18;
        } else {
            desconto = 908.86; // Teto do INSS
        }

        return desconto;
    }
}
