import random
import os


# Função para criptografar usando a cifra de Vigenère
def vigenere_encrypt(plaintext, key):
    encrypted = []
    key_length = len(key)
    key_index = 0  # Índice da chave
    for char in plaintext:
        if char.isalpha():
            shift = ord(key[key_index % key_length].lower()) - ord('a')  # ord -> retorna valor da tabelas ASCII
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


def sub_espaco(text):
    synbols = ['!', '@', "#", "&"]
    text = text.replace(" ", random.choice(synbols))
    return text


def restore_spaces(text, symbol):
    return text.replace(symbol, ' ')


# Função para descriptografar usando a cifra de Vigenère
def vigenere_decrypt(ciphertext, key):
    decrypted = []
    key_length = len(key)
    key_index = 0  # Índice da chave
    for char in ciphertext:
        if char.isalpha():
            shift = ord(key[key_index % key_length].lower()) - ord('a')
            if char.islower():
                decrypted.append(chr((ord(char) - ord('a') - shift) % 26 + ord('a')))
            else:
                decrypted.append(chr((ord(char) - ord('A') - shift) % 26 + ord('A')))
            key_index += 1  # Avança no índice da chave
        else:
            decrypted.append(char)  # Não altera caracteres não alfabéticos
    return ''.join(decrypted)


# Função para salvar os dados em um arquivo
def save_to_file(filename, data):
    with open(filename, 'a') as file:  # ESSE 'a' é de appdend, data é de DADO. AI esta add o arquivo com os dados
        file.write(data + '\n')


# Função para ler e exibir o conteúdo do arquivo
def mostra_file_content(filename):
    if os.path.exists(filename): # veririfica se o arquivo existe
        print("Conteúdo do arquivo (frases criptografadas):")
        with open(filename, 'r') as file: # se ele exire abre no modo 'r' de read(ler)
            for line in file:
                print(line.strip())
    else:
        print("O arquivo não existe.")


# Função principal
def start():
    continuar = 'S'
    while continuar.upper() == 'S':

        key = input('\n\nDigite a palavra-chave: ')
        if not key:
            print("     Palavra-Chave não pode estar em branco!")
            continue
        if len(key) > 128:
            print('     "ERRO"', "A palavra-chave não pode exceder 128 caracteres.")
            continue

        action = input("\nDigite 'c' para criptografar ou 'd' para descriptografar: ").lower().strip()

        if action == 'c':
            print('- -' * 20)
            plaintext = input("Digite a frase a ser criptografada: ").strip()
            if not plaintext:
                print("Frase não pode estar em branco!")
                continue
            if len(plaintext) > 128:
                print('"ERRO"', "A frase não pode exceder 128 caracteres.")
                continue

            plaintext = sub_espaco(plaintext) # def pra substituir os espaços na hora da digitação
            encrypted = vigenere_encrypt(plaintext, key) # chama a def de Criptografia e passa os parametros
            print('- -' * 20)
            print(f"Frase criptografada: {encrypted}")
            save_to_file('Historico.txt', f"Criptografado: {encrypted}")

        elif action == 'd':
            print('-=-' * 50)
            mostra_file_content('Historico.txt') # def para abrir o doc Historico.txt
            print('_-_' * 50)
            ciphertext = input("Digite a frase a ser descriptografada: ").strip()
            if not ciphertext:
                print("Frase não pode estar em branco!")
                continue

            decrypted = vigenere_decrypt(ciphertext, key) #def de Descriptografia
            # Restaura os espaços removidos
            for symbol in ['!', '@', "#", "&"]:
                decrypted = restore_spaces(decrypted, symbol)

            print('_-_' * 50)
            print(f"Frase descriptografada: {decrypted}")
            print('\n')

        else:
            print("Ação inválida!")

        continuar = input("Quer continuar S/N? ").strip().upper()
        if continuar != "S":
            print("Ok, muito obg.")
            exit()


if __name__ == "__main__": # __name__ == "__main__" -> è uma função do PYTHON essa é a sintaxe, Ela verifica se o arquivo esta sendo executado e atribui o valor de "main" para variavel/função e executa.
    start()
