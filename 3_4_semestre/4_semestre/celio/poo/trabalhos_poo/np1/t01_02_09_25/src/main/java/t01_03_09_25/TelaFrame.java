package t01_03_09_25;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;

public class TelaFrame extends JFrame {

    private JLabel lblStatus, lblSituacao, lblCpf, lblNome;
    private JTextField edtStatus, edtSituacao, edtCpf, edtNome;
    private JButton btnSalvar, btnConcluir, btnExcluir, btnOcorrencia, btnFechar;

    private JTabbedPane abas;
    private JToolBar toolBar; // Nova barra de ferramentas

    public TelaFrame() { 
                
        setTitle("Funcionários");
        setPreferredSize(new Dimension(1500, 800));
        setLayout(null);



        criarComponentes();
        adicionarComponentes();
        configurarPosicoes();
        
        // Look and Feel
        try {
            for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(i.getName())) { UIManager.setLookAndFeel(i.getClassName()); break; }
            }
        } catch (Exception ignored) {}


        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void criarComponentes() {

        lblStatus = new JLabel("Status:");
        edtStatus = new JTextField("Aberta");
        edtStatus.setEnabled(false);
        edtStatus.setDisabledTextColor(Color.BLUE);

        lblSituacao = new JLabel("Situação:");
        edtSituacao = new JTextField("Ativo");
        edtSituacao.setEnabled(false);
        edtSituacao.setDisabledTextColor(Color.GREEN);

        btnSalvar = new JButton("Salvar");
        btnConcluir = new JButton("Concluir");
        btnExcluir = new JButton("Excluir");
        btnOcorrencia = new JButton("Ocorrência");
        btnFechar = new JButton("Fechar");

        lblCpf = new JLabel("CPF");
        edtCpf = new JTextField(11);

        lblNome = new JLabel("Nome");
        edtNome = new JTextField(150);

        abas = new JTabbedPane();
        abas.addTab("Principal", new AbaPrincipal());
        abas.addTab("Documentação", new AbaDocumentacao());
        abas.addTab("Contrato", new AbaContrato());
        abas.addTab("Operacional", new AbaOperacional());

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(lblStatus);
        toolBar.add(edtStatus);
        toolBar.addSeparator();
        toolBar.add(btnSalvar);
        toolBar.add(btnConcluir);
        toolBar.add(btnExcluir);
        toolBar.add(btnOcorrencia);
        toolBar.add(btnFechar);
        toolBar.addSeparator();
        toolBar.add(lblSituacao);
        toolBar.add(edtSituacao);

    }

    private void adicionarComponentes() {

        add(toolBar);

        add(lblCpf);
        add(edtCpf);
        add(lblNome);
        add(edtNome);

        add(abas);

    }

    private void configurarPosicoes() {

        toolBar.setBounds(10, 10, 800, 40);

        lblCpf.setBounds(10, 60, 50, 30);
        edtCpf.setBounds(50, 60, 80, 30);

        lblNome.setBounds(160, 60, 80, 30);
        edtNome.setBounds(200, 60, 200, 30);

        abas.setBounds(10, 110, 1200, 650);

    }

}
