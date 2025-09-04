package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private int pk_cpr_codigo,
            fk_usu_codigo,
            fk_for_codigo;
    private LocalDate cpr_emissao,
            cpr_dtentrada;
    private BigDecimal cpr_valor,
            cpr_desconto,
            cpr_total;
    private String cpr_obs;

    // Construtor vazio
    public Compra() {
    }

    // Construtor completo
    public Compra(int pk_cpr_codigo, int fk_usu_codigo, int fk_for_codigo,
            LocalDate cpr_emissao, LocalDate cpr_dtentrada,
            BigDecimal cpr_valor, BigDecimal cpr_desconto,
            BigDecimal cpr_total, String cpr_obs) {
        this.pk_cpr_codigo = pk_cpr_codigo;
        this.fk_usu_codigo = fk_usu_codigo;
        this.fk_for_codigo = fk_for_codigo;
        this.cpr_emissao = cpr_emissao;
        this.cpr_dtentrada = cpr_dtentrada;
        this.cpr_valor = cpr_valor;
        this.cpr_desconto = cpr_desconto;
        this.cpr_total = cpr_total;
        this.cpr_obs = cpr_obs;
    }

    // Getters
    public int getPkCprCodigo() {
        return pk_cpr_codigo;
    }

    public int getFkUsuCodigo() {
        return fk_usu_codigo;
    }

    public int getFkForCodigo() {
        return fk_for_codigo;
    }

    public LocalDate getCprEmissao() {
        return cpr_emissao;
    }

    public LocalDate getCprDtentrada() {
        return cpr_dtentrada;
    }

    public BigDecimal getCprValor() {
        return cpr_valor;
    }

    public BigDecimal getCprDesconto() {
        return cpr_desconto;
    }

    public BigDecimal getCprTotal() {
        return cpr_total;
    }

    public String getCprObs() {
        return cpr_obs;
    }

    // Setters
    public void setPkCprCodigo(int pk_cpr_codigo) {
        this.pk_cpr_codigo = pk_cpr_codigo;
    }

    public void setFkUsuCodigo(int fk_usu_codigo) {
        this.fk_usu_codigo = fk_usu_codigo;
    }

    public void setFkForCodigo(int fk_for_codigo) {
        this.fk_for_codigo = fk_for_codigo;
    }

    public void setCprEmissao(LocalDate cpr_emissao) {
        this.cpr_emissao = cpr_emissao;
    }

    public void setCprDtentrada(LocalDate cpr_dtentrada) {
        this.cpr_dtentrada = cpr_dtentrada;
    }

    public void setCprValor(BigDecimal cpr_valor) {
        this.cpr_valor = cpr_valor;
    }

    public void setCprDesconto(BigDecimal cpr_desconto) {
        this.cpr_desconto = cpr_desconto;
    }

    public void setCprTotal(BigDecimal cpr_total) {
        this.cpr_total = cpr_total;
    }

    public void setCprObs(String cpr_obs) {
        this.cpr_obs = cpr_obs;
    }
}
