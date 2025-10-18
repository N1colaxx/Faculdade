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
    
    private double cpp_qtde;
    private double cpp_preco;
    private double cpp_desconto;
    private double cpp_total;
    
    public CompraProdutoModel() {
        
    }

    public CompraProdutoModel(Integer cpp_codigo, ProdutoModel produto_compraPro, CompraModel compra_compraPro, double cpp_qtde, double cpp_preco, double cpp_desconto, double cpp_total) {
        this.cpp_codigo = cpp_codigo;
        this.produto_compraPro = produto_compraPro;
        this.compra_compraPro = compra_compraPro;
        this.cpp_qtde = cpp_qtde;
        this.cpp_preco = cpp_preco;
        this.cpp_desconto = cpp_desconto;
        this.cpp_total = cpp_total;
    }
    
    /**
     * Getters
     * @return 
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

    @Column (name = "CPP_QTDE", nullable = false, precision = 14, scale = 4)
    public double getCpp_qtde() {
        return cpp_qtde;
    }

    @Column (name = "CPP_PRECO", nullable = false, precision = 18, scale = 2)
    public double getCpp_preco() {
        return cpp_preco;
    }

    @Column (name = "CPP_DESCONTO", nullable = true, precision = 18, scale = 2)
    public double getCpp_desconto() {
        return cpp_desconto;
    }

    @Column (name = "CPP_TOTAL", nullable = true, precision = 18, scale = 2)
    public double getCpp_total() {
        return cpp_total;
    }
    
    /**
     * Setters
     * 
     * @param cpp_codigo
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

    public void setCpp_qtde(double cpp_qtde) {
        this.cpp_qtde = cpp_qtde;
    }

    public void setCpp_preco(double cpp_preco) {
        this.cpp_preco = cpp_preco;
    }

    public void setCpp_desconto(double cpp_desconto) {
        this.cpp_desconto = cpp_desconto;
    }

    public void setCpp_total(double cpp_total) {
        this.cpp_total = cpp_total;
    }
    
    
    
    
}
