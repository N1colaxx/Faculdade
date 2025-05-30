 package model;

public class EmitenteModel extends PessoaModel {
        
    public EmitenteModel(String razaoSocial, String Cnpj, String Cpf, EnderecoModel endereco) {
        super(razaoSocial, Cnpj, Cpf, endereco);
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
