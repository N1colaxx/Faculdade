# * _> serve para chamar TUDO da biblioteca
from tkinter import *


def sequencia():
    try:
        n = float(numero_entrada.get())

        su = []
        an = []
        for i in range(0, 51):
            su.append(n + i)
            an.append(n - i)

        res = f'''
            Os 50 Anteriores de  é: {an}
        f{"-="*(2**7)}
            Os 50 Sucesores de  é: {su} '''

        print(res)

        txt_sequencia["text"] = res
    except ValueError:
        txt_sequencia.config(text="Por favor, insira um número válido.")  # Caso o valor inserido não seja numérico


janela = Tk()
janela.title('Tabelas de Municipios')




txt_orientacao = Label(janela, text=" Ver a sequencia dos Numeros. \n Clique no botão")
txt_orientacao.grid(column=0, row=0)

numero_entrada = Entry(janela)
numero_entrada.grid(column=0, row=1)

button = Button(janela, text='VER sequencia.', command = sequencia)
button.grid(column=0, row=2)

txt_sequencia = Label(janela, text="")
txt_sequencia.grid(column=0, row=3)

janela.mainloop()























