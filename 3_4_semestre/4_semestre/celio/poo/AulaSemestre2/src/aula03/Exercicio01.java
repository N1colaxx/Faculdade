/** ex 1:
 *    Faça uma classe em java que entre com os dados:
 * -   RA, Nome, Curso, Nota_b1, Nota_b2, Nota_b3 e Nota_b4
 * -   Calcular e exiba a media anual (com tratamento de eventos)
 * -   No curso, utilize JComboBox com 5 cursos.
 */

/**
 * Ex 2:
 * Add 4 campos para faltas:
 * falta B1, B2, B3, B4
 * -  Crie um btn p/ calcular o total de faltas
 * Regra de negocio:
 * -    Defina uma constante MAX_FALTAS = 40
 * -    Some as faltas de cada Bimestre
 * -    Se o total de faltas >= 25% do MAX_FALTA apresentar JLabel *vermelho* com texto: "reprovadp (a) por faltas"
 **/
package aula03;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class Exercicio01 extends JFrame {

//  1° Declarando os OBJs
    private JLabel lblRA, lblNome, lblCurso, lblNota_b1, lblNota_b2, lblNota_b3, lblNota_b4, 
            lblFaltaB1, lblFaltaB2, lblFaltaB3, lblFaltaB4, lblMedia, lblTotalFaltas;
    
    private JTextField edtRA, edtNome, edtNota_b1, edtNota_b2, edtNota_b3, edtNota_b4,
            edtFaltaB1, edtFaltaB2, edtFaltaB3, edtFaltaB4, edtMedia, edtTotalFaltas;
    
    private JComboBox<String> cmbCurso;
    private JButton btnCallMedia, btnCallFalta;
    
    private final int MAX_FALTAS = 40;
    private int total;
    private double media;
    
    
            
            
    public Exercicio01() {
        setTitle("Execicio_01");
        
        CriarObj();
        PosicionarObj();
        AddObj();
        InicializarFrame();
    };

    // valida o que foi digitado nos JTextField das Notas
    KeyAdapter ValidarNota = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e){
            char c = e.getKeyChar();
            JTextField campo = (JTextField) e.getSource();
            Double valor = Double.valueOf(campo.getText());
            
            if(!Character.isDigit(c) && c !='.'){
                e.consume(); //ignota tecla digitada
            }
            
            if(valor >= 0 || valor <= 10){
                campo.requestFocus();
            }
            
            if(campo.getText().length() >= 5){
                e.consume();
            }
            
        }
    };
   

    
//  2° Atribuir valor ao OBJ
    public void CriarObj() {
        lblRA = new JLabel("R.A :");
        edtRA = new JTextField(5);

        lblNome = new JLabel("Nome :");
        edtNome = new JTextField(20);
        
        lblCurso = new JLabel("Curso :");
        cmbCurso = new JComboBox<>(new String[] {"TI", "CC", "Fisica"});
        
        // Notas 
        lblNota_b1 = new JLabel("Nota_b1 :");
        edtNota_b1 = new JTextField(5);
        edtNota_b1.addKeyListener(ValidarNota);
        
        lblNota_b2 = new JLabel("Nota_b2 :");
        edtNota_b2 = new JTextField(5);
        edtNota_b2.addKeyListener(ValidarNota);
        
        lblNota_b3 = new JLabel("Nota_b3 :");
        edtNota_b3 = new JTextField(5);
        edtNota_b3.addKeyListener(ValidarNota);

        
        lblNota_b4 = new JLabel("Nota_b4 :");
        edtNota_b4 = new JTextField(5);
        edtNota_b4.addKeyListener(ValidarNota);
       
        
        lblMedia = new JLabel("Media Anual:");
        edtMedia = new JTextField();
        edtMedia.setEditable(false); // impede edição manual
      
        
        // Faltas
        lblFaltaB1 = new JLabel("Falta_b1 :");
        edtFaltaB1 = new JTextField(5);
        
        lblFaltaB2 = new JLabel("Falta_b2 :");
        edtFaltaB2 = new JTextField(5);
        
        lblFaltaB3 = new JLabel("Falta_b3 :");
        edtFaltaB3 = new JTextField(5);
        
        lblFaltaB4 = new JLabel("Falta_b4 :");
        edtFaltaB4 = new JTextField(5);
        
        lblTotalFaltas = new JLabel("QTD Faltas Bimestral:");
        edtTotalFaltas = new JTextField();
        edtTotalFaltas.setEditable(false);
        
        
        // Botões
        btnCallMedia = new JButton("Calcular");
        btnCallMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CallMedia();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite apenas números nas notas!", "Tratamento de Evento", 
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        btnCallFalta = new JButton("Somar Faltas");
        btnCallFalta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CallFalta();
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Digite apenas números nas Faltas!", "Tratamento de Evento", 
                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    };
   
//  3° Posicionando OBJ
    public void PosicionarObj() {
        // SetBounds ( X, Y, Lagura, Altura)
        lblRA.setBounds(20, 20, 80, 25);
        edtRA.setBounds(100, 20, 100, 25);

        lblNome.setBounds(20, 60, 80, 25);
        edtNome.setBounds(100, 60, 200, 25);

        lblCurso.setBounds(20, 100, 80, 25);
        cmbCurso.setBounds(100, 100, 200, 25);

        // Nota
        lblNota_b1.setBounds(20, 140, 80, 25);
        edtNota_b1.setBounds(100, 140, 50, 25);

        lblNota_b2.setBounds(20, 180, 80, 25);
        edtNota_b2.setBounds(100, 180, 50, 25);

        lblNota_b3.setBounds(20, 220, 80, 25);
        edtNota_b3.setBounds(100, 220, 50, 25);

        lblNota_b4.setBounds(20, 260, 80, 25);
        edtNota_b4.setBounds(100, 260, 50, 25);
        
        lblMedia.setBounds(100, 350, 80, 25);
        edtMedia.setBounds(340, 350, 300, 25);
        
        // Faltas
        lblFaltaB1.setBounds(200, 140, 80, 25);
        edtFaltaB1.setBounds(300, 140, 50, 25);
        
        lblFaltaB2.setBounds(200, 180, 80, 25);
        edtFaltaB2.setBounds(300, 180, 50, 25);
        
        lblFaltaB3.setBounds(200, 220, 80, 25);
        edtFaltaB3.setBounds(300, 220, 50, 25);
        
        lblFaltaB4.setBounds(200, 260, 80, 25);
        edtFaltaB4.setBounds(300, 260, 50, 25);
        
        lblTotalFaltas.setBounds(100, 390, 130, 25);
        edtTotalFaltas.setBounds(340, 390, 500, 25);
          
        // Botão
        btnCallMedia.setBounds(20, 450, 80, 25);
        btnCallFalta.setBounds(200, 450, 150, 25);
    }
 
//  4° add OBJ
    public void AddObj() {
        add(lblRA);
        add(edtRA);

        add(lblNome);
        add(edtNome);
        
        add(lblCurso);
        add(cmbCurso);

        // Notas
        add(lblNota_b1);
        add(edtNota_b1);

        add(lblNota_b2);
        add(edtNota_b2);

        add(lblNota_b3);
        add(edtNota_b3);

        add(lblNota_b4);
        add(edtNota_b4);
        
        add(lblMedia);
        add(edtMedia);
        
        // Faltas
        add(lblFaltaB1);
        add(edtFaltaB1);
        
        add(lblFaltaB2);
        add(edtFaltaB2);
        
        add(lblFaltaB3);
        add(edtFaltaB3);
        
        add(lblFaltaB4);
        add(edtFaltaB4);
     
        add(lblTotalFaltas);
        add(edtTotalFaltas);
        
        // Botão
        add(btnCallMedia);
        add(btnCallFalta);
        

    }

//  cfg layout   
    public void InicializarFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));
        setLayout(null);
        setVisible(true);
        pack();
    }
    
    private void CallMedia(){
        // Pegando valores das notas
        double n1 = Double.parseDouble(edtNota_b1.getText());
        double n2 = Double.parseDouble(edtNota_b2.getText());
        double n3 = Double.parseDouble(edtNota_b3.getText());
        double n4 = Double.parseDouble(edtNota_b4.getText());

        media = (n1 + n2 + n3 + n4) / 4;

        String aluno = edtNome.getText();
        // print media
        edtMedia.setText(String.format("Aluno: %s sua Media Anual foi de: %.2f", aluno, media));
        edtMedia.setForeground(Color.black);
        edtMedia.setFont(new Font("Arial", Font.BOLD, 14));
    }
    
    private void CallFalta(){
        // pegando as faltas
        int f1 = Integer.parseInt(edtFaltaB1.getText());
        int f2 = Integer.parseInt(edtFaltaB2.getText());
        int f3 = Integer.parseInt(edtFaltaB3.getText());
        int f4 = Integer.parseInt(edtFaltaB4.getText());

        String aluno = edtNome.getText();

        total = f1 + f2 + f3 + f4;

        if (total >= ((25*MAX_FALTAS)/100)){
            edtTotalFaltas.setText(String.format("Aluno: %s , Reprovado por faltas, qtd faltas: %d", aluno, total));
            edtTotalFaltas.setBackground(Color.red);
            edtTotalFaltas.setForeground(Color.white);
            edtTotalFaltas.setFont(new Font("Arial", Font.BOLD, 14));
            
        } else {
            edtTotalFaltas.setText(String.format("Aluno: %s , APROVADO, qdt faltas: %d", aluno, total));
            edtTotalFaltas.setBackground(Color.green);
            edtTotalFaltas.setForeground(Color.black);
            edtTotalFaltas.setFont(new Font("Arial", Font.BOLD, 14));
        }
    }
      
    
 
    // valida o que foi digitado nos JTextField das Faltas
    
    
    
    
    public static void main(String[] args) {
        Exercicio01 obj1 = new Exercicio01();
    };

}
