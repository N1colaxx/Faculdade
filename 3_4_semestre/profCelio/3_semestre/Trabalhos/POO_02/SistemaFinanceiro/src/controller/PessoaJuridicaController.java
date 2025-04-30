package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.PessoaJuridicaModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class PessoaJuridicaController implements InterfaceController {

    private ArrayList<PessoaJuridicaModel> pessoasJuridicas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID da pessoa jurídica para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaJuridicaModel pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                System.out.print("Novo nome: ");
                pj.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                pj.setEmail(scanner.nextLine());

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

                pj.setEndereco(endereco);

                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                pj.setTelefone(telefone);

                System.out.print("Novo CNPJ: ");
                pj.setCnpj(scanner.nextLine());

                System.out.print("Nova Inscrição Estadual: ");
                pj.setInscricaoEstadual(scanner.nextLine());

                System.out.print("Novo contato: ");
                pj.setContato(scanner.nextLine());

                System.out.println("Pessoa jurídica alterada com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa jurídica com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < pessoasJuridicas.size()) {
            PessoaJuridicaModel pj = pessoasJuridicas.get(pos);
            System.out.println("ID: " + pj.getId());
            System.out.println("Nome: " + pj.getNome());
            System.out.println("Email: " + pj.getEmail());
            System.out.println("Endereço: " + pj.getEndereco().getLogradouro() + ", " + pj.getEndereco().getNumero());
            System.out.println("Telefone: (" + pj.getTelefone().getDdd() + ") " + pj.getTelefone().getNumero());
            System.out.println("CNPJ: " + pj.getCnpj());
            System.out.println("Inscrição Estadual: " + pj.getInscricaoEstadual());
            System.out.println("Contato: " + pj.getContato());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da pessoa jurídica para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PessoaJuridicaModel pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                pessoasJuridicas.remove(pj);
                System.out.println("Pessoa jurídica removida com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa jurídica com ID não encontrado.");
    }

    public void adicionarPessoaJuridica(PessoaJuridicaModel pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public ArrayList<PessoaJuridicaModel> getPessoasJuridicas() {
        return pessoasJuridicas;
    }
}
