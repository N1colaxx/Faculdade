import random
import os


# Função para criptografar usando a cifra de Vigenère
def vigenere_encrypt(plaintext, key):
    encrypted = []
    key_length = len(key)
    key_index = 0  # Índice da chave
    for char in plaintext:
        if char.isalpha():
            shift = ord(key[key_index % key_length].lower()) - ord('a')
            if char.islower():
                encrypted.append(chr((ord(char) - ord('a') + shift) % 26 + ord('a')))
            else:
                encrypted.append(chr((ord(char) - ord('A') + shift) % 26 + ord('A')))
            key_index += 1  # Avança no índice da chave
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
    with open(filename, 'a') as file:
        file.write(data + '\n')


# Função para ler e exibir o conteúdo do arquivo
def mostra_file_content(filename):
    if os.path.exists(filename):
        print("Conteúdo do arquivo (frases criptografadas):")
        with open(filename, 'r') as file:
            for line in file:
                print(line.strip())
    else:
        print("O arquivo não existe.")


# Função principal
def main():
    continuar = 'S'
    while continuar.upper() == 'S':
        key = 'i love diks '
        print('\n')
        action = input("Digite 'c' para criptografar ou 'd' para descriptografar: ").lower().strip()

        if action == 'c':
            print('- -' * 20)
            plaintext = input("Digite a frase a ser criptografada: ").strip()
            if not plaintext:
                print("Frase não pode estar em branco!")
                continue

            plaintext = sub_espaco(plaintext)
            encrypted = vigenere_encrypt(plaintext, key)
            print('- -' * 20)
            print(f"Frase criptografada: {encrypted}")
            save_to_file('Historico.txt', f"Criptografado: {encrypted}")

        elif action == 'd':
            print('-=-' * 50)
            mostra_file_content('Historico.txt')
            print('_-_' * 50)
            ciphertext = input("Digite a frase a ser descriptografada: ").strip()
            if not ciphertext:
                print("Frase não pode estar em branco!")
                continue

            decrypted = vigenere_decrypt(ciphertext, key)
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


if __name__ == "__main__":
    main()
