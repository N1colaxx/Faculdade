package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneVendas extends JPanel {
    
    public PaneVendas(){
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Vendas", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
