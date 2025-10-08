SELECT current_database();



DROP VIEW IF EXISTS view_venda_cliente;
CREATE VIEW view_venda_cliente
AS
	SELECT 
		p.pro_nome AS nome,
		pes.pes_celular AS celular,
		v.vda_data AS data_venda,
		v.vda_valor AS valor,
		v.vda_desconto AS desconto,
		v.vda_total AS total
	FROM pessoa pes
	JOIN cliente c ON pes.pes_codigo = c.pes_codigo
	JOIN venda v ON c.cli_codigo = v.cli_codigo
	JOIN venda_produto vp ON vp.vda_codigo = v.vda_codigo
	JOIN produto p ON vp.pro_codigo = p.pro_codigo
	ORDER BY v.vda_data;

SELECT * FROM view_venda_cliente vvc;



DROP VIEW IF EXISTS view_compra_fornecedor;
CREATE VIEW view_compra_fornecedor
AS
SELECT
	p.pes_nome AS nome_fornecedor,
	COUNT(c.for_codigo) AS quantidade_compras,
	MIN(c.cpr_total) AS valor_min_compra,
	MAX(c.cpr_total) AS valor_max_compra,
	SUM(c.cpr_total) AS soma_todas_compra
FROM pessoa p
JOIN fornecedor f 		ON p.pes_codigo = f.pes_codigo
JOIN compra c 			ON f.for_codigo = c.for_codigo
GROUP BY p.pes_nome
ORDER BY p.pes_nome;

SELECT * FROM view_compra_fornecedor vcf;




DROP VIEW IF EXISTS view_venda_cliente_02;
CREATE VIEW view_venda_cliente_02
AS
SELECT 
	v.vda_codigo 	AS "COD Venda",
	v.vda_data 		AS "Data Venda",
	pe.pes_nome 	AS "Nome Cliente",
	pro.pro_nome 	AS "Nome Produto",
	pro.pro_preco 	AS "Valor da Produto",
	vp.vep_qtde  	AS "Quantidade de Produto",
	v.vda_total 	AS "Valor da Venda"
FROM venda v
JOIN cliente c  		ON v.cli_codigo = c.cli_codigo
JOIN pessoa pe   		ON pe.pes_codigo = c.pes_codigo
JOIN  venda_produto vp 	ON v.vda_codigo  = vp.vda_codigo
JOIN produto pro 	 	ON vp.pro_codigo = pro.pro_codigo
ORDER BY "COD Venda";
	
SELECT * FROM view_venda_cliente_02 vvc;


