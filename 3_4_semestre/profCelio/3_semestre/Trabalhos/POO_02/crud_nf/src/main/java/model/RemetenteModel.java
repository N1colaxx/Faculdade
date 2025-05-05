package model;

public class RemetenteModel extends PessoaModel {

    public RemetenteModel(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        super(razaoSocial, cpfCnpj, endereco);
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setEndereco(EnderecoModel endereco) {
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

    @Override
    public String toString() {
        return "| =============== REMETENTE ===============" + "\n"
                + "| Razao Social = " + getRazaoSocial() + "\n"
                + "| cpfCnpj='" + getCpfCnpj() + "\n"
                + "| " + getEndereco() + "\n"
                + "|==============================================";
    }

}
