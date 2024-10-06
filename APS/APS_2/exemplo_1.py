from tkinter import *
from tkinter import messagebox

from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import padding
import os

from typing import Optional



key: Optional[bytes] = None
iv: Optional[bytes] = None
msg_cripto: Optional[bytes] = None


def encrypt_msg():
    global key, iv, msg_cripto

    msg = entrada_mensagem.get()

    if not msg:
        messagebox.showerror("ERRO.", "Por favor, digite uma mensagem.")
        return

    if len(msg) > 128:
        messagebox.showerror("ERRO", "A frase não pode exceder 128 caracteres.")
        return

    else:
        # Criptografando a mensagem
        key = os.urandom(32)
        iv = os.urandom(16)

        cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
        encryptor = cipher.encryptor()

        padder = padding.PKCS7(algorithms.AES.block_size).padder()
        padded_data = padder.update(msg.encode()) + padder.finalize()

        msg_cripto = encryptor.update(padded_data) + encryptor.finalize()
        msg_hex = msg_cripto.hex()

        #Mudando para a segunda tela e exibindo a mensagem criptografada
        janela_des_cripto.pack_forget()  # Esconde a primeira tela
        janela_cripto.pack(padx=10, pady=10)  # Exibe a segunda tela

        label_orientacao_j2.config(text=f"Mensagem antes de Criptografar ==> {msg} ")
        label_mensagem_criptografada.config(text=f"Mensagem Criptografada ==> {msg_hex} ")

        botao_voltar.pack_forget()  # Deixa o botão "Voltar" oculto até descriptografar


# Função para descriptografar a mensagem
def decrypt_msg():
    global key, iv, msg_cripto

    cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    des_encryptor = cipher.decryptor()

    msg_des_cripto = des_encryptor.update(msg_cripto) + des_encryptor.finalize()

    unpadder = padding.PKCS7(algorithms.AES.block_size).unpadder()
    msg_des_cripto = unpadder.update(msg_des_cripto) + unpadder.finalize()

    clean_tex = msg_des_cripto.decode('utf-8')
    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada ==> {clean_tex} ")

    botao_voltar.pack(pady=10)  # Mostra o botão "Voltar" após descriptografar


def btn_voltar():
    label_mensagem_criptografada.config(text="")
    label_mensagem_descriptografada.config(text="")
    janela_cripto.pack_forget()  # Esconde a segunda tela
    janela_des_cripto.pack(padx=10, pady=10)  # Volta a exibir a primeira tela
    entrada_mensagem.delete(0, END)


# Criar a janela principal
janela = Tk()
janela.title("Criptografia de Mensagem")


# Configuração da primeira tela
janela_des_cripto = Frame(janela)
janela_des_cripto.pack(padx=10, pady=10) # pedx -> horizontal pedy -> vertical

# Adicionar espaço à esquerda
espaco_esquerdo = Frame(janela_des_cripto, width=3)  # Largura de 3px
espaco_esquerdo.pack(side=LEFT)

label_orientacao_j1 = Label(janela_des_cripto, text="Digite uma mensagem para criptografar")
label_orientacao_j1.pack(pady=10)

entrada_mensagem = Entry(janela_des_cripto, width=40, bd=2, relief="solid")
entrada_mensagem.pack(pady=10)

botao_criptografar = Button(janela_des_cripto, text="Criptografar", command=encrypt_msg)
botao_criptografar.pack(pady=10)


# Configuração da segunda tela
janela_cripto = Frame(janela)
# Não usamos pack() aqui para esconder a segunda tela até que seja chamada

label_orientacao_j2 = Label(janela_cripto, text="", bd=2, relief="solid")
label_orientacao_j2.pack(pady=10)

label_mensagem_criptografada = Label(janela_cripto, text="")
label_mensagem_criptografada.pack(pady=10)

botao_descriptografar = Button(janela_cripto, text="Descriptografar", command=decrypt_msg)
botao_descriptografar.pack(pady=10)

label_mensagem_descriptografada = Label(janela_cripto, text=" ", bd=1, relief="solid")
label_mensagem_descriptografada.pack(pady=10)

botao_voltar = Button(janela_cripto, text='Voltar', command=btn_voltar)
botao_voltar.pack(pady=10)

# Mostrar a tela de entrada de mensagem primeiro
janela_des_cripto.tkraise()

# Iniciar o loop da janela
janela.mainloop()
