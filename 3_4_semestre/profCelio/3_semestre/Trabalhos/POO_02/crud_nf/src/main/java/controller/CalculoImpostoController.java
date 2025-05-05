package controller;

import dao.CalculoImpostoDAO;
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

    public double calcularTotalImpostos(CalculoImpostoModel imposto) {
        return imposto.getValorTotalImpostos();
    }
}
