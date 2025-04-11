/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula06;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class AlimentandoCaixa {
    private Scanner scanner;
    
    private double alimentando_caixa;
    private double novo_saldo_caixa = 0;
    private double[] novas_cedulas_caixa;
        
    
    
    public AlimentandoCaixa(){
        scanner = new Scanner(System.in);
        
    }
    
    
    public double entrada(double saldo_caixa, double[] cedula_caixa) {
        System.out.println(" Carregado com Sucesso!!!\n");
       
        entradaValor(cedula_caixa);
 
        return novo_saldo_caixa;
    }
    
    private void entradaValor(double[] cedula_caixa){
        boolean sair = false;
        int opc;
        do{
            System.out.println("""
            \nEscolha a forma como quer alimentar o caixa: 
                [1] valor digitado
                [2] cedula
                [9] voltar ao SUB-MENU""");

            System.out.println("\nDigite sua excolha: ");
            if(scanner.hasNextInt()){
                opc = scanner.nextInt();
                switch (opc) {
                    case 1 -> {
                        System.out.println("\nVC escolheu OPC [1] valor digitado ");
                        valor_digitado(cedula_caixa);
                    }
                    case 2 -> {
                        System.out.println("\nVC escolheu OPC [2] cedula");
                        System.out.println("Em modo de desenvolvimento...");
                    }
                    case 9 -> {
                        System.out.println("\nVC escolheu OPC [9] voltar ao SUB-MENU");
                        sair = true;
                    }

                    default -> {
                        System.out.println("\n ERRO: OPC INVALIDA... \n");
                    }
                }
            }else{
                System.out.println("\n ERRO: Digite um numero inteiro... \n");
                scanner.nextLine();
            }
        }while(!sair);
    }
    
    private void valor_digitado(double[] cedula_caixa){
        double[] tipo_cedulas_caixa = {100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0};
        
        boolean sair = false;
        do {
            System.out.print(" Digite quanto quer Alimentar o Caixa: ");
            if (scanner.hasNextDouble()) {
                alimentando_caixa = scanner.nextDouble();

                if (alimentando_caixa <= 0) {
                    System.out.println("\n ERRO: Numeros abaixo ou igual a 0(zero) NAO SAO PERMITIDOS!!! Digite novamente...");
                    scanner.nextLine();
                }else{
                    novo_saldo_caixa += alimentando_caixa;
                    System.out.print("\n SUCESSO: Caixa alimentado com: R$" + alimentando_caixa + "\n \n");
                    sair = true;
                }
            }else{
                System.out.println("\n ERRO: Digite um Numero!!! digite novamente...");
                scanner.nextLine();
            }

        } while (!sair);
        
    }

}
