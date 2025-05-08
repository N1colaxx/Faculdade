package controller;

import dao.EmitenteDAO;
import java.util.Optional;
import java.util.Scanner;
import model.EnderecoModel;
import model.EmitenteModel;

public class EmitenteController {

    private final EmitenteDAO dao;

    public EmitenteController(EmitenteDAO dao) {
        this.dao = dao;
    }

    public void cadastrarEmitente(String razaoSocial, String cnpj, String Cpf, EnderecoModel endereco) {
        EmitenteModel emitente = new EmitenteModel(razaoSocial, cnpj, Cpf, endereco);
        dao.salvar(emitente);
    }
    
    public EmitenteModel cadastrarEmitente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.println("--- Endereço do Emitente ---");
        EnderecoController enderecoController = new EnderecoController();
        EnderecoModel endereco = enderecoController.criarEndereco();

        EmitenteModel emitente = new EmitenteModel(razaoSocial, cnpj,  cnpj, endereco);
        dao.salvar(emitente);

        return emitente;
    }

    public Optional<EmitenteModel> buscarPorCnpj(String cnpj) {
        return dao.buscarPorCnpj(cnpj);
    }
}
