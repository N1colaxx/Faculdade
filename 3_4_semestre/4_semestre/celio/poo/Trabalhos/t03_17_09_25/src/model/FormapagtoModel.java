package model;

public class FormapagtoModel {
    
    private int FPG_CODIGO;
    private String 
            FPG_NOME,
            FPG_ATIVO;


    public FormapagtoModel() { }

    public FormapagtoModel(int FPG_CODIGO, String FPG_NOME, String FPG_ATIVO) {
        this.FPG_CODIGO = FPG_CODIGO;
        this.FPG_NOME = FPG_NOME;
        this.FPG_ATIVO = FPG_ATIVO;
    }

    // Getters
    public int getFPG_CODIGO() { return FPG_CODIGO; }
    public String getFPG_NOME() { return FPG_NOME; }
    public String getFPG_ATIVO() { return FPG_ATIVO; }

    // Setters
    public void setFPG_CODIGO(int FPG_CODIGO) {
        this.FPG_CODIGO = FPG_CODIGO;
    }

    public void setFPG_NOME(String FPG_NOME) {
        this.FPG_NOME = FPG_NOME;
    }

    public void setFPG_ATIVO(String FPG_ATIVO) {
        this.FPG_ATIVO = FPG_ATIVO;
    }
}
