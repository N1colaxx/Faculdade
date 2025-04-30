package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.FuncionarioModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class FuncionarioController implements InterfaceController {

    private ArrayList<FuncionarioModel> funcionarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        FuncionarioModel funcionario = new FuncionarioModel();

        System.out.print("Nome: ");
        funcionario.setNome(scanner.nextLine());

        System.out.print("Email: ");
        funcionario.setEmail(scanner.nextLine());

        System.out.println("Endereço:");
        EnderecoModel endereco = new EnderecoModel();
        System.out.print("Logradouro: ");
        endereco.setLogradouro(scanner.nextLine());

        System.out.print("Número: ");
        endereco.setNumero(scanner.nextLine());

        System.out.print("Complemento: ");
        endereco.setComplemento(scanner.nextLine());

        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());

        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());

        System.out.print("Estado: ");
        endereco.setEstado(scanner.nextLine());

        System.out.print("CEP: ");
        endereco.setCep(Integer.parseInt(scanner.nextLine()));

        funcionario.setEndereco(endereco);

        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        funcionario.setTelefone(telefone);

        System.out.print("CPF: ");
        funcionario.setCpf(scanner.nextLine());

        System.out.print("RG: ");
        funcionario.setRg(scanner.nextLine());

        System.out.print("Emissor do RG: ");
        funcionario.setEmissor(scanner.nextLine());

        System.out.print("Data de Admissão: ");
        funcionario.setDataAdmissao(scanner.nextLine());

        System.out.print("Data de Demissão (ou deixe vazio se ainda ativo): ");
        funcionario.setDataDemissao(scanner.nextLine());

        System.out.print("CTPS: ");
        funcionario.setCtps(scanner.nextLine());

        System.out.print("Salário: ");
        funcionario.setSalario(Double.parseDouble(scanner.nextLine()));

        funcionario.setId(funcionarios.size() + 1);

        funcionarios.add(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID do funcionário para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FuncionarioModel funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                System.out.print("Novo nome: ");
                funcionario.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                funcionario.setEmail(scanner.nextLine());

                System.out.println("Alterar endereço:");
                EnderecoModel endereco = new EnderecoModel();
                System.out.print("Logradouro: ");
                endereco.setLogradouro(scanner.nextLine());

                System.out.print("Número: ");
                endereco.setNumero(scanner.nextLine());

                System.out.print("Complemento: ");
                endereco.setComplemento(scanner.nextLine());

                System.out.print("Bairro: ");
                endereco.setBairro(scanner.nextLine());

                System.out.print("Cidade: ");
                endereco.setCidade(scanner.nextLine());

                System.out.print("Estado: ");
                endereco.setEstado(scanner.nextLine());

                System.out.print("CEP: ");
                endereco.setCep(Integer.parseInt(scanner.nextLine()));

                funcionario.setEndereco(endereco);

                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                funcionario.setTelefone(telefone);

                System.out.print("Novo CPF: ");
                funcionario.setCpf(scanner.nextLine());

                System.out.print("Novo RG: ");
                funcionario.setRg(scanner.nextLine());

                System.out.print("Novo emissor do RG: ");
                funcionario.setEmissor(scanner.nextLine());

                System.out.print("Nova data de admissão: ");
                funcionario.setDataAdmissao(scanner.nextLine());

                System.out.print("Nova data de demissão (ou deixe vazio): ");
                funcionario.setDataDemissao(scanner.nextLine());

                System.out.print("Nova CTPS: ");
                funcionario.setCtps(scanner.nextLine());

                System.out.print("Novo salário: ");
                funcionario.setSalario(Double.parseDouble(scanner.nextLine()));

                System.out.println("Funcionário alterado com sucesso!");
                return;
            }
        }
        System.out.println("Funcionário com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < funcionarios.size()) {
            FuncionarioModel funcionario = funcionarios.get(pos);
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Email: " + funcionario.getEmail());
            System.out.println("Endereço: " + funcionario.getEndereco().getLogradouro() + ", " + funcionario.getEndereco().getNumero());
            System.out.println("Telefone: (" + funcionario.getTelefone().getDdd() + ") " + funcionario.getTelefone().getNumero());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("RG: " + funcionario.getRg());
            System.out.println("Emissor: " + funcionario.getEmissor());
            System.out.println("Data de Admissão: " + funcionario.getDataAdmissao());
            System.out.println("Data de Demissão: " + funcionario.getDataDemissao());
            System.out.println("CTPS: " + funcionario.getCtps());
            System.out.println("Salário: R$" + funcionario.getSalario());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID do funcionário para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FuncionarioModel funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionarios.remove(funcionario);
                System.out.println("Funcionário removido com sucesso!");
                return;
            }
        }
        System.out.println("Funcionário com ID não encontrado.");
    }

    public void adicionarFuncionario(FuncionarioModel funcionario) {
        funcionarios.add(funcionario);
    }

    public ArrayList<FuncionarioModel> getFuncionarios() {
        return funcionarios;
    }
}
