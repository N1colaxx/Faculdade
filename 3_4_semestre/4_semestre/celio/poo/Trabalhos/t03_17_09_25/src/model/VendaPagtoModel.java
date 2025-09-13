package model;

import java.math.BigDecimal;

public class VendaPagtoModel {

    private int
            VDP_CODIGO,
            VDA_CODIGO,
            FPG_CODIG;
    private BigDecimal VDP_VALOR;

    
    public VendaPagtoModel() { }

    public VendaPagtoModel(int VDP_CODIGO, int VDA_CODIGO, int FPG_CODIG, BigDecimal VDP_VALOR) {
        this.VDP_CODIGO = VDP_CODIGO;
        this.VDA_CODIGO = VDA_CODIGO;
        this.FPG_CODIG = FPG_CODIG;
        this.VDP_VALOR = VDP_VALOR;
    }

    // Getters
    public int getVDP_CODIGO() { return VDP_CODIGO; }
    public int getVDA_CODIGO() { return VDA_CODIGO; }
    public int getFPG_CODIG() { return FPG_CODIG; }
    public BigDecimal getVDP_VALOR() { return VDP_VALOR; }

    // Setters
    public void setVDP_CODIGO(int VDP_CODIGO) {
        this.VDP_CODIGO = VDP_CODIGO;
    }

    public void setVDA_CODIGO(int VDA_CODIGO) {
        this.VDA_CODIGO = VDA_CODIGO;
    }

    public void setFPG_CODIG(int FPG_CODIG) {
        this.FPG_CODIG = FPG_CODIG;
    }

    public void setVDP_VALOR(BigDecimal VDP_VALOR) {
        this.VDP_VALOR = VDP_VALOR;
    }
}
