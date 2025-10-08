package TesteLayouts;
import javax.swing.*;
import java.awt.*;

public class BoxDemo extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BoxDemo().setVisible(true));
    }

    public BoxDemo() {
        setTitle("BoxLayout Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 260);
        setLocationRelativeTo(null);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS)); // empilha verticalmente

        JLabel titulo = new JLabel("Formulário (BoxLayout - Y_AXIS)");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT); // gruda à esquerda
        root.add(titulo);
        root.add(Box.createRigidArea(new Dimension(0, 8))); // espaçamento vertical

        // para alinhar rótulos, usamos largura fixa
        Dimension tamRotulo = new Dimension(80, 24);

        // função util para criar uma “linha” horizontal com rótulo + campo
        root.add(linha(tamRotulo, "Nome:", new JTextField(20)));
        root.add(Box.createRigidArea(new Dimension(0, 6)));
        root.add(linha(tamRotulo, "E-mail:", new JTextField(20)));
        root.add(Box.createRigidArea(new Dimension(0, 6)));
        root.add(linha(tamRotulo, "Senha:", new JPasswordField(20)));
        root.add(Box.createRigidArea(new Dimension(0, 10)));

        // linha de botões (horizontal)
        JPanel linhaBotoes = new JPanel();
        linhaBotoes.setLayout(new BoxLayout(linhaBotoes, BoxLayout.X_AXIS));
        linhaBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        linhaBotoes.add(new JButton("Salvar"));
        linhaBotoes.add(Box.createRigidArea(new Dimension(6, 0)));
        linhaBotoes.add(new JButton("Cancelar"));
        linhaBotoes.add(Box.createRigidArea(new Dimension(6, 0)));
        linhaBotoes.add(new JButton("Limpar"));
        root.add(linhaBotoes);

        // dica
        root.add(Box.createRigidArea(new Dimension(0, 10)));
        JTextArea dica = new JTextArea(
                "BoxLayout: empilha (Y) ou alinha em linha (X); com linhas auxiliares, dá bom acabamento.");
        dica.setOpaque(false);
        dica.setEditable(false);
        dica.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(dica);

        setContentPane(root);
    }

    private JPanel linha(Dimension tamRotulo, String rotulo, JComponent campo) {
        // cria uma linha horizontal com rótulo de largura fixa e um campo ao lado
        JPanel linha = new JPanel();
        linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lbl = new JLabel(rotulo);
        lbl.setPreferredSize(tamRotulo); // padroniza largura do rótulo para alinhar as colunas
        linha.add(lbl);
        linha.add(Box.createRigidArea(new Dimension(6, 0))); // espaçamento entre rótulo e campo
        linha.add(campo);
        return linha;
    }
}
