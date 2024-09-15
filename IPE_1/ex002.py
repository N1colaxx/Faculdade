



# Faça um algoritmo que receba três números do tipo real e exiba o maior e o menor deles.



n1 = int(input('Digite o Primeiro Numero: '))
n2 = int(input('Digite o Segundo numero: '))
n3 = int(input('Digite o Terceiro numero: '))

maior = max (n1, n2, n3)
menor = min (n1, n2, n3)

print('''
    O Menor entre ({}, {}, {}) é {}
    O Maior entre ({}, {}, {}) é {}
'''.format(n1,n2,n3,menor,  n1,n2,n3,maior))