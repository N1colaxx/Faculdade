package testes;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class UIHelper {
    
    // Criar JScrollPane padronizado
    public static JScrollPane criarScrollPane(JComponent inner) {
        JScrollPane sp = new JScrollPane(inner);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.setBorder(BorderFactory.createEmptyBorder()); // sem borda dupla
        return sp;
    }
    
    // Criar bloco com título e número de linhas
    public static JPanel criarBloco(String titulo, int linhas) {
        JPanel p = new JPanel(new GridLayout(linhas, 1, 6, 6));
        p.setBorder(BorderFactory.createTitledBorder(titulo));
        return p;
    }
    
    // Criar linha com rótulo e campo
    public static JPanel criarLinha(String rotulo, JComponent campo) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        p.add(new JLabel(rotulo));
        p.add(campo);
        return p;
    }
    
    // Criar linha dupla
    public static JPanel criarLinhaDupla(String r1, JComponent c1, String r2, JComponent c2) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        p.add(new JLabel(r1));
        p.add(c1);
        p.add(new JLabel(r2));
        p.add(c2);
        return p;
    }
    
    // Criar linha tripla
    public static JPanel criarLinhaTripla(String r1, JComponent c1, String r2, JComponent c2, String r3, JComponent c3) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        p.add(new JLabel(r1));
        p.add(c1);
        p.add(new JLabel(r2));
        p.add(c2);
        p.add(new JLabel(r3));
        p.add(c3);
        return p;
    }
    
    // Criar linha de checkboxes
    public static JPanel criarLinhaCheckbox(JCheckBox a, JCheckBox b) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.add(a);
        p.add(b);
        return p;
    }
    
    // Criar campo com máscara
    public static JFormattedTextField criarCampoMascara(String padrao) {
        try {
            MaskFormatter mf = new MaskFormatter(padrao);
            mf.setPlaceholderCharacter('_');
            return new JFormattedTextField(mf);
        } catch (Exception e) {
            return new JFormattedTextField();
        }
    }
    
    // Criar combo UF
    public static JComboBox<String> criarComboUF() {
        return new JComboBox<>(new String[]{
                "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MG","MS","MT",
                "PA","PB","PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"
        });
    }
    
    // Criar bloco sem título (apenas com borda)
    public static JPanel criarBlocoSemTitulo(int linhas) {
        JPanel p = new JPanel(new GridLayout(linhas, 1, 6, 6));
        p.setBorder(new TitledBorder(""));
        return p;
    }
}