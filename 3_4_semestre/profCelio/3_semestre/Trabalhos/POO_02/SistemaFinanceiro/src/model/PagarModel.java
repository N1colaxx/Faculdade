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
    
}
