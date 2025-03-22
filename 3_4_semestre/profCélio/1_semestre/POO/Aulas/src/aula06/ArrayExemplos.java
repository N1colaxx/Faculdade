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
public class ArrayExemplos {
    private CaixaEletronicoFixo caixaFixo;
    private CaixaEletronicoSaldo caixaSaldo;
    private CaixaEletronicoEntrada caixaEntrada;
    
    private final Scanner scanner;
    private int opc_menu;
    
    public ArrayExemplos(){
        caixaFixo = new CaixaEletronicoFixo();
        caixaSaldo = new CaixaEletronicoSaldo();
        caixaEntrada = new CaixaEletronicoEntrada();
        
        scanner = new Scanner(System.in);
    }
    

    public void menu(){
        do{
            System.out.println("\n \n \n ");
            System.out.println("|===================================================|");
            System.out.println("|                Caixa Eletronico                   |");
            System.out.println("|===================================================|");
            System.out.println("""                                              
            |   Escolha uma Opcao:                              |
            |   [1] Caixa Eletrônico com Valor Fixo             |
            |   [2] Caixa Eletrônico com Entrada do Valor       |
            |   [3} Caixa Eletrônico com Saldo                  |
            |   [9] Sair                                        |
            |===================================================|""");
            System.out.print("Digite sua Opcao: ");
            if(scanner.hasNextInt()){
                opc_menu = scanner.nextInt();
                scanner.nextLine();
                
                switch(opc_menu){
                    case 1 ->{
                        System.out.println("\n  Vc escolheu OPC [1] Caixa Eletrônico com Valor Fixo");
                        System.out.println("  Carregando...");
                        caixaFixo.entrada();                      
                    }
                    case 2 ->{
                        System.out.println("\n  Vc escolheu OPC [2] Caixa Eletrônico com Entrada do Valor");
                        System.out.println("    Carregando...");
                        caixaEntrada.entrada();
                        caixaEntrada.imprimir();
                    }
                    case 3 ->{
                        System.out.println("\n  Vc escolheu OPC [3] Caixa Eletrônico com Saldo");
                        System.out.println("    Carregando...");
                        caixaSaldo.menu();
                        caixaEntrada.imprimir();
                    }
                    case 9 ->{
                        System.out.println("\n Vc escolheu OPC [9] Sair");
                        System.out.println(" Saindo ....");
                        break;
                    }
                    default ->
                        System.out.println("\n  ERRO: Digite uma OPCAO valida!!!... \n");
                }
            }else{
                System.out.println("\n  ERRO: Digite um Numemo valido!!!... \n");
                scanner.nextLine();
            }
            
        }while(opc_menu != 9);
    }
    
    
    
    public static void main(String[] args) {
        ArrayExemplos obj = new ArrayExemplos();
        
        obj.menu();
    }
}
