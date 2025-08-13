# Como declarar componentes
> Slide 10

1. Declarar cada    componente
-   private Jlabel lblNome
    
    ---

2. Alocar valor no componente
    -   lblNome = JLabel ("Nome);

    ---
    
3. CFG Posicionamento
    -   lblNome.SetBounds (15, 90, 100, 25);

    ---
    
4. ADD componente ao frame
    -   Add(lblNome);

5. Gerar o frame
    -    Criar o frame em si, o construtor (ver melhor forma de carregar os recursos)

---
<br><br>

## Componentes Swing 
> Slide 21

***JLabel, JTextField, Jbutton, JCheckBox, JComboBox, JList, JPanel***


1. Jlabel
    -   Uma legenda, (igual ao sout.print)
    
    ---
    
2. JTextFiled
    -   Permite o user a digitar
    -   Texto editável ou não editável

    ---
    
3. JButton
    -   Causa um evento quando clicado com o mouse

    ---
    
3. JCheckBox
    -   Uma opçao que pode ser ou não selecionada 

    ---
    
4. JComboBox 
    -   Lista drop-down de itens, onde o user pode selecionar clicando ou digitar em uma caixa de texto 

    ---
    
5. JList
    -   Lista de itens, onde o user pode selecionar clicando em um deles

    ---
    
6. JPanel
    -   Uma area onde os itens podem ser colocados e organizados
    -   Ou uma area para desenho para img graficas


---
<br><br>

# Swing versus AWT

## Superclasses de componentes GUI leves do Swing
> Slide 26

1. Classe *Component* (pacote `java.awt`):
    -   Subclasse de Object
    -   **Declara** muitos comportamentos e atributos comuns

    ---

2. Classe *Container* (pacote `java.awt`):
    -   Subclasse de Component
    -   **Organiza** Components

    ---

3. Classe *JComponent* (pacote `javax.swing`):
    -   Subclasse de Container
    -   **Superclasse de todos os componentes Swing leves**

---
<br><br>

# Exibição de Texto e Imagens em uma janela
> Slide 30

1. Classe JFrame:
    -   A maior classe das janelas é **uma instância ou subclasse desse classe**
    -   Fornece barra de titulo
    -   BTN: Minimizar, Maximizar e Fechar

---
<br><br>

# Rotulando componentes
> Slide 31

1. Rotulo
    -   Info de texto que declara o propósito de cada componente
    -   Criadas com a classe Jlabel

---
<br><br>


# Defininco o Layout
> Slide 33

1. Organização do Container
    -   Feita com gerenciadores de layout
        -   Um dos quais é a classe `FlowLayout`
    -   CFG com metodo `SetLayout` da classe JFrame 



---
<br><br>

# Criando e anexando label1
> Slide 37, 40, 41

1. Metodo `SetToolTipeText` da classe JComponent
    -   Expecifica a dica de ferramenta

    ---

2. Metodo `add` da classe Container
    -   Adiciona um componente ao contâiner

    > Se você não adicionar explicitamente um componente GUI a um contêiner, o componente GUI não será exibido quando o contêiner aparecer na tela

    ---
    
3. Interface `Icon`
    -   Adicionado a JLabel com `setIcon`
    -   Implementado pela classe ImageIcon

    ---
    
4. Interface `SwingContains`
    -   Declara um conjunto de constantes inteiras comuns
    -   Pode ser utilizada com os metodos:
        -   SetHotizotalAligment
        -   SetVerticalAligment

    ---
    
**Outros métodos JLabel**

1.   `getTextesetText`
    -   Para configurar e recuperar o texto de um rótulo.

2.   `getIcon` e `setIcon`
    -   Para configurar e recuperar o ícone exibido no rótulo.

3.   `getHorizontalTextPosition` e  `setHorizontalTextPosition`
    -   Para configurar e recuperar a posição horizontal do texto 
    exibido no rótulo

---
<br><br>


# Criando e exibindo uma janela 
> Slide 43

**Outros métodos Jframe:**

1. `setDefaultCloseOperation`
    -   Determina como a aplicação reage quando o usuário clica no 
    -   botão de fechar.
    
2. `setSize`
    -   Especifica a largura e altura da janela.
    -   Determina se a janela é exibida (true) ou não (false)