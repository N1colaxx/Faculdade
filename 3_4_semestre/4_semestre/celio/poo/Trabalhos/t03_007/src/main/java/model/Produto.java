package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {

    private int pk_pro_codigo;
    private String pro_nome,
            pro_unidade,
            pro_obs,
            pro_ativo;
    private BigDecimal pro_estoque,
            pro_preco,
            pro_custo,
            pro_atacado,
            pro_mim,
            pro_max,
            pro_embalagem,
            pro_peso;
    private LocalDate pro_cadastro;


    public Produto() {
    }


    public Produto(int pk_pro_codigo, String pro_nome, String pro_unidade, String pro_obs, String pro_ativo,
            BigDecimal pro_estoque, BigDecimal pro_preco, BigDecimal pro_custo, BigDecimal pro_atacado,
            BigDecimal pro_mim, BigDecimal pro_max, BigDecimal pro_embalagem, BigDecimal pro_peso,
            LocalDate pro_cadastro) {
        this.pk_pro_codigo = pk_pro_codigo;
        this.pro_nome = pro_nome;
        this.pro_unidade = pro_unidade;
        this.pro_obs = pro_obs;
        this.pro_ativo = pro_ativo;
        this.pro_estoque = pro_estoque;
        this.pro_preco = pro_preco;
        this.pro_custo = pro_custo;
        this.pro_atacado = pro_atacado;
        this.pro_mim = pro_mim;
        this.pro_max = pro_max;
        this.pro_embalagem = pro_embalagem;
        this.pro_peso = pro_peso;
        this.pro_cadastro = pro_cadastro;
    }

    // Getters
    public int getPkProCodigo() {
        return pk_pro_codigo;
    }

    public String getProNome() {
        return pro_nome;
    }

    public String getProUnidade() {
        return pro_unidade;
    }

    public String getProObs() {
        return pro_obs;
    }

    public String getProAtivo() {
        return pro_ativo;
    }

    public BigDecimal getProEstoque() {
        return pro_estoque;
    }

    public BigDecimal getProPreco() {
        return pro_preco;
    }

    public BigDecimal getProCusto() {
        return pro_custo;
    }

    public BigDecimal getProAtacado() {
        return pro_atacado;
    }

    public BigDecimal getProMim() {
        return pro_mim;
    }

    public BigDecimal getProMax() {
        return pro_max;
    }

    public BigDecimal getProEmbalagem() {
        return pro_embalagem;
    }

    public BigDecimal getProPeso() {
        return pro_peso;
    }

    public LocalDate getProCadastro() {
        return pro_cadastro;
    }

    // Setters
    public void setPkProCodigo(int pk_pro_codigo) {
        this.pk_pro_codigo = pk_pro_codigo;
    }

    public void setProNome(String pro_nome) {
        this.pro_nome = pro_nome;
    }

    public void setProUnidade(String pro_unidade) {
        this.pro_unidade = pro_unidade;
    }

    public void setProObs(String pro_obs) {
        this.pro_obs = pro_obs;
    }

    public void setProAtivo(String pro_ativo) {
        this.pro_ativo = pro_ativo;
    }

    public void setProEstoque(BigDecimal pro_estoque) {
        this.pro_estoque = pro_estoque;
    }

    public void setProPreco(BigDecimal pro_preco) {
        this.pro_preco = pro_preco;
    }

    public void setProCusto(BigDecimal pro_custo) {
        this.pro_custo = pro_custo;
    }

    public void setProAtacado(BigDecimal pro_atacado) {
        this.pro_atacado = pro_atacado;
    }

    public void setProMim(BigDecimal pro_mim) {
        this.pro_mim = pro_mim;
    }

    public void setProMax(BigDecimal pro_max) {
        this.pro_max = pro_max;
    }

    public void setProEmbalagem(BigDecimal pro_embalagem) {
        this.pro_embalagem = pro_embalagem;
    }

    public void setProPeso(BigDecimal pro_peso) {
        this.pro_peso = pro_peso;
    }

    public void setProCadastro(LocalDate pro_cadastro) {
        this.pro_cadastro = pro_cadastro;
    }
}
