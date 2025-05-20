### Java

✅ JVM (Java Virtual Machine) 
-   Executa o código Java compilado (bytecode), permitindo que o Java rode em qualquer sistema operacional.

✅ JRE (Java Runtime Environment) 
-   Contém a JVM + bibliotecas essenciais para rodar aplicações Java.

✅ JDK (Java Development Kit)
-   Inclui a JRE + ferramentas para desenvolvimento Java:

`javac` (compilador)

`jar` (empacotador)

`javadoc` (geração de documentação)

`jdb` (depurador)

### Tipos Primitivos:

    byte	    8 bits	    0
    short	    16 bits	    0
    int	        32 bits	    0
    long	    64 bits	    0L
    float	    32 bits	    0.0f
    double	    64 bits	    0.0d
    char	    16 bits	    '\u0000'
    boolean	     1 bit	    false

### Tipos por Referência

Tipos por Referência (Apontam para Objetos na Memória)

* String → String nome = "Java";

* Arrays → int[] numeros = {1, 2, 3};

* Objetos → Pessoa p = new Pessoa();

✅ Primitivos armazenam valores diretamente

✅ Referências armazenam um endereço de memória

✅ Modificar um objeto afeta todas as referências a ele

A diferença entre tipos primitivos e tipos por referência no Java está na forma como eles armazenam os dados e na maneira como são manipulados na memória.

### 🔹 Tipos Primitivos
-   ✅ Armazenam diretamente um valor na memória.

-   ✅ São mais rápidos e usam menos memória.

-   ✅ Não possuem métodos ou funcionalidades extras.

### 🔹 Tipos por Referência
-   ✅ Armazenam um endereço (referência) de um objeto na memória.

-   ✅ Podem conter vários métodos e funcionalidades.


-   ✅ Objetos compartilhados podem ser modificados por diferentes variáveis.


### 🎯 Resumo para lembrar na prova

`Primitivos` 
-   armazenam o valor direto na variável.
`Referência` 
-   armazena o endereço do objeto na memória.

> Modificar um primitivo não afeta cópias, mas modificar um objeto pode afetar todas as referências a ele. 🚀

### Operadores em Java
* ✔ Aritméticos → + - * / %
* ✔ Relacionais → == != > < >= <=
* ✔ Lógicos → && || !
* ✔ Atribuição → = += -= *= /= %=
* ✔ Incremento/Decremento → ++ --
* ✔ Ternário → condição ? verdadeiro : falso


🔹 `static` → Método/atributo pertence à classe, não ao objeto.

🔹 `void` → Indica que um método não retorna valor.

🔹 `Construtor` → Método especial que inicializa objetos, quando a class é instanciada.

🔹 `Sobrecarga de Construtores` → Vários construtores na mesma classe, com parâmetros diferentes.

🔹 `this` → Referência ao próprio objeto.

🔹 `super` → Chama um método ou construtor da superclasse (classe pai).

### Tipo de Sobrecarga	Exemplo:

**Mudança no `número` de parâmetros** 
-   somar(int, int), somar(int, int, int)

**Mudança no `tipo` dos parâmetros**	
- multiplicar(int, int), multiplicar(double, double)

**Mudança na `ordem` dos parâmetros** 
- imprimir(String, int), imprimir(int, String)

### POO Pilares:
`Abstração:`  

Repartir o problema em partes e esconder detalhes internos e expor apenas o necessário.

`Emcapsulamento: `

É o  controle de acesso usando os modificadores de acesso.

-   ✔ private → apenas à própria classe.
-   ✔ protected → Permite o acesso dentro da classe e de suas subclasses.
-   ✔ public → Permite o acesso de qualquer classe.

`Herança:`

Pertmite uma class herdar caracteristicas de outra class, Pai para Filho. 
-   comando: extends

`Polimorfismo:`

Capacidade de um metodo possuir diferentes comportamentos dependendo da implementação. 
-   sobrecarga: Mesmo nome, diferentes atributos.
-   sobrescrita: Redefinição em subclasses.

`Construtores: `
- Inicializa os obj com ou sem valores.   
- publico
- mesmo nome da class
- com ou sem parametros 
 
`Classes Abstratas`
-  Não pode ser instanciada diretamente.
-  Pode conter **métodos abstratos** (sem implementação) e **métodos concretos** (com implementação).
-   Pode ter métodos concretos (com implementação).
-   Não pode ser instanciada diretamente.
-   Usa a palavra-chave abstract.
-   É usada para definir um modelo base para outras classes.

`Interfaces` 
-   ✅ Interface → Define métodos que devem ser implementados.
-   ✅ Pode ter métodos abstratos (que devem ser implementados pelas subclasses).


### Diferença entre `Classes Abstratas` e `Interfaces` 
| Característica   |	Interface	    |  Classe Abstrata
|------------------|--------------------|---
| Métodos	       | Apenas abstratos	|   Pode ter concretos e abstratos
| Implementação	   | implements	        |   extends
| Herança múltipla | Sim	            |   Não


`Getters e Setters `
-   Getter → retorna o valor de um atributo.
-   Setter → altera o valor de um atributo.

`this, super, Overrade`

-   this → Refere-se ao próprio objeto atual da classe.
-   super → Refere-se à superclasse (classe pai) da classe atual.
-   @Override → Indica que um método está sendo sobrescrito (não sobrecarregado) da superclasse.

>Obs: **"Override"** é sobrescrita (mesmo nome, mesma assinatura, redefinido na subclasse); **"Overload"** é sobrecarga (mesmo nome, assinaturas diferentes).