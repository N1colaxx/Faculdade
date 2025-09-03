package testes;

import javax.swing.*;

public class ComponentesValidacao {
    
    private final JFormattedTextField txtCPF;
    private final JTextField txtNome;
    private final JFormattedTextField txtDataNasc;
    private final JTextField txtEmail;
    private final JTextField txtSalario;
    
    public ComponentesValidacao(JFormattedTextField txtCPF, JTextField txtNome, 
                               JFormattedTextField txtDataNasc, JTextField txtEmail, 
                               JTextField txtSalario) {
        this.txtCPF = txtCPF;
        this.txtNome = txtNome;
        this.txtDataNasc = txtDataNasc;
        this.txtEmail = txtEmail;
        this.txtSalario = txtSalario;
    }
    
    // Getters
    public JFormattedTextField getTxtCPF() { return txtCPF; }
    public JTextField getTxtNome() { return txtNome; }
    public JFormattedTextField getTxtDataNasc() { return txtDataNasc; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtSalario() { return txtSalario; }
}