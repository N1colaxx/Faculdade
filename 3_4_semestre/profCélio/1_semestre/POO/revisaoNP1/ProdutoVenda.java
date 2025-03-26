package revisaoNP1;

// Classe para produtos de venda (herança e implementação de interface)
public class ProdutoVenda extends Produto implements Vendavel {

    private int quantidadeEstoque;

    // Construtor
    public ProdutoVenda(int id, String nome, double preco, int quantidadeEstoque) {
        super(id, nome, preco);
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Implementação do método da interface Vendavel
    @Override
    public void vender() {
        if (quantidadeEstoque > 0) {
            quantidadeEstoque--;
            System.out.println("Vendido: " + getNome() + ". Estoque restante: " + quantidadeEstoque);
        } else {
            System.out.println("Estoque esgotado para o produto: " + getNome());
        }
    }

    // Implementação do método abstrato da classe pai
    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId() + ", Produto de Venda: " + getNome() + ", Preço: R$" + getPreco() + ", Estoque: " + quantidadeEstoque);
    }

    // Getter e setter para quantidadeEstoque
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
