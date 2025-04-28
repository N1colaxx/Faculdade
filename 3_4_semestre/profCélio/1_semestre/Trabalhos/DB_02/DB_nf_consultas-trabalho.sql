-- 1. Clientes do estado "SC"

SELECT 
    p.nome_razao_social AS "Razão Social",
    p.numero_documento AS "CNPJ",
    e.municipio AS "Município",
    e.uf AS "UF"
FROM Pessoa p
JOIN PessoaJuridica pj ON p.id_pessoa = pj.id_pessoa
JOIN Endereco e ON p.id_pessoa = e.id_pessoa
WHERE e.uf <> 'SC'
ORDER BY p.nome_razao_social;

select * from Endereco e ; -- isso é []


--2. Produtos de NF-e emitidas entre 01/01/2014 e 20/05/2024
SELECT 
    p.codigo AS "Código",
    p.nome AS "Descrição",
    ii.situacao_tributaria AS "CST",
    inf.quantidade AS "Quantidade",
    inf.valor_unitario AS "Valor Unitário",
    inf.valor_total_item AS "Valor Total"
FROM ItemNotaFiscal inf
JOIN Produto p 
	ON inf.id_produto = p.id_produto
JOIN NotaFiscal nf 
	ON inf.id_nf = nf.id_nf
LEFT JOIN ImpostoItem ii 
	ON inf.id_item_nf = ii.id_item_nf AND ii.id_tipo_imposto IN (
    SELECT id_tipo_imposto 
    FROM TipoImposto 
    WHERE id_imposto = (
        SELECT id_imposto 
        FROM Imposto WHERE codigo = 'ICMS'
    )
)
WHERE nf.data_emissao BETWEEN '2014-01-01' AND '2024-05-20'
ORDER BY nf.data_emissao DESC;

SELECT * FROM ItemNotaFiscal inf ;



-- 3. NF-e de entrada com filtros específicos
SELECT 
    nf.numero AS "Nº NF-e",
    nf.chave_acesso AS "Chave de Acesso",
    p.nome_razao_social AS "Cliente",
    p.numero_documento AS "CPF/CNPJ",
    nf.valor_total_nf AS "Valor Total"
FROM NotaFiscal nf
JOIN Destinatario d 
	ON nf.id_destinatario = d.id_destinatario
JOIN Pessoa p 
	ON d.id_pessoa = p.id_pessoa
WHERE nf.tipo_operacao = 'ENTRADA'
AND nf.data_saida_entrada < '2024-03-15'
AND nf.valor_total_nf BETWEEN 1000.00 AND 5000.00
ORDER BY nf.data_emissao DESC; 

--4. NF-e com dados de fatura e filtros específicos
SELECT 
    nf.numero AS "Nº NF-e",
    nf.natureza_da_operacao AS "Natureza da Operação",
    nf.protocolo_autorizacao AS "Protocolo",
    f.numero AS "Nº Fatura",
    f.data_vencimento AS "Vencimento",
    f.valor AS "Valor Fatura"
FROM NotaFiscal nf
JOIN Fatura f 
	ON nf.id_nf = f.id_nf
WHERE nf.numero BETWEEN 100 AND 950 -- valor q o prof queria 300 a 950
AND f.valor BETWEEN 300.00 AND 850.00
AND f.data_vencimento > '2022-01-01' -- valor q o prof queria 01/01/2024”
ORDER BY nf.numero;

SELECT * FROM NotaFiscal nf ;
SELECT * FROM Fatura f ;


-- 	5. Itens de NF-e com filtros tributários específicos
SELECT 
    p.codigo AS "Código",
    p.nome AS "Descrição",
    inf.quantidade AS "Quantidade",
    inf.valor_unitario AS "Valor Unitário",
    inf.valor_total_item AS "Valor Total",
    icms.aliquota AS "Alíquota ICMS (%)",
    ipi.aliquota AS "Alíquota IPI (%)",
    icms.valor AS "Valor ICMS",
    ipi.valor AS "Valor IPI"
FROM ItemNotaFiscal inf
JOIN Produto p ON inf.id_produto = p.id_produto
JOIN NotaFiscal nf ON inf.id_nf = nf.id_nf
LEFT JOIN ImpostoItem icms ON inf.id_item_nf = icms.id_item_nf AND icms.id_tipo_imposto IN (
    SELECT id_tipo_imposto FROM TipoImposto WHERE id_imposto = (
        SELECT id_imposto FROM Imposto WHERE codigo = 'ICMS'
    )
)
LEFT JOIN ImpostoItem ipi ON inf.id_item_nf = ipi.id_item_nf AND ipi.id_tipo_imposto IN (
    SELECT id_tipo_imposto FROM TipoImposto WHERE id_imposto = (
        SELECT id_imposto FROM Imposto WHERE codigo = 'IPI'
    )
)
WHERE nf.valor_total_nf < 10000.00
AND icms.situacao_tributaria IN ('010', '040', '060')
AND ipi.valor > 50.00
AND icms.valor < 100.00
ORDER BY inf.valor_total_item DESC;