package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DestinatarioModel;


public class DestinatarioDAO {

    private final List<DestinatarioModel> destinatarios = new ArrayList<>();

    public void salvar(DestinatarioModel destinatario) {
        destinatarios.add(destinatario);
    }

    public Optional<DestinatarioModel> buscarPorCpfCnpj(String cpfCnpj) {
        return destinatarios.stream()
                .filter(d -> d.getCpfCnpj().equals(cpfCnpj))
                .findFirst();
    }
}
