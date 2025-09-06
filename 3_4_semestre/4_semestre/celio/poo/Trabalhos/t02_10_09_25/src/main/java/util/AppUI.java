package util;

import javax.swing.*;
import java.awt.*;

public final class AppUI {

    // === Constantes globais ===
    public static final Dimension SIZE_PADRAO  = new Dimension(800, 800);
  

    private AppUI() {} // utility class

    // === Look & Feel (opcional) ===
    public static void setNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) { }
    }



    // === Tamanhos ===
    public static void applyDefaultSize(JComponent c) {
        c.setPreferredSize(SIZE_PADRAO);
    }
    
    public static void applySize(JComponent c, Dimension d) {
        c.setPreferredSize(d);
    }
    

    // Usa o preferredSize do card visível (quando você usa CardPanelView)
    public static void packToVisibleCard(JFrame frame, JPanel cardPanel) {
        cardPanel.revalidate();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    // Add campo em Movimentos
    public static void addCampo(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        // LABEL
        gbc.gridx = 0;
        gbc.weightx = 0;
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(110, 25));
        panel.add(label, gbc);

        // CAMPO
        gbc.gridx = 1;
        gbc.weightx = 0;                     // não recebe espaço extra
        field.setPreferredSize(new Dimension(300, 25));
        panel.add(field, gbc);

        gbc.gridy++;
    }
}
