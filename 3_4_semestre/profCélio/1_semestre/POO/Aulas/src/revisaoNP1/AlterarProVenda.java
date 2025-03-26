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
public class AlterarProVenda {
   private ProdutoVenda produtoVenda;
   private final Scanner scanner;
    
    public AlterarProVenda(Scanner scanner){
        this.scanner = scanner;
    }
    public void alterarProduto() {
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

}
