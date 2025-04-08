/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula06;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Nicolas
 */
public class CaixaEletronicoSaldo {
    private int opc_menu;
    private final int[] cedulas_caixa = {100, 50, 20, 10, 5, 2, 1};
    private double saldo_caixa; // quanto o caixa tem guadado
    
    private final Scanner scanner;
    
//    Armazena a quantidade de cada cedula usada 
    
    public CaixaEletronicoSaldo(){
        scanner = new Scanner(System.in);
    }
    
    
    public void menu(){
        System.out.println("\n  Carregado com Sucesso!!...\n");
        do{
            System.out.println("\n \n");
            System.out.println("|===============================================|");
            System.out.println("|          Sub-Menu Caixa Eletronico            |");
            System.out.println("|===============================================|");
            System.out.println("""
            |   Escolha uma Opcao:                          |
            |   [1] Alimentar Caixa                         |       
            |   [2] Mostrar Saldo e Cedulas                 |
            |   [3] Saque                                   |
            |   [9] Voltar ao Menu principal                |                           
            |===============================================|""");
            System.out.print("| Digite sua Opcao: ");
            if(scanner.hasNextInt()){
                opc_menu = scanner.nextInt();
                scanner.nextLine();

                switch (opc_menu) {
                    case 1 ->{
                        System.out.println("\n Vc escolheu a Opcao [1] Alimentar Caixa");
                        System.out.println(" Carregando...");
                        ali_caixa();
                    }
                    case 2 ->{
                        System.out.println("\n Vc escolheu a Opcao [2] Mostrar Saldo e Cedulas");
                        System.out.println(" Carregando...");
                        mostra_saldo();
                    }
                    case 3 ->{
                        System.out.println("\n Vc escolheu a Opcao [3] Saque");
                        System.out.println(" Carregando...");
                        saque();
                    }
                    case 9 ->{
                        System.out.println("\n Vc escolheu a Opcao [9] Voltar ao Menu principal");
                        System.out.println(" Voltando... ");
                        opc_menu = 9;
                    }
                    default ->{
                        System.out.println("\n ERRO: Digite um Numero!!! Digite novamente...");   
                        scanner.nextLine();
                    }
                }
            }
        }while(opc_menu != 9);
    }
    
    
    private void ali_caixa(){
        System.out.println(" Carregado com Sucesso!!!\n");
        boolean sair = false;
        do{
            System.out.print(" Digite quanto quer Alimentar o Caixa: ");
            if(scanner.hasNextDouble()){
                double alimentandoCaixa = scanner.nextDouble();
                scanner.nextLine();
                if(alimentandoCaixa > 0){
                    saldo_caixa += alimentandoCaixa;
                    System.out.print("\n SUCESSO: Caixa alimentado com: R$" + alimentandoCaixa + "\n \n");
                    sair = true;
                }else{
                    System.out.println("\n ERRO: Numeros abaixo de 0(zero) NAO SAO PERMITIDOS!!! Digite novamente...");
                    scanner.nextLine();
                }
            }else{
                System.out.println("\n ERRO: Digite um Numero!!! digite novamente...");
                scanner.nextLine();
            }
        }while(!sair);
    }
    
    
    private void mostra_saldo(){
        System.out.println(" Carregado com Sucesso!!! \n");
        System.out.println("|===============================================================|");
        System.out.println("|                   Saldo do Caixa                              |");
        System.out.println("|---------------------------------------------------------------|");
        
//      Monta a string com as cédulas disponíveis separadas por vírgula
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i < cedulas_caixa.length; i++){
            sb.append("R$").append(cedulas_caixa[i]);
//          O -1 é usado para evitar colocar uma vírgula após o último elemento do array.
            if(i < cedulas_caixa.length - 1){
                sb.append(", ");
            }
        }
        
//      Aqui, definimos uma largura total para a linha
        String cedulasLinha = String.format("| Cedulas Disponiveis:   %-35s |", sb.toString());
        System.out.println(cedulasLinha);
        String saldoLinha = String.format("| Saldo Disponivel: R$%-24.2f                  |", saldo_caixa);
        System.out.println(saldoLinha);
        System.out.println("|===============================================================|");
        System.out.println("\n \n ");
    }
    
    
    private void saque(){
        System.out.println(" Carregado com Sucesso!!! \n");
        boolean sair = false;      
        if(saldo_caixa > 0){     
            do{
                System.out.println("|================================================|");
                System.out.println("|           Saque Caixa Eletronico               |");
                System.out.println("|================================================|");
                System.out.print("| Digite quanto quer sacar: ");
                if(scanner.hasNextDouble()){
                    double sacando = scanner.nextDouble();
                    scanner.nextLine();
                    if((sacando < saldo_caixa) && (sacando > 0)){
                        System.out.println("| Saque permitido, vc sacou R$" + sacando);
                        saldo_caixa -= sacando;
                        sair = true;
                    }else{
                        System.out.println("\n ERRO: Saque NEGADO! valor acimado permitido ou menor que 0(zero), limite permitido ate: R$" + saldo_caixa + "\n");
                    }
                }else{
                    System.out.println("\n ERRO: Digite um Numero!! Digite novamente...\n");
                    scanner.nextLine();
                }
            }while(!sair);
        }else{
            System.out.println("\n ERRO: Caixa sem saldo. Alimente o caixa ou volte outro dia.");
            scanner.nextLine();
        }
    }

}