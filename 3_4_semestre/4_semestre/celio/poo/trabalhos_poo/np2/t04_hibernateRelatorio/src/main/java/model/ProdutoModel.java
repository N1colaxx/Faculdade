package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * Representa tabela produto
 */

@Entity
@Table(name = "PRODUTO")
public class ProdutoModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_CODIGO")
    private int pro_codigo;
    private String pro_nome;
    private double  pro_estoque;
    private String pro_unidade;
    private double  pro_preco;
    private double  pro_custo;
    private double  pro_atacado;
    private double  pro_min;
    private double  pro_max;
    private double  pro_embalagem;
    private double  pro_peso;
    private LocalDate pro_cadastro;
    private String pro_obs;
    private String pro_ativo;

    public ProdutoModel() {
    }

    public ProdutoModel(int PRO_CODIGO, String PRO_NOME, double  PRO_ESTOQUE, String PRO_UNIDADE, double  PRO_PRECO, 
            double  PRO_CUSTO, double  PRO_ATACADO, double  PRO_MIN, double  PRO_MAX, double  PRO_EMBALAGEM,
            double  PRO_PESO, LocalDate PRO_CADASTRO, String PRO_OBS, String PRO_ATIVO) {
        this.pro_codigo = PRO_CODIGO;
        this.pro_nome = PRO_NOME;
        this.pro_estoque = PRO_ESTOQUE;
        this.pro_unidade = PRO_UNIDADE;
        this.pro_preco = PRO_PRECO;
        this.pro_custo = PRO_CUSTO;
        this.pro_atacado = PRO_ATACADO;
        this.pro_min= PRO_MIN;
        this.pro_max = PRO_MAX;
        this.pro_embalagem = PRO_EMBALAGEM;
        this.pro_peso = PRO_PESO;
        this.pro_cadastro = PRO_CADASTRO;
        this.pro_obs = PRO_OBS;
        this.pro_ativo = PRO_ATIVO;
    }

    /** 
     * GETTERS
     */
    public int getPRO_CODIGO() {
        return pro_codigo;
    }
    
    @Column(name = "PRO_NOME", nullable = false, length = 80)
    public String getPRO_NOME() {
        return pro_nome;
    }

    @Column(name = "PRO_ESTOQUE", nullable = true, precision = 14, scale = 4)
    public double  getPRO_ESTOQUE() {
        return pro_estoque;
    }
    
    
    @Column(name = "PRO_UNIDADE", nullable = true, length = 5)
    public String getPRO_UNIDADE() {
        return pro_unidade;
    }


    @Column(name = "PRO_PRECO", nullable = true, precision = 18, scale = 2)
    public double  getPRO_PRECO() {
        return pro_preco;
    }

    
    @Column(name = "PRO_CUSTO", nullable = true, precision = 18, scale = 2)
    public double  getPRO_CUSTO() {
        return pro_custo;
    }

    
    @Column(name = "PRO_ATACADO", nullable = true, precision = 18, scale = 2)
    public double  getPRO_ATACADO() {
        return pro_atacado;
    }


    @Column(name = "PRO_MIN", nullable = true, precision = 14, scale = 4)
    public double  getPRO_MIN() {
        return pro_min;
    }

    
    @Column(name = "PRO_MAX", nullable = true, precision = 14, scale = 4)
    public double  getPRO_MAX() {
        return pro_max;
    }


    @Column(name = "PRO_EMBALAGEM", nullable = true, precision = 9, scale = 0)
    public double  getPRO_EMBALAGEM() {
        return pro_embalagem;
    }

    
    @Column(name = "PRO_PESO", nullable = true, precision = 14, scale = 4)
    public double  getPRO_PESO() {
        return pro_peso;
    }
    
    
    @Column(name = "PRO_CADASTRO", nullable = true)
    public LocalDate getPRO_CADASTRO() {
        return pro_cadastro;
    }

    
    @Column(name = "PRO_OBS", nullable = true)
    public String getPRO_OBS() {
        return pro_obs;
    }

    
    @Column(name = "PRO_ATIVO", nullable = true, length = 1)
    public String getPRO_ATIVO() {
        return pro_ativo;
    }

    /**
     *  SETTERS
     */
    public void setPRO_CODIGO(int PRO_CODIGO) {
        this.pro_codigo = PRO_CODIGO;
    }
    
       
    public void setPRO_NOME(String PRO_NOME) {
        this.pro_nome = PRO_NOME;
    }

    public void setPRO_ESTOQUE(double  PRO_ESTOQUE) {
        this.pro_estoque = PRO_ESTOQUE;
    }
    
    public void setPRO_UNIDADE(String PRO_UNIDADE) {
        this.pro_unidade = PRO_UNIDADE;
    }

    public void setPRO_PRECO(double  PRO_PRECO) {
        this.pro_preco = PRO_PRECO;
    }

    public void setPRO_CUSTO(double  PRO_CUSTO) {
        this.pro_custo = PRO_CUSTO;
    }

    public void setPRO_ATACADO(double  PRO_ATACADO) {
        this.pro_atacado = PRO_ATACADO;
    }

    public void setPRO_MIN(double  PRO_MIN) {
        this.pro_min = PRO_MIN;
    }

    public void setPRO_MAX(double  PRO_MAX) {
        this.pro_max = PRO_MAX;
    }

    public void setPRO_EMBALAGEM(double  PRO_EMBALAGEM) {
        this.pro_embalagem = PRO_EMBALAGEM;
    }

    public void setPRO_PESO(double PRO_PESO) {
        this.pro_peso = PRO_PESO;
    }

    public void setPRO_CADASTRO(LocalDate PRO_CADASTRO) {
        this.pro_cadastro = PRO_CADASTRO;
    }


    public void setPRO_OBS(String PRO_OBS) {
        this.pro_obs = PRO_OBS;
    }

    public void setPRO_ATIVO(String PRO_ATIVO) {
        this.pro_ativo = PRO_ATIVO;
    }
}
