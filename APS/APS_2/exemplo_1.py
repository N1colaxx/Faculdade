from tkinter import *
from tkinter import messagebox

abc = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
    'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
    'X', 'Y', 'Z', ' '
]

cod = [
    '!', '@', '#', "'", '$', '%', '&', '(', ')', '+', '=', '§', '_', '-', '£', '¢', '¬', '.', ';', ':', '/',
    '?', 'º', '^', '~', ']', '}', '[', '{', '<', '>', '|', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
    '`', '"', '*', ',', '\\', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'
]

msg_crip = ""


def encrypt_msg():
    global msg_crip
    list_caracter = []
    msg = entrada_mensagem.get()

    try: # verifica a entrada da Key
        key = int(entrada_key.get())  # Obtém a chave inserida pelo usuário
    except ValueError:
        messagebox.showerror("ERRO", "Por favor, confira se o campo CHAVE está preenchido e se a chave digitada é numérica.")
        return

    if not msg:
        messagebox.showerror("ERRO", "Por favor, digite uma mensagem.")
        return

    if len(msg) > 128:
        messagebox.showerror("ERRO", "A frase não pode exceder 128 caracteres.")
        return

    for letra in msg:
        if letra in abc:
            i = (abc.index(letra) + key) % len(abc)
            nova_letra = cod[i]
            list_caracter.append(nova_letra)
    msg_crip = ''.join(list_caracter)

    janela.title("Descriptografia das Mensagens")

    janela_des_cripto.pack_forget() # Esconde a primeira tela
    janela_cripto.pack(padx=10, pady=10)    # Exibe a segunda tela

    label_orientacao_j2.config(text=f"Mensagem antes de Criptografar ==> {msg}")
    label_mensagem_criptografada.config(text=f"Mensagem Criptografada ==> {msg_crip}")

    botao_voltar.pack_forget()  # Deixa o botão "Voltar" oculto até descriptografar


def decrypt_msg():
    global msg_crip
    list_caracter = []

    try:
        key = int(entrada_key.get())  # Usa a chave inserida pelo usuário para descriptografar
    except ValueError:
        messagebox.showerror("ERRO", "Chave inválida.")
        return

    if not msg_crip:
        messagebox.showerror("ERRO", "Não há mensagem criptografada para descriptografar.")
        return

    for letra in msg_crip:
        if letra in cod:
            i = (cod.index(letra) - key) % len(cod)
            nova_letra = abc[i]  # não precisa da verificação aqui, já que i é garantido para ser válido
        else:
            nova_letra = letra
        list_caracter.append(nova_letra)

    msg_des_crip = ''.join(list_caracter)

    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada ==> {msg_des_crip}")
    botao_voltar.pack(pady=10)


def btn_voltar():
    janela.title("Criptografia das Mensagens")
    label_mensagem_criptografada.config(text="")
    label_mensagem_descriptografada.config(text="")
    janela_cripto.pack_forget()
    janela_des_cripto.pack(padx=10, pady=10)
    entrada_mensagem.delete(0, END)
    entrada_key.delete(0, END)


# Criar a janela principal
janela = Tk()
janela.title("Criptografia das Mensagens")

# Configuração da primeira tela
janela_des_cripto = Frame(janela)
janela_des_cripto.pack(padx=10, pady=10)

label_orientacao_j1 = Label(janela_des_cripto, text="Digite a chave e uma mensagem para criptografar")
label_orientacao_j1.pack(pady=10)

label_key = Label(janela_des_cripto, text="Chave:")
label_key.pack()
entrada_key = Entry(janela_des_cripto, width=40, bd=2, relief="solid")
entrada_key.pack(pady=10)

label_message = Label(janela_des_cripto, text="Mensagem:")
label_message.pack()
entrada_mensagem = Entry(janela_des_cripto, width=40, bd=2, relief="solid")
entrada_mensagem.pack(pady=10)

botao_criptografar = Button(janela_des_cripto, text="Criptografar", command=encrypt_msg)
botao_criptografar.pack(pady=10)

# Configuração da segunda tela
janela_cripto = Frame(janela)

label_orientacao_j2 = Label(janela_cripto, text="", bd=2, relief="solid")
label_orientacao_j2.pack(pady=10)

label_mensagem_criptografada = Label(janela_cripto, text="")
label_mensagem_criptografada.pack(pady=10)

botao_descriptografar = Button(janela_cripto, text="Descriptografar", command=decrypt_msg)
botao_descriptografar.pack(pady=10)

label_mensagem_descriptografada = Label(janela_cripto, text="", bd=1, relief="solid")
label_mensagem_descriptografada.pack(pady=10)

botao_voltar = Button(janela_cripto, text='Voltar', command=btn_voltar)
botao_voltar.pack(pady=10)

janela_des_cripto.tkraise()

janela.mainloop()

