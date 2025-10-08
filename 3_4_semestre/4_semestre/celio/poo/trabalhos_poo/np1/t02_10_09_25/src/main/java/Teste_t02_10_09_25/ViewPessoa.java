package Teste_t02_10_09_25;

import javax.swing.*;
import java.awt.*;

public class ViewPessoa extends JPanel {

    private final Dimension dimLabel;
    private final Dimension dimField;

    protected JLabel 
            lblNome, lblPesFisica, lblDocumento, lblRgie, lblEndereco,
            lblNumero, lblComplemento, lblBairro, lblCidade, lblUF, lblCEP,
            lblCelular, lblSite, lblEmail ;
    protected JTextField 
            edtNome, edtDocumento, edtRgie, edtEndereco, edtNumero, edtComplemento,
            edtBairro, edtCidade, edtCEP, edtCelular, edtSite, edtEmail;
    protected JComboBox<String> cbPesFisica, cbUF;

    public ViewPessoa(Dimension dimLabel, Dimension dimField) {
        this.dimLabel = dimLabel;
        this.dimField = dimField;

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instanciar();
        adicionar();
    }

    private void instanciar() {
        lblNome = new JLabel("Nome:"); edtNome = new JTextField();
        lblPesFisica = new JLabel("Pessoa Física?"); cbPesFisica = new JComboBox<>(new String[]{"Sim", "Não"});
        lblDocumento = new JLabel("CPF/CNPJ:"); edtDocumento = new JTextField();
        lblRgie = new JLabel("RG/IE:"); edtRgie = new JTextField();
        lblEndereco = new JLabel("Endereço:"); edtEndereco = new JTextField();
        lblNumero = new JLabel("Número:"); edtNumero = new JTextField();
        lblComplemento = new JLabel("Complemento:"); edtComplemento = new JTextField();
        lblBairro = new JLabel("Bairro:"); edtBairro = new JTextField();
        lblCidade = new JLabel("Cidade:"); edtCidade = new JTextField();
        lblUF = new JLabel("UF:");
        cbUF = new JComboBox<>(new String[]{
                "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG",
                "PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
        });
        lblCEP = new JLabel("CEP:"); edtCEP = new JTextField();
        lblCelular = new JLabel("Celular:"); edtCelular = new JTextField();
        lblSite = new JLabel("Site:"); edtSite = new JTextField();
        lblEmail = new JLabel("E-mail:"); edtEmail = new JTextField();

        // padroniza tamanho dos campos
        padronizarCampo(edtNome);
        padronizarCampo(edtDocumento);
        padronizarCampo(edtRgie);
        padronizarCampo(edtEndereco);
        padronizarCampo(edtNumero);
        padronizarCampo(edtComplemento);
        padronizarCampo(edtBairro);
        padronizarCampo(edtCidade);
        padronizarCampo(edtCEP);
        padronizarCampo(edtCelular);
        padronizarCampo(edtSite);
        padronizarCampo(edtEmail);
        padronizarCampo(cbPesFisica);
        padronizarCampo(cbUF);

        padronizarLabel(lblNome);
        padronizarLabel(lblPesFisica);
        padronizarLabel(lblDocumento);
        padronizarLabel(lblRgie);
        padronizarLabel(lblEndereco);
        padronizarLabel(lblNumero);
        padronizarLabel(lblComplemento);
        padronizarLabel(lblBairro);
        padronizarLabel(lblCidade);
        padronizarLabel(lblUF);
        padronizarLabel(lblCEP);
        padronizarLabel(lblCelular);
        padronizarLabel(lblSite);
        padronizarLabel(lblEmail);
    }

    private void padronizarCampo(JComponent c) {
        c.setPreferredSize(dimField);
        c.setMinimumSize(dimField);
        c.setMaximumSize(dimField);
    }

    private void padronizarLabel(JLabel l) {
        l.setPreferredSize(dimLabel);
        l.setMinimumSize(dimLabel);
        l.setMaximumSize(dimLabel);
    }

    private void adicionar() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.CENTER; // centraliza
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 0;

        addCampo(lblNome,        edtNome,      gbc);
        addCampo(lblPesFisica,   cbPesFisica,  gbc);
        addCampo(lblDocumento,   edtDocumento, gbc);
        addCampo(lblRgie,        edtRgie,      gbc);
        addCampo(lblEndereco,    edtEndereco,  gbc);
        addCampo(lblNumero,      edtNumero,    gbc);
        addCampo(lblComplemento, edtComplemento, gbc);
        addCampo(lblBairro,      edtBairro,    gbc);
        addCampo(lblCidade,      edtCidade,    gbc);
        addCampo(lblUF,          cbUF,         gbc);
        addCampo(lblCEP,         edtCEP,       gbc);
        addCampo(lblCelular,     edtCelular,   gbc);
        addCampo(lblSite,        edtSite,      gbc);
        addCampo(lblEmail,       edtEmail,     gbc);
    }

    private void addCampo(JLabel label, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        add(label, gbc);
        gbc.gridx = 1;
        add(field, gbc);
        gbc.gridy++;
    }

    public void adicionarExtras() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = getComponentCount() / 2;

        JLabel lblFantasia = new JLabel("Fantasia:");
        JTextField edtFantasia = new JTextField();
        padronizarLabel(lblFantasia);
        padronizarCampo(edtFantasia);

        JLabel lblFone1 = new JLabel("Telefone 1:");
        JTextField edtFone1 = new JTextField();
        padronizarLabel(lblFone1);
        padronizarCampo(edtFone1);

        JLabel lblFone2 = new JLabel("Telefone 2:");
        JTextField edtFone2 = new JTextField();
        padronizarLabel(lblFone2);
        padronizarCampo(edtFone2);

        addCampo(lblFantasia, edtFantasia, gbc);
        addCampo(lblFone1, edtFone1, gbc);
        addCampo(lblFone2, edtFone2, gbc);
    }
}
