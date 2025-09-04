package util;

import javax.swing.*;
import java.awt.*;

public final class AppUI {

    // === Constantes globais ===
    public static final Dimension SIZE_PADRAO     = new Dimension(800, 800);

    public static final Color COLOR_BG      = new Color(245, 250, 255);
  

    private AppUI() {} // utility class

    // === Look & Feel (opcional) ===
    public static void setNimbusLaFIfAvailable() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) { }
    }


    public static JPanel columnPanel() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return p;
    }

    // === Tamanhos ===
    public static void applyDefaultSize(JComponent c) {
        c.setPreferredSize(SIZE_PADRAO);
    }

    // Usa o preferredSize do card visível (quando você usa CardPanelView)
    public static void packToVisibleCard(JFrame frame, JPanel cardPanel) {
        cardPanel.revalidate();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
