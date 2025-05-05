package dao;
// EmitenteDAO.java

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.EmitenteModel;

public class EmitenteDAO {

    private final List<EmitenteModel> emitentes = new ArrayList<>();

    public void salvar(EmitenteModel emitente) {
        emitentes.add(emitente);
    }

    public Optional<EmitenteModel> buscarPorCnpj(String cnpj) {
        return emitentes.stream()
                .filter(e -> e.getCpfCnpj().equals(cnpj))
                .findFirst();
    }

    public List<EmitenteModel> listarTodos() {
        return new ArrayList<>(emitentes);
    }
}
