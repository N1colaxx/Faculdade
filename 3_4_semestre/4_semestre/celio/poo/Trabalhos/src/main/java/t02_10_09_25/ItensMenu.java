package t02_10_09_25;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import t02_10_09_25.view.ViewVenda;
import t02_10_09_25.view.ViewCompra;

public final class ItensMenu extends JPanel {

    private Main classMain;

    // Referências de botões padrão
    private JButton btnCadastrar, btnCancelar;     // para cadastros
 
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public ItensMenu(Main classMain) {
        this.classMain = classMain;
        setLayout(new BorderLayout());
    }

    // ========= Helpers internos =========
    private void mostrar(JComponent conteudo) {
        removeAll();
        setLayout(new BorderLayout());
        add(conteudo, BorderLayout.CENTER);
        revalidate();
        repaint();
        classMain.defineFrame(1);
    }

    // ========= TELAS DE CADASTRO =========
    public void paneCliente() {
        JPanel col = UIHelper.colunaCentral();
        col.add(UIHelper.titulo("Cadastro de Cliente"));
        col.add(Box.createVerticalStrut(15));

        ViewPessoa viewPessoa = new ViewPessoa(UIHelper.DIM_LABEL, UIHelper.DIM_FIELD);
        viewPessoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        col.add(viewPessoa);

        col.add(Box.createVerticalStrut(15));

        btnCadastrar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        UIHelper.estilizarBotaoPrimario(btnCadastrar);
        UIHelper.estilizarBotaoPerigo(btnCancelar);

        col.add(UIHelper.barraBotoes(btnCadastrar, btnCancelar));

        mostrar(UIHelper.criarScrollPane(col));
    }

    public void paneFornecedor() {
        JPanel col = UIHelper.colunaCentral();
        col.add(UIHelper.titulo("Cadastro de Fornecedor"));
        col.add(Box.createVerticalStrut(15));

        ViewPessoa viewPessoa = new ViewPessoa(UIHelper.DIM_LABEL, UIHelper.DIM_FIELD);
        viewPessoa.adicionarExtras();
        viewPessoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        col.add(viewPessoa);

        col.add(Box.createVerticalStrut(15));

        btnCadastrar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        UIHelper.estilizarBotaoPrimario(btnCadastrar);
        UIHelper.estilizarBotaoPerigo(btnCancelar);

        col.add(UIHelper.barraBotoes(btnCadastrar, btnCancelar));

        mostrar(UIHelper.criarScrollPane(col));
    }

    public void paneFormaPagamento() {
        JPanel col = UIHelper.colunaCentral();
        col.add(UIHelper.titulo("Cadastro de Forma de Pagamento"));
        col.add(Box.createVerticalStrut(10));

        JTextField edtNome = new JTextField();
        UIHelper.padronizarCampo(edtNome);
        col.add(UIHelper.formRow("Nome:", edtNome));

        col.add(Box.createVerticalStrut(15));

        btnCadastrar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        UIHelper.estilizarBotaoPrimario(btnCadastrar);
        UIHelper.estilizarBotaoPerigo(btnCancelar);

        col.add(UIHelper.barraBotoes(btnCadastrar, btnCancelar));

        mostrar(UIHelper.criarScrollPane(col));
    }

    public void paneUsuario() {
        JPanel col = UIHelper.colunaCentral();
        col.add(UIHelper.titulo("Cadastro de Usuário"));
        col.add(Box.createVerticalStrut(10));

        JTextField edtNome = new JTextField();
        JTextField edtLogin = new JTextField();
        JPasswordField edtSenha = new JPasswordField();

        col.add(UIHelper.formRow("Nome:", edtNome));
        col.add(UIHelper.formRow("Login (e-mail):", edtLogin));
        col.add(UIHelper.formRow("Senha:", edtSenha));

        col.add(Box.createVerticalStrut(15));

        btnCadastrar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        UIHelper.estilizarBotaoPrimario(btnCadastrar);
        UIHelper.estilizarBotaoPerigo(btnCancelar);

        col.add(UIHelper.barraBotoes(btnCadastrar, btnCancelar));

        mostrar(UIHelper.criarScrollPane(col));
    }

    // ========= TELAS DE MOVIMENTO =========
    public void paneVendas() {
        removeAll();
        add(new ViewVenda(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void paneCompras() {
        removeAll();
        add(new ViewCompra(), BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
