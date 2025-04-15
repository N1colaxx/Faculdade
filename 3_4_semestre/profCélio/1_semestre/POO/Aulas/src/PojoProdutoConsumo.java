



public class PojoProdutoConsumo {
    private int id;
    private String nome;
    private double preco;
    private String validade;

    public PojoProdutoConsumo() {
    }

    public PojoProdutoConsumo(int id, String nome, double preco, String validade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getValidade() {
        return validade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Produto de Consumo: " + nome +
               ", Preço: R$" + preco +
               ", Validade: " + validade;
    }
}
