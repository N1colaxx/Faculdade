#    Temos 3 siglas para o java: JVM - JRE - JDK

###    JVM (Java Virtual Machine):
        A JVM é responsavel por interpretar e executar o codigo java compilado (bycode), 
        dessa forma o aplicativo conversa com o sistema operacional. o que possibilita 
        que aplicativos Java sejam multiplataforma.

###    JRE (Java Runtime Environment):
        JRE (Ambiente de tempo de execução Java) é composto pela JVM (máquina virtual 
        java) e pela biblioteca de classes Java, utilizadas para execução de aplicações java. 
        Essas bibliotecas são chamadas de APIs Java. Portanto, o JRE é necessário
        para que possamos executar uma aplicação java
    
###    JDK (Java Development Kit):
        JDK é o conjunto de ferramentas necessárias para realizar o desenvolvimento de 
        aplicações java, e inclui a JRE e ferramentas de programação, como: 
        javac – compilador 
        jar – empacotador 
        javadoc – ferramenta para geração de documentação 
        jdb - depurador 
    

### Tipo Primitivos Java:

**N° Inteiro:**                   
-   byte                8    bits       
-   short              16    bits      
-   int                32    bits
-   long               64    bits

**N° com virgula:**
-   float              32 bits
-   double             64 bits

**Caractere:**
-   char               16 bits

**booleano:**
-   booleano            1 bit


### Operadores logiscos:
    
    Opração         Operador 
        E               &
        Ou              |
      Negação           !
      Ou Exclusivo      ^ 

`&&` (E condicional) 
- (SE x = Verdade e somente SE y = Verdade, res = true)

`||` (OU condicional)  
- (SE x = Verdade ou y = Verdade, res = true )

`^` (OU Exclusivo logico booleano)  
- (SE x = False E y = False) res= False 
- (SE x = False E y = Verdade) res = Verdade 

    > se apenas um dos operandos for verdadeiro, mas não ambos.

`|`   
-   (OU inclusivo logico booleano) 

`&`   
-   (E logico booleano (SE x E y for iguais, res = true)

`!`  
-   (Não logico)


### Operadores de Incremento:

    Operador	    Descrição	        Ordem de Execução
    ++op	     Pré-incremento	    Incrementa antes de usar
    op++	     Pós-incremento	    Usa e depois incrementa
    --op	     Pré-decremento	    Decrementa antes de usar
    op--	     Pós-decremento	    Usa e depois decrementa


### Conversão de String

**É `possível` converter String para qualquer tipo primitivo**
-   int –> Integer.parseInt( string )
-   float –> Float.parseFloat( string )
-   double -> Double.parseFloat( string )


### Instrução de seleção:

    if(condicao){
        //executa se a CONDIÇÂO for verdae
    }

    if (condicao) {
        // Executa este bloco se a condição for verdadeira.
    } else {
        // Executa este bloco se a condição for falsa.
    }

    switch(condicao){
        case,
        case,
        defalt
    }


    Operador condicional ( ? : ):
    Único operador ternário do Java (recebe três operandos);
        variavel = (condicao) ? valor_se_true : valor_se_false;


### Laços de Repetição:
    for()

    while();

    do while();



### Array:

**Para declarar um array**

`tipo [] nome;`  OU   `tipo nome[];`
    
Existe uma classe que auxilia a utilização de array, que está localizada no pacote  java.util, tem o nome de 
Arrays, e possui os seguintes métodos: 

-   `binarySearch` – permite uma pesquisa nos elementos de um determinado array ordenado, 
retornando um atributo inteiro com a posição deste elemento;  
-   `equal` – permite a comparação entre dois arrays, retornando um booleao verdadeiro(true), caso os 
array sejam iguais;  
-   `fill` – realiza o preenchimento de todos os elementos de um determinado array;  
-   `sort` – faz uma ordenação nos elementos de um determinado array;  
-   `toString` – mostra os elementos de um determinado array.

