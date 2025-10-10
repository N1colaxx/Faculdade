package view;


import javax.swing.*;
import java.awt.*;

public class CardPanelView extends JPanel {
    public CardPanelView(LayoutManager lm) { super(lm); }

    @Override
    public Dimension getPreferredSize() {
        // devolve o preferredSize do componente visível
        for (Component c : getComponents()) {
            if (c.isVisible()) {
                return c.getPreferredSize();
            }
        }
        // fallback
        return super.getPreferredSize();
    }
}
