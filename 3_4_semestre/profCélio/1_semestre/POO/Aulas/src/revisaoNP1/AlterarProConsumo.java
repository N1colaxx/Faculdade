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
public class AlterarProConsumo {
    private ProdutoConsumo produtoConsumo;


    protected ProdutoConsumo alterarProduto(ProdutoConsumo produtoConsumo) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o novo nome: ");
        produtoConsumo.setNome(scanner.nextLine());
        
        System.out.print("Digite o novo preço: ");
        produtoConsumo.setPreco(scanner.nextDouble());    
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Digite a nova data de validade: ");
        produtoConsumo.setDataValidade(scanner.nextLine());

        System.out.println("Produto de consumo alterado com sucesso!");
        
        return produtoConsumo;
    }
}
