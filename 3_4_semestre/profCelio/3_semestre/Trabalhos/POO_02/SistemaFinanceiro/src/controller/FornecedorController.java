package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.EnderecoModel;
import model.TelefoneModel;
import model.FornecedorModel;

public class FornecedorController implements InterfaceCadastro {
    // Implementação do padrão Singleton
    private static FornecedorController instancia;
    
    private ArrayList<FornecedorModel> fornecedores = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Construtor privado
    private FornecedorController() {}
    
    // Método para obter instância única
    public static FornecedorController getInstancia() {
        if (instancia == null) {
            instancia = new FornecedorController();
        }
        return instancia;
    }

    @Override
    public void incluir() {
        FornecedorModel fornecedor = new FornecedorModel();

        System.out.print("Nome: ");
        fornecedor.setNome(scanner.nextLine());

        
        System.out.println(" --- Cadastro Endereço ---");
        EnderecoController enderecoController = EnderecoController.getInstancia();
        EnderecoModel endereco = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
        fornecedor.setEndereco(endereco);

        
        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        fornecedor.setTelefone(telefone);
        
        
        
        System.out.print("Email: ");
        fornecedor.setEmail(scanner.nextLine());
        
        
        
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
        } else {
            int id = lerIdValido();
            for (FornecedorModel fornecedor : fornecedores) {
                if (fornecedor.getId() == id) {
                    System.out.print("Novo nome: ");
                    fornecedor.setNome(scanner.nextLine());
                    System.out.print("Novo e-mail: ");
                    fornecedor.setEmail(scanner.nextLine());

                    System.out.println("\n--- ALTERAR ENDEREÇO DE ENTREGA ---");
                    EnderecoController enderecoController = EnderecoController.getInstancia();
                    EnderecoModel enderecoEntrega = enderecoController.entrar();  // Chama o método entrar() da EnderecoController
                    fornecedor.setEndereco(enderecoEntrega);
                    
                    
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

                    System.out.println("Fornecedor alterado com sucesso!");
                    return;
                }
            }
            System.out.println("Fornecedor com ID não encontrado.");
        }
    }

    @Override
    public void ConsultarPosicaoLista() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            System.out.println("\nEssa lista contém: " + (fornecedores.size() - 1) + " posições.");
            int pos = lerPosicaoValida(fornecedores, "fornecedor");
            FornecedorModel fornecedor = fornecedores.get(pos);
            System.out.println(fornecedor);
        }
    }
    
    
    @Override
    public void excluir() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for (FornecedorModel fornecedor : fornecedores) {
                if (fornecedor.getId() == id) {
                    fornecedores.remove(fornecedor);
                    System.out.println("Fornecedor removido com sucesso!");
                    return;
                }
            }
            System.out.println("Fornecedor com ID não encontrado.");
        }
    }
    
   
    public void consultarPorId() {
        if (fornecedores.isEmpty()) {
            System.out.println("\nEsta lista está VAZIA!!");
        } else {
            int id = lerIdValido();
            for (FornecedorModel fornecedor : fornecedores) {
                if (fornecedor.getId() == id) {
                    System.out.println(fornecedor);
                    return;
                }
            }
            System.out.println("Fornecedor com ID não encontrado.");
        }
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

    
    public FornecedorModel criarFornecedorCompleto() {
        FornecedorModel fornecedor = new FornecedorModel();

        System.out.print("Nome: ");
        fornecedor.setNome(scanner.nextLine());
        
        System.out.println("Incluindo Endereço: ");
        EnderecoController enderecoController = EnderecoController.getInstancia();
        EnderecoModel endereco = enderecoController.entrar();
        fornecedor.setEndereco(endereco);
        
        System.out.println("Telefone:");
        TelefoneModel telefone = new TelefoneModel();
        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));
        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));
        fornecedor.setTelefone(telefone);
        
        System.out.print("Email: ");
        fornecedor.setEmail(scanner.nextLine());
        
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
        System.out.println("✅ Fornecedor cadastrado com sucesso!");
        
        return fornecedor;
    }
    
    
    public FornecedorModel alterarFornecedor() {
        if (fornecedores.isEmpty()) {
            System.out.println("❌ Lista de fornecedores está vazia.");
            return null;
        }

        int id = lerIdValido();
        
        for (FornecedorModel fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                System.out.println("🔄 Alterando Fornecedor ID: " + id);
                
                System.out.print("Novo nome: ");
                fornecedor.setNome(scanner.nextLine());
                
                System.out.print("Novo e-mail: ");
                fornecedor.setEmail(scanner.nextLine());

                System.out.println("\n--- ALTERAR ENDEREÇO DE ENTREGA ---");
                EnderecoController enderecoController = EnderecoController.getInstancia();
                EnderecoModel enderecoEntrega = enderecoController.entrar();
                fornecedor.setEndereco(enderecoEntrega);
                
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
                
                System.out.print("Novo Site: ");
                fornecedor.setSite(scanner.nextLine());

                System.out.println("✅ Fornecedor alterado com sucesso!");
                return fornecedor;
            }
        }
        
        System.out.println("❌ Fornecedor com ID não encontrado.");
        return null;
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
                System.out.print("Informe o ID do Fornecedor: ");
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
    
    
    public void exibirDadosFornecedor(FornecedorModel fornecedor) {
        System.out.println("\n \n----- DADOS DO FORNECEDOR -----");
        System.out.println(fornecedor);  
        System.out.println("----------------------------\n \n");
    }
}