package lista_exercicios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

public class EleitoresVotos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lê os dados do município e votos
        System.out.print("Digite o nome do município: ");
        String municipio = scanner.nextLine();
        System.out.print("Digite o total de eleitores: ");
        int totalEleitores = scanner.nextInt();
        System.out.print("Digite o número de votos em branco: ");
        int votosBrancos = scanner.nextInt();
        System.out.print("Digite o número de votos nulos: ");
        int votosNulos = scanner.nextInt();
        System.out.print("Digite o número de votos válidos: ");
        int votosValidos = scanner.nextInt();
        
        // Calcula as porcentagens
        double percBrancos = (votosBrancos / (double) totalEleitores) * 100;
        double percNulos = (votosNulos / (double) totalEleitores) * 100;
        double percValidos = (votosValidos / (double) totalEleitores) * 100;
        
        // Exibe os resultados
        System.out.println("Município: " + municipio);
        System.out.println("Totais:");
        System.out.println("Eleitores: " + totalEleitores + " (" + 100 + "%)");
        System.out.println("Votos em branco: " + votosBrancos + " (" + percBrancos + "%)");
        System.out.println("Votos nulos: " + votosNulos + " (" + percNulos + "%)");
        System.out.println("Votos válidos: " + votosValidos + " (" + percValidos + "%)");
    }
}

