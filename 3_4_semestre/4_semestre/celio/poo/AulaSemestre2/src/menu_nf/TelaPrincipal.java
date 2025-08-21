package menu_nf;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    
    private JButton btn_gravar, btn_cancelar, btn_sair;
    
    public TelaPrincipal(){
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
    
    // 2º) instanciar cada componente
    private void instanciar() {
        btn_gravar = new JButton("Gravar");
        btn_cancelar = new JButton("Cancelar");
        btn_sair = new JButton("Sair");
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
