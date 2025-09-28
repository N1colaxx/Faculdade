# Simulado NP1 - Estruturas de Dados

Este simulado foi elaborado com base nas listas de exercícios fornecidas pela professora (Lista 1 e Lista 2).

---

## Questões Objetivas

**1. Relativo a um Tipo Abstrato de Dados (TAD), assinale a alternativa incorreta:**  
a) Um TAD pode ser visto como um modelo matemático que encapsula dados e procedimentos.  
b) Um TAD pode ser manipulado apenas por procedimentos internos definidos.  
c) A implementação de cada TAD deve ocupar porções bem definidas no programa.  
d) Qualquer processamento pode ser realizado por procedimentos externos ao TAD.  
e) Estruturas como dicionário (conjunto de verbetes) podem ser consideradas um TAD.  

### Responta 
-   D

---

**2. Sobre Ponteiros, assinale a alternativa correta:**  
a) Um ponteiro guarda valores numéricos.  
b) O valor de um ponteiro indica “onde” está armazenada uma variável.  
c) Ponteiros só armazenam valores em hexadecimal.  
d) Ponteiros não são usados para criar estruturas complexas.  
e) Ponteiros não podem ser usados em funções.  

### Resposta
-   A
---

**3. Sobre Listas, marque a alternativa incorreta:**  
a) Listas sequenciais armazenam elementos em posições consecutivas de memória.  
b) O tamanho máximo da lista sequencial é definido na inicialização.  
c) Inserir um elemento em A[i] desloca os outros à esquerda.  
d) Listas sequenciais permitem inserção dinâmica sem limitação de tamanho.  
e) A remoção em listas sequenciais exige movimentação de elementos.  

### Resposta
-   B
---

**4. Classifique como Pilha ou Fila:**  
I – Inserções e remoções em uma única extremidade → ____  
II – Primeiro a entrar é o primeiro a sair → ____  
III – Último a entrar é o primeiro a sair → ____  
IV – Exemplo: documentos em espera para impressão → ____  

### Resposta
-   pilha: I, III 
-   fila: II
---

**5. Considere a função:**  
```c
int Calculo(int A) {
    if (A == 1) return 1;
    else return A * Calculo(A - 1);
}
```
Essa função implementa:  
a) A^2  
b) Fatorial de A  
c) A^A  
d) A elevado ao quadrado sem recursão  
e) Função iterativa  

### Resposta 
-   B
---

## Questão Aberta

Explique a diferença entre **alocação estática, dinâmica, sequencial e encadeada**. Dê exemplos de situações em que cada uma é mais apropriada.

### Resposta

1. Alocaçao estatica
    -   Vetores ,ex: um tamanho fixo ja definido

2. Dinamica
    -   Feita em tempo de execuçao. malloc e free 
    
3. sequencial
    -   Alocada de forma quantigua, n sei um exemplo

4. encadeada
    -   Com ponteiros de referencia ao proximo nó. Uma lista ou um vetor de tamanho n definido

---

