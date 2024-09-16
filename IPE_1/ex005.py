



# declaração das var
desconto = str
p_desconto = 0

# a = quantidade,  b = preco
def cal_valor_total (a, b):
     return  a * b  # vai fazer a conta e retornar para v_total


lista_produtos = []

for i in range (1,4):
    print('-=-'*20)
    nome = input(f'Informe o nome do {i}° produto: '.strip())
    quantidade = int(input('Quantidade adiquirida: '.strip()))
    preco = float(input('Qual o valor unitario: '.strip()))

    # Calcular o valor total
    v_total = cal_valor_total(quantidade, preco)

    # Aplicar desconto com base na quantidade
    if quantidade <= 5:
        desconto = '2%'
        p_desconto = v_total - (v_total * (2 / 100))
    if  (quantidade > 5) and (quantidade <= 10):
        desconto = '3%'
        p_desconto = v_total - (v_total * (3 / 100))
    if quantidade > 10:
        desconto = '5%'
        p_desconto = v_total - (v_total * (5 / 100))

    produto = {
        'nome': nome,
        'quantidade': quantidade,
        'preco': preco,
        'v_total': v_total,
        'desconto': desconto,
        'p_desconto': p_desconto
    }

    lista_produtos.append(produto)


print('=='*50)
print('''   {:<15}  {:<15}  {:<15} {:<15}  {:<15}  {:<15}'''.format('Produto', 'Quantidade', 'Preço',  'Valor a Pagar',     'Desconto',     'Total a Pagar'))

for produto in lista_produtos:
    print('''   {:<15}  {:<15}  {:<15}  {:<15}  {:<15} {:<15} '''.format( produto['nome'], produto['quantidade'], produto['preco'], produto['v_total'], produto['desconto'], produto['p_desconto']))