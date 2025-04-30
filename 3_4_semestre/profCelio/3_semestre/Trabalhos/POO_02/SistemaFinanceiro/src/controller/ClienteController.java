package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ClienteModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class ClienteController {

    private ArrayList<ClienteModel> clientes = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Método para incluir um novo cliente
    public void incluir() {
        ClienteModel cliente = new ClienteModel();

        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

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

        cliente.setEndereco(endereco);

        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        cliente.setTelefone(telefone);

        System.out.print("CNPJ: ");
        cliente.setCnpj(scanner.nextLine());

        System.out.print("Inscrição Estadual: ");
        cliente.setInscricaoEstadual(scanner.nextLine());

        cliente.setId(clientes.size() + 1);

        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Método para alterar um cliente pelo ID
    public void alterarID() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("Informe o ID do cliente para alterar: ");
            int id = Integer.parseInt(scanner.nextLine());

            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == id) {
                    System.out.print("Novo nome: ");
                    cliente.setNome(scanner.nextLine());

                    System.out.print("Novo e-mail: ");
                    cliente.setEmail(scanner.nextLine());

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

                    cliente.setEndereco(endereco);

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

    // Método para consultar cliente pela posição na lista
    public void consultarPosicaoLista() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + clientes.size() + " posições.");
            System.out.print("Digite a posição que deseja consultar: ");
            int pos = Integer.parseInt(scanner.nextLine());

            if (pos >= 0 && pos < clientes.size()) {
                ClienteModel cliente = clientes.get(pos);
                exibirDadosCliente(cliente);
            } else {
                System.out.println("Posição inválida!");
            }
        }
    }

    // Método para consultar cliente pelo ID
    public void consultarPorId() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("Informe o ID do cliente para consultar: ");
            int id = Integer.parseInt(scanner.nextLine());

            for (ClienteModel cliente : clientes) {
                if (cliente.getId() == id) {
                    exibirDadosCliente(cliente);
                    return;
                }
            }
            System.out.println("Cliente com ID não encontrado.");
        }
    }

    // Método para consultar cliente pelo CNPJ
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
                    exibirDadosCliente(cliente);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Cliente com CNPJ " + cnpj + " não encontrado.");
            }
        }
    }

    // Método para excluir cliente pelo ID
    public void excluir() {
        if (clientes.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.print("Informe o ID do cliente para excluir: ");
            int id = Integer.parseInt(scanner.nextLine());

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

    // Método auxiliar para exibir os dados de um cliente
    private void exibirDadosCliente(ClienteModel cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Endereço: " + cliente.getEndereco().getLogradouro() + ", " + cliente.getEndereco().getNumero());
        System.out.println("Telefone: (" + cliente.getTelefone().getDdd() + ") " + cliente.getTelefone().getNumero());
        System.out.println("CNPJ: " + cliente.getCnpj());
        System.out.println("Inscrição Estadual: " + cliente.getInscricaoEstadual());
    }

    // Método para adicionar um cliente à lista
    public void adicionarCliente(ClienteModel cliente) {
        clientes.add(cliente);
    }

    // Método para obter a lista de clientes
    public ArrayList<ClienteModel> getClientes() {
        return clientes;
    }
}
