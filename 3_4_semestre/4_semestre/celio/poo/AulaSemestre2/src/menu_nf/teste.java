/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu_nf;

import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;

public class teste {
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemplo Campo de Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        JFormattedTextField campoData = new JFormattedTextField(format);
        campoData.setColumns(10);

        frame.add(campoData);
        frame.setVisible(true);
    }
}
