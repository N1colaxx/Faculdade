package view;
import faker.ClienteFaker;

import controller.FuncionarioController;
import controller.ClienteController;
import controller.FornecedorController;
import faker.FornecedorFaker;
import faker.FuncionarioFaker;
//import controller.ContasReceberController;
//import controller.ContasPagarController;
//import controller.FluxoCaixaController;
import java.util.Scanner;
import model.ClienteModel;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final FuncionarioController funcionario = new FuncionarioController();
    private final ClienteController cliente = new ClienteController();
    private final FornecedorController fornecedor = new FornecedorController();
//    private final ContasReceberController contasReceber = new ContasReceberController();
//    private final ContasPagarController contasPagar = new ContasPagarController();
//    private final FluxoCaixaController fluxoCaixa = new FluxoCaixaController();

    private boolean clientesFakeJaGerados = false;
    private boolean funcionariosFakeJaGerados = false;
    private boolean fornecedoresFakeJaGerados = false;

    public void exibirMenuPrincipal() {
        gerarDadosFakerAutomaticamente();
        
        int opcao;
        do {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1) Cadastro de Funcionários");
            System.out.println("2) Cadastro de Clientes");
            System.out.println("3) Cadastro de Fornecedores");
            System.out.println("4) Contas a Receber");
            System.out.println("5) Contas a Pagar");
            System.out.println("6) Fluxo de Caixa");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    menuFuncionario();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 3:
                    menuFornecedor();
                    break;
                case 4:
//                    menuContasReceber();
                    break;
                case 5:
//                    menuContasPagar();
                    break;
                case 6:
//                    menuFluxoCaixa();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    public void gerarDadosFakerAutomaticamente() {
        int qtdPadrao = 5;

        if (!clientesFakeJaGerados) {
            ClienteFaker faker = new ClienteFaker();
            for (int i = 0; i < qtdPadrao; i++) {
                cliente.adicionarFake(faker.gerarClienteFalso());
            }
            clientesFakeJaGerados = true;
            System.out.println(qtdPadrao + " clientes fake gerados automaticamente.");
        }

        if (!funcionariosFakeJaGerados) {
            FuncionarioFaker faker = new FuncionarioFaker();
            for (int i = 0; i < qtdPadrao; i++) {
                funcionario.adicionarFake(faker.gerarFuncionario(i));
            }
            funcionariosFakeJaGerados = true;
            System.out.println(qtdPadrao + " funcionários fake gerados automaticamente.");
        }

        if (!fornecedoresFakeJaGerados) {
            FornecedorFaker faker = new FornecedorFaker();
            for (int i = 0; i < qtdPadrao; i++) {
                fornecedor.adicionarFake(faker.gerarFornecedorFake());
            }
            fornecedoresFakeJaGerados = true;
            System.out.println(qtdPadrao + " fornecedores fake gerados automaticamente.");
        }
    }

    private void menuCliente() {
        int opcao;
        do {
            System.out.println("\n--- Cadastro de Clientes ---");
            System.out.println("1) Incluir");
            System.out.println("2) Alterar pelo ID");
            System.out.println("3) Consultar pela posição na Lista");
            System.out.println("4) Consultar pelo ID");
            System.out.println("5) Consultar pelo CNPJ");
            System.out.println("6) Excluir pelo ID");
            System.out.println("7) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    cliente.incluir();
                    break;
                case 2:
                    cliente.alterarID();
                    break;
                case 3:
                    cliente.ConsultarPosicaoLista();
                    break;
                case 4:
                    cliente.consultarPorId();
                    break;
                case 5:
                    cliente.consultarCNPJ();
                    break;
                case 6:
                    cliente.excluir();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private void menuFuncionario() {
        int opcao;
        do {
            System.out.println("\n--- Cadastro de Funcionarios ---");
            System.out.println("1) Incluir");
            System.out.println("2) Alterar pelo ID");
            System.out.println("3) Consultar pela posição na Lista");
            System.out.println("4) Consultar por CPF");
            System.out.println("5) Consultar por Nome");
            System.out.println("6) Excluir pelo ID");
            System.out.println("7) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    funcionario.incluir();
                    break;
                case 2:
                    funcionario.alterarID();
                    break;
                case 3:
                    funcionario.ConsultarPosicaoLista();
                    break;
                case 4:
                    funcionario.consultarCPF();
                    break;
                case 5:
                    funcionario.consultarPorNome();
                    break;
                case 6:
                    funcionario.excluir();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

    private void menuFornecedor() {
        int opcao;
        do {
            System.out.println("\n--- Cadastro de Fornecedores ---");
            System.out.println("1) Incluir");
            System.out.println("2) Alterar pelo ID");
            System.out.println("3) Consultar pela posição na Lista");
            System.out.println("4) Consultar pelo ID");
            System.out.println("5) Consultar pelo CNPJ");
            System.out.println("6) Excluir pelo ID");
            System.out.println("7) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    fornecedor.incluir();
                    break;
                case 2:
                    fornecedor.alterarID();
                    break;
                case 3:
                    fornecedor.ConsultarPosicaoLista();
                    break;
                case 4:
                    fornecedor.consultarPorId();
                    break;
                case 5:
                    fornecedor.consultarCNPJ();
                    break;
                case 6:
                    fornecedor.excluir();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }

//    private void menuContasReceber() {
//        int opcao;
//        do {
//            System.out.println("\n--- Contas a Receber ---");
//            System.out.println("1) Incluir");
//            System.out.println("2) Alterar pelo número");
//            System.out.println("3) Consultar pelo Nome do Cliente");
//            System.out.println("4) Consultar pelo Número");
//            System.out.println("5) Consultar pelo Valor");
//            System.out.println("6) Consultar pela Nota Fiscal");
//            System.out.println("7) Excluir pelo ID");
//            System.out.println("8) Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            opcao = Integer.parseInt(scanner.nextLine());
//            switch (opcao) {
//                case 1:
//                    contasReceber.incluir();
//                    break;
//                case 2:
//                    contasReceber.alterarPorNumero();
//                    break;
//                case 3:
//                    contasReceber.consultarPorNomeCliente();
//                    break;
//                case 4:
//                    contasReceber.consultarPorNumero();
//                    break;
//                case 5:
//                    contasReceber.consultarPorValor();
//                    break;
//                case 6:
//                    contasReceber.consultarPorNotaFiscal();
//                    break;
//                case 7:
//                    contasReceber.excluir();
//                    break;
//                case 8:
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//            }
//        } while (opcao != 8);
//    }
//
//    private void menuContasPagar() {
//        int opcao;
//        do {
//            System.out.println("\n--- Contas a Pagar ---");
//            System.out.println("1) Incluir");
//            System.out.println("2) Alterar pelo número");
//            System.out.println("3) Consultar pelo CNPJ do Fornecedor");
//            System.out.println("4) Consultar pelo Número");
//            System.out.println("5) Consultar pelo Valor");
//            System.out.println("6) Consultar pelo Boleto");
//            System.out.println("7) Excluir pelo ID");
//            System.out.println("8) Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            opcao = Integer.parseInt(scanner.nextLine());
//            switch (opcao) {
//                case 1:
//                    contasPagar.incluir();
//                    break;
//                case 2:
//                    contasPagar.alterarPorNumero();
//                    break;
//                case 3:
//                    contasPagar.consultarPorCNPJFornecedor();
//                    break;
//                case 4:
//                    contasPagar.consultarPorNumero();
//                    break;
//                case 5:
//                    contasPagar.consultarPorValor();
//                    break;
//                case 6:
//                    contasPagar.consultarPorBoleto();
//                    break;
//                case 7:
//                    contasPagar.excluir();
//                    break;
//                case 8:
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//            }
//        } while (opcao != 8);
//    }
//
//    private void menuFluxoCaixa() {
//        System.out.println("\n--- Fluxo de Caixa ---");
//        System.out.println("Gerando relatório de fluxo de caixa...");
//        
//        fluxoCaixa.gerarRelatorio();
//        
//        System.out.println("\nPressione ENTER para voltar ao menu principal");
//        scanner.nextLine();
//    }


    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.exibirMenuPrincipal();
    }
}