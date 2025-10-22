# Coleções - Listas 

Tem 2 tipos de de coleções:

-   1. As ordenas são as `Listas` -> (a, b, c, ...)
-   2. AS desordenadas  são os `Conjuntos`

    ---
    <br>

## Listas

1. **Comprimento** = quantidade de elementos

Duas listas são iguais somente se:

-   A quantidade de elementos for igual
-   E se os elementos em seus respectivos lugares for iguais.

Exemplo:

    1 - (1, 2)
    2 - (x, y) 


Só é igual se X = 1 e Y = 2

Para contar quantas opções de listas temos podemos usar:

-   **Principio Fundamental da Contagem**, multiplica a quantidade de Eventos X quantidade de Elementos 
-   **Fatorial**:
    -   O comprimento = quantidade de elementos
    -   Não foi permitido repetição
    -   As listas se diferem somente 
    pela posição uma das outras

<br>

# Analise Combinatorio I - Permutação Simples

Estuda a contagem e propiedade de agrupamentos, divididos em 3 principais:
-   Arranjos
-   Permutações
-   Combinações

Que podem ser formados por objetos distintos ou repetidos.

##  Permutação Simples 

È agrupamentos ordenados sem repetição, denotado pela formula `Pn = n!`

    EX:
     P = {(235), (253), (325), (352), (523), (532)}

     3! = 3 . 2 . 1 = 6 


## Arranjo Simples
> slaide 21

    An,p =  n! / (n - p)!

    Para o exemplo anterior:

    A4,2 = 4! / (4-2)!  = 24/2 = 12


## Combinação Simples
> slaide 24

São todos os subconjustos de P elementos formados a partir dos N elementos dados.

Denota-se por: 

    Cn,p  =  n! / p!(n-p)!


-   Exemplo, com par de 2:

    -       (2)
            A = ab, ac, ad, ae 
            B = ba, bc, bd, be
            C = ca, cb, cd, ce
            D = da, db, dc, de
            E = ea, eb, ec, ed

            Observe que a comissão formada por {A,B} é a mesma comissão que {B,A}. Podendo-se, assim, aplicar esse raciocínio para os demais alunos. Retirando as comissões repetidas:
            
            {A,B}, {A,C}, {A,D}, {A,E}, 
            {B,C}, {B,D}, {B,E}, 
            {C,D}, {C,E}, 
            {D,E}

-   Exemplo:

    -       Para o exemplo dado, temos:
            n = 5 (alunos disponíveis)
            p = 2 (quantidade de alunos por comissão)

            Cn,p = n! / p!(n-p)!

            C5,2 = 5! / 2!(5-2)!  = 120/2.6 = 60/6 = 10


# Binoimio de Newton
> slaide 28

Denotado por:

    (p) = Cn,p = n! / p!(n-p)! com n e N, p e N e n >= p  
    (n)