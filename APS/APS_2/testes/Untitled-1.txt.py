
# from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    # from cryptography.hazmat.backends import default_backend
    # import os

    # # Geração de chave e IV (vetor de inicialização)
    # key = os.urandom(32)  # Chave de 256 bits
    # iv = os.urandom(16)   # IV de 128 bits
    #
    # # Texto que queremos criptografar (convertido para bytes)
    # plaintext = "Este é um texto secreto".encode()  # Convertendo para bytes
    #
    # # Criptografia
    # cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    # encryptor = cipher.encryptor()
    # ciphertext = encryptor.update(plaintext) + encryptor.finalize()
    #
    # print('|==='*20)
    # print("Texto criptografado:", ciphertext)
    #
    # # Descriptografia
    # decryptor = cipher.decryptor()
    # decrypted_text = decryptor.update(ciphertext) + decryptor.finalize()
    #
    # print('-='*20)
    # print("Texto descriptografado:", decrypted_text.decode())  # Decodificando para string

from tkinter import *
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import os


def criptografar (text: str, key: bytes, iv: bytes) -> bytes:  # A seta indica o parametro da def ( -> bytes) indica q a def retorna um valor em BYTES
    cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    encryptor = cipher.encryptor()

    text_byts = text.encode('utf-8') # encode conver str para byts
    cipher_text = encryptor.update(text_byts) + encryptor.finalize()
    cipher_text = cipher_text.hex
    



def des_criptografar (cipher_text: bytes, key: bytes, iv: bytes) -> str:
    des_cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    decryptor = des_cipher.decryptor()

    clear_text = decryptor.update(cipher_text) + decryptor.finalize()
    clear_text.decode('utf-8')



def iniciar():
    print('====='*50)
    text = input('Digite aqui: ')

    key = os.urandom(32) # 256 bits
    iv = os.urandom(16) # 128 bits

    crip_text = criptografar (text, key, iv)

    print('--'*50)
    print('Texto criptografado ==> ',crip_text)

    des_text = des_criptografar(crip_text, key, iv)
    print('--'*50)
    print('Texto descriptografado ==>', des_text)


if __name__ == "__main__":  # name -> func q recebe um modulo e ver se ta sendo executado
    iniciar()




# =============================================================================




# from Crypto.Cipher import AES
# from Crypto.Random import get_random_bytes

# # Geração de chave e IV
# key = get_random_bytes(32)  # Chave de 256 bits
# iv = get_random_bytes(16)   # IV de 128 bits

# # Criptografando
# cipher = AES.new(key, AES.MODE_CBC, iv)
# plaintext = b"Texto secreto"  # duvido tirar o -> b
# ciphertext = cipher.encrypt(plaintext.ljust(16))  # Preenchimento do bloco

# # Descriptografando
# cipher_dec = AES.new(key, AES.MODE_CBC, iv)
# decrypted_text = cipher_dec.decrypt(ciphertext)

# print(f"Texto criptografado: {ciphertext}")
# print(f"Texto descriptografado: {decrypted_text.strip()}")



# =======================================================




















