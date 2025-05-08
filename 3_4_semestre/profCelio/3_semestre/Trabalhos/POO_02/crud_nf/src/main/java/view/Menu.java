package view;

import controller.*;
import dao.*;
import fake.NotaFiscalFaker;
import util.Validacoes;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner;
    private Validacoes valid;
    private NotaFiscalController nfController;
    private NotaFiscalDAO dao;

    private boolean nfeFakerGerados = false;
    private boolean nfeGerados = false;


    public Menu(NotaFiscalController nfController, NotaFiscalDAO dao) {
        this.dao = dao;
        gerarDadosFakerAutomaticamente();
        gerarDadosAutomaticamente();
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

    
    public void gerarDadosAutomaticamente() {
        int qtdPadrao = 5;

        if (!nfeGerados) {
            NotaFiscalFaker faker = new NotaFiscalFaker();
            for (int i = 0; i < qtdPadrao; i++) {
                dao.adicionarFaker(faker.gerarNotaFiscal());
            }
            nfeGerados = true;
            System.out.println(qtdPadrao + " clientes gerados automaticamente.");
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

            int opcao = valid.lerInteiroPositivo("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("\nCRIANDO NF-e à mão...");
                    nfController.IncluirNF();
                    break;
                case 2:
                    subMenuAlteracoes();
                    break;
                case 3:
                    nfController.excluirNFE();
                    break;
                case 4:
                    subMenuConsultas();
                    break;
                case 5:
                    nfController.ListagemPorIntervalo();
                    break;
                case 6:
                    nfController.exibirNFfake();
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

            String opcaoSubmenu = Validacoes.lerOpcaoLetraE("Escolha uma opção: ");

            switch (opcaoSubmenu) {
                case "A":
                    System.out.print("Digite o número da NF-e para alterar destinatário e remetente: ");
                    int numAlt1 = valid.lerInteiroPositivo("");
                    nfController.alterarDestinatarioRemetente(numAlt1);
                    break;
                case "B":
                    System.out.print("Digite o número da NF-e para alterar fatura: ");
                    int numAlt2 = valid.lerInteiroPositivo("");
                    nfController.alterarFatura(numAlt2);
                    break;
                case "C":
                    System.out.print("Digite o número da NF-e para alterar cálculo do imposto: ");
                    int numAlt3 = valid.lerInteiroPositivo("");
                    nfController.alterarCalculoImposto(numAlt3);
                    break;
                case "D":
                    System.out.print("Digite o número da NF-e para alterar transportadora: ");
                    int numAlt4 = valid.lerInteiroPositivo("");
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

            String opcaoSubmenu = Validacoes.lerOpcaoLetraE("Escolha uma opção: ");

            switch (opcaoSubmenu) {
                case "A":
                    System.out.print("Digite o número da NF-e: ");
                    int numeroConsulta = valid.lerInteiroPositivo("");
                    nfController.consultarPorNumero(numeroConsulta);
                    break;
                case "B":
                    System.out.print("Digite a razão social do cliente: ");
                    String razao = scanner.nextLine();
                    nfController.consultarPorRazaoSocial(razao);
                    break;
                case "C":
                    nfController.consultarPorCpfCnpj();
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
