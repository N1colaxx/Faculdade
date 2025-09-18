# AULA 01

<br>

### 1.	Algoritmos e a Solução de Problemas

### O que é análise de algoritmos?

É a previsão dos recursos de que o algoritmo necessitará

-	 Memória.
-	 Largura de banda de comunicação.
-	 Hardware de computação.
-	 Tempo de computação.

Envolve dois tipos de problemas distintos:

- análise de um algoritmo particular: calcular o custo de um determinado algoritmo na resolução de um problema.
- análise de uma classe de algoritmos:  determinar o algoritmo de menor custo possível para resolver um problema.

### 2.	Estruturas de Dados

Entendendo a difenreça de :
    
-   **Tipos de Dados**
-   **Tipos Abstratos de Dados**
-   **Estruturas de Dados**

    --- 
    <BR>

1. Tipo de dados

    Os tipos de dados devem ser entendidos como sendo o conjunto de valores que uma variável pode assumir ou ainda o conjunto de valores que possam ser gerados por uma função. 

    O tipo de dado é o conceito de como um computador interpreta os bits

    --- 
    <BR>

2.	Tipos Abstratos de Dados (TAD)

    Quando os dados são dispostos e manipulados de forma homogênea, constituem um tipo abstrato de dados. 

    `TAD` modelo matematico definido por:
    -   valores
    -   operadores

    Os usuários de um TAD só têm acesso às operações disponibilizadas sobre os dados.

    Um TAD é a abstração em si onde o tipo abstrato é definido e as operações associadas são específicas para a implementação de certo tipo de dado. Exemplos de TAD: **Listas, Pilhas, Filas, Árvores, etc.**

    ---
    <br>

3.	Estrutura de Dados (ED)

    Estrutura de dados é qualquer meio utilizado para armazenar e recuperar informações.

    É uma forma específica de se implementar ou representar um TAD numa linguagem de programação. Cada ED é construída a partir dos tipos básicos (int, real, char...) e/ou dos tipos estruturados (array, record) de uma linguagem de programação

    <br>
    
    **`Dados Homogêneo`**
    -   Uma estrutura de dados, que utiliza somente um tipo de dado, em sua definição é conhecida como dados homogêneos. 
    - vetores e matrizes

        ---

    **`Vetor`**
        
    -   Alocaçao Estatica
    -   Estgrutura Homogenia
    -   Alocação sequencial
    -   Posicao de momeria não liberada

        ---

    **`Matriz`**

    Uma matriz é um arranjo bidimensional ou multidimensional de alocação estática e sequencial

---
<br><br><br>




# AULA 02

<br>

### Modularização 

Quando temos um arquivo com funções que representam apenas parte da implementação de um programa completo, denominamos esse arquivo de módulo. 

Geralmente, um módulo agrupa vários tipos e funções com funcionalidades relacionadas, caracterizando assim uma finalidade bem definida.

---

### Ponteiros

-   **Ponteiros são variáveis que permitem armazenar um endereço de memória e modificar o que está armazenado naquele endereço;**
-   **Ponteiros guardam endereços de memória**


Para declarar um ponteiro temos a seguinte forma geral:   

        tipo_do_ponteiro *nome_da_variável;


É o asterisco ( *) que faz o compilador saber que aquela variável não vai guardar um valor mas sim um endereço para aquele tipo especificado. Exemplos de declarações:    

        int *pt;  // declaro um ponteiro para PT do tipo INT            
        char *temp,*pt2; // declaro um ponteiro par TEMP e PT do tipo char

        // Aqui ainda não inicializou os ponteiros somente declarou.


---

### Operadores de Ponteiros: & e * 

-   `&` devolve o valor da MEMORIA. Conhecido como:  **o endereço de**
-   `*` devolve o valor ARMAZENADO na memoria. Conhecido como: **o conteúdo de**

---

### Aritmética com ponteiros 

Estamos falando de **operações com ponteiros** e `não` de **operações com o conteúdo das variáveis** para as quais eles apontam.

1. p++;
    -   O ponteiro anda na memória,  mudamos onde o ponteiro P esta armazenado na memoria, ex: 
        
        int *P
        começo p = 10 // 10 é o valor da memoria 
        p++ 
        final p = 14 // pq 14, pois p é do tipo int e int armazena 4 bytes na memoria

2. (*p)++;
    -   aumenta o valor apontado em P, ex:

        int a = 10
        int *p
        p = &a
        (*p)++

        printf("valor final de a = %d" a) // 11

           
3. *(p++); 
    -   O ponteiro anda na memória,  mudamos onde o ponteiro P esta armazenado na memoria, ex: 

        int a = 10
        printf("inicio memoria de a = %d" &a) // 10
        int *p = &a
        *(p**)
        printf("Final memoria de a = %d" &a) // 14
    

--- 

### Vetores e Matrizes como ponteiros 





















