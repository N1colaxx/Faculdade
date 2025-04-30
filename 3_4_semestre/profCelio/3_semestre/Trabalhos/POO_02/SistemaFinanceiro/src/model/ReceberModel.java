
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
    
}
