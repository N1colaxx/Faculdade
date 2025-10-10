package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa tabela formapagto
 */
@Entity
@Table(name = "FORMAPAGTO")
public class FormaPagtoModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_CODIGO")
    private int fpg_codigo;

    private String fpg_nome;
    private String fpg_ativo; // "S" / "N"

    public FormaPagtoModel() {
    }

    public FormaPagtoModel(int FPG_CODIGO, String FPG_NOME, String FPG_ATIVO) {
        this.fpg_codigo = FPG_CODIGO;
        this.fpg_nome = FPG_NOME;
        this.fpg_ativo = FPG_ATIVO;
    }

    /**
     * GETTERS
     */
    public int getFPG_CODIGO() {
        return fpg_codigo;
    }

    ;
    @Column(name = "FPG_NOME", nullable = false, length = 80)
    public String getFPG_NOME() {
        return fpg_nome;
    }

    @Column(name = "FPG_ATIVO", nullable = true, length = 1)
    public String getFPG_ATIVO() {
        return fpg_ativo;
    }

    /**
     * SETTERS
     */
    public void setFPG_CODIGO(int v) {
        fpg_codigo = v;
    }

    public void setFPG_NOME(String v) {
        fpg_nome = v;
    }

    public void setFPG_ATIVO(String v) {
        fpg_ativo = v;
    }
}
