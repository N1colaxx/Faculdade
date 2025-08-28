package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class PaneFornecedor extends JPanel {
    public PaneFornecedor() {
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Cadastro de Fornecedor", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
    