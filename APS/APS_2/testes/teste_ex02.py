import os


# Função para criptografar usando a cifra de Vigenère
def vigenere_encrypt(plaintext, key):
    encrypted = []
    key_length = len(key)
    for i, char in enumerate(plaintext):
        if char.isalpha():
            shift = ord(key[i % key_length].lower()) - ord('a')
            if char.islower():
                encrypted.append(chr((ord(char) - ord('a') + shift) % 26 + ord('a')))
            else:
                encrypted.append(chr((ord(char) - ord('A') + shift) % 26 + ord('A')))
        else:
            encrypted.append(char)  # Não altera caracteres não alfabéticos
    return ''.join(encrypted)


# Função para descriptografar usando a cifra de Vigenère
def vigenere_decrypt(ciphertext, key):
    decrypted = []
    key_length = len(key)
    for i, char in enumerate(ciphertext):
        if char.isalpha():
            shift = ord(key[i % key_length].lower()) - ord('a')
            if char.islower():
                decrypted.append(chr((ord(char) - ord('a') - shift) % 26 + ord('a')))
            else:
                decrypted.append(chr((ord(char) - ord('A') - shift) % 26 + ord('A')))
        else:
            decrypted.append(char)  # Não altera caracteres não alfabéticos
    return ''.join(decrypted)


# Função para salvar os dados em um arquivo
def save_to_file(filename, data):
    with open(filename, 'a') as file:  # 'a' para adicionar ao final do arquivo
        file.write(data + '\n')


# Função para ler e exibir o conteúdo do arquivo
def mostra_file_content(filename):
    if os.path.exists(filename):
        print("Conteúdo do arquivo (frases criptografadas):")
        with open(filename, 'r') as file:
            for line in file:
                # Apenas exibe as linhas que contêm "Criptografado"
                if "Criptografado" in line:
                    print(line.strip())
    else:
        print("O arquivo não existe.")




# Função principal
def main():
    key = 'i love diks and the c#'
    action = input("Digite 'c' para criptografar ou 'd' para descriptografar: ").lower().strip()

    if action == 'c':
        plaintext = input("Digite a frase a ser criptografada: ")
        encrypted = vigenere_encrypt(plaintext, key)
        print(f"Frase criptografada: {encrypted}")
        save_to_file('Hitorico.txt', f"Criptografado: {encrypted}")

    elif action == 'd':
        print('-=-' * 50)
        # Exibir o conteúdo do arquivo antes de descriptografar
        mostra_file_content('Hitorico.txt')

        ciphertext = input("Digite a frase a ser descriptografada: ")
        decrypted = vigenere_decrypt(ciphertext, key)

        # Não salva a descriptografia no arquivo
        print(f"Frase descriptografada: {decrypted}")


    else:
        print("Ação inválida!")


if __name__ == "__main__":
    main()
