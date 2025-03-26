/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisaoNP1;

import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class IncluirProVenda {
    private ProdutoVenda produtoVenda = null;
    private final Scanner scanner;
    private int proximoId;
    
    public IncluirProVenda(Scanner scanner, int proximoId){
        this.scanner = scanner;
        this.proximoId = proximoId;
    } 
    
    public void incluirProduto() {
        double preco;
        int quantidadeEstoque;
        
        if (produtoVenda != null) {
            System.out.println("Já existe um produto de venda cadastrado. Exclua ou altere o existente.");
            return;
        }

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Digite a quantidade em estoque: ");
        quantidadeEstoque = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        produtoVenda = new ProdutoVenda(proximoId++, nome, preco, quantidadeEstoque);
        System.out.println("Produto de venda incluído com sucesso!");
    }
}
