package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class UtilsUI {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Para DATAS
     */
    
    
    public static LocalDate parseDate(String s) {
        try {
            if (s == null || s.isEmpty()) return null;
            return LocalDate.parse(s, DF);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data inválida: '" + s + "'. Esperado (yyyy-MM-dd) = ano-mes-dia");
            System.out.println("Data inválida: '" + s + "'. Esperado yyyy-MM-dd.");
            return null;
        }
    }

    public static String fmtDate(LocalDate d) {
        return d == null ? "" : d.format(DF);
    }


    /**
     * Numeros 
     */

    public static double parseDouble(String old_val_s) {
        System.out.println("\n[ClienteView] parseDouble, valor atual de Limite credito = " + old_val_s);

        try {
            if (old_val_s == null || old_val_s.trim().isEmpty()) return 0.0;

            // Remove espaços e pontos (milhar), troca vírgula por ponto (decimal)
            String val_s = old_val_s.trim().replace(".", "").replace(",", ".");

            if (val_s.isEmpty()) return 0.0;

            return Double.parseDouble(val_s);
        } catch (Exception e) {
            System.out.println("Falha ao converter double: '" + old_val_s + "': " + e.getMessage());
            return 0.0;
        }
}

    
    /**
     * Contas
     */
    
    public static double recalc(double qdte, double preco, double desconto) { 
        double total = (qdte * preco) - desconto;
        return total;
    }

}
