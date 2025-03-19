package PDF1.ExerciciosQueEUfiz;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nicolas
 */
import java.util.Scanner;

public class PostoCombustivel {

    int opc;
    float qtd_litros;
    float desconto;
    String des_print;
    float preco_G = 5.79f;
    float preco_E = 3.35f;
    float valor_bruto;
    float val_desconto;
    float total_pagar;

    public void entrada() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("""
                Qual o tipo de combustivel e quantos litros vai abastecer? Temos 2 opções:  1 = E-etanol ou 2 = G-gasolina 
                O valor do E-etanol de R$ 3,35 
                O valor da G-gasolina de R$ 5,79 """);
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

//            Validando o Conbustivel
            do {
                System.out.print("\nDigite qual o Tipo de combustivel: ");
                //  OBS: O Scanner entra em execução assim que chamamos qualquer um de seus métodos, hasNextInt(), nextInt(), nextLine(), etc. então quando chamo ele no IF ele para a execução do codigo e aguar o user digitar algo e apertar enter, ai o IF entra em execução e vai validar o que o user digitou se é um tipo inteiro
                if (scanner.hasNextInt()) {
                    opc = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    switch (opc) {
                        case 1 -> System.out.println("Vc escolheu o E-etanol no valor de R$ " + preco_E);
                        case 2 -> System.out.println("Vc escolheu o G-gasolina no valor de R$ " + preco_G);
                        default -> {
                            throw new IllegalArgumentException();
                        }
                    }
                    break;

                } else {
                    System.out.println("ERRO: texto digitado INVALIDO, digite novamente!!");
                    scanner.nextLine();
                }
            } while (true);

        
//            Validando a quantidade de litros
            do {            
                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                System.out.print("Digite quantos litros de combustivel vai abastecer: ");
                
                if (scanner.hasNextFloat()){
                    qtd_litros = scanner.nextFloat();
                    scanner.nextLine();
                    if (qtd_litros > 0 ){
                        break;
                    } else {
                        System.out.println("ERR): Digite um N° maior que 0(zero)");
                        continue;
                    }
                } else {
                    System.out.println("ERRO: texto digitado INVALIDO, digite novamente!!");
                    scanner.nextLine();
                }
                
            } while (true);
        
                
        callDesconto();
        }
    }

    public void callDesconto(){
        
        switch (opc) {
            case 1 :
            case 2 :
                valor_bruto = qtd_litros * (opc == 1 ? preco_E : preco_G);
                
                if (qtd_litros <= 20){
                    desconto = opc == 1 ? 0.03f : 0.04f;
                    des_print = opc == 1 ?"3%" :"4%";
                } else {
                    desconto = opc == 1 ? 0.05f : 0.06f;
                    des_print = opc == 1 ? "5%" : "6%";
                }
                
                val_desconto = valor_bruto * desconto;
                total_pagar = valor_bruto - val_desconto;
        }
        
        imprimir();
    }
 
    public void imprimir(){
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Sua compra ficou em R$ " + String.format("%.2f", valor_bruto));
        System.out.println("Porem vc tem direito a " +  des_print + " de desconto!");
        System.out.println("O valor do desconto ficou em R$ " + String.format("%.2f", val_desconto));
        System.out.println("Sua compra ficou com o valor tota de R$ " + String.format("%.2f", total_pagar));
    }
    
    
    public static void main(String[] args) {
        PostoCombustivel obj = new PostoCombustivel();

        obj.entrada();
        //obj.callDesconto();
        //obj.imprimir();
    }
}
