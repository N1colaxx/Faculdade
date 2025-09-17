package view;

import controller.VendaController;
// import controller.ProdutoController; // se quiser buscar produto por código

import model.VendaModel;
import model.VendaItemModel;
import model.VendaPagtoModel;
// import model.ProdutoModel;
import util.VendaItemTableModel;
import util.VendaPagtoTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VendaView extends JPanel {

    // Botões (cabeçalho)
    private JButton btnPrimeiro, btnAnterior, btnProximo, btnUltimo;
    private JButton btnNovo, btnAlterar, btnExcluir, btnGravar;

    // Abas / painéis
    private JPanel paneCabecario;
    private JPanel paneCentro;
    private JTabbedPane tabs;

    // ====== ABA DADOS ======
    private JPanel tabDados;
    private JLabel lblVdaCodigo, lblUsuCodigo, lblCliCodigo, lblData, lblObs;
    private JTextField edtVdaCodigo, edtUsuCodigo, edtCliCodigo, edtData;
    private JTextArea edtObs;
    private JLabel lblValor, lblDesc, lblTotal;
    private JTextField edtValor, edtDesc, edtTotal;

    // ====== ABA ITENS ======
    private JPanel tabItens, paneItemEditor, paneItemTabela;
    private JLabel lblProCod, lblProNome, lblUn, lblQtde, lblPreco, lblDesconto, lblSubTotal;
    private JTextField edtProCod, edtProNome, edtUn, edtQtde, edtPreco, edtDescItem, edtSubTotal; // <-- edtDescItem declarado
    private JButton btnAddItem, btnUpdItem, btnDelItem, btnLimpaItem;
    private JTable tabItensGrid;
    private VendaItemTableModel itensModel;

    // ====== ABA PAGAMENTOS ======
    private JPanel tabPgtos, panePgEditor, panePgTabela;
    private JLabel lblFpgCod, lblFpgNome, lblPgValor;
    private JTextField edtFpgCod, edtPgValor;
    private JComboBox<String> cbFpgNome; // <-- trocado para combo
    private JButton btnAddPg, btnUpdPg, btnDelPg, btnLimpaPg;
    private JTable tabPgtosGrid;
    private VendaPagtoTableModel pgtosModel;

    // ====== ABA CONSULTA ======
    private JPanel tabConsulta, paneConsultaDados, paneConsultaTabela;
    private JLabel lblFiltroCodIni, lblATxt, lblFiltroCodFim;
    private JTextField edtFiltroCodIni, edtFiltroCodFim;
    private JButton btnConsultar, btnLimpar;
    private JTable tabelaConsulta; // simplificado (cabeçalho) -> aquii fica os clientes
    private JTable tabelaItensCunsulta; // aqui se vc selecioanr 1 cliente na tabela (tabelaConsulta) mostra os produstos aqui
    private JScrollPane scrollConsulta;
    private javax.swing.table.DefaultTableModel consultaModel;

    // Estado
    private String operacao = "";
    private ArrayList<VendaItemModel> listaItens = new ArrayList<>();
    private ArrayList<VendaPagtoModel> listaPgtos = new ArrayList<>();
    private ArrayList<VendaModel> listaVendas = new ArrayList<>();

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public VendaView() {
        setLayout(null);
        setBackground(Color.BLACK);

        instanciar();
        adicionar();
        posicionar();
    }

    //  esse metodo é para: instanciar todos os componentes da UI
    private void instanciar() {
        // Cabeçalho
        btnPrimeiro = new JButton("Primeiro");
        btnAnterior = new JButton("Anterior");
        btnProximo  = new JButton("Próximo");
        btnUltimo   = new JButton("Último");
        btnNovo     = new JButton("Novo");
        btnAlterar  = new JButton("Alterar");
        btnExcluir  = new JButton("Excluir");
        btnGravar   = new JButton("Gravar");

        paneCabecario = new JPanel(null);
        paneCentro    = new JPanel(null);
        paneCentro.setBackground(new Color(245,250,255));
        tabs = new JTabbedPane();

        // ===== Dados =====
        tabDados = new JPanel(null);
        lblVdaCodigo = new JLabel("Código:");
        edtVdaCodigo = new JTextField();
        lblUsuCodigo = new JLabel("Usuário:");
        edtUsuCodigo = new JTextField();
        lblCliCodigo = new JLabel("Cliente:");
        edtCliCodigo = new JTextField();
        lblData = new JLabel("Data (yyyy-MM-dd):");
        edtData = new JTextField(LocalDate.now().toString());
        lblObs = new JLabel("Obs.:");
        edtObs = new JTextArea(); edtObs.setLineWrap(true); edtObs.setWrapStyleWord(true);

        lblValor = new JLabel("Valor:");
        edtValor = new JTextField(); edtValor.setEditable(false);
        lblDesc = new JLabel("Desconto:");
        edtDesc = new JTextField(); edtDesc.setEditable(false);
        lblTotal = new JLabel("Total:");
        edtTotal = new JTextField(); edtTotal.setEditable(false);

        // ===== Itens =====
        tabItens = new JPanel(null);
        paneItemEditor = new JPanel(null);
        paneItemTabela = new JPanel(null);

        lblProCod = new JLabel("Prod Cód:");
        edtProCod = new JTextField();
        lblProNome = new JLabel("Nome:");
        edtProNome = new JTextField(); edtProNome.setEditable(false);
        lblUn = new JLabel("Und:");
        edtUn = new JTextField(); edtUn.setEditable(false);
        lblQtde = new JLabel("Qtde:");
        edtQtde = new JTextField();
        lblPreco = new JLabel("Preço:");
        edtPreco = new JTextField(); edtPreco.setEditable(false); // <-- não editável (vem do produto)
        lblDesconto = new JLabel("Desc:");
        edtDescItem = new JTextField();
        lblSubTotal = new JLabel("Subtotal:");
        edtSubTotal = new JTextField(); edtSubTotal.setEditable(false);

        btnAddItem = new JButton("Adicionar");
        btnUpdItem = new JButton("Atualizar");
        btnDelItem = new JButton("Remover");
        btnLimpaItem = new JButton("Limpar");

        itensModel = new util.VendaItemTableModel(listaItens);
        tabItensGrid = new JTable(itensModel);
        tabItensGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ===== Pagamentos =====
        tabPgtos = new JPanel(null);
        panePgEditor = new JPanel(null);
        panePgTabela = new JPanel(null);

        lblFpgCod = new JLabel("FPG Cód:");
        edtFpgCod = new JTextField();
        lblFpgNome = new JLabel("Forma:");
        cbFpgNome = new JComboBox<>(); // <-- combo para formas
        lblPgValor = new JLabel("Valor:");
        edtPgValor = new JTextField();

        btnAddPg = new JButton("Adicionar");
        btnUpdPg = new JButton("Atualizar");
        btnDelPg = new JButton("Remover");
        btnLimpaPg = new JButton("Limpar");

        pgtosModel = new util.VendaPagtoTableModel(listaPgtos);
        tabPgtosGrid = new JTable(pgtosModel);
        tabPgtosGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ===== Consulta =====
        tabConsulta = new JPanel(null);
        paneConsultaDados = new JPanel(null);
        paneConsultaTabela = new JPanel(null);

        lblFiltroCodIni = new JLabel("Cód de");
        edtFiltroCodIni = new JTextField();
        lblATxt = new JLabel("à");
        lblFiltroCodFim = new JLabel("Cód até");
        edtFiltroCodFim = new JTextField();
        btnConsultar = new JButton("Consulta");
        btnLimpar = new JButton("Limpa");

        consultaModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Código", "Usuário", "Cliente", "Data", "Valor", "Desc", "Total"}
        ){
            public boolean isCellEditable(int r, int c){ return false; }
        };
        tabelaConsulta = new JTable(consultaModel);
        scrollConsulta = new JScrollPane(tabelaConsulta);
    }

    //  esse metodo é para: adicionar os componentes nos painéis/abas
    private void adicionar() {
        // Cabeçalho
        paneCabecario.add(btnPrimeiro); paneCabecario.add(btnAnterior);
        paneCabecario.add(btnProximo);  paneCabecario.add(btnUltimo);
        paneCabecario.add(btnNovo);     paneCabecario.add(btnAlterar);
        paneCabecario.add(btnExcluir);  paneCabecario.add(btnGravar);
        add(paneCabecario);

        add(paneCentro);
        paneCentro.add(tabs);

        // Aba Dados
        tabDados.add(lblVdaCodigo); tabDados.add(edtVdaCodigo);
        tabDados.add(lblUsuCodigo); tabDados.add(edtUsuCodigo);
        tabDados.add(lblCliCodigo); tabDados.add(edtCliCodigo);
        tabDados.add(lblData);      tabDados.add(edtData);
        tabDados.add(lblObs);       tabDados.add(edtObs);
        tabDados.add(lblValor);     tabDados.add(edtValor);
        tabDados.add(lblDesc);      tabDados.add(edtDesc);
        tabDados.add(lblTotal);     tabDados.add(edtTotal);
        tabs.addTab("Dados", tabDados);

        // Aba Itens
        tabItens.add(paneItemEditor); tabItens.add(paneItemTabela);
        paneItemEditor.add(lblProCod); paneItemEditor.add(edtProCod);
        paneItemEditor.add(lblProNome); paneItemEditor.add(edtProNome);
        paneItemEditor.add(lblUn); paneItemEditor.add(edtUn);
        paneItemEditor.add(lblQtde); paneItemEditor.add(edtQtde);
        paneItemEditor.add(lblPreco); paneItemEditor.add(edtPreco);
        paneItemEditor.add(lblDesconto); paneItemEditor.add(edtDescItem);
        paneItemEditor.add(lblSubTotal); paneItemEditor.add(edtSubTotal);
        paneItemEditor.add(btnAddItem); paneItemEditor.add(btnUpdItem);
        paneItemEditor.add(btnDelItem); paneItemEditor.add(btnLimpaItem);

        paneItemTabela.add(new JScrollPane(tabItensGrid));
        tabs.addTab("Itens (Produtos)", tabItens);

        // Aba Pagamentos
        tabPgtos.add(panePgEditor); tabPgtos.add(panePgTabela);
        panePgEditor.add(lblFpgCod); panePgEditor.add(edtFpgCod);
        panePgEditor.add(lblFpgNome); panePgEditor.add(cbFpgNome); // <-- combo no lugar do textfield
        panePgEditor.add(lblPgValor); panePgEditor.add(edtPgValor);
        panePgEditor.add(btnAddPg); panePgEditor.add(btnUpdPg);
        panePgEditor.add(btnDelPg); panePgEditor.add(btnLimpaPg);

        panePgTabela.add(new JScrollPane(tabPgtosGrid));
        tabs.addTab("Pagamentos", tabPgtos);

        // Aba Consulta
        tabConsulta.add(paneConsultaDados); tabConsulta.add(paneConsultaTabela);
        paneConsultaDados.add(lblFiltroCodIni); paneConsultaDados.add(edtFiltroCodIni);
        paneConsultaDados.add(lblATxt);
        paneConsultaDados.add(lblFiltroCodFim); paneConsultaDados.add(edtFiltroCodFim);
        paneConsultaDados.add(btnConsultar); paneConsultaDados.add(btnLimpar);
        paneConsultaTabela.add(scrollConsulta);
        tabs.addTab("Consulta", tabConsulta);
    }

    //  esse metodo é para: posicionar (setBounds) todos os componentes
    private void posicionar() {
        // Painéis base
        paneCabecario.setBounds(10, 10, 970, 40);
        paneCabecario.setBackground(Color.LIGHT_GRAY);
        paneCentro.setBounds(10, 60, 970, 730);
        tabs.setBounds(10, 10, 950, 710);

        // Botões cabeçalho
        btnPrimeiro.setBounds(  0, 7, 90, 25);
        btnAnterior.setBounds( 95, 7, 90, 25);
        btnProximo .setBounds(190, 7, 90, 25);
        btnUltimo  .setBounds(285, 7, 90, 25);
        btnNovo    .setBounds(430, 7, 90, 25);
        btnAlterar .setBounds(520, 7, 90, 25);
        btnExcluir .setBounds(610, 7, 90, 25);
        btnGravar  .setBounds(835, 7, 90, 25);

        // ===== Dados =====
        lblVdaCodigo.setBounds(10, 15, 60, 25);  edtVdaCodigo.setBounds(75, 15, 100, 25);
        lblUsuCodigo.setBounds(190, 15, 60, 25); edtUsuCodigo.setBounds(255, 15, 80, 25);
        lblCliCodigo.setBounds(345, 15, 60, 25); edtCliCodigo.setBounds(410, 15, 80, 25);
        lblData.setBounds(500, 15, 140, 25);     edtData.setBounds(640, 15, 120, 25);

        lblObs.setBounds(10, 55, 40, 25);
        edtObs.setBounds(55, 55, 705, 90);

        lblValor.setBounds(10, 160, 60, 25);    edtValor.setBounds(70, 160, 120, 25);
        lblDesc.setBounds(200, 160, 70, 25);    edtDesc.setBounds(270, 160, 120, 25);
        lblTotal.setBounds(400, 160, 60, 25);   edtTotal.setBounds(460, 160, 120, 25);

        // ===== Itens =====
        tabItens.setBounds(0,0,950,710);
        paneItemEditor.setBounds(10, 10, 925, 120);
        paneItemTabela.setBounds(10, 140, 925, 520);

        lblProCod.setBounds(10, 10, 80, 25);    edtProCod.setBounds(90, 10, 80, 25);
        lblProNome.setBounds(180, 10, 45, 25);  edtProNome.setBounds(230, 10, 260, 25);
        lblUn.setBounds(500, 10, 35, 25);       edtUn.setBounds(540, 10, 50, 25);

        lblQtde.setBounds(10, 45, 50, 25);      edtQtde.setBounds(60, 45, 80, 25);
        lblPreco.setBounds(150, 45, 45, 25);    edtPreco.setBounds(200, 45, 80, 25);
        lblDesconto.setBounds(290, 45, 45, 25); edtDescItem.setBounds(340, 45, 80, 25);
        lblSubTotal.setBounds(430, 45, 60, 25); edtSubTotal.setBounds(495, 45, 95, 25);

        btnAddItem.setBounds(610, 45, 100, 25);
        btnUpdItem.setBounds(715, 45, 100, 25);
        btnDelItem.setBounds(820, 45, 90, 25);
        btnLimpaItem.setBounds(820, 80, 90, 25);

        JScrollPane spItens = (JScrollPane) paneItemTabela.getComponent(0);
        spItens.setBounds(0, 0, 925, 520);

        // ===== Pagamentos =====
        tabPgtos.setBounds(0,0,950,710);
        panePgEditor.setBounds(10, 10, 925, 90);
        panePgTabela.setBounds(10, 110, 925, 550);

        lblFpgCod.setBounds(10, 10, 60, 25);    edtFpgCod.setBounds(75, 10, 80, 25);
        lblFpgNome.setBounds(165, 10, 50, 25);  cbFpgNome.setBounds(220, 10, 250, 25); // <-- bounds do combo
        lblPgValor.setBounds(480, 10, 40, 25);  edtPgValor.setBounds(525, 10, 100, 25);

        btnAddPg.setBounds(640, 10, 90, 25);
        btnUpdPg.setBounds(735, 10, 90, 25);
        btnDelPg.setBounds(830, 10, 90, 25);
        btnLimpaPg.setBounds(830, 45, 90, 25);

        JScrollPane spPg = (JScrollPane) panePgTabela.getComponent(0);
        spPg.setBounds(0, 0, 925, 550);

        // ===== Consulta =====
        tabConsulta.setBounds(0,0,950,710);
        paneConsultaDados.setBounds(10, 10, 925, 60);
        paneConsultaTabela.setBounds(10, 80, 925, 580);

        lblFiltroCodIni.setBounds(10, 10, 60, 25);    edtFiltroCodIni.setBounds(70, 10, 80, 25);
        lblATxt.setBounds(160, 10, 10, 25);
        lblFiltroCodFim.setBounds(180, 10, 60, 25);   edtFiltroCodFim.setBounds(240, 10, 80, 25);

        btnConsultar.setBounds(340, 10, 100, 25);
        btnLimpar.setBounds(445, 10, 100, 25);

        scrollConsulta.setBounds(0, 0, 925, 580);
    }

}
