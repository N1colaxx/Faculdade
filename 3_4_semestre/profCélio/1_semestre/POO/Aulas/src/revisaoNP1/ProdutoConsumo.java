package revisaoNP1;

// Classe para produtos de consumo (herança)
public class ProdutoConsumo extends Produto {

    private String dataValidade;

    // Construtor
    public ProdutoConsumo(int id, String nome, double preco, String dataValidade) {
        super(id, nome, preco);
        this.dataValidade = dataValidade;
    }


    // Getter e setter para dataValidade
    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }
    

        // Implementação do método abstrato da classe pai
    @Override
    public void exibirDetalhes() {
        System.out.println("ID: " + getId() + ", Produto de Consumo: " + getNome() + ", Preço: R$" + getPreco() + ", Validade: " + dataValidade);
    }
}
