package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

public class VendaPagtoModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "VDP_CODIGO")
    private Integer vdp_codigo;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "VDA_CODIGO")
    private VendaModel vda_codigo; // tab venda

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "FPG_CODIGO")
    private FormapagtoModel pfg_codigo; // tab Forma de Pagamento
    
    private double vdp_valor;
    
    public VendaPagtoModel() {    
    }
 
    
    public VendaPagtoModel(Integer vdp_codigo, VendaModel vda_codigo, FormapagtoModel pfg_codigo, double vdp_valor) {
        this.vdp_codigo = vdp_codigo;
        this.vda_codigo = vda_codigo;
        this.pfg_codigo = pfg_codigo;
        this.vdp_valor = vdp_valor;
    }

    /**
     * Getters
     */

    public Integer getVdp_codigo() {
        return vdp_codigo;
    }

    public VendaModel getVda_codigo() {
        return vda_codigo;
    }

    public FormapagtoModel getPfg_codigo() {
        return pfg_codigo;
    }

    @Column (name = "VDP_VALOR", nullable = true, precision = 18, scale = 2)
    public double getVdp_valor() {
        return vdp_valor;
    }
    
     /** 
     * Setters 
     */

    public void setVdp_codigo(Integer vdp_codigo) {
        this.vdp_codigo = vdp_codigo;
    }

    public void setVda_codigo(VendaModel vda_codigo) {
        this.vda_codigo = vda_codigo;
    }

    public void setPfg_codigo(FormapagtoModel pfg_codigo) {
        this.pfg_codigo = pfg_codigo;
    }

    public void setVdp_valor(double vdp_valor) {
        this.vdp_valor = vdp_valor;
    }
    

    
    
}
