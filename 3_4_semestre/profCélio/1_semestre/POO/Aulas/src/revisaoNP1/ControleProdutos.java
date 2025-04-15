package revisaoNP1;

// Classe principal para testar o sistema
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ControleProdutos {
    private ProdutoConsumo produtoConsumo = null;
    private ProdutoVenda produtoVenda = null;
    private Loja loja = null;
    private int proximoId = 1;
    Scanner scanner = new Scanner(System.in);
    
    private List<ProdutoConsumo> produtos = new ArrayList<>();

    public ControleProdutos() {
        this.menu();
    }
    public void menu() {
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
                    menuProdutosVenda();
                    break;
                case 3:
                    menuLojas();
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

    
//    Produtos de Consumo ------------------------------------------------------------------
    
    
    private void menuProdutosConsumo() {
        int opcao;
        do {
            System.out.println("\n=== Menu Produtos de Consumo ===");
            System.out.println("a) Incluir");
            System.out.println("b) Alterar");
            System.out.println("c) Consultar");
            System.out.println("d) Excluir");
            System.out.println("e) Criar Faker Produtos Consumo");
            System.out.println("g) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.next().charAt(0);
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 'a':
                    incluirProdutoConsumo();
                    break;
                case 'b':
                    alterarProdutoConsumo();
                    break;
                case 'c':
                    consultarProdutoConsumo();
                    break;
                case 'd':
                    excluirProdutoConsumo();
                    break;
                case 'e':
                    incluirFakerProdutoConsumo();
                    break;
                case 'g':
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 'g');
    }

    private void incluirProdutoConsumo() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Digite a data de validade: ");
        String dataValidade = scanner.nextLine();

        produtoConsumo = new ProdutoConsumo(proximoId++, nome, preco, dataValidade);
        produtos.add(produtoConsumo);
        System.out.println("Produto de consumo incluído com sucesso!");
    }

    private void alterarProdutoConsumo() {
        if (produtos.isEmpty()) {
        System.out.println("Nenhum produto de consumo cadastrado.");
        }else{
            System.out.print("Digite o ID do produto que deseja alterar: ");
            int idDigitado = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            ProdutoConsumo produtoEncontrado = null;
            for (ProdutoConsumo p : produtos) {
                if (p.getId() == idDigitado) { // assumindo que há um getter getId() na classe Produto ou ProdutoConsumo
                    produtoEncontrado = p;
                    break;
                }
            }

            if (produtoEncontrado == null) {
                System.out.println("Produto de consumo não encontrado.");
                return;
            }

            System.out.print("Digite o novo nome: ");
            produtoEncontrado.setNome(scanner.nextLine());
            System.out.print("Digite o novo preço: ");
            produtoEncontrado.setPreco(scanner.nextDouble());
            scanner.nextLine(); // Limpar buffer
            System.out.print("Digite a nova data de validade: ");
            produtoEncontrado.setDataValidade(scanner.nextLine());

            System.out.println("Produto de consumo alterado com sucesso!");
        }
}

    private void consultarProdutoConsumo() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto de consumo cadastrado.");
        } else {
            System.out.println("\n exibindo consulta.... \n");
            for (ProdutoConsumo p : produtos) {
                p.exibirDetalhes();
            }
        }
    }

    private void excluirProdutoConsumo() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto de consumo cadastrado.");
            return;
        }

        System.out.print("Digite o ID do produto que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        ProdutoConsumo produtoParaRemover = null;
        for (ProdutoConsumo p : produtos) {
            if (p.getId() == id) {
                produtoParaRemover = p;
                break;
            }
        }

        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover);
            System.out.println("Produto de consumo excluído com sucesso!");
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }
    }

    private void incluirFakerProdutoConsumo() {
        Faker faker = new Faker(new Locale("pt-BR")); // Configura o Faker para pt-BR (ajuste conforme necessário)
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy"); // Formata a data para o formato desejado

        // Gerando 5 produtos fake
        for (int i = 1; i <= 5; i++) {
            // Gerando um nome fake usando o módulo food do Faker
            String nomeProduto = faker.food().ingredient(); 
            // Gerando um preço com 2 casas decimais, entre 1 e 50
            double preco = faker.number().randomDouble(2, 1, 50);
            // Gerando uma data de validade aleatória no futuro (até 365 dias)
            Date fakeDate = faker.date().future(365, TimeUnit.DAYS);
            String dataValidade = sdf.format(fakeDate);

            // Adicionando o novo ProdutoConsumo à lista
            produtos.add(new ProdutoConsumo(i, nomeProduto, preco, dataValidade));
        }

        System.out.println("\nCriado com Sucesso!!!");
        System.out.println("Exibindo produtos... \n");
        exibirFakerProdutosConsumo();
    }
    
    public void exibirFakerProdutosConsumo() {
        for (ProdutoConsumo p : produtos) {
            p.exibirDetalhes();
        }
        
        System.out.println("");
    }
    //    Produtos de Venda ------------------------------------------------------------------
    
    
    private void menuProdutosVenda() {
        int opcao;
        do {
            System.out.println("\n=== Menu Produtos de Venda ===");
            System.out.println("a) Incluir");
            System.out.println("b) Alterar");
            System.out.println("c) Consultar");
            System.out.println("d) Excluir");
            System.out.println("e) Vender");
            System.out.println("f) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.next().charAt(0);
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 'a':
                    incluirProdutoVenda();
                    break;
                case 'b':
                    alterarProdutoVenda();
                    break;
                case 'c':
                    consultarProdutoVenda();
                    break;
                case 'd':
                    excluirProdutoVenda();
                    break;
                case 'e':
                    venderProdutoVenda();
                    break;
                case 'f':
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 'f');
    }

    private void incluirProdutoVenda() {
        if (produtoVenda != null) {
            System.out.println("Já existe um produto de venda cadastrado. Exclua ou altere o existente.");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Digite a quantidade em estoque: ");
        int quantidadeEstoque = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        produtoVenda = new ProdutoVenda(proximoId++, nome, preco, quantidadeEstoque);
        System.out.println("Produto de venda incluído com sucesso!");
    }

    private void alterarProdutoVenda() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
            return;
        }

        System.out.print("Digite o novo nome: ");
        produtoVenda.setNome(scanner.nextLine());
        System.out.print("Digite o novo preço: ");
        produtoVenda.setPreco(scanner.nextDouble());
        scanner.nextLine(); // Limpar buffer
        System.out.print("Digite a nova quantidade em estoque: ");
        produtoVenda.setQuantidadeEstoque(scanner.nextInt());
        scanner.nextLine(); // Limpar buffer

        System.out.println("Produto de venda alterado com sucesso!");
    }

    private void consultarProdutoVenda() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
        } else {
            produtoVenda.exibirDetalhes();
        }
    }

    private void excluirProdutoVenda() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
        } else {
            produtoVenda = null;
            System.out.println("Produto de venda excluído com sucesso!");
        }
    }

    private void venderProdutoVenda() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
        } else {
            produtoVenda.vender();
        }
    }

    
    //    Menu Loja ------------------------------------------------------------------
    
    private void menuLojas() {
        int opcao;
        do {
            System.out.println("\n=== Menu Lojas ===");
            System.out.println("a) Incluir");
            System.out.println("b) Alterar");
            System.out.println("c) Consultar");
            System.out.println("d) Excluir");
            System.out.println("e) Adicionar produto");
            System.out.println("f) Listar produto");
            System.out.println("g) Excluir produto");
            System.out.println("h) Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.next().charAt(0);
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 'a':
                    incluirLoja();
                    break;
                case 'b':
                    alterarLoja();
                    break;
                case 'c':
                    consultarLoja();
                    break;
                case 'd':
                    excluirLoja();
                    break;
                case 'e':
                    adicionarProdutoALoja();
                    break;
                case 'f':
                    listarProdutoDaLoja();
                    break;
                case 'g':
                    excluirProdutoDaLoja();
                    break;
                case 'h':
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 'h');
    }

    private void incluirLoja() {
        if (loja != null) {
            System.out.println("Já existe uma loja cadastrada. Exclua ou altere a existente.");
            return;
        }

        System.out.print("Digite o nome da loja: ");
        String nomeLoja = scanner.nextLine();

        loja = new Loja(proximoId++, nomeLoja);
        System.out.println("Loja incluída com sucesso!");
    }

    private void alterarLoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.print("Digite o novo nome da loja: ");
        loja.setNomeLoja(scanner.nextLine());
        System.out.println("Loja alterada com sucesso!");
    }

    private void consultarLoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
        } else {
            System.out.println("ID: " + loja.getId() + ", Nome da loja: " + loja.getNomeLoja());
        }
    }

    private void excluirLoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
        } else {
            loja = null;
            System.out.println("Loja excluída com sucesso!");
        }
    }

    private void adicionarProdutoALoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        System.out.println("Selecione o tipo de produto a ser adicionado:");
        System.out.println("1) Produto de Consumo");
        System.out.println("2) Produto de Venda");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Produto produto = null;
        if (tipoProduto == 1 && produtoConsumo != null) {
            produto = produtoConsumo;
        } else if (tipoProduto == 2 && produtoVenda != null) {
            produto = produtoVenda;
        } else {
            System.out.println("Nenhum produto disponível para adicionar à loja.");
            return;
        }

        loja.adicionarProduto(produto);
        System.out.println("Produto adicionado à loja com sucesso!");
    }

    private void listarProdutoDaLoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
        } else {
            loja.exibirProduto();
        }
    }

    private void excluirProdutoDaLoja() {
        if (loja == null) {
            System.out.println("Nenhuma loja cadastrada.");
        } else {
            loja.excluirProduto();
            System.out.println("Produto excluído da loja com sucesso!");
        }
    }
    
    public static void main(String[] args) {
        ControleProdutos controleProdutos = new ControleProdutos();
    }
}
