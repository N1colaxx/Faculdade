package model;

public class Destinatario extends PessoaModel {

    public Destinatario(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        super(razaoSocial, cpfCnpj, endereco);
    }
    
    @Override
    public String toString() {
        return 
               "| =============== DESTINATARIO ===============" + "\n" +  
               "| Razao Social = " + getRazaoSocial() + "\n" + 
               "| CNPJ = " + getCpfCnpj() + "\n" + 
               "|       -- ENDERECO -- " + "\n" + 
               "| " + getEndereco() + "\n" + 
               "|==============================================";
    }

}
