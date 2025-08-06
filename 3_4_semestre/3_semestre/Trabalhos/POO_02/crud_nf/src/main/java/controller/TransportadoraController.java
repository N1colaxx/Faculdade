package controller;

import dao.TransportadoraDAO;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.EnderecoModel;
import model.TelefoneModel;
import model.TransportadoraModel;

public class TransportadoraController {
    private final TransportadoraDAO dao;

    public TransportadoraController(TransportadoraDAO dao) {
        this.dao = dao;
    }

    public void cadastrarTransportadora(String nome, String cnpj, EnderecoModel endereco, TelefoneModel telefone) {
        TransportadoraModel transportadora = new TransportadoraModel(nome, cnpj, endereco, telefone);
        dao.salvar(transportadora);
    }
    
    public TransportadoraModel cadastrarTransportadora() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome da Transportadora: ");
        String nome = scanner.nextLine();

        System.out.print("CNPJ da Transportadora: ");
        String cnpj = scanner.nextLine();

        System.out.println("--- Endereço da Transportadora ---");
        EnderecoController enderecoController = new EnderecoController();
        EnderecoModel endereco = enderecoController.criarEndereco();

        System.out.println("--- Telefone da Transportadora ---");
        TelefoneController telefoneController = new TelefoneController();
        TelefoneModel telefone = telefoneController.criarTelefone();

        TransportadoraModel transportadora = new TransportadoraModel(nome, cnpj, endereco, telefone);
        dao.salvar(transportadora);
        
        return transportadora;
    }

    public Optional<TransportadoraModel> buscarPorCnpj(String cnpj) {
        return dao.buscarPorCnpj(cnpj);
    }
}