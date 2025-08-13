package aula02;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exemplo2 extends JFrame {

// 1° declarando os componentes 
    private JLabel lblTitulo,lblRA, lblNome;
    private JTextField edtRA, edtNome;
    private JButton btnEnviar, btnLimpar;
    private EventoMouse evento;

    
    
    public Exemplo2(){
        setTitle("Exemplo de Componentes do Swing + eventos de mouse");
        
        // carregando os metodos q criei
        CriandoComponentes();
        AlinhamentoComponentes();
        AddComponente();

        // add um evento ao btn com uma class externa e aplicando direto no obj
        
        // class externa
        evento = new EventoMouse(btnEnviar);
        btnEnviar.addActionListener(evento);
        
        // aplicando direto no obj
        btnLimpar.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Clicou no btn Limpar", "Tratamento de Evento",
                    JOptionPane.WARNING_MESSAGE);
        });
    };
    
// 2° atribuindo valor as componentes 
    private void CriandoComponentes(){
        lblTitulo = new JLabel("Utilizando componentes do Swing em Java");
        
        lblNome = new JLabel("NOME");
        edtNome = new JTextField(20); // qtd max de caracteres 

        lblRA   = new JLabel("R.A");        
        edtRA = new JTextField(5); // qtd max de caracteres 
        
        btnEnviar = new JButton("Enviar");
        btnLimpar = new JButton("Limpar Campos");
    }
    
    
// 3° posicionando os componentes 
    private void AlinhamentoComponentes(){
        // SetBounds ( X, Y, Lagura, Altura)
        lblTitulo.setBounds(150, 20, 300, 30);
        
        lblNome.setBounds(20, 60, 50, 20);
        edtNome.setBounds(85, 60, 400, 20);

        lblRA.setBounds(20, 90, 50, 20);
        edtRA.setBounds(85, 90, 400, 20);

        btnEnviar.setBounds(100, 150, 100, 20);
        btnLimpar.setBounds(220, 150, 150, 20);
    };
    
//  4° add componente ao frema
    
    private void AddComponente(){
        add (lblTitulo);
        add (lblNome);
        add (lblRA);
        add (edtNome);
        add (edtRA);
        add (btnEnviar);
        add (btnLimpar);
    }
    
    
    public static void main(String[] args) {
        Exemplo2 obj1 = new Exemplo2();
        
        // cfg layout
        obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj1.setSize(new Dimension(600, 300));
        obj1.setLayout(null);
        obj1.setVisible(true);

    }

}