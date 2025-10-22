package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


/**
 * Representa tabela cliente
 */

@Entity
@Table(name = "CLIENTE")
public class ClienteModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_CODIGO",  nullable = false)
    private Integer cli_codigo;
    
    @OneToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn (name = "PES_CODIGO", nullable = false)
    private PessoaModel pessoa;

    private double cli_limitecred;

    public ClienteModel() {
    }

    public ClienteModel(Integer cli_codigo, PessoaModel pessoa, double cli_limitecred) {
        this.cli_codigo = cli_codigo;
        this.pessoa = pessoa;
        this.cli_limitecred = cli_limitecred;
    }

    /** 
     * GETTERS
     */
    public Integer getCLI_CODIGO() {
        return cli_codigo;
    }
    
    public PessoaModel getPessoa_Cliente() {
        return pessoa;
    }
    
    @Column(name = "CLI_LIMITECRED", nullable = true, precision = 18, scale = 2)
    public double getCLI_LIMITECRED() {
        return cli_limitecred;
    }

    /**
     * SETTERS
     */
    
    public void setCLI_CODIGO(Integer cli_codigo) {
        this.cli_codigo = cli_codigo;
    }
    
    public void setPessoa_Cliente(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }
    
    public void setCLI_LIMITECRED(double cli_limitecred) {
        this.cli_limitecred = cli_limitecred;
    }

    public void parseInt(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
