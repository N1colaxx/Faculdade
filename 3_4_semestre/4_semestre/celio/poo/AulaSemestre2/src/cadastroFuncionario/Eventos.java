    package cadastroFuncionario;

import java.awt.HeadlessException;
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
            
            validandoDatas();
            
        }
        
        private void validandoDatas(){
            Date date_nas, date_contrato;
            LocalDate data_nas = null, data_contra = null;
            
            try{
                // Carregando Datas
                try {
                    date_nas = TDPessoais.getSpnDataNas();
                    date_contrato = TDContratuais.getSpnDataAdimissao();

                    // Convertendo para -> LocalDate
                    /*
                        1. O método toInstant() converte esse Date em um Instant. Instant = ponto no tempo em UTC (ex: 2025-08-23T20:05:00Z).
                        2. ZoneId.systemDefault() pega o fuso do computador do usuário (ex: "America/Sao_Paulo"). O resultado é um ZonedDateTime (data + hora + fuso).
                        3. A partir do ZonedDateTime, você extrai só a data (ano, mês, dia). Ignora hora e fuso.
                    */
                    data_nas = date_nas.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    System.out.println("Data de Nascimento, recebida em Eventos: " + data_nas);

                    data_contra = date_contrato.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    System.out.println("Data de Contratual, recebida em Eventos: " + data_contra);

                    dataHj = LocalDate.now();

                } catch(Exception e){
                    System.out.println("\n Erro ao Capturar e Converter Datas \n" + e);
                }



                if(data_nas.isAfter(dataHj)){
                        JOptionPane.showMessageDialog(null, "Data não pode ser futuro", "ERRO! Data Nascimento", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                System.out.println("Data de Nascimento ( valida ): " + data_nas);

                // dataContratua tem q ser DEPOIS de dataNas e >=  data_Hj
                if (data_contra.isAfter(data_nas) && (data_contra.isEqual(dataHj) || data_contra.isAfter(dataHj))){
                     System.out.print("Data de Contratual ( valida ): " + data_contra);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Contrato, tem que ser DEPOIS de Data Nascimento e >= dataHJ!", "ERRO! Data Contratual", JOptionPane.WARNING_MESSAGE);
                }

            } catch (HeadlessException e){
                System.out.println("ERRO! Validar Datas \n" + e);
            }
                        JOptionPane.showConfirmDialog(null, "SUCESSO! Funcionario Cadastrado", "Aviso!", JOptionPane.DEFAULT_OPTION);

        }
        
    }
