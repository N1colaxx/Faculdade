package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ClienteModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class ClienteController implements InterfaceCadastro {

    private ArrayList<ClienteModel> clientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        ClienteModel cliente = new ClienteModel();
        
        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        
        System.out.println("\n--- ENDEREÇO DE ENTREGA ---");
        EnderecoController enderecoController = new EnderecoController();
        EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
        cliente.setEndereco(enderecoEntrega);
        
        // Cadastro do Endereço de Cobrança
        System.out.println("\n--- ENDEREÇO DE COBRANÇA ---");
        System.out.println("Deseja usar o mesmo endereço de entrega para cobrança? (S/N)");
        String opcao = scanner.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            cliente.setEnderecoCobranca(enderecoEntrega); // Usa o mesmo endereço
        } else {
            System.out.println("\nCadastro do Endereço de Cobrança:");
            EnderecoModel enderecoCobranca = enderecoController.entrar();
            cliente.setEnderecoCobranca(enderecoCobranca);
        }
        
        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));
        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));
        cliente.setTelefone(telefone);
        
        
        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

        
        System.out.print("CNPJ: ");
        cliente.setCnpj(scanner.nextLine());
        System.out.print("Inscrição Estadual: ");
        cliente.setInscricaoEstadual(scanner.nextLine());

        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    @Override
    public void alterarID() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == id) {
                    System.out.print("Novo nome: ");
                    cliente.setNome(scanner.nextLine());
                    System.out.print("Novo e-mail: ");
                    cliente.setEmail(scanner.nextLine());

                    System.out.println("\n--- ALTERAR ENDEREÇO DE ENTREGA ---");
                    EnderecoController enderecoController = new EnderecoController();
                    EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                    cliente.setEndereco(enderecoEntrega);

                    // Cadastro do Endereço de Cobrança
                    System.out.println("\n--- ALTERAR ENDEREÇO DE COBRANÇA ---");
                    System.out.println("Deseja usar o mesmo endereço de entrega para cobrança? (S/N)");
                    String opcao = scanner.nextLine();

                    System.out.println("Alterar telefone:");
                    TelefoneModel telefone = new TelefoneModel();
                    System.out.print("DDD: ");
                    telefone.setDdd(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Número: ");
                    telefone.setNumero(Long.parseLong(scanner.nextLine()));
                    cliente.setTelefone(telefone);

                    System.out.print("Novo CNPJ: ");
                    cliente.setCnpj(scanner.nextLine());
                    System.out.print("Nova Inscrição Estadual: ");
                    cliente.setInscricaoEstadual(scanner.nextLine());

                    System.out.println("Cliente alterado com sucesso!");
                    return;
                }
            }
            System.out.println("Cliente com ID não encontrado.");
        }
    }

    @Override
    public void ConsultarPosicaoLista() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (clientes.size() - 1) + " posições.");
            int pos = lerPosicaoValida(clientes, "cliente");
            ClienteModel cliente = clientes.get(pos);
            System.out.println(cliente);
        }
    }

    public void consultarPorId() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == id) {
                    exibirDadosCliente(cliente);
                    return;
                }
            }
            System.out.println("Cliente com ID não encontrado.");
        }
    }

    public void consultarCNPJ() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("\nDigite o CNPJ do Cliente: ");
            String cnpj = scanner.nextLine();

            boolean encontrado = false;
            for (ClienteModel cliente : clientes) {
                if (cliente.getCnpj().equals(cnpj)) {
                    System.out.println("Cliente encontrado!");
                    System.out.println(cliente);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Cliente com CNPJ " + cnpj + " não encontrado.");
            }
        }
    }

    @Override
    public void excluir() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == id) {
                    clientes.remove(cliente);
                    System.out.println("Cliente removido com sucesso!");
                    return;
                }
            }
            System.out.println("Cliente com ID não encontrado.");
        }
    }

    
    
    
    public void adicionarCliente(ClienteModel cliente) {
        clientes.add(cliente);
    }

    public ArrayList<ClienteModel> getClientes() {
        return clientes;
    }

    public void adicionarFake(ClienteModel cliente) {
        clientes.add(cliente);
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


    public void exibirDadosCliente(ClienteModel cliente) {
        System.out.println("----- DADOS DO CLIENTE -----");
        System.out.println(cliente);  // Chama o toString() da classe ClienteModel
        System.out.println("----------------------------");
        }

}
