package t01_03_09_25;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class AbaContrato extends JPanel {

    private JLabel lblVinculo, 
            lblTipoDeAdmissao, 
            lblDataAdmissao, 
            lblTipoDeSalario, 
            lblHorario, 
            lblHrsSem, 
            lblFgts,
            lblOpcao, 
            lblDataOpcao, 
            lblAtividadeDesenvolvida, 
            lblAdiantamentoQuinzenal, 
            lblAdiantamento,
            lblPercentual, 
            lblValorFixo, 
            lblValorSalario, 
            lblTipoDeReajuste, 
            lblExperiencia, 
            lblVencimento,
            lblProrrogacao, 
            lblCargo, 
            lblDepartamento,
            lblCategoriaGfip, 
            lblTipoContrato, 
            lblRescisao,
            lblDataDemissao, 
            lblMotivoDemissao, 
            lblDataAvisoInicio, 
            lblDataAvisoFim, 
            lblMotivoRais;
    
    private JTextField edtDataAdmissao, 
            edtHrsSem, 
            edtDataOpcao, 
            edtPercentual, 
            edtValorFixo, 
            edtValorSalario,
            edtVencimento, 
            edtProrrogacao, 
            edtDataDemissao, 
            edtDataAvisoInicio, 
            edtDataAvisoFim;
    
    private JComboBox<String> cbVinculo, 
            cbTipoDeAdmissao, 
            cbTipoDeSalario, 
            cbHorario, 
            cbOpcao, 
            cbAdiantamento,
            cbTipoDeReajuste, 
            cbCargo, 
            cbDepartamento, 
            cbCategoriaGfip, 
            cbTipoContrato, 
            cbMotivoDemissao, 
            cbMotivoRais;
    
    private JCheckBox chkAtividadeDesenvolvidaUrbana, 
            chkAtividadeDesenvolvidaRural, 
            chkAvisoPrevio;

    private String[] vinculo = {"Trabalhador", "Gerente"};
    private String[] tipodeadmissao = {"Emprego", "Reemprego"};
    private String[] tipodesalario = {"Mensal", "Anual"};
    private String[] horario = {"Geral", "Parcial"};
    private String[] opcao = {"Optante"};
    private String[] adiantamento = {"Sim", "Não"};
    private String[] tipodereajuste = {"Variável", "Não Variável"};
    private String[] cargo = {"Gerente Administrativo", "Operário"};
    private String[] departamento = {"Administrativo", "TI"};
    private String[] categoriagfip = {"Contribuinte Individual", "Contribuinte Coletivo"};
    private String[] tipocontrato = {"Semestral", "Anual"};
    private String[] motivodemissao = {"Com Justa Causa", "Sem Justa Causa"};
    private String[] motivorais = {"1", "2"};

    public AbaContrato() {
        setLayout(null);
        setBackground(new Color(60, 63, 65));


        criarComponentes();
        adicionarComponentes();
        configurarPosicoes();

    }

    private void criarComponentes() {

        lblVinculo = new JLabel("Vínculo");
        cbVinculo = new JComboBox(vinculo);

        lblTipoDeAdmissao = new JLabel("Tipo de Admissão");
        cbTipoDeAdmissao = new JComboBox(tipodeadmissao);

        lblDataAdmissao = new JLabel("Data Admissão");
        edtDataAdmissao = new JTextField(10);

        lblTipoDeSalario = new JLabel("Tipo de Salário");
        cbTipoDeSalario = new JComboBox(tipodesalario);

        lblHorario = new JLabel("Horário");
        cbHorario = new JComboBox(horario);

        lblHrsSem = new JLabel("Hrs. Sem.");
        edtHrsSem = new JTextField(3);

        lblFgts = new JLabel("FGTS");

        lblOpcao = new JLabel("Opção");
        cbOpcao = new JComboBox(opcao);

        lblDataOpcao = new JLabel("Data Opção");
        edtDataOpcao = new JTextField(10);

        lblAtividadeDesenvolvida = new JLabel("Atividade Desenvolvida");
        chkAtividadeDesenvolvidaUrbana = new JCheckBox("Urbana");
        chkAtividadeDesenvolvidaRural = new JCheckBox("Rural");

        lblAdiantamentoQuinzenal = new JLabel("Adiantamento Quinzenal");

        lblAdiantamento = new JLabel("Adiantamento");
        cbAdiantamento = new JComboBox(adiantamento);

        lblPercentual = new JLabel("Percentual");
        edtPercentual = new JTextField(3);

        lblValorFixo = new JLabel("Valor Fixo");
        edtValorFixo = new JTextField(10);

        lblValorSalario = new JLabel("Valor Salário");
        edtValorSalario = new JTextField(10);

        lblTipoDeReajuste = new JLabel("Tipo de Reajuste");
        cbTipoDeReajuste = new JComboBox(tipodereajuste);

        lblExperiencia = new JLabel("Experiência");

        lblVencimento = new JLabel("Vencimento");
        edtVencimento = new JTextField(10);

        lblProrrogacao = new JLabel("Prorrogação");
        edtProrrogacao = new JTextField(10);

        lblCargo = new JLabel("Cargo");
        cbCargo = new JComboBox(cargo);

        lblDepartamento = new JLabel("Departamento");
        cbDepartamento = new JComboBox(departamento);

        lblCategoriaGfip = new JLabel("Categoria GFIP");
        cbCategoriaGfip = new JComboBox(categoriagfip);

        lblTipoContrato = new JLabel("Tipo Contrato");
        cbTipoContrato = new JComboBox(tipocontrato);

        lblRescisao = new JLabel("Rescisão");

        lblDataDemissao = new JLabel("Data Demissão");
        edtDataDemissao = new JTextField(10);

        lblMotivoDemissao = new JLabel("Motivo Demissão");
        cbMotivoDemissao = new JComboBox(motivodemissao);

        chkAvisoPrevio = new JCheckBox("Aviso Prévio");

        lblDataAvisoInicio = new JLabel("Data Aviso Início");
        edtDataAvisoInicio = new JTextField(10);

        lblDataAvisoFim = new JLabel("Data Aviso Fim");
        edtDataAvisoFim = new JTextField(10);

        lblMotivoRais = new JLabel("Motivo RAIS");
        cbMotivoRais = new JComboBox(motivorais);

    }

    private void adicionarComponentes() {

        add(lblVinculo);
        add(cbVinculo);

        add(lblTipoDeAdmissao);
        add(cbTipoDeAdmissao);

        add(lblDataAdmissao);
        add(edtDataAdmissao);

        add(lblTipoDeSalario);
        add(cbTipoDeSalario);

        add(lblHorario);
        add(cbHorario);

        add(lblHrsSem);
        add(edtHrsSem);

        add(lblFgts);

        add(lblOpcao);
        add(cbOpcao);

        add(lblDataOpcao);
        add(edtDataOpcao);

        add(lblAtividadeDesenvolvida);
        add(chkAtividadeDesenvolvidaUrbana);
        add(chkAtividadeDesenvolvidaRural);

        add(lblAdiantamentoQuinzenal);

        add(lblAdiantamento);
        add(cbAdiantamento);

        add(lblPercentual);
        add(edtPercentual);

        add(lblValorFixo);
        add(edtValorFixo);

        add(lblValorSalario);
        add(edtValorSalario);

        add(lblTipoDeReajuste);
        add(cbTipoDeReajuste);

        add(lblExperiencia);

        add(lblVencimento);
        add(edtVencimento);

        add(lblProrrogacao);
        add(edtProrrogacao);

        add(lblCargo);
        add(cbCargo);

        add(lblDepartamento);
        add(cbDepartamento);

        add(lblCategoriaGfip);
        add(cbCategoriaGfip);

        add(lblTipoContrato);
        add(cbTipoContrato);

        add(lblRescisao);

        add(lblDataDemissao);
        add(edtDataDemissao);

        add(lblMotivoDemissao);
        add(cbMotivoDemissao);

        add(chkAvisoPrevio);

        add(lblDataAvisoInicio);
        add(edtDataAvisoInicio);

        add(lblDataAvisoFim);
        add(edtDataAvisoFim);

        add(lblMotivoRais);
        add(cbMotivoRais);

    }

    private void configurarPosicoes() {

        lblVinculo.setBounds(20, 20, 120, 25);
        cbVinculo.setBounds(150, 20, 250, 25);

        lblTipoDeAdmissao.setBounds(20, 55, 140, 25);
        cbTipoDeAdmissao.setBounds(150, 55, 180, 25);

        lblDataAdmissao.setBounds(20, 90, 140, 25);
        edtDataAdmissao.setBounds(150, 90, 120, 25);

        lblTipoDeSalario.setBounds(20, 125, 140, 25);
        cbTipoDeSalario.setBounds(150, 125, 120, 25);

        lblHorario.setBounds(300, 125, 100, 25);
        cbHorario.setBounds(380, 125, 120, 25);

        lblHrsSem.setBounds(520, 125, 80, 25);
        edtHrsSem.setBounds(580, 125, 50, 25);

        lblFgts.setBounds(20, 160, 100, 25);

        lblOpcao.setBounds(40, 190, 80, 25);
        cbOpcao.setBounds(150, 190, 120, 25);

        lblDataOpcao.setBounds(300, 190, 100, 25);
        edtDataOpcao.setBounds(400, 190, 120, 25);

        lblAtividadeDesenvolvida.setBounds(20, 230, 180, 25);
        chkAtividadeDesenvolvidaUrbana.setBounds(200, 230, 100, 25);
        chkAtividadeDesenvolvidaRural.setBounds(310, 230, 100, 25);

        lblAdiantamentoQuinzenal.setBounds(20, 270, 180, 25);

        lblAdiantamento.setBounds(40, 300, 100, 25);
        cbAdiantamento.setBounds(150, 300, 120, 25);

        lblPercentual.setBounds(300, 300, 100, 25);
        edtPercentual.setBounds(400, 300, 60, 25);

        lblValorFixo.setBounds(480, 300, 100, 25);
        edtValorFixo.setBounds(580, 300, 120, 25);

        lblValorSalario.setBounds(20, 340, 120, 25);
        edtValorSalario.setBounds(150, 340, 120, 25);

        lblTipoDeReajuste.setBounds(300, 340, 120, 25);
        cbTipoDeReajuste.setBounds(420, 340, 150, 25);

        lblExperiencia.setBounds(20, 380, 120, 25);

        lblVencimento.setBounds(40, 410, 120, 25);
        edtVencimento.setBounds(150, 410, 120, 25);

        lblProrrogacao.setBounds(300, 410, 120, 25);
        edtProrrogacao.setBounds(420, 410, 120, 25);

        lblCargo.setBounds(650, 20, 120, 25);
        cbCargo.setBounds(780, 20, 220, 25);

        lblDepartamento.setBounds(650, 55, 120, 25);
        cbDepartamento.setBounds(780, 55, 220, 25);

        lblCategoriaGfip.setBounds(650, 90, 140, 25);
        cbCategoriaGfip.setBounds(780, 90, 220, 25);

        lblTipoContrato.setBounds(650, 125, 120, 25);
        cbTipoContrato.setBounds(780, 125, 220, 25);

        lblRescisao.setBounds(650, 170, 100, 25);

        lblDataDemissao.setBounds(670, 200, 120, 25);
        edtDataDemissao.setBounds(800, 200, 120, 25);

        lblMotivoDemissao.setBounds(670, 235, 120, 25);
        cbMotivoDemissao.setBounds(800, 235, 220, 25);

        chkAvisoPrevio.setBounds(670, 270, 140, 25);

        lblDataAvisoInicio.setBounds(670, 300, 140, 25);
        edtDataAvisoInicio.setBounds(800, 300, 120, 25);

        lblDataAvisoFim.setBounds(670, 335, 140, 25);
        edtDataAvisoFim.setBounds(800, 335, 120, 25);

        lblMotivoRais.setBounds(670, 370, 140, 25);
        cbMotivoRais.setBounds(800, 370, 220, 25);

    }

}
