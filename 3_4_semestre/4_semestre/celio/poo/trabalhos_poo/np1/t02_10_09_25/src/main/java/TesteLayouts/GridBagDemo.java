package TesteLayouts;
import javax.swing.*;
import java.awt.*;

public class GridBagDemo extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GridBagDemo().setVisible(true));
    }

    public GridBagDemo() {
        setTitle("GridBagLayout Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 260);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new GridBagLayout()); // usa grid flexível
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8); // margens internas
        gbc.anchor = GridBagConstraints.WEST; // alinha conteúdo à esquerda

        // título (ocupa 2 colunas e estica na horizontal)
        JLabel titulo = new JLabel("Formulário (GridBagLayout)");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // pode esticar
        gbc.weightx = 1;                           // ocupa largura disponível
        root.add(titulo, gbc);

        // volta defaults
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        // linha 1: Nome
        gbc.gridx = 0; gbc.gridy = 1;
        root.add(new JLabel("Nome:"), gbc);

        JTextField txtNome = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // campo pode esticar
        gbc.weightx = 1;
        root.add(txtNome, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 2: E-mail
        gbc.gridx = 0; gbc.gridy = 2;
        root.add(new JLabel("E-mail:"), gbc);

        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        root.add(txtEmail, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 3: Senha
        gbc.gridx = 0; gbc.gridy = 3;
        root.add(new JLabel("Senha:"), gbc);

        JPasswordField txtSenha = new JPasswordField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        root.add(txtSenha, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 4: botões agrupados com FlowLayout (layout aninhado)
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Cancelar"));
        botoes.add(new JButton("Limpar"));
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        root.add(botoes, gbc);

        // linha 5: dica (ocupa 2 colunas)
        JTextArea dica = new JTextArea(
                "GridBagLayout: controla linha/coluna, larguras (gridwidth), fill/anchor e pesos.\n" +
                "Mais verboso, porém o mais flexível.");
        dica.setOpaque(false); dica.setEditable(false);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        root.add(dica, gbc);

        setContentPane(root);
    }
}
