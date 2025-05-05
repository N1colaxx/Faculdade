package model;

public class EmitenteModel extends PessoaModel {

    public EmitenteModel(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        super(razaoSocial, cpfCnpj, endereco);
    }
    
    @Override
    public String toString() {
        return 
               "|==============================================" + "\n" +
               "| =============== EMITENTE ===============" + "\n" +  
               "| Razao Social = " + getRazaoSocial() + "\n" +
               "| CNPJ = " + getCpfCnpj() + "\n" +
               "|           -- ENDERECO -- " + "\n" + 
               "| " +getEndereco() + "\n" + 
               "|==============================================";
    }

}
