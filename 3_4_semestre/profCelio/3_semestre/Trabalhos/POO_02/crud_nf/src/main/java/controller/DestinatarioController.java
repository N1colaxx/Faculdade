package controller;

import dao.DestinatarioDAO;
import java.util.Optional;
import model.Destinatario;
import model.EnderecoModel;

public class DestinatarioController {

    private final DestinatarioDAO dao;

    public DestinatarioController(DestinatarioDAO dao) {
        this.dao = dao;
    }

    public void cadastrarDestinatario(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        Destinatario destinatario = new Destinatario(razaoSocial, cpfCnpj, endereco);
        dao.salvar(destinatario);
    }

    public Optional<Destinatario> buscarPorCpfCnpj(String cpfCnpj) {
        return dao.buscarPorCpfCnpj(cpfCnpj);
    }
}
