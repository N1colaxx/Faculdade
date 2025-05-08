package model;

public class RemetenteModel extends PessoaModel {

    public RemetenteModel(String razaoSocial, String Cnpj, String Cpf, EnderecoModel endereco) {
        super(razaoSocial, Cnpj, Cpf, endereco);
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }
    
    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }
    
    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return Cnpj;
    }
    
    public String getCpf() {
        return Cpf;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return "| =============== REMETENTE ===============" + "\n"
                + "| Razao Social = " + getRazaoSocial() + "\n"
                + "| Cnpj='" + getCpfCnpj() + "\n"
                + "| CPF ='" + getCpf() + "\n"
                + "| " + getEndereco() + "\n"
                + "|==============================================";
    }

}
