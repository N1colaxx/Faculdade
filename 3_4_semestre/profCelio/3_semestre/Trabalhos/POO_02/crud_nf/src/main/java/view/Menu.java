package view;

import controller.*;
import dao.*;
import fake.NotaFiscalFaker;
import model.NotaFiscalModel;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner;
    private NotaFiscalController nfController;
    private NotaFiscalDAO dao;

    private boolean nfeFakerGerados = false;

    public Menu(NotaFiscalController nfController, NotaFiscalDAO dao) {
        this.dao = dao;
        gerarDadosFakerAutomaticamente();
        scanner = new Scanner(System.in);
        this.nfController = nfController;
    }

    public void gerarDadosFakerAutomaticamente() {
        int qtdPadrao = 5;

        if (!nfeFakerGerados) {
            NotaFiscalFaker faker = new NotaFiscalFaker();
            for (int i = 0; i < qtdPadrao; i++) {
                dao.adicionarFaker(faker.gerarNotaFiscalFake());
            }
            nfeFakerGerados = true;
            System.out.println(qtdPadrao + " clientes fake gerados automaticamente.");
        }
    }

    public void exibir() {
        boolean continuar = true;

        while (continuar) {

            System.out.println("""    
            | ------------------------ MENU --------------------|"
            |   1. Incluir NF-e                                 |
            |   2. Alterar NF-e pelo número                     |
            |   3. Excluir NF-e pelo número                     |
            |   4. Consultar NF-e"                              |
            |   5. Listagem de NF-e por intervalo de número     |
            |   6. Exibir NF-e FAKE                             |
            |   7. Sair                                         |
            |---------------------------------------------------|
            """);

            int opcao = lerOpcaoNumerica("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("\nCRIANDO NF-e à mão...");
                    nfController.IncluirNF();
                    break;
                case 2:
                    subMenuAlteracoes();
                    break;
                case 3:
                    System.out.print("Digite o número da NF-e a ser excluída: ");
                    int numeroExcluir = lerOpcaoNumerica("");
                    boolean resultado = nfController.removerPorNumero(numeroExcluir);
                    if (resultado) {
                        System.out.println("Nota fiscal " + numeroExcluir + " removida com sucesso!");
                    } else {
                        System.out.println("Nota fiscal não encontrada ou não pode ser removida.");
                    }
                    break;
                case 4:
                    subMenuConsultas();
                    break;
                case 5:
                    System.out.print("Digite o número inicial: ");
                    int inicio = lerOpcaoNumerica("");
                    System.out.print("Digite o número final: ");
                    int fim = lerOpcaoNumerica("");

                    List<NotaFiscalModel> lista = nfController.listarPorIntervalo(inicio, fim);
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma nota fiscal encontrada nesse intervalo.");
                    } else {
                        System.out.println("\n=== Notas Fiscais no intervalo " + inicio + " a " + fim + " ===");
                        exibirResumosNotasFiscais(lista);
                        
                        // Opção para visualizar detalhes de uma nota específica
                        System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
                        int numeroDetalhe = lerOpcaoNumerica("");
                        if (numeroDetalhe > 0) {
                            nfController.consultarPorNumero(numeroDetalhe);
                        }
                    }
                    break;

                case 6:
                    System.out.println("\n=== Listagem de todas as Notas Fiscais ===\n");
                    List<NotaFiscalModel> notas = dao.listarTodas();
                    if (notas.isEmpty()) {
                        System.out.println("Nenhuma NF-e cadastrada.");
                    } else {
                        exibirResumosNotasFiscais(notas);
                        
                        // Opção para visualizar detalhes de uma nota específica
                        System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
                        int numeroDetalhe = lerOpcaoNumerica("");
                        if (numeroDetalhe > 0) {
                            nfController.consultarPorNumero(numeroDetalhe);
                        }
                    }
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Método para exibir o resumo de uma lista de notas fiscais
    private void exibirResumosNotasFiscais(List<NotaFiscalModel> notas) {
        for (NotaFiscalModel nota : notas) {
            System.out.println("------------------------------");
            System.out.println("Nota Fiscal: " + nota.getNumero());
            System.out.println("Série: " + nota.getSerie());
            System.out.println("Data de Emissão: " + nota.getDataEmissao());
            System.out.println("Emitente: " + nota.getEmitente().getRazaoSocial());
            System.out.println("Destinatário: " + nota.getDestinatario().getRazaoSocial());
            System.out.println("Valor Total: R$ " + nota.getValorTotalNf());
            System.out.println("Status: " + nota.getStatus());
            System.out.println("------------------------------");
        }
    }

    private void subMenuAlteracoes() {
        boolean submenuContinuar = true;

        while (submenuContinuar) {
            System.out.println("\n");
            System.out.println("""
            | ==================================================|
            |           __Submenu Alterações__                  |
            |   A. Alterar Destinatário/Remetente               |
            |   B. Alterar Fatura                               |
            |   C. Alterar Cálculo do Imposto                   |
            |   D. Alterar Transportadora                       |
            |   E. Voltar ao menu principal                     |
            |===================================================|
                """);

            String opcaoSubmenu = lerOpcaoLetra("Escolha uma opção: ");

            switch (opcaoSubmenu) {
                case "A":
                    System.out.print("Digite o número da NF-e para alterar destinatário e remetente: ");
                    int numAlt1 = lerOpcaoNumerica("");
                    nfController.alterarDestinatarioRemetente(numAlt1);
                    break;
                case "B":
                    System.out.print("Digite o número da NF-e para alterar fatura: ");
                    int numAlt2 = lerOpcaoNumerica("");
                    nfController.alterarFatura(numAlt2);
                    break;
                case "C":
                    System.out.print("Digite o número da NF-e para alterar cálculo do imposto: ");
                    int numAlt3 = lerOpcaoNumerica("");
                    nfController.alterarCalculoImposto(numAlt3);
                    break;
                case "D":
                    System.out.print("Digite o número da NF-e para alterar transportadora: ");
                    int numAlt4 = lerOpcaoNumerica("");
                    nfController.alterarTransportadora(numAlt4);
                    break;
                case "E":
                    submenuContinuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void subMenuConsultas() {
        boolean submenuContinuar = true;

        while (submenuContinuar) {
            System.out.println("\n");
            System.out.println("""
            | ==================================================|
            |           __Submenu Consultas__                   |
            |   A. Consulta pelo número da NF-e                 |
            |   B. Consulta pela razão social do cliente        |
            |   C. Consulta pelo CNPJ/CPF do cliente            |
            |   D. Consulta pelo valor total da NF-e            |
            |   E. Voltar ao menu principal                     |
            |===================================================|
                """);

            String opcaoSubmenu = lerOpcaoLetra("Escolha uma opção: ");

            switch (opcaoSubmenu) {
                case "A":
                    System.out.print("Digite o número da NF-e: ");
                    int numeroConsulta = lerOpcaoNumerica("");
                    nfController.consultarPorNumero(numeroConsulta);
                    break;
                case "B":
                    System.out.print("Digite a razão social do cliente: ");
                    String razao = scanner.nextLine();
                    nfController.consultarPorRazaoSocial(razao);
                    break;
                case "C":
                    System.out.print("Digite o CNPJ/CPF: ");
                    String cpfCnpj = scanner.nextLine().trim();

                    List<NotaFiscalModel> notasCpfCnpj = dao.buscarPorCpfCnpj(cpfCnpj);

                    if (notasCpfCnpj.isEmpty()) {
                        System.out.println("Nenhuma NF-e encontrada para o CNPJ/CPF informado.");
                    } else {
                        System.out.println("\n=== Notas Fiscais encontradas para CPF/CNPJ: " + cpfCnpj + " ===");
                        exibirResumosNotasFiscais(notasCpfCnpj);
                        
                        // Opção para visualizar detalhes de uma nota específica
                        System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
                        int numeroDetalhe = lerOpcaoNumerica("");
                        if (numeroDetalhe > 0) {
                            nfController.consultarPorNumero(numeroDetalhe);
                        }
                    }
                    break;
                case "D":
                    System.out.print("Digite o valor total da NF-e: ");
                    double valor = Double.parseDouble(scanner.nextLine());
                    nfController.consultarPorValorTotal(valor);
                    break;
                case "E":
                    submenuContinuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Método para validar e ler número inteiro
    private int lerOpcaoNumerica(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine().trim();

            // Se estiver vazio, apenas continua pedindo
            if (input.isEmpty()) {
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            }
        }
    }

    // Método para validar e ler letra única (A-E)
    private String lerOpcaoLetra(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().toUpperCase();

            // Se estiver vazio, apenas continua pedindo
            if (entrada.isEmpty()) {
                continue;
            }

            // Verifica se é uma única letra entre A-E
            if (entrada.length() == 1 && entrada.matches("[A-E]")) {
                return entrada;
            }

            System.out.println("Por favor, digite apenas uma letra entre A e E.");
        }
    }

    public static void main(String[] args) {
        NotaFiscalDAO dao = NotaFiscalDAO.getInstancia();
        NotaFiscalController nfController = new NotaFiscalController(
                dao,
                new EmitenteController(new EmitenteDAO()),
                new RemetenteController(new RemetenteDAO()),
                new DestinatarioController(new DestinatarioDAO()),
                new TransportadoraController(new TransportadoraDAO()),
                new FaturaController(new FaturaDAO()),
                new CalculoImpostoController(new CalculoImpostoDAO())
        );

        Menu m = new Menu(nfController, dao);
        m.exibir();
    }
}