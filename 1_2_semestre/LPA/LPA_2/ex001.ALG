Algoritmo "asd"
// 
//  
// Descri��o   : Aqui voc� descreve o que o programa faz! (fun��o)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 14/10/2024
Var
// Se��o de Declara��es das vari�veis 
 num: vetor [1..3] de inteiro
 n: inteiro
 
soma, maior, menor:inteiro

media: real

Inicio
// Se��o de Comandos, procedimento, fun��es, operadores, etc... 
   soma <- 0
   media <- 0
   maior <- 0
   menor := 1000000000


   para n de 1 ate 3 faca
             escreva ("Digite o ", n," N�: ")
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
   escreval ("A soma �: ", soma)
   escreval ("A media �: ", media:2:2)
   escreval ("O maior �: ", maior)
   escreval ("O menor �: ", menor)

   

Fimalgoritmo