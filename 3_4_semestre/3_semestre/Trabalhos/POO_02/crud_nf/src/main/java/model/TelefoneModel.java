package model;

public class TelefoneModel {

    private String ddd, numero;

    public TelefoneModel(String ddd, String numero) {
        this.numero = numero;
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }
    
    @Override
    public String toString() {
        return 
               "(" + ddd + ")" + numero;
    }


}
