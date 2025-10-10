package relatorios;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AbrePDF {
    
    public static boolean abrirPDF(String caminhoPDF) {
        try {
            File arquivo = new File(caminhoPDF);
            
            // Verifica se arquivo existe
            if (!arquivo.exists()) {
                JOptionPane.showMessageDialog(null,
                    "Arquivo não encontrado: " + caminhoPDF,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
           
            // Método universal moderno
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(arquivo);
                System.out.println("PDF aberto com sucesso!");
                return true;
            } else {
                // Método alternativo para sistemas muito antigos
                return abrirNoLinuxOuWindows(caminhoPDF);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao abrir PDF:\n" + e.getMessage() +
                "\n\nVerifique se tem um leitor de PDF instalado.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private static boolean abrirNoLinuxOuWindows(String caminhoPDF) {
        try {
            String sistema = System.getProperty("os.name").toLowerCase();
            
            if (sistema.contains("win")) {
                // Windows
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "", caminhoPDF});
                System.out.println("Aberto no Windows");
            } else {
                // Linux/Unix/Mac
                Runtime.getRuntime().exec(new String[]{"xdg-open", caminhoPDF});
                System.out.println("Aberto no Linux/Mac");
            }
            return true;
            
        } catch (Exception e) {
            System.err.println("Erro no método alternativo: " + e.getMessage());
            return false;
        }
    }
}