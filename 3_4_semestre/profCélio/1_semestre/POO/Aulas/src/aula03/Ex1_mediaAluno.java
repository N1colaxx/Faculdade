/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula03;
import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Ex1_mediaAluno {
 // Atributos da media

    int condigo;
    String nome;
    float nota1;
    float nota2;
    float nota3;
    float nota4;
   
    float alu_media;
    
    public void calculandoMedia() {
         
        alu_media = (nota1 + nota2 + nota3 + nota4) / 4;
        if (alu_media >= 28) {
           System.out.println("\nAluno Aprovado !!");
        }   else {
           System.out.println("\nAluno Reprovado !!");
        }
    }   
    
    public void imprimeAluno() {
        System.out.println("Nome: " + nome);
        System.out.println("Média Final: " + alu_media);
    }


    public void entradaAluno() {   
        Scanner scanner = new Scanner(System.in); // ler os dados via teclado
        
        System.out.println("Digite seu nome: ");
        nome = scanner.nextLine();

        System.out.println("Digite a nota 1: ");
        nota1 = scanner.nextFloat();

        System.out.println("Digite a nota 2: ");
        nota2 = scanner.nextFloat();

        System.out.println("Digite a nota 3: ");
        nota3 = scanner.nextFloat();

        System.out.println("Digite a nota 4: ");
        nota4 = scanner.nextFloat();

        scanner.nextLine();

        calculandoMedia(); // Limpar buffer do Scanner
        
    }

    public static void main(String[] args) {
//      Criando a entidade Aluno 
        Ex1_mediaAluno  aluno = new Ex1_mediaAluno();
        
//      chamando a entrada de dados do aluno
        aluno.entradaAluno();
  
//      chamando o print das info do aluno 
        aluno.imprimeAluno();
    }
    
}
