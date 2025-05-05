package controller;

import dao.RemetenteDAO;  // Se você estiver usando um DAO
import model.EnderecoModel;
import model.RemetenteModel;
import java.util.Scanner;

public class RemetenteController {

    private final RemetenteDAO dao;

    public RemetenteController(RemetenteDAO dao) {
        this.dao = dao;
    }

    // Método para cadastrar o remetente
    public RemetenteModel cadastrarRemetente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do Remetente: ");
        String nomeRemetente = scanner.nextLine();

        System.out.print("CNPJ/CPF do Remetente: ");
        String cnpjCpfRemetente = scanner.nextLine();

        System.out.println("--- Endereço do Remetente ---");
        EnderecoController enderecoController = new EnderecoController();
        EnderecoModel endereco = enderecoController.criarEndereco();  // Usa o mesmo método do EnderecoController

        RemetenteModel remetente = new RemetenteModel(nomeRemetente, cnpjCpfRemetente, endereco); // Cria o remetente com dados
        dao.salvar(remetente); // Salva no DAO

        return remetente;  // Retorna o remetente criado
    }

}
