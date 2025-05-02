package controller;

import java.util.ArrayList;
import java.util.List;
import model.FuncionarioModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class FuncionarioController implements InterfaceCadastro {

    private ArrayList<FuncionarioModel> funcionarios = new ArrayList<>();
 
    @Override
    public void incluir() {
        FuncionarioModel funcionario = new FuncionarioModel();

        System.out.print("Nome: ");
        funcionario.setNome(scanner.nextLine());

        
        System.out.println("\n--- ENDEREÇO DE ENTREGA ---");
        EnderecoController enderecoController = EnderecoController.getInstancia();
        EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
        funcionario.setEndereco(enderecoEntrega);

        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));
        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));
        funcionario.setTelefone(telefone);

        
        System.out.print("Email: ");
        funcionario.setEmail(scanner.nextLine());
        
        
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

        System.out.println("\n Funcionário cadastrado com sucesso !!! \n");
    }

    @Override
    public void alterarID() {
        if(funcionarios.size() == 0 ) {
            System.out.println("\nEsta lista esta VAZIA!!");
        } else {
            int id = lerIdValido();
            for (FuncionarioModel funcionario : funcionarios) {
                if (funcionario.getId() == id) {
                    System.out.print("Novo nome: ");
                    funcionario.setNome(scanner.nextLine());

                    
                    System.out.println("\n--- ALTERAR ENDEREÇO DE ENTREGA ---");
                    EnderecoController enderecoController = EnderecoController.getInstancia();
                    EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                    funcionario.setEndereco(enderecoEntrega);

                    System.out.println("----- ALTERAR TELEFONE ----- ");
                    TelefoneModel telefone = new TelefoneModel();
                    System.out.print("Novo DDD: ");
                    telefone.setDdd(Integer.parseInt(scanner.nextLine()));
                    System.out.print("novo Número: ");
                    telefone.setNumero(Long.parseLong(scanner.nextLine()));
                    funcionario.setTelefone(telefone);
                    
                    
                    System.out.print("Novo e-mail: ");
                    funcionario.setEmail(scanner.nextLine());
                    

                    System.out.print("Novo CPF: ");
                    funcionario.setCpf(scanner.nextLine());

                    System.out.print("Novo RG: ");
                    funcionario.setRg(scanner.nextLine());

                    System.out.print("Novo emissor do RG: ");
                    funcionario.setEmissor(scanner.nextLine());

                    
                    
                    System.out.print("Nova data de admissão: ");
                    funcionario.setDataAdmissao(scanner.nextLine());

                    System.out.print("Nova data de demissão: ");
                    funcionario.setDataDemissao(scanner.nextLine());

                    System.out.print("Nova CTPS: ");
                    funcionario.setCtps(scanner.nextLine());

                    System.out.print("Novo salário: ");
                    funcionario.setSalario(Double.parseDouble(scanner.nextLine()));

                    System.out.println("\n Funcionário alterado com sucesso !!! \n");
                    return;
                }
            }
            System.out.println("Funcionário com ID não encontrado.");
        }
    }

    
    @Override
    public void ConsultarPosicaoLista() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (funcionarios.size() - 1) + " posições.");
            int pos = lerPosicaoValida(funcionarios, "cliente");
            FuncionarioModel funcionario = funcionarios.get(pos);
            System.out.println(funcionario);
        }
    }
    
    
        // método  para interação com o usuário
    public void consultarCPF() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("\nDigite o CPF do Funcionário: ");
            String cpf = scanner.nextLine();

            boolean encontrado = false;

            for (FuncionarioModel funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    System.out.println("Funcionário encontrado !!");
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("CPF: " + funcionario.getCpf());
                    encontrado = true;
                    break; // se você quer parar após encontrar o primeiro
                }
            }

            if (!encontrado) {
                System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
            }
        }
    }

    
    public void consultarPorNome() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("\nDigite o nome do funcionário que deseja buscar: ");
            String nomeBusca = scanner.nextLine().toLowerCase(); // converte para minúsculas para busca flexível

            boolean encontrado = false;

            for (FuncionarioModel funcionario : funcionarios) {
                if (funcionario.getNome().toLowerCase().contains(nomeBusca)) {
                    System.out.println("\nFuncionário encontrado:");
                    System.out.println("ID: " + funcionario.getId());
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("CPF: " + funcionario.getCpf());
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("Nenhum funcionário com o nome \"" + nomeBusca + "\" foi encontrado.");
            }
        }
    }
    
    


    @Override
    public void excluir() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for ( FuncionarioModel funcionario : funcionarios) {
                if (funcionario.getId() == id) {
                    funcionarios.remove(funcionario);
                    System.out.println("Cliente removido com sucesso!");
                    return;
                }
            }
            System.out.println("Cliente com ID não encontrado.");
        }
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
                System.out.print("Informe o ID do Funcionario: ");
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

    
    
    
    public void adicionarFuncionario(FuncionarioModel funcionario) {
        funcionarios.add(funcionario);
    }

    public ArrayList<FuncionarioModel> getFuncionarios() {
        return funcionarios;
    }
    
    public void adicionarFake(FuncionarioModel funcionario) {
        funcionarios.add(funcionario);
    }
    
    
    
        // (Opcional) mostrar dados bonitinhos
    public void exibirDadosCliente(FuncionarioModel cliente) {
        System.out.println("----- DADOS DO FUNCIONARIO -----");
        System.out.println(cliente);  // Chama o toString() da classe ClienteModel
        System.out.println("----------------------------");
        }
}
