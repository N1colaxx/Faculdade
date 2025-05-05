package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.TransportadoraModel;


public class TransportadoraDAO {
    private final List<TransportadoraModel> transportadoras = new ArrayList<>();

    public void salvar(TransportadoraModel transportadora) {
        transportadoras.add(transportadora);
    }
    
    public Optional<TransportadoraModel> buscarPorCnpj(String cnpj) {
        return transportadoras.stream()
                .filter(t -> t.getCnpj().equals(cnpj))
                .findFirst();
    }
    
    public List<TransportadoraModel> listarTodas() {
        return new ArrayList<>(transportadoras);
    }
}