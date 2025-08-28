package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneCompras extends JPanel{
    
    public PaneCompras(){
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Compras", SwingConstants.CENTER), BorderLayout.CENTER);
    }
    
}
