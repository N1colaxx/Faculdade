package view;

import controller.NotaFiscalController;
import dao.NotaFiscalDAO;
import java.util.Date;
import model.NotaFiscalModel;
import java.util.Scanner;
import model.CalculoImpostoModel;
import model.EmitenteModel;
import model.EnderecoModel;
import model.StatusModel;
import model.TipoNfModel;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static NotaFiscalDAO dao = new NotaFiscalDAO(); // Criar a instância do DAO
    private static NotaFiscalController controller = new NotaFiscalController(dao); // Passar o DAO para o Controller

    public Menu() {
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
                    incluirNFe();
                    break;
                case 2:
                    alterarNFePorNumero();
                    break;
                case 3:
                    excluirNFePorNumero();
                    break;
                case 4:
                    consultarNFe();
                    break;
                case 5:
                    listarNFePorIntervalo();
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

    
private void incluirNFe() {
    System.out.println("\n--- Inclusão Manual de NF-e ---");

    try {
        // Coletar dados básicos da NF
        System.out.print("Número da NF-e: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Série: ");
        int serie = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Modelo: ");
        int modelo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Chave de Acesso: ");
        String chaveAcesso = scanner.nextLine();

        System.out.print("Natureza da Operação: ");
        String naturezaOperacao = scanner.nextLine();

        System.out.print("Protocolo de Autorização: ");
        String protocoloAutorizacao = scanner.nextLine();

        System.out.print("Data de Emissão (dd/MM/yyyy): ");
        Date dataEmissao = parseDate(scanner.nextLine());

        System.out.print("Data de Saída/Entrada (dd/MM/yyyy): ");
        Date dataSaidaEntrada = parseDate(scanner.nextLine());

        System.out.print("Hora de Saída/Entrada (HH:mm): ");
        String horaSaidaEntrada = scanner.nextLine();

        // Valores financeiros
        System.out.print("Valor Total da NF: ");
        double valorTotal = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valor dos Produtos: ");
        double valorProdutos = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valor dos Serviços: ");
        double valorServicos = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valor do Desconto: ");
        double valorDesconto = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valor do Frete: ");
        double valorFrete = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Valor de Outras Despesas: ");
        double valorOutrasDespesas = scanner.nextDouble();
        scanner.nextLine();

        // Status e Tipo
        System.out.print("Status (1-AUTORIZADA, 2-CANCELADA, 3-DENEGADA): ");
        int statusOp = scanner.nextInt();
        scanner.nextLine();
        StatusModel.StatusNFE status = StatusModel.StatusNFE.values()[statusOp-1];

        System.out.print("Tipo (1-ENTRADA, 2-SAIDA): ");
        int tipoOp = scanner.nextInt();
        scanner.nextLine();
        TipoNfModel.TipoNFE tipo = TipoNfModel.TipoNFE.values()[tipoOp-1];

        // Dados do Emitente
        System.out.println("\n--- Dados do Emitente ---");
        System.out.print("CNPJ do Emitente: ");
        String cnpjEmitente = scanner.nextLine();
        
        EmitenteModel emitente = emitenteController.buscarPorCnpj(cnpjEmitente)
            .orElseGet(() -> {
                System.out.println("Emitente não cadastrado. Vamos cadastrar:");
                System.out.print("Razão Social: ");
                String razaoSocial = scanner.nextLine();
                
                System.out.println("\n--- Endereço do Emitente ---");
                EnderecoModel endereco = coletarEndereco();
                
                emitenteController.cadastrarEmitente(razaoSocial, cnpjEmitente, endereco);
                return new EmitenteModel(razaoSocial, cnpjEmitente, endereco);
            });

        // Dados do Destinatário
        System.out.println("\n--- Dados do Destinatário ---");
        System.out.print("CPF/CNPJ do Destinatário: ");
        String cpfCnpjDestinatario = scanner.nextLine();
        
        Destinatario destinatario = destinatarioController.buscarPorCpfCnpj(cpfCnpjDestinatario)
            .orElseGet(() -> {
                System.out.println("Destinatário não cadastrado. Vamos cadastrar:");
                System.out.print("Razão Social: ");
                String razaoSocial = scanner.nextLine();
                
                System.out.println("\n--- Endereço do Destinatário ---");
                EnderecoModel endereco = coletarEndereco();
                
                destinatarioController.cadastrarDestinatario(razaoSocial, cpfCnpjDestinatario, endereco);
                return new Destinatario(razaoSocial, cpfCnpjDestinatario, endereco);
            });

        // Transportadora (opcional)
        TransportadoraModel transportadora = null;
        System.out.print("\nDeseja cadastrar Transportadora? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.print("CNPJ da Transportadora: ");
            String cnpjTransportadora = scanner.nextLine();
            
            transportadora = transportadoraController.buscarPorCnpj(cnpjTransportadora)
                .orElseGet(() -> {
                    System.out.println("Transportadora não cadastrada. Vamos cadastrar:");
                    System.out.print("Nome da Transportadora: ");
                    String nome = scanner.nextLine();
                    
                    System.out.println("\n--- Endereço da Transportadora ---");
                    EnderecoModel endereco = coletarEndereco();
                    
                    System.out.println("\n--- Telefone da Transportadora ---");
                    System.out.print("DDD: ");
                    String ddd = scanner.nextLine();
                    System.out.print("Número: ");
                    String numTel = scanner.nextLine();
                    TelefoneModel telefone = new TelefoneModel(ddd, numTel);
                    
                    return transportadoraController.cadastrarTransportadora(nome, cnpjTransportadora, endereco, telefone);
                });
        }

        // Fatura
        System.out.println("\n--- Dados da Fatura ---");
        System.out.print("Número da Fatura: ");
        String numeroFatura = scanner.nextLine();
        FaturaModel fatura = faturaController.criarFatura(numeroFatura, valorTotal);

        // Cálculo de Impostos
        System.out.println("\n--- Cálculo de Impostos ---");
        System.out.print("Valor ICMS: ");
        double icms = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Valor IPI: ");
        double ipi = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Valor PIS: ");
        double pis = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Valor COFINS: ");
        double cofins = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Valor ISS: ");
        double iss = scanner.nextDouble();
        scanner.nextLine();
        
        CalculoImpostoModel calculoImposto = impostoController.calcularImpostos(icms, ipi, pis, cofins, iss);

        // Criar a nota fiscal usando o controller
        nfController.criarNFe(
            chaveAcesso, modelo, serie, numero,
            dataEmissao, dataSaidaEntrada, horaSaidaEntrada,
            naturezaOperacao, protocoloAutorizacao,
            valorTotal, valorProdutos, valorServicos,
            valorDesconto, valorOutrasDespesas, valorFrete,
            emitente.getCpfCnpj(), destinatario.getCpfCnpj(),
            (transportadora != null) ? transportadora.getCnpj() : null,
            numeroFatura,
            icms, ipi, pis, cofins, iss,
            status, tipo
        );

        System.out.println("\nNF-e cadastrada com sucesso!");
    } catch (Exception e) {
        System.out.println("\nErro ao cadastrar NF-e: " + e.getMessage());
    }
}

private EnderecoModel coletarEndereco() {
    System.out.print("Logradouro: ");
    String logradouro = scanner.nextLine();
    
    System.out.print("Número: ");
    String numero = scanner.nextLine();
    
    System.out.print("Bairro: ");
    String bairro = scanner.nextLine();
    
    System.out.print("Cidade: ");
    String cidade = scanner.nextLine();
    
    System.out.print("Estado: ");
    String estado = scanner.nextLine();
    
    System.out.print("CEP: ");
    String cep = scanner.nextLine();
    
    return new EnderecoModel(logradouro, numero, bairro, cidade, estado, cep);
}

//    // Método auxiliar para parse de data
//    private Date parseDate(String dateStr) {
//        try {
//            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
//        } catch (ParseException e) {
//            System.out.println("Formato de data inválido. Usando data atual.");
//            return new Date();
//        }
//    }
//    
    
    private void alterarNFePorNumero() {
        System.out.println("Alterar NF-e pelo número:");
        System.out.print("Digite o número da NF-e que deseja alterar: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        NotaFiscalModel notaFiscal = controller.buscarNotaFiscal(numero).orElse(null);
        if (notaFiscal != null) {
            // Exibir sub-menu de alterações
            System.out.println("Escolha o que deseja alterar:");
            System.out.println("a. Alterar Destinatário/Remetente");
            System.out.println("b. Alterar Fatura");
            System.out.println("c. Alterar Cálculo do Imposto");
            System.out.println("d. Alterar Transportadora");

            String opcaoSubMenu = scanner.nextLine();
            switch (opcaoSubMenu) {
                case "a":
                    alterarDestinatarioRemetente(notaFiscal);
                    break;
                case "b":
                    alterarFatura(notaFiscal);
                    break;
                case "c":
                    alterarCalculoImposto(notaFiscal);
                    break;
                case "d":
                    alterarTransportadora(notaFiscal);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("NF-e não encontrada.");
        }
    }

    
    private void excluirNFePorNumero() {
        System.out.print("Digite o número da NF-e para excluir: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        boolean excluida = controller.removerNotaFiscal(numero);
        if (excluida) {
            System.out.println("NF-e excluída com sucesso.");
        } else {
            System.out.println("NF-e não encontrada.");
        }
    }
    
    
    private void consultarNFe() {
        System.out.println("Consultar NF-e:");

        System.out.println("Escolha o tipo de consulta:");
        System.out.println("a. Consulta pelo número da NF-e");
        System.out.println("b. Consulta pela razão social do cliente");
        System.out.println("c. Consulta pelo CNPJ/CPF do cliente");
        System.out.println("d. Consulta pelo valor total da NF-e");

        String opcaoSubMenu = scanner.nextLine();
        switch (opcaoSubMenu) {
            case "a":
                consultarPorNumero();
                break;
            case "b":
                consultarPorRazaoSocial();
                break;
            case "c":
                consultarPorCnpjCpf();
                break;
            case "d":
                consultarPorValorTotal();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    
    private void listarNFePorIntervalo() {
        System.out.print("Digite o número inicial: ");
        int inicio = scanner.nextInt();
        System.out.print("Digite o número final: ");
        int fim = scanner.nextInt();
        List<NotaFiscalModel> notas = controller.listarPorIntervalo(inicio, fim);
        if (!notas.isEmpty()) {
            notas.forEach(n -> System.out.println(n));
        } else {
            System.out.println("Nenhuma NF-e encontrada nesse intervalo.");
        }
    }

    
   
//   SUB menu     2. Alterar NF-e pelo número
    private void alterarDestinatarioRemetente(NotaFiscalModel nf) {
        System.out.println("Alterar Destinatário/Remetente");
        // Solicitar novo Destinatário/Remetente
        System.out.print("Novo CNPJ: ");
        String cnpj = scanner.nextLine();
        nf.getDestinatario().setCnpj(cnpj);
        controller.atualizarNotaFiscal(nf);
        System.out.println("Destinatário/Remetente alterado com sucesso.");
    }

    private void alterarFatura(NotaFiscalModel nf) {
        System.out.println("Alterar Fatura");
        // Solicitar nova fatura
        System.out.print("Novo valor da fatura: ");
        double valorFatura = scanner.nextDouble();
        nf.setValorFatura(valorFatura);
        controller.atualizarNotaFiscal(nf);
        System.out.println("Fatura alterada com sucesso.");
    }

    private void alterarCalculoImposto(NotaFiscalModel nf) {
        System.out.println("Alterar Cálculo do Imposto");
        // Solicitar novo cálculo de imposto
        System.out.print("Novo valor do imposto: ");
        double imposto = scanner.nextDouble();
        nf.setValorImposto(imposto);
        controller.atualizarNotaFiscal(nf);
        System.out.println("Cálculo do Imposto alterado com sucesso.");
    }

    private void alterarTransportadora(NotaFiscalModel nf) {
        System.out.println("Alterar Transportadora");
        // Solicitar nova transportadora
        System.out.print("Nome da nova transportadora: ");
        String transportadora = scanner.nextLine();
        nf.setTransportadora(transportadora);
        controller.atualizarNotaFiscal(nf);
        System.out.println("Transportadora alterada com sucesso.");
    }

 
// Sub menu 4. Consultar NF-e  
    private void consultarPorNumero() {
        System.out.print("Digite o número da NF-e: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Optional<NotaFiscalModel> nf = controller.buscarNotaFiscal(numero);
        nf.ifPresentOrElse(
            nota -> System.out.println("NF-e encontrada: " + nota),
            () -> System.out.println("NF-e não encontrada.")
        );
    }

    private void consultarPorRazaoSocial() {
        System.out.print("Digite a razão social do cliente: ");
        String razaoSocial = scanner.nextLine();
        List<NotaFiscalModel> notas = controller.buscarPorRazaoSocial(razaoSocial);
        if (!notas.isEmpty()) {
            notas.forEach(n -> System.out.println(n));
        } else {
            System.out.println("Nenhuma NF-e encontrada para essa razão social.");
        }
    }

    private void consultarPorCnpjCpf() {
        System.out.print("Digite o CNPJ/CPF do cliente: ");
        String cnpjCpf = scanner.nextLine();
        List<NotaFiscalModel> notas = controller.buscarPorCpfCnpj(cnpjCpf);
        if (!notas.isEmpty()) {
            notas.forEach(n -> System.out.println(n));
        } else {
            System.out.println("Nenhuma NF-e encontrada para esse CNPJ/CPF.");
        }
    }

    private void consultarPorValorTotal() {
        System.out.print("Digite o valor total da NF-e: ");
        double valorTotal = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        List<NotaFiscalModel> notas = controller.buscarPorValorTotal(valorTotal);
        if (!notas.isEmpty()) {
            notas.forEach(n -> System.out.println(n));
        } else {
            System.out.println("Nenhuma NF-e encontrada para esse valor.");
        }
    }

   
    
    public static void main(String[] args) {
        Menu m = new Menu();
        m.exibir();
    }

    private Date parseDate(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
