package model;

/**
 * Modelo que representa os principais impostos de uma Nota Fiscal Eletrônica.
 */
public class CalculoImpostoModel {
    private double icms;
    private double ipi;
    private double pis;
    private double cofins;
    private double iss;

    // Construtor completo para inicializar os valores dos impostos
    public CalculoImpostoModel(double icms, double ipi, double pis, double cofins, double iss) {
        this.icms = icms;
        this.ipi = ipi;
        this.pis = pis;
        this.cofins = cofins;
        this.iss = iss;
    }

    // Getters para acessar os valores dos impostos individualmente
    public double getIcms() {
        return icms;
    }

    public double getIpi() {
        return ipi;
    }

    public double getPis() {
        return pis;
    }

    public double getCofins() {
        return cofins;
    }

    public double getIss() {
        return iss;
    }

    // Método que retorna o valor total de todos os impostos somados
    public double getValorTotalImpostos() {
        return icms + ipi + pis + cofins + iss;
    }
    
    @Override
    public String toString() {
        return 
               "| =============== CALCULO IMPOSTO ===============" + "\n" +
               "| ICMS = " + icms + "\n" +
               "| IPI = " + ipi + "\n" +
               "| PIS = " + pis + "\n" +
               "| CONFINS = " + cofins + "\n" +
               "| ISS = " + iss + "\n" +
               "|==============================================";
    }

}
