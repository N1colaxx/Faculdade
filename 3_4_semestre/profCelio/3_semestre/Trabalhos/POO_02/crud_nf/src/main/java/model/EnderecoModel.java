package model;

public class EnderecoModel {

    private String logradouro, numero, bairro, cidade, estado, cep;

    public EnderecoModel(String logradouro, String numero, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

//    Getters
    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

//     Setters
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    @Override
    public String toString() {
        return 
               "logradouro = " + logradouro + "\n" +
               "| numero = " + numero + "\n" +
               "| bairro = " + bairro + "\n" +
               "| cidade = " + cidade + "\n" +
               "| estado = " + estado + "\n" +
               "| cep = " + cep;
    }


}
