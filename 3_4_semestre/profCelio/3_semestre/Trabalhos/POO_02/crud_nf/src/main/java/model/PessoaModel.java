package model;

public abstract class PessoaModel {

    protected String razaoSocial;
    protected String cpfCnpj;
    protected EnderecoModel endereco;

    public PessoaModel(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        this.razaoSocial = razaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }
}
 