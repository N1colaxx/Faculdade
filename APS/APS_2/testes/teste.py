from tkinter import *
from tkinter import messagebox
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
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

        mensagem_bytes = mensagem.encode()      
        mensagem_criptografada = encryptor.update(mensagem_bytes) + encryptor.finalize()
        mensagem_hex = mensagem_criptografada.hex()

        # Mudando para a segunda t  ela e exibindo a mensagem criptografada
        label_mensagem_criptografada.config(text=f"Mensagem Criptografada: {mensagem_hex}")
        janela_criptografada.tkraise()
        botao_voltar.pack_forget() #deixa ele oculto


# Função para descriptografar a mensagem
def descriptografar_mensagem():
    global key, iv, mensagem_criptografada

    cipher = Cipher(algorithms.AES(key), modes.CFB(iv), backend=default_backend())
    des_encryptor = cipher.decryptor()

    mensagem_descriptografada = des_encryptor.update(mensagem_criptografada) + des_encryptor.finalize()
    clean_tex = mensagem_descriptografada.decode('utf-8')

    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada: {clean_tex}")

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
frame_entrada = Frame(janela)
frame_entrada.grid(row=0, column=0, sticky='news')

label_orientacao = Label(frame_entrada, text="Digite uma mensagem para criptografar: ")
label_orientacao.pack(pady=10)

entrada_mensagem = Entry(frame_entrada, width=40)
entrada_mensagem.pack(pady=10)

botao_criptografar = Button(frame_entrada, text="Criptografar", command=criptografar_mensagem)
botao_criptografar.pack(pady=10)


# Configuração da segunda tela
janela_criptografada = Frame(janela)
janela_criptografada.grid(row=0, column=0, sticky='news')

label_mensagem_criptografada = Label(janela_criptografada, text="")
label_mensagem_criptografada.pack(pady=10)

botao_descriptografar = Button(janela_criptografada, text="Descriptografar", command=descriptografar_mensagem)
botao_descriptografar.pack(pady=10)

label_mensagem_descriptografada = Label(janela_criptografada, text="")
label_mensagem_descriptografada.pack(pady=10)

botao_voltar = Button(janela_criptografada, text='Voltar', command=btn_voltar)
botao_voltar.pack(pady=10)

# Mostrar a tela de entrada de mensagem primeiro
frame_entrada.tkraise()

# Iniciar o loop da janela
janela.mainloop()


#  Outro tipo de Algoritimo Cifra de Cigenere parecida com a de Cesar porem um oiuco mais forte
# 
# def cifra_vigenere(texto, chave):
#     resultado = []
#     chave_expandida = expandir_chave(texto, chave)
#     for i in range(len(texto)):
#         letra_texto = texto[i]
#         letra_chave = chave_expandida[i]
# 
#         if letra_texto.isalpha():
#             base = 65 if letra_texto.isupper() else 97
#             deslocamento = (ord(letra_texto) + ord(letra_chave) - 2 * base) % 26
#             resultado.append(chr(deslocamento + base))
#         else:
#             resultado.append(letra_texto)
# 
#     return ''.join(resultado)
# 
# 
# def decifra_vigenere(texto, chave):
#     resultado = []
#     chave_expandida = expandir_chave(texto, chave)
#     for i in range(len(texto)):
#         letra_texto = texto[i]
#         letra_chave = chave_expandida[i]
# 
#         if letra_texto.isalpha():
#             base = 65 if letra_texto.isupper() else 97
#             deslocamento = (ord(letra_texto) - ord(letra_chave)) % 26
#             resultado.append(chr(deslocamento + base))
#         else:
#             resultado.append(letra_texto)
# 
#     return ''.join(resultado)
# 
# 
# def expandir_chave(texto, chave):
#     chave_expandida = []
#     indice_chave = 0
#     for i in range(len(texto)):
#         if texto[i].isalpha():
#             chave_expandida.append(chave[indice_chave % len(chave)])
#             indice_chave += 1
#         else:
#             chave_expandida.append(texto[i])
#     return ''.join(chave_expandida)