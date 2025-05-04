package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.PagarModel;
import model.FornecedorModel;
import controller.FornecedorController;

public class PagarController implements InterfaceFinanceiro {

    // Implementação correta do padrão Singleton
    private static PagarController instancia;
    
    private ArrayList<PagarModel> contasPagar = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Construtor privado para evitar instanciação externa
    private PagarController() {}

    // Método Singleton para obter a única instância
    public static PagarController getInstancia() {
        if (instancia == null) {
            instancia = new PagarController();
        }
        return instancia;
    }

    // ===== IMPLEMENTAÇÃO DOS MÉTODOS DA INTERFACE =====

    @Override
    public void Incluir() {
        PagarModel novo = new PagarModel();

        System.out.println("🔽 Cadastrar CONTA A PAGAR:");

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

        // Usando o FornecedorController para criar um fornecedor
        System.out.println("Incluindo Fornecedor: ");
        FornecedorController fornecedorController = FornecedorController.getInstancia();
        FornecedorModel fornecedor = fornecedorController.criarFornecedorCompleto();
        novo.setFornecedor(fornecedor);

        novo.setBoleto(lerTexto("Boleto: "));

        contasPagar.add(novo);
        System.out.println("✅ Conta a pagar cadastrada com sucesso!");
    }

    @Override
    public void AlterarPorNumero() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        int numero = lerInteiro("Informe o número da conta a pagar para alterar: ");

        for (PagarModel p : contasPagar) {
            if (p.getNumero() == numero) {
                System.out.println("🔄 Alterando Conta a Pagar Nº " + numero);

                p.setNumero(lerInteiro("Novo número: "));
                p.setEmissao(lerTexto("Nova emissão: "));
                p.setVencimento(lerTexto("Novo vencimento: "));
                p.setPagamento(lerTexto("Novo pagamento: "));
                p.setValor(lerDouble("Novo valor: "));
                p.setJuros(lerDouble("Novo juros: "));
                p.setMulta(lerDouble("Nova multa: "));
                p.setDesconto(lerDouble("Novo desconto: "));
                p.setTotal(calcularTotal(p.getValor(), p.getJuros(), p.getDesconto()));

                // Alterando o fornecedor usando FornecedorController
                System.out.println("Alteração dos dados do Fornecedor:");
                FornecedorController fornecedorController = FornecedorController.getInstancia();
                
                // Se já existe um fornecedor associado, usamos seu ID para alteração
                if (p.getFornecedor() != null && p.getFornecedor().getId() > 0) {
                    System.out.println("Alterando fornecedor existente (ID: " + p.getFornecedor().getId() + "):");
                    fornecedorController.adicionarFake(p.getFornecedor()); // Adicionamos o fornecedor atual à lista temporariamente
                    FornecedorModel fornecedorAlterado = fornecedorController.alterarFornecedor();
                    if (fornecedorAlterado != null) {
                        p.setFornecedor(fornecedorAlterado);
                    } else {
                        System.out.println("❌ Fornecedor não foi alterado.");
                    }
                } else {
                    // Se não existe fornecedor, criamos um novo
                    System.out.println("Criando novo fornecedor:");
                    FornecedorModel novoFornecedor = fornecedorController.criarFornecedorCompleto();
                    p.setFornecedor(novoFornecedor);
                }

                p.setBoleto(lerTexto("Novo Boleto: "));

                System.out.println("✅ Conta a pagar alterada com sucesso!");
                return;
            }
        }

        System.out.println("❌ Conta a pagar não encontrada.");
    }

    @Override
    public void ConsultarPorNumero() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        int numero = lerInteiro("Informe o número da conta: ");

        for (PagarModel p : contasPagar) {
            if (p.getNumero() == numero) {
                exibirDadosPagamento(p);
                return;
            }
        }

        System.out.println("❌ Nenhuma conta encontrada com esse número.");
    }

    @Override
    public void ConsultarPorValor() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        double valor = lerDouble("Informe o valor a buscar: ");
        boolean encontrado = false;

        for (PagarModel p : contasPagar) {
            if (p.getValor() == valor) {
                exibirDadosPagamento(p);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada com esse valor.");
    }

    @Override
    public void ExcluirPorID() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        int id = lerInteiro("Informe o ID da conta a excluir: ");

        for (int i = 0; i < contasPagar.size(); i++) {
            if (contasPagar.get(i).getId() == id) {
                contasPagar.remove(i);
                System.out.println("✅ Conta removida com sucesso.");
                return;
            }
        }

        System.out.println("❌ Nenhuma conta com esse ID.");
    }

    public void ConsultarPorCNPJ() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        String cnpj = lerTexto("Informe o CNPJ do fornecedor: ");
        boolean encontrado = false;

        for (PagarModel p : contasPagar) {
            // Usando o método getCnpj() conforme a implementação atual
            if (p.getFornecedor() != null && p.getFornecedor().getCnpj() != null &&
                p.getFornecedor().getCnpj().equals(cnpj)) {

                exibirDadosPagamento(p);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada para esse CNPJ.");
    }

    public void ConsultarPorBoleto() {
        if (contasPagar.isEmpty()) {
            System.out.println("❌ Lista de contas a pagar está vazia.");
            return;
        }

        String boleto = lerTexto("Informe o número do boleto: ");
        boolean encontrado = false;

        for (PagarModel p : contasPagar) {
            if (p.getBoleto().equalsIgnoreCase(boleto)) {
                exibirDadosPagamento(p);
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("❌ Nenhuma conta encontrada com esse boleto.");
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
        return (valor + juros) - desconto;
    }

    private void exibirDadosPagamento(PagarModel p) {
        System.out.println(p);
        System.out.println("----------------------------\n \n");

        
    }

    // ===== MÉTODOS EXTRAS =====

    public void adicionarFake(PagarModel pagar) {
        contasPagar.add(pagar);
    }

    public ArrayList<PagarModel> getContasPagar() {
        return contasPagar;
    }
}