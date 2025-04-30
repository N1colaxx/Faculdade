package controller;

import java.util.ArrayList;
import model.ClienteModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class ClienteController implements InterfaceController {

    private ArrayList<ClienteModel> clientes = new ArrayList<>();


    @Override
    public void incluir() {
        ClienteModel cliente = new ClienteModel();

        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

        System.out.print("CNPJ: ");
        cliente.setCnpj(scanner.nextLine());

        System.out.print("Inscrição Estadual: ");
        cliente.setInscricaoEstadual(scanner.nextLine());

        System.out.print("Contato: ");
        cliente.setContato(scanner.nextLine());

        // Endereço
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

        // Telefone
        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        cliente.setTelefone(telefone);

        // Limite de Crédito
        System.out.print("Limite de Crédito: ");
        cliente.setLimiteCredito(Double.parseDouble(scanner.nextLine()));

        // Endereço de Cobrança
        System.out.println("Endereço de Cobrança:");
        EnderecoModel enderecoCobranca = new EnderecoModel();
        System.out.print("Logradouro: ");
        enderecoCobranca.setLogradouro(scanner.nextLine());

        System.out.print("Número: ");
        enderecoCobranca.setNumero(scanner.nextLine());

        System.out.print("Complemento: ");
        enderecoCobranca.setComplemento(scanner.nextLine());

        System.out.print("Bairro: ");
        enderecoCobranca.setBairro(scanner.nextLine());

        System.out.print("Cidade: ");
        enderecoCobranca.setCidade(scanner.nextLine());

        System.out.print("Estado: ");
        enderecoCobranca.setEstado(scanner.nextLine());

        System.out.print("CEP: ");
        enderecoCobranca.setCep(Integer.parseInt(scanner.nextLine()));

        cliente.setEnderecoCobranca(enderecoCobranca);

        // Definindo o ID do cliente
        cliente.setId(clientes.size() + 1);

        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID do cliente para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (ClienteModel cliente : clientes) {
            if (cliente.getId() == id) {
                System.out.print("Novo nome: ");
                cliente.setNome(scanner.nextLine());

                System.out.print("Novo e-mail: ");
                cliente.setEmail(scanner.nextLine());

                System.out.print("Novo CNPJ: ");
                cliente.setCnpj(scanner.nextLine());

                System.out.print("Nova Inscrição Estadual: ");
                cliente.setInscricaoEstadual(scanner.nextLine());

                System.out.print("Novo contato: ");
                cliente.setContato(scanner.nextLine());

                // Alterar endereço
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

                // Alterar telefone
                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                cliente.setTelefone(telefone);

                // Alterar limite de crédito
                System.out.print("Novo limite de crédito: ");
                cliente.setLimiteCredito(Double.parseDouble(scanner.nextLine()));

                // Alterar endereço de cobrança
                System.out.println("Alterar endereço de cobrança:");
                EnderecoModel enderecoCobranca = new EnderecoModel();
                System.out.print("Logradouro: ");
                enderecoCobranca.setLogradouro(scanner.nextLine());

                System.out.print("Número: ");
                enderecoCobranca.setNumero(scanner.nextLine());

                System.out.print("Complemento: ");
                enderecoCobranca.setComplemento(scanner.nextLine());

                System.out.print("Bairro: ");
                enderecoCobranca.setBairro(scanner.nextLine());

                System.out.print("Cidade: ");
                enderecoCobranca.setCidade(scanner.nextLine());

                System.out.print("Estado: ");
                enderecoCobranca.setEstado(scanner.nextLine());

                System.out.print("CEP: ");
                enderecoCobranca.setCep(Integer.parseInt(scanner.nextLine()));

                cliente.setEnderecoCobranca(enderecoCobranca);

                System.out.println("Cliente alterado com sucesso!");
                return;
            }
        }
        System.out.println("Cliente com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < clientes.size()) {
            ClienteModel cliente = clientes.get(pos);
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("CNPJ: " + cliente.getCnpj());
            System.out.println("Inscrição Estadual: " + cliente.getInscricaoEstadual());
            System.out.println("Contato: " + cliente.getContato());
            System.out.println("Endereço: " + cliente.getEndereco().getLogradouro() + ", " + cliente.getEndereco().getNumero());
            System.out.println("Telefone: (" + cliente.getTelefone().getDdd() + ") " + cliente.getTelefone().getNumero());
            System.out.println("Limite de Crédito: R$" + cliente.getLimiteCredito());
            System.out.println("Endereço de Cobrança: " + cliente.getEnderecoCobranca().getLogradouro() + ", " + cliente.getEnderecoCobranca().getNumero());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
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

    // Métodos auxiliares
    public void adicionarCliente(ClienteModel cliente) {
        clientes.add(cliente);
    }

    public ArrayList<ClienteModel> getClientes() {
        return clientes;
    }
}
