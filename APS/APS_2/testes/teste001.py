import tkinter as tk
from tkinter import messagebox
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
import os

# Função de criptografia AES
def encrypt_aes(plaintext):
    key = os.urandom(32)  # Gera uma chave aleatória de 32 bytes (256 bits)
    iv = os.urandom(16)  # Vetor de inicialização (IV)
    cipher = Cipher(algorithms.AES(key), modes.CFB(iv))
    encryptor = cipher.encryptor()
    ciphertext = encryptor.update(plaintext.encode()) + encryptor.finalize()
    return ciphertext, key, iv

# Função de descriptografia AES
def decrypt_aes(ciphertext, key, iv):
    cipher = Cipher(algorithms.AES(key), modes.CFB(iv))
    decryptor = cipher.decryptor()
    plaintext = decryptor.update(ciphertext) + decryptor.finalize()
    return plaintext.decode()

# Função que será chamada ao clicar no botão de criptografia AES
def on_encrypt_aes():
    text = entry.get()
    if len(text) > 128:
        messagebox.showerror("Erro", "A frase não pode exceder 128 caracteres.")
        return

    global ciphertext, key, iv
    ciphertext, key, iv = encrypt_aes(text)

    label_ciphertext.config(text=f"Criptografado: {ciphertext}")
    btn_decrypt.pack()
    btn_clear.pack()

# Função que será chamada ao clicar no botão de descriptografia
def on_decrypt():
    plaintext = decrypt_aes(ciphertext, key, iv)
    label_plaintext.config(text=f"Descriptografado: {plaintext}")
    label_plaintext.pack()

# Função para limpar a tela e permitir uma nova digitação
def on_clear():
    entry.delete(0, tk.END)
    label_ciphertext.config(text="")
    label_plaintext.config(text="")
    btn_decrypt.pack_forget()
    btn_clear.pack_forget()
    label_plaintext.pack_forget()

# Janela principal
root = tk.Tk()
root.title("Criptografia Simétrica")

# Label de entrada
label_input = tk.Label(root, text="Digite aqui:")
label_input.pack(pady=5)

# Entrada de texto destacada
entry = tk.Entry(root, width=50, font=('Arial', 14), bd=3, relief="solid")
entry.pack(pady=10)

# Botão de criptografia AES
btn_encrypt_aes = tk.Button(root, text="Criptografar AES", command=on_encrypt_aes)
btn_encrypt_aes.pack(pady=5)

# Label para exibir o texto criptografado
label_ciphertext = tk.Label(root, text="")
label_ciphertext.pack(pady=5)

# Botão de descriptografar (só aparece após a criptografia)
btn_decrypt = tk.Button(root, text="Descriptografar", command=on_decrypt)
btn_decrypt.pack_forget()

# Botão para limpar a tela
btn_clear = tk.Button(root, text="Limpar", command=on_clear)
btn_clear.pack_forget()

# Label para exibir o texto descriptografado
label_plaintext = tk.Label(root, text="")
label_plaintext.pack_forget()

root.mainloop()
