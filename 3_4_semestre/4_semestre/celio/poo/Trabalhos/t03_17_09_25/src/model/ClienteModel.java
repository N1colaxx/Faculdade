package model;

import java.math.BigDecimal;


public class ClienteModel {
    
    private int 
            CLI_CODIGO,
            PES_CODIGO;
    
    private BigDecimal CLI_LIMITECRED;
    
    public ClienteModel() {
        
    }
    
    public ClienteModel(int CLI_CODIGO, int PES_CODIGO, BigDecimal CLI_LIMITECRED) {
        this.CLI_CODIGO = CLI_CODIGO;
        this.PES_CODIGO = PES_CODIGO;
        this.CLI_LIMITECRED = CLI_LIMITECRED;
    }
    
    // Getters
    public int getCLI_CODIGO() { return CLI_CODIGO; }
    public int getPES_CODIG() { return PES_CODIGO; }
    public BigDecimal getCLI_LIMETECRED() { return CLI_LIMITECRED; }
    
    // Setters
    public void setCLI_CODIGO (int CLI_CODIGO) {
        this.CLI_CODIGO = CLI_CODIGO;
    }
    
    public void setPES_CODIGO (int PES_CODIGO) {
        this.PES_CODIGO = PES_CODIGO;
    }
    
    public void setCLI_LIMITECRED (BigDecimal CLI_LIMETECRED) {
        this.CLI_LIMITECRED = CLI_LIMETECRED;
    }
}
