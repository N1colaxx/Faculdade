



# Ler um N° e imprimir os 50 anteriores e 50 posteriores

su = float
an = float

num = float(input('Digite um Numero: '))

for i in range(0,51):
    su = num + i
    an = num - i
print('''
    Os 50 Anteriores de {} é: {}
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    Os 50 Sucesores de {} é: {}
'''.format(num, an, num, su))