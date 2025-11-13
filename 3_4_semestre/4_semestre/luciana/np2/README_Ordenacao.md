# 🔢 Estruturas de Dados — Métodos de Ordenação

## 1. Introdução
Ordenar significa **rearranjar elementos** em ordem crescente ou decrescente.  
Facilita a **busca, recuperação e análise** de dados.

### Tipos de ordenação
- **Interna:** todos os dados cabem na memória.  
- **Externa:** dados grandes, processados em partes (disco/fita).

---

## 2. Classificação dos Métodos Internos
| Tipo | Algoritmos | Complexidade |
|------|-------------|---------------|
| **Diretos (simples)** | Inserção Direta, Seleção Direta | O(n²) |
| **Por Troca (Permutação)** | Bubble Sort, Shell Sort | O(n²) a O(n log n) |
| **Por Particionamento** | QuickSort, MergeSort, HeapSort | O(n log n) |

---

## 3. Inserção Direta
**Ideia:** Forma um bloco ordenado e insere cada novo elemento na posição correta.

**Características:**
- Simples e estável
- Boa para listas pequenas ou quase ordenadas
- Complexidade: O(n²)

**Vantagens:** rápida em listas pequenas.  
**Desvantagens:** lenta em grandes conjuntos.

---

## 4. Seleção Direta
**Ideia:** Busca o menor elemento e o coloca na posição correta.

**Características:**
- Poucos movimentos (O(n))
- Não é estável
- Complexidade: O(n²)

---

## 5. Bubble Sort
**Ideia:** Compara pares vizinhos e troca se estiver fora de ordem.  
O maior valor “borbulha” para o final.

**Complexidade:** O(n²)  
**Vantagem:** simples de implementar.  
**Desvantagem:** muito lento para grandes listas.

---

## 6. Shell Sort
Variação do método de inserção.  
Compara elementos distantes e reduz o intervalo até 1.

**Complexidade média:** O(n log n)  
**Vantagens:** eficiente para tamanhos médios.  
**Desvantagem:** resultado depende do intervalo inicial.

---

## 7. QuickSort (Ordenação Rápida)
**Baseado em:** dividir e conquistar.  
Escolhe um **pivô**, separa o vetor em duas partes (menores e maiores que o pivô), e ordena recursivamente.

**Complexidade média:** O(n log n)  
**Pior caso:** O(n²)  
**Vantagens:** muito rápido e pouco uso de memória.  
**Desvantagens:** sensível à escolha do pivô.

---

## 8. MergeSort
Divide o vetor em duas metades, ordena cada uma e **intercala (merge)**.

**Complexidade:** O(n log n)  
**Vantagens:** sempre estável.  
**Desvantagens:** exige memória auxiliar.

---

## 9. HeapSort
Usa uma **árvore binária completa (heap)**.  
O maior elemento (raiz) é trocado com o último, e o heap é reconstruído.

**Complexidade:** O(n log n)  
**Vantagens:** desempenho constante, sem memória extra.  
**Desvantagem:** implementação mais complexa.

---

## 10. Comparativo dos Métodos

| Método | Complexidade | Estável | Usa Memória Extra | Melhor uso |
|---------|--------------|---------|-------------------|-------------|
| Inserção Direta | O(n²) | ✅ | ❌ | Pequenos conjuntos |
| Seleção Direta | O(n²) | ❌ | ❌ | Registros grandes |
| Bubble Sort | O(n²) | ✅ | ❌ | Simplicidade |
| Shell Sort | O(n log n) | ❌ | ❌ | Conjuntos médios |
| QuickSort | O(n log n) | ❌ | ❌ | Rápido em geral |
| MergeSort | O(n log n) | ✅ | ✅ | Estabilidade necessária |
| HeapSort | O(n log n) | ❌ | ❌ | Desempenho fixo |

---

## 11. Dicas para Prova
- **QuickSort**: mais rápido em geral.
- **HeapSort**: mais estável no desempenho.
- **MergeSort**: único com necessidade de vetor auxiliar.
- **ShellSort**: intermediário (médio desempenho e fácil).  
- **Inserção**: ótimo para listas pequenas e quase ordenadas.
