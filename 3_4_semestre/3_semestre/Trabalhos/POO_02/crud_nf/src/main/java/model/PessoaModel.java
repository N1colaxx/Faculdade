package model;

public abstract class PessoaModel {

    protected String razaoSocial;
    protected String  Cnpj, Cpf;
    protected EnderecoModel endereco;

    public PessoaModel(String razaoSocial, String Cnpj, String Cpf, EnderecoModel endereco) {
        this.razaoSocial = razaoSocial;
        this.Cnpj = Cnpj;
        this.Cpf = Cpf;
        this.endereco = endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCpfCnpj() {
        return Cnpj;
    }
    
    public String getCpf() {
        return Cpf;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.Cnpj = Cnpj;
    }
    
    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
