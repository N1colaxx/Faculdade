algoritmo "CadastroMunicipios"
var
   nome: caractere
   t_eleitores, v_branco, v_nulo, v_validos: inteiro
   i: inteiro
   p_branco, p_nulo, p_valido: real

   max_branco, max_nulo, max_valido, max_eleitores: inteiro
   nome_max_branco, nome_max_nulo, nome_max_valido, nome_max_eleitores: caractere

inicio
   // Inicializa os valores máximos
   max_branco <- 0
   max_nulo   <- 0
   max_valido  <- 0
   max_eleitores <- 0

   para i de 1 ate 1 passo 1 faca
      escreval("")
      escreval("")
      escreval("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=")
      escreval("")
     
     escreva("Digite o nome do ", i, "º município: ")
     leia(nome)

     escreva("Digite o número de votos em branco: ")
     leia(v_branco)

     escreva("Digite o número de votos nulos: ")
     leia(v_nulo)

     escreva("Digite o número de votos válidos: ")
     leia(v_validos)

     t_eleitores <-  v_branco + v_nulo + v_validos

     // Cálculo dos percentuais
     p_branco := (v_branco  / t_eleitores) * 100
     p_nulo   := (v_nulo    / t_eleitores) * 100
     p_valido := (v_validos / t_eleitores) * 100

     // Impressão dos resultados
     Escreval("===============================================")
     Escreval("   Tabela 1                                    ")
     Escreval("===============================================")
     escreval("Municipio: ", nome)
     escreval("   Totais                          |    Quantidade                       |      Porcentagem")
     escreval("   eleitores                       |   ", t_eleitores, "                 |             100%")
     escreval("   Votos Branco                    |   ", v_branco, "                    |   ", p_branco:0:0, "%")
     escreval("   Votos Nulo                      |   ", v_nulo, "                      |   ", p_nulo:0:0, "%")
     escreval("   Votos Valido                    |   ", v_validos, "                   |   ", p_valido:0:0, "%")
     escreval()

     // Atualiza os máximos
     Se v_branco > max_branco entao
        max_branco <- v_branco
        nome_max_branco <- nome
     fimSe

     Se v_nulo > max_nulo entao
        max_nulo <- v_nulo
        nome_max_nulo <- nome
     fimSe

     Se v_validos > max_valido entao
        max_valido <- v_validos
        nome_max_valido <- nome
     fimSe

     Se t_eleitores > max_eleitores entao
        max_eleitores <- t_eleitores
        nome_max_eleitores <- nome
     fimSe
   fimpara

   // Impressão dos resultados máximos
   Escreval("===============================================")
   Escreval("   Tabela 2                                    ")
   Escreval("===============================================")
   escreval("   Totais              |  Nome               |    Quantidade              |      Porcentagem")
   escreval("   eleitores           |  ",  nome_max_eleitores,            "                   |", max_eleitores, "                      |             100%")
   escreval("   Votos Branco        |  ",  nome_max_branco,               "                   |", max_branco,         "                               |", (max_branco * 100 / max_eleitores):10:0, "%")
   escreval("   Votos Nulo          |  ",  nome_max_nulo,                 "                   |", max_nulo,           "                               |", (max_nulo * 100 / max_eleitores):10:0,   "%")
   escreval("   Votos Valido        |  ",  nome_max_valido,               "                   |", max_valido,         "                              |", (max_valido * 100 / max_eleitores):10:0, "%")
   escreval()
fimalgoritmo
