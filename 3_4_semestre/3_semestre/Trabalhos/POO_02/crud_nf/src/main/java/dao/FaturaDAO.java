package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.FaturaModel;

public class FaturaDAO {

    private final List<FaturaModel> faturas = new ArrayList<>();

    public void salvar(FaturaModel fatura) {
        faturas.add(fatura);
    }
    
    public Optional<FaturaModel> buscarPorNumero(String numero) {
        return faturas.stream()
                .filter(f -> f.getNumero().equals(numero))
                .findFirst();
    }
}
