package model;

import java.math.BigDecimal;

public class VendaPagto {

    private int pk_vdp_codigo,
            pf_vda_codigo,
            fk_fpg_codigo;
    private BigDecimal vdp_valor;

 
    public VendaPagto() {
    }

    // Construtor completo
    public VendaPagto(int pk_vdp_codigo, int pf_vda_codigo, int fk_fpg_codigo, BigDecimal vdp_valor) {
        this.pk_vdp_codigo = pk_vdp_codigo;
        this.pf_vda_codigo = pf_vda_codigo;
        this.fk_fpg_codigo = fk_fpg_codigo;
        this.vdp_valor = vdp_valor;
    }

    // Getters
    public int getPkVdpCodigo() {
        return pk_vdp_codigo;
    }

    public int getPfVdaCodigo() {
        return pf_vda_codigo;
    }

    public int getFkFpgCodigo() {
        return fk_fpg_codigo;
    }

    public BigDecimal getVdpValor() {
        return vdp_valor;
    }

    // Setters
    public void setPkVdpCodigo(int pk_vdp_codigo) {
        this.pk_vdp_codigo = pk_vdp_codigo;
    }

    public void setPfVdaCodigo(int pf_vda_codigo) {
        this.pf_vda_codigo = pf_vda_codigo;
    }

    public void setFkFpgCodigo(int fk_fpg_codigo) {
        this.fk_fpg_codigo = fk_fpg_codigo;
    }

    public void setVdpValor(BigDecimal vdp_valor) {
        this.vdp_valor = vdp_valor;
    }
}
