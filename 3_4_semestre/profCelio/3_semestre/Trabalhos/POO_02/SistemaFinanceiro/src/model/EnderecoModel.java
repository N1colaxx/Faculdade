package model;

public class EnderecoModel {
    
    private String logradouro, numero, complemento, bairro, cidade, estado;
    private int cep;

    public EnderecoModel() {
    }

    public EnderecoModel(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, int cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
    
    @Override
    public String toString() {
        return
               "Logradouro: " + logradouro + "\n" +
               "Numero: "+ numero + "\n" +
    //         Se o complemento não for nulo nem vazio, adiciona ele entre parênteses.Caso contrário, não adiciona nada.
               "Complemento: " + (complemento != null && !complemento.isEmpty() ? " (" + complemento + ")" : " Nenhum") + "\n" + 
               "Bairro: " + bairro + "\n"  + 
               "Cidade: " + cidade + " - " + estado + "\n" + 
               "CEP: " + cep;
    }



    
}
