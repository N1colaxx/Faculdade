package testes;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    // ==== Campos da janela ====
    private JMenuBar menuBar;
    private JMenu mArq, mAjuda;
    private JMenuItem miSair, miSobre;

    // Barra de ações (topo)
    private JPanel barraAcoes;
    private JLabel lblStatus, lblSituacao, lblAtivo;
    private JComboBox<String> cmbStatus;
    private JButton btnSalvar, btnConcluir, btnExcluir, btnOcorrencia, btnFechar;

    // Centro
    private JPanel painelCentro;          // container com layout null
    private JPanel painelCabecalho;       // vindo da AbaPrincipal
    private JTabbedPane tabs;             // com as abas
    private JScrollPane spPrincipal, spDoc, spContrato, spOperacional;

    // Abas
    private AbaPrincipal abaPrincipal;
    private AbaDocumentacao abaDocumentacao;
    private AbaContrato abaContrato;
    private AbaOperacional abaOperacional;

    // ===== Construtor =====
    public Main() {
        super("Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        configurarLookAndFeel();

        // Ciclo em 3 passos:
        instanciar();
        posicionar();
        adicionar();

        configurarJanela();
    }

    // ====== 1) INSTANCIAR ======
    private void instanciar() {
        // Menus
        menuBar = new JMenuBar();
        mArq = new JMenu("Arquivo");
        miSair = new JMenuItem("Sair");
        miSair.addActionListener(e -> dispose());
        mArq.add(miSair);

        mAjuda = new JMenu("Ajuda");
        miSobre = new JMenuItem("Sobre");
        miSobre.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Cadastro de Funcionários (Swing)\nVersão com setBounds na casca.",
                "Sobre",
                JOptionPane.INFORMATION_MESSAGE
        ));
        mAjuda.add(miSobre);

        menuBar.add(mArq);
        menuBar.add(mAjuda);
        setJMenuBar(menuBar);

        // Barra de ações
        barraAcoes = new JPanel(null); // próprio null para posicionar filhos aqui
        lblStatus = new JLabel("Status:");
        cmbStatus = new JComboBox<>(new String[]{"Aberta", "Pendente", "Fechada"});
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> validar());
        btnConcluir = new JButton("Concluir");
        btnExcluir = new JButton("Excluir");
        btnOcorrencia = new JButton("Ocorrência");
        lblSituacao = new JLabel("Situação:");
        lblAtivo = new JLabel("Ativo");
        lblAtivo.setForeground(new Color(22, 130, 46));
        btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        // Abas (reaproveitando seu código)
        abaPrincipal = new AbaPrincipal();
        abaDocumentacao = new AbaDocumentacao();
        abaContrato = new AbaContrato();
        abaOperacional = new AbaOperacional();

        painelCabecalho = abaPrincipal.criarCabecalho();

        tabs = new JTabbedPane();
        spPrincipal    = UIHelper.criarScrollPane(abaPrincipal.criarAba());
        spDoc          = UIHelper.criarScrollPane(abaDocumentacao.criarAba());
        spContrato     = UIHelper.criarScrollPane(abaContrato.criarAba());
        spOperacional  = UIHelper.criarScrollPane(abaOperacional.criarAba());

        tabs.addTab("Principal",     spPrincipal);
        tabs.addTab("Documentação",  spDoc);
        tabs.addTab("Contrato",      spContrato);
        tabs.addTab("Operacional",   spOperacional);

        // Container do centro (layout null)
        painelCentro = new JPanel(null);
    }

    // ====== 2) POSICIONAR (setBounds) ======
    private void posicionar() {
        // Layout da janela principal
        getContentPane().setLayout(null);

        // — Barra de ações (altura fixa) —
        // Margens que vou usar como padrão
        int margemX = 10;
        int margemY = 10;

        // Defina uma altura confortável para a barra
        int alturaBarra = 52;
        barraAcoes.setBounds(0, 0, getLarguraInicial(), alturaBarra);

        // Posicionamento interno dos componentes da barraAcoes
        // (coordenadas relativas ao próprio painel barraAcoes)
        int x = margemX;
        int y = 10;
        int h = 28;

        lblStatus.setBounds(x, y, 60, h);
        x += 60 + 6;

        cmbStatus.setBounds(x, y, 120, h);
        x += 120 + 10;

        btnSalvar.setBounds(x, y, 90, h);
        x += 90 + 8;

        btnConcluir.setBounds(x, y, 90, h);
        x += 90 + 8;

        btnExcluir.setBounds(x, y, 90, h);
        x += 90 + 8;

        btnOcorrencia.setBounds(x, y, 110, h);
        x += 110 + 16;

        lblSituacao.setBounds(x, y, 70, h);
        x += 70 + 6;

        lblAtivo.setBounds(x, y, 60, h);

        // Botão "Fechar" à direita
        // Deixo a largura fixa e vou ajustar X no resize (ver windowResized opcional se quiser)
        btnFechar.setBounds(getLarguraInicial() - 100, y, 90, h);

        // — Centro (cabeçalho + abas) —
        int topoCentro = barraAcoes.getY() + barraAcoes.getHeight() + margemY;
        painelCentro.setBounds(0, topoCentro, getLarguraInicial(), getAlturaInicial() - topoCentro);

        // Cabeçalho (altura fixa)
        int alturaCab = 70;
        painelCabecalho.setBounds(margemX, margemY, getLarguraInicial() - 2 * margemX - 16, alturaCab);

        // Abas embaixo do cabeçalho
        int topoTabs = painelCabecalho.getY() + painelCabecalho.getHeight() + 8;
        tabs.setBounds(margemX, topoTabs,
                getLarguraInicial() - 2 * margemX - 16,
                getAlturaInicial() - topoCentro - topoTabs - margemY - 40);
    }

    // ====== 3) ADICIONAR (add) ======
    private void adicionar() {
        // Barra de ações
        barraAcoes.add(lblStatus);
        barraAcoes.add(cmbStatus);
        barraAcoes.add(btnSalvar);
        barraAcoes.add(btnConcluir);
        barraAcoes.add(btnExcluir);
        barraAcoes.add(btnOcorrencia);
        barraAcoes.add(lblSituacao);
        barraAcoes.add(lblAtivo);
        barraAcoes.add(btnFechar);

        // Centro
        painelCentro.add(painelCabecalho);
        painelCentro.add(tabs);

        // Janela
        getContentPane().add(barraAcoes);
        getContentPane().add(painelCentro);
    }

    // ===== Utilitários de tamanho inicial =====
    private int getLarguraInicial() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) (scr.width * 0.9);
    }

    private int getAlturaInicial() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) (scr.height * 0.9);
    }

    // ====== Look and Feel ======
    private void configurarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(i.getName())) {
                    UIManager.setLookAndFeel(i.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    // ====== Janela ======
    private void configurarJanela() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(900, 600));
        setPreferredSize(new Dimension(getLarguraInicial(), getAlturaInicial()));
        pack();
        setLocationRelativeTo(null);
    }

    // ====== Validação (reusa suas classes) ======
    private void validar() {
        ValidadorDados validador = new ValidadorDados();

        ComponentesValidacao componentes = new ComponentesValidacao(
                abaPrincipal.getTxtCPF(),
                abaPrincipal.getTxtNome(),
                abaPrincipal.getTxtDataNasc(),
                abaPrincipal.getTxtEmail(),
                abaContrato.getTxtSalario()
        );

        String resultado = validador.validarTodos(componentes);

        if (!resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, resultado, "Erros de validação", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Dados validados com sucesso! (simulação de salvar)",
                    "OK", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ====== Main ======
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
