package model;

import java.math.BigDecimal;

public class CompraProduto {

    private int pk_cpp_codigo,
            fk_cpr_codigo,
            fk_pro_codigo;
    private BigDecimal cpr_qtde,
            cpr_preco,
            cpr_desconto,
            cpr_total;


    public CompraProduto() {
    }


    public CompraProduto(int pk_cpp_codigo, int fk_cpr_codigo, int fk_pro_codigo,
            BigDecimal cpr_qtde, BigDecimal cpr_preco,
            BigDecimal cpr_desconto, BigDecimal cpr_total) {
        this.pk_cpp_codigo = pk_cpp_codigo;
        this.fk_cpr_codigo = fk_cpr_codigo;
        this.fk_pro_codigo = fk_pro_codigo;
        this.cpr_qtde = cpr_qtde;
        this.cpr_preco = cpr_preco;
        this.cpr_desconto = cpr_desconto;
        this.cpr_total = cpr_total;
    }

    // Getters
    public int getPkCppCodigo() {
        return pk_cpp_codigo;
    }

    public int getFkCprCodigo() {
        return fk_cpr_codigo;
    }

    public int getFkProCodigo() {
        return fk_pro_codigo;
    }

    public BigDecimal getCprQtde() {
        return cpr_qtde;
    }

    public BigDecimal getCprPreco() {
        return cpr_preco;
    }

    public BigDecimal getCprDesconto() {
        return cpr_desconto;
    }

    public BigDecimal getCprTotal() {
        return cpr_total;
    }

    // Setters
    public void setPkCppCodigo(int pk_cpp_codigo) {
        this.pk_cpp_codigo = pk_cpp_codigo;
    }

    public void setFkCprCodigo(int fk_cpr_codigo) {
        this.fk_cpr_codigo = fk_cpr_codigo;
    }

    public void setFkProCodigo(int fk_pro_codigo) {
        this.fk_pro_codigo = fk_pro_codigo;
    }

    public void setCprQtde(BigDecimal cpr_qtde) {
        this.cpr_qtde = cpr_qtde;
    }

    public void setCprPreco(BigDecimal cpr_preco) {
        this.cpr_preco = cpr_preco;
    }

    public void setCprDesconto(BigDecimal cpr_desconto) {
        this.cpr_desconto = cpr_desconto;
    }

    public void setCprTotal(BigDecimal cpr_total) {
        this.cpr_total = cpr_total;
    }
}
