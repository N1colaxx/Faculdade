package menu_nf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class TelaDadosContratuais extends JPanel {
    // ComboBox 
    private JComboBox box_departamento, box_cargo_funcao, box_tipo_contrato, box_regime_trabalho;

    // Labels
    private JLabel lbl_ra, lbl_empresa, lbl_local_trabalho, lbl_data_admissao,
            lbl_jornada_trabalho, lbl_salario,
            lbl_info_sobre_ferias, lbl_info_sobre_afastamento, lbl_info_sobre_recisao;

    // TextFields
    private JTextField edt_ra, edt_empresa, edt_local_trabalho,
            edt_jornada_trabalho, edt_salario,
            edt_info_sobre_ferias, edt_info_sobre_afastamento, edt_info_sobre_recisao;

    private JSpinner spn_data_admissao;
    
    public TelaDadosContratuais() {
        setLayout(null);

        implementar();
        posicionar();
        adicionar();
    }

    private void implementar() {
        // LBL e EDT
        lbl_ra = new JLabel("Matrícula (RA): ");
        edt_ra = new JTextField(10);

        lbl_empresa = new JLabel("Empresa: ");
        edt_empresa = new JTextField(100);

        lbl_local_trabalho = new JLabel("Local de Trabalho: ");
        edt_local_trabalho = new JTextField(100);

        lbl_data_admissao = new JLabel("Data de Admissão: ");
        
        Date dataHJ = new Date();
        // SpinnerDateModel -> (Valor inicial, Valor MIM, Valor MAX, uniadede de incremento (sei o Calendar);
        SpinnerDateModel dateModel = new SpinnerDateModel(dataHJ, null, null, Calendar.DAY_OF_MONTH);
        spn_data_admissao = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spn_data_admissao, "yyyy/MM/dd");
        spn_data_admissao.setEditor(dateEditor);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dataFormatada = sdf.format(getSpnDataAdimissao());
        System.out.println("Data de Admissao, incerida na TELA Dados Contratuais: " + dataFormatada);
        
        lbl_jornada_trabalho = new JLabel("Jornada de Trabalho: ");
        edt_jornada_trabalho = new JTextField(5);

        lbl_salario = new JLabel("Salário: ");
        edt_salario = new JTextField(10);

        lbl_info_sobre_ferias = new JLabel("Informações sobre Férias: ");
        edt_info_sobre_ferias = new JTextField(100);

        lbl_info_sobre_afastamento = new JLabel("Informações sobre Afastamento: ");
        edt_info_sobre_afastamento = new JTextField(100);

        lbl_info_sobre_recisao = new JLabel("Informações sobre Rescisão: ");
        edt_info_sobre_recisao = new JTextField(100);

        // ComboBox obrigatórios
        box_departamento = new JComboBox<>(new String[]{"RH", "Financeiro", "TI", "Marketing", "Operacional"});
        box_cargo_funcao = new JComboBox<>(new String[]{"Analista", "Gerente", "Estagiário", "Supervisor"});
        box_tipo_contrato = new JComboBox<>(new String[]{"Determinado", "Indeterminado", "Aprendizagem", "Estágio", "Temporário"});
        box_regime_trabalho = new JComboBox<>(new String[]{"Integral", "Parcial", "Remoto", "Híbrido"});
    }

    private void posicionar() {
        int xLabel = 20;
        int xEdit = 200;
        int largura = 200;
        int altura = 25;
        int y = 20;
        int passo = 40;

        lbl_ra.setBounds(xLabel, y, 150, altura);
        edt_ra.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_empresa.setBounds(xLabel, y, 150, altura);
        edt_empresa.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_local_trabalho.setBounds(xLabel, y, 150, altura);
        edt_local_trabalho.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_data_admissao.setBounds(xLabel, y, 150, altura);
        spn_data_admissao.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_jornada_trabalho.setBounds(xLabel, y, 150, altura);
        edt_jornada_trabalho.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_salario.setBounds(xLabel, y, 150, altura);
        edt_salario.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_info_sobre_ferias.setBounds(xLabel, y, 200, altura);
        edt_info_sobre_ferias.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_info_sobre_afastamento.setBounds(xLabel, y, 200, altura);
        edt_info_sobre_afastamento.setBounds(xEdit, y, largura, altura);
        y += passo;

        lbl_info_sobre_recisao.setBounds(xLabel, y, 200, altura);
        edt_info_sobre_recisao.setBounds(xEdit, y, largura, altura);
        y += passo;

        // ComboBox
        box_departamento.setBounds(xLabel, y, 150, altura);
        box_cargo_funcao.setBounds(xEdit, y, 150, altura);
        y += passo;

        box_tipo_contrato.setBounds(xLabel, y, 150, altura);
        box_regime_trabalho.setBounds(xEdit, y, 150, altura);
    }

    private void adicionar() {
        add(lbl_ra);
        add(edt_ra);

        add(lbl_empresa);
        add(edt_empresa);

        add(lbl_local_trabalho);
        add(edt_local_trabalho);

        add(lbl_data_admissao);
        add(spn_data_admissao);

        add(lbl_jornada_trabalho);
        add(edt_jornada_trabalho);

        add(lbl_salario);
        add(edt_salario);

        add(lbl_info_sobre_ferias);
        add(edt_info_sobre_ferias);

        add(lbl_info_sobre_afastamento);
        add(edt_info_sobre_afastamento);

        add(lbl_info_sobre_recisao);
        add(edt_info_sobre_recisao);

        add(box_departamento);
        add(box_cargo_funcao);
        add(box_tipo_contrato);
        add(box_regime_trabalho);
    }
    
    // Getters
    public Date getSpnDataAdimissao() {
        try {
            spn_data_admissao.commitEdit(); // aplica o texto digitado no spinner

            // Captura o que o usuário digitou
            String texto = ((JSpinner.DefaultEditor) spn_data_admissao.getEditor())
                              .getTextField().getText();

            // Valida com formato estrito
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.setLenient(false); // NÃO aceita datas inválidas tipo 2025/02/30

            Date data = sdf.parse(texto); // se falhar, cai no catch
            return data;

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,
                "Formato de Data Inválido! Use (yyyy/MM/dd)",
                "ERRO! Dados Contratuais!",
                JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    
}
