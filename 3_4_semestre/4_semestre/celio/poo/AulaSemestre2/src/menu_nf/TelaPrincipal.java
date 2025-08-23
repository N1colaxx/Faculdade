package menu_nf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class TelaPrincipal extends JFrame {
    
    private JButton btn_gravar, btn_cancelar, btn_sair;
    
    private Eventos evento;
    
//    Construtor
    public TelaPrincipal(){
        //
        
        // dimensão do Frame
        setTitle("Cadastro de Nota Fiscal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        // Abas
        JTabbedPane abas = new JTabbedPane();
        abas.add("Dados Pessoais", new TelaDadosPessoais());
        abas.add("Informações de Contato", new TelaInfoContato());
        abas.add("Dados Contratuais", new TelaDadosContratuais());
        
        add(abas, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        
        instanciar();
        
        painelBotoes.add(btn_gravar);
        painelBotoes.add(btn_cancelar);
        painelBotoes.add(btn_sair);

        add(painelBotoes, BorderLayout.SOUTH); // adiciona painel de botões no rodapé
        
        
    }
    
    
//       Tratamento de eventos
        private void validar(int n){
        System.out.println("\n Valor de N: " + n + "\n");
        switch (n) {
            
            case 1:
                
                TelaDadosPessoais telaDadosP = new TelaDadosPessoais();
                System.out.println("Clique detectado em Tela Principal em: Gravar");
                
                // tenho q remover pois: a cada click ele agrega um ActionListener, pois instancio 2 Actionlistener, um em Instanciar e outro dentro do Switch
                for(ActionListener al : btn_gravar.getActionListeners()){
                    btn_gravar.removeActionListener(al);
                }
                
                evento = new Eventos(btn_gravar, telaDadosP);
                btn_gravar.addActionListener(evento);
                break;
                
            case 2:
                System.out.println("Clique detectado em Tela Principal em: Cancelar");
                JOptionPane.showMessageDialog(
                        null,
                        "Clicou no BTN Cancelar",
                        "Aviso",
                        JOptionPane.ERROR_MESSAGE
                );
                
                break;
                
            case 3:
                  System.out.println("Clique detectado em Tela Principal em: Sair");
                  JOptionPane.showMessageDialog(
                          null,
                          "Clicou no BTN Sair",
                          "Aviso",
                          JOptionPane.ERROR_MESSAGE);
                  System.exit(0);
                  break;

            default:
                throw new AssertionError();
        }
    }

    
    // instanciar cada componente
    private void instanciar() {
        btn_gravar = new JButton("Gravar");
        btn_gravar.addActionListener(e -> validar(1));
        
        btn_cancelar = new JButton("Cancelar");
        btn_cancelar.addActionListener(e -> validar(2));
        
        btn_sair = new JButton("Sair");
        btn_sair.addActionListener(e -> validar(3));
    }
    
    

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
