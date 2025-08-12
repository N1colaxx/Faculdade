package aula01; // Pacote onde a classe está localizada

// Importa as classes necessárias do Swing, AWT e eventos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Classe principal que estende JFrame (janela principal do Swing)
public class TelaSwingDiversa extends JFrame {

    // Construtor da janela
    public TelaSwingDiversa() {
        // Define título da janela
        setTitle("Componentes Swing Diversos");

        // Define o tamanho inicial (largura, altura)
        setSize(800, 600);

        // Define que o programa será encerrado ao fechar a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        // Cria um painel com abas (JTabbedPane)
        JTabbedPane abas = new JTabbedPane();

        // Adiciona abas chamando métodos que criam cada painel
        abas.addTab("Formulário", criarPainelFormulario());
        abas.addTab("Tabela e Lista", criarPainelTabela());
        abas.addTab("Outros", criarPainelOutros());

        // Define a barra de menus
        setJMenuBar(criarMenu());

        // Adiciona o painel de abas na janela
        add(abas);

        // Torna a janela visível
        setVisible(true);
    }

    // Cria painel com campos de formulário
    private JPanel criarPainelFormulario() {
        // Painel com layout de grade (10 linhas, 2 colunas, espaçamento 10px)
        JPanel painel = new JPanel(new GridLayout(10, 2, 10, 10));

        // Campo de texto simples
        painel.add(new JLabel("Nome:"));
        painel.add(new JTextField());

        // Campo de texto multi-linha com barra de rolagem
        painel.add(new JLabel("Descrição:"));
        painel.add(new JScrollPane(new JTextArea(3, 20)));

        // Grupo de botões de rádio (sexo)
        painel.add(new JLabel("Sexo:"));
        JPanel sexoPanel = new JPanel(); // Painel para agrupar os botões
        ButtonGroup grupoSexo = new ButtonGroup(); // Garante seleção única
        JRadioButton masc = new JRadioButton("Masculino");
        JRadioButton fem = new JRadioButton("Feminino");
        grupoSexo.add(masc);
        grupoSexo.add(fem);
        sexoPanel.add(masc);
        sexoPanel.add(fem);
        painel.add(sexoPanel);

        // Checkboxes para múltiplas escolhas
        painel.add(new JLabel("Linguagens favoritas:"));
        painel.add(new JCheckBox("Java"));
        painel.add(new JLabel()); // Espaço vazio na grade
        painel.add(new JCheckBox("Python"));
        painel.add(new JLabel());
        painel.add(new JCheckBox("C++"));

        // ComboBox (lista suspensa)
        painel.add(new JLabel("Curso:"));
        String[] cursos = {"ADS", "Ciência da Computação", "Eng. Software"};
        painel.add(new JComboBox<>(cursos));

        // Spinner para selecionar números
        painel.add(new JLabel("Idade:"));
        painel.add(new JSpinner(new SpinnerNumberModel(18, 0, 100, 1)));

        // Botão com ação
        painel.add(new JLabel("Enviar"));
        JButton btn = new JButton("Salvar");
        btn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Salvo com sucesso!"));
        painel.add(btn);

        return painel;
    }

    // Cria painel com tabela e lista
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());

        // Dados e colunas para a tabela
        String[] colunas = {"ID", "Nome", "Idade"};
        Object[][] dados = {
            {1, "João", 22},
            {2, "Maria", 24},
            {3, "Pedro", 19}
        };

        // Cria a tabela
        JTable tabela = new JTable(dados, colunas);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Lista simples de strings
        String[] nomes = {"Java", "Python", "C#", "JavaScript", "Kotlin"};
        JList<String> lista = new JList<>(nomes);
        painel.add(new JScrollPane(lista), BorderLayout.EAST);

        return painel;
    }

    // Cria painel com outros componentes
    private JPanel criarPainelOutros() {
        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Slider (controle deslizante)
        JSlider slider = new JSlider(0, 100, 50);
        painel.add(new JLabel("Volume:"));
        painel.add(slider);

        // Barra de progresso indeterminada
        JProgressBar progresso = new JProgressBar();
        progresso.setIndeterminate(true);
        painel.add(new JLabel("Progresso:"));
        painel.add(progresso);

        // SplitPane (divisão ajustável)
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JButton("Esquerda"), new JButton("Direita"));
        painel.add(split);

        return painel;
    }

    // Cria barra de menus
    private JMenuBar criarMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Arquivo com item Sair
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);
        
        // Menu Ajuda com item Sobre
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this, "Feito por Nicolas - NetBeans + Ant"));
        menuAjuda.add(itemSobre);

        // Adiciona menus à barra
        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);

        return menuBar;
    }

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        // Garante que a interface será criada na thread do Swing
        SwingUtilities.invokeLater(() -> new TelaSwingDiversa());
    }
}
