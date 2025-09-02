package model;

import java.math.BigDecimal;

public class VendaProduto {

    private int vep_codigo, fk_vda_codigo, fk_pro_codigo;
    private BigDecimal vep_qtde, vep_preco, vep_desconto, vep_total;

    public VendaProduto() {

    }

    public VendaProduto(int vep_codigo, int fk_vda_codigo, int fk_pro_codigo,
            BigDecimal vep_qtde, BigDecimal vep_preco,
            BigDecimal vep_desconto, BigDecimal vep_total) {
        this.vep_codigo = vep_codigo;
        this.fk_vda_codigo = fk_vda_codigo;
        this.fk_pro_codigo = fk_pro_codigo;
        this.vep_qtde = vep_qtde;
        this.vep_preco = vep_preco;
        this.vep_desconto = vep_desconto;
        this.vep_total = vep_total;
    }

    // Getters
    public int getVepCodigo() {
        return vep_codigo;
    }

    public int getFkVdaCodigo() {
        return fk_vda_codigo;
    }

    public int getFkProCodigo() {
        return fk_pro_codigo;
    }

    public BigDecimal getVepQtde() {
        return vep_qtde;
    }

    public BigDecimal getVepPreco() {
        return vep_preco;
    }

    public BigDecimal getVepDesconto() {
        return vep_desconto;
    }

    public BigDecimal getVepTotal() {
        return vep_total;
    }

    // Setters
    public void setVepCodigo(int vep_codigo) {
        this.vep_codigo = vep_codigo;
    }

    public void setFkVdaCodigo(int fk_vda_codigo) {
        this.fk_vda_codigo = fk_vda_codigo;
    }

    public void setFkProCodigo(int fk_pro_codigo) {
        this.fk_pro_codigo = fk_pro_codigo;
    }

    public void setVepQtde(BigDecimal vep_qtde) {
        this.vep_qtde = vep_qtde;
    }

    public void setVepPreco(BigDecimal vep_preco) {
        this.vep_preco = vep_preco;
    }

    public void setVepDesconto(BigDecimal vep_desconto) {
        this.vep_desconto = vep_desconto;
    }

    public void setVepTotal(BigDecimal vep_total) {
        this.vep_total = vep_total;
    }
}
