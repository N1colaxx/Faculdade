package testes;

import javax.swing.*;
import java.awt.*;

public class AbaContrato {

    private JTextField txtSalario;

    public JPanel criarAba() {
        JPanel aba = new JPanel(new GridLayout(1, 2, 8, 8));
        aba.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));

        JPanel esq = new JPanel(new GridLayout(5, 1, 8, 8));
        esq.add(criarVinculo());
        esq.add(criarFGTSAtividade());
        esq.add(criarAdiantamento());
        esq.add(criarExperiencia());
        esq.add(criarSalario());

        JPanel dir = new JPanel(new GridLayout(2, 1, 8, 8));
        dir.add(criarCargo());
        dir.add(criarRescisao());

        aba.add(esq);
        aba.add(dir);
        return aba;
    }

    private JPanel criarVinculo() {
        JPanel vinc = UIHelper.criarBlocoSemTitulo(4);

        vinc.add(UIHelper.criarLinha("Vínculo", new JComboBox<>(new String[]{
            "15 - Trabalhador urbano vinculado a empregador pessoa física por contrato indeterminado"})));

        vinc.add(UIHelper.criarLinhaDupla("Tipo de Admissão",
                new JComboBox<>(new String[]{"Normal", "Readmissão", "Transferência"}),
                "Reemprego", new JComboBox<>(new String[]{"Não", "Sim"})));

        vinc.add(UIHelper.criarLinhaDupla("Data Admissão", UIHelper.criarCampoMascara("##/##/####"),
                "Tipo de Salário", new JComboBox<>(new String[]{"Mensal", "Semanal", "Diário", "Hora"})));

        vinc.add(UIHelper.criarLinhaDupla("Horário", new JComboBox<>(new String[]{"GERAL"}),
                "Hrs. Sem.", new JTextField(6)));

        return vinc;
    }

    private JPanel criarFGTSAtividade() {
        JPanel fgtsAtv = UIHelper.criarBlocoSemTitulo(2);

        fgtsAtv.add(UIHelper.criarLinhaDupla("FGTS",
                new JComboBox<>(new String[]{"Optante", "Não Optante"}),
                "Data Opção", UIHelper.criarCampoMascara("##/##/####")));

        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        radios.add(new JLabel("Atividade Desenvolvida"));
        JRadioButton urbana = new JRadioButton("Urbana", true);
        JRadioButton rural = new JRadioButton("Rural");
        ButtonGroup bg = new ButtonGroup();
        bg.add(urbana);
        bg.add(rural);
        radios.add(urbana);
        radios.add(rural);
        fgtsAtv.add(radios);

        return fgtsAtv;
    }

    private JPanel criarAdiantamento() {
        JPanel adiant = UIHelper.criarBloco("Adiantamento Quinzenal", 1);
        adiant.add(UIHelper.criarLinhaTripla("Adiantamento", new JComboBox<>(new String[]{"Sim", "Não"}),
                "Percentual", new JTextField(6),
                "Valor Fixo", new JTextField(8)));
        return adiant;
    }

    private JPanel criarExperiencia() {
        JPanel exp = UIHelper.criarBloco("Experiência", 1);
        exp.add(UIHelper.criarLinhaDupla("Vencimento", UIHelper.criarCampoMascara("##/##/####"),
                "Prorrogação", UIHelper.criarCampoMascara("##/##/####")));
        return exp;
    }

    private JPanel criarSalario() {
        JPanel salario = UIHelper.criarBlocoSemTitulo(1);
        txtSalario = new JTextField(10);
        salario.add(UIHelper.criarLinhaDupla("Valor Salário", txtSalario,
                "Tipo de Reajuste", new JComboBox<>(new String[]{"Variável", "Anual", "Convenção"})));
        return salario;
    }

    private JPanel criarCargo() {
        JPanel cargo = UIHelper.criarBlocoSemTitulo(4);

        cargo.add(UIHelper.criarLinha("Cargo", new JTextField(26)));
        cargo.add(UIHelper.criarLinha("Departamento", new JTextField(26)));
        cargo.add(UIHelper.criarLinha("Categoria GFIP", new JComboBox<>(new String[]{
            "11 - Contribuinte individual - Diretor não empregado e demais"})));
        cargo.add(UIHelper.criarLinha("Tipo Contrato", new JComboBox<>(new String[]{
            "Prazo indeterminado", "Prazo determinado", "Aprendiz"})));

        return cargo;
    }

    private JPanel criarRescisao() {
        JPanel resc = UIHelper.criarBloco("Rescisão", 3);

        resc.add(UIHelper.criarLinhaDupla("Data Demissão", UIHelper.criarCampoMascara("##/##/####"),
                "Motivo Demissão", new JTextField(18)));

        JPanel pAviso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pAviso.add(new JCheckBox("Aviso Prévio"));
        resc.add(pAviso);

        resc.add(UIHelper.criarLinhaTripla("Data Aviso Início", UIHelper.criarCampoMascara("##/##/####"),
                "Data Aviso Fim", UIHelper.criarCampoMascara("##/##/####"),
                "Motivo RAIS", new JTextField(16)));

        return resc;
    }

    // Getter para validação
    public JTextField getTxtSalario() {
        return txtSalario;
    }
}
