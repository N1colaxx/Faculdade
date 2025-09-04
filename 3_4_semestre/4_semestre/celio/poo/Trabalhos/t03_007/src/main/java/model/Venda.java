package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {

    // Dados Tabela de Vendas
    private int pk_vda_codigo, fk_usu_codigo, fk_cli_codigo;
    private LocalDate vda_date;
    private BigDecimal vda_valor, vda_desconto, vda_total;
    private String vda_obs;

    public Venda() {
    }

    public Venda(int pk_vda_codigo, int fk_cli_codigo, int pf_usu_codigo,
            LocalDate vda_date, BigDecimal vda_desconto, String vda_obs, BigDecimal vda_total, BigDecimal vda_valor) {

        this.pk_vda_codigo = pk_vda_codigo;
        this.fk_cli_codigo = fk_cli_codigo;
        this.fk_usu_codigo = pf_usu_codigo;
        this.vda_date = vda_date;
        this.vda_desconto = vda_desconto;
        this.vda_obs = vda_obs;
        this.vda_total = vda_total;
        this.vda_valor = vda_valor;
    }

    // Getters
    public int getPkVdaCodigo() {
        return pk_vda_codigo;
    }

    public int getFkCliCodigo() {
        return fk_cli_codigo;
    }

    public int getFkUsuCodigo() {
        return fk_usu_codigo;
    }

    public LocalDate getVdaDate() {
        return vda_date;
    }

    public BigDecimal getVdaDesconto() {
        return vda_desconto;
    }

    public String getVdaObs() {
        return vda_obs;
    }

    public BigDecimal getVdaTotal() {
        return vda_total;
    }

    public BigDecimal getVdaValor() {
        return vda_valor;
    }

    //Setters
    public void setPkVdaCodigo(int pk_vda_codigo) {
        this.pk_vda_codigo = pk_vda_codigo;
    }

    public void setFkCliCodigo(int fk_cli_codigo) {
        this.fk_cli_codigo = fk_cli_codigo;
    }

    public void setFkUsuCodigo(int fk_usu_codigo) {
        this.fk_usu_codigo = fk_usu_codigo;
    }

    public void setVdaDate(LocalDate vda_date) {
        this.vda_date = vda_date;
    }

    public void setVdaDesconto(BigDecimal vda_desconto) {
        this.vda_desconto = vda_desconto;
    }

    public void setVdaObs(String vda_obs) {
        this.vda_obs = vda_obs;
    }

    public void setVdaTotal(BigDecimal vda_total) {
        this.vda_total = vda_total;
    }

    public void setVdaValor(BigDecimal vda_valor) {
        this.vda_valor = vda_valor;
    }

}
