Algoritmo "ex004"
// 
//  
// Descrição   : Aqui você descreve o que o programa faz! (função)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 19/09/2024
Var
// Seção de Declarações das variáveis 
   nome: caractere
   n_dep: inteiro
   
   c_renda, r_anual, r_mensal, desconto: real
   
   v_licota: real
   licota: caracter
Inicio
// Seção de Comandos, procedimento, funções, operadores, etc...
   escreva ("escreva seu nome: ")
   leia (nome)
   escreva ("N° de dependentes: ")
   leia (n_dep)
   escreva ("Qual sua renda anual: ")
   leia (r_anual)
   escreva ("Contribuintes para a renda: ")
   leia (c_renda)

   r_mensal <- r_anual / 12
   desconto <- n_dep * 600

   v_licota <- r_mensal - desconto

   Escreval ("|-------------    TABELA DA LICOTA  ------------|")
   escreval ("|_______________________________________________|")
   escreval ("| Renda Liquida                  |      Alicota |")
   escreval ("|_______________________________________________|")
   escreval ("| Até R$ 2 .000                  |      Isento  |")
   escreval ("|_______________________________________________|")
   escreval ("| De R$ 2.001 até R$ 5.000       |         5%   |")
   escreval ("|_______________________________________________|")
   escreval ("| De R$ 5.001 até R$ 10.000      |         10%  |")
   escreval ("|_______________________________________________|")
   escreval ("| Acima de R$10.000              |         15%  |")
   escreval ("|_______________________________________________|")
   
   escreval ("")
   escreval ("")
   
   se (v_licota <= 2000) entao
              licota <- "está ISENTO"
   senao
        se(v_licota < 5000) entao
                    licora <- " pagara 5% de Alicota."
        senao
             se(v_licota < 10000) entao
                         licota <- " pagara 10% de Alicota."
             senao
                  licota <- " pagara 15% de Alicota."
            fimse
        fimse
   fimSE
        escreval ("Sr. ", nome, " vc tem dependentes: ", n_dep)
        escreval ("Sua renda anual é:", r_anual, " porem a Liquida é: ", r_mensal)
        escreval ("E o N° de contribuintes de renda é: ", c_renda)
        escreval ("Por esses motivos, o Sr. ", licota)

Fimalgoritmo