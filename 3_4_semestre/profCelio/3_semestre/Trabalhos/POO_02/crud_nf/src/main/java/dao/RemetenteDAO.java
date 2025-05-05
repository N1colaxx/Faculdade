
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.RemetenteModel;

public class RemetenteDAO {

    private final List<RemetenteModel> remetentes = new ArrayList<>();

    public void salvar(RemetenteModel remetente) {
        remetentes.add(remetente);
    }

    public Optional<RemetenteModel> buscarPorCnpj(String cnpj) {
        return remetentes.stream()
                .filter(e -> e.getCpfCnpj().equals(cnpj))
                .findFirst();
    }

    public List<RemetenteModel> listarTodos() {
        return new ArrayList<>(remetentes);
    }
}

