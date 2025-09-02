package t01_02_09_25;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AbaPrincipal {
    
    // Campos para validação
    private JFormattedTextField txtCPF;
    private JTextField txtNome;
    private JFormattedTextField txtDataNasc;
    private JTextField txtEmail;
    
    public JPanel criarCabecalho() {
        JPanel cab = new JPanel(new GridLayout(2, 2, 8, 4));
        cab.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        
        
        cab.add(new JLabel("CPF"));
        txtCPF = UIHelper.criarCampoMascara("###.###.###-##");
        cab.add(txtCPF);
        
        cab.add(new JLabel("Nome"));
        txtNome = new JTextField(50);
        cab.add(txtNome);
        
        return cab;
    }
    
    public JPanel criarAba() {
        JPanel aba = new JPanel(new GridLayout(3, 1, 8, 8));
        aba.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));
        
        // Dados Gerais
        aba.add(criarDadosGerais());
        
        // Endereço
        aba.add(criarEndereco());
        
        // Dados Pessoais
        aba.add(criarDadosPessoais());
        
        return aba;
    }
    
    private JPanel criarDadosGerais() {
        JPanel dadosGerais = UIHelper.criarBloco("Dados Gerais", 4);
        
        dadosGerais.add(UIHelper.criarLinha("Empresa", 
            new JComboBox<>(new String[]{"1000 - EMPRESA RH"})));
        dadosGerais.add(UIHelper.criarLinha("Divisão RH", 
            new JComboBox<>(new String[]{"1001 - ADMINISTRACAO"})));
        dadosGerais.add(UIHelper.criarLinha("Matrícula", new JTextField(10)));
        dadosGerais.add(UIHelper.criarLinha("Funcionário", new JTextField(35)));
        
        return dadosGerais;
    }
    
    private JPanel criarEndereco() {
        JPanel endereco = UIHelper.criarBloco("Endereço", 5);
        
        endereco.add(UIHelper.criarLinha("CEP", UIHelper.criarCampoMascara("#####-###")));
        endereco.add(UIHelper.criarLinha("Endereço", new JTextField(36)));
        endereco.add(UIHelper.criarLinhaDupla("Nº", new JTextField(6), "Bairro", new JTextField(22)));
        endereco.add(UIHelper.criarLinha("Município", new JTextField(24)));
        
        txtEmail = new JTextField(30);
        JPanel telEmail = UIHelper.criarLinhaTripla("Telefone", UIHelper.criarCampoMascara("(##)####-####"),
                "Telefone Cel.", UIHelper.criarCampoMascara("(##)#####-####"),
                "Email", txtEmail);
        
        endereco.add(telEmail);
        
        return endereco;
    }
    
    private JPanel criarDadosPessoais() {
        JPanel pessoais = UIHelper.criarBloco("Dados Pessoais", 4);
        
        pessoais.add(UIHelper.criarLinha("Sexo", 
            new JComboBox<>(new String[]{"Feminino","Masculino","Não informar"})));
        
        txtDataNasc = UIHelper.criarCampoMascara("##/##/####");
        pessoais.add(UIHelper.criarLinhaDupla("Data Nascimento", txtDataNasc,
                "Estado Civil", new JComboBox<>(new String[]{"Solteiro","Casado","Divorciado","Viúvo"})));
        
        // Filiação
        JPanel fili = UIHelper.criarBloco("Filiação", 2);
        fili.add(UIHelper.criarLinha("Pai", new JTextField(24)));
        fili.add(UIHelper.criarLinha("Mãe", new JTextField(24)));
        pessoais.add(fili);
        
        // Naturalidade
        JPanel nat = new JPanel(new GridLayout(2,1,6,6));
        nat.setBorder(new TitledBorder(""));
        nat.add(UIHelper.criarLinhaDupla("Naturalidade", new JTextField(18),
                "Nacionalidade", new JTextField(18)));
        nat.add(UIHelper.criarLinhaDupla("Grau de Instrução", 
                new JComboBox<>(new String[]{"Fundamental","Médio","Superior completo","Pós","Mestrado"}),
                "Formação", new JTextField(18)));
        pessoais.add(nat);
        
        return pessoais;
    }
    
    // Getters para validação
    public JFormattedTextField getTxtCPF() { return txtCPF; }
    public JTextField getTxtNome() { return txtNome; }
    public JFormattedTextField getTxtDataNasc() { return txtDataNasc; }
    public JTextField getTxtEmail() { return txtEmail; }
}