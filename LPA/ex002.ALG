Algoritmo "ex002"
//
//
// Descrição   : Aqui você descreve o que o programa faz! (função)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 19/09/2024
Var
// Seção de Declarações das variáveis
         num: inteiro
         res: caracter

Inicio
// Seção de Comandos, procedimento, funções, operadores, etc...
   escreva ("Digite um Numero: ")
   Leia (num)
   Se (num % 2 = 0) entao
      escreval ("Este numero é valido: ", num)
      num := num + 1
      escreval ("Quer ir novamente? S/N")
      leia (res)
           Se (res = "S") ou (res = "s") entao
              escreva ("Digite um Numero: ")
              leia (num)
           Senão
                escreva ("OK, muito obg!")
           FimSe
   senao
         escreval ("Este numero é invalido: ", num)
         escreval ("Quer ir novamente? S/N")
         leia (res)
            Se (res = "S") ou (res = "s") entao
              leia (num)
            Senão
                escreva ("OK, muito obg!")
            FimSe
   FimSE




Fimalgoritmo