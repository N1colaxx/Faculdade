package menu_nf;

import java.awt.*;
import javax.swing.*;

public class TelaInfoContato extends JPanel {
    // 1° declarando
    private JLabel lbl_endereco, 
            lbl_logradouro, lbl_numero, lbl_complemento, lbl_bairro, lbl_cidade, lbl_estado, lbl_cep,
            lbl_telefone, lbl_email;
    
    private JTextField edt_endereco, 
            edt_logradouro, edt_numero, edt_complemento, edt_bairro, edt_cidade, edt_estado, edt_cep,
            edt_telefone, edt_email;
    
    public TelaInfoContato() {
        setLayout(null); // usando null layout por enquanto

        instanciar();
        posicionar();
        adicionar();
    }
    
    // 2° instanciar
    private void instanciar() {
        lbl_endereco = new JLabel("Endereço: ");
        edt_endereco = new JTextField();

        lbl_logradouro = new JLabel("Logradouro: ");
        edt_logradouro = new JTextField();

        lbl_numero = new JLabel("Número: ");
        edt_numero = new JTextField();

        lbl_complemento = new JLabel("Complemento: ");
        edt_complemento = new JTextField();

        lbl_bairro = new JLabel("Bairro: ");
        edt_bairro = new JTextField();

        lbl_cidade = new JLabel("Cidade: ");
        edt_cidade = new JTextField();

        lbl_estado = new JLabel("Estado: ");
        edt_estado = new JTextField();

        lbl_cep = new JLabel("CEP: ");
        edt_cep = new JTextField();

        lbl_telefone = new JLabel("Telefone: ");
        edt_telefone = new JTextField();

        lbl_email = new JLabel("E-mail: ");
        edt_email = new JTextField();
    }
        
    // 3° posicionar
    private void posicionar() {
        int xLabel = 20;
        int xField = 150;
        int largura = 200;
        int altura = 25;
        int y = 20;
        int passo = 40;

        lbl_endereco.setBounds(xLabel, y, 100, altura);
        edt_endereco.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_logradouro.setBounds(xLabel, y, 100, altura);
        edt_logradouro.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_numero.setBounds(xLabel, y, 100, altura);
        edt_numero.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_complemento.setBounds(xLabel, y, 100, altura);
        edt_complemento.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_bairro.setBounds(xLabel, y, 100, altura);
        edt_bairro.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_cidade.setBounds(xLabel, y, 100, altura);
        edt_cidade.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_estado.setBounds(xLabel, y, 100, altura);
        edt_estado.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_cep.setBounds(xLabel, y, 100, altura);
        edt_cep.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_telefone.setBounds(xLabel, y, 100, altura);
        edt_telefone.setBounds(xField, y, largura, altura);
        y += passo;

        lbl_email.setBounds(xLabel, y, 100, altura);
        edt_email.setBounds(xField, y, largura, altura);
    }
        
    // 4° adicionar
    private void adicionar() {
        add(lbl_endereco);
        add(edt_endereco);

        add(lbl_logradouro);
        add(edt_logradouro);

        add(lbl_numero);
        add(edt_numero);

        add(lbl_complemento);
        add(edt_complemento);

        add(lbl_bairro);
        add(edt_bairro);

        add(lbl_cidade);
        add(edt_cidade);

        add(lbl_estado);
        add(edt_estado);

        add(lbl_cep);
        add(edt_cep);

        add(lbl_telefone);
        add(edt_telefone);

        add(lbl_email);
        add(edt_email);
    }
}
