package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.PessoaModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class PessoaController implements InterfaceCadastro {

    private ArrayList<PessoaModel> pessoas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterarID() {
        System.out.print("Informe o ID da pessoa para alterar: ");
        int id = lerIdValido();
        for (PessoaModel p : pessoas) {
            if (p.getId() == id) {
                System.out.print("Novo nome: ");
                p.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                p.setEmail(scanner.nextLine());

                System.out.println("\n--- ENDEREÇO DE ENTREGA ---");
                EnderecoController enderecoController = new EnderecoController();
                EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                p.setEndereco(enderecoEntrega);

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
    public void ConsultarPosicaoLista() {
        if (pessoas.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (pessoas.size() - 1) + " posições.");
            int pos = lerPosicaoValida(pessoas, "pessoa");
            PessoaModel pessoa = pessoas.get(pos);
            System.out.println(pessoa);
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da pessoa para excluir: ");
        int id = lerIdValido();
        for (PessoaModel p : pessoas) {
            if (p.getId() == id) {
                pessoas.remove(p);
                System.out.println("Pessoa removida com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa com ID não encontrado.");
    }

    
    
        // 🔁 Método reutilizável para validar posições de lista
    public int lerPosicaoValida(List<?> lista, String nomeLista) {
        while (true) {
            try {
                System.out.print("Digite a posição que deseja consultar na lista de " + nomeLista + ": ");
                int pos = Integer.parseInt(scanner.nextLine());
                if (pos < 0 || pos >= lista.size()) {
                    System.out.println("❌ Posição inválida! Digite entre 0 e " + (lista.size() - 1));
                } else {
                    return pos;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números inteiros.");
            }
        }
    }

    // 🔁 Método reutilizável para validar ID (positivo)
    public int lerIdValido() {
        while (true) {
            try {
                System.out.print("Informe o ID do cliente: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ O ID deve ser maior que zero.");
                } else {
                    return id;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números inteiros.");
            }
        }
    }
    
    
    
    public void adicionarPessoa(PessoaModel pessoa) {
        pessoas.add(pessoa);
    }

    public ArrayList<PessoaModel> getPessoas() {
        return pessoas;
    }
    

    public void exibirDadosCliente(PessoaModel cliente) {
        System.out.println("----- DADOS DO PESSOA-----");
        System.out.println(cliente);  // Chama o toString() da classe ClienteModel
        System.out.println("----------------------------");
        }
}
