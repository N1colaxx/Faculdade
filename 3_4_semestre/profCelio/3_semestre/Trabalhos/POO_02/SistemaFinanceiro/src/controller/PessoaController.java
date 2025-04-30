package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.PessoaModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class PessoaController implements InterfaceController {

    private ArrayList<PessoaModel> pessoas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID da pessoa para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaModel p : pessoas) {
            if (p.getId() == id) {
                System.out.print("Novo nome: ");
                p.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                p.setEmail(scanner.nextLine());

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

                p.setEndereco(endereco);

                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                p.setTelefone(telefone);

                System.out.println("Pessoa alterada com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < pessoas.size()) {
            PessoaModel p = pessoas.get(pos);
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Email: " + p.getEmail());
            System.out.println("Endereço: " + p.getEndereco().getLogradouro() + ", " + p.getEndereco().getNumero());
            System.out.println("Telefone: (" + p.getTelefone().getDdd() + ") " + p.getTelefone().getNumero());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da pessoa para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaModel p : pessoas) {
            if (p.getId() == id) {
                pessoas.remove(p);
                System.out.println("Pessoa removida com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa com ID não encontrado.");
    }

    public void adicionarPessoa(PessoaModel pessoa) {
        pessoas.add(pessoa);
    }

    public ArrayList<PessoaModel> getPessoas() {
        return pessoas;
    }
}
