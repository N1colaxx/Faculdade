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
// o RA, nome, curso, disciplina e nº de faltas no 1º bimestre, 2º bimestre, 3º bimestre e 4º bimestre. 
public class ex2 {
    // Atributos da classe 
    int RA;
    String nome;
    String curso;
    String disciplina;
    int faltas1b, faltas2b, faltas3b, faltas4b;

    public void entradaDados() {
     try   (Scanner scanner = new Scanner(System.in)) {

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
        faltas1b = validaNota(scanner, "Digite as faltas do 1b: ");
        faltas2b = validaNota(scanner, "Digite as faltas do 2b: ");
        faltas3b = validaNota(scanner, "Digite as faltas do 3b: ");
        faltas4b = validaNota(scanner, "Digite as faltas do 4b: ");
        // Fechar o scanner
        
     }
    }
    
    private int validaNota(Scanner scanner, String mensagem) {
        int faltas;
        
        do {
            System.out.println(mensagem);
            faltas = scanner.nextInt();
            if (faltas < 0 || faltas > 8) {
                System.out.println("VALOR incerido INVALIDO, redigite!");
            }
        } while (faltas < 0 || faltas > 8);
        return faltas;
    }
    

    
    public void imprimeDados() {
        int soma = faltas1b + faltas2b + faltas3b + faltas4b;
        String frequencia;

        if (soma == 0) {
            frequencia = "Máxima";
        } else if (soma <= 4) {
            frequencia = "Boa";
        } else if (soma <= 8) {
            frequencia = "Regular";
        } else if (soma <= 12) {
            frequencia = "Média";
        } else if (soma <= 16) {
            frequencia = "Ruim";
        } else if (soma <= 20) {
            frequencia = "Péssima";
        } else {
            frequencia = "sem nenhuma presença";
        }

        System.out.println("Aluno com frequência " + frequencia + ", N° faltas = " + soma);
    }

    
    
    public static void main(String[] args) {
        // entidade 
        ex2 aluno = new ex2();
        // entrada dos dados
        aluno.entradaDados();
        // impreção dos dados 
        aluno.imprimeDados();
    }
}
