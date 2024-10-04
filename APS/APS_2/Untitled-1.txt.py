# from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
# from cryptography.hazmat.backends import default_backend
# import os

# # Geração de chave e IV (vetor de inicialização)
# key = os.urandom(32)  # Chave de 256 bits
# iv = os.urandom(16)   # IV de 128 bits

# # Texto que queremos criptografar (convertido para bytes)
# plaintext = "Este é um texto secreto".encode()  # Convertendo para bytes

# # Criptografia
# cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
# encryptor = cipher.encryptor()
# ciphertext = encryptor.update(plaintext) + encryptor.finalize()

# print('|==='*20)
# print("Texto criptografado:", ciphertext)

# # Descriptografia
# decryptor = cipher.decryptor()
# decrypted_text = decryptor.update(ciphertext) + decryptor.finalize()

# print('-='*20)
# print("Texto descriptografado:", decrypted_text.decode())  # Decodificando para string


from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

# Geração de chave e IV
key = get_random_bytes(32)  # Chave de 256 bits
iv = get_random_bytes(16)   # IV de 128 bits 

# Criptografando
cipher = AES.new(key, AES.MODE_CBC, iv)
plaintext = b"Texto secreto"  # duvido tirar o -> b
ciphertext = cipher.encrypt(plaintext.ljust(16))  # Preenchimento do bloco

# Descriptografando
cipher_dec = AES.new(key, AES.MODE_CBC, iv)
decrypted_text = cipher_dec.decrypt(ciphertext)

print(f"Texto criptografado: {ciphertext}")
print(f"Texto descriptografado: {decrypted_text.strip()}")
