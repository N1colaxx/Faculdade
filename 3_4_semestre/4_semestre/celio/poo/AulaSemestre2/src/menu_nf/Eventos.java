    package menu_nf;

    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.time.LocalDate;
    import java.time.ZoneId;
    import java.util.Date;

    public class Eventos extends JFrame implements ActionListener {
        private TelaDadosPessoais TDPessoais;
        private TelaDadosContratuais TDContratuais;
        private TelaInfoContato  TIContato;
        
        private LocalDate dataNascimentoValida, dataHj = LocalDate.now();
        
        public Eventos(){
        }

        public Eventos(TelaDadosPessoais TDPessoais, TelaDadosContratuais TDContratuais, TelaInfoContato TIContato){
            this.TDPessoais = TDPessoais;
            this.TDContratuais = TDContratuais;
            this.TIContato = TIContato;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("\nClasse externa de EVENTOS foi Iniciada");
            System.out.println("Data de Hj: " + dataHj);
            
            validandoDataNascimento();
            validandoDataContratuais();
        }
        
        
        private void validandoDataNascimento(){
            Date dataNascimento = TDPessoais.getSpnDataNas();
            // converto para LocalDate para remover os segundo e deixar somente (dd/mm/yyyy)
            LocalDate dataNas = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataHj = LocalDate.now();
            System.out.println("Data de nascimento, recebida em Eventos: " + dataNas);

            /*
            1. O método toInstant() converte esse Date em um Instant. Instant = ponto no tempo em UTC (ex: 2025-08-23T20:05:00Z).
            2. ZoneId.systemDefault() pega o fuso do computador do usuário (ex: "America/Sao_Paulo"). O resultado é um ZonedDateTime (data + hora + fuso).
            3. A partir do ZonedDateTime, você extrai só a data (ano, mês, dia). Ignora hora e fuso.
            */


            if (dataNas.isAfter(dataHj)) {
                JOptionPane.showMessageDialog(null, "Data não pode ser no Futuro!", "ERRO! DataNascimento", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("Data de Nascimento ( válida ): " + dataNas);
                dataNascimentoValida = dataNas;
            }
        }
        
        private void validandoDataContratuais(){
            Date dataContratual = TDContratuais.getSpnDataAdimissao();
            LocalDate dataContra = dataContratual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            System.out.println("Data de Contratual, recebida em Eventos: " + dataContra);

            if (dataContra.isBefore(dataHj) || dataContra.isAfter(dataNascimentoValida)){
                 System.out.print("Data de Contratual ( válida ): " + dataContra);
            } else {
                JOptionPane.showMessageDialog(null, "Data não pode ser <=  OHJE ou a Data Nascimento!", "ERRO! Contratual", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }
