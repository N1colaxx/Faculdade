


package menu_nf;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TelaDadosPessoais extends JPanel{

    // 1º) declarar cada componente gráfico (SWING)
    private JLabel lbl_nome_completo, lbl_data_nascimento, lbl_etinia, lbl_deficienci;
    private JTextField edt_nome_completo, edt_etinia, edt_deficienci;
    private JComboBox box_sexo, box_estado_civil, box_nacionalidade, box_tipo_sague;
    private JSpinner spn_data_nascimento;
    
    public TelaDadosPessoais() {

        setLayout(null);
            
        instanciar();
        posicionar();
        adicionar();
        
    }

    // 2º) instanciar cada componente
    private void instanciar() {
        lbl_nome_completo = new JLabel("Nome Completo : ");
        edt_nome_completo = new JTextField(100);
        
        lbl_data_nascimento = new JLabel("Data Nascimento (dd/mm/yyyy): ");

        spn_data_nascimento = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spn_data_nascimento, "dd/MM/yyyy");
        spn_data_nascimento.setEditor(editor);
          
        // aqui formato a data somente para exibir
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        String dataFormatada = sdf.format(getSpnDataNas());
        System.out.println("Data de nascimento, incerida na TELA Dados Pessoais: " + dataFormatada);
          
        
        
        lbl_deficienci = new JLabel("Deficiencia (se ouver) : ");
        edt_deficienci = new JTextField(100);
        
        lbl_etinia = new JLabel("Etinia : ");
        edt_etinia = new JTextField(50);
        
        
        // JComboBox
        box_sexo = new JComboBox<>(new String[] {"Masculino", "Feninino", "Outro"});
        box_nacionalidade = new JComboBox<>(new String[] {"Brasileiro", "Estrangeira"});
        box_estado_civil = new JComboBox<>(new String[] {"Casado", "Solteiro", "Divorciado", "Viuvo"});
        box_tipo_sague = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
    }


    private void posicionar() {
        // exemplo simples com null layout
        lbl_nome_completo.setBounds(20, 20, 150, 25);
        edt_nome_completo.setBounds(180, 20, 200, 25);

        lbl_data_nascimento.setBounds(20, 60, 180, 25);
        spn_data_nascimento.setBounds(200, 60, 200, 25);

        lbl_deficienci.setBounds(20, 100, 150, 25);
        edt_deficienci.setBounds(180, 100, 200, 25);

        lbl_etinia.setBounds(20, 140, 150, 25);
        edt_etinia.setBounds(180, 140, 200, 25);

        box_sexo.setBounds(20, 180, 150, 25);
        box_estado_civil.setBounds(180, 180, 150, 25);
        box_nacionalidade.setBounds(20, 220, 150, 25);
        box_tipo_sague.setBounds(180, 220, 150, 25);
    }

    // 4º) adicionar cada componente
    private void adicionar() {
        
        // LBL e EDT 
        add(lbl_nome_completo);
        add(edt_nome_completo);
        
        add(lbl_data_nascimento);
        add(spn_data_nascimento);
        
        add(lbl_deficienci);
        add(edt_deficienci);
        
        add(lbl_etinia);
        add(edt_etinia);
        
        //BOX
        add(box_estado_civil);
        add(box_nacionalidade);
        add(box_sexo);
        add(box_tipo_sague);
    }
    
    
    // Getters
    public Date getSpnDataNas(){
        // aqui forso o JSpinner a escrever o q eu digitei
        try {
            spn_data_nascimento.commitEdit(); // sincroniza campo com modelo
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        
        return(Date) spn_data_nascimento.getValue();
    }
    
}