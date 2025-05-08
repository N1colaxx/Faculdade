package controller;

import dao.NotaFiscalDAO;
import util.Validacoes;
import model.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NotaFiscalController {

    private final NotaFiscalDAO dao;
    private Validacoes valid;
    private final EmitenteController emitenteController;
    private final RemetenteController remetenteController;
    private final DestinatarioController destinatarioController;
    private final TransportadoraController transportadoraController;
    private final FaturaController faturaController;
    private final CalculoImpostoController impostoController;

    public NotaFiscalController(
            NotaFiscalDAO dao,
            EmitenteController emitenteController,
            RemetenteController remetenteController,
            DestinatarioController destinatarioController,
            TransportadoraController transportadoraController,
            FaturaController faturaController,
            CalculoImpostoController impostoController
    ) {
        this.dao = dao;
        this.emitenteController = emitenteController;
        this.remetenteController = remetenteController;
        this.destinatarioController = destinatarioController;
        this.transportadoraController = transportadoraController;
        this.faturaController = faturaController;
        this.impostoController = impostoController;
    }

//    Conecção com o DAO
    public boolean removerPorNumero(int numero) {
        return dao.removerPorNumero(numero);
    }

    public Optional<NotaFiscalModel> buscarPorNumero(int numero) {
        return dao.buscarPorNumero(numero);
    }

    public List<NotaFiscalModel> buscarPorRazaoSocial(String razao) {
        return dao.buscarPorRazaoSocial(razao);
    }

    public List<NotaFiscalModel> buscarPorCpf(String Cpf) {
        return dao.buscarPorCpf(Cpf);
    }
    
    public List<NotaFiscalModel> buscarPorCnfj(String Cnpj) {
        return dao.buscarPorCnpj(Cnpj);
    }

    public List<NotaFiscalModel> buscarPorValorTotal(double valorTotal) {
        return dao.buscarPorValorTotal(valorTotal);
    }

    public List<NotaFiscalModel> listarPorIntervalo(int inicio, int fim) {
        if (inicio < 0 || fim < inicio) {
            throw new IllegalArgumentException("Intervalo inválido: verifique os valores de início e fim.");
        }
        return dao.listarPorIntervalo(inicio, fim);
    }

    public void atualizarNotaFiscal(NotaFiscalModel nf) {
        // Implementação para atualizar a NF no DAO
    }

    public List<NotaFiscalModel> listarTodas() {
        return dao.listarTodas();
    }

    public void adicionarNfFake(NotaFiscalModel nota) {
        dao.adicionarNfFake(nota);
    }

    public void adicionarNF(NotaFiscalModel nota) {
        dao.adicionarNF(nota);
    }

//    Metodos do MENU
    public void IncluirNF() {
        Scanner scanner = new Scanner(System.in);
        NotaFiscalModel nf = new NotaFiscalModel();

        // Dados básicos da Nota Fiscal
        System.out.print(" Chave de Acesso: ");
        nf.setChaveAcesso(scanner.nextLine());

        System.out.print(" Modelo: ");
        nf.setModelo(scanner.nextInt());

        System.out.print(" Série: ");
        nf.setSerie(scanner.nextInt());

        System.out.print(" Número: ");
        nf.setNumero(scanner.nextInt());
        scanner.nextLine(); // limpar buffer

        System.out.print(" Data Autorização (AAAA-MM-DD): ");
        nf.setDataAutorizacao(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print(" Data Emissão (AAAA-MM-DD): ");
        nf.setDataEmissao(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print(" Data Saída/Entrada (AAAA-MM-DD): ");
        nf.setDataSaidaEntrada(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print(" Hora Saída/Entrada (HH:MM): ");
        nf.setHoraSaidaEntrada(scanner.nextLine());

        System.out.print(" Natureza da Operação: ");
        nf.setNaturezaDaOperacao(scanner.nextLine());

        System.out.print(" Protocolo de Autorização: ");
        nf.setProtocoloAutorizacao(scanner.nextLine());

        System.out.print(" Valor Total da NF: ");
        nf.setValorTotalNf(scanner.nextDouble());

        System.out.print(" Valor dos Produtos: ");
        nf.setValorProdutos(scanner.nextDouble());

        System.out.print(" Valor dos Serviços: ");
        nf.setValorServicos(scanner.nextDouble());

        System.out.print(" Valor do Desconto: ");
        nf.setValorDesconto(scanner.nextDouble());

        System.out.print(" Valor de Outras Despesas: ");
        nf.setValorOutrasDespesas(scanner.nextDouble());

        System.out.print(" Valor do Frete: ");
        nf.setValorFrete(scanner.nextDouble());
        scanner.nextLine(); // limpar buffer

        // Status e Tipo
        System.out.print(" Status da NF-e (AUTORIZADA, CANCELADA, DENEGADA): ");
        nf.setStatus(StatusModel.StatusNFE.valueOf(scanner.nextLine().toUpperCase()));

        System.out.print(" Tipo da NF-e (ENTRADA, SAIDA): ");
        nf.setTipo(TipoNfModel.TipoNFE.valueOf(scanner.nextLine().toUpperCase()));

        // Criação dos objetos associados (com seus próprios controllers)
        nf.setEmitente(emitenteController.cadastrarEmitente());
        nf.setRemetente(remetenteController.cadastrarRemetente());
        nf.setDestinatario(destinatarioController.cadastrarDestinatario());
        nf.setTransportadora(transportadoraController.cadastrarTransportadora());
        nf.setFatura(faturaController.criarFatura());
        nf.setCalculoImposto(impostoController.incerirImpostos());

        // Persistir NF-e
        dao.adicionarNF(nf);

        System.out.println("\n Nota Fiscal incluída com sucesso!\n");
    }

    public void excluirNFE() {
        System.out.print(" Digite o número da NF-e a ser excluída: ");
        int numeroExcluir = valid.lerInteiroPositivo("");
        boolean resultado = removerPorNumero(numeroExcluir);
        if (resultado) {
            System.out.println("\n Nota fiscal " + numeroExcluir + " removida com sucesso!");
        } else {
            System.out.println("\n Nota fiscal não encontrada ou não pode ser removida.");
        }
    }

    public void ListagemPorIntervalo() {
        System.out.print("Digite o número inicial: ");
        int inicio = valid.lerInteiroPositivo("");
        System.out.print("Digite o número final: ");
        int fim = valid.lerInteiroPositivo("");

        List<NotaFiscalModel> lista = listarPorIntervalo(inicio, fim);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma nota fiscal encontrada nesse intervalo.");
        } else {
            System.out.println("\n=== Notas Fiscais no intervalo " + inicio + " a " + fim + " ===");
            exibirResumosNF(lista);

            // Opção para visualizar detalhes de uma nota específica
            System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
            int numeroDetalhe = valid.lerInteiroPositivo("");
            if (numeroDetalhe > 0) {
                consultarPorNumero(numeroDetalhe);
            }
        }
    }

    public void listarTodasNF() {
        System.out.println("\n=== Listagem de todas as Notas Fiscais ===\n");
        List<NotaFiscalModel> notas = dao.listarTodas();
        if (notas.isEmpty()) {
            System.out.println("Nenhuma NF-e cadastrada.");
        } else {
            exibirResumosNF(notas);

            // Opção para visualizar detalhes de uma nota específica
            System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
            int numeroDetalhe = valid.lerNumeroInteiro("");
            if (numeroDetalhe > 0) {
                consultarPorNumero(numeroDetalhe);
            }
        }
    }

//  Sub-menu Alterações
    public void alterarDestinatarioRemetente(int numero) {
        Scanner scanner = new Scanner(System.in);
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println(" Nota Fiscal encontrada. Escolha o que deseja alterar:");
            System.out.println(" 1. Alterar Destinatário");
            System.out.println(" 2. Alterar Remetente");
            System.out.print(" Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("\n === Alterando Destinatário ===");
                    nota.setDestinatario(destinatarioController.cadastrarDestinatario());
                    atualizarNotaFiscal(nota);
                    break;
                case 2:
                    System.out.println("\n === Alterando Remetente ===");
                    nota.setRemetente(remetenteController.cadastrarRemetente());
                    atualizarNotaFiscal(nota);
                    break;
                default:
                    System.out.println("\n Opção inválida.");
                    break;
            }
        } else {
            System.out.println("\n Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void alterarFatura(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println("Nota Fiscal encontrada. Alterando informações da fatura...");
            nota.setFatura(faturaController.criarFatura());
            atualizarNotaFiscal(nota);
        } else {
            System.out.println("\n Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void alterarCalculoImposto(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println("\n Nota Fiscal encontrada. Alterando informações de impostos...");
            nota.setCalculoImposto(impostoController.incerirImpostos());
            atualizarNotaFiscal(nota);
        } else {
            System.out.println("\n Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void alterarTransportadora(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println("\n Nota Fiscal encontrada. Alterando informações da transportadora...");
            nota.setTransportadora(transportadoraController.cadastrarTransportadora());
            atualizarNotaFiscal(nota);
        } else {
            System.out.println("\n Nota Fiscal não encontrada com o número: " + numero);
        }
    }

//    Sub-menu Consultas 
    public void consultarPorNumero(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();

            System.out.println(nota.mostraComfake());

        } else {
            System.out.println("\n Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void consultarPorRazaoSocial(String razaoSocial) {
        List<NotaFiscalModel> notas = dao.buscarPorRazaoSocial(razaoSocial);

        if (notas.isEmpty()) {
            System.out.println("Nenhuma Nota Fiscal encontrada para a Razão Social: " + razaoSocial);
        } else {
            for (NotaFiscalModel nota : notas) {
                System.out.println(nota.mostraComfake());  // ou exibirResumoNF(nota)
                System.out.println("------------------------------");
            }
        }
    }

    public void consultarPorCpfCnpj(String cpfCnpj) {
        List<NotaFiscalModel> notas;

        // Valida se é CPF (11 dígitos) ou CNPJ (14 dígitos)
        if (cpfCnpj.length() == 11) {
            notas = dao.buscarPorCpf(cpfCnpj);
        } else if (cpfCnpj.length() == 14) {
            notas = dao.buscarPorCnpj(cpfCnpj);
        } else {
            System.out.println("\n CPF ou CNPJ inválido. Certifique-se de digitar 11 ou 14 dígitos.");
            return;
        }

        // Verifica se encontrou notas
        if (notas.isEmpty()) {
            System.out.println("\n  Nenhuma NF-e encontrada para o CPF/CNPJ informado.");
            return;
        }

        System.out.println("\n === Notas Fiscais encontradas para CPF/CNPJ: " + cpfCnpj + " ===");

        // Exibe de forma diferente se for FAKE
        for (NotaFiscalModel nota : notas) {
            if (isNotaFake(nota)) {
                System.out.println("*** NOTA FISCAL FAKE ***");
                exibirResumoNF(nota);
            } else {
                exibirResumoNF(nota);  // ou exibirResumoNF(nota)
            }
            System.out.println("------------------------------");
        }

        // Permite ver detalhes
        System.out.print("\nDeseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
        int numeroDetalhe = valid.lerInteiroPositivo("");
        if (numeroDetalhe > 0) {
            consultarPorNumero(numeroDetalhe);
        }
    }

    public void consultarPorValorTotal(double valor) {
        List<NotaFiscalModel> notas = dao.buscarPorValorTotal(valor);

        if (!notas.isEmpty()) {
            System.out.println("\n=== Notas Fiscais encontradas com Valor Total: R$ " + valor + " ===");
            for (NotaFiscalModel nota : notas) {
                exibirResumoNF(nota);
                System.out.println("------------------------------");
            }
        }
    }

    private boolean isNotaFake(NotaFiscalModel nota) {
        return nota.getMetodoGerado() == TipoNfModel.MetodoGerado.FAKE;
    }

//    Metodos de EXIBIÇÂO
    public void exibirNFfake() {
        List<NotaFiscalModel> notas = dao.listarTodas();

        if (notas.isEmpty()) {
            System.out.println("Nenhuma Nota Fiscal Fake cadastrada.");
            return;
        }

        for (NotaFiscalModel nota : notas) {
            if (nota.getMetodoGerado() == TipoNfModel.MetodoGerado.FAKE) {
                System.out.println(nota.mostraComfake()); // ou exibirResumoNF(nota)
            } else {
                System.out.println(nota); // Exibe normalmente
            }
        }
    }


    public void exibirResumoNF(NotaFiscalModel nota) {
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

    // Método para exibir o resumo da de todas as notas fiscais em registradas
    private void exibirResumosNF(List<NotaFiscalModel> notas) {
        for (NotaFiscalModel nota : notas) {
            System.out.println("------------------------------");
            System.out.println("Metodo Emição: " + nota.getMetodoGerado());
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

}
