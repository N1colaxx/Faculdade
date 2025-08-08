package aula1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaSwingDiversa extends JFrame {

    public TelaSwingDiversa() {
        setTitle("Componentes Swing Diversos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel Principal com abas
        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Formulário", criarPainelFormulario());
        abas.addTab("Tabela e Lista", criarPainelTabela());
        abas.addTab("Outros", criarPainelOutros());

        setJMenuBar(criarMenu());

        add(abas);
        setVisible(true);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridLayout(10, 2, 10, 10));

        painel.add(new JLabel("Nome:"));
        painel.add(new JTextField());

        painel.add(new JLabel("Descrição:"));
        painel.add(new JScrollPane(new JTextArea(3, 20)));

        painel.add(new JLabel("Sexo:"));
        JPanel sexoPanel = new JPanel();
        ButtonGroup grupoSexo = new ButtonGroup();
        JRadioButton masc = new JRadioButton("Masculino");
        JRadioButton fem = new JRadioButton("Feminino");
        grupoSexo.add(masc);
        grupoSexo.add(fem);
        sexoPanel.add(masc);
        sexoPanel.add(fem);
        painel.add(sexoPanel);

        painel.add(new JLabel("Linguagens favoritas:"));
        painel.add(new JCheckBox("Java"));
        painel.add(new JLabel());
        painel.add(new JCheckBox("Python"));
        painel.add(new JLabel());
        painel.add(new JCheckBox("C++"));

        painel.add(new JLabel("Curso:"));
        String[] cursos = {"ADS", "Ciência da Computação", "Eng. Software"};
        painel.add(new JComboBox<>(cursos));

        painel.add(new JLabel("Idade:"));
        painel.add(new JSpinner(new SpinnerNumberModel(18, 0, 100, 1)));

        painel.add(new JLabel("Enviar"));
        JButton btn = new JButton("Salvar");
        btn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Salvo com sucesso!"));
        painel.add(btn);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Idade"};
        Object[][] dados = {
            {1, "João", 22},
            {2, "Maria", 24},
            {3, "Pedro", 19}
        };
        JTable tabela = new JTable(dados, colunas);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        String[] nomes = {"Java", "Python", "C#", "JavaScript", "Kotlin"};
        JList<String> lista = new JList<>(nomes);
        painel.add(new JScrollPane(lista), BorderLayout.EAST);

        return painel;
    }

    private JPanel criarPainelOutros() {
        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));

        JSlider slider = new JSlider(0, 100, 50);
        painel.add(new JLabel("Volume:"));
        painel.add(slider);

        JProgressBar progresso = new JProgressBar();
        progresso.setIndeterminate(true);
        painel.add(new JLabel("Progresso:"));
        painel.add(progresso);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JButton("Esquerda"), new JButton("Direita"));
        painel.add(split);

        return painel;
    }

    private JMenuBar criarMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this, "Feito por Nicolas - NetBeans + Ant"));
        menuAjuda.add(itemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaSwingDiversa());
    }
}
