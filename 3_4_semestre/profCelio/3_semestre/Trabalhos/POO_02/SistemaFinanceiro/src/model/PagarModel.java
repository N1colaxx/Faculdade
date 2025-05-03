package model;


public class PagarModel extends FinanceiroModel {
    protected FornecedorModel fornecedor;
    protected String boleto;

    public PagarModel() {
    }

    public PagarModel(FornecedorModel fornecedor, String boleto, int id, int numero, String emissao, String vencimento, String pagamento, double valor, double juros, double multa, double desconto, double total) {
        super(id, numero, emissao, vencimento, pagamento, valor, juros, multa, desconto, total);
        this.fornecedor = fornecedor;
        this.boleto = boleto;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getBoleto() {
        return boleto;
    }

    public void setBoleto(String boleto) {
        this.boleto = boleto;
    }
    
    public String getCnpj() {
        // Verifica se o fornecedor é uma instância de PessoaJuridicaModel
        if (fornecedor instanceof PessoaJuridicaModel) {
            // Cast para PessoaJuridicaModel e acessa o CNPJ
            PessoaJuridicaModel pj = (PessoaJuridicaModel) fornecedor; // Declare a variável 'pj' aqui
            return pj.getCnpj();
        }
        // Se não for PJ, retorna um valor padrão ou vazio
        return "CNPJ não disponível";
    }

    
    @Override
    public String toString() {
        return 
                " ----- INFO CONTAS A PAGAR ----- " + "\n" +
                super.toString() + "\n" +
                "Boleto: " + boleto + "\n" +
                " -- INFO FORNECEDOR --" + "\n" +
               (fornecedor != null ? fornecedor.toString() : "Nenhum");
    }

}
