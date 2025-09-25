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
    int num;                 // Valor armazenado no nó (elemento) da pilha
    struct PILHA *prox;      // Ponteiro para o próximo nó (abaixo do topo)
} PILHA;

int main(void)                               // Função principal do programa
{
    PILHA *topo = NULL;                      // Ponteiro para o topo da pilha (NULL = pilha vazia)
    PILHA *aux;                              // Ponteiro auxiliar para percorrer/remover
    int op;                                  // Opção de menu digitada pelo usuário

    {                                        // Bloco único que contém menu + leituras (mantido como no seu código)

        printf(" MENU DE OPCOES:\n ");       // Mostra o título do menu
        printf(" 1 - Inserir na pilha\n ");  // Opção 1: empilhar (push)
        printf(" 2 - Consultar a pilha\n "); // Opção 2: listar elementos
        printf(" 3 - Remover da pilha\n ");  // Opção 3: desempilhar (pop)
        printf(" 4 - Esvaziar a pilha\n ");  // Opção 4: remover todos os elementos
        printf(" 5 - Sair\n ");              // Opção 5: finalizar programa
        printf(" Digite sua opcao: ");       // Pede a entrada da opção

        scanf("%d", &op);                    // Lê a opção escolhida (sem tratamento de erro, mantendo seu padrão)

        if (op < 1 || op > 5)                // Verifica se a opção está fora do intervalo 1..5
            printf(" Opcao invalida !!\n "); // Se estiver, avisa (e segue; sua lógica não reexibe o menu aqui)

        while (op == 1)                      // Enquanto a opção for 1 (INSERIR), repete este bloco
        {
            printf(" Digite o numero a ser inserido na pilha: "); // Solicita o valor do novo nó

            PILHA *novo = (PILHA*) malloc(sizeof(PILHA));         // Aloca memória para um novo nó
            // Obs: Em produção, seria bom checar (novo == NULL) antes de usar

            scanf("%d", &novo->num);         // Lê o valor e guarda no campo num do novo nó

            novo->prox = topo;               // Encadeia: novo aponta para o antigo topo
            topo = novo;                     // Atualiza o topo para o novo nó (push)

            printf(" Numero inserido na pilha !! \n"); // Confirma a inserção
            printf(" Digite sua opcao: ");             // Pede a próxima opção (aqui você troca de “modo”)
            scanf("%d", &op);                          // Lê nova opção; se continuar 1, insere de novo; senão sai do while
        }

        while (op == 2)                      // Enquanto a opção for 2 (CONSULTAR), repete este bloco
        {
            if (topo == NULL)                // Se não há nós na pilha
            {
                printf(" Pilha vazia ");     // Informa que está vazia
            }
            else
            {
                printf(" Consultando a pilha: "); // Cabeçalho da listagem
                aux = topo;                       // Começa pelo topo

                while (aux != NULL)               // Percorre até o fim (NULL)
                {
                    printf("num = %d\n", aux->num); // Imprime o valor do nó atual
                    aux = aux->prox;                // Avança para o próximo nó
                }

                printf(" Digite sua opcao: ");   // Após listar, pergunta qual a próxima ação
                scanf("%d", &op);                // Lê nova opção (2 = lista de novo; outro valor = sai do while)
            }
        }

        while (op == 3)                      // Enquanto a opção for 3 (REMOVER TOPO), repete este bloco
        {
            if (topo != NULL)                // Se a pilha tem pelo menos um nó
            {
                aux = topo;                  // aux aponta para o nó que será removido
                printf(" Numero removido: %d\n", topo->num); // Mostra o valor removido

                topo = topo->prox;           // “Desce” o topo para o próximo nó
                free(aux);                   // Libera a memória do nó removido

                printf(" Digite sua opcao: "); // Pergunta a próxima ação
                scanf("%d", &op);              // Lê nova opção (3 = remove de novo; outro valor = sai do while)
            }
            else
            {
                printf(" Pilha vazia !! ");  // Não há o que remover
                printf(" Digite sua opcao: "); // Pergunta nova ação
                scanf("%d", &op);              // Lê opção e volta ao controle dos while
            }
        }

        while (op == 4)                      // Enquanto a opção for 4 (ESVAZIAR), repete este bloco
        {
            if (topo == NULL)                // Se já está vazia
            {
                printf(" Pilha vazia !! ");  // Informa e segue o fluxo
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                aux = topo;                  // Começa no topo
                while (aux != NULL)          // Enquanto existirem nós
                {
                    topo = topo->prox;       // Avança o topo
                    free(aux);               // Libera o nó atual
                    aux = topo;              // Atualiza aux para continuar o laço
                }

                printf(" Pilha esvaziada !! "); // Confirma esvaziamento
                printf(" Digite sua opcao: ");  // Pergunta próxima ação
                scanf("%d", &op);               // Lê nova opção (4 = tentará esvaziar de novo; outro = sai do while)
            }
        }

        while (op != 5)                     // Enquanto não for 5 (SAIR), fica preso aqui
            ;                               // Laço vazio (não faz nada) — isto está “como no seu código”
    }

    return 0;                               // Encerra o programa com sucesso
}
