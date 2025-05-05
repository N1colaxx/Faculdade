package model;

public class FaturaModel {

    private String numero;
    private double valorTotal;

    public FaturaModel(String numero, double valorTotal) {
        this.numero = numero;
        this.valorTotal = valorTotal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    @Override
    public String toString() {
        return 
               "| =============== FATURA ===============" + "\n" +  
               "| Numero = " + numero + "\n" +
               "| valorTotal = " + valorTotal + "\n" +
               "|==============================================";
    }

}
