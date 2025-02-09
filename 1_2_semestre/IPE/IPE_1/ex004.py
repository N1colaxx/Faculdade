#  Faça um algoritmo que cadastre 5 municípios, contendo os seguintes dados para cada:
# nome, o total de eleitores, o número de votos em branco, o número de votos nulos e o número de
# votos válidos.
# Calcular e imprimir de acordo com a tabela abaixo:
#
# Município: Bauru
#
# Totais            Quantidade      Porcentagem
# Eleitores             1.000           100%
# Votos em branco       250             25%
# Votos nulos           250             25%
# Votos válidos         500             50%

#               Totais                            Nome    Quantidade  Porcentagem
# Município com mais eleitores Município            A       x           y
# Município com mais votos em branco Município      B       x           y
# Município com mais votos nulos Município          C       x           y
# Município com mais votos válidos Município        D       x           y





# Inicializa variáveis para armazenar os dados dos municípios com os máximos
max_eleitores = {'nome': '', 'total': 0}
max_brancos = {'nome': '', 'total': 0}
max_nulos = {'nome': '', 'total': 0}
max_validos = {'nome': '', 'total': 0}

# a = brancos      b = nulos    c = validos     d = eleitores
def calcular_porcentagem (a, b, c, d):
    a = (a / d) * 100
    b = (b / d) * 100
    c = (c / d) * 100
    return  a, b, c


# Lista para armazenar os dados dos Municipios
municipios = []

# Cadastro dos Municipios
for i in range (1,3):
    print('\n','-=-'*5, 'Cadastro de Municipios', '-=-'*5)
    nome = input('Informe o Nome do {}° Municipio: '.format(i))
    brancos   = int(input('Informe o Numero de votos em branco: '.strip()))
    nulos     = int(input('Informe o Numero de votos nulos: '.strip()))
    validos   = int(input('Informe o numero de votos validos: '.strip()))

    eleitores = (brancos + nulos + validos)

    # chama a função para calcular a porcentage,
    # onde (P_brancos = brancos)  (P_nulos = nulos) (P_validos_validos)
    p_brancos, p_nulos, p_validos = calcular_porcentagem(brancos, nulos, validos, eleitores)
    # m_brancos, m_nulos, m_validos, m_eleitores = calcular_maiximos(brancos, nulos, validos, eleitores)

    # Armazena os dados do munipio em um dicionario
    municipio = {
        'nome': nome,
        'eleitores': eleitores,
        'brancos': brancos,
        'nulos': nulos,
        'validos': validos,

        'p_brancos': p_brancos,
        'p_nulos': p_nulos,
        'p_validos': p_validos,
    }

    municipios.append(municipio)


# Printar a primeira table
for municipio in municipios:
    print('-=-'*50)
    print('-------- Primeira TABELA -------------')
    print(f"\nMunicípio: {municipio['nome']}")
    print(f"{'Totais':<20} {'Quantidade':<15} {'Porcentagem':<15}")
    print(f"Eleitores            {municipio['eleitores']:<15} 100%")
    print(f"Votos em branco      {municipio['brancos']:<15} {municipio['p_brancos']:.2f}%")
    print(f"Votos nulos          {municipio['nulos']:<15} {municipio['p_nulos']:.2f}%")
    print(f"Votos válidos        {municipio['validos']:<15} {municipio['p_validos']:.2f}%")




# Encontra o município com o maior número em cada categoria
for municipio in municipios:
    if municipio['eleitores'] > max_eleitores['total']:
        max_eleitores = {'nome': municipio['nome'], 'total': municipio['eleitores']}

    if municipio['brancos'] > max_brancos['total']:
        max_brancos = {'nome': municipio['nome'], 'total': municipio['brancos']}

    if municipio['nulos'] > max_nulos['total']:
        max_nulos = {'nome': municipio['nome'], 'total': municipio['nulos']}

    if municipio['validos'] > max_validos['total']:
        max_validos = {'nome': municipio['nome'], 'total': municipio['validos']}


# Calcula a porcentagem, para calcular a % ela é referente ao N° total de eleitores no caso,
# a soma de todos os eleitores da cidade ex: A=10, B=20, C=30. res= 60 é o total e dai tiramos a %
total_eleitores = sum(m['eleitores'] for m in municipios)

# SUM -> função para somar o n° de eleitores guardados em M
# M -> var temporaria criada so para guardar os eleitores e depois SUM fazer a soma disso


# Calcular a Porcentagem
p_max_brancos = (max_brancos['total'] / total_eleitores) * 100
p_max_nulos = (max_nulos['total'] / total_eleitores) * 100
p_max_validos = (max_validos['total'] / total_eleitores) * 100


# Exibe a segunda table com os resultados
print('='*80)
print('---------------- Segunda Tabela ---------------')
print('''{:<40} {:<20} {:<20} {:>20}
{:<40} {:<20} {:<20} {:>20}
{:<40} {:<20} {:<20} {:>20}
{:<40} {:<20} {:<20} {:>20}
{:<40} {:<20} {:<20} {:>20}'''.format(
    'Totais', 'Nome', 'Quantidade', 'Porcentagem',
    'Município com mais Eleitores',         max_eleitores['nome'],  max_eleitores['total'],     '100%',
    'Município com mais votos em Branco',   max_brancos['nome'],    max_brancos['total'],       f"{p_max_brancos:.0f}%",
    'Município com mais votos Nulos',       max_nulos['nome'],      max_nulos['total'],         f"{p_max_nulos:.0f}%",
    'Município com mais votos Válidos',     max_validos['nome'],    max_validos['total'],       f"{p_max_validos:.0f}%"
))