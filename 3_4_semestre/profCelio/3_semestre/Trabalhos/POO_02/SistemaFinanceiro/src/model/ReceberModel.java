
package model;


public class ReceberModel extends FinanceiroModel {
    
    protected ClienteModel cliente;
    protected String notaFiscal;

    public ReceberModel() {
    }

    public ReceberModel(ClienteModel cliente, String notaFiscal, int id, int numero, String emissao, String vencimento, String pagamento, double valor, double juros, double multa, double desconto, double total) {
        super(id, numero, emissao, vencimento, pagamento, valor, juros, multa, desconto, total);
        this.cliente = cliente;
        this.notaFiscal = notaFiscal;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
    
    public String getCnpj() {
        // Verifica se o fornecedor é uma instância de PessoaJuridicaModel
        if (cliente instanceof PessoaJuridicaModel) {
            // Cast para PessoaJuridicaModel e acessa o CNPJ
            PessoaJuridicaModel pf = (PessoaJuridicaModel) cliente; // Declare a variável 'pj' aqui
            return pf.getCnpj();
        }
        // Se não for PJ, retorna um valor padrão ou vazio
        return "CNPJ não disponível";
    }
    
    @Override
    public String toString() {
        return "\n" + "\n" +
               " ----- INFO CONTAS A RECEBER  ----- " + "\n" +
               super.toString() + "\n" +
               "Nota Fiscal: " + notaFiscal +
                (cliente != null ? cliente.toString() : "Nenhum");
    }

    
    
}
