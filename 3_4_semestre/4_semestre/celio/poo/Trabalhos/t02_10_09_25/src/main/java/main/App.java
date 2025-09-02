package main;

import view.AppView;
import javax.swing.*;

public class App {
    
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppView().setVisible(true));
    }

} 