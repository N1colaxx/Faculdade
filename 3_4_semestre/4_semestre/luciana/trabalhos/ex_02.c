
/*
  >>> ALTERAÇÕES PARA C PURO (explicação):
  - Por que mudar: o código original misturava C e C++ (usava `new`/`delete`, que são do C++).
    Em C puro, a alocação/liberação dinâmica é feita com `malloc`/`free` (em <stdlib.h>).
  - O que mudou:
    1) Incluí <stdlib.h> e troquei `new PILHA()` por `(PILHA*) malloc(sizeof(PILHA))`.
    2) Troquei `delete(aux)` por `free(aux)`.
    3) Ajustei a definição da struct para C:
       - Em C, o nome do `struct` não vira tipo automaticamente (como em C++).
       - Usei `typedef struct PILHA { ... } PILHA;` e, DENTRO da struct, referenciei o ponteiro
         como `struct PILHA *prox;` (forma válida em C).
    4) Ajustei a assinatura de main para `int main(void)` (padrão em C).
  - Observação: mantive a MESMA lógica/fluxo do seu programa (inclusive os mesmos `while (op == X)`),
    apenas convertendo a sintaxe para C puro e comentando linha a linha.
*/

#include <stdio.h>   // printf, scanf
#include <stdlib.h>  // malloc, free

// Definindo o registro (struct) que representará cada elemento da pilha em C puro
typedef struct PILHA
{
    int num;                 // Armazena o número do elemento
    struct PILHA *prox;      // Ponteiro para o próximo elemento (usa "struct PILHA" dentro do typedef)
} PILHA;

int main(void)
{
    // A pilha está inicialmente vazia; operações de inserção/remoção acontecem no TOPO
    PILHA *topo = NULL;  // topo aponta para o primeiro nó (NULL = vazio)

    // Ponteiro auxiliar usado em percursos e remoções
    PILHA *aux;

    // Variável para armazenar a opção do menu
    int op;

    {
        // Exibe o menu de opções
        printf(" MENU DE OPCOES:\n ");
        printf(" 1 - Inserir na pilha\n ");
        printf(" 2 - Consultar a pilha\n ");
        printf(" 3 - Remover da pilha\n ");
        printf(" 4 - Esvaziar a pilha\n ");
        printf(" 5 - Sair\n ");
        printf(" Digite sua opcao: ");

        // Lê a opção escolhida
        scanf("%d", &op);

        // Valida faixa de opções (1..5)
        if (op < 1 || op > 5)
            printf(" Opcao invalida !!\n ");

        // Enquanto a opção for 1: INSERIR
        while (op == 1)
        {
            printf(" Digite o numero a ser inserido na pilha: ");

            // Aloca dinamicamente um novo nó (C puro: malloc em vez de new)
            PILHA *novo = (PILHA*) malloc(sizeof(PILHA));  // criação do nó da pilha
            // (em produção, valeria checar se novo == NULL para erro de alocação)

            // Lê o valor que será armazenado no campo "num"
            scanf("%d", &novo->num);

            // Encadeia o novo nó no topo: ele passa a apontar para o topo atual
            novo->prox = topo;

            // Atualiza o topo para o nó recém-inserido
            topo = novo;

            printf(" Numero inserido na pilha !! \n");
            printf(" Digite sua opcao: ");
            scanf("%d", &op);
        }

        // Enquanto a opção for 2: CONSULTAR
        while (op == 2)
        {
            if (topo == NULL)  // pilha vazia se topo == NULL
            {
                printf(" Pilha vazia ");
            }
            else
            {
                // Percorre a pilha do topo até o fim (NULL)
                printf(" Consultando a pilha: ");
                aux = topo;  // aux começa no topo

                while (aux != NULL)  // enquanto não atingir o fim
                {
                    printf("num = %d\n", aux->num);  // mostra o valor do nó
                    aux = aux->prox;                 // avança para o próximo nó
                }

                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Enquanto a opção for 3: REMOVER DO TOPO
        while (op == 3)
        {
            if (topo != NULL)  // há elementos para remover
            {
                aux = topo;  // aux aponta para o nó que será removido (o topo)
                printf(" Numero removido: %d\n", topo->num);  // mostra o valor removido

                topo = topo->prox;  // topo “desce” para o próximo elemento
                free(aux);          // libera a memória do antigo topo (C puro: free em vez de delete)

                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                // pilha vazia: nada a remover
                printf(" Pilha vazia !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Enquanto a opção for 4: ESVAZIAR A PILHA
        while (op == 4)
        {
            if (topo == NULL)  // já está vazia
            {
                printf(" Pilha vazia !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                // Remove todos os nós até o topo virar NULL
                aux = topo;  // aux começa no topo
                while (aux != NULL)
                {
                    topo = topo->prox;  // avança o topo para o próximo nó
                    free(aux);          // libera o nó atual
                    aux = topo;         // atualiza aux para o novo topo
                }

                printf(" Pilha esvaziada !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Enquanto a opção NÃO for 5: (nota: isso aqui, do jeito original, não reexibe o menu)
        // Mantido exatamente como no seu código.
        while (op != 5)
            ;  // laço vazio (aguarda op == 5 para sair)
    }

    return 0;  // término normal do programa em C
}
