



# Ler um N° e imprimir os 50 anteriores e 50 posteriores
num = float(input('Digite um Numero: '))

su = []
an = []
for i in range(0,51):
    su.append(num + i)
    an.append(num - i)
print('''
    Os 50 Anteriores de {} é: {}
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    Os 50 Sucesores de {} é: {}
'''.format(num, an, num, su))