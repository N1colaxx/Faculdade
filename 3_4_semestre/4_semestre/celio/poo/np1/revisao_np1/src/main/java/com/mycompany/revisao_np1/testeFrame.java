package com.mycompany.revisao_np1;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class testeFrame extends JFrame{
    
    private JLabel
            lblNome, lblCbxUF;
    private JTextField
            edtNome;
    private JButton 
            btnCancelar, btnSalvar;
    private JTabbedPane 
               tabl01;
    private JPanel
            abaCadastro;
    
    private JComboBox<String> cbxUF;
    private String[] listaUF = {"SP", "RJ", "MG"};
    
    public testeFrame() {
        instanciar();
        posicionar();
        adicionar();
        
        cfgFrame();
    }
    
    private void cfgFrame(){
        setTitle("Teste de Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void instanciar(){        
        System.out.println(" Instanciar Iniciado (...)");
        
        try {            
            lblNome = new JLabel("Digite Nome: "); 
            edtNome = new JTextField(10);
            
            lblCbxUF = new JLabel("UF");
            cbxUF = new JComboBox<>(listaUF);
            
                        // --- Abas ---
            tabl01 = new JTabbedPane();
            abaCadastro = new JPanel(null); 

            
            btnCancelar = new JButton("Cancelar");
                btnCancelar.setBackground(Color.RED);
                btnCancelar.addActionListener(e -> {
                    System.out.println(" btnCancelar CLICADO");
                });
            btnSalvar = new JButton ("Salvar");
                btnSalvar.setBackground(Color.GREEN);
                btnSalvar.addActionListener(e -> {
                    String nome = edtNome.getText();
                    String uf = (String) cbxUF.getSelectedItem();
                    
                    System.out.println(" btnSalvar CLICADO");
                    
                    JOptionPane.showMessageDialog(this, "Salvo: " + nome + "\nUF = " + uf, "info", JOptionPane.INFORMATION_MESSAGE);
                });
                
        } catch (Exception ex){
            System.out.println("\n ERRO! ao INSTANCIAR \n " + ex);
        }
        
        System.out.println(" Instanciar Finalisado (SUCESSO!)");
    }
    
    private void posicionar() {
        // x, y, width, height
        lblNome.setBounds(30, 30, 120, 30);
        edtNome.setBounds(160, 30, 300, 30);
        
  
        // --- Tamanho/posição do componente de abas ---
        tabl01.setBounds(470, 20, 300, 200);       
                
        lblCbxUF.setBounds(10, 70, 120, 30);
        cbxUF.setBounds(150, 70, 100, 30);
        
        
        btnCancelar.setBounds(160, 80, 120, 40);
        btnSalvar.setBounds(300, 80, 120, 40);
        
      
    }

private void adicionar() {
        // 1) Monta a aba "Cadastro"
        add(lblNome);
        add(edtNome);
        
        abaCadastro.add(lblCbxUF);
        abaCadastro.add(cbxUF);

        // 2) Adiciona a aba ao tabbed pane
        tabl01.addTab("Cadastro", abaCadastro);

        // 3) Adiciona o tabbed pane no frame
        add(tabl01);

        // 4) Adiciona os botões no frame (fora da aba)
        add(btnCancelar);
        add(btnSalvar);
    }
    
    public static void main(String[] args) {
        testeFrame obj = new testeFrame();
    }
}
