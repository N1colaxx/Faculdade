algoritmo "CadastroMunicipios"
var
   nomeMunicipio: caractere
   total_eleitores, votos_branco, votos_nulo, votos_validos: inteiro
   i: inteiro
   percentual_branco, percentual_nulo, percentual_valido: real

inicio
   // Cadastro e impressão dos dados
   para i de 1 ate 5 passo 1 faca
      escreva("Digite o nome do ", i, "º município: ")
      leia(nomeMunicipio)

      escreva("Digite o número de votos em branco para ", nomeMunicipio, ": ")
      leia(votos_branco)

      escreva("Digite o número de votos nulos para ", nomeMunicipio, ": ")
      leia(votos_nulo)

      escreva("Digite o número de votos válidos para ", nomeMunicipio, ": ")
      leia(votos_validos)
      
      total_eleitores <-  votos_branco + votos_nulo + votos_validos

      // Cálculo dos percentuais
      percentual_branco := (votos_branco / total_eleitores) * 100
      percentual_nulo := (votos_nulo / total_eleitores) * 100
      percentual_valido := (votos_validos / total_eleitores) * 100

      // Impressão dos resultados
      escreval("Municipio: ", nomeMunicipio)
      escreval("   Totais                      |    quantidade     |      porcentagem")
      escreval("   eleitores                   |   ", total_eleitores, "                 |  100%")
      escreval("   v_branco                    |   ", votos_branco, "                    |   ", percentual_branco:10:0, "%")
      escreval("   v_nulo                      |   ", votos_nulo, "                      |   ", percentual_nulo:10:0, "%")
      escreval("   v_valido                    |   ", votos_validos, "                   |   ", percentual_valido:10:0, "%")
      escreval()
   fimpara
fimalgoritmo
