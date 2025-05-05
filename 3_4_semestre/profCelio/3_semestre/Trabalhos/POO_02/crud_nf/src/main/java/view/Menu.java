package view;

import fake.NotaFiscalFaker;
import controller.NotaFiscalController;
import dao.NotaFiscalDAO;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.NotaFiscalModel;
import model.CalculoImpostoModel;
import model.EmitenteModel;
import model.EnderecoModel;
import model.StatusModel;
import model.TipoNfModel;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static NotaFiscalDAO dao = new NotaFiscalDAO(); // Criar a instância do DAO

    public Menu() {
        gerarDadosFakerAutomaticamente();
    }

    private boolean nfeFakerGerados = false;

    public void gerarDadosFakerAutomaticamente() {
        int qtdPadrao = 1;

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
            System.out.println("\nMenu:");
            System.out.println("1. Incluir NF-e");
            System.out.println("2. Alterar NF-e pelo número");
            System.out.println("3. Excluir NF-e pelo número");
            System.out.println("4. Consultar NF-e");
            System.out.println("5. Listagem de NF-e por intervalo de número");
            System.out.println("6. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    List<NotaFiscalModel> notas = dao.listarTodas();
                    if (notas.isEmpty()) {
                        System.out.println("Nenhuma NF-e cadastrada.");
                    } else {
                        for (NotaFiscalModel nota : notas) {
                            System.out.println(nota);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Digite o CNPJ: ");
                    String cnpj = scanner.nextLine();

                    List<NotaFiscalModel> notas2 = dao.buscarPorCpfCnpj(cnpj);

                    if (notas2.isEmpty()) {
                        System.out.println("Nenhuma NF-e encontrada para o CNPJ informado.");
                    } else {
                        for (NotaFiscalModel nota : notas2) {
                            System.out.println(" NOTA FIZCAL POR CNPJ 000000000000000000000000000000");
                            System.out.println(nota);
                        }
                    }
                    break;
                case 3:
//                    excluirNFePorNumero();
                    break;
                case 4:
//                    consultarNFe();
                    break;
                case 5:
//                    listarNFePorIntervalo();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        Menu m = new Menu();
        m.exibir();
    }
}
