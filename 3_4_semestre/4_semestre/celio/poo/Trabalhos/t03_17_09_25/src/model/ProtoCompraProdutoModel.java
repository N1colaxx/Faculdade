package model;


import java.time.LocalDate;

/** Produto simplificado para a tela de COMPRA. */
public class ProtoCompraProdutoModel {
    private int PRO_CODIGO;
    private String PRO_NOME;
    private String PRO_UNIDADE;
    private double PRO_CUSTO;      // preço de compra sugerido
    private String PRO_ATIVO;
    private LocalDate PRO_CADASTRO;

    public ProtoCompraProdutoModel(){}

    public int getPRO_CODIGO(){ return PRO_CODIGO; }
    public void setPRO_CODIGO(int v){ PRO_CODIGO = v; }

    public String getPRO_NOME(){ return PRO_NOME; }
    public void setPRO_NOME(String v){ PRO_NOME = v; }

    public String getPRO_UNIDADE(){ return PRO_UNIDADE; }
    public void setPRO_UNIDADE(String v){ PRO_UNIDADE = v; }

    public double getPRO_CUSTO(){ return PRO_CUSTO; }
    public void setPRO_CUSTO(double v){ PRO_CUSTO = v; }

    public String getPRO_ATIVO(){ return PRO_ATIVO; }
    public void setPRO_ATIVO(String v){ PRO_ATIVO = v; }

    public LocalDate getPRO_CADASTRO(){ return PRO_CADASTRO; }
    public void setPRO_CADASTRO(LocalDate v){ PRO_CADASTRO = v; }
}
