package revisaoNP1;

// Classe que representa uma loja
public class Loja {

    private int id; // Atributo ID
    private String nomeLoja;
    private Produto produtoAdicionado; // Um único produto associado à loja

    // Construtor
    public Loja(int id, String nomeLoja) {
        this.id = id;
        this.nomeLoja = nomeLoja;
    }

    // Métodos getters e setters para encapsulamento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public Produto getProdutoAdicionado() {
        return produtoAdicionado;
    }

    public void adicionarProduto(Produto produto) {
        this.produtoAdicionado = produto;
    }

    public void excluirProduto() {
        this.produtoAdicionado = null;
    }

    // Método para exibir o produto adicionado à loja
    public void exibirProduto() {
        if (produtoAdicionado != null) {
            System.out.println("Produto na loja " + nomeLoja + ":");
            produtoAdicionado.exibirDetalhes();
        } else {
            System.out.println("Nenhum produto adicionado à loja.");
        }
    }
}
