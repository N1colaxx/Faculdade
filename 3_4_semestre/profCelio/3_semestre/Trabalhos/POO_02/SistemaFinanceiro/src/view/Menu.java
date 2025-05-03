package view;
import faker.ClienteFaker;
import faker.FornecedorFaker;
import faker.FuncionarioFaker;
import faker.ReceberFaker;
import faker.PagarFaker;

import controller.FuncionarioController;
import controller.ClienteController;
import controller.FornecedorController;
import controller.ReceberController;
import controller.PagarController;
import controller.FluxoCaixaController;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final FuncionarioController funcionario = new FuncionarioController();
    private final ClienteController cliente =  ClienteController.getInstancia();
    private final FornecedorController fornecedor =  FornecedorController.getInstancia();
    private final ReceberController contasReceber = ReceberController.getInstancia();
    private final PagarController contasPagar = PagarController.getInstancia();

    private boolean clientesFakeJaGerados = false;
    private boolean funcionariosFakeJaGerados = false;
    private boolean fornecedoresFakeJaGerados = false;
    private boolean receberFakeJaGerados = false;
    private boolean pagarFakerGerados = false;
    

    public void exibirMenuPrincipal() {
        gerarDadosFakerAutomaticamente();
        
        String opcao;
        boolean sair = false;
        do {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1. Cadastro de Funcionários");
            System.out.println("2. Cadastro de Clientes");
            System.out.println("3. Cadastro de Fornecedores");
            System.out.println("4. Contas a Receber");
            System.out.println("5. Contas a Pagar");
            System.out.println("6. Fluxo de Caixa");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim();

            if (validarOpcaoNumerica(opcao, 1, 7)) {
                switch (Integer.parseInt(opcao)) {
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
                        menuContasReceber();
                        break;
                    case 5:
                        menuContasPagar();
                        break;
                    case 6:
                        FluxoCaixaController.getInstancia().mostrarFluxoCaixa();
                        break;
                    case 7:
                        System.out.println("Encerrando o sistema.");
                        sair = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite um número entre 1 e 7.");
            }
        } while (!sair);
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
        
        if(!receberFakeJaGerados){
            ReceberFaker faker = new ReceberFaker();
            for (int i = 0; i < qtdPadrao; i++){
                contasReceber.adicionarFake(faker.gerarRecebimentoFalso());
            }
            receberFakeJaGerados = true;
            System.out.println(qtdPadrao + " contas a receber FAKE gerados automaticamente ");
        }
        
        if(!pagarFakerGerados){
            PagarFaker Faker = new PagarFaker();
            for (int i = 0; i < qtdPadrao; i++){
                contasPagar.adicionarFake(Faker.gerarPagamentoFalso());
            }
            pagarFakerGerados = true;
            System.out.println(qtdPadrao + " contas a pagar FAKE gerados automaticamente ");
        }
    }

    private void menuCliente() {
        String opcao;
        boolean voltar = false;
        do {
            System.out.println("\n--- Cadastro de Clientes ---");
            System.out.println("a. Incluir");
            System.out.println("b. Alterar pelo ID");
            System.out.println("c. Consultar pela posição na Lista");
            System.out.println("d. Consultar pelo ID");
            System.out.println("e. Consultar pelo CNPJ");
            System.out.println("f. Excluir pelo ID");
            System.out.println("g. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim().toLowerCase();

            if (validarOpcaoAlfabetica(opcao, 'a', 'g')) {
                switch (opcao) {
                    case "a":
                        cliente.incluir();
                        break;
                    case "b":
                        cliente.alterarID();
                        break;
                    case "c":
                        cliente.ConsultarPosicaoLista();
                        break;
                    case "d":
                        cliente.consultarPorId();
                        break;
                    case "e":
                        cliente.consultarCNPJ();
                        break;
                    case "f":
                        cliente.excluir();
                        break;
                    case "g":
                        voltar = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite uma letra entre 'a' e 'g'.");
            }
        } while (!voltar);
    }

    private void menuFuncionario() {
        String opcao;
        boolean voltar = false;
        do {
            System.out.println("\n--- Cadastro de Funcionarios ---");
            System.out.println("a. Incluir");
            System.out.println("b. Alterar pelo ID");
            System.out.println("c. Consultar pela posição na Lista");
            System.out.println("d. Consultar por CPF");
            System.out.println("e. Consultar por Nome");
            System.out.println("f. Excluir pelo ID");
            System.out.println("g. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim().toLowerCase();

            if (validarOpcaoAlfabetica(opcao, 'a', 'g')) {
                switch (opcao) {
                    case "a":
                        funcionario.incluir();
                        break;
                    case "b":
                        funcionario.alterarID();
                        break;
                    case "c":
                        funcionario.ConsultarPosicaoLista();
                        break;
                    case "d":
                        funcionario.consultarCPF();
                        break;
                    case "e":
                        funcionario.consultarPorNome();
                        break;
                    case "f":
                        funcionario.excluir();
                        break;
                    case "g":
                        voltar = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite uma letra entre 'a' e 'g'.");
            }
        } while (!voltar);
    }

    private void menuFornecedor() {
        String opcao;
        boolean voltar = false;
        do {
            System.out.println("\n--- Cadastro de Fornecedores ---");
            System.out.println("a. Incluir");
            System.out.println("b. Alterar pelo ID");
            System.out.println("c. Consultar pela posição na Lista");
            System.out.println("d. Consultar pelo ID");
            System.out.println("e. Consultar pelo CNPJ");
            System.out.println("f. Excluir pelo ID");
            System.out.println("g. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim().toLowerCase();

            if (validarOpcaoAlfabetica(opcao, 'a', 'g')) {
                switch (opcao) {
                    case "a":
                        fornecedor.incluir();
                        break;
                    case "b":
                        fornecedor.alterarID();
                        break;
                    case "c":
                        fornecedor.ConsultarPosicaoLista();
                        break;
                    case "d":
                        fornecedor.consultarPorId();
                        break;
                    case "e":
                        fornecedor.consultarCNPJ();
                        break;
                    case "f":
                        fornecedor.excluir();
                        break;
                    case "g":
                        voltar = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite uma letra entre 'a' e 'g'.");
            }
        } while (!voltar);
    }

    private void menuContasReceber() {
        String opcao;
        boolean voltar = false;
        do {
            System.out.println("\n\n--- Cadastro de Contas a Receber ---");
            System.out.println("a. Incluir");
            System.out.println("b. Alterar pelo número");
            System.out.println("c. Consultar pelo Nome do Cliente");
            System.out.println("d. Consultar pelo Número");
            System.out.println("e. Consultar pelo Valor");
            System.out.println("f. Consultar pela Nota Fiscal");
            System.out.println("g. Excluir pelo ID");
            System.out.println("h. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim().toLowerCase();

            if (validarOpcaoAlfabetica(opcao, 'a', 'h')) {
                switch (opcao) {
                    case "a":
                        contasReceber.Incluir();
                        break;
                    case "b":
                        contasReceber.AlterarPorNumero();
                        break;
                    case "c":
                        contasReceber.ConsultarPorNomeCliente();
                        break;
                    case "d":
                        contasReceber.ConsultarPorNumero();
                        break;
                    case "e":
                        contasReceber.ConsultarPorValor();
                        break;
                    case "f":
                        contasReceber.ConsultarPorNotaFiscal();
                        break;
                    case "g":
                        contasReceber.ExcluirPorID();
                        break;
                    case "h":
                        voltar = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite uma letra entre 'a' e 'h'.");
            }
        } while (!voltar);
    }

    private void menuContasPagar() {
        String opcao;
        boolean voltar = false;
        do {
            System.out.println("\n--- Contas a Pagar ---");
            System.out.println("a. Incluir");
            System.out.println("b. Alterar pelo número");
            System.out.println("c. Consultar pelo CNPJ do Fornecedor");
            System.out.println("d. Consultar pelo Número");
            System.out.println("e. Consultar pelo Valor");
            System.out.println("f. Consultar pelo Boleto");
            System.out.println("g. Excluir pelo ID");
            System.out.println("h. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine().trim().toLowerCase();

            if (validarOpcaoAlfabetica(opcao, 'a', 'h')) {
                switch (opcao) {
                    case "a":
                        contasPagar.Incluir();
                        break;
                    case "b":
                        contasPagar.AlterarPorNumero();
                        break;
                    case "c":
                        contasPagar.ConsultarPorCNPJ();
                        break;
                    case "d":
                        contasPagar.ConsultarPorNumero();
                        break;
                    case "e":
                        contasPagar.ConsultarPorValor();
                        break;
                    case "f":
                        contasPagar.ConsultarPorBoleto();
                        break;
                    case "g":
                        contasPagar.ExcluirPorID();
                        break;
                    case "h":
                        voltar = true;
                        break;
                }
            } else {
                System.out.println("ERRO: Opção inválida! Por favor, digite uma letra entre 'a' e 'h'.");
            }
        } while (!voltar);
    }

   

    /**
     * Valida se a entrada é um número dentro do intervalo especificado
     * @param opcao A string a ser validada
     * @param min O valor mínimo aceitável
     * @param max O valor máximo aceitável
     * @return true se for um número válido dentro do intervalo, false caso contrário
     */
    private boolean validarOpcaoNumerica(String opcao, int min, int max) {
        try {
            int valor = Integer.parseInt(opcao);
            return valor >= min && valor <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida se a entrada é uma letra dentro do intervalo especificado
     * @param opcao A string a ser validada
     * @param min A letra mínima aceitável
     * @param max A letra máxima aceitável
     * @return true se for uma letra válida dentro do intervalo, false caso contrário
     */
    private boolean validarOpcaoAlfabetica(String opcao, char min, char max) {
        if (opcao.length() != 1) {
            return false;
        }
        
        char letra = opcao.charAt(0);
        return letra >= min && letra <= max;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.exibirMenuPrincipal();
    }
}