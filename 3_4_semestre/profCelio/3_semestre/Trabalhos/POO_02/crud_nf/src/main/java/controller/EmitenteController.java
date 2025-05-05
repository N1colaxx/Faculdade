package controller;
// EmitenteController.java

import dao.EmitenteDAO;
import java.util.Optional;
import model.EnderecoModel;
import model.EmitenteModel;

public class EmitenteController {

    private final EmitenteDAO dao;

    public EmitenteController(EmitenteDAO dao) {
        this.dao = dao;
    }

    public void cadastrarEmitente(String razaoSocial, String cnpj, EnderecoModel endereco) {
        EmitenteModel emitente = new EmitenteModel(razaoSocial, cnpj, endereco);
        dao.salvar(emitente);
    }

    public Optional<EmitenteModel> buscarPorCnpj(String cnpj) {
        return dao.buscarPorCnpj(cnpj);
    }
}
