package controller;

import java.util.ArrayList;
import model.ClienteModel;
import model.ReceberModel;

public class ReceberController implements InterfaceFinanceiro {

    private ArrayList<ReceberModel> recebimentos = new ArrayList<>();


    @Override
    public void Incluir() {
        ReceberModel novoRecebimento = new ReceberModel();
        
        System.out.println("🔽 INCLUSÃO CONTA A RECEBER:");

        novoRecebimento.setId(lerInteiro("ID: "));
        novoRecebimento.setNumero(lerInteiro("Número: "));
        novoRecebimento.setEmissao(lerTexto("Data de Emissão: "));
        novoRecebimento.setVencimento(lerTexto("Data de Vencimento: "));
        novoRecebimento.setPagamento(lerTexto("Data de Pagamento: "));
        novoRecebimento.setValor(lerDouble("Valor: "));
        novoRecebimento.setJuros(lerDouble("Juros: "));
        novoRecebimento.setMulta(lerDouble("Multa: "));
        novoRecebimento.setDesconto(lerDouble("Desconto: "));
        novoRecebimento.setTotal(lerDouble("Total: "));

        ClienteController clienteController = new ClienteController();
        ClienteModel cliente = clienteController.criarClienteCompleto();

        novoRecebimento.setNotaFiscal(lerTexto("Nota Fiscal: "));

        recebimentos.add(novoRecebimento);
        System.out.println("✅ CONTA A RECEBER incluído com sucesso!");
    }

    @Override
    public void AlterarPorNumero() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            int numero = lerInteiro("Informe o número do título a ser alterado: ");
            for (ReceberModel r : recebimentos) {
                if (r.getNumero() == numero) {
                    System.out.println("🔄 Alterando título: " + numero);

                    r.setNumero(lerInteiro("Novo número: "));
                    r.setEmissao(lerTexto("Nova emissão: "));
                    r.setVencimento(lerTexto("Novo vencimento: "));
                    r.setPagamento(lerTexto("Novo pagamento: "));
                    r.setValor(lerDouble("Novo valor: "));
                    r.setJuros(lerDouble("Novo juros: "));
                    r.setMulta(lerDouble("Novo multa: "));
                    r.setDesconto(lerDouble("Novo desconto: "));
                    r.setTotal(lerDouble("Novo total: "));

                    // Adicionando alteração de cliente
                    ClienteController clienteController = new ClienteController();
                    ClienteModel clienteAlterado = clienteController.alterarCliente();  
                    r.setCliente(clienteAlterado);

                    r.setNotaFiscal(lerTexto("Nova Nota Fiscal: "));

                    System.out.println("✅ Título alterado com sucesso!");
                    return;
                }
            }
            System.out.println("❌ Título não encontrado.");
        }
    }

    @Override
    public void ConsultarPorNumero() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            int numero = lerInteiro("Informe o número do título: ");
            for (ReceberModel r : recebimentos) {
                if (r.getNumero() == numero) {
                    exibirDadosCliente(r);
                    return;
                }
            }
            System.out.println("❌ Nenhum título encontrado com esse número.");
        }
    }

    @Override
    public void ConsultarPorValor() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            double valor = lerDouble("Informe o valor a buscar: ");
            boolean encontrado = false;
            for (ReceberModel r : recebimentos) {
                if (r.getValor() == valor) {
                    exibirDadosCliente(r);
                    encontrado = true;
                }
            }
            if (!encontrado)
                System.out.println("❌ Nenhum título com esse valor.");
        }
    }

    public void ConsultarPorNomeCliente() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            String nome = lerTexto("Informe o nome do cliente: ").toLowerCase();
            boolean encontrado = false;
            for (ReceberModel r : recebimentos) {
                if (r.getCliente().getNome().toLowerCase().contains(nome)) {
                    exibirDadosCliente(r);
                    encontrado = true;
                }
            }
            if (!encontrado)
                System.out.println("❌ Nenhum título encontrado para esse cliente.");
        }
    }

    public void ConsultarPorNotaFiscal() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            String nf = lerTexto("Informe a nota fiscal: ");
            boolean encontrado = false;
            for (ReceberModel r : recebimentos) {
                if (r.getNotaFiscal().equalsIgnoreCase(nf)) {
                    exibirDadosCliente(r);
                    encontrado = true;
                }
            }
            if (!encontrado)
                System.out.println("❌ Nenhum título com essa nota fiscal.");
        }
    }

    @Override
    public void ExcluirPorID() {
        if(recebimentos.isEmpty()){
            System.out.println("\nERRO: Essa Lista esta VEZIA !!!");
        } else {
            int id = lerInteiro("Informe o ID do título a excluir: ");
            for (int i = 0; i < recebimentos.size(); i++) {
                if (recebimentos.get(i).getId() == id) {
                    recebimentos.remove(i);
                    System.out.println("✅ Título removido com sucesso.");
                    return;
                }
            }
            System.out.println("❌ Nenhum título com esse ID.");
        }
    }

    
    // =============================
    // MÉTODOS AUXILIARES
    // =============================

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido. Digite um número decimal.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public ArrayList<ReceberModel> getRecebimentos() {
        return recebimentos;
    }
    
    
    public void exibirDadosCliente(ReceberModel receber) {
    System.out.println("----- DADOS DO CONTA A RECEBER -----");
    System.out.println(receber);
    ClienteModel cliente = receber.getCliente();
    if (cliente != null) {
        System.out.println("----- DADOS DO CLIENTE -----");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("E-mail: " + cliente.getEmail());
        System.out.println("CNPJ: " + cliente.getCnpj());
        // e mais campos se desejar
    }
    System.out.println("----------------------------");
    }
}
