package t01_03_09_25;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AbaDocumentacao extends JPanel {

    private JLabel
    lblRg,
    lblNumeroRg,
    lblOrgaoExpedidor,
    lblUf,
    lblDataExpedicao,
    lblInformacaoMilitar,
    lblSituacao,
    lblDataBaixa,
    lblNumeroMilitar,
    lblCategoriaMilitar,
    lblCnh,
    lblNumeroCnh,
    lblCategoriaCnh,
    lblDataCadastroCnh,
    lblDataVencimentoCnh,
    lblConselhoRegional,
    lblNome,
    lblSigla,
    lblRegRegiao,
    lblNumeroConselhoRegional,
    lblDataExpedicaoConselhoRegional,
    lblOrgEmissor,
    lblDataValidadeConselhoRegional,
    lblCtps,
    lblNumeroCtps,
    lblSerie,
    lblOrgaoCtps,
    lblUfCtps,
    lblCpf,
    lblNumeroCpf,
    lblPis,
    lblNumeroPis,
    lblDataCadastroPis,
    lblTituloDeEleitor,
    lblNumeroTituloDeEleitor,
    lblZona,
    lblSecao,
    lblRic,
    lblNumeroRic,
    lblOrgaoExpedidorRic,
    lblDataExpedicaoRic;

    private JTextField
        edtNumeroRg,
        edtOrgaoExpedidor,
        edtDataExpedicao,
        edtSituacao,
        edtDataBaixa,
        edtNumeroMilitar,
        edtNumeroCnh,
        edtCategoriaCnh,
        edtDataCadastroCnh,
        edtDataVencimentoCnh,
        edtNome,
        edtSigla,
        edtRegRegiao,
        edtNumeroConselhoRegional,
        edtDataExpedicaoConselhoRegional,
        edtOrgEmissor,
        edtDataValidadeConselhoRegional,
        edtNumeroCtps,
        edtSerie,
        edtOrgaoCtps,
        edtNumeroCpf,
        edtNumeroPis,
        edtDataCadastroPis,
        edtNumeroTituloDeEleitor,
        edtZona,
        edtSecao,
        edtNumeroRic,
        edtOrgaoExpedidorRic,
        edtDataExpedicaoRic;

    private JComboBox<String>
        cbUf,
        cbCategoriaMilitar,
        cbUfCtps;


    private String[] uf = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
    private String[] categoria = {"Soldado", "Sargento", "Cabo", "Tenente", "Capitão"};
    private String[] ufctps = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};

    public AbaDocumentacao() {
        setLayout(null);
        setBackground(new Color(60, 63, 65));

        criarComponentes();
        adicionarComponentes();
        configurarPosicoes();

    }

    private void criarComponentes() {

        lblRg = new JLabel("RG");

        lblNumeroRg = new JLabel("Número");
        edtNumeroRg = new JTextField(20);

        lblOrgaoExpedidor = new JLabel("Órgão Expedidor");
        edtOrgaoExpedidor = new JTextField(10);

        lblUf = new JLabel("UF");
        cbUf = new JComboBox(uf);

        lblDataExpedicao = new JLabel("Data Expedição");
        edtDataExpedicao = new JTextField(10);

        lblInformacaoMilitar = new JLabel("Informação Militar");

        lblSituacao = new JLabel("Situação");
        edtSituacao = new JTextField(200);

        lblDataBaixa = new JLabel("Data Baixa");
        edtDataBaixa = new JTextField(10);

        lblNumeroMilitar = new JLabel("Número");
        edtNumeroMilitar = new JTextField(4);

        lblCategoriaMilitar = new JLabel("Categoria");
        cbCategoriaMilitar = new JComboBox(categoria);

        lblCnh = new JLabel("CNH");

        lblNumeroCnh = new JLabel("Número");
        edtNumeroCnh = new JTextField(15);

        lblCategoriaCnh = new JLabel("Categoria");
        edtCategoriaCnh = new JTextField(6);

        lblDataCadastroCnh = new JLabel("Data Cadastro");
        edtDataCadastroCnh = new JTextField(10);

        lblDataVencimentoCnh = new JLabel("Data Vencimento");
        edtDataVencimentoCnh = new JTextField(10);

        lblConselhoRegional = new JLabel("Conselho Regional");

        lblNome = new JLabel("Nome");
        edtNome = new JTextField(150);

        lblSigla = new JLabel("Sigla");
        edtSigla = new JTextField(4);

        lblRegRegiao = new JLabel("Reg Região");
        edtRegRegiao = new JTextField(5);

        lblNumeroConselhoRegional = new JLabel("Número");
        edtNumeroConselhoRegional = new JTextField(10);

        lblDataExpedicaoConselhoRegional = new JLabel("Data Expedição");
        edtDataExpedicaoConselhoRegional = new JTextField(10);

        lblOrgEmissor = new JLabel("Org. Emissor");
        edtOrgEmissor = new JTextField(10);

        lblDataValidadeConselhoRegional = new JLabel("Data Validade");
        edtDataValidadeConselhoRegional = new JTextField(10);

        lblCtps = new JLabel("CTPS");

        lblNumeroCtps = new JLabel("Número");
        edtNumeroCtps = new JTextField(20);

        lblSerie = new JLabel("Série");
        edtSerie = new JTextField(4);

        lblOrgaoCtps = new JLabel("Órgão");
        edtOrgaoCtps = new JTextField(100);

        lblUfCtps = new JLabel("UF");
        cbUfCtps = new JComboBox(ufctps);

        lblCpf = new JLabel("CPF");

        lblNumeroCpf = new JLabel("Número");
        edtNumeroCpf = new JTextField(15);

        lblPis = new JLabel("PIS");

        lblNumeroPis = new JLabel("Número");
        edtNumeroPis = new JTextField(20);

        lblDataCadastroPis = new JLabel("Data Cadastro");
        edtDataCadastroPis = new JTextField(10);

        lblTituloDeEleitor = new JLabel("Título de Eleitor");

        lblNumeroTituloDeEleitor = new JLabel("Número");
        edtNumeroTituloDeEleitor = new JTextField(20);

        lblZona = new JLabel("Zona");
        edtZona = new JTextField(3);

        lblSecao = new JLabel("Seção");
        edtSecao = new JTextField(5);

        lblRic = new JLabel("RIC");

        lblNumeroRic = new JLabel("Número");
        edtNumeroRic = new JTextField(10);

        lblOrgaoExpedidorRic = new JLabel("Órgão Expedidor");
        edtOrgaoExpedidorRic = new JTextField(4);

        lblDataExpedicaoRic = new JLabel("Data Expedição");
        edtDataExpedicaoRic = new JTextField(10);

    }

    private void adicionarComponentes() {

        add(lblRg);

        add(lblNumeroRg);
        add(edtNumeroRg);

        add(lblOrgaoExpedidor);
        add(edtOrgaoExpedidor);

        add(lblUf);
        add(cbUf);

        add(lblDataExpedicao);
        add(edtDataExpedicao);

        add(lblInformacaoMilitar);

        add(lblSituacao);
        add(edtSituacao);

        add(lblDataBaixa);
        add(edtDataBaixa);

        add(lblNumeroMilitar);
        add(edtNumeroMilitar);

        add(lblCategoriaMilitar);
        add(cbCategoriaMilitar);

        add(lblCnh);

        add(lblNumeroCnh);
        add(edtNumeroCnh);

        add(lblCategoriaCnh);
        add(edtCategoriaCnh);

        add(lblDataCadastroCnh);
        add(edtDataCadastroCnh);

        add(lblDataVencimentoCnh);
        add(edtDataVencimentoCnh);

        add(lblConselhoRegional);

        add(lblNome);
        add(edtNome);

        add(lblSigla);
        add(edtSigla);

        add(lblRegRegiao);
        add(edtRegRegiao);

        add(lblNumeroConselhoRegional);
        add(edtNumeroConselhoRegional);

        add(lblDataExpedicaoConselhoRegional);
        add(edtDataExpedicaoConselhoRegional);

        add(lblOrgEmissor);
        add(edtOrgEmissor);

        add(lblDataValidadeConselhoRegional);
        add(edtDataValidadeConselhoRegional);

        add(lblCtps);

        add(lblNumeroCtps);
        add(edtNumeroCtps);

        add(lblSerie);
        add(edtSerie);

        add(lblOrgaoCtps);
        add(edtOrgaoCtps);

        add(lblUfCtps);
        add(cbUfCtps);

        add(lblCpf);

        add(lblNumeroCpf);
        add(edtNumeroCpf);

        add(lblPis);

        add(lblNumeroPis);
        add(edtNumeroPis);

        add(lblDataCadastroPis);
        add(edtDataCadastroPis);

        add(lblTituloDeEleitor);

        add(lblNumeroTituloDeEleitor);
        add(edtNumeroTituloDeEleitor);

        add(lblZona);
        add(edtZona);

        add(lblSecao);
        add(edtSecao);

        add(lblRic);

        add(lblNumeroRic);
        add(edtNumeroRic);

        add(lblOrgaoExpedidorRic);
        add(edtOrgaoExpedidorRic);

        add(lblDataExpedicaoRic);
        add(edtDataExpedicaoRic);

    }

    private void configurarPosicoes() {

        lblRg.setBounds(20, 10, 100, 20);

        lblNumeroRg.setBounds(20, 40, 60, 20);
        edtNumeroRg.setBounds(85, 40, 120, 25);

        lblOrgaoExpedidor.setBounds(220, 40, 120, 20);
        edtOrgaoExpedidor.setBounds(340, 40, 80, 25);

        lblUf.setBounds(430, 40, 30, 20);
        cbUf.setBounds(460, 40, 60, 25);

        lblDataExpedicao.setBounds(540, 40, 100, 20);
        edtDataExpedicao.setBounds(640, 40, 100, 25);

        lblInformacaoMilitar.setBounds(20, 75, 150, 20);

        lblSituacao.setBounds(20, 100, 60, 20);
        edtSituacao.setBounds(85, 100, 200, 25);

        lblDataBaixa.setBounds(300, 100, 80, 20);
        edtDataBaixa.setBounds(385, 100, 100, 25);

        lblNumeroMilitar.setBounds(500, 100, 60, 20);
        edtNumeroMilitar.setBounds(565, 100, 80, 25);

        lblCategoriaMilitar.setBounds(660, 100, 80, 20);
        cbCategoriaMilitar.setBounds(740, 100, 100, 25);

        lblCnh.setBounds(20, 140, 100, 20);

        lblNumeroCnh.setBounds(20, 165, 60, 20);
        edtNumeroCnh.setBounds(85, 165, 180, 25);

        lblCategoriaCnh.setBounds(280, 165, 80, 20);
        edtCategoriaCnh.setBounds(365, 165, 50, 25);

        lblDataCadastroCnh.setBounds(430, 165, 100, 20);
        edtDataCadastroCnh.setBounds(530, 165, 100, 25);

        lblDataVencimentoCnh.setBounds(640, 165, 120, 20);
        edtDataVencimentoCnh.setBounds(760, 165, 100, 25);

        lblConselhoRegional.setBounds(20, 205, 200, 20);

        lblNome.setBounds(20, 230, 60, 20);
        edtNome.setBounds(85, 230, 180, 25);

        lblSigla.setBounds(280, 230, 50, 20);
        edtSigla.setBounds(335, 230, 60, 25);

        lblRegRegiao.setBounds(405, 230, 80, 20);
        edtRegRegiao.setBounds(490, 230, 80, 25);

        lblNumeroConselhoRegional.setBounds(580, 230, 60, 20);
        edtNumeroConselhoRegional.setBounds(645, 230, 120, 25);

        lblDataExpedicaoConselhoRegional.setBounds(20, 260, 100, 20);
        edtDataExpedicaoConselhoRegional.setBounds(125, 260, 100, 25);

        lblOrgEmissor.setBounds(240, 260, 80, 20);
        edtOrgEmissor.setBounds(325, 260, 100, 25);

        lblDataValidadeConselhoRegional.setBounds(440, 260, 100, 20);
        edtDataValidadeConselhoRegional.setBounds(545, 260, 100, 25);

        lblCtps.setBounds(20, 295, 100, 20);

        lblNumeroCtps.setBounds(20, 320, 60, 20);
        edtNumeroCtps.setBounds(85, 320, 140, 25);

        lblSerie.setBounds(240, 320, 50, 20);
        edtSerie.setBounds(295, 320, 50, 25);

        lblOrgaoCtps.setBounds(360, 320, 50, 20);
        edtOrgaoCtps.setBounds(410, 320, 60, 25);

        lblUfCtps.setBounds(480, 320, 30, 20);
        cbUfCtps.setBounds(510, 320, 60, 25);

        lblCpf.setBounds(20, 355, 100, 20);

        lblNumeroCpf.setBounds(20, 380, 60, 20);
        edtNumeroCpf.setBounds(85, 380, 200, 25);

        lblPis.setBounds(310, 355, 100, 20);

        lblNumeroPis.setBounds(310, 380, 60, 20);
        edtNumeroPis.setBounds(375, 380, 180, 25);

        lblDataCadastroPis.setBounds(570, 380, 100, 20);
        edtDataCadastroPis.setBounds(675, 380, 100, 25);

        lblTituloDeEleitor.setBounds(20, 415, 150, 20);

        lblNumeroTituloDeEleitor.setBounds(20, 440, 60, 20);
        edtNumeroTituloDeEleitor.setBounds(85, 440, 180, 25);

        lblZona.setBounds(280, 440, 40, 20);
        edtZona.setBounds(325, 440, 50, 25);

        lblSecao.setBounds(390, 440, 50, 20);
        edtSecao.setBounds(445, 440, 60, 25);

        lblRic.setBounds(20, 475, 100, 20);

        lblNumeroRic.setBounds(20, 500, 60, 20);
        edtNumeroRic.setBounds(85, 500, 180, 25);

        lblOrgaoExpedidorRic.setBounds(285, 500, 100, 20);
        edtOrgaoExpedidorRic.setBounds(390, 500, 100, 25);

        lblDataExpedicaoRic.setBounds(500, 500, 100, 20);
        edtDataExpedicaoRic.setBounds(605, 500, 100, 25);

    }

}
