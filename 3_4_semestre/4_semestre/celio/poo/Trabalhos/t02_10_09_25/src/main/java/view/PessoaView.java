package view;

import javax.swing.*;
import java.awt.*;

public class PessoaView extends JPanel {

    // ===== Campos base =====
    protected JLabel
            lblNome,
            lblPesFisica,
            lblDocumento,
            lblRgie,
            lblEndereco,
            lblNumero,
            lblComplemento,
            lblBairro,
            lblCidade,
            lblUF,
            lblCEP,
            lblCelular,
            lblSite,
            lblEmail,
            lblTelefone;

    protected JTextField
            edtNome, edtDocumento, edtRgie, edtEndereco, edtNumero, edtComplemento,
            edtBairro, edtCidade, edtCEP, edtCelular, edtSite, edtEmail, edtTelefone;

    protected JComboBox<String> cbPesFisica, cbUF;

    // ===== Estado do layout =====
    private final GridBagConstraints gbc = new GridBagConstraints();
    private int nextRow = 0; // controla a próxima linha disponível

    public PessoaView() {
//        setBackground(Color.red);
        setLayout(new GridBagLayout());

        configurarGbc();
        instanciar();
        adicionarBase();
    }

    private void configurarGbc() {
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 0; // nada expande por padrão
        gbc.fill = GridBagConstraints.NONE;
    }

    private void instanciar() {
        lblNome = new JLabel("Nome:"); edtNome = new JTextField(20);
        lblPesFisica = new JLabel("Pessoa Física?"); cbPesFisica = new JComboBox<>(new String[]{"Sim", "Não"});
        lblDocumento = new JLabel("CPF/CNPJ:"); edtDocumento = new JTextField(15);
        lblRgie = new JLabel("RG/IE:"); edtRgie = new JTextField(15);
        lblEndereco = new JLabel("Endereço:"); edtEndereco = new JTextField(20);
        lblNumero = new JLabel("Número:"); edtNumero = new JTextField(5);
        lblComplemento = new JLabel("Complemento:"); edtComplemento = new JTextField(15);
        lblBairro = new JLabel("Bairro:"); edtBairro = new JTextField(15);
        lblCidade = new JLabel("Cidade:"); edtCidade = new JTextField(15);
        lblUF = new JLabel("UF:");
        cbUF = new JComboBox<>(new String[]{
                "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
                "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
        });
        lblCEP = new JLabel("CEP:"); edtCEP = new JTextField(10);
        lblCelular = new JLabel("Celular:"); edtCelular = new JTextField(15);
        lblTelefone = new JLabel("Telefone:"); edtTelefone = new JTextField(15);
        lblSite = new JLabel("Site:"); edtSite = new JTextField(20);
        lblEmail = new JLabel("E-mail:"); edtEmail = new JTextField(20);
    }

    private void adicionarBase() {
        addCampo(lblNome, edtNome);
        addCampo(lblPesFisica, cbPesFisica);
        addCampo(lblDocumento, edtDocumento);
        addCampo(lblRgie, edtRgie);
        addCampo(lblEndereco, edtEndereco);
        addCampo(lblNumero, edtNumero);
        addCampo(lblComplemento, edtComplemento);
        addCampo(lblBairro, edtBairro);
        addCampo(lblCidade, edtCidade);
        addCampo(lblUF, cbUF);
        addCampo(lblCEP, edtCEP);
        addCampo(lblCelular, edtCelular);
        addCampo(lblTelefone, edtTelefone);
        addCampo(lblSite, edtSite);
        addCampo(lblEmail, edtEmail);
    }

    // label na col 0, campo na col 1; nada expande
    private void addCampo(JLabel label, JComponent field) {
        // Label (coluna 0)
        gbc.gridx = 0;
        gbc.gridy = nextRow;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(label, gbc);

        // Campo (coluna 1)
        gbc.gridx = 1;
        gbc.gridy = nextRow;
        gbc.weightx = 0;                 // não distribuir espaço extra
        gbc.fill = GridBagConstraints.HORIZONTAL; // mantém preferredSize do componente
        add(field, gbc);

        nextRow++;
    }

    public void addCampoExtra(JLabel label, JComponent field) {
        addCampo(label, field);
        revalidate();
        repaint();
    }

    // ==== Getters ====
    public String getNome() { return edtNome.getText(); }
    public String getDocumento() { return edtDocumento.getText(); }
    public String getEndereco() { return edtEndereco.getText(); }
    public String getUf() { return (String) cbUF.getSelectedItem(); }
    public boolean isPessoaFisica() { return cbPesFisica.getSelectedItem().equals("Sim"); }
}
