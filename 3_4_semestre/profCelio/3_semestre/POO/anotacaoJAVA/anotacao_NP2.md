



### 1. Tipos primitivos:
int[] numeros = {1, 2, 3};

float[] alturas = {1.70f, 1.80f};

double[] pesos = {70.5, 80.0};

char[] letras = {'a', 'b'};

boolean[] flags = {true, false};




### 2. Tipos por referência (objetos):
String[] nomes = {"Ana", "João"};

Pessoa[] pessoas = new Pessoa[3];

Object[] qualquerCoisa = new Object[10];


`Resumo:`

→ Um array pode guardar qualquer tipo, desde que você defina o tipo corretamente na declaração.

→ Cada array é homogêneo (todos os elementos são do mesmo tipo).




### Array Mutidimensionais em Java

Um **arry multidimencional** é um **array de arrays.**

Cada elemento é acessado usando múltiplos indices (um para cada dimensão).

**🧠 Conceito:**

> Em java, um array, multidimencional é na verdade um array cujo elementos são outros arrays

**🧾 Exemplo:**

``` java
public class ArrayMultiDemo{
    public static void main(String[] args) {
        String [][] nomes = { 
            {"Mr. ", "Mrs. ", "Ms. "},
            {"Smith", "Jones"}
        };

        System.out.println(nomes[0][0] + nomes[1][0]); // Mr. Smith
        System.out.println(nomes[0][2] + nomes[1][1]); // Ms. Jones
    } 
}
```
**💡 Saída:**
``` java
Mr. Smith  
Ms. Jones
```

### Array:

**Para declarar um array**

`tipo [] nome;`  OU   `tipo nome[];`
    
Existe uma classe que auxilia a utilização de array, que está localizada no pacote  java.util.Arrays    , tem o nome de 
Arrays, e possui os seguintes métodos: 

-   `binarySearch` – permite uma pesquisa nos elementos de um determinado array ordenado, 
retornando um atributo inteiro com a posição deste elemento;  
``` java
        int[] meuArray = {22,2,242,4552,36}; 

//      Aplicando -> binarySearch 
//      OBS: para usar o binarySearch nosso array tem q estar ordenado
//      para isso usamos o metodo SORT
        System.out.println("\n");
        Arrays.sort(meuArray);
        for (int i=0; i < meuArray.length; i++){
            System.out.println("Posição ["+ i +"] = " + meuArray[i]);
        }

        System.out.println("posição do elemento 4552 : " + Arrays.binarySearch(meuArray, 2));
```

-   `equal` – permite a comparação entre dois arrays, retornando um booleao verdadeiro(true), caso os 
array sejam iguais;  
``` java
        int[] meuArray = {22,2,242,4552,36}; 

//      Vamos agora construir um objeto array idêntico ao meuArray da seguinte forma:
        int[] espelho = meuArray;
        
//      Aplicando o EQUALS, permite comparar dois arrays retornando “true” ou “false”
        System.out.println("\n");
        if(Arrays.equals(meuArray, espelho)){
            System.out.println("são IDENTICOS");
        } else{
            System.out.println(" são DIFERENTES");
        }
```
-   `fill` – realiza o preenchimento de todos os elementos de um determinado array;  
``` java
        int[] meuArray = {22,2,242,4552,36}; 

//      Aplicando FILL, preenche todos os elementos de um array com um dado valor
        Arrays.fill(espelho, 5522);
        
        System.out.println("\n");
        for(int i=0; i < espelho.length; i++){
            System.out.println("Posição["+i+"] = " + espelho[i]);
        }
```

-   `sort` – faz uma ordenação nos elementos de um determinado array;  
``` java
        int[] meuArray = {22,2,242,4552,36}; 

//      Aplicando SORT, faz a ordenação
        System.out.println("\n");
        Arrays.sort(meuArray);
        for (int i=0; i < meuArray.length; i++){
            System.out.println("Posição ["+ i +"] = " + meuArray[i]);
        }
```

-   `toString` – mostra os elementos de um determinado array.
``` java
        int[] meuArray = {22,2,242,4552,36}; 

//      Aplicando  toString(),  exibir todos elementos de um objeto array
        System.out.println("\n");
        System.out.println("Conteúdo do objeto array : " + Arrays.toString(meuArray));   
```