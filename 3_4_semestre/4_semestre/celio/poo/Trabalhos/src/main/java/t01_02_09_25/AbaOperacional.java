package t01_02_09_25;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class AbaOperacional {
    
    private JLabel lblFoto;
    private Image fotoOriginal;
    
    public JPanel criarAba() {
        JPanel aba = new JPanel(new GridLayout(1, 2, 8, 8));
        aba.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));
        
        JPanel esquerda = new JPanel(new GridLayout(3, 1, 8, 8));
        esquerda.add(criarCheckboxes1());
        esquerda.add(criarCheckboxes2());
        esquerda.add(criarRacaDeficiencia());
        
        JPanel direita = new JPanel(new GridLayout(4, 1, 8, 8));
        direita.add(criarSindicato());
        direita.add(criarDadosFuncionario());
        direita.add(criarExamesAdmissionais());
        direita.add(criarFoto());
        
        aba.add(esquerda);
        aba.add(direita);
        return aba;
    }
    
    private JPanel criarCheckboxes1() {
        JPanel chk1 = UIHelper.criarBlocoSemTitulo(3);
        chk1.add(UIHelper.criarLinhaCheckbox(new JCheckBox("INSS", true), new JCheckBox("Vale Transporte")));
        chk1.add(UIHelper.criarLinhaCheckbox(new JCheckBox("FGTS", true), new JCheckBox("Vale Refeição", true)));
        chk1.add(UIHelper.criarLinhaCheckbox(new JCheckBox("IRRF", true), new JCheckBox("Plano de Saúde")));
        return chk1;
    }
    
    private JPanel criarCheckboxes2() {
        JPanel chk2 = UIHelper.criarBlocoSemTitulo(3);
        chk2.add(new JCheckBox("Reembolso INSS/IRRF"));
        chk2.add(new JCheckBox("Empregado Doméstico"));
        chk2.add(new JCheckBox("Benefício Previdência - Aposentadoria"));
        return chk2;
    }
    
    private JPanel criarRacaDeficiencia() {
        JPanel racaDef = UIHelper.criarBlocoSemTitulo(2);
        racaDef.add(UIHelper.criarLinha("Raça Cor", new JComboBox<>(new String[]{
                "Branca","Preta","Parda","Amarela","Indígena","Não Informar"})));
        racaDef.add(UIHelper.criarLinha("Tipo de Deficiência", new JComboBox<>(new String[]{
                "0","1","2","3","4","5"})));
        return racaDef;
    }
    
    private JPanel criarSindicato() {
        JPanel sind = UIHelper.criarBloco("Sindicato", 1);
        sind.add(UIHelper.criarLinhaDupla("Sindicato", new JTextField(24),
                "Mês Dissídio", new JComboBox<>(new String[]{
                        "Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho",
                        "Agosto","Setembro","Outubro","Novembro","Dezembro"})));
        return sind;
    }
    
    private JPanel criarDadosFuncionario() {
        JPanel dados = UIHelper.criarBloco("Dados Funcionário", 2);
        dados.add(UIHelper.criarLinha("Forma de Pagamento", new JComboBox<>(new String[]{
                "Crédito em Conta","Débito em Conta","Dinheiro","Cheque"})));
        
        JPanel bancoLinha = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        bancoLinha.add(new JLabel("Banco"));
        bancoLinha.add(new JComboBox<>(new String[]{"Banco do Brasil","Bradesco","Caixa","Itaú","Santander"}));
        bancoLinha.add(new JLabel("Agência"));
        bancoLinha.add(new JTextField(8));
        bancoLinha.add(new JLabel("Número Conta"));
        bancoLinha.add(new JTextField(12));
        dados.add(bancoLinha);
        
        return dados;
    }
    
    private JPanel criarExamesAdmissionais() {
        JPanel exames = UIHelper.criarBloco("Exames Admissionais", 2);
        exames.add(UIHelper.criarLinha("Nome Médico", new JTextField(26)));
        exames.add(UIHelper.criarLinhaDupla("CRM", new JTextField(10), 
                "Data Exame", UIHelper.criarCampoMascara("##/##/####")));
        return exames;
    }
    
    private JPanel criarFoto() {
        JPanel foto = UIHelper.criarBloco("Foto", 2);
        
        // Label da foto
        lblFoto = new JLabel("sem foto", SwingConstants.CENTER);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblFoto.setMinimumSize(new Dimension(140, 160));
        lblFoto.setPreferredSize(new Dimension(160, 180));
        
        // Centralizar a foto
        JPanel fotoBox = new JPanel(new GridBagLayout());
        fotoBox.add(lblFoto);
        foto.add(fotoBox);
        
        // Botões da foto
        JPanel botoesFoto = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        JButton btnSel = new JButton("Selecionar");
        JButton btnLimpar = new JButton("Limpar");
        btnSel.addActionListener(e -> selecionarFoto());
        btnLimpar.addActionListener(e -> limparFoto());
        botoesFoto.add(btnSel);
        botoesFoto.add(btnLimpar);
        foto.add(botoesFoto);
        
        // Listener para redimensionamento da foto
        lblFoto.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                renderFoto();
            }
        });
        
        return foto;
    }
    
    private void selecionarFoto() {
        try {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(lblFoto.getParent()) == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                if (img != null) {
                    fotoOriginal = img;
                    renderFoto();
                } else {
                    JOptionPane.showMessageDialog(lblFoto.getParent(), "Arquivo inválido.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(lblFoto.getParent(), "Erro ao carregar imagem.");
        }
    }
    
    private void limparFoto() {
        fotoOriginal = null;
        lblFoto.setIcon(null);
        lblFoto.setText("sem foto");
    }
    
    private void renderFoto() {
        if (fotoOriginal == null) return;
        
        int w = Math.max(lblFoto.getWidth(), 140);
        int h = Math.max(lblFoto.getHeight(), 160);
        
        if (w > 0 && h > 0) {
            Image scaled = fotoOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(scaled));
            lblFoto.setText(null);
        }
    }
}