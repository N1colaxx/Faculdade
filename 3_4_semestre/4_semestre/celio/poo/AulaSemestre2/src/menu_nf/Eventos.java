    package menu_nf;

    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.text.SimpleDateFormat;
    import java.time.LocalDate;
    import java.time.ZoneId;
    import java.util.Date;

    public class Eventos extends JFrame implements ActionListener {
        private JButton btn_gravar;
        private TelaDadosPessoais TDPessoais;

        public Eventos(){

        }

        public Eventos(JButton btn_gravar, TelaDadosPessoais TDPessoais){
            this.btn_gravar = btn_gravar;
            this.TDPessoais = TDPessoais;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            System.out.println("\nClasse externa de EVENTOS foi Iniciada");
            
            validandoDataNascimento();
        }
        
        
        private void validandoDataNascimento(){
            
            Date dataNascimento = TDPessoais.getSpnDataNas();
            // converto para LocalDate para remover os segundo e deixar somente (dd/mm/yyyy)
            LocalDate dataNas = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataHj = LocalDate.now();

            
            System.out.println("Data de nascimento, recebida em Eventos: " + dataNas);
            System.out.println("Data de Hj: " + dataHj);

            if (dataNas.equals(dataHj)) {
                JOptionPane.showMessageDialog(null, "Data válida: " + dataNas, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data não pode ser no Futuro ou Passado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        
    }
