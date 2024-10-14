Algoritmo "ex002"
//  
//  
// 
//  
// Descrição   : Aqui você descreve o que o programa faz! (função)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 19/09/2024
Var
// Seção de Declarações das variáveis 

         n: inteiro  // n = N° de meses
         p, i, v_acumulado: real    // p = aplicação mensal / i = taxa

Inicio
// Seção de Comandos, procedimento, funções, operadores, etc... 

   escreva ("Qual o valor depositado mensalmente: ")
   leia (p)
   escreva ("A quantos meses ja está na poupança: ")
   leia (n)
   escreva ("Qual foi a taxa mensal definida: ")
   leia (i)

   i <- (i / 100)
   v_acumulado <- p * (((1 + i)^n - 1) / i)
   escreval ("O Valor acumulado foi de: ", v_acumulado:10:2)

Fimalgoritmo