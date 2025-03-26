package revisaoNP1;

import java.util.Scanner;

// Interface para definir comportamentos comuns entre produtos
public interface Vendavel {
    Scanner scanner = new Scanner(System.in);

    // Método que será implementado por classes concretas
    public void vender(); 
}