package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneUsuarios extends JPanel {
    public PaneUsuarios() {
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Cadastro de Usuário", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
