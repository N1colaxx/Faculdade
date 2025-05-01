package controller;

import java.util.ArrayList;
import java.util.List;
import model.PessoaFisicaModel;
import model.EnderecoModel;
import model.FuncionarioModel;
import model.TelefoneModel;

public class PessoaFisicaController implements InterfaceCadastro{

    private ArrayList<PessoaFisicaModel> pessoasFisicas = new ArrayList<>();

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterarID() {
        System.out.print("Informe o ID da pessoa física para alterar: ");
        int id = lerIdValido();
        for (PessoaFisicaModel pf : pessoasFisicas) {
            if (pf.getId() == id) {
                System.out.print("Novo nome: ");
                pf.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                pf.setEmail(scanner.nextLine());

                System.out.println("\n--- ENDEREÇO DE ENTREGA ---");
                EnderecoController enderecoController = new EnderecoController();
                EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                pf.setEndereco(enderecoEntrega);

                System.out.println("Alterar telefone: ");
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
    public void ConsultarPosicaoLista() {
        if(pessoasFisicas.isEmpty()){
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (pessoasFisicas.size() - 1) + " posições.");
            int pos = lerPosicaoValida(pessoasFisicas, "Pessoas Fisicas");
            PessoaFisicaModel pf = pessoasFisicas.get(pos);
            System.out.println(pf);
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


    public void adicionarPessoaFisica(PessoaFisicaModel pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public ArrayList<PessoaFisicaModel> getPessoasFisicas() {
        return pessoasFisicas;
    }
}
