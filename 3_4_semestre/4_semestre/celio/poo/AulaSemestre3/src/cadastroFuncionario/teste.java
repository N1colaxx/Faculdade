//
//package cadastroFuncionario;
//
//
//import javax.swing.*;
//import java.text.SimpleDateFormat;
//
//import javax.swing.*;
//import javax.swing.text.MaskFormatter;
//
//public class teste extends JPanel {
//    private JFormattedTextField txtDataNascimento;
//
//    public teste() {
//        try {
//            MaskFormatter mask = new MaskFormatter("##/##/####");
//            mask.setPlaceholderCharacter('_');
//            txtDataNascimento = new JFormattedTextField(mask);
//            add(new JLabel("Data de Nascimento:"));
//            add(txtDataNascimento);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getDataNascimento() {
//        return txtDataNascimento.getText();
//    }
//}
//
//



// Testando  validações 

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class teste {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Validação de Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria o modelo de datas
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner spnData = new JSpinner(model);

        // Define o editor no formato dd/MM/yyyy
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnData, "dd/MM/yyyy");
        spnData.setEditor(editor);

        JButton btnValidar = new JButton("Validar");
        btnValidar.addActionListener(e -> {
            String texto = ((JSpinner.DateEditor) spnData.getEditor()).getTextField().getText();

            if (isDataValida(texto, "dd/MM/yyyy")) {
                JOptionPane.showMessageDialog(frame, "Data válida: " + texto);
            } else {
                JOptionPane.showMessageDialog(frame, "Data inválida ou formato errado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(spnData, "North");
        frame.add(btnValidar, "South");
        frame.pack();
        frame.setVisible(true);
    }

    // Método de validação
    public static boolean isDataValida(String data, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false); // não aceita datas "malucas" tipo 32/02/2025
        try {
            Date d = sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
