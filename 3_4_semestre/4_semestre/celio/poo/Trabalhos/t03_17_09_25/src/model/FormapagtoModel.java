package model;

/** Representa uma forma de pagamento (tabela formapagto). */
public class FormapagtoModel {
    private int FPG_CODIGO;
    private String FPG_NOME;
    private String FPG_ATIVO; // "S" / "N"

    public FormapagtoModel(){}
    public FormapagtoModel(int FPG_CODIGO, String FPG_NOME, String FPG_ATIVO){
        this.FPG_CODIGO = FPG_CODIGO;
        this.FPG_NOME = FPG_NOME;
        this.FPG_ATIVO = FPG_ATIVO;
    }

    public int getFPG_CODIGO(){ return FPG_CODIGO; }
    public void setFPG_CODIGO(int v){ FPG_CODIGO = v; }
    public String getFPG_NOME(){ return FPG_NOME; }
    public void setFPG_NOME(String v){ FPG_NOME = v; }
    public String getFPG_ATIVO(){ return FPG_ATIVO; }
    public void setFPG_ATIVO(String v){ FPG_ATIVO = v; }
}
