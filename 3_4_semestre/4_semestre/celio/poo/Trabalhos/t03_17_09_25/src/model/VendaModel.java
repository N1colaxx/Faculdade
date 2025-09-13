package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaModel {
    
    private int 
            VDA_CODIGO,
            USU_CODIGO,
            CLI_CODIGO;
    private BigDecimal 
            VDA_VALOR,
            VDA_DESCONTO,
            VDA_TOTAL;
    private String VDA_OBS;
    private LocalDate VDA_DATE;
    
    
    public VendaModel() {
        
    }
    
    public VendaModel(int VDA_CODIGO, int USU_CODIGO, int CLI_CODIGO,
                    BigDecimal VDA_VALOR, BigDecimal VDA_DESCONTO, BigDecimal VDA_TOTAL,
                    String VDA_OBS, LocalDate VDA_DATE) {
        this.VDA_CODIGO = VDA_CODIGO;
        this.USU_CODIGO = USU_CODIGO;
        this.CLI_CODIGO = CLI_CODIGO;
        this.VDA_VALOR = VDA_VALOR;
        this.VDA_DESCONTO = VDA_DESCONTO;
        this.VDA_TOTAL = VDA_TOTAL;
        this.VDA_OBS = VDA_OBS;
        this.VDA_DATE = VDA_DATE;
    }
    
    // Getters
    public int getVDA_CODIGO() { return VDA_CODIGO; }
    public int getUSU_CODIGO() { return USU_CODIGO; }
    public int getCLI_CODIGO() { return CLI_CODIGO; }
    public BigDecimal getVDA_VALOR() { return VDA_VALOR; }
    public BigDecimal getVDA_DESCONTO() { return VDA_DESCONTO; }
    public BigDecimal getVDA_TOTAL() { return VDA_TOTAL; }
    public String getVDA_OBS() { return VDA_OBS; }
    public LocalDate getVDA_DATE() { return VDA_DATE; }

    // Setters
    public void setVDA_CODIGO(int VDA_CODIGO) {
        this.VDA_CODIGO = VDA_CODIGO;
    }

    public void setUSU_CODIGO(int USU_CODIGO) {
        this.USU_CODIGO = USU_CODIGO;
    }

    public void setCLI_CODIGO(int CLI_CODIGO) {
        this.CLI_CODIGO = CLI_CODIGO;
    }

    public void setVDA_VALOR(BigDecimal VDA_VALOR) {
        this.VDA_VALOR = VDA_VALOR;
    }

    public void setVDA_DESCONTO(BigDecimal VDA_DESCONTO) {
        this.VDA_DESCONTO = VDA_DESCONTO;
    }

    public void setVDA_TOTAL(BigDecimal VDA_TOTAL) {
        this.VDA_TOTAL = VDA_TOTAL;
    }

    public void setVDA_OBS(String VDA_OBS) {
        this.VDA_OBS = VDA_OBS;
    }

    public void setVDA_DATE(LocalDate VDA_DATE) {
        this.VDA_DATE = VDA_DATE;
    }

}
