package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ClienteModel;
import model.ReceberModel;

public class ReceberController implements InterfaceFinanceiro {

    // Implementação correta do padrão Singleton
    private static ReceberController instancia;
    
    private ArrayList<ReceberModel> recebimentos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Construtor privado para evitar instanciação externa
    private ReceberController() {}

    // Método Singleton para obter a única instância
    public static ReceberController getInstancia() {
        if (instancia == null) {
            instancia = new ReceberController();
        }
        return instancia;
    }

    // ===== IMPLEMENTAÇÃO DOS MÉTODOS DA INTERFACE =====

    @Override
    public void Incluir() {
        ReceberModel novo = new ReceberModel();

        System.out.println("🔽 Cadastrar CONTA A RECEBER:");

        novo.setId(lerInteiro("ID: "));
        novo.setNumero(lerInteiro("Número: "));
        novo.setEmissao(lerTexto("Data de Emissão: "));
        novo.setVencimento(lerTexto("Data de Vencimento: "));
        novo.setPagamento(lerTexto("Data de Pagamento: "));
        novo.setValor(lerDouble("Valor: "));
        novo.setJuros(lerDouble("Juros: "));
        novo.setMulta(lerDouble("Multa: "));
        novo.setDesconto(lerDouble("Desconto: "));
        novo.setTotal(calcularTotal(novo.getValor(), novo.getJuros(), novo.getDesconto()));

        // Usando o singleton do ClienteController
        ClienteModel cliente = ClienteController.getInstancia().criarClienteCompleto();
        novo.setCliente(cliente);

        novo.setNotaFiscal(lerTexto("Nota Fiscal: "));

        recebimentos.add(novo);
        
        System.out.println("✅ Conta a receber cadastrada com sucesso!" + "\n \n");
    }

    @Override
    public void AlterarPorNumero() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        int numero = lerInteiro("Informe o número da conta a receber para alterar: ");

        for (ReceberModel r : recebimentos) {
            if (r.getNumero() == numero) {
                System.out.println("🔄 Alterando Conta a Receber Nº " + numero);

                r.setNumero(lerInteiro("Novo número: "));
                r.setEmissao(lerTexto("Nova emissão: "));
                r.setVencimento(lerTexto("Novo vencimento: "));
                r.setPagamento(lerTexto("Novo pagamento: "));
                r.setValor(lerDouble("Novo valor: "));
                r.setJuros(lerDouble("Novo juros: "));
                r.setMulta(lerDouble("Novo multa: "));
                r.setDesconto(lerDouble("Novo desconto: "));
                r.setTotal(calcularTotal(r.getValor(), r.getJuros(), r.getDesconto()));

                // Usando o singleton do ClienteController
                ClienteModel clienteAlterado = ClienteController.getInstancia().alterarCliente();
                if (clienteAlterado != null) {
                    r.setCliente(clienteAlterado);
                } else {
                    System.out.println("❌ Cliente não foi alterado.");
                }

                r.setNotaFiscal(lerTexto("Nova Nota Fiscal: "));

                System.out.println("✅ Conta a receber alterada com sucesso!" + "\n \n");
                return;
            }
        }

        System.out.println("❌ Conta a receber não encontrada.");
    }

    @Override
    public void ConsultarPorNumero() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        int numero = lerInteiro("Informe o número da conta: ");

        for (ReceberModel r : recebimentos) {
            if (r.getNumero() == numero) {
                
                exibirDadosRecebimento(r);
                return;
            }
        }

        System.out.println("❌ Nenhuma conta encontrada com esse número.");
    }

    @Override
    public void ConsultarPorValor() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        double valor = lerDouble("Informe o valor a buscar: ");
        boolean encontrado = false;

        for (ReceberModel r : recebimentos) {
            if (r.getValor() == valor) {
                exibirDadosRecebimento(r);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada com esse valor.");
    }

    @Override
    public void ExcluirPorID() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        int id = lerInteiro("Informe o ID da conta a excluir: ");

        for (int i = 0; i < recebimentos.size(); i++) {
            if (recebimentos.get(i).getId() == id) {
                recebimentos.remove(i);
                System.out.println("✅ Conta removida com sucesso.");
                return;
            }
        }

        System.out.println("❌ Nenhuma conta com esse ID.");
    }

    
    public void ConsultarPorNomeCliente() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        String nome = lerTexto("Informe o nome do cliente: ").toLowerCase();
        boolean encontrado = false;

        for (ReceberModel r : recebimentos) {
            if (r.getCliente() != null && r.getCliente().getNome() != null &&
                r.getCliente().getNome().toLowerCase().contains(nome)) {

                exibirDadosRecebimento(r);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada para esse cliente.");
    }

    
    public void ConsultarPorNotaFiscal() {
        if (recebimentos.isEmpty()) {
            System.out.println("❌ Lista de contas a receber está vazia.");
            return;
        }

        String nf = lerTexto("Informe a nota fiscal: ");
        boolean encontrado = false;

        for (ReceberModel r : recebimentos) {
            if (r.getNotaFiscal().equalsIgnoreCase(nf)) {
                exibirDadosRecebimento(r);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada com essa nota fiscal.");
    }

    
    // ===== MÉTODOS AUXILIARES =====

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
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty())
                System.out.println("❌ Entrada vazia. Tente novamente.");
        } while (entrada.isEmpty());

        return entrada;
    }

    private double calcularTotal(double valor, double juros, double desconto) {
        return (valor + juros)  - desconto;
    }

    private void exibirDadosRecebimento(ReceberModel r) {
        System.out.println(r);
        System.out.println("----------------------------\n \n");

    }

    // ===== MÉTODOS EXTRAS =====

    public void adicionarFake(ReceberModel receber) {
        recebimentos.add(receber);
    }

    public ArrayList<ReceberModel> getRecebimentos() {
        return recebimentos;
    }
}