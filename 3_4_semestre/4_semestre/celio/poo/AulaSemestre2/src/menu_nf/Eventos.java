package menu_nf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos extends JFrame implements ActionListener {
    private JButton btn_gravar;
    
    public Eventos(JButton btn_gravar){
        this.btn_gravar = btn_gravar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btn_gravar){
          System.out.println("Clique detectado em Evento Validacao ");
          JOptionPane.showMessageDialog(
                  null,
                  "Clicou no BTN Gravar",
                  "Aviso",
                  JOptionPane.WARNING_MESSAGE
          );
          
          
      }  
    }
    
}
