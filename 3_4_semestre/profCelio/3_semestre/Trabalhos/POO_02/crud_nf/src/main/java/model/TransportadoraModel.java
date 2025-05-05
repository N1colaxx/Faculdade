package model;

public class TransportadoraModel {

    private String nome;
    private String cnpj;
    private EnderecoModel endereco;
    private TelefoneModel telefone;

    public TransportadoraModel(String nome, String cnpj, EnderecoModel endereco, TelefoneModel telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

//          Getters
    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public TelefoneModel getTelefone() {
        return telefone;
    }
    
    
//    Setter

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(TelefoneModel telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString() {
        return 
               "| =============== TRANSPORTADORA ===============" + "\n" +  
               "| Nome = " + nome + "\n" + 
               "| CNPJ = " + cnpj + "\n" + 
               "|  --- ENDERECO ---" + "\n" +  
               "| " + endereco + "\n" + 
               "| Telefone = " + telefone + "\n" + 
               "|==============================================";
    }

}
