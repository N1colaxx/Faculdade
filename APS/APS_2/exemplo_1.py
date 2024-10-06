from tkinter import *
from tkinter import messagebox
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import padding
import os
from typing import Optional



key: Optional[bytes] = None
iv: Optional[bytes] = None
mensagem_criptografada: Optional[bytes] = None


def criptografar_mensagem():
    global  key, iv, mensagem_criptografada

    mensagem = entrada_mensagem.get()

    if not mensagem :
        messagebox.showerror("ERRO.", "Por favor, digite uma mensagem.")
        return
    
    if len(mensagem) > 128:
        messagebox.showerror("ERRO", "A frase não pode exceder 128 caracteres.")
        return

    else:
        # Criptografando a mensagem
        key = os.urandom(32)
        iv = os.urandom(16)
        cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
        encryptor = cipher.encryptor()

        padder = padding.PKCS7(algorithms.AES.block_size).padder()
        padded_data = padder.update(mensagem.encode()) + padder.finalize()

        mensagem_criptografada = encryptor.update(padded_data) + encryptor.finalize()
        mensagem_hex = mensagem_criptografada.hex()

        # Mudando para a segunda t  ela e exibindo a mensagem criptografada
        label_mensagem_criptografada.config(text=f"Mensagem Criptografada ==> {mensagem_hex}")
        janela_cripto.tkraise()
        botao_voltar.pack_forget() #deixa ele oculto


# Função para descriptografar a mensagem
def descriptografar_mensagem():
    global key, iv, mensagem_criptografada

    cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    des_encryptor = cipher.decryptor()

    mensagem_descriptografada = des_encryptor.update(mensagem_criptografada) + des_encryptor.finalize()

    unpadder = padding.PKCS7(algorithms.AES.block_size).unpadder()
    mensagem_descriptografada = unpadder.update(mensagem_descriptografada) + unpadder.finalize()
    
    clean_tex = mensagem_descriptografada.decode('utf-8')

    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada ==> {clean_tex}")

    botao_voltar.pack(pady=10)

def btn_voltar():
    label_mensagem_criptografada.config(text="")
    label_mensagem_descriptografada.config(text="")
    frame_entrada.tkraise()
    entrada_mensagem.delete(0, END)



# Criar a janela principal
janela = Tk()
janela.title("Criptografia de Mensagem")

# Configuração da primeira
janela_des_cripto = Frame(janela)
janela_des_cripto.grid(row=0, column=0, sticky='news')

label_orientacao_j1= Label(janela_des_cripto, text="Digite uma mensagem para criptografar ", )
label_orientacao_j1.pack(pady=10)

entrada_mensagem = Entry(janela_des_cripto, width=40, bd=3, relief="solid" )
entrada_mensagem.pack(pady=10)

botao_criptografar = Button(janela_des_cripto, text="Criptografar", command=criptografar_mensagem)
botao_criptografar.pack(pady=10)


# Configuração da segunda tela
janela_cripto = Frame(janela)
janela_cripto.grid(row=0, column=0, sticky='news')

label_orientacao_j2 = Label(janela_cripto, text="")
label_orientacao_j2.pack(pady=10)

label_mensagem_criptografada = Label(janela_cripto, text="")
label_mensagem_criptografada.pack(pady=10)

botao_descriptografar = Button(janela_cripto, text="Descriptografar", command=descriptografar_mensagem)
botao_descriptografar.pack(pady=10)

label_mensagem_descriptografada = Label(janela_cripto, text="")
label_mensagem_descriptografada.pack(pady=10)

botao_voltar = Button(janela_cripto, text='Voltar', command=btn_voltar)
botao_voltar.pack(pady=10)

# Mostrar a tela de entrada de mensagem primeiro
frame_entrada.tkraise()

# Iniciar o loop da janela
janela.mainloop()