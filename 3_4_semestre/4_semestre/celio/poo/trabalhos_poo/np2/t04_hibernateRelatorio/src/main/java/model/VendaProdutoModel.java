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
@Table (name = "VENDA_PRODUTO")
public class VendaProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column (name = "VEP_CODIGO")
    private Integer vep_codigo;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "VDA_CODIGO")
    private VendaModel venda;
    
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "PRO_CODIGO")
    private ProdutoModel produto;
    
    private double vep_qtde;
    private double vep_preco;
    private double vep_desconto;
    private double vep_total;
    
    public VendaProdutoModel() {
        
    }

    public VendaProdutoModel(Integer vep_codigo, VendaModel venda, ProdutoModel produto, double vep_qtde, double vep_preco, double vep_desconto, double vep_total) {
        this.vep_codigo = vep_codigo;
        this.venda = venda;
        this.produto = produto;
        this.vep_qtde = vep_qtde;
        this.vep_preco = vep_preco;
        this.vep_desconto = vep_desconto;
        this.vep_total = vep_total;
    }

    
    /***
     *  Getters
     */
     
    public Integer getVep_codigo() {
        return vep_codigo;
    }

    public VendaModel getVenda_VendaProduto() {
        return venda;
    }

    public ProdutoModel getProduto_VendaProduto() {
        return produto;
    }

    @Column (name = "VEP_QTDE", nullable = true, precision = 14, scale = 4)
    public double getVep_qtde() {
        return vep_qtde;
    }

    @Column (name = "VEP_PRECO", nullable = true, precision = 18, scale = 2)
    public double getVep_preco() {
        return vep_preco;
    }

    @Column (name = "VEP_DESCONTO", nullable = true, precision = 18, scale = 2)
    public double getVep_desconto() {
        return vep_desconto;
    }

    @Column (name = "VEP_TOTAL", nullable = true, precision = 18, scale = 2)
    public double getVep_total() {
        return vep_total;
    }

    /**
     * Setters
     */
    
    public void setVep_codigo(Integer vep_codigo) {
        this.vep_codigo = vep_codigo;
    }

    public void setVenda_VendaProduto(VendaModel venda) {
        this.venda = venda;
    }

    public void setProduto_VendaProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public void setVep_qtde(double vep_qtde) {
        this.vep_qtde = vep_qtde;
    }

    public void setVep_preco(double vep_preco) {
        this.vep_preco = vep_preco;
    }

    public void setVep_desconto(double vep_desconto) {
        this.vep_desconto = vep_desconto;
    }

    public void setVep_total(double vep_total) {
        this.vep_total = vep_total;
    }
    
}
