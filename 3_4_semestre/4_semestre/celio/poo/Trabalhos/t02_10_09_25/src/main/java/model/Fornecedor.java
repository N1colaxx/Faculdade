package model;

public class Fornecedor {

    private int pk_for_codigo,
            fk_pes_codigo;

    private String for_contato;

    public Fornecedor() {
    }

    public Fornecedor(int pk_for_codigo, int fk_pes_codigo, String for_contato) {
        this.pk_for_codigo = pk_for_codigo;
        this.fk_pes_codigo = fk_pes_codigo;
        this.for_contato = for_contato;
    }

    // Getters
    public int getPkForCodigo() {
        return pk_for_codigo;
    }

    public int getFkPesCodigo() {
        return fk_pes_codigo;
    }

    public String getForContato() {
        return for_contato;
    }

    // Setters
    public void setPkForCodigo(int pk_for_codigo) {
        this.pk_for_codigo = pk_for_codigo;
    }

    public void setFkPesCodigo(int fk_pes_codigo) {
        this.fk_pes_codigo = fk_pes_codigo;
    }

    public void setForContato(String for_contato) {
        this.for_contato = for_contato;
    }
}
