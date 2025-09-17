/*
 
 Projeto: Módulo de Vendas de um Sistema ERP, com controle de estoque. 
Integrado com a disciplina ALPOO.

1) Crie um Script SQL contendo os seguintes as seguintes implementações: 
a. Criar todas as tabelas, sem a definição de chaves primárias (PK). 
b. Criar as PK com autoincremento (PostgreSQL=serial) 
c. Alterar todas as tabelas adicionando sua respectiva chave primária (PK). 
d. Alterar as tabelas que contenham chaves secundárias e adicioná-las. 
e. Inserir 10 registros em cada tabela, respeitando a integridade referencial. 
f. Alterar 2 registros em cada tabela (de livre escolha, respeitando a integridade 
referencial). 
g. Excluir 2 registros de cada tabela (de livre escolha, respeitando a integridade 
referencial). 

*/


-- Drop tables
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;


DROP TABLE IF EXISTS pessoa;
DROP TABLE IF EXISTS fornecedor;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS compra_produto;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS venda_produto;
DROP TABLE IF EXISTS venda_pagto;
DROP TABLE IF EXISTS formapagto;



CREATE TABLE pessoa(
	pes_codigo SERIAL NOT NULL, -- PK
	pes_nome varchar(80) NOT NULL,
	pes_fantasia varchar(80),
	pes_fisica char(1) NOT NULL,
	pes_cpfcnpj varchar(20) NOT NULL,
	pes_rgie varchar(20),
	pes_cadastro date,
	pes_endereco varchar(120),
	pes_numero varchar(10),
	pes_complemento varchar(30),
	pes_bairro varchar(50),
	pes_cidade varchar(80),
	pes_uf char(2),
	pes_cep varchar(9),
	pes_fone1 varchar(16),
	pes_fone2 varchar(16),
	pes_celular varchar(16),
	pes_site varchar(200),
	pes_email varchar(200),
	pes_ativo char(1)
);
 

CREATE TABLE fornecedor (
	for_codigo SERIAL NOT NULL, --PK
	pes_codigo int NOT NULL, --FK_FORNECEDOR_PESSOA
	for_contato varchar(80)
);

CREATE TABLE cliente(
	cli_codigo SERIAL NOT NULL, --PK
	pes_codigo int NOT NULL, -- FK_CLIENTE_PESSOA
	cli_limitecred numeric(18,2) -- em Oracle -> Number
);


CREATE TABLE venda (
	vda_codigo SERIAL NOT NULL, --PK
	usu_codigo int NOT NULL, --FK_VENDA_USUARIO
	cli_codigo int NOT NULL, --FK_VENDA_CLIENTE
	vda_data date NOT NULL,
	vda_valor numeric(18,2), 
	vda_desconto numeric(18,2),
	vda_total numeric(18,2),
	vda_obs text -- em Oracle -> clob
);


CREATE TABLE usuario (
	usu_codigo SERIAL NOT NULL, -- PK
	usu_nome varchar(80),
	usu_login varchar(20) NOT NULL,
	usu_senha varchar(20),
	usu_cadastro date,
	usu_ativo char(1)
);


CREATE TABLE compra(
	cpr_codigo SERIAL NOT NULL, --PK
	usu_codigo int NOT NULL, --FK_COMPRA_USUARIO
	for_codigo int NOT NULL, --FK_COMPRA_FORNECEDOR
	cpr_emissao date,
	cpr_valor numeric(18,2),
	cpr_desconto numeric(18,2),
	cpr_total numeric(18,2),
	cpr_dtentrada date,
	cpr_obs text
);



CREATE TABLE compra_produto (
	cpp_codigo SERIAL NOT NULL, --PK
	cpr_codigo int NOT NULL, --FK_COMPRA_PRODUTO_COMPRA
	pro_codigo int NOT NULL, --FK_COMPRA_PRODUTO_PRODUTO
	cpr_qtde numeric(14,4) NOT NULL,
	cpr_preco numeric(18,2) NOT NULL,
	cpr_desconto numeric(18,2),
	cpr_total numeric(18,2)
);


CREATE TABLE produto (
	pro_codigo SERIAL NOT NULL, --PK
	pro_nome varchar(80) NOT NULL,
	pro_estoque numeric(14,4),
	pro_unidade varchar(5),
	pro_preco numeric(18,2),
	pro_custo numeric(18,2),
	pro_atacado numeric(18,2),
	pro_min numeric(14,4),
	pro_max numeric(14,4),
	pro_embalagem decimal(9,0),
	pro_peso numeric(14,4),
	pro_cadastro date,
	pro_obs text,
	pro_ativo char(1)
);

CREATE TABLE venda_produto (
	vep_codigo SERIAL NOT NULL, --PK
	vda_codigo int NOT NULL, --FK_VENDA_PRODUTO_VENDA
	pro_codigo int NOT NULL, --FK_VENDA_PRODUTO_PRODUTO
	vep_qtde numeric(14,4),
	vep_preco numeric(18,2),
	vep_desconto numeric(18,2),
	vep_total numeric(18,2)
);

CREATE TABLE venda_pagto (
	vdp_codigo SERIAL NOT NULL, --PK
	vda_codigo int NOT NULL, --FK_VENDA_PAGTO_VEND
	fpg_codigo int NOT NULL, --FK_VENDA_PAGAMENTO_FORMAPAGTO
	vdp_valor numeric(18,2)
);

CREATE TABLE formapagto (
	fpg_codigo SERIAL NOT NULL, --pk
	fpg_nome varchar(80) NOT NULL,
	fpg_ativo char(1)
);

-- ADD as CONSTRAINTS das PK

ALTER TABLE cliente 
	add PRIMARY KEY (cli_codigo);

ALTER TABLE compra
	ADD PRIMARY KEY (cpr_codigo);

ALTER TABLE pessoa
	ADD PRIMARY KEY (pes_codigo);

ALTER TABLE fornecedor
    ADD PRIMARY KEY (for_codigo);

ALTER TABLE venda
    ADD PRIMARY KEY (vda_codigo);

ALTER TABLE usuario
    ADD PRIMARY KEY (usu_codigo);

ALTER TABLE compra_produto
    ADD PRIMARY KEY (cpp_codigo);

ALTER TABLE produto
    ADD PRIMARY KEY (pro_codigo);

ALTER TABLE venda_produto
    ADD PRIMARY KEY (vep_codigo);

ALTER TABLE venda_pagto
    ADD PRIMARY KEY (vdp_codigo);

ALTER TABLE formapagto
    ADD PRIMARY KEY (fpg_codigo);

/*
 *	ADD as FK 
 * */




-- CLIENTE	
ALTER TABLE cliente
	ADD CONSTRAINT fk_cliente_pessoa
	FOREIGN KEY (pes_codigo) REFERENCES pessoa (pes_codigo)
	ON DELETE RESTRICT   -- impede apagar pessoa se houver cliente
	ON UPDATE RESTRICT; 	

-- FORNECEDOR
ALTER TABLE fornecedor
	ADD CONSTRAINT fk_fornecedor_pessoa
	FOREIGN KEY (pes_codigo) REFERENCES pessoa (pes_codigo)
	ON DELETE RESTRICT   -- impede apagar pessoa se houver fornecedor
	ON UPDATE RESTRICT;

-- COMPRA
ALTER TABLE compra
	ADD CONSTRAINT fk_compra_usuario
	FOREIGN KEY (usu_codigo) REFERENCES usuario (usu_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- COMPRA
ALTER TABLE compra
	ADD CONSTRAINT fk_compra_fornecedor
	FOREIGN KEY (for_codigo) REFERENCES fornecedor (for_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- COMPRA_PRODUTO: não pode apagar compra ou produto se houver itens, para manter histórico
ALTER TABLE compra_produto
	ADD CONSTRAINT fk_compra_produto_compra
	FOREIGN KEY (cpr_codigo) REFERENCES compra (cpr_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE compra_produto
	ADD CONSTRAINT fk_compra_produto_produto
	FOREIGN KEY (pro_codigo) REFERENCES produto (pro_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- VENDA: não pode apagar usuário ou cliente se houver vendas
ALTER TABLE venda
	ADD CONSTRAINT fk_venda_usuario
	FOREIGN KEY (usu_codigo) REFERENCES usuario (usu_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE venda
	ADD CONSTRAINT fk_venda_cliente
	FOREIGN KEY (cli_codigo) REFERENCES cliente (cli_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- VENDA_PRODUTO: não pode apagar venda ou produto se houver histórico de vendas
ALTER TABLE venda_produto
	ADD CONSTRAINT fk_venda_produto_venda
	FOREIGN KEY (vda_codigo) REFERENCES venda (vda_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE venda_produto
	ADD CONSTRAINT fk_venda_produto_produto
	FOREIGN KEY (pro_codigo) REFERENCES produto (pro_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- VENDA_PAGTO: não pode apagar venda ou forma de pagamento
ALTER TABLE venda_pagto
	ADD CONSTRAINT fk_venda_pagto_venda
	FOREIGN KEY (vda_codigo) REFERENCES venda (vda_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE venda_pagto
	ADD CONSTRAINT fk_venda_pagto_formapagto
	FOREIGN KEY (fpg_codigo) REFERENCES formapagto (fpg_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;



/*
 * 	INCERINDO VALORES EM TODAS AS  TABELAS
 * 
 * -- Sempre inserir primeiro as tabelas que não têm FK. --
 * 
 * 	ORDEM DE DEPENDENCIA das tab: 
 * 
 * pessoa → não depende de ninguém, então vai primeiro.
 * usuario → não depende de ninguém.
 * formapagto → não depende de ninguém.
 * produto → não depende de ninguém.
 * cliente → depende de pessoa.
 * fornecedor → depende de pessoa.
 * venda → depende de cliente e usuario.
 * compra → depende de fornecedor e usuario.
 * compra_produto → depende de compra e produto.
 * venda_produto → depende de venda e produto.
 * venda_pagto → depende de venda e formapagto.
 * 
 * */


/* 1. PESSOAS */
INSERT INTO pessoa (pes_nome, pes_fantasia, pes_fisica, pes_cpfcnpj, pes_rgie, pes_cadastro, pes_endereco, pes_numero, pes_bairro, pes_cidade, pes_uf, pes_cep, pes_fone1, pesc_celular, pes_email, pes_ativo)
VALUES
('João Silva', 'JS Ltda', 'F', '12345678900', 'MG123456', '2025-01-01', 'Rua A', '100', 'Centro', 'Belo Horizonte', 'MG', '30000-000', '31999999999', '31988888888', 'joao@js.com.br', 'S'),
('Maria Souza', 'MS Ltda', 'F', '98765432100', 'SP654321', '2025-02-01', 'Rua B', '200', 'Jardim', 'São Paulo', 'SP', '01000-000', '11999999999', '11988888888', 'maria@ms.com.br', 'S'),
('Pedro Santos', NULL, 'F', '11122233344', NULL, '2025-03-01', 'Rua C', '300', 'Centro', 'Rio de Janeiro', 'RJ', '20000-000', '21999999999', '21988888888', 'pedro@exemplo.com', 'S'),
('Ana Souza', NULL, 'F', '98762432250', NULL, '2025-04-01', 'Rua D', '400', 'Bairro X', 'São Paulo', 'SP', '01100-000', '34234344999', '1198882323', 'ana@ms.com.br', 'S'),
('Nicolas Santos', NULL, 'F', '54645645645', NULL, '2025-05-01', 'Rua E', '500', 'Bairro Y', 'Rio de Janeiro', 'RJ', '22000-000', '219999234999', '2198842318', 'nicolas@exemplo.com', 'S');

/* 2. USUÁRIOS */
INSERT INTO usuario (usu_nome, usu_login, usu_senha, usu_cadastro, usu_ativo)
VALUES
('Admin', 'admin', '123', '2025-01-01', 'S'),
('João', 'joao', '123', '2025-01-02', 'S');

/* 3. FORMAS DE PAGAMENTO */
INSERT INTO formapagto (fpg_nome, fpg_ativo)
VALUES
('Dinheiro','S'),
('Cartão Crédito','S');

/* 4. PRODUTOS */
INSERT INTO produto (pro_nome, pro_estoque, pro_unidade, pro_preco, pro_custo, pro_atacado, pro_min, pro_max, pro_embalagem, pro_peso, pro_cadastro, pro_ativo)
VALUES
('Produto A', 100, 'UN', 10, 5, 9, 10, 200, 1, 0.5, '2025-01-01', 'S'),
('Produto B', 200, 'UN', 20, 10, 18, 20, 400, 1, 0.8, '2025-01-02', 'S');

/* 5. CLIENTES */
INSERT INTO cliente (pes_codigo, cli_limitecred)
VALUES
(1, 1000),  
(2, 2000),  
(4, 500),  
(5, 600); 

/* 6. FORNECEDORES */
INSERT INTO fornecedor (pes_codigo, for_contato)
VALUES
(1, 'João Contato'),
(2, 'Maria Contato'),
(3, 'Pedro Contato'); 

/* 7. VENDAS */
INSERT INTO venda (usu_codigo, cli_codigo, vda_data, vda_valor, vda_desconto, vda_total)
VALUES
(1,1,'2025-08-01',100,10,90),  
(2,2,'2025-08-02',200,20,180);

/* 8. COMPRAS */
INSERT INTO compra (usu_codigo, for_codigo, cpr_emissao, cpr_valor, cpr_desconto, cpr_total, cpr_dtentrada)
VALUES
(1,1,'2025-08-01',1000,100,900,'2025-08-02'), 
(2,3,'2025-08-02',2000,200,1800,'2025-08-03');

/* 9. VENDA PRODUTO */
INSERT INTO venda_produto (vda_codigo, pro_codigo, vep_qtde, vep_preco, vep_desconto, vep_total)
VALUES
(1,1,5,10,0.5,49.5),
(2,2,10,20,1,199);

/* 10. VENDA PAGTO */
INSERT INTO venda_pagto (vda_codigo, fpg_codigo, vdp_valor)
VALUES
(1,1,49.5),
(2,2,199);



/**
 * 
 * DELETANDO REGISTROS
 * 
 * */

-- 1) Apagar Ana e Nicolas de clientes
SELECT * FROM cliente;

DELETE FROM cliente AS c
WHERE c.pes_codigo IN (4,5);

-- 2) Apagar Ana e Nicolas de fornecedores
SELECT * FROM fornecedor;

DELETE FROM fornecedor AS f
WHERE f.pes_codigo IN (2);

-- 3) Apagar Ana e Nicolas da tabela pessoa
SELECT * FROM pessoa;

DELETE FROM pessoa AS p
WHERE p.pes_codigo IN (4,5);