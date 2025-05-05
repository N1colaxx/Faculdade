package controller;

import model.TelefoneModel;

public class TelefoneController {

    public TelefoneModel criarTelefone(String ddd, String numero) {
        return new TelefoneModel(ddd, numero);
    }

    public String formatarTelefone(TelefoneModel telefone) {
        return String.format("(%s) %s", telefone.getDDD(), telefone.getNumero());
    }
}
