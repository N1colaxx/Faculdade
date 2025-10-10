package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa uma forma de pagamento (tabela formapagto).
 */
@Entity
@Table(name = "FORMAPAGTO")
public class FormaPagtoModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_CODIGO")
    private int FPG_CODIGO;

    private String FPG_NOME;
    private String FPG_ATIVO; // "S" / "N"

    public FormaPagtoModel() {
    }

    public FormaPagtoModel(int FPG_CODIGO, String FPG_NOME, String FPG_ATIVO) {
        this.FPG_CODIGO = FPG_CODIGO;
        this.FPG_NOME = FPG_NOME;
        this.FPG_ATIVO = FPG_ATIVO;
    }

    public int getFPG_CODIGO() {
        return FPG_CODIGO;
    }

    public void setFPG_CODIGO(int v) {
        FPG_CODIGO = v;
    }


    @Column(name = "FPG_NOME", nullable = false, length = 80)
    public String getFPG_NOME() {
        return FPG_NOME;
    }

    public void setFPG_NOME(String v) {
        FPG_NOME = v;
    }

    
    @Column(name = "FPG_ATIVO", nullable = true, length = 1)
    public String getFPG_ATIVO() {
        return FPG_ATIVO;
    }

    public void setFPG_ATIVO(String v) {
        FPG_ATIVO = v;
    }
}
