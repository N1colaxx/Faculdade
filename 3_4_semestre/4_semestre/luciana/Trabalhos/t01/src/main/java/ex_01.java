import java.util.ArrayList; // importa a classe ArrayList (não é usada neste código, mas está importada)
import java.util.Collection; // importa a interface Collection (não é usada aqui)
import java.util.LinkedList; // importa a classe LinkedList (não é usada aqui)
import java.util.List; // importa a interface List (não é usada aqui)
import java.util.Scanner; // importa a classe Scanner para ler dados do teclado
import java.util.TreeSet; // importa a classe TreeSet (não é usada aqui)
import java.util.stream.Collectors; // importa Collectors para streams (não é usado aqui)
import java.util.stream.Stream; // importa Stream (não é usado aqui)
 
public class ex_01 { // declara a classe pública ex_01 (nome do arquivo deve ser ex_01.java)
    
    /* // abre um comentário de bloco explicativo
    Uma class interna de ex_01 // descrição textual da classe interna
    Ela permite ser instanciada sem criar um OBJ. // nota: por ser static, pode ser instanciada sem instanciar ex_01
    E todo OBJ que for criado apartir dela vai conter: NUM e PROX // descreve os campos do nó
    Imagine como se fosse uma caixa: [num = null | prox = null]   SIM eles vem com valor null padrão // ilustração do nó
    */ // fecha o comentário de bloco
    private static class LISTA { // declara uma classe interna estática chamada LISTA (servirá como nó da lista)
        public int num; //armazena um número inteiro // campo de dado do nó
        public LISTA prox; //aponta para o proximo elemento da Lista, imagine uma "seta" // referência ao próximo nó
    } // fecha a definição da classe interna LISTA
 
    public static void main(String[] args) { // método principal: ponto de entrada do programa
 
        Scanner leia = new Scanner(System.in);  //permite o user digitar // cria Scanner para ler do System.in
        LISTA inicio = null; // começo da lista // ponteiro para o primeiro nó (cabeça)
        LISTA fim = null;  //fim da lista  // ponteiro para o último nó (cauda)
        LISTA aux; // declara ponteiro auxiliar para percorrer a lista
        LISTA anterior; // declara ponteiro para guardar o nó anterior durante remoções
        int op, numero, achou; //var que armazena um Número inteiro // op=opção do menu; numero=valor informado; achou=contagem de remoções
        
        do { // inicia laço do...while para repetir o menu até escolher sair
            // Menu com opções para o User // comentário informativo do menu
           System.out.println("1 - Inserir no início da lista"); 
            System.out.println("2 - Inserir no fim da lista"); 
            System.out.println("3 - Consultar toda Lista"); 
            System.out.println("4 - Remover da Lista");
            System.out.println("5 - Esvaziar a Lista"); 
            System.out.println("6 - Sair");
            System.out.print("Digite sua opção: "); // solicita a opção do usuário
            op = leia.nextInt();  // armazena o valor que o user digitou, somente INT // lê a opção digitada
            if (op < 1 || op > 6) { // valida se o numero esta entre 0 e 6, fora isso da erro  
                System.out.println("Operação Inválida!"); // Mensagem exibida ao User // informa erro
            } 
            
            /*
                O SWITCH recebe o valor de OP e entrega em uma das CASE e
            */
            switch (op) { // inicia switch para decidir a ação conforme a opção
                
                case 1: // caso 1: inserir no início
                    System.out.print("Digite o número a ser inserido no início da lista: "); //exibe um msg ao User // pede o número
                    LISTA novo = new LISTA(); // cria um novo OBJ de LISTA -> novo = [num | prox] // instancia um novo nó
                    novo.num = leia.nextInt(); // add o valor que o User digitou ao --> NUM do obj lista // lê e atribui o valor ao campo num
                    
                    /* Uma validação simples para ver se o primeiro campo da LISTA -> INICIO esta preenchido ou vazio, se 
                    for igual a NULL executa o IF diferente disso executa o ELSE 
                    */
                    if (inicio == null) { // verificaçao simples, somente SE o inicio da lista estiver NULL ele vai executar 
                        inicio = novo; // armazena o valor de NOVO ao primeiro campo da LISTA, campo -> INICIO 
                        fim = novo; // armazena o valor de NOVO ao ultimo campo da LISTA, campo -> FIM  e também o último
                        novo.prox = null; // NOVO esta apontando para nada(null) não há próximo
                    } else { // executa se o INICIO da lista estiver diferente de NULL 
                        novo.prox = inicio; // NOVO aponta para INICIO que é o primeiro campo da LISTA, campo -> INICIO // encadeia antes do antigo início
                        inicio = novo; //INICIO recebe o valor de NOVO e o armazena // atualiza o início para o novo nó
                        
                    } 
                    System.out.println("Numero inserido no início da Lista"); // exibe uma msg ao user // confirma a operação
                    break; // para a exucao do bloco caso o CASE for satisfeito  // encerra o case 1
                    
                case 2: // caso 2: inserir no fim
                    System.out.println("Digite o numero a ser inserido ao fim da lista: "); // exibe um msg ao user  // pede o número
                    novo = new LISTA();  // cria um novo OBJ de LISTA -> novo = [num | prox ] // instancia novo nó
                    novo.num = leia.nextInt();  // add o valor que o User digitou ao --> NUM do obj lista // lê e atribui o valor
                    if (inicio == null) { // verificaçao simples, somente SE o inicio da lista estiver NULL ele vai executar 
                        inicio = novo; // o inicio da lisata vai receber o valor de novo, novo vira o primeiro
                        fim = novo;  // armazena o valor de NOVO ao ultimo campo da LISTA, campo -> FIM  e também o último
                        novo.prox = null; //  // não há próximo (terminador da lista)
                    } else { // executa se o INICIO da lista estiver diferente de NULL (lista não vazia)
                        fim.prox = novo; // encadeia o novo nó após o antigo último
                        fim = novo; // atualiza o ponteiro 'fim' para o novo nó
                        fim.prox = null; // garante que o último aponte para null
                    } 
                    System.out.println("Numero Inserido no fim da lista!!"); // confirma a operação de inserção no fim
                    break; // encerra o case 2
                case 3: // caso 3: consultar/imprimir a lista
                    if (inicio == null) { // verifica se a lista está vazia
                        System.out.println("Lista Vazia"); // informa que não há elementos
                    } else { // se tiver elementos
                        System.out.println("Consultando toda a lista"); // título da consulta
                        aux = inicio; // inicia o ponteiro auxiliar no começo da lista
                        while (aux != null) { // percorre até o final (quando o próximo é null)
                            System.out.println(aux.num+ "  "); // imprime o valor do nó atual (com espaço extra)
                            aux = aux.prox; // avança para o próximo nó
                        } // fim do while de varredura
                    } // fim do if/else da consulta
                    break; // encerra o case 3
                case 4: // caso 4: remover elementos pelo valor
                    if (inicio == null) { // se a lista está vazia
                        System.out.println("Lista Vazia"); // informa que não há o que remover
                    } else { // caso tenha elementos
                        System.out.println("Digite o elemento a ser removido: "); // solicita o número a remover
                        numero = leia.nextInt(); // lê o número
                        aux = inicio; // começa varredura pelo início
                        anterior = null; // anterior começa null (não há anterior do primeiro)
                        achou = 0; // zera o contador de ocorrências removidas
                        while (aux != null) { // percorre a lista inteira
                            if (aux.num == numero) {  // verifica se o nó atual tem o valor desejado
                                achou = achou + 1; // incrementa contagem de remoções
                                if (aux == inicio) { // caso especial: removendo o primeiro nó
                                    inicio = aux.prox; // move o início para o próximo nó
                                    aux = inicio; // atualiza 'aux' para o novo início
                                } else if (aux == fim) { // caso especial: removendo o último nó
                                    anterior.prox = null; // o penúltimo passa a ser o último (aponta para null)
                                    fim = anterior; // atualiza o ponteiro 'fim'
                                    aux = null; // para o laço (encerra varredura)
                                } else { // remoção no meio da lista
                                    anterior.prox = aux.prox; // liga o anterior diretamente ao próximo, pulando 'aux'
                                    aux = aux.prox; // avança para o próximo nó
                                } // fim dos casos de remoção
                            } else { // se não é o valor a remover
                                anterior = aux; // move 'anterior' para o atual
                                aux = aux.prox; // avança 'aux' para o próximo
                            } // fim do else (não bateu o número)
 
                            if (achou == 0) { // verifica a contagem de remoções
                                System.out.println("Numero não encontrado!"); // mensagem se não removeu nada
                            } else if (achou == 1) { // exatamente uma remoção
                                System.out.println("Numero removildo 1 vez"); // mensagem (obs: "removildo" está com typo no original)
                            } else { // mais de uma remoção
                                System.out.println("Numero removildo" + achou + "vezes"); // mensagem acumulada (sem espaço entre número e 'vezes' no original)
                            } // fim do bloco de mensagens (nota: está dentro do while)
                        } // fim do while de remoção (varredura completa)
 
                    } // fim do else (lista não está vazia) do case 4
                    break; // encerra o case 4
                case 5: // caso 5: esvaziar a lista
                    if (inicio == null) { // se já está vazia
                        System.out.println("Lista vazia!"); // apenas informa
                    } else { // caso tenha elementos
                        inicio = null; // solta a referência do primeiro nó (GC cuidará do resto)
                        System.out.println("Lista esvaziada"); // confirma a operação
                    } // fim do if/else do esvaziamento
                    break; // encerra o case 5
            } // fecha o switch
 
        } while (op != 6); // repete o menu enquanto a opção for diferente de 6 (Sair)
 
    } // fecha o main
} // fecha a classe ex_01
