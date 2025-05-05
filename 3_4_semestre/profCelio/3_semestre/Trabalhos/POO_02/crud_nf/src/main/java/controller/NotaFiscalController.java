package controller;

import dao.NotaFiscalDAO;
import model.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NotaFiscalController {

    private final NotaFiscalDAO dao;
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

    public void IncluirNF() {
        Scanner scanner = new Scanner(System.in);
        NotaFiscalModel nf = new NotaFiscalModel();

        // Dados básicos da Nota Fiscal
        System.out.print("Chave de Acesso: ");
        nf.setChaveAcesso(scanner.nextLine());

        System.out.print("Modelo: ");
        nf.setModelo(scanner.nextInt());

        System.out.print("Série: ");
        nf.setSerie(scanner.nextInt());

        System.out.print("Número: ");
        nf.setNumero(scanner.nextInt());
        scanner.nextLine(); // limpar buffer

        System.out.print("Data Autorização (AAAA-MM-DD): ");
        nf.setDataAutorizacao(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print("Data Emissão (AAAA-MM-DD): ");
        nf.setDataEmissao(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print("Data Saída/Entrada (AAAA-MM-DD): ");
        nf.setDataSaidaEntrada(java.sql.Date.valueOf(scanner.nextLine()));

        System.out.print("Hora Saída/Entrada (HH:MM): ");
        nf.setHoraSaidaEntrada(scanner.nextLine());

        System.out.print("Natureza da Operação: ");
        nf.setNaturezaDaOperacao(scanner.nextLine());

        System.out.print("Protocolo de Autorização: ");
        nf.setProtocoloAutorizacao(scanner.nextLine());

        System.out.print("Valor Total da NF: ");
        nf.setValorTotalNf(scanner.nextDouble());

        System.out.print("Valor dos Produtos: ");
        nf.setValorProdutos(scanner.nextDouble());

        System.out.print("Valor dos Serviços: ");
        nf.setValorServicos(scanner.nextDouble());

        System.out.print("Valor do Desconto: ");
        nf.setValorDesconto(scanner.nextDouble());

        System.out.print("Valor de Outras Despesas: ");
        nf.setValorOutrasDespesas(scanner.nextDouble());

        System.out.print("Valor do Frete: ");
        nf.setValorFrete(scanner.nextDouble());
        scanner.nextLine(); // limpar buffer

        // Status e Tipo
        System.out.print("Status da NF-e (AUTORIZADA, CANCELADA, DENEGADA): ");
        nf.setStatus(StatusModel.StatusNFE.valueOf(scanner.nextLine().toUpperCase()));

        System.out.print("Tipo da NF-e (ENTRADA, SAIDA): ");
        nf.setTipo(TipoNfModel.TipoNFE.valueOf(scanner.nextLine().toUpperCase()));

        // Criação dos objetos associados (com seus próprios controllers)
        nf.setEmitente(emitenteController.cadastrarEmitente());
        nf.setRemetente(remetenteController.cadastrarRemetente());
        nf.setDestinatario(destinatarioController.cadastrarDestinatario());
        nf.setTransportadora(transportadoraController.cadastrarTransportadora());
        nf.setFatura(faturaController.criarFatura());
        nf.setCalculoImposto(impostoController.incerirImpostos());

        // Persistir NF-e
        dao.adicionar(nf);

        System.out.println("\nNota Fiscal incluída com sucesso!\n");
    }

    
    public boolean removerPorNumero(int numero) {
        return dao.removerPorNumero(numero);
    }

    public Optional<NotaFiscalModel> buscarPorNumero(int numero) {
        return dao.buscarPorNumero(numero);
    }

    public List<NotaFiscalModel> buscarPorRazaoSocial(String razao) {
        return dao.buscarPorRazaoSocial(razao);
    }

    public List<NotaFiscalModel> buscarPorCpfCnpj(String cpfCnpj) {
        return dao.buscarPorCpfCnpj(cpfCnpj);
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

    public List<NotaFiscalModel> listarTodas() {
        return dao.listarTodas();
    }

    public void atualizarNotaFiscal(NotaFiscalModel nf) {
        // Implementação para atualizar a NF no DAO
    }
    
    public void alterarDestinatarioRemetente(int numero) {
    Scanner scanner = new Scanner(System.in);
    Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);
    
    if (notaOpt.isPresent()) {
        NotaFiscalModel nota = notaOpt.get();
        System.out.println("Nota Fiscal encontrada. Escolha o que deseja alterar:");
        System.out.println("1. Alterar Destinatário");
        System.out.println("2. Alterar Remetente");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        
        switch (opcao) {
            case 1:
                System.out.println("\n=== Alterando Destinatário ===");
                nota.setDestinatario(destinatarioController.cadastrarDestinatario());
                atualizarNotaFiscal(nota);
                break;
            case 2:
                System.out.println("\n=== Alterando Remetente ===");
                nota.setRemetente(remetenteController.cadastrarRemetente());
                atualizarNotaFiscal(nota);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    } else {
        System.out.println("Nota Fiscal não encontrada com o número: " + numero);
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
            System.out.println("Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void alterarCalculoImposto(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println("Nota Fiscal encontrada. Alterando informações de impostos...");
            nota.setCalculoImposto(impostoController.incerirImpostos());
            atualizarNotaFiscal(nota);
        } else {
            System.out.println("Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void alterarTransportadora(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println("Nota Fiscal encontrada. Alterando informações da transportadora...");
            nota.setTransportadora(transportadoraController.cadastrarTransportadora());
            atualizarNotaFiscal(nota);
        } else {
            System.out.println("Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void consultarPorNumero(int numero) {
        Optional<NotaFiscalModel> notaOpt = dao.buscarPorNumero(numero);

        if (notaOpt.isPresent()) {
            NotaFiscalModel nota = notaOpt.get();
            System.out.println(nota);
        } else {
            System.out.println("Nota Fiscal não encontrada com o número: " + numero);
        }
    }

    public void consultarPorRazaoSocial(String razaoSocial) {
        List<NotaFiscalModel> notas = dao.buscarPorRazaoSocial(razaoSocial);

        if (!notas.isEmpty()) {
            System.out.println("\n=== Notas Fiscais encontradas para Razão Social: " + razaoSocial + " ===");
            for (NotaFiscalModel nota : notas) {
                exibirResumoNotaFiscal(nota);
                System.out.println("------------------------------");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Deseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
            int numeroNota = scanner.nextInt();

            if (numeroNota > 0) {
                consultarPorNumero(numeroNota);
            }
        } else {
            System.out.println("Nenhuma Nota Fiscal encontrada para a Razão Social: " + razaoSocial);
        }
    }

    public void consultarPorValorTotal(double valor) {
        List<NotaFiscalModel> notas = dao.buscarPorValorTotal(valor);

        if (!notas.isEmpty()) {
            System.out.println("\n=== Notas Fiscais encontradas com Valor Total: R$ " + valor + " ===");
            for (NotaFiscalModel nota : notas) {
                exibirResumoNotaFiscal(nota);
                System.out.println("------------------------------");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Deseja ver detalhes de alguma nota? (Digite o número da nota ou 0 para sair): ");
            int numeroNota = scanner.nextInt();

            if (numeroNota > 0) {
                consultarPorNumero(numeroNota);
            }
        } else {
            System.out.println("Nenhuma Nota Fiscal encontrada com o Valor Total: R$ " + valor);
        }
    }

    public void exibirResumoNotaFiscal(NotaFiscalModel nota) {
        System.out.println("Nota Fiscal: " + nota.getNumero());
        System.out.println("Série: " + nota.getSerie());
        System.out.println("Data de Emissão: " + nota.getDataEmissao());
        System.out.println("Emitente: " + nota.getEmitente().getRazaoSocial());
        System.out.println("Destinatário: " + nota.getDestinatario().getRazaoSocial());
        System.out.println("Valor Total: R$ " + nota.getValorTotalNf());
        System.out.println("Status: " + nota.getStatus());
    }
    
    // dentro de NotaFiscalController
    public void adicionarNotaFake(NotaFiscalModel nota) {
        dao.adicionarFaker(nota);
    }
}
