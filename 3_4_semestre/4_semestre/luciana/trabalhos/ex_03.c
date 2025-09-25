/*
  >>> ALTERAÇÕES PARA C PURO (explicação):
  - O código original usava `new` e `delete`, que são do C++.
  - Em C puro usamos `malloc` (para alocar memória) e `free` (para liberar memória).
  - Também ajustamos a struct para C, usando `typedef struct` e dentro dela `struct FILA *prox;`.
  - Mantive a lógica igual à original (while (op == X), etc.), apenas convertendo para C.
*/  // <-- bloco de comentário explicando a motivação da conversão

#include <stdio.h>   // funções de entrada/saída (printf, scanf)
#include <stdlib.h>  // malloc e free

// Definindo o registro que representará cada elemento da fila
typedef struct FILA       // <-- inicia a definição do tipo de nó da fila
{
    int num;              // valor armazenado no nó
    struct FILA *prox;    // ponteiro para o próximo elemento da fila
} FILA;                   // <-- cria o apelido (typedef) "FILA" para a struct

int main(void)            // ponto de entrada do programa (retorna int, sem argumentos)
{
    // Ponteiros para controlar a fila
    // "inicio" aponta para o primeiro elemento (onde remove)
    // "fim" aponta para o último elemento (onde insere)
    FILA *inicio = NULL;  // fila começa vazia: início = NULL
    FILA *fim = NULL;     // fila começa vazia: fim = NULL

    // Ponteiro auxiliar usado em percursos e remoções
    FILA *aux;            // usado para caminhar pela fila e liberar nós

    // Variável para armazenar a opção escolhida no menu
    int op;               // guarda a opção digitada pelo usuário

    {                     // bloco único (mantido como no seu código), com o menu + laços por opção
        // Exibe o menu inicial
        printf(" MENU DE OPCOES:\n ");   // imprime cabeçalho do menu
        printf(" 1 - Inserir na fila\n ");  // opção 1: enfileirar
        printf(" 2 - Consultar a fila\n "); // opção 2: listar elementos
        printf(" 3 - Remover da fila\n ");  // opção 3: desenfileirar
        printf(" 4 - Esvaziar a fila\n ");  // opção 4: remover todos os nós
        printf(" 5 - Sair\n ");             // opção 5: encerrar
        printf(" Digite sua opcao: ");      // solicita a opção

        // Lê a opção escolhida
        scanf("%d", &op);                   // lê um inteiro para "op" (sem validação de scanf)

        // Valida a faixa das opções
        if (op < 1 || op > 5)               // se estiver fora do intervalo válido
            printf(" Opcao invalida !!\n "); // avisa, mas segue o fluxo (não reexibe menu completo)

        // Enquanto a opção for 1: INSERIR
        while (op == 1)                     // entra no “modo inserir” e fica até op mudar
        {
            printf(" Digite o numero a ser inserido na fila: "); // pede o valor a enfileirar

            // Cria dinamicamente um novo nó (em C puro com malloc)
            FILA *novo = (FILA*) malloc(sizeof(FILA)); // aloca memória para um nó
            // (boa prática: checar se novo == NULL antes de usar)

            // Lê o valor a ser armazenado
            scanf("%d", &novo->num);        // grava o número digitado no campo "num" do nó

            // Como será o último da fila, prox = NULL
            novo->prox = NULL;              // o novo último elemento não aponta para ninguém

            if (inicio == NULL)             // se a fila estava vazia
            {
                // A fila estava vazia: início e fim apontam para o mesmo nó
                inicio = novo;              // início passa a ser o novo nó
                fim = novo;                 // fim também passa a ser o novo nó
            }
            else
            {
                // A fila já tem elementos: adiciona no fim
                fim->prox = novo;           // o antigo fim passa a apontar para o novo
                fim = novo;                 // atualiza o ponteiro "fim" para o novo nó
            }

            printf(" Numero inserido na fila !! \n"); // confirma a operação
            printf(" Digite sua opcao: ");            // solicita próxima ação (não reimprime o menu todo)
            scanf("%d", &op);                         // lê nova opção (se 1, insere de novo; senão sai do while)
        }

        // Enquanto a opção for 2: CONSULTAR
        while (op == 2)                     // “modo consultar”
        {
            if (inicio == NULL)             // fila vazia?
            {
                // Se inicio == NULL → fila está vazia
                printf(" Fila vazia !! ");  // informa que não há elementos
            }
            else
            {
                // Percorre do início até o fim mostrando os números
                printf(" Consultando a fila: ");  // cabeçalho da listagem
                aux = inicio;                     // começa pelo primeiro nó

                while (aux != NULL)               // percorre até encontrar NULL (fim da fila)
                {
                    printf("num = %d\n", aux->num); // imprime o valor do nó atual
                    aux = aux->prox;                // avança para o próximo nó
                }

                printf(" Digite sua opcao: ");   // pede a próxima ação
                scanf("%d", &op);                // lê nova opção (2 repete; outra sai do while)
            }
        }

        // Enquanto a opção for 3: REMOVER
        while (op == 3)                     // “modo remover” (desenfileirar do início)
        {
            if (inicio != NULL)             // há algum elemento?
            {
                aux = inicio;               // guarda o nó que será removido (o primeiro)
                printf(" Numero removido: %d\n", inicio->num); // mostra o valor removido

                // O novo início é o próximo nó
                inicio = inicio->prox;      // avança o ponteiro "inicio" uma posição

                // Libera a memória do antigo início
                free(aux);                  // libera o nó removido

                // (melhoria sugerida: se início virou NULL, ajustar fim = NULL; ver notas abaixo)

                printf(" Digite sua opcao: "); // solicita a próxima ação
                scanf("%d", &op);              // lê nova opção (3 remove de novo; outra sai do modo)
            }
            else
            {
                // Fila vazia: nada a remover
                printf(" Fila vazia !! ");  // avisa que não há elementos
                printf(" Digite sua opcao: "); // pede nova ação
                scanf("%d", &op);              // lê opção e retorna ao controle de fluxo
            }
        }

        // Enquanto a opção for 4: ESVAZIAR
        while (op == 4)                     // “modo esvaziar” (remove todos)
        {
            if (inicio == NULL)             // se já está vazia
            {
                // Já está vazia
                printf(" Fila vazia !! ");  // informa e segue o fluxo
                printf(" Digite sua opcao: ");
                scanf("%d", &op);
            }
            else
            {
                // Esvazia liberando todos os nós
                aux = inicio;               // começa pelo primeiro nó

                while (aux != NULL)         // enquanto houver nós
                {
                    inicio = inicio->prox;  // avança "inicio" para o próximo nó
                    free(aux);              // libera o nó atual
                    aux = inicio;           // atualiza aux para continuar
                }

                // Depois do esvaziamento, também zera o fim
                fim = NULL;                 // importante: com a fila vazia, fim deve ser NULL

                printf(" Fila esvaziada !! "); // confirma operação
                printf(" Digite sua opcao: ");  // solicita próxima ação
                scanf("%d", &op);               // lê nova opção
            }
        }

        // Laço final: só sai quando op == 5
        while (op != 5)                  // permanece aqui enquanto a opção for diferente de 5
            ;                            // laço vazio: “trava” até o usuário digitar 5 (conforme seu modelo)
    }

    return 0;                            // encerra o programa com sucesso
}
