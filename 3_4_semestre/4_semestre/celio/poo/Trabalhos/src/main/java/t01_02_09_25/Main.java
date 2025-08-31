package t01_02_09_25;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    
    // Campos principais para validação
    private ComponentesValidacao componentesValidacao;
    
    // Abas
    private AbaPrincipal abaPrincipal;
    private AbaDocumentacao abaDocumentacao;
    private AbaContrato abaContrato;
    private AbaOperacional abaOperacional;
    
    public Main() {
        super("Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configurar Look and Feel
        configurarLookAndFeel();
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Configurar layout
        configurarLayout();
        
        // Configurar janela
        configurarJanela();
    }
    
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
    
    private void inicializarComponentes() {
        // Inicializar abas
        abaPrincipal = new AbaPrincipal();
        abaDocumentacao = new AbaDocumentacao();
        abaContrato = new AbaContrato();
        abaOperacional = new AbaOperacional();
        
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        // Menu
        setJMenuBar(criarMenuBar());
        
        // Topo - barra de ações
        add(criarBarraAcoes(), BorderLayout.NORTH);
        
        // Centro - cabeçalho + abas
        add(criarCentro(), BorderLayout.CENTER);
    }
    
    private JMenuBar criarMenuBar() {
        JMenuBar bar = new JMenuBar();
        
        JMenu mArq = new JMenu("Arquivo");
        JMenuItem miSair = new JMenuItem("Sair");
        miSair.addActionListener(e -> dispose());
        mArq.add(miSair);
        
        JMenu mAjuda = new JMenu("Ajuda");
        JMenuItem miSobre = new JMenuItem("Sobre");
        miSobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Cadastro de Funcionários (Swing)\nVersão responsiva (scroll + redimensionamento de foto).",
                "Sobre", JOptionPane.INFORMATION_MESSAGE));
        mAjuda.add(miSobre);
        
        bar.add(mArq);
        bar.add(mAjuda);
        return bar;
    }
    
    private JPanel criarBarraAcoes() {
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        
        topo.add(new JLabel("Status:"));
        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Aberta","Pendente","Fechada"});
        topo.add(cmbStatus);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> validar());
        topo.add(btnSalvar);
        
        topo.add(new JButton("Concluir"));
        topo.add(new JButton("Excluir"));
        topo.add(new JButton("Ocorrência"));
        topo.add(new JLabel("Situação:"));
        
        JLabel lblAtivo = new JLabel("Ativo");
        lblAtivo.setForeground(new Color(22,130,46));
        topo.add(lblAtivo);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        topo.add(Box.createHorizontalStrut(20));
        topo.add(btnFechar);
        
        return topo;
    }
    
    private JPanel criarCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        
        // Cabeçalho (CPF / Nome) - vem da AbaPrincipal
        centro.add(abaPrincipal.criarCabecalho(), BorderLayout.NORTH);
        
        // Abas com scroll
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Principal", UIHelper.criarScrollPane(abaPrincipal.criarAba()));
        tabs.addTab("Documentação", UIHelper.criarScrollPane(abaDocumentacao.criarAba()));
        tabs.addTab("Contrato", UIHelper.criarScrollPane(abaContrato.criarAba()));
        tabs.addTab("Operacional", UIHelper.criarScrollPane(abaOperacional.criarAba()));
        
        centro.add(tabs, BorderLayout.CENTER);
        return centro;
    }
    
    private void configurarJanela() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension(900, 600));
        setPreferredSize(new Dimension((int)(scr.width * 0.9), (int)(scr.height * 0.9)));
        pack();
        setLocationRelativeTo(null);
    }
    
    private void validar() {
        ValidadorDados validador = new ValidadorDados();
        
        // Configurar componentes para validação
        componentesValidacao = new ComponentesValidacao(
            abaPrincipal.getTxtCPF(),
            abaPrincipal.getTxtNome(),
            abaPrincipal.getTxtDataNasc(),
            abaPrincipal.getTxtEmail(),
            abaContrato.getTxtSalario()
        );
        
        String resultado = validador.validarTodos(componentesValidacao);
        
        if (!resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, resultado, "Erros de validação", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Dados validados com sucesso! (simulação de salvar)",
                    "OK", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}