# Formas de alinhamento 

### 🔹 `FlowLayout`

**Como funciona:**
-   Coloca os componentes um do lado do outro (na horizontal, como se fosse um texto), e quando não cabe mais, quebra a linha.
É o layout padrão de um JPanel.

**Exemplo visual:**
``` csharp 
[Botão1] [Botão2] [Botão3]
Se a janela ficar pequena, vira:
[Botão1] [Botão2]
[Botão3]
```

**Quando usar:**
-   Ideal para linhas simples de botões ou campos, quando você não precisa de alinhamento complexo.

### 🔹 `BoxLayout`

**Como funciona:**
-   Permite alinhar componentes em linha (X_AXIS) ou em coluna (Y_AXIS).
Ele respeita o tamanho preferido dos componentes, mas você pode usar Box.createRigidArea, Box.createHorizontalGlue, etc. para ajustar espaçamentos.

Exemplo visual:

```csharp
Vertical (Y_AXIS):

[Botão1]
[Botão2]
[Botão3]


Horizontal (X_AXIS):
[Botão1] [Botão2] [Botão3]
```
-   **Quando usar:**
-   Bom para formularios mais simples ou menus verticais/horizontais que precisam ficar bem alinhados.

### 🔹 `GridBagLayout`

**Como funciona:**

    É o mais poderoso e também o mais chato 😅.

    Ele divide o painel em uma grade (grid), mas você pode controlar quantas colunas/linhas cada componente ocupa, alinhamento dentro da célula, espaçamentos etc.

**Exemplo visual (formulário):**
```csharp
Nome:   [__________]
Email:  [__________]
Senha:  [__________] [OK]
```

Aqui você pode dizer que o campo de texto ocupa 2 colunas, e o botão só 1.

**Quando usar:**

Quando você precisa de layouts complexos, como formulários de cadastro.
É flexível, mas exige bastante configuração (GridBagConstraints).

### ⚖️ Comparação rápida

1. FlowLayout	    
    -   Muito fácil	
    -   Baixa	
    -   Coisas simples em linha
    
2. BoxLayout	    
    -   Fácil	
    -   Média	

    -   Listas verticais/horizontais bem alinhadas
3. GridBagLayout	
    -   Difícil	
    -   Muito alta	
    -   Formulários e telas mais complexas
