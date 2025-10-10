package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUTO")
public class ProdutoModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_CODIGO")
    private int PRO_CODIGO;

    private BigDecimal PRO_ESTOQUE;
    private BigDecimal PRO_PRECO;
    private BigDecimal PRO_CUSTO;
    private BigDecimal PRO_ATACADO;
    private BigDecimal PRO_MIN;
    private BigDecimal PRO_MAX;
    private BigDecimal PRO_EMBALAGEM;
    private BigDecimal PRO_PESO;
    private LocalDate PRO_CADASTRO;
    private String PRO_NOME;
    private String PRO_UNIDADE;
    private String PRO_OBS;
    private String PRO_ATIVO;

    public ProdutoModel() {
    }

    public ProdutoModel(int PRO_CODIGO, BigDecimal PRO_ESTOQUE, BigDecimal PRO_PRECO, BigDecimal PRO_CUSTO,
            BigDecimal PRO_ATACADO, BigDecimal PRO_MIN, BigDecimal PRO_MAX, BigDecimal PRO_EMBALAGEM,
            BigDecimal PRO_PESO, LocalDate PRO_CADASTRO, String PRO_NOME, String PRO_UNIDADE, String PRO_OBS, String PRO_ATIVO) {
        this.PRO_CODIGO = PRO_CODIGO;
        this.PRO_ESTOQUE = PRO_ESTOQUE;
        this.PRO_PRECO = PRO_PRECO;
        this.PRO_CUSTO = PRO_CUSTO;
        this.PRO_ATACADO = PRO_ATACADO;
        this.PRO_MIN = PRO_MIN;
        this.PRO_MAX = PRO_MAX;
        this.PRO_EMBALAGEM = PRO_EMBALAGEM;
        this.PRO_PESO = PRO_PESO;
        this.PRO_CADASTRO = PRO_CADASTRO;
        this.PRO_NOME = PRO_NOME;
        this.PRO_UNIDADE = PRO_UNIDADE;
        this.PRO_OBS = PRO_OBS;
        this.PRO_ATIVO = PRO_ATIVO;
    }

    /** 
     * GETTERS
     */
    public int getPRO_CODIGO() {
        return PRO_CODIGO;
    }

    @Column(name = "PRO_ESTOQUE", nullable = true, precision = 14, scale = 4)
    public BigDecimal getPRO_ESTOQUE() {
        return PRO_ESTOQUE;
    }


    @Column(name = "PRO_PRECO", nullable = true, precision = 18, scale = 2)
    public BigDecimal getPRO_PRECO() {
        return PRO_PRECO;
    }

    
    @Column(name = "PRO_CUSTO", nullable = true, precision = 18, scale = 2)
    public BigDecimal getPRO_CUSTO() {
        return PRO_CUSTO;
    }

    
    @Column(name = "PRO_ATACADO", nullable = true, precision = 18, scale = 2)
    public BigDecimal getPRO_ATACADO() {
        return PRO_ATACADO;
    }


    @Column(name = "PRO_MIN", nullable = true, precision = 14, scale = 4)
    public BigDecimal getPRO_MIN() {
        return PRO_MIN;
    }

    
    @Column(name = "PRO_MAX", nullable = true, precision = 14, scale = 4)
    public BigDecimal getPRO_MAX() {
        return PRO_MAX;
    }


    @Column(name = "PRO_EMBALAGEM", nullable = true, precision = 9, scale = 0)
    public BigDecimal getPRO_EMBALAGEM() {
        return PRO_EMBALAGEM;
    }

    
    @Column(name = "PRO_PESO", nullable = true, precision = 14, scale = 4)
    public BigDecimal getPRO_PESO() {
        return PRO_PESO;
    }
    
    
    @Column(name = "PRO_CADASTRO", nullable = true)
    public LocalDate getPRO_CADASTRO() {
        return PRO_CADASTRO;
    }

    
    @Column(name = "PRO_NOME", nullable = false, length = 80)
    public String getPRO_NOME() {
        return PRO_NOME;
    }


    @Column(name = "PRO_UNIDADE", nullable = true, length = 5)
    public String getPRO_UNIDADE() {
        return PRO_UNIDADE;
    }

    
    @Column(name = "PRO_OBS", nullable = true)
    public String getPRO_OBS() {
        return PRO_OBS;
    }

    
    @Column(name = "PRO_ATIVO", nullable = true, length = 1)
    public String getPRO_ATIVO() {
        return PRO_ATIVO;
    }

    /**
     *  SETTERS
     */
    public void setPRO_CODIGO(int PRO_CODIGO) {
        this.PRO_CODIGO = PRO_CODIGO;
    }

    public void setPRO_ESTOQUE(BigDecimal PRO_ESTOQUE) {
        this.PRO_ESTOQUE = PRO_ESTOQUE;
    }

    public void setPRO_PRECO(BigDecimal PRO_PRECO) {
        this.PRO_PRECO = PRO_PRECO;
    }

    public void setPRO_CUSTO(BigDecimal PRO_CUSTO) {
        this.PRO_CUSTO = PRO_CUSTO;
    }

    public void setPRO_ATACADO(BigDecimal PRO_ATACADO) {
        this.PRO_ATACADO = PRO_ATACADO;
    }

    public void setPRO_MIN(BigDecimal PRO_MIN) {
        this.PRO_MIN = PRO_MIN;
    }

    public void setPRO_MAX(BigDecimal PRO_MAX) {
        this.PRO_MAX = PRO_MAX;
    }

    public void setPRO_EMBALAGEM(BigDecimal PRO_EMBALAGEM) {
        this.PRO_EMBALAGEM = PRO_EMBALAGEM;
    }

    public void setPRO_PESO(BigDecimal PRO_PESO) {
        this.PRO_PESO = PRO_PESO;
    }

    public void setPRO_CADASTRO(LocalDate PRO_CADASTRO) {
        this.PRO_CADASTRO = PRO_CADASTRO;
    }

    public void setPRO_NOME(String PRO_NOME) {
        this.PRO_NOME = PRO_NOME;
    }

    public void setPRO_UNIDADE(String PRO_UNIDADE) {
        this.PRO_UNIDADE = PRO_UNIDADE;
    }

    public void setPRO_OBS(String PRO_OBS) {
        this.PRO_OBS = PRO_OBS;
    }

    public void setPRO_ATIVO(String PRO_ATIVO) {
        this.PRO_ATIVO = PRO_ATIVO;
    }
}
