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
public class Ex2_mediaAluno {
 // Atributos da media

    int condigo;
    String nome;
    float nota1;
    float nota2;
    float nota3;
    float nota4;
   
    float alu_media;
    
    public void calculandoMedia() {
        
        alu_media = (nota1 + nota2 + nota3 + nota4 ) / 4;
        
        // Convertendo a média para um inteiro antes de usar switch
        int mediaArredondada = Math.round(alu_media);
        
        switch (mediaArredondada) {
            case 0 -> {
                 System.out.println("Nota Zero    – Péssimo \n");
                break;
            }
            case 1 -> {
                System.out.println("Nota 1      -Ruim");
                break;
            }
            case 2 -> {
                System.out.println("Nota 2      -Regular");
                break;
            }
            case 3 -> {
                System.out.println("Nota 3      -Bom");
                break;
            }
            case 4 -> {
                System.out.println("Nota 4      -Otimo");
                break;
            }
            case 5 -> {
                System.out.println("Nota 5      -Exelente   ");
                break;
            }
            default -> throw new AssertionError("Nota inesperada: " + alu_media);
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
        Ex2_mediaAluno  aluno = new Ex2_mediaAluno();
        
//      chamando a entrada de dados do aluno
        aluno.entradaAluno();
  
//      chamando o print das info do aluno 
        aluno.imprimeAluno();
    }
    
}