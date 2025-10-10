package model;

import model.PessoaModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "FORNECEDOR")
public class FornecedorModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_CODIGO")
    private int FOR_CODIGO;

//    JOIN com pessoa -> pessoa 1:N fornecedor
    @OneToOne
    @JoinColumn (name = "PES_CODIGO")
    private PessoaModel PES_CODIGO;
    
    @Column(name = "FOR_CONTATO", nullable = true, length = 80)
    private String FOR_CONTATO;

    public FornecedorModel() {
    }

    public FornecedorModel(int FOR_CODIGO, PessoaModel PES_CODIGO, String FOR_CONTATO ) {
        this.FOR_CODIGO = FOR_CODIGO;
        this.PES_CODIGO = PES_CODIGO;
        this.FOR_CONTATO = FOR_CONTATO;
    }

    public int getFOR_CODIGO() {
        return FOR_CODIGO;
    }

    public void setFOR_CODIGO(int FOR_CODIGO) {
        this.FOR_CODIGO = FOR_CODIGO;
    }
    
    public PessoaModel getPES_CODIGO() {
        return PES_CODIGO;
    }

    public void setPES_CODIGO(PessoaModel PES_CODIGO){
        this.PES_CODIGO = PES_CODIGO;
    }
    
    public String getFOR_CONTATO() {
        return FOR_CONTATO;
    }

    public void setFOR_CONTATO(String FOR_CONTATO) {
        this.FOR_CONTATO = FOR_CONTATO;
    }
}
