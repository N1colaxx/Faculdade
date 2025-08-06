package controller;

import dao.FaturaDAO;
import java.util.Optional;
import java.util.Scanner;
import model.FaturaModel;

public class FaturaController {

    private final FaturaDAO dao;

    public FaturaController(FaturaDAO dao) {
        this.dao = dao;
    }

    public FaturaModel criarFatura(String numero, double valorTotal) {
        FaturaModel fatura = new FaturaModel(numero, valorTotal);
        dao.salvar(fatura);
        return  fatura;
    }
    
    
    // Novo método para criar fatura com Scanner
    public FaturaModel criarFatura() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número da fatura: ");
        String numero = scanner.nextLine();

        System.out.print("Valor total da fatura: ");
        double valorTotal = scanner.nextDouble();

        FaturaModel fatura = new FaturaModel(numero, valorTotal);
        dao.salvar(fatura);
        return fatura;
    }

    public Optional<FaturaModel> buscarPorNumero(String numero) {
        return dao.buscarPorNumero(numero);
    }
}
