-- DIM_TEMPO - Dimensão temporal para análise por data
CREATE VIEW dim_tempo AS
SELECT DISTINCT
    date(data_emissao) AS chave_data,
    date(data_emissao) AS data_completa,
    strftime('%Y', data_emissao) AS ano,
    strftime('%m', data_emissao) AS mes,
    strftime('%d', data_emissao) AS dia,
    strftime('%Y-%m', data_emissao) AS ano_mes,
    CASE 
        WHEN strftime('%m', data_emissao) IN ('12', '01', '02') THEN 'Verão'
        WHEN strftime('%m', data_emissao) IN ('03', '04', '05') THEN 'Outono'
        WHEN strftime('%m', data_emissao) IN ('06', '07', '08') THEN 'Inverno'
        ELSE 'Primavera'
    END AS estacao,
    strftime('%W', data_emissao) AS semana_ano,
    strftime('%w', data_emissao) AS dia_semana
FROM NotaFiscal;

select * from dim_tempo dt ;

-- DIM_PESSOA - Dimensão para todas as pessoas (emitentes, destinatários, remetentes)
CREATE VIEW dim_pessoa AS
SELECT 
    p.id_pessoa,
    p.tipo_pessoa,
    p.tipo_documento,
    p.numero_documento,
    p.nome_razao_social,
    pf.cod AS codigo_pf,
    pj.nome_fantasia,
    pj.regime_tributario,
    pj.inscricao_estadual,
    pj.inscricao_suframa,
    pj.is_contribuinte_icms,
    pj.cnae_fiscal,
    e.tipo AS tipo_endereco,
    e.logradouro,
    e.numero,
    e.complemento,
    e.bairro,
    e.municipio,
    e.uf,
    e.cep,
    e.codigo_pais,
    c.ddd AS telefone_ddd,
    c.numero AS telefone_numero,
    c.email
FROM Pessoa p
LEFT JOIN PessoaFisica pf ON p.id_pessoa = pf.id_pessoa
LEFT JOIN PessoaJuridica pj ON p.id_pessoa = pj.id_pessoa
LEFT JOIN Endereco e ON p.id_pessoa = e.id_pessoa
LEFT JOIN Contato c ON p.id_pessoa = c.id_pessoa AND c.tipo = 'TELEFONE';

select * from dim_pessoa dp ;

-- DIM_TRANSPORTADORA - Dimensão para transportadoras
CREATE VIEW dim_transportadora AS
SELECT 
    t.id_transportadora,
    t.nome,
    t.cnpj,
    e.tipo AS tipo_endereco,
    e.logradouro,
    e.numero,
    e.complemento,
    e.bairro,
    e.municipio,
    e.uf,
    e.cep,
    e.codigo_pais
FROM Transportadora t
LEFT JOIN Endereco e ON t.id_transportadora = e.id_transportadora;

select * from dim_transportadora dt ;

-- DIM_PRODUTO - Dimensão de produtos
CREATE VIEW dim_produto AS
SELECT 
    p.id_produto,
    p.codigo,
    p.nome,
    p.descricao,
    p.ncm,
    p.cest,
    p.unidade_comercial,
    p.valor_unitario
FROM Produto p;

select * from dim_produto dp ;

-- DIM_SERVICO - Dimensão de serviços
CREATE VIEW dim_servico AS
SELECT 
    s.id_servico,
    s.codigo,
    s.nome,
    s.descricao,
    s.valor_unitario
FROM Servico s;

select * from dim_servico ds ;

-- DIM_IMPOSTO - Dimensão de impostos
CREATE VIEW dim_imposto AS
SELECT 
    i.id_imposto,
    i.codigo,
    i.nome,
    i.descricao,
    ti.id_tipo_imposto,
    ti.codigo_tipo,
    ti.descricao_tipo
FROM Imposto i
JOIN TipoImposto ti ON i.id_imposto = ti.id_tipo_imposto;

select * from dim_imposto di ;


-- DIM_STATUS - Dimensão de status
CREATE VIEW dim_status AS
SELECT 
    id_status,
    cod AS codigo_status,
    CASE cod
        WHEN 'EM_DIGITACAO' THEN 'Em digitação'
        WHEN 'VALIDADA' THEN 'Validada'
        WHEN 'AUTORIZADA' THEN 'Autorizada'
        WHEN 'CANCELADA' THEN 'Cancelada'
        WHEN 'DENEGADA' THEN 'Denegada'
        ELSE cod
    END AS descricao_status
FROM TbStatus;
select * from dim_status ds ;

-- DIM_FORMA_PAGAMENTO - Dimensão de formas de pagamento
CREATE VIEW dim_forma_pagamento AS
SELECT 
    id_forma_pagamento,
    codigo,
    descricao
FROM FormaPagamento;

select * from dim_forma_pagamento dfp ;