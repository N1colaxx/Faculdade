package controller;

import dao.CalculoImpostoDAO;
import java.util.Scanner;
import model.CalculoImpostoModel;

public class CalculoImpostoController {

    private final CalculoImpostoDAO dao;

    public CalculoImpostoController(CalculoImpostoDAO dao) {
        this.dao = dao;
    }

    public CalculoImpostoModel calcularImpostos(double icms, double ipi, double pis, double cofins, double iss) {
        CalculoImpostoModel imposto = new CalculoImpostoModel(icms, ipi, pis, cofins, iss);
        dao.salvar(imposto);
        return imposto;
    }
    
    public CalculoImpostoModel incerirImpostos() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o valor do ICMS: ");
        double icms = scanner.nextDouble();

        System.out.print("Informe o valor do IPI: ");
        double ipi = scanner.nextDouble();

        System.out.print("Informe o valor do PIS: ");
        double pis = scanner.nextDouble();

        System.out.print("Informe o valor do COFINS: ");
        double cofins = scanner.nextDouble();

        System.out.print("Informe o valor do ISS: ");
        double iss = scanner.nextDouble();

        CalculoImpostoModel imposto = new CalculoImpostoModel(icms, ipi, pis, cofins, iss);
        dao.salvar(imposto);
     
        return imposto;
    }

    public double calcularTotalImpostos(CalculoImpostoModel imposto) {
        return imposto.getValorTotalImpostos();
    }
}
