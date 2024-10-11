from tkinter import *
from tkinter import messagebox


abc = [
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '  # Letras e espaço
]

# Correspondente de caracteres no array cod
cod = [
    '!', '@', '#', "'", '$', '%', '&', '(', ')', '+', '=', '§', '_', '-', '£', '¢', '¬', '.', ';', ':', '/',
    '?', 'º', '^', '~', ']', '}', '[', '{', '<', '>', '|', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
    '`', '"', '*', ',', '\\', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', # Caracteres que correspondem às letras
]


key = 5
msg_crip = ""

def encrypt_msg():
    global msg_crip
    list_caracter = []
    shit = key
    msg = entrada_mensagem.get()

    if not msg:
        messagebox.showerror("ERRO.", "Por favor, digite uma mensagem.")
        return

    if len(msg) > 128:
        messagebox.showerror("ERRO", "A frase não pode exceder 128 caracteres.")
        return

    for letra in msg:
            if letra in abc:
                i = (abc.index(letra) + shit) % len(abc)
                nova_letra = cod[i]
                list_caracter.append(nova_letra)
    msg_crip = ''.join(list_caracter)

    # Mudar o título da janela para "Descriptografia das Mensagens"
    janela.title("Descriptografia das Mensagens")

    #Mudando para a segunda tela e exibindo a mensagem criptografada
    janela_des_cripto.pack_forget()  # Esconde a primeira tela
    janela_cripto.pack(padx=10, pady=10)  # Exibe a segunda tela

    label_orientacao_j2.config(text=f"Mensagem antes de Criptografar ==> {msg} ")
    label_mensagem_criptografada.config(text=f"Mensagem Criptografada ==> {msg_crip} ")

    botao_voltar.pack_forget()  # Deixa o botão "Voltar" oculto até descriptografar

    return msg_crip

# Função para descriptografar a mensagem
def decrypt_msg():
    global msg_crip
    list_caracter = []
    shit = key

    if msg_crip is None:
        messagebox.showerror("ERRO", "Não há mensagem criptografada para descriptografar.")
        botao_voltar.pack(pady=10)
        return


    for letra in msg_crip:
        if letra in cod:
            i = (cod.index(letra) - shit) % len(cod)
            nova_letra = abc[i] if i < len(abc) else ' '
            list_caracter.append(nova_letra)
    msg_des_crip = ''.join(list_caracter)


    label_mensagem_descriptografada.config(text=f"Mensagem Descriptografada ==> {msg_des_crip} ")
    botao_voltar.pack(pady=10)  # Mostra o botão "Voltar" após descriptografar


def btn_voltar():
    # Mudar o título da janela de volta para "Criptografia das Mensagens"
    janela.title("Criptografia das Mensagens")

    label_mensagem_criptografada.config(text="")
    label_mensagem_descriptografada.config(text="")
    janela_cripto.pack_forget()  # Esconde a segunda tela
    janela_des_cripto.pack(padx=10, pady=10)  # Volta a exibir a primeira tela
    entrada_mensagem.delete(0, END)


# Criar a janela principal
janela = Tk()
janela.title("Criptografia das Mensagem")


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
