import random

# Gera lista aleatória de 50 números entre 0 e 499
random_list = random.sample(range(500), 50)

# Ordena a lista para cálculo da mediana
sorted_list = sorted(random_list)

# Calcula as estatísticas
valor_minimo = min(random_list)
valor_maximo = max(random_list)
media = sum(random_list) / len(random_list)

# Calcula a mediana
n = len(sorted_list)
if n % 2 == 1:
    mediana = sorted_list[n//2]
else:
    mediana = (sorted_list[n//2 - 1] + sorted_list[n//2]) / 2

# Exibe no formato EXATO que o teste espera
print(f"Media: {media:.2f}, Mediana: {mediana}, Minimo: {valor_minimo}, Máximo: {valor_maximo}")