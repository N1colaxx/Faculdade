package TesteLayouts;

import javax.swing.*;
import java.awt.*;

public class MixedDemo extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MixedDemo().setVisible(true));
    }

    public MixedDemo() {
        setTitle("Layout Misto (Flow + GridBag + Box)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 320);
        setLocationRelativeTo(null);

        // 1) Root com BorderLayout para dividir a tela em regiões
        JPanel root = new JPanel(new BorderLayout(10, 10));
        setContentPane(root);

        // 2) Cabeçalho (NORTH) com FlowLayout para título e “breadcrumbs”
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel titulo = new JLabel("Cadastro de Usuário");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 18f));
        header.add(titulo);
        header.add(new JLabel(" / Configurações / Permissões")); // breadcrumb ilustrativo
        root.add(header, BorderLayout.NORTH);

        // 3) Formulário principal (CENTER) com GridBagLayout
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // linha 1
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        JTextField txtNome = new JTextField(22);
        form.add(txtNome, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 2
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        JTextField txtEmail = new JTextField(22);
        form.add(txtEmail, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 3
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        JPasswordField txtSenha = new JPasswordField(22);
        form.add(txtSenha, gbc);
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        // linha 4: checkboxes (num painel com FlowLayout para ir “fluindo” lado a lado)
        JPanel flags = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        flags.add(new JCheckBox("Ativo", true));
        flags.add(new JCheckBox("Administrador"));
        flags.add(new JCheckBox("Receber Newsletter"));
        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        form.add(flags, gbc);

        // dica (ocupa duas colunas)
        JTextArea dica = new JTextArea(
                "Misto: BorderLayout divide regiões; GridBag alinha o formulário; Flow e Box organizam grupos.\n" +
                "Na prática, compor layouts é o caminho mais limpo e escalável.");
        dica.setOpaque(false); dica.setEditable(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
        form.add(dica, gbc);
        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;

        root.add(form, BorderLayout.CENTER);

        // 4) Barra de ações (SOUTH) com BoxLayout horizontal
        JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));
        actions.setBorder(BorderFactory.createEmptyBorder(6, 10, 10, 10));
        actions.add(Box.createHorizontalGlue());         // empurra botões para a direita
        actions.add(new JButton("Salvar"));
        actions.add(Box.createRigidArea(new Dimension(8, 0)));
        actions.add(new JButton("Cancelar"));
        actions.add(Box.createRigidArea(new Dimension(8, 0)));
        actions.add(new JButton("Limpar"));
        root.add(actions, BorderLayout.SOUTH);
    }
}
