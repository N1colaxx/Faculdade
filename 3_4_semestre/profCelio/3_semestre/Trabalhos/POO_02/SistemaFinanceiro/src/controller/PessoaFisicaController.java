package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.PessoaFisicaModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class PessoaFisicaController implements InterfaceController {

    private ArrayList<PessoaFisicaModel> pessoasFisicas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID da pessoa física para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaFisicaModel pf : pessoasFisicas) {
            if (pf.getId() == id) {
                System.out.print("Novo nome: ");
                pf.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                pf.setEmail(scanner.nextLine());

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

                pf.setEndereco(endereco);

                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                pf.setTelefone(telefone);

                System.out.print("Novo CPF: ");
                pf.setCpf(scanner.nextLine());

                System.out.print("Novo RG: ");
                pf.setRg(scanner.nextLine());

                System.out.print("Novo Emissor: ");
                pf.setEmissor(scanner.nextLine());

                System.out.println("Pessoa física alterada com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa física com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < pessoasFisicas.size()) {
            PessoaFisicaModel pf = pessoasFisicas.get(pos);
            System.out.println("ID: " + pf.getId());
            System.out.println("Nome: " + pf.getNome());
            System.out.println("Email: " + pf.getEmail());
            System.out.println("Endereço: " + pf.getEndereco().getLogradouro() + ", " + pf.getEndereco().getNumero());
            System.out.println("Telefone: (" + pf.getTelefone().getDdd() + ") " + pf.getTelefone().getNumero());
            System.out.println("CPF: " + pf.getCpf());
            System.out.println("RG: " + pf.getRg());
            System.out.println("Emissor: " + pf.getEmissor());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da pessoa física para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaFisicaModel pf : pessoasFisicas) {
            if (pf.getId() == id) {
                pessoasFisicas.remove(pf);
                System.out.println("Pessoa física removida com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa física com ID não encontrado.");
    }


    public void adicionarPessoaFisica(PessoaFisicaModel pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public ArrayList<PessoaFisicaModel> getPessoasFisicas() {
        return pessoasFisicas;
    }
}
