Algoritmo "semnome"
// 
//  
// Descri��o   : Aqui voc� descreve o que o programa faz! (fun��o)
// Autor(a)    : Nome do(a) aluno(a)
// Data atual  : 14/10/2024
Var
// Se��o de Declara��es das vari�veis 
m1: vetor [1..2, 1..2] de inteiro
m2: vetor [1..2, 1..2] de inteiro
lin, col: inteiro

Inicio
// Se��o de Comandos, procedimento, fun��es, operadores, etc... 

   Escreval ("Valores da Primeira Matriz")
   Para lin de 1 ate 2 faca
        para col de 1 ate 2 faca
             escreva ("Digite o valor da Linha ", lin," e Coluna ", col," : ")
             leia (m1[lin, col])
        fimpara
   fimpara
   
   Escreval ("==============================================")
   Escreval ("Valores da Segunda Matriz")
      Para lin de 1 ate 2 faca
        para col de 1 ate 2 faca
             escreva ("Digite o valor da Linha ", lin," e Coluna ", col," : ")
             leia (m2[lin, col])
        fimpara
   fimpara
   
   Escreval ("_____________________")
   Escreval ("Primeira Matriz")
      Para lin de 1 ate 2 faca
        para col de 1 ate 2 faca
             escreva (m1[lin, col])
        fimpara
        escreval
   fimpara
   
   Escreval ("_____________________")
   Escreval ("Segunda Matriz")
      Para lin de 1 ate 2 faca
        para col de 1 ate 2 faca
             escreva (m2[lin, col])
        fimpara
        escreval
   fimpara
   
   Escreval("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
   Escreval("Matriz resultante da soma da 2 matrizes")
   Para lin de 1 ate 2 faca
        para col de 1 ate 2 faca
             escreva (m1[lin, col] + m2[lin,col])
        fimpara
        escreval
   fimpara

Fimalgoritmo