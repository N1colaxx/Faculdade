/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.Aluno to edit this template
 */
package aula02;

/**
 *
 * @author Nicolas
 */

import java.util.Scanner;

public class Aluno {
    // Atributos da classe
    
long alu_cpf;
int alu_codigo;
String alu_nome;
String alu_endereco;
int alu_numero;
String alu_complemento;
String alu_bairro;
String alu_cidade;
String alu_estado;
String alu_telefone;
String alu_celular;
String alu_email;
float alu_salario;
String alu_sexo;

    // Método para entrada de dados
    public void entradaAluno() {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler os dados do teclado

        System.out.print("Nome: ");
        alu_nome = scanner.nextLine(); // Lê o nome do aluno

        System.out.print("Código: ");
        alu_codigo = scanner.nextInt(); // Lê o código do aluno
        scanner.nextLine(); // Consome a nova linha, o \n q fica no buffer

        System.out.print("CPF: ");
        alu_cpf = scanner.nextLong(); // Lê o CPF do aluno
        scanner.nextLine(); 

        System.out.print("Endereço: ");
        alu_endereco = scanner.nextLine(); // Lê o endereço do aluno

        System.out.print("Número: ");
        alu_numero = scanner.nextInt(); // Lê o número do endereço
        scanner.nextLine(); 

        System.out.print("Complemento: ");
        alu_complemento = scanner.nextLine(); // Lê o complemento do endereço

        System.out.print("Bairro: ");
        alu_bairro = scanner.nextLine(); // Lê o bairro

        System.out.print("Cidade: ");
        alu_cidade = scanner.nextLine(); // Lê a cidade

        System.out.print("Estado: ");
        alu_estado = scanner.nextLine(); // Lê o estado

        System.out.print("Telefone: ");
        alu_telefone = scanner.nextLine(); // Lê o telefone

        System.out.print("Celular: ");
        alu_celular = scanner.nextLine(); // Lê o celular

        System.out.print("E-mail: ");
        alu_email = scanner.nextLine(); // Lê o e-mail

        System.out.print("Salário: ");
        alu_salario = scanner.nextFloat(); // Lê o salário do aluno
        scanner.nextLine();
            
        System.out.print("Sexo masculino ou feminino: ");
        alu_sexo = scanner.nextLine(); // 
        
    }

    // Método para imprimir os dados
    public void imprimeAluno() {
        System.out.println("Nome: " + alu_nome); // Imprime o nome do aluno
        System.out.println("Código: " + alu_codigo); // Imprime o código do aluno
        System.out.println("CPF: " + alu_cpf); // Imprime o CPF
        System.out.println("Endereço: " + alu_endereco); // Imprime o endereço
        System.out.println("Número: " + alu_numero); // Imprime o número do endereço
        System.out.println("Complemento: " + alu_complemento); // Imprime o complemento
        System.out.println("Bairro: " + alu_bairro); // Imprime o bairro
        System.out.println("Cidade: " + alu_cidade); // Imprime a cidade
        System.out.println("Estado: " + alu_estado); // Imprime o estado
        System.out.println("Telefone: " + alu_telefone); // Imprime o telefone
        System.out.println("Celular: " + alu_celular); // Imprime o celular
        System.out.println("E-mail: " + alu_email); // Imprime o e-mail
        System.out.println("Salário: " + alu_salario); // Imprime o salário
        System.out.println("Sexo: " + alu_sexo ); // Imprime o sexo, usando um operador ternário para exibir o texto correspondente
    }
    
    public static void main(String[] args) {
        // Instancia um objeto da classe Aluno
        Aluno aluno = new Aluno();

        // Chama o método para entrada de dados
        aluno.entradaAluno();

        // Chama o método para imprimir os dados do aluno
        aluno.imprimeAluno();
    }
}
