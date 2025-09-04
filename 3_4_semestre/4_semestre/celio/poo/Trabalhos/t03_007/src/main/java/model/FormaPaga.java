package model;

public class FormaPaga {

    private int pk_fpg_codigo;
    private String fpg_nome, fpg_ativo; // Pode ser "S"/"N" ou "Sim"/"Não"


    public FormaPaga() {
    }


    public FormaPaga(int pk_fpg_codigo, String fpg_nome, String fpg_ativo) {
        this.pk_fpg_codigo = pk_fpg_codigo;
        this.fpg_nome = fpg_nome;
        this.fpg_ativo = fpg_ativo;
    }

    // Getters
    public int getPkFpgCodigo() {
        return pk_fpg_codigo;
    }

    public String getFpgNome() {
        return fpg_nome;
    }

    public String getFpgAtivo() {
        return fpg_ativo;
    }

    // Setters
    public void setPkFpgCodigo(int pk_fpg_codigo) {
        this.pk_fpg_codigo = pk_fpg_codigo;
    }

    public void setFpgNome(String fpg_nome) {
        this.fpg_nome = fpg_nome;
    }

    public void setFpgAtivo(String fpg_ativo) {
        this.fpg_ativo = fpg_ativo;
    }
}
