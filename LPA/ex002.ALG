Algoritmo "ex002"
//
//
// Descri��o   : Aqui voc� descreve o que o programa faz! (fun��o)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 19/09/2024
Var
// Se��o de Declara��es das vari�veis
         num: inteiro
         res: caracter

Inicio
// Se��o de Comandos, procedimento, fun��es, operadores, etc...
   escreva ("Digite um Numero: ")
   Leia (num)
   Se (num % 2 = 0) entao
      escreval ("Este numero � valido: ", num)
      num := num + 1
      escreval ("Quer ir novamente? S/N")
      leia (res)
           Se (res = "S") ou (res = "s") entao
              escreva ("Digite um Numero: ")
              leia (num)
           Sen�o
                escreva ("OK, muito obg!")
           FimSe
   senao
         escreval ("Este numero � invalido: ", num)
         escreval ("Quer ir novamente? S/N")
         leia (res)
            Se (res = "S") ou (res = "s") entao
              leia (num)
            Sen�o
                escreva ("OK, muito obg!")
            FimSe
   FimSE




Fimalgoritmo