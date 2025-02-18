/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula02;

/**
 *
 * @author nicol
 */

import java.util.Scanner;

public class Funcionario {
//  Atributos da casse Funcionario 

int fun_codigo;
String fun_nome;
String fun_nome_pai;
String fun_nome_mae;
String fun_telefone;

double fun_salario_base;
double fun_salario_familia;
double fun_liquido;
int fun_quantidade_dependentes;
int fun_vale_transporte;
int fun_valor_assis_med; // valor da assistencia medica


double fun_valor_inss; //  % do INSS 
double fun_valor_irrf; // % do IRRF




    

    public static double calcularINSS(double fun_salario_base) {
        double val_inss;

        if (fun_salario_base <= 1412.00) {
            val_inss = fun_salario_base * 0.075;
        } else if (fun_salario_base <= 2666.68) {
            val_inss = (fun_salario_base * 0.09) - 21.18;
        } else if (fun_salario_base <= 4000.03) {
            val_inss = (fun_salario_base * 0.12) - 101.18;
        } else if (fun_salario_base <= 7786.02) {
            val_inss = (fun_salario_base * 0.14) - 181.18;
        } else {
            val_inss = 908.86; // Teto máximo do INSS
        }

        return val_inss;
    }
    
    public static double calcularIRRF(double fun_salario_base) {
        double val_irrf;

        if (fun_salario_base <= 2259.20) {
            val_irrf = 0; // Isento
        } else if (fun_salario_base <= 2826.65) {
            val_irrf = (fun_salario_base * 0.075) - 169.44;
        } else if (fun_salario_base <= 3751.05) {
            val_irrf = (fun_salario_base * 0.15) - 381.44;
        } else if (fun_salario_base <= 4664.68) {
            val_irrf = (fun_salario_base * 0.225) - 662.77;
        } else {
            val_irrf = (fun_salario_base * 0.275) - 896.00;
        }

        return val_irrf;
    }
     
    public void entradaDadosF() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Codigo: ");
        fun_codigo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Nome do Funcionario: ");
        fun_nome = scanner.nextLine();
        
        System.out.println("Nome do Pai: ");
        fun_nome_pai = scanner.nextLine();
        
        System.out.println("Nome da Mãe: ");
        fun_nome_mae = scanner.nextLine();
        
        System.out.println("Valor do salario base: ");
        fun_salario_base = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Valor do salario da familia: ");
        fun_salario_familia = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Quantidade de dependentes: ");
        fun_quantidade_dependentes = scanner.nextInt();
        
        System.out.println("Valor do vale transporte:: ");
        fun_vale_transporte = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Valor da assistencia medica: ");
        fun_valor_assis_med = scanner.nextInt();
        
        
//      chamando as fun e calculando 
        fun_valor_inss = calcularINSS(fun_salario_base);
        fun_valor_irrf = calcularIRRF(fun_salario_base - fun_valor_inss);

//      calculando o Salario liquido
        fun_liquido = (fun_salario_base + fun_salario_familia) - (fun_valor_inss + fun_valor_irrf + fun_vale_transporte + fun_valor_assis_med);
                     
    }

    public void printarDados() {
    System.out.println("\n========== DADOS DO FUNCIONÁRIO ==========");
    System.out.println("Código: " + fun_codigo);
    System.out.println("Nome: " + fun_nome);
    System.out.println("Salário Base: R$ " + fun_salario_base);
    System.out.println("Salário Líquido: R$ " + fun_liquido);
}

 
    public static void main(String[] args) {
        // Instanciando o obj funcionario 
        Funcionario funcionario = new Funcionario();
        
        // Chamando a funcao de entrada dos Dados do Funcionario 
        funcionario.entradaDadosF();
        
        // Chama a funcao para inprimir os resultado.
        funcionario.printarDados();
       
    }
    
}
