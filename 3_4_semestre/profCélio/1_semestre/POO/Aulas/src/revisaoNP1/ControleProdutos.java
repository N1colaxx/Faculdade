package revisaoNP1;

// Classe principal para testar o sistema
import java.util.Scanner;


public class ControleProdutos {
//    private Loja loja = null;
    private final Scanner scanner = new Scanner(System.in);
    private int proximoId = 1;
    private ProdutoConsumo produtoConsumo;
    
    private  IncluirProConsumo IPC;
    private  AlterarProConsumo APC;
    private  ConsultarProConsumo CPC;

//    private  ExcluirProConcumo XPC;

//    private  IncluirProVenda IPV;
//    private  AlterarProVenda APV;
//    private  ConsultarProVenda CPV;
//    private  ExcluirProVenda XPV;
//    private  VenderProVenda VPV;

    public ControleProdutos() {
        produtoConsumo = null;
        this.menu();
    }
    
    
    public final void menu() {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    menuProdutosConsumo();
                    break;
                case 2:
//                    menuProdutosVenda();
                    break;
                case 3:
//                    menuLojas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n=== Controle de Produtos ===");
        System.out.println("1) Produtos de Consumo");
        System.out.println("2) Produtos de Venda");
        System.out.println("3) Loja");
        System.out.println("4) Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void menuProdutosConsumo() {
        int opcao;
        do {
            System.out.println("\n=== Menu Produtos de Consumo ===");
            System.out.println("a) Incluir");
            System.out.println("b) Alterar");
            System.out.println("c) Consultar");
            System.out.println("d) Excluir");
            System.out.println("e) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.next().charAt(0);
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 'a' ->{
                    if(produtoConsumo != null){
                        System.out.println("ERRO: Produto ja cadastrado!... ");
                        System.out.println("Altere ele ou Exclua ...\n");
                    } else{
                        IPC = new IncluirProConsumo();
                        produtoConsumo = IPC.incluirProduto(proximoId); // o id ta qui pra atualizar sozim sem a ajuda o User digitar para evitar id iguais.
                    }
                }
                case 'b' ->{
                    if(produtoConsumo  == null){
                        System.out.println("ERRO: Produto NULL Inclua um antes de alterar...");
                    }else{
                        APC = new AlterarProConsumo();
                        produtoConsumo = APC.alterarProduto(produtoConsumo);
                    }
                }
                case 'c' -> {
                    CPC = new ConsultarProConsumo();
                    CPC.consultarProduto(produtoConsumo);
                }
                case 'd' -> { 
//                    XPC.excluirProduto();
                }
                case 'e' -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
//                     consultarProdutoConsumo();
//                    excluirProdutoConsumo();
                    } while (opcao != 'e');
    }

//
//    private void menuProdutosVenda() {
//        int opcao;
//        do {
//            System.out.println("\n=== Menu Produtos de Venda ===");
//            System.out.println("a) Incluir");
//            System.out.println("b) Alterar");
//            System.out.println("c) Consultar");
//            System.out.println("d) Excluir");
//            System.out.println("e) Vender");
//            System.out.println("f) Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            opcao = scanner.next().charAt(0);
//            scanner.nextLine(); // Limpar buffer
//
//            switch (opcao) {
//                case 'a' -> {
//                    
//                    IPV.incluirProduto();
//                }
//                case 'b' -> { 
//                    
//                    APV.alterarProduto();
//                }
//                case 'c' -> {
//                    
//                    CPV.consultarProduto();
//                }
//                case 'd' -> {
//                    
//                    XPV.excluirProduto();
//                }
//                case 'e' -> {
//                    
//                    VPV.venderProduto();
//                }
//                case 'f' -> System.out.println("Voltando ao menu principal...");
//                default -> System.out.println("Opção inválida. Tente novamente.");
//            }
//        } while (opcao != 'f');
//    }
// 




//
//    private void menuLojas() {
//        int opcao;
//        do {
//            System.out.println("\n=== Menu Lojas ===");
//            System.out.println("a) Incluir");
//            System.out.println("b) Alterar");
//            System.out.println("c) Consultar");
//            System.out.println("d) Excluir");
//            System.out.println("e) Adicionar produto");
//            System.out.println("f) Listar produto");
//            System.out.println("g) Excluir produto");
//            System.out.println("h) Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            opcao = scanner.next().charAt(0);
//            scanner.nextLine(); // Limpar buffer
//
//            switch (opcao) {
//                case 'a':
//                    incluirLoja();
//                    break;
//                case 'b':
//                    alterarLoja();
//                    break;
//                case 'c':
//                    consultarLoja();
//                    break;
//                case 'd':
//                    excluirLoja();
//                    break;
//                case 'e':
//                    adicionarProdutoALoja();
//                    break;
//                case 'f':
//                    listarProdutoDaLoja();
//                    break;
//                case 'g':
//                    excluirProdutoDaLoja();
//                    break;
//                case 'h':
//                    System.out.println("Voltando ao menu principal...");
//                    break;
//                default:
//                    System.out.println("Opção inválida. Tente novamente.");
//            }
//        } while (opcao != 'h');
//    }
//
//    private void incluirLoja() {
//        if (loja != null) {
//            System.out.println("Já existe uma loja cadastrada. Exclua ou altere a existente.");
//            return;
//        }
//
//        System.out.print("Digite o nome da loja: ");
//        String nomeLoja = scanner.nextLine();
//
//        loja = new Loja(proximoId++, nomeLoja);
//        System.out.println("Loja incluída com sucesso!");
//    }
//
//    private void alterarLoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//            return;
//        }
//
//        System.out.print("Digite o novo nome da loja: ");
//        loja.setNomeLoja(scanner.nextLine());
//        System.out.println("Loja alterada com sucesso!");
//    }
//
//    private void consultarLoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//        } else {
//            System.out.println("ID: " + loja.getId() + ", Nome da loja: " + loja.getNomeLoja());
//        }
//    }
//
//    private void excluirLoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//        } else {
//            loja = null;
//            System.out.println("Loja excluída com sucesso!");
//        }
//    }
//
//    private void adicionarProdutoALoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//            return;
//        }
//
//        System.out.println("Selecione o tipo de produto a ser adicionado:");
//        System.out.println("1) Produto de Consumo");
//        System.out.println("2) Produto de Venda");
//        int tipoProduto = scanner.nextInt();
//        scanner.nextLine(); // Limpar buffer
//
//        Produto produto = null;
//        if (tipoProduto == 1 && produtoConsumo != null) {
//            produto = produtoConsumo;
//        } else if (tipoProduto == 2 && produtoVenda != null) {
//            produto = produtoVenda;
//        } else {
//            System.out.println("Nenhum produto disponível para adicionar à loja.");
//            return;
//        }
//
//        loja.adicionarProduto(produto);
//        System.out.println("Produto adicionado à loja com sucesso!");
//    }
//
//    private void listarProdutoDaLoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//        } else {
//            loja.exibirProduto();
//        }
//    }
//
//    private void excluirProdutoDaLoja() {
//        if (loja == null) {
//            System.out.println("Nenhuma loja cadastrada.");
//        } else {
//            loja.excluirProduto();
//            System.out.println("Produto excluído da loja com sucesso!");
//        }
//    }
//    
    public static void main(String[] args) {
        ControleProdutos controleProdutos = new ControleProdutos();
    }
}
