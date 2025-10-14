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


@Entity
@Table (name = "VENDAPAGTO")
public class VendapagtoModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "VDP_CODIGO")
    private Integer vdp_codigo;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "VDA_CODIGO")
    private VendaModel venda; // tab venda

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "FPG_CODIGO")
    private FormapagtoModel formapagto; // tab Forma de Pagamento
    
    private double vdp_valor;
    
    public VendapagtoModel() {    
    }
 
    
    public VendapagtoModel(Integer vdp_codigo, VendaModel venda, FormapagtoModel pfg_codigo, double vdp_valor) {
        this.vdp_codigo = vdp_codigo;
        this.venda = venda;
        this.formapagto = pfg_codigo;
        this.vdp_valor = vdp_valor;
    }

    /**
     * Getters
     */

    public Integer getVdp_codigo() {
        return vdp_codigo;
    }

    public VendaModel getVenda_Vendapagto() {
        return venda;
    }

    public FormapagtoModel getFormapagto_Vendapagto() {
        return formapagto;
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

    public void setVenda_Vendapagto(VendaModel vda_codigo) {
        this.venda = vda_codigo;
    }

    public void setFormapagto_Vendapagto(FormapagtoModel pfg_codigo) {
        this.formapagto = pfg_codigo;
    }

    public void setVdp_valor(double vdp_valor) {
        this.vdp_valor = vdp_valor;
    }
    

    
    
}
