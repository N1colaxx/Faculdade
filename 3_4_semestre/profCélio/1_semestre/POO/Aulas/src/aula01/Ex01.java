/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aula01;

/**
 *
 * @author Nicolas
 */
public class Ex01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double salario = 1340;
        int diastrabalhados = 18;
        int horasmensais = 160;
        int horasdiarias = 8;
        double liquido;
        double descontoInss;
        double salarioMenoInss;
        float inss = 0.11f;

        /**
         * Calculo salario liquido e INSS *
         */
        descontoInss = salario * inss;
        liquido = (salario / horasmensais) * (diastrabalhados * horasdiarias) - descontoInss;
        
                
        
        /**
         * Impresão das variaveis*
         */
        System.out.println("Salario Mensal... " + salario);
        System.out.println("Dias trabalhados... " + diastrabalhados);
        System.out.println("Horas mensais... " + horasmensais);
        System.out.println("Desconto do INSS... " + descontoInss);
        System.out.println("Salario liquido... " + liquido);
    }

}
