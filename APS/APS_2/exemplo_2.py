import random
import os
from tkinter import *
from tkinter import messagebox


# Função para criptografar a mensagem
def vigenere_encrypt(plaintext, key):
    encrypted = []
    key_length = len(key)
    key_index = 0  # intera sobre cada char

    for char in plaintext:
        if char.isalpha():
            shift = ord(key[key_index % key_length].lower()) - ord('a') # ord -> retorna valor da tabelas ASCII
            #   Aqui está um exemplo prático para ilustrar:

            #   Texto: "HELLO"
            #   Chave: "KEY"

            #   Durante a criptografia:

            #   Para a letra 'H', key_index é 0, então usamos a letra da chave 'K' (deslocamento 10).
            #   Para a letra 'E', key_index agora é 1, então usamos a letra da chave 'E' (deslocamento 4).
            #   Para a letra 'L', key_index é 2, então usamos a letra da chave 'Y' (deslocamento 24).
            #   Para o próximo 'L', key_index é 3, e como o índice da chave ultrapassa seu comprimento, key_index % key_length retorna 0, reiniciando para usar a letra 'K' novamente.
            #   Para a letra 'O', key_index é 4, então usamos a letra 'E' novamente.

            if char.islower():
                encrypted.append(chr((ord(char) - ord('a') + shift) % 26 + ord('a')))
            else:
                encrypted.append(chr((ord(char) - ord('A') + shift) % 26 + ord('A')))
            key_index += 1  # Avança no índice da chave, assim passando para proscima letra a ser encriptada
        else:
            encrypted.append(char)  # Não altera caracteres não alfabéticos
    return ''.join(encrypted)


# Função para descriptografar
def vigenere_decrypt(ciphertext, key):
    decrypted = []
    key_length = len(key)
    key_index = 0

    for char in ciphertext:
        if char.isalpha():
            shift = ord(key[key_index % key_length].lower()) - ord('a')
            if char.islower():
                decrypted.append(chr((ord(char) - ord('a') - shift) % 26 + ord('a')))
            else:
                decrypted.append(chr((ord(char) - ord('A') - shift) % 26 + ord('A')))
            key_index += 1
        else:
            decrypted.append(char)
    return ''.join(decrypted)


# Função para substituir os espaços
def sub_espaco(text):
    synbols = ['!', '@', "#", "&"]
    text = text.replace(" ", random.choice(synbols))
    return text


def restore_spaces(text, symbol):
    return text.replace(symbol, ' ')


# Função para salvar os dados em um arquivo
def save_to_file(filename, data):
    with open(filename, 'a') as file:   # ESSE 'a' é de appdend, data é de DADO. AI esta add o arquivo com os dados
        file.write( data + '\n')


# Função para ler e exibir o conteúdo do arquivo
def mostra_file_content(filename):
    if os.path.exists(filename):
        with open(filename, 'r') as file:   # se ele exire abre no modo 'r' de read(ler)
            return file.readlines()
    return []


# Função para gerar a janela e validar a key, msg
def create_encrypt():
    key = entrada_key.get().strip()
    plaintext = entrada_mensagem.get().strip()

    if not key or not plaintext:
        messagebox.showerror("ERRO", "A palavra-chave e a mensagem não podem estar vazias.")
        return

    if len(key) > 128 or len(plaintext) > 128:
        messagebox.showerror("ERRO", "A palavra-chave e a mensagem não podem exceder 128 caracteres.")
        return

    plaintext = sub_espaco(plaintext)
    encrypted = vigenere_encrypt(plaintext, key)

    label_resultado.delete(1.0, END)    # deleta todoo o texto, 1.0 referece a --> Linha= 1 Coluna = 0
    label_resultado.insert(END, f"Frase criptografada: {encrypted}")    # add o novo texto no ofinaldo doc, mas como ele foi "limpo" fica no inicio 
    save_to_file('Historico.txt', f"Frase Criptografada -->   {encrypted} \n")


# Função para identificar os erros na descriptografia
def error_decrypt():
    key = entrada_key.get().strip()
    ciphertext = entrada_mensagem.get().strip()

    if not key or not ciphertext:
        messagebox.showerror("ERRO", "A palavra-chave e a mensagem não podem estar vazias.")
        return

    msg_decrypted = vigenere_decrypt(ciphertext, key)

    for symbol in ['!', '@', "#", "&"]:
        msg_decrypted = restore_spaces(msg_decrypted, symbol)

    label_resultado.delete(1.0, END)
    label_resultado.insert(END, f"Frase descriptografada: {msg_decrypted}")


# Função para mostrar o conteúdo do histórico em uma nova janela
def mostrar_historico():
    historico = mostra_file_content('Historico.txt')

    # Criar uma nova janela
    janela_historico = Toplevel(janela)
    janela_historico.title("Histórico")

    # Criar um Frame para o Text e a Scrollbar
    frame_texto = Frame(janela_historico)
    frame_texto.pack(pady=10)

    # Criar o widget Text para o histórico
    texto_historico = Text(frame_texto, bd=1, relief="solid", width=50, height=30)
    texto_historico.pack(side=LEFT)

    # Adicionar a scrollbar
    scrollbar = Scrollbar(frame_texto, command=texto_historico.yview)
    scrollbar.pack(side=RIGHT, fill=Y)

    texto_historico.config(yscrollcommand=scrollbar.set)  # Conecta a scrollbar ao Text

    # Inserir o histórico no Text
    if historico:
        texto_historico.insert(END, ''.join(historico))
    else:
        texto_historico.insert(END, "O histórico está vazio.")


# Criar a janela principal
janela = Tk()
janela.title("Criptografia Vigenère")

# Configuração da interface
label_key = Label(janela, text="Palavra-chave:")
label_key.pack(pady=10)
entrada_key = Entry(janela, width=40, bd=2, relief="solid")
entrada_key.pack(pady=10)

label_mensagem = Label(janela, text="Mensagem:")
label_mensagem.pack(pady=10)
entrada_mensagem = Entry(janela, width=40, bd=2, relief="solid")
entrada_mensagem.pack(pady=10)

# Criar um Frame para os botões
frame_botoes = Frame(janela)
frame_botoes.pack(pady=10)

botao_criptografar = Button(frame_botoes, text="Criptografar", command=create_encrypt)
botao_criptografar.pack(side=LEFT, padx=5)

botao_descriptografar = Button(frame_botoes, text="Descriptografar", command=error_decrypt)
botao_descriptografar.pack(side=LEFT, padx=5)

# Criar o Text para o resultado da criptografia/descriptografia
label_resultado = Text(janela, bd=1, relief="solid", width=50, height=10)
label_resultado.pack(pady=10)

botao_historico = Button(janela, text="Mostrar Histórico", command=mostrar_historico)
botao_historico.pack(pady=10)

janela.mainloop()
