package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "COMPRA_PRODUTO")
public class CompraProdutoModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CPP_CODIGO", nullable = false)
    private Integer cpp_codigo;
    
    @ManyToOne
    @JoinColumn(name = "PRO_CODIGO", nullable = false)
    private ProdutoModel produto_compraPro;
    
    @ManyToOne
    @JoinColumn (name = "CPR_CODIGO", nullable = false)
    private CompraModel compra_compraPro;
    
    private double cpr_qtde;
    private double cpr_preco;
    private double cpr_desconto;
    private double cpr_total;
    
    public CompraProdutoModel() {
        
    }

    public CompraProdutoModel(Integer cpp_codigo, ProdutoModel produto_compraPro, CompraModel compra_compraPro, double cpr_qtde, double cpr_preco, double cpr_desconto, double cpr_total) {
        this.cpp_codigo = cpp_codigo;
        this.produto_compraPro = produto_compraPro;
        this.compra_compraPro = compra_compraPro;
        this.cpr_qtde = cpr_qtde;
        this.cpr_preco = cpr_preco;
        this.cpr_desconto = cpr_desconto;
        this.cpr_total = cpr_total;
    }

    /**
     * Getters
     */
    public Integer getCpp_codigo() {
        return cpp_codigo;
    }

    public ProdutoModel getProduto_compraPro() {
        return produto_compraPro;
    }

    public CompraModel getCompra_compraPro() {
        return compra_compraPro;
    }

    @Column(name = "CPR_QTDE", nullable = false, precision = 14, scale = 4)
    public double getCpr_qtde() {
        return cpr_qtde;
    }
    
    @Column(name = "CPR_QTDE", nullable = false, precision = 18, scale = 2)
    public double getCpr_preco() {
        return cpr_preco;
    
    }
    @Column(name = "CPR_QTDE", nullable = true, precision = 18, scale = 2)
    public double getCpr_desconto() {
        return cpr_desconto;
    }
    
    @Column(name = "CPR_QTDE", nullable = true, precision = 18, scale = 2)
    public double getCpr_total() {
        return cpr_total;
    }

    /**
     * Setters
     */
    public void setCpp_codigo(Integer cpp_codigo) {
        this.cpp_codigo = cpp_codigo;
    }

    public void setProduto_compraPro(ProdutoModel produto_compraPro) {
        this.produto_compraPro = produto_compraPro;
    }

    public void setCompra_compraPro(CompraModel compra_compraPro) {
        this.compra_compraPro = compra_compraPro;
    }

    public void setCpr_qtde(double cpr_qtde) {
        this.cpr_qtde = cpr_qtde;
    }

    public void setCpr_preco(double cpr_preco) {
        this.cpr_preco = cpr_preco;
    }

    public void setCpr_desconto(double cpr_desconto) {
        this.cpr_desconto = cpr_desconto;
    }

    public void setCpr_total(double cpr_total) {
        this.cpr_total = cpr_total;
    }
}
