package controller;

import java.util.ArrayList;

import model.EnderecoModel;
import model.TelefoneModel;
import model.FornecedorModel;

public class FornecedorController implements InterfaceCadastro {

    private ArrayList<FornecedorModel> fornecedores = new ArrayList<>();

    @Override
    public void incluir() {
        FornecedorModel fornecedor = new FornecedorModel();

        System.out.print("Nome: ");
        fornecedor.setNome(scanner.nextLine());

        System.out.print("Email: ");
        fornecedor.setEmail(scanner.nextLine());

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

        fornecedor.setEndereco(endereco);

        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        fornecedor.setTelefone(telefone);

        System.out.print("CNPJ: ");
        fornecedor.setCnpj(scanner.nextLine());

        System.out.print("Inscrição Estadual: ");
        fornecedor.setInscricaoEstadual(scanner.nextLine());

        System.out.print("Contato: ");
        fornecedor.setContato(scanner.nextLine());

        System.out.print("Limite de Compra: ");
        fornecedor.setLimiteCompra(Double.parseDouble(scanner.nextLine()));

        System.out.print("Data de Cadastro: ");
        fornecedor.setDataCadastro(scanner.nextLine());

        System.out.print("Site: ");
        fornecedor.setSite(scanner.nextLine());

        fornecedor.setId(fornecedores.size() + 1);

        fornecedores.add(fornecedor);

        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    @Override
    public void alterarID() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
            return;
        }

        System.out.print("Informe o ID do fornecedor para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FornecedorModel fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                System.out.print("Novo nome: ");
                fornecedor.setNome(scanner.nextLine());

                System.out.print("Novo email: ");
                fornecedor.setEmail(scanner.nextLine());

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

                fornecedor.setEndereco(endereco);

                System.out.println("Alterar telefone:");
                TelefoneModel telefone = new TelefoneModel();
                System.out.print("DDD: ");
                telefone.setDdd(Integer.parseInt(scanner.nextLine()));

                System.out.print("Número: ");
                telefone.setNumero(Long.parseLong(scanner.nextLine()));

                fornecedor.setTelefone(telefone);

                System.out.print("Novo CNPJ: ");
                fornecedor.setCnpj(scanner.nextLine());

                System.out.print("Nova Inscrição Estadual: ");
                fornecedor.setInscricaoEstadual(scanner.nextLine());

                System.out.print("Novo Contato: ");
                fornecedor.setContato(scanner.nextLine());

                System.out.print("Novo Limite de Compra: ");
                fornecedor.setLimiteCompra(Double.parseDouble(scanner.nextLine()));

                System.out.print("Nova Data de Cadastro: ");
                fornecedor.setDataCadastro(scanner.nextLine());

                System.out.print("Novo Site: ");
                fornecedor.setSite(scanner.nextLine());

                System.out.println("Fornecedor alterado com sucesso!");
                return;
            }
        }
        System.out.println("Fornecedor com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicaoLista() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
            return;
        }
        
        System.out.println("\nEssa lista contem: " + fornecedores.size() + " porzições.");;
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < fornecedores.size()) {
            FornecedorModel fornecedor = fornecedores.get(pos);
            System.out.println(fornecedor);
        } else {
            System.out.println("Posição inválida!");
        }
    }
    
    
    @Override
    public void excluir() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
            return;
        }

        System.out.print("Informe o ID do fornecedor para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FornecedorModel fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                fornecedores.remove(fornecedor);
                System.out.println("Fornecedor removido com sucesso!");
                return;
            }
        }
        System.out.println("Fornecedor com ID não encontrado.");
    }

    
    public void consultarPorId() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
            return;
        }

        System.out.print("Informe o ID do fornecedor para consultar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FornecedorModel fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                System.out.println(fornecedor);
                return;
            }
        }
        System.out.println("Fornecedor com ID não encontrado.");
    }
    
    
    public void consultarCNPJ() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
            return;
        }

        System.out.print("\nDigite o CNPJ do Fornecedor: ");
        String cnpj = scanner.nextLine();

        for (FornecedorModel fornecedor : fornecedores) {
            if (fornecedor.getCnpj().equals(cnpj)) {
                System.out.println("Fornecedor encontrado!");
                System.out.println(fornecedor);
                return;
            }
        }

        System.out.println("Fornecedor com CNPJ " + cnpj + " não encontrado.");
    }


    // Métodos auxiliares
    public void adicionarFornecedor(FornecedorModel fornecedor) {
        fornecedores.add(fornecedor);
    }

    public ArrayList<FornecedorModel> getFornecedores() {
        return fornecedores;
    }
    
    public void adicionarFake(FornecedorModel fornecedor) {
        fornecedores.add(fornecedor);
    }
}
