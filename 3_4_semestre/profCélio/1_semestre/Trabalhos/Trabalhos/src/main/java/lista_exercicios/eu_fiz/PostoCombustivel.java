package lista_exercicios.eu_fiz;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

//    desconto:
//    etanol -> (<= 20L) = 0.03; ( > 20L) = 0.05; 
//    gasolina -> 0.04 = ( > 20L); 0.06 = ( > 20L);

// tipo: E-tanol, G-gasolina

public class PostoCombustivel { 
    
    int tipo;
    float qtd_litros;
    float desconto;
    float preco_G = 5.35f;
    float preco_E = 3.79f;
    float valor_bruto;
    float total_pagar;
    
    
    public void entrada(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Qual o tipo de combustivel e quantos litros vai abastecer? \n temos 2 tipos:  1 = E-etanol ou 2 = G-gasolina \n" +
                " O valor do E-etanol é de R$3,35 \n O valor da G-gasolina está R$ 5,79");

        tipo = validar (scanner, "Digite aqui o Tipo: ");
        qtd_litros = validar (scanner, "Digite aqui Quantos litros: ");
        scanner.close();
        
        callDesconto();
    }
    
    // Metodo para validar
    private float validar(){
        
    }
    
    public void callDesconto(){
        
        switch (tipo) {
            case 1 -> {
                System.out.println("Você escoleu o tipo 1 E-etanol com o valor de R$ " + preco_E + " o LITRO!");
                valor_bruto = qtd_litros + preco_E;
                if (qtd_litros <= 20) { 
                    desconto = 0.03f;
                } else {
                    desconto = 0.05f;
                }
                total_pagar = valor_bruto - desconto;
                break;
            }
            case 2 -> {
                System.out.println("Você escoleu o tipo 2 G-gasolina com o valor de R$ " + preco_G + " o LITRO!");
                valor_bruto = qtd_litros + preco_G;
                if (qtd_litros < 20) {
                    desconto = 0.04f;
                } else { 
                    desconto = 0.06f;
                }
                total_pagar = valor_bruto - desconto;
            }
            default -> throw new AssertionError("Valor inesperado: " + tipo);
        }
 
    }
    
    
    public void imprimir(){
        System.out.println("acabo");
    }
    public static void main(String[] args) {
        PostoCombustivel obj = new PostoCombustivel();
        
        obj.entrada();
        obj.callDesconto();
        obj.imprimir();
    }
}
