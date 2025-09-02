package model;

import java.math.BigDecimal;

public class Cliente {

    private int pk_cli_codigo, fk_pes_codigo;
    private BigDecimal cli_limitecred;

    public Cliente() {
    }

    public Cliente(int pk_cli_codigo, int fk_pes_codigo, BigDecimal cli_limitecred) {
        this.pk_cli_codigo = pk_cli_codigo;
        this.fk_pes_codigo = fk_pes_codigo;
        this.cli_limitecred = cli_limitecred;
    }

    //Getters
    public int getPkCliCodigo() {
        return pk_cli_codigo;
    }

    public int getFkPesCodigo() {
        return fk_pes_codigo;
    }

    public BigDecimal getCliLimitecred() {
        return cli_limitecred;
    }

    // Setters
    public void setPkCliCodigo(int pk_cli_codigo) {
        this.pk_cli_codigo = pk_cli_codigo;
    }

    public void setFkPesCodigo(int fk_pes_codigo) {
        this.fk_pes_codigo = fk_pes_codigo;
    }

    public void setCliLimitecred(BigDecimal cli_limitecred) {
        this.cli_limitecred = cli_limitecred;
    }
}
