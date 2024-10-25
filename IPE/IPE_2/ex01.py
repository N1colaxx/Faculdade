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

def encontrar_maiores_menores(pro):
    # Produto com maior e menor preço de venda
    produto_maior_preco = max(pro, key=lambda x: x['preco_venda'])
    produto_menor_preco = min(pro, key=lambda x: x['preco_venda'])

    # Produto com maior e menor estoque
    produto_maior_estoque = max(pro, key=lambda x: x['estoque'])
    produto_menor_estoque = min(pro, key=lambda x: x['estoque'])

    # Produto com maior e menor ICMS (maior ICMS > 0)
    produto_maior_icms = max(pro, key=lambda x: x['icms'])
    produto_menor_icms = min([p for p in pro if p['icms'] > 0], key=lambda x: x['icms'])

    # Produto com maior e menor lucro
    produto_maior_lucro = max(pro, key=lambda x: x['preco_venda'] - x['preco_custo'])
    produto_menor_lucro = min(pro, key=lambda x: x['preco_venda'] - x['preco_custo'])

    return {
        'maior_preco': produto_maior_preco,
        'menor_preco': produto_menor_preco,
        'maior_estoque': produto_maior_estoque,
        'menor_estoque': produto_menor_estoque,
        'maior_icms': produto_maior_icms,
        'menor_icms': produto_menor_icms,
        'maior_lucro': produto_maior_lucro,
        'menor_lucro': produto_menor_lucro
    }

def exibir_tabela(resultados):
    print(f"{'Descrição':<30} | {'Produto':<25} | {'Valor':>12}")
    print('-' * 70)
    print(f"Produto de maior preço      | {resultados['maior_preco']['nome']:<25} |  R$ {resultados['maior_preco']['preco_venda']:>5.2f}")
    print(f"Produto de menor preço      | {resultados['menor_preco']['nome']:<25} |  R$ {resultados['menor_preco']['preco_venda']:>5.2f}")
    print(f"Produto de maior estoque    | {resultados['maior_estoque']['nome']:<25} |  {resultados['maior_estoque']['estoque']:>5}")
    print(f"Produto de menor estoque    | {resultados['menor_estoque']['nome']:<25} |  {resultados['menor_estoque']['estoque']:>5}")
    print(f"Produto de maior ICMS       | {resultados['maior_icms']['nome']:<25} |  {resultados['maior_icms']['icms']:>5.2f}%")
    print(f"Produto de menor ICMS > 0   | {resultados['menor_icms']['nome']:<25} |  {resultados['menor_icms']['icms']:>5.2f}%")
    print(f"Produto de maior lucro      | {resultados['maior_lucro']['nome']:<25} |  R$ {round(resultados['maior_lucro']['preco_venda'] - resultados['maior_lucro']['preco_custo'], 2):>5.2f}")
    print(f"Produto de menor lucro      | {resultados['menor_lucro']['nome']:<25} |  R$ {round(resultados['menor_lucro']['preco_venda'] - resultados['menor_lucro']['preco_custo'], 2):>5.2f}")

# Gerar a lista de produtos
produtos = gerar_list_produto(10)

# Encontrar os produtos com maiores e menores valores
resultados = encontrar_maiores_menores(produtos)


iniciar = produtos

exibir_produtos(iniciar)
print('\n\n')
exibir_tabela(resultados)