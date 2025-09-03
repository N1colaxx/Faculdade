package t01_03_09_25;

import java.awt.Color;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("\n[Main] Iniciando Frame...");
                
                System.out.println("[Main] Criando e exibindo TelaFrame...");
                new TelaFrame().setVisible(true);

                System.out.println("[Main] TelaFrame exibida com sucesso!");
            } catch (Exception ex) {
                // Qualquer erro na criação/exibição do frame cai aqui
                System.out.println("\n[Main] ERRO! " + ex);
                ex.printStackTrace(); // útil para depurar a causa real
            }
        });
    }
}
