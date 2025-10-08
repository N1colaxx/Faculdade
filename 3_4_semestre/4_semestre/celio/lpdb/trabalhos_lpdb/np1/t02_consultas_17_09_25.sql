/* a) vendas entre 01/01/2024 e hoje, clientes de SP, ordenado por data desc */
SELECT
  v.vda_codigo,
  v.vda_data,
  p.pes_nome AS cliente,
  v.vda_total
FROM venda v
JOIN cliente c   ON c.cli_codigo = v.cli_codigo
JOIN pessoa  p   ON p.pes_codigo = c.pes_codigo
WHERE p.pes_uf = 'SP'
  AND v.vda_data BETWEEN DATE '2024-01-01' AND CURRENT_DATE
ORDER BY v.vda_data DESC;


/* b) compras com usuário cujo login contém "au", usuário ATIVO, fornecedor PJ; ordenado pelo nome fantasia */
SELECT
  co.cpr_codigo,
  co.cpr_emissao,
  co.cpr_desconto,
  u.usu_nome,
  u.usu_login,
  p.pes_fantasia AS nome_fantasia
FROM compra      co
JOIN usuario     u  ON u.usu_codigo = co.usu_codigo
JOIN fornecedor  f  ON f.for_codigo = co.for_codigo
JOIN pessoa      p  ON p.pes_codigo = f.pes_codigo
WHERE u.usu_login ILIKE '%au%'
  AND u.usu_ativo = '1' 
  AND p.pes_fisica = '0'
ORDER BY p.pes_fantasia ASC;


/* c) vendas de clientes PF, com pagamento via "Google Pay",
      total entre 100 e 5560, no intervalo 01/06/2023–30/06/2024.
      Ordenar por nome do cliente asc e valor do pagamento desc. */
SELECT
  v.vda_codigo,
  v.vda_data,
  p.pes_nome        AS cliente,
  p.pes_fone1,
  p.pes_fone2,
  fp.fpg_nome       AS forma_pagto,
  vp.vdp_valor      AS valor_pagamento
FROM venda v
JOIN cliente     c  ON c.cli_codigo = v.cli_codigo
JOIN pessoa      p  ON p.pes_codigo = c.pes_codigo
JOIN venda_pagto vp ON vp.vda_codigo = v.vda_codigo
JOIN formapagto  fp ON fp.fpg_codigo = vp.fpg_codigo
WHERE p.pes_fisica = '1'
  AND fp.fpg_codigo = '13'
  AND v.vda_total BETWEEN 100.00 AND 5560.00
  AND v.vda_data BETWEEN DATE '2023-06-01' AND DATE '2024-06-30'
ORDER BY p.pes_nome ASC, vp.vdp_valor DESC;


/* d) nome do cliente, email, quantidade de vendas e soma dos totais,
      somente clientes cujo email contém "@gmail.com".
      Ordenar pelo nome do cliente. */
SELECT
  p.pes_nome  AS cliente,
  p.pes_email,
  COUNT(v.vda_codigo)                 AS qtde_vendas,
  COALESCE(SUM(v.vda_total), 0.00)    AS soma_totais
FROM cliente c
JOIN pessoa  p ON p.pes_codigo = c.pes_codigo
LEFT JOIN venda v ON v.cli_codigo = c.cli_codigo
WHERE p.pes_email ILIKE '%@gmail.com%'
GROUP BY p.pes_nome, p.pes_email
ORDER BY p.pes_nome;


/* e) compras-produtos: produtos INATIVOS, código entre 2 e 150,
      compras com desconto > 100, produtos cujo estoque > mínimo,
      e nome do produto contendo "Cabo".
      Ordenar por nome do produto e emissão desc. */
SELECT
  co.cpr_codigo,
  co.cpr_emissao,
  pr.pro_nome,
  pr.pro_unidade,
  pr.pro_estoque,
  pr.pro_preco,
  pr.pro_custo,
  cp.cpr_qtde,
  cp.cpr_preco,
  cp.cpr_desconto,
  cp.cpr_total
FROM compra_produto cp
JOIN compra  co ON co.cpr_codigo = cp.cpr_codigo
JOIN produto pr ON pr.pro_codigo = cp.pro_codigo
WHERE pr.pro_ativo = '0'
  AND pr.pro_codigo BETWEEN 2 AND 150
  AND co.cpr_desconto > 100.00
  AND pr.pro_estoque > pr.pro_min
  AND pr.pro_nome ILIKE '%cabo%'
ORDER BY pr.pro_nome ASC, co.cpr_emissao DESC;


/* f) por fornecedor (INATIVO, PJ), estados SP/MG/RS/SC, cadastro em 2023:
      menor, maior, quantidade, média e soma das compras; trazer nome e contato.
      Agrupar por fornecedor. */
SELECT
  pf.pes_fantasia         AS fornecedor,
  f.for_contato,
  MIN(co.cpr_total)       AS menor_compra,
  MAX(co.cpr_total)       AS maior_compra,
  COUNT(co.cpr_codigo)    AS qtde_compras,
  AVG(co.cpr_total)       AS media_compras,
  SUM(co.cpr_total)       AS soma_compras
FROM fornecedor f
JOIN pessoa pf ON pf.pes_codigo = f.pes_codigo
JOIN compra co ON co.for_codigo = f.for_codigo
WHERE pf.pes_ativo   = '0'
  AND pf.pes_fisica  = '1'
  AND pf.pes_uf IN ('SP','MG','RS','SC')
  AND pf.pes_dtcadastro >= DATE '2023-01-01' AND pf.pes_dtcadastro <  DATE '2024-01-01'
-- AND EXTRACT (YEAR FROM pf.pes_dtcadastro) = 2023
GROUP BY pf.pes_fantasia, f.for_contato
ORDER BY pf.pes_fantasia;
