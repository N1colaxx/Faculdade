package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Destinatario;


public class DestinatarioDAO {

    private final List<Destinatario> destinatarios = new ArrayList<>();

    public void salvar(Destinatario destinatario) {
        destinatarios.add(destinatario);
    }

    public Optional<Destinatario> buscarPorCpfCnpj(String cpfCnpj) {
        return destinatarios.stream()
                .filter(d -> d.getCpfCnpj().equals(cpfCnpj))
                .findFirst();
    }
}
