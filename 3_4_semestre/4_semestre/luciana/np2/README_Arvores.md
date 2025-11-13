# 🌳 Estruturas de Dados — Árvores

## 1. Conceito de Árvore
Estrutura de dados **não linear** onde cada elemento (nó) pode possuir **subnós (filhos)**. Representa relações hierárquicas (ex: organogramas, sistemas de arquivos).

### Termos principais:
- **Raiz:** nó inicial.
- **Pai:** nó acima de outro.
- **Filho:** nó abaixo de outro.
- **Irmãos:** nós com o mesmo pai.
- **Folhas:** nós sem filhos.
- **Ancestrais:** nós acima de um determinado nó.
- **Descendentes:** nós abaixo de um determinado nó.
- **Nível:** distância da raiz.
- **Altura:** nível mais distante da raiz.
- **Grau:** número de subárvores de um nó.

---

## 2. Árvore Binária
Cada nó tem no máximo **dois filhos** (esquerdo e direito).

**Propriedades:**
- Grau máximo = 2  
- O grau da árvore é o maior grau entre seus nós.  
- Número máximo de nós por nível: `2^n`  
- **Árvore estritamente binária:** todos os nós têm 0 ou 2 filhos.  
- **Árvore completa:** nós com menos de dois filhos ficam no último ou penúltimo nível.  
- **Árvore cheia:** estritamente binária e completa.

---

## 3. Tipos de Percurso (Travessia)
1. **Pré-Ordem:** Raiz → Esquerda → Direita  
2. **Em Ordem (Infixa):** Esquerda → Raiz → Direita  
3. **Pós-Ordem:** Esquerda → Direita → Raiz  

**Exemplo:**
```
Pré-ordem: A, B, D, E, C, F, G
Em ordem: D, B, E, A, F, C, G
Pós-ordem: D, E, B, F, G, C, A
```

---

## 4. Propriedades Numéricas
- **Máximo de nós em um nível:** `2^n`
- **Nós de uma árvore estritamente binária:** `2n − 1`
- **Altura mínima:** `log2(n)`

---

## 5. Complexidade das Operações
| Operação  | Melhor Caso | Pior Caso |
|------------|--------------|------------|
| Busca      | O(log n)     | O(n)       |
| Inserção   | O(log n)     | O(n)       |
| Remoção    | O(log n)     | O(n)       |

---

## 6. Árvores Balanceadas (AVL)
Uma **árvore binária de busca balanceada**, onde as alturas das subárvores esquerda e direita de qualquer nó diferem no máximo de 1.

**Fator de Balanço (FB):**
```
FB = altura(esquerda) - altura(direita)
FB ∈ {-1, 0, 1}
```

**Operações:**
- Após inserção ou remoção, calcula-se o FB.  
- Se FB = ±2, aplica-se **rotação** para reequilibrar.

**Complexidade (AVL):**  
Busca, inserção e remoção → O(log n)

---

## 7. Aplicações de Árvores
- Representação hierárquica de dados (pastas, menus)
- Expressões matemáticas (árvores de expressão)
- Compiladores
- Estruturas de decisão (IA)
