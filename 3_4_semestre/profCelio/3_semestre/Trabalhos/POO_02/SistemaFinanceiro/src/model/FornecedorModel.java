
package model;


public class FornecedorModel extends PessoaJuridicaModel {
    
    protected double limiteCompra;
    protected String dataCadastro;
    protected String site;

    public FornecedorModel() {
    }

    public FornecedorModel(double limiteCompra, String dataCadastro, String site, String cnpj, String inscricaoEstadual, String contato, int id, String nome, String email, EnderecoModel endereco, TelefoneModel telefone) {
        super(cnpj, inscricaoEstadual, contato, id, nome, email, endereco, telefone);
        this.limiteCompra = limiteCompra;
        this.dataCadastro = dataCadastro;
        this.site = site;
    }

    public double getLimiteCompra() {
        return limiteCompra;
    }

    public void setLimiteCompra(double limiteCompra) {
        this.limiteCompra = limiteCompra;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
}
