package ExerciciosLogica;
import java.util.Scanner;

public class Ex3 {
    // When using static method, static variables must be declared static
    private static String nome;
    private static String emon;
    
    // Static method to input name
    public static void entrar(Scanner scanner) {
        System.out.print("Digite seu nome: ");
        nome = scanner.nextLine();
    }
    
    
    public static void imprimir(StringBuilder sb1, StringBuilder sb2){
        String res;
        System.out.println("Nome Origiinal = " + sb1);
        System.out.println("Nome aucontrario = " + sb2);
        if(sb1 == sb2){
            res = "SIM e um Palindromo!!";
        }else{
            res = "NAO e um Palimdromo";
        }
        System.out.println("Essas palavras: " + res);

    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Call the static method directly
        entrar(scanner);
        
        
        StringBuilder sbOriginal = new StringBuilder(nome);
        
        StringBuilder  sbReversa = new StringBuilder(nome);
        sbReversa.reverse();
  
        // Call method to print the name
        imprimir(sbOriginal, sbReversa);
        
   }
}