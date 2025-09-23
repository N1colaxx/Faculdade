/*
  >>> ALTERAÇÕES PARA C PURO (explicação):
  - O código original usava `new` e `delete`, que são do C++.
  - Em C puro usamos `malloc` (para alocar memória) e `free` (para liberar memória).
  - Também ajustamos a struct para C, usando `typedef struct` e dentro dela `struct FILA *prox;`.
  - Mantive a lógica igual à original (while (op == X), etc.), apenas convertendo para C.
*/

#include <stdio.h>   // funções de entrada/saída (printf, scanf)
#include <stdlib.h>  // malloc e free

// Definindo o registro que representará cada elemento da fila
typedef struct FILA
{
    int num;                // valor armazenado no nó
    struct FILA *prox;      // ponteiro para o próximo elemento da fila
} FILA;

int main(void)
{
    // Ponteiros para controlar a fila
    // "inicio" aponta para o primeiro elemento (onde remove)
    // "fim" aponta para o último elemento (onde insere)
    FILA *inicio = NULL;
    FILA *fim = NULL;

    // Ponteiro auxiliar usado em percursos e remoções
    FILA *aux;

    // Variável para armazenar a opção escolhida no menu
    int op;

    {
        // Exibe o menu inicial
        printf(" MENU DE OPCOES:\n ");
        printf(" 1 - Inserir na fila\n ");
        printf(" 2 - Consultar a fila\n ");
        printf(" 3 - Remover da fila\n ");
        printf(" 4 - Esvaziar a fila\n ");
        printf(" 5 - Sair\n ");
        printf(" Digite sua opcao: ");

        // Lê a opção escolhida
        scanf("%d", &op);

        // Valida a faixa das opções
        if (op < 1 || op > 5)
            printf(" Opcao invalida !!\n ");

        // Enquanto a opção for 1: INSERIR
        while (op == 1)
        {
            printf(" Digite o numero a ser inserido na fila: ");

            // Cria dinamicamente um novo nó (em C puro com malloc)
            FILA *novo = (FILA*) malloc(sizeof(FILA));

            // Lê o valor a ser armazenado
            scanf("%d", &novo->num);

            // Como será o último da fila, prox = NULL
            novo->prox = NULL;

            if (inicio == NULL)
            {
                // A fila estava vazia: início e fim apontam para o mesmo nó
                inicio = novo;
                fim = novo;
            }
            else
            {
                // A fila já tem elementos: adiciona no fim
                fim->prox = novo;
                fim = novo;
            }

            printf(" Numero inserido na fila !! \n");
            printf(" Digite sua opcao: ");
            scanf("%d", &op);
        }

        // Enquanto a opção for 2: CONSULTAR
        while (op == 2)
        {
            if (inicio == NULL)
            {
                // Se inicio == NULL → fila está vazia
                printf(" Fila vazia !! ");
            }
            else
            {
                // Percorre do início até o fim mostrando os números
                printf(" Consultando a fila: ");
                aux = inicio;

                while (aux != NULL)
                {
                    printf("num = %d\n", aux->num);
                    aux = aux->prox;
                }

                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Enquanto a opção for 3: REMOVER
        while (op == 3)
        {
            if (inicio != NULL) // há elementos
            {
                aux = inicio;  // aux aponta para o primeiro da fila
                printf(" Numero removido: %d\n", inicio->num);

                // O novo início é o próximo nó
                inicio = inicio->prox;

                // Libera a memória do antigo início
                free(aux);

                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                // Fila vazia: nada a remover
                printf(" Fila vazia !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Enquanto a opção for 4: ESVAZIAR
        while (op == 4)
        {
            if (inicio == NULL)
            {
                // Já está vazia
                printf(" Fila vazia !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                // Esvazia liberando todos os nós
                aux = inicio;

                while (aux != NULL)
                {
                    inicio = inicio->prox; // avança para o próximo
                    free(aux);             // libera o nó atual
                    aux = inicio;          // atualiza aux
                }

                // Depois do esvaziamento, também zera o fim
                fim = NULL;

                printf(" Fila esvaziada !! ");
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
        }

        // Laço final: só sai quando op == 5
        while (op != 5)
            ; // laço vazio → espera o usuário digitar 5
    }

    return 0;
}
