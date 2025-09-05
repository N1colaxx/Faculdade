
import java.util.ArrayList; 
import java.util.Collection; 
import java.util.LinkedList; 
import java.util.List; 
import java.util.Scanner; 
import java.util.TreeSet; 
import java.util.stream.Collectors; 
import java.util.stream.Stream; 
 
public class ex_01 { 
    
    /* 
    Uma class interna de ex_01 
    Ela permite ser instanciada sem criar um OBJ.
    E todo OBJ que for criado apartir dela vai conter: NUM e PROX
    Imagine como se fosse uma caixa: [num = null | prox = null]   SIM eles vem com valor null padrão
    */
    private static class LISTA { 
        public int num; //armazena um número inteiro
        public LISTA prox; //aponta para o proximo elemento da Lista, imagine uma "seta"
    } 
 
    public static void main(String[] args) { 
 
        Scanner leia = new Scanner(System.in);  //permite o user digitar
        LISTA inicio = null; // começo da lista
        LISTA fim = null;  //fim da lista 
        LISTA aux; 
        LISTA anterior; 
        int op, numero, achou; //var que armazena um Número inteiro
        
        do { 
            // Menu com opções para o User
           System.out.println("1 - Inserir no início da lista"); 
            System.out.println("2 - Inserir no fim da lista"); 
            System.out.println("3 - Consultar toda Lista"); 
            System.out.println("4 - Remover da Lista"); 
            System.out.println("5 - Esvaziar a Lista"); 
            System.out.println("6 - Sair"); 
            System.out.print("Digite sua opção: "); 
            op = leia.nextInt();  // armazena o valor que o user digitou, somente INT
            // valida se o numero esta entre 0 e 6, fora isso da erro
            if (op < 1 || op > 6) { 
                System.out.println("Operação Inválida!"); // Mensagem exibida ao User
            } 
            
            /*
                O SWITCH recebe o valor de OP e entrega em uma das CASE e 
            executa o codigo dentro dela.
            */
            switch (op) { 
                case 1: 
                    System.out.print("Digite o número a ser inserido no início da lista: "); //exibe um msg ao User
                    LISTA novo = new LISTA(); // cria um novo OBJ de LISTA -> novo = [num | prox]
                    novo.num = leia.nextInt(); // add o valor que o User digitou ao --> NUM 
                    
                    /* Uma validação simples para ver se o primeiro campo da LISTA -> INICIO esta preenchido ou vazio, 
                    se for igual a NULL executa o IF diferente disso executa o ELSE
                    */
                    if (inicio == null) { 
                        inicio = novo; // armazena o valor de NOVO ao primeiro campo da LISTA, campo -> INICIO
                        fim = novo; // armazena o valor de NOVO ao ultimo campo da LISTA, campo -> FIM
                        novo.prox = null; // NOVO esta apontando para nada(null)
                    } else { 
                        novo.prox = inicio; // NOVO aponta para INICIO que é o primeiro campo da LISTA, campo -> INICIO
                        inicio = novo; //INICIO recebe o valor de NOVO e o armazena
                        
                    } 
                    System.out.println("Numero inserido no início da Lista"); 
                    break; 
                case 2: 
                    System.out.println("Digite o numero a ser inserido ao fim da lista: "); 
                    novo = new LISTA(); 
                    novo.num = leia.nextInt(); 
                    if (inicio == null) { 
                        inicio = novo; 
                        fim = novo; 
                        novo.prox = null; 
                    } else { 
                        fim.prox = novo; 
                        fim = novo; 
                        fim.prox = null; 
                    } 
                    System.out.println("Numero Inserido no fim da lista!!"); 
                    break; 
                case 3: 
                    if (inicio == null) { 
                        System.out.println("Lista Vazia"); 
                    } else { 
                        System.out.println("Consultando toda a lista"); 
                        aux = inicio; 
                        while (aux != null) { 
                            System.out.println(aux.num+ "  "); 
                            aux = aux.prox; 
                        } 
                    } 
                    break; 
                case 4: 
                    if (inicio == null) { 
                        System.out.println("Lista Vazia"); 
                    } else { 
                        System.out.println("Digite o elemento a ser removido: "); 
                        numero = leia.nextInt(); 
                        aux = inicio; 
                        anterior = null; 
                        achou = 0; 
                        while (aux != null) { 
                            if (aux.num == numero) {  
                                achou = achou + 1; 
                                if (aux == inicio) { 
                                    inicio = aux.prox; 
                                    aux = inicio; 
                                } else if (aux == fim) { 
                                    anterior.prox = null; 
                                    fim = anterior; 
                                    aux = null; 
                                } else { 
                                    anterior.prox = aux.prox; 
                                    aux = aux.prox; 
                                } 
                            } else { 
                                anterior = aux; 
                                aux = aux.prox; 
                            } 
 
                            if (achou == 0) { 
                                System.out.println("Numero não encontrado!"); 
                            } else if (achou == 1) { 
                                System.out.println("Numero removildo 1 vez"); 
                            } else { 
                                System.out.println("Numero removildo" + achou + "vezes"); 
                            } 
                        } 
 
                    } 
                    break; 
                case 5: 
                    if (inicio == null) { 
                        System.out.println("Lista vazia!"); 
                    } else { 
                        inicio = null; 
                        System.out.println("Lista esvaziada"); 
                    } 
                    break; 
            } 
 
        } while (op != 6); 
 
    } 
} 