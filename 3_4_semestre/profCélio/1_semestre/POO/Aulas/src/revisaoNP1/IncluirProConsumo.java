/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisaoNP1;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class IncluirProConsumo {
    private ProdutoConsumo produtoConsumo = null;
    private int proximoId;
    private Scanner scanner;
    
    public IncluirProConsumo(int proximoId, Scanner scanner){
        this.proximoId = proximoId; 
        this.scanner = scanner;
    }
    
        
    protected void incluirProduto(){
        String nome, dataValidade;
        double preco;
        
        if(produtoConsumo != null){
            System.out.println("ERRO: Produto ja cadastrado!... ");
        } else {
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
            System.out.print("Digite o preço do produto: ");
            preco = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer
            System.out.print("Digite a data de validade: ");
            dataValidade = scanner.nextLine();

            produtoConsumo = new ProdutoConsumo(proximoId++, nome, preco, dataValidade);
            System.out.println("Produto de consumo incluído com sucesso!");
        }
    }

    
}
