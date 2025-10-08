package model;

import java.time.LocalDate;

/**
 * Cabeçalho da Venda (tabela venda).
 */
public class VendaModel {

    private int VDA_CODIGO;
    private int USU_CODIGO;
    private int CLI_CODIGO;
    private LocalDate VDA_DATA;
    private double VDA_VALOR;     // soma itens
    private double VDA_DESCONTO;  // desconto total
    private double VDA_TOTAL;     // VALOR - DESCONTO
    private String VDA_OBS;

    public VendaModel() {
    }

    public VendaModel(int VDA_CODIGO, int USU_CODIGO, int CLI_CODIGO,
            LocalDate VDA_DATA, double VDA_VALOR, double VDA_DESCONTO,
            double VDA_TOTAL, String VDA_OBS) {
        this.VDA_CODIGO = VDA_CODIGO;
        this.USU_CODIGO = USU_CODIGO;
        this.CLI_CODIGO = CLI_CODIGO;
        this.VDA_DATA = VDA_DATA;
        this.VDA_VALOR = VDA_VALOR;
        this.VDA_DESCONTO = VDA_DESCONTO;
        this.VDA_TOTAL = VDA_TOTAL;
        this.VDA_OBS = VDA_OBS;
    }

    public int getVDA_CODIGO() {
        return VDA_CODIGO;
    }

    public void setVDA_CODIGO(int v) {
        VDA_CODIGO = v;
    }

    public int getUSU_CODIGO() {
        return USU_CODIGO;
    }

    public void setUSU_CODIGO(int v) {
        USU_CODIGO = v;
    }

    public int getCLI_CODIGO() {
        return CLI_CODIGO;
    }

    public void setCLI_CODIGO(int v) {
        CLI_CODIGO = v;
    }

    public LocalDate getVDA_DATA() {
        return VDA_DATA;
    }

    public void setVDA_DATA(LocalDate v) {
        VDA_DATA = v;
    }

    public double getVDA_VALOR() {
        return VDA_VALOR;
    }

    public void setVDA_VALOR(double v) {
        VDA_VALOR = v;
    }

    public double getVDA_DESCONTO() {
        return VDA_DESCONTO;
    }

    public void setVDA_DESCONTO(double v) {
        VDA_DESCONTO = v;
    }

    public double getVDA_TOTAL() {
        return VDA_TOTAL;
    }

    public void setVDA_TOTAL(double v) {
        VDA_TOTAL = v;
    }

    public String getVDA_OBS() {
        return VDA_OBS;
    }

    public void setVDA_OBS(String v) {
        VDA_OBS = v;
    }
}
