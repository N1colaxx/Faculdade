package t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class UIHelper {

    // ======= Padronização de tamanhos =======
    public static final Dimension DIM_LABEL = new Dimension(140, 28);
    public static final Dimension DIM_FIELD = new Dimension(260, 28);

    // Criar coluna central (BoxLayout + padding)
    public static JPanel colunaCentral() {
        JPanel col = new JPanel();
        col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));
        col.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        return col;
    }

    // Criar título
    public static JLabel titulo(String texto) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 22));
        l.setForeground(new Color(30, 30, 120));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    // Criar linha de formulário: label + campo
    public static JPanel formRow(String rotulo, JComponent campo) {
        JLabel lbl = new JLabel(rotulo);
        lbl.setPreferredSize(DIM_LABEL);
        lbl.setMinimumSize(DIM_LABEL);
        lbl.setMaximumSize(DIM_LABEL);

        padronizarCampo(campo);

        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 2));
        row.add(lbl);
        row.add(campo);
        row.setAlignmentX(Component.CENTER_ALIGNMENT);
        return row;
    }

    // Padronizar campo de entrada
    public static void padronizarCampo(JComponent c) {
        c.setPreferredSize(DIM_FIELD);
        c.setMinimumSize(DIM_FIELD);
        c.setMaximumSize(DIM_FIELD);
    }

    // Criar barra de botões
    public static JPanel barraBotoes(JButton... botoes) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        for (JButton b : botoes) {
            panel.add(b);
        }
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // Estilizar botão verde (primário)
    public static void estilizarBotaoPrimario(JButton b) {
        b.setBackground(new Color(0, 150, 0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }

    // Estilizar botão vermelho (perigo)
    public static void estilizarBotaoPerigo(JButton b) {
        b.setBackground(new Color(180, 0, 0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }

    // Criar JScrollPane padronizado
    public static JScrollPane criarScrollPane(JComponent inner) {
        JScrollPane sp = new JScrollPane(inner);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.setBorder(BorderFactory.createEmptyBorder()); // sem borda dupla
        return sp;
    }
}
