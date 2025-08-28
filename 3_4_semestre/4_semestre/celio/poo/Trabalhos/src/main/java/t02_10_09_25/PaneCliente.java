package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneCliente extends JPanel {
    public PaneCliente() {
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Cadastro de Cliente", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
