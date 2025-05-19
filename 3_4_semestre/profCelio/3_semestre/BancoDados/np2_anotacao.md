
# Álgebra Relacional

Este documento apresenta os principais operadores da Álgebra Relacional, explicando cada um com exemplos e representações no site RELAX ([http://dbis-uibk.github.io/relax/calc/local/uibk/local/0](http://dbis-uibk.github.io/relax/calc/local/uibk/local/0)).

---

## **SELECT** → `σ`

Seleciona tuplas (linhas) que satisfazem uma condição.

**Exemplo:**
Selecionar carros do modelo "Civic":

```relational algebra
σ modelo = "Civic" (Carro)
```

---

## **PROJECT** → `π`

Seleciona apenas certas colunas (atributos) da tabela.

**Exemplo:**
Selecionar apenas a placa e o modelo dos carros:

```relational algebra
π placa, modelo (Carro)
```

---

## **UNION** → `∪`

Inclui todas as tuplas que estão em R ou em S ou em ambas. Tuplas duplicadas são eliminadas.

**Exemplo:**

```relational algebra
π modelo (Carro) ∪ π modelo (CarroAntigo)
```

---

## **União Exclusiva (diferença simétrica)** → `∪|`

Inclui tuplas que estão em R ou em S, mas não nas duas ao mesmo tempo.

**Exemplo:**

```relational algebra
(π modelo (Carro) ∪ π modelo (CarroAntigo)) - (π modelo (Carro) ∩ π modelo (CarroAntigo))
```

---

## **INTERSECTION** → `∩`

Inclui apenas as tuplas que estão em ambas as relações.

**Exemplo:**

```relational algebra
π modelo (Carro) ∩ π modelo (CarroAntigo)
```

---

## **DIFFERENCE** → `-`

Inclui as tuplas que estão em R, mas não em S.

**Exemplo:**

```relational algebra
π modelo (Carro) - π modelo (CarroAntigo)
```

---

## **PRODUTO CARTESIANO** → `⨯`

Combina todas as tuplas de R com todas as de S (junção cruzada).

**Exemplo:**

```relational algebra
Cliente ⨯ Carro
```

---

## **JOIN (Junção)** → `⨝` ou `|X|` no RELAX

Combina tuplas de duas relações baseadas em um atributo comum.

**Exemplo:**

```relational algebra
Cliente ⨝ Cliente.cpf = Locacao.cpf
```

---

## **DIVISION** → `÷`

Retorna tuplas de R que estão relacionadas com *todas* as tuplas de S.

**Exemplo (conceitual):**

```relational algebra
AlunoDisciplina ÷ DisciplinaObrigatoria
```

Significa: selecionar os alunos que cursam todas as disciplinas obrigatórias.

---

## Outros símbolos comuns:

* **Renomear** → `ρ`
* **Atribuição** → `←`
* **Ordenar** → `τ`
* **Agrupamento com agregação** → `γ`
* **E (and)** → `∧`
* **OU (or)** → `∨`
* **Negação** → `¬`
* **Igual** → `=`
* **Diferente** → `≠`
* **Menor ou igual** → `≤`
* **Maior ou igual** → `≥`
* **Divisão** → `÷`
* **Diferença** → `-`
* **Junção Externa à Esquerda** → `⟕`
* **Junção Externa à Direita** → `⟖`

---

Esse material pode ser usado para treinar no site RELAX ([http://dbis-uibk.github.io/relax/calc/local/uibk/local/0](http://dbis-uibk.github.io/relax/calc/local/uibk/local/0)), testando suas expressões de álgebra relacional com dados simples.



# Álgebra Relacional – Resumo Didático

Este material tem como base o conteúdo fornecido pelo Prof. Célio R. Castelano e aborda os principais conceitos e operações da Álgebra Relacional, utilizada na manipulação de bancos de dados relacionais.

## Conceitos Iniciais

A Álgebra Relacional é uma **linguagem procedural** que possui um conjunto de operações para manipular relações (tabelas). Cada operação resulta em uma nova relação, permitindo a composição de consultas complexas.

As operações são divididas em dois grupos:

* **Operações da teoria de conjuntos**:

  * UNIÃO
  * INTERSEÇÃO
  * DIFERENÇA
  * PRODUTO CARTESIANO

* **Operações específicas para bancos de dados relacionais**:

  * SELEÇÃO (SELECT)
  * PROJEÇÃO (PROJECT)
  * JUNÇÃO (JOIN)
  * RENOMEAÇÃO (RENAME)

A tabela de exemplo utilizada contém atributos como Nome, Sobrenome, RG, Data de Nascimento, Endereço, Sexo, Salário, RG do Supervisor e Número do Departamento.

---

## 1. Operação SELEÇÃO (SELECT)

### Objetivo:

Selecionar um subconjunto de tuplas (registros) de uma relação que satisfaça uma condição.

### Notação:

```
σ <condição> (Relação)
```

### Exemplos:

Selecionar empregados do departamento 4:

```
σ NroDepto = 4 (EMPREGADO)
```

Selecionar empregados com salário > 3000:

```
σ Salario > 3000 (EMPREGADO)
```

### Exemplo composto:

```
σ (NroDepto = 4 AND Salario > 4000) OR (NroDepto = 5 AND Salario > 3000) (EMPREGADO)
```

### Características:

* Mantém os mesmos atributos (mesmo grau da relação original).
* O número de tuplas pode ser menor ou igual.
* **É comutativa**:

  ```
  ```

σ cond1 (σ cond2 (R)) = σ cond2 (σ cond1 (R))

```
- Pode-se combinar múltiplas seleções em uma única com operadores booleanos (AND, OR, NOT).

---

## 2. Operação PROJEÇÃO (PROJECT)

### Objetivo:
Selecionar colunas específicas da tabela (atributos).

### Notação:
```

π <lista de atributos> (Relação)

```

### Exemplo:
Listar nome, sobrenome e salário dos empregados:
```

π Nome, Sobren, Salario (EMPREGADO)

```

### Resultado:
| Nome        | Sobrenome   | Salário |
|-------------|-------------|---------|
| João        | Silva       | 3000    |
| Francisco   | Souza       | 4000    |
| Alice       | Fernandes   | 2500    |
| Joana       | Pereira     | 4300    |
| Rodolfo     | Nogueira    | 3800    |

### Características:
- O número de atributos é reduzido (grau = número de colunas escolhidas).
- O número de tuplas pode diminuir (linhas duplicadas são removidas).
- **Não é comutativa** (ordem dos atributos importa).

---

## 3. Combinação de Operações e Renomeação

### Operações Aninhadas:
Selecionar empregados do departamento 5 e mostrar nome, sobrenome e salário:
```

π Nome, Sobren, Salario (σ NroDepto = 5 (EMPREGADO))

```

### Relações Intermediárias:
```

R1 ← σ NroDepto = 5 (EMPREGADO)
RESULT ← π Nome, Sobren, Salario (R1)

```

### Renomeação de Atributos:
```

TEMP ← σ NroDepto = 5 (EMPREGADO)
R(FirstName, LastName, Salary) ← π Nome, Sobren, Salario (TEMP)

```

### Resultado:
| FirstName  | LastName | Salary |
|------------|----------|--------|
| João       | Silva    | 3000   |
| Francisco  | Souza    | 4000   |
| Rodolfo    | Nogueira | 3800   |

---

## 4. Operações da Teoria dos Conjuntos (Resumo)
Essas operações seguem as propriedades da teoria de conjuntos:
- **União ( ∪ )**
- **Interseção ( ∩ )**
- **Diferença ( - )**
- **Produto Cartesiano ( x )**

Essas operações serão explicadas nas próximas seções do material do professor.

---

> **Material elaborado com base no conteúdo do Prof. Célio R. Castelano – Álgebra Relacional.**

```
