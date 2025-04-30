package model;


public abstract class PessoaFisicaModel extends PessoaModel {
    protected String cpf;
    protected String rg;
    protected String emissor;

    public PessoaFisicaModel() {
    }

    public PessoaFisicaModel(String cpf, String rg, String emissor, int id, String nome, String email, EnderecoModel endereco, TelefoneModel telefone) {
        super(id, nome, email, endereco, telefone);
        this.cpf = cpf;
        this.rg = rg;
        this.emissor = emissor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }
}