
package TesteLayouts;


// Imports básicos do Swing e AWT para criar a interface gráfica
import javax.swing.*;              // Componentes Swing (JFrame, JPanel, JButton, JLabel, etc.)
import java.awt.*;                 // Classes de layout e utilidades gráficas (LayoutManagers, Dimension, Insets)

public class LayoutsTeste extends JFrame { // Janela principal herda de JFrame


    public LayoutsTeste (){                            // Construtor da nossa janela
        setTitle("Comparativo: FlowLayout × BoxLayout × GridBagLayout"); // Título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      // Fecha o app ao fechar a janela
        setSize(700, 420);                                   // Define tamanho inicial da janela
        setLocationRelativeTo(null);                         // Centraliza a janela na tela

        JTabbedPane abas = new JTabbedPane();                // Cria um controle de abas
        abas.addTab("FlowLayout", criarPainelFlow());        // Abre a primeira aba com FlowLayout
        abas.addTab("BoxLayout", criarPainelBox());          // Segunda aba com BoxLayout
        abas.addTab("GridBagLayout", criarPainelGridBag());  // Terceira aba com GridBagLayout

        setContentPane(abas);                                // Define o conteúdo da janela como o JTabbedPane
    }

    // ===========================
    // 1) Exemplo com FlowLayout
    // ===========================
    private JPanel criarPainelFlow() {                       // Método que monta um painel usando FlowLayout
        JPanel p = new JPanel();                             // Cria um painel comum
        // FlowLayout posiciona componentes em sequência (esquerda→direita) e quebra linha quando não cabe
        p.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));// Alinha à esquerda, com espaçamentos H=10 e V=10

        // Criação dos rótulos e campos (o Flow não mantém colunas alinhadas por padrão)
        JLabel lblTitulo = new JLabel("Formulário (FlowLayout)"); // Um título simples
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f)); // Deixa o título em negrito e maior
        p.add(lblTitulo);                                       // Adiciona o título

        // Linha "Nome"
        p.add(new JLabel("Nome:"));                              // Rótulo de "Nome"
        JTextField txtNome = new JTextField(12);                 // Campo de texto com ~12 colunas (largura sugerida)
        p.add(txtNome);                                         // Adiciona o campo

        // Linha "E-mail"
        p.add(new JLabel("E-mail:"));                            // Rótulo de "E-mail"
        JTextField txtEmail = new JTextField(15);                // Campo de e-mail (um pouco mais largo)
        p.add(txtEmail);                                        // Adiciona o campo

        // Linha "Senha"
        p.add(new JLabel("Senha:"));                             // Rótulo de "Senha"
        JPasswordField txtSenha = new JPasswordField(10);        // Campo de senha (com eco de caracteres)
        p.add(txtSenha);                                        // Adiciona o campo

        // Botões (ficam na sequência; se a janela estreitar, eles podem “pular” de linha)
        JButton btnSalvar = new JButton("Salvar");               // Botão "Salvar"
        JButton btnCancelar = new JButton("Cancelar");           // Botão "Cancelar"
        JButton btnLimpar = new JButton("Limpar");               // Botão "Limpar"
        p.add(btnSalvar);                                        // Adiciona "Salvar"
        p.add(btnCancelar);                                      // Adiciona "Cancelar"
        p.add(btnLimpar);                                        // Adiciona "Limpar"

        // Dica visual: um texto explicando o comportamento do FlowLayout
        JTextArea dica = new JTextArea(
            "FlowLayout: componentes fluem na horizontal e quebram linha quando não cabem.\n" +
            "Prático para barras simples de botões; não alinha colunas automaticamente."
        );
        dica.setEditable(false);                                 // Torna a área de texto somente leitura
        dica.setOpaque(false);                                   // Fundo transparente para parecer um rótulo grande
        p.add(dica);                                             // Adiciona a “dica”

        return p;                                                // Retorna o painel pronto
    }

    // ===========================
    // 2) Exemplo com BoxLayout
    // ===========================
    private JPanel criarPainelBox() {                           // Método que monta um painel usando BoxLayout
        JPanel p = new JPanel();                                // Painel raiz
        // BoxLayout organiza tudo em linha ou coluna; aqui faremos uma COLUNA (Y_AXIS)
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));        // Define eixo vertical para empilhar linhas

        // Título
        JLabel lblTitulo = new JLabel("Formulário (BoxLayout - vertical)"); // Título
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);       // Alinha o título à esquerda dentro do BoxLayout
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f)); // Negrito e maior
        p.add(lblTitulo);                                       // Adiciona o título
        p.add(Box.createRigidArea(new Dimension(0, 8)));        // Espaço vertical fixo (8px) após o título

        // Para alinhar bonitinho, criaremos “linhas” (JPanels) horizontais, cada uma com BoxLayout no X
        // Também padronizamos a largura dos rótulos para simular colunas

        Dimension tamRotulo = new Dimension(80, 24);            // Tamanho fixo para rótulos (alinhamento visual)

        // Linha "Nome"
        JPanel linhaNome = new JPanel();                        // Uma linha para "Nome"
        linhaNome.setLayout(new BoxLayout(linhaNome, BoxLayout.X_AXIS)); // Linha em eixo horizontal
        linhaNome.setAlignmentX(Component.LEFT_ALIGNMENT);      // Alinha a linha à esquerda no painel pai

        JLabel lblNome = new JLabel("Nome:");                   // Rótulo "Nome"
        lblNome.setPreferredSize(tamRotulo);                    // Define largura fixa do rótulo (alinha com os demais)
        JTextField txtNome = new JTextField(20);                // Campo de texto (20 colunas)
        linhaNome.add(lblNome);                                 // Adiciona rótulo na linha
        linhaNome.add(Box.createRigidArea(new Dimension(6, 0)));// Espaço fixo horizontal de 6px entre rótulo e campo
        linhaNome.add(txtNome);                                 // Adiciona o campo
        p.add(linhaNome);                                       // Coloca a linha no painel principal
        p.add(Box.createRigidArea(new Dimension(0, 6)));        // Espaço vertical entre linhas

        // Linha "E-mail"
        JPanel linhaEmail = new JPanel();                       // Linha para "E-mail"
        linhaEmail.setLayout(new BoxLayout(linhaEmail, BoxLayout.X_AXIS)); // Horizontal
        linhaEmail.setAlignmentX(Component.LEFT_ALIGNMENT);     // Alinha à esquerda
        JLabel lblEmail = new JLabel("E-mail:");                // Rótulo "E-mail"
        lblEmail.setPreferredSize(tamRotulo);                   // Mesma largura para alinhar
        JTextField txtEmail = new JTextField(20);               // Campo de e-mail
        linhaEmail.add(lblEmail);                               // Adiciona rótulo
        linhaEmail.add(Box.createRigidArea(new Dimension(6, 0)));// Espaço de 6px
        linhaEmail.add(txtEmail);                               // Adiciona campo
        p.add(linhaEmail);                                      // Adiciona a linha ao painel
        p.add(Box.createRigidArea(new Dimension(0, 6)));        // Espaço vertical

        // Linha "Senha"
        JPanel linhaSenha = new JPanel();                       // Linha para "Senha"
        linhaSenha.setLayout(new BoxLayout(linhaSenha, BoxLayout.X_AXIS)); // Horizontal
        linhaSenha.setAlignmentX(Component.LEFT_ALIGNMENT);     // Alinha à esquerda
        JLabel lblSenha = new JLabel("Senha:");                 // Rótulo "Senha"
        lblSenha.setPreferredSize(tamRotulo);                   // Largura fixa
        JPasswordField txtSenha = new JPasswordField(20);       // Campo de senha
        linhaSenha.add(lblSenha);                               // Adiciona rótulo
        linhaSenha.add(Box.createRigidArea(new Dimension(6, 0)));// Espaço 6px
        linhaSenha.add(txtSenha);                               // Adiciona campo
        p.add(linhaSenha);                                      // Adiciona linha
        p.add(Box.createRigidArea(new Dimension(0, 10)));       // Espaço maior antes dos botões

        // Linha dos botões (horizontal), usando "glue" para empurrar botões para a esquerda/direita se quiser
        JPanel linhaBotoes = new JPanel();                      // Linha para botões
        linhaBotoes.setLayout(new BoxLayout(linhaBotoes, BoxLayout.X_AXIS)); // Horizontal
        linhaBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);    // Alinha a linha em si à esquerda

        JButton btnSalvar = new JButton("Salvar");              // Botão "Salvar"
        JButton btnCancelar = new JButton("Cancelar");          // Botão "Cancelar"
        JButton btnLimpar = new JButton("Limpar");              // Botão "Limpar"

        linhaBotoes.add(btnSalvar);                             // Adiciona "Salvar"
        linhaBotoes.add(Box.createRigidArea(new Dimension(6, 0))); // Espaço 6px
        linhaBotoes.add(btnCancelar);                           // Adiciona "Cancelar"
        linhaBotoes.add(Box.createRigidArea(new Dimension(6, 0))); // Espaço 6px
        linhaBotoes.add(btnLimpar);                             // Adiciona "Limpar"

        p.add(linhaBotoes);                                     // Adiciona a linha de botões ao painel

        // Texto explicativo sobre BoxLayout
        p.add(Box.createRigidArea(new Dimension(0, 10)));       // Espaço vertical
        JTextArea dica = new JTextArea(
            "BoxLayout: organiza em coluna (Y_AXIS) ou linha (X_AXIS).\n" +
            "Com linhas auxiliares e rótulos com largura fixa, dá para alinhar formulários com elegância."
        );
        dica.setEditable(false);                                // Somente leitura
        dica.setOpaque(false);                                  // Fundo transparente
        dica.setAlignmentX(Component.LEFT_ALIGNMENT);           // Alinha o texto à esquerda
        p.add(dica);                                            // Adiciona explicação

        return p;                                               // Retorna o painel pronto
    }

    // ===============================
    // 3) Exemplo com GridBagLayout
    // ===============================
    private JPanel criarPainelGridBag() {                       // Método que monta um painel usando GridBagLayout
        JPanel p = new JPanel(new GridBagLayout());             // Usa GridBagLayout diretamente no construtor
        GridBagConstraints gbc = new GridBagConstraints();      // Objeto de restrições para cada componente

        // Configurações base que se repetem:
        gbc.insets = new Insets(5, 8, 5, 8);                    // Margens internas (topo, esquerda, baixo, direita)
        gbc.anchor = GridBagConstraints.WEST;                   // Alinha conteúdo à esquerda dentro da célula
        gbc.fill = GridBagConstraints.NONE;                     // Não “estica” os componentes por padrão
        gbc.weightx = 0;                                        // Peso horizontal inicial (0 = não expande)
        gbc.weighty = 0;                                        // Peso vertical inicial (0 = não expande)

        // Título (linha 0, ocupa 2 colunas)
        JLabel lblTitulo = new JLabel("Formulário (GridBagLayout)"); // Título
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f)); // Negrito e maior
        gbc.gridx = 0;                                          // Coluna 0
        gbc.gridy = 0;                                          // Linha 0
        gbc.gridwidth = 2;                                      // Ocupa 2 colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;               // Título pode esticar horizontalmente
        gbc.weightx = 1;                                        // Deixa o título ocupar largura disponível
        p.add(lblTitulo, gbc);                                  // Adiciona o título com as restrições atuais

        // Reseta para próximo componente (para não “vazar” configurações)
        gbc.gridwidth = 1;                                      // Volta a ocupar 1 coluna
        gbc.fill = GridBagConstraints.NONE;                     // Não esticar os próximos por padrão
        gbc.weightx = 0;                                        // Peso horizontal volta a 0

        // Linha "Nome" (rótulo na coluna 0, campo na coluna 1)
        JLabel lblNome = new JLabel("Nome:");                   // Rótulo "Nome"
        gbc.gridx = 0;                                          // Coluna 0
        gbc.gridy = 1;                                          // Linha 1
        p.add(lblNome, gbc);                                    // Adiciona o rótulo

        JTextField txtNome = new JTextField(20);                // Campo de texto
        gbc.gridx = 1;                                          // Coluna 1
        gbc.gridy = 1;                                          // Linha 1
        gbc.fill = GridBagConstraints.HORIZONTAL;               // Campo deve esticar na horizontal
        gbc.weightx = 1;                                        // Dá peso para expandir quando a janela cresce
        p.add(txtNome, gbc);                                    // Adiciona o campo
        gbc.fill = GridBagConstraints.NONE;                     // Reseta para o próximo
        gbc.weightx = 0;                                        // Reseta peso

        // Linha "E-mail"
        JLabel lblEmail = new JLabel("E-mail:");                // Rótulo "E-mail"
        gbc.gridx = 0;                                          // Coluna 0
        gbc.gridy = 2;                                          // Linha 2
        p.add(lblEmail, gbc);                                   // Adiciona rótulo

        JTextField txtEmail = new JTextField(20);               // Campo e-mail
        gbc.gridx = 1;                                          // Coluna 1
        gbc.gridy = 2;                                          // Linha 2
        gbc.fill = GridBagConstraints.HORIZONTAL;               // Estica horizontalmente
        gbc.weightx = 1;                                        // Peso para expandir
        p.add(txtEmail, gbc);                                   // Adiciona campo
        gbc.fill = GridBagConstraints.NONE;                     // Reseta
        gbc.weightx = 0;                                        // Reseta

        // Linha "Senha"
        JLabel lblSenha = new JLabel("Senha:");                 // Rótulo "Senha"
        gbc.gridx = 0;                                          // Coluna 0
        gbc.gridy = 3;                                          // Linha 3
        p.add(lblSenha, gbc);                                   // Adiciona rótulo

        JPasswordField txtSenha = new JPasswordField(20);       // Campo senha
        gbc.gridx = 1;                                          // Coluna 1
        gbc.gridy = 3;                                          // Linha 3
        gbc.fill = GridBagConstraints.HORIZONTAL;               // Estica horizontalmente
        gbc.weightx = 1;                                        // Peso para expandir
        p.add(txtSenha, gbc);                                   // Adiciona campo
        gbc.fill = GridBagConstraints.NONE;                     // Reseta
        gbc.weightx = 0;                                        // Reseta

        // Linha de botões (podemos alinhar os três na coluna 1, usando um painel auxiliar com FlowLayout LEFT)
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); // Painel só para agrupar botões
        JButton btnSalvar = new JButton("Salvar");              // Botão "Salvar"
        JButton btnCancelar = new JButton("Cancelar");          // Botão "Cancelar"
        JButton btnLimpar = new JButton("Limpar");              // Botão "Limpar"
        botoes.add(btnSalvar);                                  // Adiciona "Salvar"
        botoes.add(btnCancelar);                                // Adiciona "Cancelar"
        botoes.add(btnLimpar);                                  // Adiciona "Limpar"

        gbc.gridx = 1;                                          // Coluna 1
        gbc.gridy = 4;                                          // Linha 4
        gbc.fill = GridBagConstraints.NONE;                     // Não precisa esticar
        gbc.anchor = GridBagConstraints.WEST;                   // Alinha o grupo de botões à esquerda
        p.add(botoes, gbc);                                     // Adiciona o painel de botões

        // Mensagem explicativa (ocupa 2 colunas, estica horizontal)
        JTextArea dica = new JTextArea(
            "GridBagLayout: mais flexível e poderoso. Você controla linha/coluna, \n" +
            "largura (gridwidth), preenchimento (fill), alinhamento (anchor) e pesos (weightx/weighty)."
        );
        dica.setEditable(false);                                // Somente leitura
        dica.setOpaque(false);                                  // Fundo transparente

        gbc.gridx = 0;                                          // Coluna 0
        gbc.gridy = 5;                                          // Linha 5
        gbc.gridwidth = 2;                                      // Ocupa 2 colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;               // Estica horizontalmente
        gbc.weightx = 1;                                        // Pode expandir se sobrar espaço
        p.add(dica, gbc);                                       // Adiciona a explicação

        return p;                                               // Retorna o painel pronto
    }


    public static void main(String[] args) {                 // Ponto de entrada da aplicação
        SwingUtilities.invokeLater(() -> {                   // Garante que a UI suba na EDT (thread do Swing)
            LayoutsTeste app = new LayoutsTeste(); // Instancia a janela principal
            app.setVisible(true);                            // Exibe a janela
        });
    }
    
}

