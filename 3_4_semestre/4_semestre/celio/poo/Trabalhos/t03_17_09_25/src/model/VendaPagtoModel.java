package model;

public class VendaPagtoModel {
    private int VDP_CODIGO;  // opcional para controle (PK)
    private int VDA_CODIGO;  // FK venda
    private int FPG_CODIGO;  // FK forma pagto
    private String FPG_NOME; // somente exibição
    private double VDP_VALOR;

    public VendaPagtoModel(){}

    // Getters/Setters
    public int getVDP_CODIGO() { return VDP_CODIGO; }
    public void setVDP_CODIGO(int v) { VDP_CODIGO = v; }

    public int getVDA_CODIGO() { return VDA_CODIGO; }
    public void setVDA_CODIGO(int v) { VDA_CODIGO = v; }

    public int getFPG_CODIGO() { return FPG_CODIGO; }
    public void setFPG_CODIGO(int v) { FPG_CODIGO = v; }

    public String getFPG_NOME() { return FPG_NOME; }
    public void setFPG_NOME(String v) { FPG_NOME = v; }

    public double getVDP_VALOR() { return VDP_VALOR; }
    public void setVDP_VALOR(double v) { VDP_VALOR = v; }
}
