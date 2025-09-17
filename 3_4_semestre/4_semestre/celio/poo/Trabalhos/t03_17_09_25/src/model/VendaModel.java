package model;

import java.time.LocalDate;

public class VendaModel {
    private int VDA_CODIGO;
    private int USU_CODIGO;
    private int CLI_CODIGO;
    private LocalDate VDA_DATA;
    private double VDA_VALOR;
    private double VDA_DESCONTO;
    private double VDA_TOTAL;
    private String VDA_OBS;

    public VendaModel() {}

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

    // Getters
    public int getVDA_CODIGO() { return VDA_CODIGO; }
    public int getUSU_CODIGO() { return USU_CODIGO; }
    public int getCLI_CODIGO() { return CLI_CODIGO; }
    public LocalDate getVDA_DATA() { return VDA_DATA; }
    public double getVDA_VALOR() { return VDA_VALOR; }
    public double getVDA_DESCONTO() { return VDA_DESCONTO; }
    public double getVDA_TOTAL() { return VDA_TOTAL; }
    public String getVDA_OBS() { return VDA_OBS; }

    // Setters
    public void setVDA_CODIGO(int VDA_CODIGO) { this.VDA_CODIGO = VDA_CODIGO; }
    public void setUSU_CODIGO(int USU_CODIGO) { this.USU_CODIGO = USU_CODIGO; }
    public void setCLI_CODIGO(int CLI_CODIGO) { this.CLI_CODIGO = CLI_CODIGO; }
    public void setVDA_DATA(LocalDate VDA_DATA) { this.VDA_DATA = VDA_DATA; }
    public void setVDA_VALOR(double VDA_VALOR) { this.VDA_VALOR = VDA_VALOR; }
    public void setVDA_DESCONTO(double VDA_DESCONTO) { this.VDA_DESCONTO = VDA_DESCONTO; }
    public void setVDA_TOTAL(double VDA_TOTAL) { this.VDA_TOTAL = VDA_TOTAL; }
    public void setVDA_OBS(String VDA_OBS) { this.VDA_OBS = VDA_OBS; }
}
