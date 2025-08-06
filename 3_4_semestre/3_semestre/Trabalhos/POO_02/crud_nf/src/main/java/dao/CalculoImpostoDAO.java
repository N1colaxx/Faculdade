package dao;

import java.util.ArrayList;
import java.util.List;
import model.CalculoImpostoModel;

public class CalculoImpostoDAO {

    private final List<CalculoImpostoModel> impostos = new ArrayList<>();

    public void salvar(CalculoImpostoModel imposto) {
        impostos.add(imposto);
    }
    
    public List<CalculoImpostoModel> listarTodos() {
        return new ArrayList<>(impostos);
    }
}
