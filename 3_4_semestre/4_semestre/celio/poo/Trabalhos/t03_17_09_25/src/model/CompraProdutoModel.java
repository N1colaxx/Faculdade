package model;

import java.math.BigDecimal;

public class CompraProdutoModel {
    
    private int 
            CPP_CODIGO,
            CPR_CODIGO,
            PRO_CODIGO;
    private BigDecimal 
            CPR_QTDE,
            CPR_PRECO,
            CPR_DESCONTO,
            CPR_TOTAL;

    // Construtor vazio
    public CompraProdutoModel() { }

    // Construtor completo
    public CompraProdutoModel(int CPP_CODIGO, int CPR_CODIGO, int PRO_CODIGO,
                              BigDecimal CPR_QTDE, BigDecimal CPR_PRECO, 
                              BigDecimal CPR_DESCONTO, BigDecimal CPR_TOTAL) {
        this.CPP_CODIGO = CPP_CODIGO;
        this.CPR_CODIGO = CPR_CODIGO;
        this.PRO_CODIGO = PRO_CODIGO;
        this.CPR_QTDE = CPR_QTDE;
        this.CPR_PRECO = CPR_PRECO;
        this.CPR_DESCONTO = CPR_DESCONTO;
        this.CPR_TOTAL = CPR_TOTAL;
    }

    // Getters
    public int getCPP_CODIGO() { return CPP_CODIGO; }
    public int getCPR_CODIGO() { return CPR_CODIGO; }
    public int getPRO_CODIGO() { return PRO_CODIGO; }
    public BigDecimal getCPR_QTDE() { return CPR_QTDE; }
    public BigDecimal getCPR_PRECO() { return CPR_PRECO; }
    public BigDecimal getCPR_DESCONTO() { return CPR_DESCONTO; }
    public BigDecimal getCPR_TOTAL() { return CPR_TOTAL; }

    // Setters
    public void setCPP_CODIGO(int CPP_CODIGO) {
        this.CPP_CODIGO = CPP_CODIGO;
    }

    public void setCPR_CODIGO(int CPR_CODIGO) {
        this.CPR_CODIGO = CPR_CODIGO;
    }

    public void setPRO_CODIGO(int PRO_CODIGO) {
        this.PRO_CODIGO = PRO_CODIGO;
    }

    public void setCPR_QTDE(BigDecimal CPR_QTDE) {
        this.CPR_QTDE = CPR_QTDE;
    }

    public void setCPR_PRECO(BigDecimal CPR_PRECO) {
        this.CPR_PRECO = CPR_PRECO;
    }

    public void setCPR_DESCONTO(BigDecimal CPR_DESCONTO) {
        this.CPR_DESCONTO = CPR_DESCONTO;
    }

    public void setCPR_TOTAL(BigDecimal CPR_TOTAL) {
        this.CPR_TOTAL = CPR_TOTAL;
    }
}
