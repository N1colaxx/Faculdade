package controller;

import dao.DestinatarioDAO;
import java.util.Optional;
import java.util.Scanner;
import model.DestinatarioModel;
import model.EnderecoModel;

public class DestinatarioController {

    private final DestinatarioDAO dao;

    public DestinatarioController(DestinatarioDAO dao) {
        this.dao = dao;
    }

    public void cadastrarDestinatario(String razaoSocial, String cpfCnpj, EnderecoModel endereco) {
        DestinatarioModel destinatario = new DestinatarioModel(razaoSocial, cpfCnpj, endereco);
        dao.salvar(destinatario);
    }
    
    public DestinatarioModel cadastrarDestinatario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Razão Social do Destinatário: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CPF/CNPJ do Destinatário: ");
        String cpfCnpj = scanner.nextLine();

        System.out.println("--- Endereço do Destinatário ---");
        EnderecoController enderecoController = new EnderecoController();
        EnderecoModel endereco = enderecoController.criarEndereco();

        DestinatarioModel destinatario = new DestinatarioModel(razaoSocial, cpfCnpj, endereco);
        dao.salvar(destinatario);
        
        return destinatario;
    }


    public Optional<DestinatarioModel> buscarPorCpfCnpj(String cpfCnpj) {
        return dao.buscarPorCpfCnpj(cpfCnpj);
    }
}
