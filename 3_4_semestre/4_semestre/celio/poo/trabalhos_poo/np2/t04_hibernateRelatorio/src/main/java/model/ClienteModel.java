package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * Representa tabela cliente
 */

@Entity
@Table(name = "CLIENTE")
public class ClienteModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_codigo")
    private int cli_codigo;
    
    @ManyToOne
    @JoinColumn (name = "PES_CODIGO")
    private PessoaModel pessoa;

    private double cli_limitecred;

    public ClienteModel() {
    }

    public ClienteModel(int cli_codigo, PessoaModel pessoa, double cli_limitecred) {
        this.cli_codigo = cli_codigo;
        this.pessoa = pessoa;
        this.cli_limitecred = cli_limitecred;
    }

    /** 
     * GETTERS
     */
    public int getcli_codigo() {
        return cli_codigo;
    }
    
    public PessoaModel getpessoa() {
        return pessoa;
    }
    
    @Column(name = "CLI_LIMITECRED", nullable = true, precision = 18, scale = 2)
    public double getCLI_LIMITECRED() {
        return cli_limitecred;
    }

    /**
     * SETTERS
     */
    
    public void setcli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }
    
    public void setPES_CODIGO(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }
    
    public void setCLI_LIMITECRED(double cli_limitecred) {
        this.cli_limitecred = cli_limitecred;
    }
}
