package controller;

import model.EnderecoModel;

public class EnderecoController {

    public EnderecoModel criarEndereco(String logradouro, String numero, String bairro,
            String cidade, String estado, String cep) {
        return new EnderecoModel(logradouro, numero, bairro, cidade, estado, cep);
    }

    public String formatarEndereco(EnderecoModel endereco) {
        return String.format("%s, %s - %s, %s/%s - CEP: %s",
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep());
    }
}
