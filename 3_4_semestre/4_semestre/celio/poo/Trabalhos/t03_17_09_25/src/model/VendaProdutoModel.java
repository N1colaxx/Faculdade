package model;

/**
 * Item da venda (grava em venda_produto).
 */
public class VendaProdutoModel {

    private int VEP_CODIGO;     // opcional
    private int VDA_CODIGO;     // FK venda
    private int PRO_CODIGO;     // FK produto
    private String PRO_NOME;    // exibição
    private String PRO_UNIDADE; // exibição
    private double VEP_QTDE;
    private double VEP_PRECO;
    private double VEP_DESCONTO; // mantido por compat.; UI usa 0
    private double VEP_TOTAL;    // qtde * preco

    public VendaProdutoModel() {
    }

    public void recalc() {
        VEP_TOTAL = (VEP_QTDE * VEP_PRECO) - VEP_DESCONTO;
        if (VEP_TOTAL < 0) {
            VEP_TOTAL = 0;
        }
    }

    public int getVEP_CODIGO() {
        return VEP_CODIGO;
    }

    public void setVEP_CODIGO(int v) {
        VEP_CODIGO = v;
    }

    public int getVDA_CODIGO() {
        return VDA_CODIGO;
    }

    public void setVDA_CODIGO(int v) {
        VDA_CODIGO = v;
    }

    public int getPRO_CODIGO() {
        return PRO_CODIGO;
    }

    public void setPRO_CODIGO(int v) {
        PRO_CODIGO = v;
    }

    public String getPRO_NOME() {
        return PRO_NOME;
    }

    public void setPRO_NOME(String v) {
        PRO_NOME = v;
    }

    public String getPRO_UNIDADE() {
        return PRO_UNIDADE;
    }

    public void setPRO_UNIDADE(String v) {
        PRO_UNIDADE = v;
    }

    public double getVEP_QTDE() {
        return VEP_QTDE;
    }

    public void setVEP_QTDE(double v) {
        VEP_QTDE = v;
    }

    public double getVEP_PRECO() {
        return VEP_PRECO;
    }

    public void setVEP_PRECO(double v) {
        VEP_PRECO = v;
    }

    public double getVEP_DESCONTO() {
        return VEP_DESCONTO;
    }

    public void setVEP_DESCONTO(double v) {
        VEP_DESCONTO = v;
    }

    public double getVEP_TOTAL() {
        return VEP_TOTAL;
    }

    public void setVEP_TOTAL(double v) {
        VEP_TOTAL = v;
    }
}
