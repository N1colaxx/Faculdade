package aula1;

import javax.swing.*; // Importa os componentes Swing

public class Exemplo1 extends JFrame {

    // Declaração dos componentes
    JLabel lblTitulo, lblRA, lblNome;
    JTextField edtRA, edtNome;

    // Construtor
    public Exemplo1() {
        setLayout(null); // Usando layout absoluto
        setTitle("Exemplo 1 ");
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Inicializando os componentes
        lblTitulo = new JLabel("Título do Formulário");
        lblRA = new JLabel("RA:");
        lblNome = new JLabel("Nome:");
        edtRA = new JTextField();
        edtNome = new JTextField();

        ConfigurarPosicoes();
        AdicionarComponentes();

        setVisible(true); // Exibe a janela
    }

    //    Possicionar os componentes
    private void ConfigurarPosicoes() {
        // setBounds( posicao x(coluna), posicao y(linha), largura, altura )
        lblTitulo.setBounds(115, 15, 500, 25);
        lblRA.setBounds(25, 55, 100, 25);
        lblNome.setBounds(25, 90, 100, 25);
        edtRA.setBounds(125, 55, 375, 25);
        edtNome.setBounds(125, 90, 375, 25);
    }

    //    ADD os componentes ao JFrame
    public void AdicionarComponentes() {
        add(lblTitulo);   // adiciona o Label do Título no Frame
        add(lblRA);       // adiciona o Label do RA no Frame
        add(lblNome);     // adiciona o Label do Nome no Frame
        add(edtRA);       // adiciona o TextField do RA no Frame
        add(edtNome);     // adiciona o TextField do Nome no Frame
    }

    public static void main(String[] args) {
        new Exemplo1();
    }
}
