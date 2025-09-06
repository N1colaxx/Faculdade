package TesteLayouts;

import javax.swing.*;
import java.awt.*;

public class FlowDemo extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlowDemo().setVisible(true));
    }

    public FlowDemo() {
        setTitle("FlowLayout Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 220);
        setLocationRelativeTo(null);

        // FlowLayout coloca os componentes em sequência (esq→dir) e quebra linha quando não cabe
        JPanel root = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // alinhado à esquerda, gaps 10px

        JLabel titulo = new JLabel("Formulário (FlowLayout)");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        root.add(titulo);

        // Linha lógica 1
        root.add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField(12); // largura sugerida
        root.add(txtNome);

        // Linha lógica 2
        root.add(new JLabel("E-mail:"));
        JTextField txtEmail = new JTextField(15);
        root.add(txtEmail);

        // Linha lógica 3
        root.add(new JLabel("Senha:"));
        JPasswordField txtSenha = new JPasswordField(10);
        root.add(txtSenha);

        // Botões (ficam na sequência; podem “pular” linha se a janela estreitar)
        root.add(new JButton("Salvar"));
        root.add(new JButton("Cancelar"));
        root.add(new JButton("Limpar"));

        // Dica
        JTextArea dica = new JTextArea(
                "FlowLayout: simples, rápido; não alinha colunas automaticamente.");
        dica.setOpaque(false);
        dica.setEditable(false);
        root.add(dica);

        setContentPane(root);
    }
}
