package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

/**
 * Representa tabela fornecedor
 */

@Entity
@Table(name = "FORNECEDOR")
public class FornecedorModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_CODIGO")
    private Integer for_codigo;

//  JOIN com pessoa -> pessoa 1:N fornecedor
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PES_CODIGO")
    private PessoaModel pessoa;

    private String for_contato;

    public FornecedorModel() {
    }

    public FornecedorModel(Integer for_codigo, PessoaModel pessoa, String for_contato) {
        this.for_codigo = for_codigo;
        this.pessoa = pessoa;
        this.for_contato = for_contato;
    }

    /**
     * GETTERS
     */
    public Integer getFOR_CODIGO() {
        return for_codigo;
    }

    public PessoaModel getPESSOA_FORNECEDOR() {
        return pessoa;
    }

    @Column(name = "FOR_CONTATO", nullable = true, length = 80)
    public String getFOR_CONTATO() {
        return for_contato;
    }

    /**
     * SETTERS
     */
    public void setFOR_CODIGO(Integer for_codigo) {
        this.for_codigo = for_codigo;
    }

    public void setPESSOA_FORNECEDOR(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }

    public void setFOR_CONTATO(String for_contato) {
        this.for_contato = for_contato;
    }
}
