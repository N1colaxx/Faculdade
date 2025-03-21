package PDF1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */

import java.util.Scanner;

public class ProdutoDesconto {

    // var de produto
    String nome;
    int quantidade;
    float preco_unitario;
    float valor_total; //  (total = quantidade adquirida * preço unitário)

    // var de desconto 
    float desconto;
    float total_pagar;

    public void produto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do produto: ");
        nome = scanner.nextLine();

        System.out.println("Digite a quantidade: ");
        quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o valor Unitario: ");
        preco_unitario = scanner.nextFloat();
        scanner.nextLine();

        valor_total = (quantidade * preco_unitario);

        desconto();
    }

    public void desconto() {
//  (total a pagar = total - desconto)
//Se quantidade <= 5 o desconto será de 2% = 0.02
//Se quantidade > 5 e quantidade <= 10 o desconto será de 3% = 0.03
//Se quantidade > 10 o desconto será de 5% = 0.05
        
        if (quantidade <= 5){
            desconto = 0.02f;
            total_pagar = valor_total - desconto;
        } else if (quantidade > 5) {
                desconto = 0.03f;
                total_pagar = valor_total - desconto;
                } else if (quantidade > 10) {
                    desconto = 0.05f;
                    total_pagar = valor_total - desconto;
                }  
    }

    public void imprimir(){
        System.out.println("\n");
        System.out.println("O valor total dos produtos ficou em: R$ " + valor_total + "");
        System.out.println("Você tem direito ao desconto de R$ " + desconto);
        System.out.println("Ao aplicar o desconto ficou: R$ " + total_pagar);
    }
    public static void main(String[] args) {
        ProdutoDesconto obj = new ProdutoDesconto();

        obj.produto();
        obj.desconto();
        obj.imprimir();

    }
}
