
import random
from emoji  import emojize

emoji_list = [
     ':thumbs_up:', ':fire:', ':red_heart:', ':sun:', ':star:',
    ':rainbow:', ':balloon:', ':rocket:', ':trophy:', ':pizza:'
]

def gerar_cartela():
    # Gera uma lista de números únicos entre 0 e 99
    numeros = random.sample(range(100), 25)  # SEMPLE -> vai sorter os numeros, ai no 1 definimos o range = 0, 99 e depois o parametro para o sorteio q é 25
    matriz_cartela = []

    # Divide os 25 números em 5 linhas de 5 números
    for i in range(5):
        # indeces ->      0   1    2   3   4  5   6   7   8  9  10  11  12  13 14  15  16  17  18  19  20  21  22  23  24
        # ex-> numeros = [23, 55, 89, 67, 12, 3, 78, 45, 99, 4, 76, 51, 34, 9, 28, 33, 46, 61, 25, 80, 44, 30, 63, 74, 18]

        # i*5 é o primeiro Indice
        # (i + 1) *5 é p segundo indice
        # i * 5 => 0 * 5 = 0
        # (i + 1) * 5 => (0 + 1) * 5 = 5

        # a fatia sera-> numero[0:5] -> [23, 55, 89, 67, 12]
        linha = numeros[i * 5:  (i + 1) * 5]

        if i == 2:
            emo = random.choice(emoji_list)
            linha[2] = emojize(emo)

        matriz_cartela.append(linha)

    return matriz_cartela

def exibir_cartela(cart):
    print("Cartela de Bingo:")
    print("=" * 25)

    for linha in cart: # linhas
        for num in linha:   # N° de cada linha
            if isinstance(num, str):
                print(f"{num:^5}", end="  ")  # Centraliza a palavra 'Bingo' em 5 espaços
            else:
                print(f"{num:3d}", end="  ")  # Formata os números para ocupar 2 espaços

        print()  # Quebra de linha após cada linha da cartela
    print("=" * 25)

# Gerar e exibir a cartela de bingo
cartela = gerar_cartela()
exibir_cartela(cartela)
