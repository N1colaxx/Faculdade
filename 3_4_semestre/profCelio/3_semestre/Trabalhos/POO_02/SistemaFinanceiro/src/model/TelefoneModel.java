
package model;


public class TelefoneModel {
    private int ddd;
    private long numero;

    public TelefoneModel() {
    }

    public TelefoneModel(int ddd, long numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
    
    
    @Override
    public String toString() {
        return "(" + ddd + ") " + numero;
    }

}
