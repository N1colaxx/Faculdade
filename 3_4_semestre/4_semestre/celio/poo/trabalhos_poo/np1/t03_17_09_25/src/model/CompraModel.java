package model;

import java.time.LocalDate;

public class CompraModel {

    private int 
            CPR_CODIGO,
            USU_CODIGO,
            FOR_CODIGO;
    private double 
            CPR_VALOR,
            CPR_DESCONTO,
            CPR_TOTAL;
    private LocalDate
            CPR_EMISSAO,
            CPR_DTENTRADA;
    private String CPR_OBS;


    public CompraModel() { }


    public CompraModel(int CPR_CODIGO, int USU_CODIGO, int FOR_CODIGO,
                       double CPR_VALOR, double CPR_DESCONTO, double CPR_TOTAL,
                       LocalDate CPR_EMISSAO, LocalDate CPR_DTENTRADA, String CPR_OBS) {
        this.CPR_CODIGO = CPR_CODIGO;
        this.USU_CODIGO = USU_CODIGO;
        this.FOR_CODIGO = FOR_CODIGO;
        this.CPR_VALOR = CPR_VALOR;
        this.CPR_DESCONTO = CPR_DESCONTO;
        this.CPR_TOTAL = CPR_TOTAL;
        this.CPR_EMISSAO = CPR_EMISSAO;
        this.CPR_DTENTRADA = CPR_DTENTRADA;
        this.CPR_OBS = CPR_OBS;
    }

    // Getters
    public int getCPR_CODIGO() { return CPR_CODIGO; }
    public int getUSU_CODIGO() { return USU_CODIGO; }
    public int getFOR_CODIGO() { return FOR_CODIGO; }
    public double getCPR_VALOR() { return CPR_VALOR; }
    public double getCPR_DESCONTO() { return CPR_DESCONTO; }
    public double getCPR_TOTAL() { return CPR_TOTAL; }
    public LocalDate getCPR_EMISSAO() { return CPR_EMISSAO; }
    public LocalDate getCPR_DTENTRADA() { return CPR_DTENTRADA; }
    public String getCPR_OBS() { return CPR_OBS; }

    // Setters
    public void setCPR_CODIGO(int CPR_CODIGO) {
        this.CPR_CODIGO = CPR_CODIGO;
    }

    public void setUSU_CODIGO(int USU_CODIGO) {
        this.USU_CODIGO = USU_CODIGO;
    }

    public void setFOR_CODIGO(int FOR_CODIGO) {
        this.FOR_CODIGO = FOR_CODIGO;
    }

    public void setCPR_VALOR(double CPR_VALOR) {
        this.CPR_VALOR = CPR_VALOR;
    }

    public void setCPR_DESCONTO(double CPR_DESCONTO) {
        this.CPR_DESCONTO = CPR_DESCONTO;
    }

    public void setCPR_TOTAL(double CPR_TOTAL) {
        this.CPR_TOTAL = CPR_TOTAL;
    }

    public void setCPR_EMISSAO(LocalDate CPR_EMISSAO) {
        this.CPR_EMISSAO = CPR_EMISSAO;
    }

    public void setCPR_DTENTRADA(LocalDate CPR_DTENTRADA) {
        this.CPR_DTENTRADA = CPR_DTENTRADA;
    }

    public void setCPR_OBS(String CPR_OBS) {
        this.CPR_OBS = CPR_OBS;
    }
}
