package model;

public class Remetente extends PessoaModel {

    public Remetente(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        super(razaoSocial, cpfCnpj, endereco);
    }
    
    @Override
    public String toString() {
        return 
               "| =============== REMETENTE ===============" + "\n" +
               "| Razao Social = " + getRazaoSocial() + "\n" +
               "| cpfCnpj='" + getCpfCnpj() + "\n" +
               "| "+ getEndereco() + "\n" +
               "|==============================================";
    }

}
