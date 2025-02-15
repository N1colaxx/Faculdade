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
Baseado no exercício 1, defina uma variável adiantamento, com valor de R$ 
120,00; uma variável FGTS, com valor de 8%, e outra que calcule o valor do FGTS. 
Calcule o salário líquido descontando o valor do adiantamento. Imprima o valor do 
adiantamento, o valor do FGTS e o salário líquido calculado (sem descontar o valor 
do FGTS). 
**/

public class Ex02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double salarioBruto = 1340;
        double inss = 0.11;
        double fgts = 0.08;
        
        int diastraBalhados = 18;
        int horasMensais = 160;
        int horasDiarias = 8;
        int adiantamento = 120;
         
        double salarioPorHora;
        double salarioLiquido;
        double descontoInss;
        double valorFgts;
   
        
        /**
         * Calculo das var *
         */
        descontoInss = salarioBruto * inss;
        valorFgts = salarioBruto * fgts;
        salarioPorHora = (salarioBruto / horasMensais) * (diastraBalhados * horasDiarias);
        salarioLiquido = salarioPorHora - (descontoInss + adiantamento);
        
        /**
         * Impresão das variaveis*
         */
        System.out.println("Salario Mensal... " + salarioBruto + "\n");
        System.out.println("Dias trabalhados... " + diastraBalhados);
        System.out.println("Horas mensais... " + horasDiarias);
        System.out.println("Salario por Hora..." + salarioPorHora + "\n");
        System.out.println("Desconto do INSS... " + descontoInss);
        System.out.println("Valor FGTS..." + valorFgts);
        System.out.println("Valor do adiantamento..." + adiantamento + "\n");
        System.out.println("Salario liquido... " + salarioLiquido);
    }
}

