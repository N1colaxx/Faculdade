package aula02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane;

public class EventoMouse extends JFrame implements ActionListener{
    private JButton btnEnviar;

    public EventoMouse(JButton btnEnviar){
        this.btnEnviar = btnEnviar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnEnviar){
            JOptionPane.showMessageDialog(null, "Clicou no btn Enviar", "Tratamento de Evento",
            JOptionPane.WARNING_MESSAGE);
        }
    }
}

