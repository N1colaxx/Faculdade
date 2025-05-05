package controller;

import dao.FaturaDAO;
import java.util.Optional;
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

    public Optional<FaturaModel> buscarPorNumero(String numero) {
        return dao.buscarPorNumero(numero);
    }
}
