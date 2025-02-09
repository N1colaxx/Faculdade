Algoritmo "asd"
// 
//  
// Descrição   : Aqui você descreve o que o programa faz! (função)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 14/10/2024
Var
// Seção de Declarações das variáveis 
 num: vetor [1..3] de inteiro
 n: inteiro
 
soma, maior, menor:inteiro

media: real

Inicio
// Seção de Comandos, procedimento, funções, operadores, etc... 
   soma <- 0
   media <- 0
   maior <- 0
   menor := 1000000000


   para n de 1 ate 3 faca
             escreva ("Digite o ", n," N°: ")
             leia (num[n])
   fimpara

   
   escreval("=================")
   escreval ("VETOR 1..3")

   Para n de 1 ate 3 faca
       escreva (num[n])
       
       soma <- num[n] + soma
       
       SE num[n] > maior entao
          maior <- num[n]
       fimSE
       
       SE num[n] < menor entao
          menor <- num[n]
       fimse
   fimpara
   
   media <- soma/3
   
   escreval
   escreval("=================")
   escreval ("A soma é: ", soma)
   escreval ("A media é: ", media:2:2)
   escreval ("O maior é: ", maior)
   escreval ("O menor é: ", menor)

   

Fimalgoritmo