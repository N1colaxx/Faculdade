package menu_nf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Eventos extends JFrame implements ActionListener {
    private JButton btn_gravar;
    private TelaDadosPessoais telaDadosP;
    
    public Eventos(){
    }
    
    public Eventos(JButton btn_gravar,  TelaDadosPessoais telaDadosP){
        this.btn_gravar = btn_gravar;
        this.telaDadosP = telaDadosP;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_gravar){
            System.out.println("Clique detectado em Evento Validacao ");
            JOptionPane.showMessageDialog(
                    null,
                    "Clicou no BTN Gravar",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );    
          
          
            // Pegando a data do JSpinner
            Date dataSelecionada = (Date) telaDadosP.getDataNascimento().getValue();
            Date dataHj = new Date();

            // Formatando apenas para exibir
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formato.format(dataSelecionada);

            System.out.println("Data Selecionada (nascimento) : " + dataFormatada);

            // Comparação correta usando Date
            if (dataSelecionada.after(dataHj)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Data não pode ser no Futuro!",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Data Válida! -> " + dataFormatada,
                        "Validação OK",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        }
    }
    
}
