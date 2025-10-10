package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class ClienteModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_CODIGO")
    private int CLI_CODIGO;
    
    @Column (name = "PES_CODIGO", nullable = false)
    private int PES_CODIGO;

    @Column(name = "CLI_LIMETECRED", nullable = true, precision = 18, scale = 2)
    private double CLI_LIMITECRED;

    public ClienteModel() {
    }

    public ClienteModel(int CLI_CODIGO, int PES_CODIGO, double CLI_LIMITECRED) {
        this.CLI_CODIGO = CLI_CODIGO;
        this.PES_CODIGO = PES_CODIGO;
        this.CLI_LIMITECRED = CLI_LIMITECRED;
    }

    // Getters/Setters
    public int getCLI_CODIGO() {
        return CLI_CODIGO;
    }
    
    public void setCLI_CODIGO(int CLI_CODIGO) {
        this.CLI_CODIGO = CLI_CODIGO;
    }
    
    public int getPES_CODIGO() {
        return PES_CODIGO;
    }
    
    public void setPES_CODIGO(int PES_CODIGO) {
        this.PES_CODIGO = PES_CODIGO;
    }
    
    public double getCLI_LIMITECRED() {
        return CLI_LIMITECRED;
    }

    public void setCLI_LIMITECRED(double CLI_LIMITECRED) {
        this.CLI_LIMITECRED = CLI_LIMITECRED;
    }
}
