
package menu_nf;


import javax.swing.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class teste extends JPanel {
    private JFormattedTextField txtDataNascimento;

    public teste() {
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            txtDataNascimento = new JFormattedTextField(mask);
            add(new JLabel("Data de Nascimento:"));
            add(txtDataNascimento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDataNascimento() {
        return txtDataNascimento.getText();
    }
}


