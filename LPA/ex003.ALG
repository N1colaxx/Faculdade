Algoritmo "ex002"
//  
//  
// 
//  
// Descri��o   : Aqui voc� descreve o que o programa faz! (fun��o)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 19/09/2024
Var
// Se��o de Declara��es das vari�veis 

         n: inteiro  // n = N� de meses
         p, i, v_acumulado: real    // p = aplica��o mensal / i = taxa

Inicio
// Se��o de Comandos, procedimento, fun��es, operadores, etc... 

   escreva ("Qual o valor depositado mensalmente: ")
   leia (p)
   escreva ("A quantos meses ja est� na poupan�a: ")
   leia (n)
   escreva ("Qual foi a taxa mensal definida: ")
   leia (i)

   i <- (i / 100)
   v_acumulado <- p * (((1 + i)^n - 1) / i)
   escreval ("O Valor acumulado foi de: ", v_acumulado:10:2)

Fimalgoritmo