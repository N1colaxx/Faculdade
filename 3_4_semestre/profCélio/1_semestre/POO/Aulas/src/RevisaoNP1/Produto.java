package revisaoNP1;

// Classe abstrata que define a base para todos os produtos
public abstract class Produto {

    private int id; // Atributo ID
    private String nome;
    private double preco;

    // Construtor da classe abstrata
    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Métodos getters e setters para encapsulamento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método abstrato que deve ser implementado pelas subclasses
    public abstract void exibirDetalhes();
}   