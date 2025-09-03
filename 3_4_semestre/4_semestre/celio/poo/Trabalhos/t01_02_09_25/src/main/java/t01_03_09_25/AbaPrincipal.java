package t01_03_09_25;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AbaPrincipal extends JPanel {

    private JLabel lblDadosGerais, 
            lblEmpresa, 
            lblDivisaoRh, 
            lblMatricula, 
            lblFuncionario, 
            lblEnderecoTitulo,
            lblCep, 
            lblEndereco, 
            lblNumero, 
            lblBairro, 
            lblMunicipio, 
            lblTelefone, 
            lblTelefoneCel, 
            lblEmail, 
            lblDadosPessoais,
            lblSexo, 
            lblDataNascimento, 
            lblEstadoCivil, 
            lblNaturalidade, 
            lblNacionalidade, 
            lblGrauDeInstrucao,
            lblFormacao, 
            lblFiliacao, 
            lblPai, 
            lblMae;
    
    private JTextField edtMatricula, 
            edtFuncionario, 
            edtCep, 
            edtEndereco, 
            edtNumero, 
            edtBairro, 
            edtTelefone,
            edtTelefoneCel, 
            edtEmail, 
            edtDataNascimento, 
            edtNaturalidade, 
            edtNacionalidade, 
            edtFormacao,
            edtPai, edtMae;
    private JComboBox<String> cbEmpresa, cbDivisaoRh, cbMunicipio, cbSexo, cbEstadoCivil, cbGrauDeInstrucao;

    private String[] empresas = {"1000- Empresa 1", "2000- Empresa 2", "3000- Empresa 3", "4000- Empresa 4", "5000- Empresa 5"};
    
    private String[] divisaorh = {"100- Dono", "200- Gerente", "300- Administrador", "400- TI"};
    
    private String[] municipios = {"Reginópolis - SP", "Bauru - SP", "Catanduva - SP", "Pongaí - SP"};
    
    private String[] generos = {"Masculino", "Feminino"};
    
    private String[] estadocivil = {"Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viúvo(a)"};
    
    private String[] graudeinstrucao = {"Ensino Fundamental", "Ensino Médio", "Graduação"};

    public AbaPrincipal() {

        setLayout(null);
        setBackground(new Color(60, 63, 65));   
        criarComponentes();
        adicionarComponentes();
        configurarPosicoes();

    }

    private void criarComponentes() {

        lblDadosGerais = new JLabel("Dados Gerais");

        lblEmpresa = new JLabel("Empresa");
        cbEmpresa = new JComboBox(empresas);

        lblDivisaoRh = new JLabel("Divisão RH");
        cbDivisaoRh = new JComboBox(divisaorh);

        lblMatricula = new JLabel("Matrícula");
        edtMatricula = new JTextField(4);

        lblFuncionario = new JLabel("Funcionário");
        edtFuncionario = new JTextField(150);

        lblEnderecoTitulo = new JLabel("Endereço");

        lblCep = new JLabel("CEP");
        edtCep = new JTextField(15);

        lblEndereco = new JLabel("Endereço");
        edtEndereco = new JTextField(150);

        lblNumero = new JLabel("Nº");
        edtNumero = new JTextField(4);

        lblBairro = new JLabel("Bairro");
        edtBairro = new JTextField(150);

        lblMunicipio = new JLabel("Município");
        cbMunicipio = new JComboBox(municipios);

        lblTelefone = new JLabel("Telefone");
        edtTelefone = new JTextField(10);

        lblTelefoneCel = new JLabel("Telefone Cel.");
        edtTelefoneCel = new JTextField(11);

        lblEmail = new JLabel("Email");
        edtEmail = new JTextField(150);

        lblDadosPessoais = new JLabel("Dados Pessoais");

        lblSexo = new JLabel("Sexo");
        cbSexo = new JComboBox(generos);

        lblDataNascimento = new JLabel("Data Nascimento");
        edtDataNascimento = new JTextField(10);

        lblEstadoCivil = new JLabel("Estado Civil");
        cbEstadoCivil = new JComboBox(estadocivil);

        lblNaturalidade = new JLabel("Naturalidade");
        edtNaturalidade = new JTextField(50);

        lblNacionalidade = new JLabel("Nacionalidade");
        edtNacionalidade = new JTextField(50);

        lblGrauDeInstrucao = new JLabel("Grau de Instrução");
        cbGrauDeInstrucao = new JComboBox(graudeinstrucao);

        lblFormacao = new JLabel("Formação");
        edtFormacao = new JTextField(100);

        lblFiliacao = new JLabel("Filiação");

        lblPai = new JLabel("Pai");
        edtPai = new JTextField(100);

        lblMae = new JLabel("Mãe");
        edtMae = new JTextField(100);

    }

    private void adicionarComponentes() {

        add(lblDadosGerais);

        add(lblEmpresa);
        add(cbEmpresa);

        add(lblDivisaoRh);
        add(cbDivisaoRh);

        add(lblMatricula);
        add(edtMatricula);

        add(lblFuncionario);
        add(edtFuncionario);

        add(lblEnderecoTitulo);

        add(lblCep);
        add(edtCep);

        add(lblEndereco);
        add(edtEndereco);

        add(lblNumero);
        add(edtNumero);

        add(lblBairro);
        add(edtBairro);

        add(lblMunicipio);
        add(cbMunicipio);

        add(lblTelefone);
        add(edtTelefone);

        add(lblTelefoneCel);
        add(edtTelefoneCel);

        add(lblEmail);
        add(edtEmail);

        add(lblDadosPessoais);

        add(lblSexo);
        add(cbSexo);

        add(lblDataNascimento);
        add(edtDataNascimento);

        add(lblEstadoCivil);
        add(cbEstadoCivil);

        add(lblNaturalidade);
        add(edtNaturalidade);

        add(lblNacionalidade);
        add(edtNacionalidade);

        add(lblGrauDeInstrucao);
        add(cbGrauDeInstrucao);

        add(lblFormacao);
        add(edtFormacao);

        add(lblFiliacao);

        add(lblPai);
        add(edtPai);

        add(lblMae);
        add(edtMae);

    }

    private void configurarPosicoes() {

        lblDadosGerais.setBounds(20, 10, 200, 30);

        lblEmpresa.setBounds(20, 50, 100, 20);
        cbEmpresa.setBounds(20, 75, 350, 25);

        lblDivisaoRh.setBounds(400, 50, 120, 20);
        cbDivisaoRh.setBounds(400, 75, 350, 25);

        lblMatricula.setBounds(770, 50, 100, 20);
        edtMatricula.setBounds(770, 75, 120, 25);

        lblFuncionario.setBounds(20, 115, 120, 20);
        edtFuncionario.setBounds(20, 140, 500, 25);

        lblEnderecoTitulo.setBounds(20, 180, 200, 25);

        lblCep.setBounds(20, 210, 80, 20);
        edtCep.setBounds(20, 235, 150, 25);

        lblEndereco.setBounds(190, 210, 100, 20);
        edtEndereco.setBounds(190, 235, 400, 25);

        lblNumero.setBounds(610, 210, 50, 20);
        edtNumero.setBounds(610, 235, 80, 25);

        lblBairro.setBounds(710, 210, 100, 20);
        edtBairro.setBounds(710, 235, 250, 25);

        lblMunicipio.setBounds(20, 270, 100, 20);
        cbMunicipio.setBounds(20, 295, 250, 25);

        lblTelefone.setBounds(300, 270, 100, 20);
        edtTelefone.setBounds(300, 295, 150, 25);

        lblTelefoneCel.setBounds(470, 270, 120, 20);
        edtTelefoneCel.setBounds(470, 295, 150, 25);

        lblEmail.setBounds(650, 270, 100, 20);
        edtEmail.setBounds(650, 295, 330, 25);

        lblDadosPessoais.setBounds(20, 340, 200, 25);

        lblSexo.setBounds(20, 370, 80, 20);
        cbSexo.setBounds(20, 395, 120, 25);

        lblDataNascimento.setBounds(160, 370, 120, 20);
        edtDataNascimento.setBounds(160, 395, 120, 25);

        lblEstadoCivil.setBounds(300, 370, 100, 20);
        cbEstadoCivil.setBounds(300, 395, 180, 25);

        lblNaturalidade.setBounds(500, 370, 100, 20);
        edtNaturalidade.setBounds(500, 395, 180, 25);

        lblNacionalidade.setBounds(700, 370, 120, 20);
        edtNacionalidade.setBounds(700, 395, 180, 25);

        lblGrauDeInstrucao.setBounds(20, 430, 150, 20);
        cbGrauDeInstrucao.setBounds(20, 455, 200, 25);

        lblFormacao.setBounds(240, 430, 100, 20);
        edtFormacao.setBounds(240, 455, 400, 25);

        lblFiliacao.setBounds(20, 500, 200, 25);

        lblPai.setBounds(20, 530, 350, 25);
        edtPai.setBounds(20, 555, 350, 25);

        lblMae.setBounds(390, 530, 350, 25);
        edtMae.setBounds(390, 555, 350, 25);

    }

}
