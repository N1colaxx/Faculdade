package controller;

import dao.TransportadoraDAO;
import java.util.List;
import java.util.Optional;
import model.EnderecoModel;
import model.TelefoneModel;
import model.TransportadoraModel;

public class TransportadoraController {
    private final TransportadoraDAO dao;

    public TransportadoraController(TransportadoraDAO dao) {
        this.dao = dao;
    }

    public void cadastrarTransportadora(String nome, String cnpj, EnderecoModel endereco, TelefoneModel telefone) {
        TransportadoraModel transportadora = new TransportadoraModel(nome, cnpj, endereco, telefone);
        dao.salvar(transportadora);
    }

    public Optional<TransportadoraModel> buscarPorCnpj(String cnpj) {
        return dao.buscarPorCnpj(cnpj);
    }
}