package controller;

import java.util.ArrayList;
import java.util.List;
import model.PessoaJuridicaModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class PessoaJuridicaController implements InterfaceCadastro {

    private ArrayList<PessoaJuridicaModel> pessoasJuridicas = new ArrayList<>();

    @Override
    public void incluir() {
        System.out.println("");
    }

    @Override
    public void alterarID() {
        int id = lerIdValido();
        for (PessoaJuridicaModel pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                System.out.print("Novo nome: ");
                pj.setNome(scanner.nextLine());
                
                System.out.println("\n--- ALTERAR ENDEREÇO DE ENTREGA ---");
                EnderecoController enderecoController = new EnderecoController();
                EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                pj.setEndereco(enderecoEntrega);

                System.out.println("Alterar telefone: ");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));
                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));
                pj.setTelefone(telefone);
                
                
                System.out.print("Novo e-mail: ");
                pj.setEmail(scanner.nextLine());
                

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
    public void ConsultarPosicaoLista() {
        if (pessoasJuridicas.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (pessoasJuridicas.size() - 1) + " posições.");
            int pos = lerPosicaoValida(pessoasJuridicas, "PJ");
            PessoaJuridicaModel pj = pessoasJuridicas.get(pos);
            System.out.println(pj);
        }
    }

    @Override
    public void excluir() {
        int id = lerIdValido();
        for (PessoaJuridicaModel pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                pessoasJuridicas.remove(pj);
                System.out.println("Pessoa jurídica removida com sucesso!");
                return;
            }
        }
        System.out.println("Pessoa jurídica com ID não encontrado.");
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
                System.out.print("Informe o ID da pessoa jurídica para excluir: ");
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
    
    
    public void adicionarPessoaJuridica(PessoaJuridicaModel pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public ArrayList<PessoaJuridicaModel> getPessoasJuridicas() {
        return pessoasJuridicas;
    }
}
