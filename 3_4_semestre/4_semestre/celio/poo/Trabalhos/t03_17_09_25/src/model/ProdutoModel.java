package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoModel {

    private int PRO_CODIGO;
    private BigDecimal 
            PRO_ESTOQUE,
            PRO_PRECO,
            PRO_CUSTO,
            PRO_ATACADO,
            PRO_MIN,
            PRO_MAX,
            PRO_EMBALAGEM,
            PRO_PESO;
    private LocalDate PRO_CADASTRO;
    private String
            PRO_NOME,
            PRO_UNIDADE, 
            PRO_OBS, 
            PRO_ATIVO;


    public ProdutoModel() { }


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

    // Getters
    public int getPRO_CODIGO() { return PRO_CODIGO; }
    public BigDecimal getPRO_ESTOQUE() { return PRO_ESTOQUE; }
    public BigDecimal getPRO_PRECO() { return PRO_PRECO; }
    public BigDecimal getPRO_CUSTO() { return PRO_CUSTO; }
    public BigDecimal getPRO_ATACADO() { return PRO_ATACADO; }
    public BigDecimal getPRO_MIN() { return PRO_MIN; }
    public BigDecimal getPRO_MAX() { return PRO_MAX; }
    public BigDecimal getPRO_EMBALAGEM() { return PRO_EMBALAGEM; }
    public BigDecimal getPRO_PESO() { return PRO_PESO; }
    public LocalDate getPRO_CADASTRO() { return PRO_CADASTRO; }
    public String getPRO_NOME() { return PRO_NOME; }
    public String getPRO_UNIDADE() { return PRO_UNIDADE; }
    public String getPRO_OBS() { return PRO_OBS; }
    public String getPRO_ATIVO() { return PRO_ATIVO; }

    // Setters
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
