from tkinter import *
from tkinter import messagebox

# Listas de caracteres para criptografia e descriptografia
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

msg_crip = ""  # Mensagem criptografada global


def encrypt_msg():
    global msg_crip  # Acessa a variável global para armazenar a mensagem criptografada
    list_caracter = []
    msg = entrada_mensagem.get()  # Obtém a mensagem da entrada

    try:
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

    # Criptografia da mensagem
    for letra in msg:
        if letra in abc:
            i = (abc.index(letra) + key) % len(abc)
            nova_letra = cod[i]  # Converte a letra para o símbolo correspondente
            list_caracter.append(nova_letra)

    msg_crip = ''.join(list_caracter)  # Junta os caracteres criptografados em uma string

    janela.title("Descriptografia das Mensagens")  # Altera o título da janela
    janela_des_cripto.pack_forget()  # Esconde a primeira tela
    janela_cripto.pack(padx=10, pady=10)  # Exibe a segunda tela

    # Atualiza as labels com as mensagens
    label_orientacao_j2.config(text=f"Mensagem antes de Criptografar ==> {msg}")
    label_mensagem_criptografada.config(text=f"Mensagem Criptografada ==> {msg_crip}")

    botao_voltar.pack_forget()  # Esconde o botão "Voltar" até que a descriptografia ocorra


def decrypt_msg():
    global msg_crip  # Acessa a variável global da mensagem criptografada
    list_caracter = []

    try:
        key = int(entrada_key.get())  # Usa a chave inserida para descriptografar
    except ValueError:
        messagebox.showerror("ERRO", "Chave inválida.")
        return

    if not msg_crip:
        messagebox.showerror("ERRO", "Não há mensagem criptografada para descriptografar.")
        return

    # Descriptografia da mensagem
    for letra in msg_crip:
        if letra in cod:
            i = (cod.index(letra) - key) % len(cod)  # Corrige a indexação usando a chave
            if i < len(abc):
                nova_letra = abc[i]  # Converte de volta para a letra correspondente
            else:
                nova_letra = " "  # Se o índice não for válido, adiciona um espaço
        else:
            nova_letra = letra  # Se não estiver na lista de codificação, mantém o caractere original
        list_caracter.append(nova_letra)

    msg_des_crip = ''.join(list_caracter)  # Junta os caracteres descriptografados

    # Atualiza a label com a mensagem descriptografada
    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada ==> {msg_des_crip}")
    botao_voltar.pack(pady=10)  # Exibe o botão "Voltar"


def btn_voltar():
    # Função para voltar à tela inicial
    janela.title("Criptografia das Mensagens")
    label_mensagem_criptografada.config(text="")
    label_mensagem_descriptografada.config(text="")
    janela_cripto.pack_forget()  # Esconde a tela de criptografia
    janela_des_cripto.pack(padx=10, pady=10)  # Volta para a tela inicial
    entrada_mensagem.delete(0, END)  # Limpa o campo de mensagem
    entrada_key.delete(0, END)  # Limpa o campo da chave


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

janela_des_cripto.tkraise()  # Levanta a tela inicial

janela.mainloop()  # Inicia o loop da aplicação
