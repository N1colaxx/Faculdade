from random import uniform



def gerar_list_produto(n):
    produtos = []
    for codigo in range(1, n+1):
        produto = gerar_produto(codigo)
        produtos.append(produto)
    return produtos



def gerar_produto(cod):

    nome = ('Produto {}'.format(cod))

    es_mini = uniform(10.00, 20.00)
    es_max = uniform( es_mini + 1, 100.00)
    estoque = uniform(es_mini, es_max)

    pre_custo = uniform(1.00, 10.00)
    pre_venda = pre_custo + uniform(10.00, 50.00)

    icms = uniform(0.1, 10.00)
    ipi = uniform(0.1, 5.0)

    return {
        'codigo': cod,
        'nome': nome,
        'estoque': round(estoque, 2),
        'estoque_minimo': round(es_mini, 2),
        'estoque_maximo': round(es_max, 2),
        'preco_custo': round(pre_custo, 2),
        'preco_venda': round(pre_venda, 2),
        'icms': round(icms, 2),
        'ipi': round(ipi, 2)
    }



def exibir_produtos(produtos):
    for produto in produtos:
        print('---'*20)
        print(produto)


iniciar = gerar_list_produto(5)
exibir_produtos(iniciar)