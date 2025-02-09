import tkinter as tk  # Importa a biblioteca tkinter para criar a interface gráfica

# Inclui letras minúsculas, maiúsculas e o espaço no array abc
abc = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '  # Letras e espaço
]

# Correspondente de caracteres no array cod
cod = [
    '!', '@', '#', "'", '$', '%', '&', '(', ')', '+', '=', '§', '_', '-', '£', '¢', '¬', '.', ';', ':', '/',
    '?', 'º', '^', '~', ']', '}', '[', '{', '<', '>', '|', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
    '`', '"', '*', ',', '\\', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'  # Caracteres que correspondem às letras
]

chave = 5  # Define a chave de rotação para a criptografia

def crip():
    novaLista = []  # Cria uma lista para armazenar os caracteres criptografados
    shift = chave  # Usa a chave de rotação
    frase = entrada.get()  # Obtém a entrada do usuário
    for letra in frase:  # Itera por cada letra na frase de entrada
        if letra in abc:  # Verifica se a letra está no conjunto de caracteres definidos
            # Calcula o novo índice, aplicando a rotação e usando módulo para não ultrapassar os limites
            indice = (abc.index(letra) + shift) % len(abc)  
            novaLetra = cod[indice]  # Substitui a letra pela correspondente no array cod
            novaLista.append(novaLetra)  # Adiciona a nova letra à lista
    novaFrase = ''.join(novaLista)  # Concatena a lista em uma string

    entrada.delete(0, tk.END)  # Limpa o campo de entrada
    entrada.insert(0, novaFrase)  # Insere a string criptografada no campo de entrada

def decrip():
    novaLista = []
    shift = chave
    frase = entrada.get()
    for letra in frase:
        if letra in cod:
            indice = (cod.index(letra) - shift) % len(cod)  
            novaLetra = abc[indice] if indice < len(abc) else ' '
            novaLista.append(novaLetra)
    novaFrase = ''.join(novaLista)

    entrada.delete(0, tk.END)  # Limpa o campo de entrada
    entrada.insert(0, novaFrase)  # Insere a string descriptografada no campo de entrada

# Configuração da janela
janela = tk.Tk()  # Cria a janela principal
janela.title("APS - Criptografia")  # Define o título da janela

# Entrada de texto
entrada = tk.Entry(janela, width=50)  # Cria um campo de entrada com largura de 50 caracteres
entrada.pack(pady=10)  # Adiciona o campo à janela com espaçamento vertical

# Botões
botao_criptografar = tk.Button(janela, text="Criptografar", command=crip)  # Cria um botão para criptografar
botao_criptografar.pack(pady=5)  # Adiciona o botão à janela com espaçamento vertical

botao_descriptografar = tk.Button(janela, text="Descriptografar", command=decrip)  # Cria um botão para descriptografar
botao_descriptografar.pack(pady=5)  # Adiciona o botão à janela com espaçamento vertical

# Iniciar a aplicação
janela.mainloop()  # Inicia o loop principal da aplicação, permitindo interação com a interface
