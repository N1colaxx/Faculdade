
package model;

public abstract class PessoaJuridicaModel extends PessoaModel{
    
    protected String cnpj, inscricaoEstadual, contato;

    public PessoaJuridicaModel() {
    }

    public PessoaJuridicaModel(String cnpj, String inscricaoEstadual, String contato, int id, String nome, String email, EnderecoModel endereco, TelefoneModel telefone) {
        super(id, nome, email, endereco, telefone);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
 
}
