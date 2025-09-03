package t01_03_09_25;

import javax.swing.*;
import java.awt.*;

public class AbaOperacional extends JPanel {

    private JLabel
    lblRacaCor,
    lblTipoDeDeficiencia,
    lblSindicato,
    lblSindicato2,
    lblMesDissidio,
    lblDadosFuncionario,
    lblFormaPagamento,
    lblBanco,
    lblAgencia,
    lblNumeroConta,
    lblExamesAdmissionais,
    lblNomeMedico,
    lblCrm,
    lblDataExame,
    lblFotoTitulo,
    lblFoto;

    private JTextField edtAgencia, edtNumeroConta, edtNomeMedico, edtCrm, edtDataExame;
    
    private JComboBox<String> cbRacaCor, cbTipoDeDeficiencia, cbSindicato2, cbMesDissidio, cbFormaPagamento, cbBanco;
    
    private JCheckBox
    chkInss,
    chkFgts,
    chkIrrf,
    chkReembolsoInssIrrf,
    chkEmpregadoDomestico,
    chkBeneficioPrevidenciaAposentadoria,
    chkValeTransporte,
    chkValeRefeicao,
    chkPlanoSaude;

    private JButton btnSelecionar, btnLimpar;

    private String[] racacor = {"Branco", "Negro", "Pardo"};
    
    private String[] tipodedeficiencia = {"0", "1", "2", "3", "+"};
    
    private String[] sindicato2 = {"Sindicato Funcional", "Sindicato Administrativo"};
    
    private String[] mesdissidio = {
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };
        
    private String[] formapagamento = {"Débito em Conta", "Dinheiro", "Cheque"};
    
    private String[] bancos = {"Banco do Brasil", "Caixa Econômica", "Bradesco", "Itaú", "Santander"};

    public AbaOperacional() {
        setLayout(null);
        setBackground(new Color(60, 63, 65));
        
        criarComponentes();
        adicionarComponentes();
        configurarPosicoes();

    }

    private void criarComponentes() {

        chkInss = new JCheckBox("INSS");
        chkFgts = new JCheckBox("FGTS");
        chkIrrf = new JCheckBox("IRRF");
        chkReembolsoInssIrrf = new JCheckBox("Reembolso INSS/IRRF");
        chkEmpregadoDomestico = new JCheckBox("Empregado Doméstico");
        chkBeneficioPrevidenciaAposentadoria = new JCheckBox("Benefício Previdência - Aposentadoria");
        chkValeTransporte = new JCheckBox("Vale Transporte");
        chkValeRefeicao = new JCheckBox("Vale Refeição");
        chkPlanoSaude = new JCheckBox("Plano Saúde");

        lblRacaCor = new JLabel("Raça Cor");
        cbRacaCor = new JComboBox(racacor);

        lblTipoDeDeficiencia = new JLabel("Tipo de Deficiência");
        cbTipoDeDeficiencia = new JComboBox(tipodedeficiencia);

        lblSindicato = new JLabel("Sindicato");
        lblSindicato2 = new JLabel("Sindicato");
        cbSindicato2 = new JComboBox(sindicato2);

        lblMesDissidio = new JLabel("Mês Dissídio");
        cbMesDissidio = new JComboBox(mesdissidio);

        lblDadosFuncionario = new JLabel("Dados Funcionário");
        lblFormaPagamento = new JLabel("Forma de Pagamento");
        cbFormaPagamento = new JComboBox(formapagamento);

        lblBanco = new JLabel("Banco");
        cbBanco = new JComboBox(bancos);

        lblAgencia = new JLabel("Agência");
        edtAgencia = new JTextField();

        lblNumeroConta = new JLabel("Número Conta");
        edtNumeroConta = new JTextField();

        lblExamesAdmissionais = new JLabel("Exames Admissionais");
        lblNomeMedico = new JLabel("Nome Médico");
        edtNomeMedico = new JTextField();

        lblCrm = new JLabel("CRM");
        edtCrm = new JTextField();

        lblDataExame = new JLabel("Data Exame");
        edtDataExame = new JTextField();

        lblFotoTitulo = new JLabel("Foto");
        lblFoto = new JLabel();
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblFoto.setPreferredSize(new Dimension(220, 240));

        btnSelecionar = new JButton("Selecionar");
        btnLimpar = new JButton("Limpar");

        // --- INÍCIO: Configuração dos botões de foto ---
        // Criamos o gerenciador de imagem, passando o JLabel onde a foto será exibida
        EventoIMG gi = new EventoIMG(lblFoto);

        // Adiciona funcionalidade ao botão "Selecionar" para abrir o explorador de arquivos
        gi.adicionarFuncaoSelecionar(btnSelecionar);

        // Adiciona funcionalidade ao botão "Limpar" para remover a foto do JLabel
        gi.adicionarFuncaoLimpar(btnLimpar);
        // --- FIM: Configuração dos botões de foto ---

    }

    private void adicionarComponentes() {

        add(chkInss);
        add(chkFgts);
        add(chkIrrf);
        add(chkReembolsoInssIrrf);
        add(chkEmpregadoDomestico);
        add(chkBeneficioPrevidenciaAposentadoria);
        add(chkValeTransporte);
        add(chkValeRefeicao);
        add(chkPlanoSaude);

        add(lblRacaCor);
        add(cbRacaCor);

        add(lblTipoDeDeficiencia);
        add(cbTipoDeDeficiencia);

        add(lblSindicato);

        add(lblSindicato2);
        add(cbSindicato2);

        add(lblMesDissidio);
        add(cbMesDissidio);

        add(lblDadosFuncionario);

        add(lblFormaPagamento);
        add(cbFormaPagamento);

        add(lblBanco);
        add(cbBanco);

        add(lblAgencia);
        add(edtAgencia);

        add(lblNumeroConta);
        add(edtNumeroConta);

        add(lblExamesAdmissionais);

        add(lblNomeMedico);
        add(edtNomeMedico);

        add(lblCrm);
        add(edtCrm);

        add(lblDataExame);
        add(edtDataExame);

        add(lblFotoTitulo);
        add(lblFoto);

        add(btnSelecionar);
        add(btnLimpar);

    }

    private void configurarPosicoes() {

        chkInss.setBounds(20, 20, 100, 20);
        chkFgts.setBounds(20, 45, 100, 20);
        chkIrrf.setBounds(20, 70, 100, 20);
        chkReembolsoInssIrrf.setBounds(20, 95, 180, 20);
        chkEmpregadoDomestico.setBounds(20, 120, 180, 20);
        chkBeneficioPrevidenciaAposentadoria.setBounds(20, 145, 250, 20);
        chkValeTransporte.setBounds(20, 170, 150, 20);
        chkValeRefeicao.setBounds(20, 195, 150, 20);
        chkPlanoSaude.setBounds(20, 220, 150, 20);

        lblRacaCor.setBounds(20, 260, 100, 20);
        cbRacaCor.setBounds(100, 260, 150, 22);

        lblTipoDeDeficiencia.setBounds(20, 290, 150, 20);
        cbTipoDeDeficiencia.setBounds(150, 290, 100, 22);

        lblSindicato.setBounds(300, 20, 100, 20);

        lblSindicato2.setBounds(300, 45, 100, 20);
        cbSindicato2.setBounds(400, 45, 200, 22);

        lblMesDissidio.setBounds(620, 45, 100, 20);
        cbMesDissidio.setBounds(720, 45, 120, 22);

        lblDadosFuncionario.setBounds(300, 85, 200, 20);

        lblFormaPagamento.setBounds(300, 110, 120, 20);
        cbFormaPagamento.setBounds(420, 110, 150, 22);

        lblBanco.setBounds(580, 110, 60, 20);
        cbBanco.setBounds(640, 110, 200, 22);

        lblAgencia.setBounds(300, 145, 80, 20);
        edtAgencia.setBounds(380, 145, 80, 22);

        lblNumeroConta.setBounds(470, 145, 100, 20);
        edtNumeroConta.setBounds(570, 145, 120, 22);

        lblExamesAdmissionais.setBounds(300, 185, 200, 20);

        lblNomeMedico.setBounds(300, 210, 100, 20);
        edtNomeMedico.setBounds(400, 210, 250, 22);

        lblCrm.setBounds(300, 240, 50, 20);
        edtCrm.setBounds(350, 240, 100, 22);

        lblDataExame.setBounds(470, 240, 100, 20);
        edtDataExame.setBounds(560, 240, 100, 22);

        lblFotoTitulo.setBounds(870, 2, 40, 40);
        lblFoto.setBounds(870, 30, 220, 240);

        btnSelecionar.setBounds(870, 290, 120, 25);
        btnLimpar.setBounds(870, 320, 120, 25);

    }

}
