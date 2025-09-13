package model;

import java.math.BigDecimal;

public class VendaProdutoModel {
    
    private int 
            VEP_CODIGO,
            VDA_CODIGO,
            PRO_CODIGO,
            VEP_QTDE;
    private BigDecimal 
            VEP_PRECO,
            VEP_DESCONTO,
            VEP_TOTAL;


    public VendaProdutoModel() { }


    public VendaProdutoModel(int VEP_CODIGO, int VDA_CODIGO, int PRO_CODIGO, int VEP_QTDE,
                             BigDecimal VEP_PRECO, BigDecimal VEP_DESCONTO, BigDecimal VEP_TOTAL) {
        this.VEP_CODIGO = VEP_CODIGO;
        this.VDA_CODIGO = VDA_CODIGO;
        this.PRO_CODIGO = PRO_CODIGO;
        this.VEP_QTDE = VEP_QTDE;
        this.VEP_PRECO = VEP_PRECO;
        this.VEP_DESCONTO = VEP_DESCONTO;
        this.VEP_TOTAL = VEP_TOTAL;
    }

    // Getters
    public int getVEP_CODIGO() { return VEP_CODIGO; }
    public int getVDA_CODIGO() { return VDA_CODIGO; }
    public int getPRO_CODIGO() { return PRO_CODIGO; }
    public int getVEP_QTDE() { return VEP_QTDE; }
    public BigDecimal getVEP_PRECO() { return VEP_PRECO; }
    public BigDecimal getVEP_DESCONTO() { return VEP_DESCONTO; }
    public BigDecimal getVEP_TOTAL() { return VEP_TOTAL; }

    // Setters
    public void setVEP_CODIGO(int VEP_CODIGO) {
        this.VEP_CODIGO = VEP_CODIGO;
    }

    public void setVDA_CODIGO(int VDA_CODIGO) {
        this.VDA_CODIGO = VDA_CODIGO;
    }

    public void setPRO_CODIGO(int PRO_CODIGO) {
        this.PRO_CODIGO = PRO_CODIGO;
    }

    public void setVEP_QTDE(int VEP_QTDE) {
        this.VEP_QTDE = VEP_QTDE;
    }

    public void setVEP_PRECO(BigDecimal VEP_PRECO) {
        this.VEP_PRECO = VEP_PRECO;
    }

    public void setVEP_DESCONTO(BigDecimal VEP_DESCONTO) {
        this.VEP_DESCONTO = VEP_DESCONTO;
    }

    public void setVEP_TOTAL(BigDecimal VEP_TOTAL) {
        this.VEP_TOTAL = VEP_TOTAL;
    }
}
