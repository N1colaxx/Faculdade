/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula04;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */


public class ex1 {
//  Atribitos da classe 
    int RA;
    String nome;
    String curso;
    String disciplina;
    float nota1, nota2, nota3, nota4;

    public void entradaDados() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o RA do aluno: ");
            RA = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Digite o nome do aluno: ");
            nome = scanner.nextLine();
            System.out.print("Digite o curso do aluno: ");
            curso = scanner.nextLine();
            System.out.print("Digite a disciplina do aluno: ");
            disciplina = scanner.nextLine();
            
            // Entrada e validação das notas
            nota1 = validaNota(scanner, "Digite a primeira nota: ");
            nota2 = validaNota(scanner, "Digite a segunda nota: ");
            nota3 = validaNota(scanner, "Digite a terceira nota: ");
            nota4 = validaNota(scanner, "Digite a quarta nota: ");
            // Fechar o scanner
        }
    }

    // Método para validar notas
    private float validaNota(Scanner scanner, String mensagem) {
        float nota;
        do {
            System.out.print(mensagem); // msg de Primeira recebe o texto da Entrada e Validação, depois valida no IF se digitou o valor errado recebe o texto do IF
            nota = scanner.nextFloat();
            if (nota < 0 || nota > 10) { 
                    System.out.println("Nota Inválida, redigite!");
            }
        } while (nota < 0 || nota > 10); // isso faz o LOOP, so vai sair quando o user digita uma nota q atenta a condição 
        return nota;
    }

    public void imprimeDados() {
        float media = (nota1 + nota2 + nota3 + nota4) / 4;

        System.out.println("\n--- Dados do Aluno ---");
        System.out.println("RA: " + RA);
        System.out.println("Nome: " + nome);
        System.out.println("Curso: " + curso);
        System.out.println("Disciplina: " + disciplina);
        System.out.println("Notas: " + nota1 + ", " + nota2 + ", " + nota3 + ", " + nota4);
        System.out.printf("Média Final: %.2f\n", media);
    }

    
    public static void main(String[] args) {
        ex1 aluno = new ex1();
        
        aluno.entradaDados();
        aluno.imprimeDados();
    }
}
