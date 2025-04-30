
package model;


public class ClienteModel extends PessoaJuridicaModel {
    
    protected double limiteCredito;
    protected EnderecoModel enderecoCobranca;

    public ClienteModel() {
    }

    public ClienteModel(double limiteCredito, EnderecoModel enderecoCobranca, String cnpj, String inscricaoEstadual, String contato, int id, String nome, String email, EnderecoModel endereco, TelefoneModel telefone) {
        super(cnpj, inscricaoEstadual, contato, id, nome, email, endereco, telefone);
        this.limiteCredito = limiteCredito;
        this.enderecoCobranca = enderecoCobranca;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public EnderecoModel getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoModel enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }
 
}
