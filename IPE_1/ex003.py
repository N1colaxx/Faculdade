# Faça um algoritmo que leia a idade de uma pessoa em anos, meses e dias e imprima a idade
# em dias. Considerar ano com 365 dias e mês com 30 dias.

print('Digite a sua idade em anos meses e dias.')
ano_nas = int(input('Informe seu ano de nascimento : '))
meses = int(input('informe quantos meses: '))
dias = int(input('E quantos dias: '))

total_dias = (ano_nas * 365) + (meses * 30) + dias

print('O total de dias que vc tem é: {}'.format(total_dias))