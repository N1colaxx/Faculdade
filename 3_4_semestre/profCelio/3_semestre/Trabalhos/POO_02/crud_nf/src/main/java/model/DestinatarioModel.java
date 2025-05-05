package model;

public class DestinatarioModel extends PessoaModel {

    public DestinatarioModel(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        super(razaoSocial, cpfCnpj, endereco);
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

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "| =============== DESTINATARIO ===============" + "\n"
                + "| Razao Social = " + getRazaoSocial() + "\n"
                + "| CNPJ = " + getCpfCnpj() + "\n"
                + "|       -- ENDERECO -- " + "\n"
                + "| " + getEndereco() + "\n"
                + "|==============================================";
    }

}
