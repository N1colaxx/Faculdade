
import random
from emoji  import emojize

emoji_list = [
     ':thumbs_up:', ':fire:', ':red_heart:', ':sun:', ':star:',
    ':rainbow:', ':balloon:', ':rocket:', ':trophy:', ':pizza:'
]

        
def gerar_cartela():
    # Define os intervalos para cada coluna do bingo
    colunas = {
        0: list(range(1, 16)),    # Coluna B (1 a 15)
        1: list(range(16, 31)),   # Coluna I (16 a 30)
        2: list(range(31, 46)),   # Coluna N (31 a 45)
        3: list(range(46, 61)),   # Coluna G (46 a 60)
        4: list(range(61, 76))    # Coluna O (61 a 75)
    }

    matriz_cartela = []

    for i in range(5):
        linha = []
        for j in range(5):
            numero = random.choice(colunas[j])  # Escolhe um número da coluna correspondente
            colunas[j].remove(numero)  # Remove o número para evitar repetição
            linha.append(numero)

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
