# Revisão NP1 - Estruturas de Dados

Este documento é um resumo teórico-prático baseado nos materiais fornecidos pela professora (Aulas 01 a 05) e nas listas de exercícios.
Ele servirá como guia de estudos para a prova, cobrindo sintaxe da linguagem C, modularização, ponteiros, listas, pilhas, filas, recursividade e outros tópicos.

---

## Aula 01 – Algoritmos, Análise e Estruturas de Dados

### Algoritmos e Solução de Problemas
- **Algoritmo**: procedimento bem definido que recebe entrada(s) e gera saída(s).
- Problemas resolvidos por algoritmos possuem **dados**, armazenados em **estruturas de dados** apropriadas.
- Linguagem de programação: conjunto de regras sintáticas e semânticas para expressar instruções.

### Análise de Algoritmos
- Mede recursos necessários: **memória, largura de banda, hardware e tempo de execução**.
- Tipos de análise:
  - **Pior caso**: maior tempo de execução.
  - **Melhor caso**: menor tempo.
  - **Caso médio**: média dos tempos possíveis.
- Modelo RAM: instruções sequenciais, semelhantes às dos computadores reais.

### Estruturas de Dados
- Dados precisam ser organizados e manipulados eficientemente.
- Diferenciar:
  - **Tipos de Dados**: inteiros, reais, booleanos, char, etc.
  - **Tipos Abstratos de Dados (TADs)**: modelo matemático com valores e operações, ex: pilha, fila.
  - **Estruturas de Dados**: implementação concreta do TAD.

### Tipos de Dados
- **Primitivos**: int, float, char, boolean.
- **Estruturados**: vetores, registros, sub-intervalos.
- **Operadores básicos**: +, -, *, /.

### Tipos Abstratos de Dados (TAD)
- Modelo matemático definido por valores e operações.
- Encapsulam representação e operações, usuário vê apenas a interface.
- Exemplo: tipo **Estudante** com atributos (nome, idade, matrícula) e operações (duplicar, validar matrícula).

---


## Aula 02 – Modularização, Ponteiros e Operadores (& e *)

### Modularização
- Programas podem ser divididos em **módulos** (arquivos separados com funções específicas).
- Cada módulo é compilado em um arquivo objeto, depois todos são ligados (linkados) em um executável.
- Vantagens:
  - Melhor organização em programas grandes.
  - Facilita teste, manutenção e reuso de código.
  - Permite reaproveitar módulos em outros projetos.

### Ponteiros em C
- **Ponteiro** é uma variável que armazena **um endereço de memória**.
- Declaração: `tipo *nome;`
  - `int *p;` → ponteiro para inteiro.
  - `char *c;` → ponteiro para caractere.
- Devem ser **inicializados antes de uso**, caso contrário apontam para lixo de memória.
- Exemplo:
  ```c
  int a = 10;
  int *p;
  p = &a;     // p recebe o endereço de a
  printf("%d", *p); // imprime 10
  ```

### Operadores de Ponteiros
- `&` → devolve o **endereço** da variável.
- `*` → devolve o **conteúdo armazenado** no endereço apontado.
- Exemplo:
  ```c
  int x = 5;
  int *px = &x;
  printf("%d", *px); // imprime 5
  ```

### Observações Importantes
- Usar ponteiros corretamente evita duplicação de dados e facilita passagem de parâmetros por referência.
- Erros comuns:
  - Usar ponteiros não inicializados.
  - Acessar memória inválida.
  - Confundir `*` (conteúdo) com multiplicação.

---


## Aula 03 – Listas e Alocação de Memória

### Listas
- Conjunto de dados organizados em ordem linear.
- Representações:
  - **Lista Estática**: usa vetores (endereços contíguos de memória).
  - **Lista Dinâmica**: usa nós encadeados (com ponteiros).

### Categorias de Alocação de Memória
- **Estática**: memória definida na compilação, tamanho fixo.
- **Dinâmica**: memória alocada em tempo de execução (`malloc`, `free`).
- **Sequencial**: elementos armazenados de forma contígua.
- **Encadeada**: elementos ligados por ponteiros.

### Lista Simplesmente Encadeada (não ordenada)
- Cada nó contém:
  - Dado (informação)
  - Ponteiro para o próximo nó
- Operações principais:
  - Criar lista (inicialmente NULL)
  - Inserir (início ou fim)
  - Consultar (percorrer com laço até NULL)
  - Remover (ajustar ponteiros e liberar memória)
  - Esvaziar (liberar todos os nós)

---


## Aula 04 – Pilhas e Filas

### Introdução
- São listas com **restrições de acesso**:
  - **Pilha (LIFO – Last In, First Out)**: último a entrar é o primeiro a sair.
  - **Fila (FIFO – First In, First Out)**: primeiro a entrar é o primeiro a sair.

### Pilha
- Operações:
  - `PUSH` (empilhar): inserir no topo.
  - `POP` (desempilhar): remover do topo.
  - `TOP`: acessar o elemento do topo sem removê-lo.
  - `EMPTY`: verificar se está vazia.
- Implementação:
  - **Estática**: com vetores.
  - **Dinâmica**: com ponteiros.
- Exemplo prático: histórico de chamadas no celular.

### Fila
- Operações:
  - Inserção no fim.
  - Remoção no início.
- Estrutura com dois ponteiros: **início** e **fim**.
- Implementação:
  - **Estática**: com vetores (necessário realocar elementos).
  - **Dinâmica**: com ponteiros encadeados.
- Exemplo prático: fila de impressão.

---
