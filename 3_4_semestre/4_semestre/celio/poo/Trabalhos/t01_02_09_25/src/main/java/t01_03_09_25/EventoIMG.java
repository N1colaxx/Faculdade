package t01_03_09_25;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EventoIMG {

    // JLabel onde a foto selecionada será exibida
    private final JLabel lblFoto;

    // Construtor recebe o JLabel alvo e guarda em uma variável
    public EventoIMG(JLabel lblFoto) {
        this.lblFoto = lblFoto;
    }

    // Configura o botão "Selecionar" para abrir um JFileChooser e carregar uma imagem
    public void adicionarFuncaoSelecionar(JButton btnSelecionar) {
        btnSelecionar.addActionListener(e -> {
            System.out.println("\n [EventoIMG] BTN Selecioanr iniciando ...");;
            
            // Cria o seletor de arquivos
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecione uma imagem");          
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  // Apenas arquivos, não pastas
            fileChooser.setAcceptAllFileFilterUsed(false);              // Remove a opção "Todos os arquivos"
            
            
            fileChooser.addChoosableFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png")
            );

            // Abre a janela de seleção de arquivos
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                
                // Se o usuário confirmou a escolha, pega o arquivo selecionado
                File arquivo = fileChooser.getSelectedFile();

                // Cria um ImageIcon a partir do caminho do arquivo
                ImageIcon imagem = new ImageIcon(arquivo.getAbsolutePath());

                // Redimensiona a imagem para caber dentro do JLabel,
                // mantendo a suavização para não perder qualidade
                Image img = imagem.getImage().getScaledInstance(
                        lblFoto.getWidth(),
                        lblFoto.getHeight(),
                        Image.SCALE_SMOOTH
                );

                // Define a imagem redimensionada no JLabel
                lblFoto.setIcon(new ImageIcon(img));
            }
        });
               
        System.out.println("[EventoIMG] BTN Selecioanr concluido (SUCESSO!)");

    }

    // Configura o botão "Limpar" para remover a imagem do JLabel
    public void adicionarFuncaoLimpar(JButton btnLimpar) {
        System.out.println("\n [EventoIMG] BTN Limpar, iniciando ...");
        
        btnLimpar.addActionListener(e -> lblFoto.setIcon(null));
        
        System.out.println("\n [EventoIMG] BTN Limpar, concluido (SUCESSO)");
    }
}
