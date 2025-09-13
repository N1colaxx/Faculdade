package model;

public class FornecedorModel {
    
    private int 
            FOR_CODIGO,
            PES_CODIGO;
    private String FOR_CONTATO;


    public FornecedorModel() { }


    public FornecedorModel(int FOR_CODIGO, int PES_CODIGO, String FOR_CONTATO) {
        this.FOR_CODIGO = FOR_CODIGO;
        this.PES_CODIGO = PES_CODIGO;
        this.FOR_CONTATO = FOR_CONTATO;
    }

    // Getters
    public int getFOR_CODIGO() { return FOR_CODIGO; }
    public int getPES_CODIGO() { return PES_CODIGO; }
    public String getFOR_CONTATO() { return FOR_CONTATO; }

    // Setters
    public void setFOR_CODIGO(int FOR_CODIGO) {
        this.FOR_CODIGO = FOR_CODIGO;
    }

    public void setPES_CODIGO(int PES_CODIGO) {
        this.PES_CODIGO = PES_CODIGO;
    }

    public void setFOR_CONTATO(String FOR_CONTATO) {
        this.FOR_CONTATO = FOR_CONTATO;
    }
}
