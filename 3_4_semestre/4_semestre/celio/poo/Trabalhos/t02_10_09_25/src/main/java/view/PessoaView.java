package view;

import javax.swing.*;
import java.awt.*;

public class PessoaView extends JPanel {

    protected JLabel 
            lblNome, lblPesFisica, lblDocumento, lblRgie, lblEndereco,
            lblNumero, lblComplemento, lblBairro, lblCidade, lblUF, lblCEP,
            lblCelular, lblSite, lblEmail, lblTelefone;
    protected JTextField 
            edtNome, edtDocumento, edtRgie, edtEndereco, edtNumero, edtComplemento,
            edtBairro, edtCidade, edtCEP, edtCelular, edtSite, edtEmail, edtTelefone;
    protected JComboBox<String> cbPesFisica, cbUF;

    public PessoaView() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instanciar();
        adicionar();
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

    private void adicionar() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        addCampo(lblNome, edtNome, gbc);
        addCampo(lblPesFisica, cbPesFisica, gbc);
        addCampo(lblDocumento, edtDocumento, gbc);
        addCampo(lblRgie, edtRgie, gbc);
        addCampo(lblEndereco, edtEndereco, gbc);
        addCampo(lblNumero, edtNumero, gbc);
        addCampo(lblComplemento, edtComplemento, gbc);
        addCampo(lblBairro, edtBairro, gbc);
        addCampo(lblCidade, edtCidade, gbc);
        addCampo(lblUF, cbUF, gbc);
        addCampo(lblCEP, edtCEP, gbc);
        addCampo(lblCelular, edtCelular, gbc);
        addCampo(lblTelefone, edtTelefone, gbc);
        addCampo(lblSite, edtSite, gbc);
        addCampo(lblEmail, edtEmail, gbc);
    }

    private void addCampo(JLabel label, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        add(label, gbc);
        gbc.gridx = 1;
        add(field, gbc);
        gbc.gridy++;
    }



// exemplo de getters para usar no Controller
public String getNome() { return edtNome.getText(); }
public String getDocumento() { return edtDocumento.getText(); }
public String getEndereco() { return edtEndereco.getText(); }
public String getUf() { return (String) cbUF.getSelectedItem(); }
public boolean isPessoaFisica() { return cbPesFisica.getSelectedItem().equals("Sim"); }

}