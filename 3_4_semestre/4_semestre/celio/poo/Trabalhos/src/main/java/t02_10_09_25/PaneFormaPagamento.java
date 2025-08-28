package t02_10_09_25;

import java.awt.*;
import javax.swing.*;

public class PaneFormaPagamento extends JPanel {

    public PaneFormaPagamento(){
        setLayout(new BorderLayout());
        add(new JLabel("Tela de Forma de Pagamento", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
