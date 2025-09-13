
package Testes;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class testeJComboBox {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("JComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create a JComboBox with items
        String[] items = {"Apple", "Banana", "Cherry", "Date"};
        JComboBox<String> comboBox = new JComboBox<>(items);

        // Add an ActionListener to handle selection events
        comboBox.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            System.out.println("Selected: " + selectedItem);
        });

        // Add the JComboBox to the frame
        frame.add(comboBox);

        // Set frame visibility
        frame.setLayout(null);
        comboBox.setBounds(50, 50, 150, 30); // Set position and size
        frame.setVisible(true);
    }
}

