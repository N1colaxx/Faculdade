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
--DROP SCHEMA public CASCADE;
--CREATE SCHEMA public;


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
 * 	INCERINDO VALORES EM TODAS AS  TABELAS, n fiz em NN
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

/* ============== 1. PESSOA (20 registros novos) ============== */
-- 10 para clientes + 10 para fornecedores
INSERT INTO pessoa (pes_nome, pes_fantasia, pes_fisica, pes_cpfcnpj, pes_rgie, pes_cadastro,
                    pes_endereco, pes_numero, pes_bairro, pes_cidade, pes_uf, pes_cep,
                    pes_fone1, pes_celular, pes_email, pes_ativo)
VALUES
('Cliente A', NULL, 'F', '11111111111', 'RG111', CURRENT_DATE, 'Rua 1', '10', 'Centro', 'São Paulo', 'SP', '01000-000', '11911111111','11991111111','clienteA@email.com','S'),
('Cliente B', NULL, 'F', '22222222222', 'RG222', CURRENT_DATE, 'Rua 2', '20', 'Jardim', 'Rio de Janeiro', 'RJ', '20000-000', '21922222222','21992222222','clienteB@email.com','S'),
('Cliente C', NULL, 'F', '33333333333', 'RG333', CURRENT_DATE, 'Rua 3', '30', 'Centro', 'Belo Horizonte', 'MG', '30000-000', '31933333333','31993333333','clienteC@email.com','S'),
('Cliente D', NULL, 'F', '44444444444', 'RG444', CURRENT_DATE, 'Rua 4', '40', 'Bairro X', 'Curitiba', 'PR', '80000-000', '41944444444','41994444444','clienteD@email.com','S'),
('Cliente E', NULL, 'F', '55555555555', 'RG555', CURRENT_DATE, 'Rua 5', '50', 'Bairro Y', 'Porto Alegre', 'RS', '90000-000', '51955555555','51995555555','clienteE@email.com','S'),
('Cliente F', NULL, 'F', '66666666666', 'RG666', CURRENT_DATE, 'Rua 6', '60', 'Bairro Z', 'Recife', 'PE', '50000-000', '81966666666','81996666666','clienteF@email.com','S'),
('Cliente G', NULL, 'F', '77777777777', 'RG777', CURRENT_DATE, 'Rua 7', '70', 'Centro', 'Fortaleza', 'CE', '60000-000', '85977777777','85997777777','clienteG@email.com','S'),
('Cliente H', NULL, 'F', '88888888888', 'RG888', CURRENT_DATE, 'Rua 8', '80', 'Jardim', 'Manaus', 'AM', '69000-000', '92988888888','92998888888','clienteH@email.com','S'),
('Cliente I', NULL, 'F', '99999999999', 'RG999', CURRENT_DATE, 'Rua 9', '90', 'Bairro A', 'Salvador', 'BA', '40000-000', '71999999999','71999999999','clienteI@email.com','S'),
('Cliente J', NULL, 'F', '10101010101', 'RG1010', CURRENT_DATE, 'Rua 10','100','Bairro B', 'Brasília','DF','70000-000','61910101010','61991010101','clienteJ@email.com','S'),

('Fornecedor A', 'Fantasia A', 'J', '11111000111', 'IE111', CURRENT_DATE, 'Av 1', '1000','Industrial','São Paulo','SP','01010-000','1131111111','1199111111','fornA@email.com','S'),
('Fornecedor B', 'Fantasia B', 'J', '22222000222', 'IE222', CURRENT_DATE, 'Av 2', '2000','Comercial','Rio de Janeiro','RJ','20020-000','2132222222','2199222222','fornB@email.com','S'),
('Fornecedor C', 'Fantasia C', 'J', '33333000333', 'IE333', CURRENT_DATE, 'Av 3', '3000','Centro','Belo Horizonte','MG','30030-000','3133333333','3199333333','fornC@email.com','S'),
('Fornecedor D', 'Fantasia D', 'J', '44444000444', 'IE444', CURRENT_DATE, 'Av 4', '4000','Bairro D','Curitiba','PR','80040-000','4134444444','4199444444','fornD@email.com','S'),
('Fornecedor E', 'Fantasia E', 'J', '55555000555', 'IE555', CURRENT_DATE, 'Av 5', '5000','Bairro E','Porto Alegre','RS','90050-000','5135555555','5199555555','fornE@email.com','S'),
('Fornecedor F', 'Fantasia F', 'J', '66666000666', 'IE666', CURRENT_DATE, 'Av 6', '6000','Bairro F','Recife','PE','50060-000','8136666666','8199666666','fornF@email.com','S'),
('Fornecedor G', 'Fantasia G', 'J', '77777000777', 'IE777', CURRENT_DATE, 'Av 7', '7000','Bairro G','Fortaleza','CE','60070-000','8537777777','8599777777','fornG@email.com','S'),
('Fornecedor H', 'Fantasia H', 'J', '88888000888', 'IE888', CURRENT_DATE, 'Av 8', '8000','Bairro H','Manaus','AM','69080-000','9238888888','9299888888','fornH@email.com','S'),
('Fornecedor I', 'Fantasia I', 'J', '99999000999', 'IE999', CURRENT_DATE, 'Av 9', '9000','Bairro I','Salvador','BA','40090-000','7139999999','7199999999','fornI@email.com','S'),
('Fornecedor J', 'Fantasia J', 'J', '10101001010', 'IE1010', CURRENT_DATE, 'Av 10','10000','Bairro J','Brasília','DF','70100-000','6131010101','6199101010','fornJ@email.com','S');

/* ============== 2. CLIENTE (10, ligando às primeiras 10 pessoas) ============== */
INSERT INTO cliente (pes_codigo, cli_limitecred) VALUES
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente A'), 1000),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente B'), 1500),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente C'),  800),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente D'), 2000),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente E'), 1200),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente F'),  900),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente G'), 1800),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente H'),  600),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente I'), 2500),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Cliente J'), 1400);

/* ============== 3. FORNECEDOR (10, ligando às últimas 10 pessoas) ============== */
INSERT INTO fornecedor (pes_codigo, for_contato) VALUES
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor A'), 'Contato A'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor B'), 'Contato B'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor C'), 'Contato C'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor D'), 'Contato D'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor E'), 'Contato E'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor F'), 'Contato F'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor G'), 'Contato G'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor H'), 'Contato H'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor I'), 'Contato I'),
( (SELECT pes_codigo FROM pessoa WHERE pes_nome='Fornecedor J'), 'Contato J');


INSERT INTO usuario (usu_nome, usu_login, usu_senha, usu_cadastro, usu_ativo) VALUES
('Bruno Costa',     'bruno',   '123', '2025-02-02', '1'),
('Alice Ribeiro',   'alice',   '123', '2025-02-01', '1'),
('Carla Mendes',    'carla',   '123', '2025-02-03', '1'),
('Diego Souza',     'diego',   '123', '2025-02-04', '1'),
('Eduarda Lima',    'edu',     '123', '2025-02-05', '1'),
('Felipe Santos',   'felipe',  '123', '2025-02-06', '0'),
('Gabriela Rocha',  'gabi',    '123', '2025-02-07', '0'),
('Henrique Silva',  'henrique','123', '2025-02-08', '1'),
('Isabela Nunes',   'isa',     '123', '2025-02-09', '1'),
('João Pedro',      'jpedro',  '123', '2025-02-10', '0');



/* ================= FORMAS DE PAGAMENTO ================= */
INSERT INTO formapagto (fpg_nome, fpg_ativo) VALUES
('Dinheiro', 'S'),
('Pix', 'S'),
('Cartão de Crédito - 1x', 'S'),
('Cartão de Crédito - 2x', 'S'),
('Cartão de Crédito - 3x', 'S'),
('Cartão de Débito', 'S'),
('Boleto Bancário', 'S'),
('Transferência Bancária', 'S'),
('Carteira Digital', 'S'),
('Cheque', 'N');

/* ================= PRODUTOS ================= */
INSERT INTO produto (pro_nome, pro_estoque, pro_unidade, pro_preco, pro_custo, pro_atacado,
                     pro_min, pro_max, pro_embalagem, pro_peso, pro_cadastro, pro_obs, pro_ativo)
VALUES
('Caneta Azul',       500, 'UN',  2.50,  1.20,  2.20,  50, 2000, 1, 0.010, CURRENT_DATE, 'Material escolar', 'S'),
('Caderno 200 fls',   300, 'UN', 18.90, 12.00, 16.00,  20, 1000, 1, 0.450, CURRENT_DATE, 'Caderno universitário', 'S'),
('Lápis HB',          800, 'UN',  1.80,  0.80,  1.50, 100, 5000, 1, 0.008, CURRENT_DATE, 'Lápis preto comum', 'S'),
('Borracha Branca',   600, 'UN',  3.50,  1.70,  3.00,  50, 3000, 1, 0.012, CURRENT_DATE, 'Apaga bem grafite', 'S'),
('Mochila Escolar',   120, 'UN', 95.00, 65.00, 85.00,  10,  500, 1, 0.800, CURRENT_DATE, 'Mochila reforçada', 'S'),
('Régua 30cm',        450, 'UN',  4.20,  2.10,  3.80,  30, 1500, 1, 0.030, CURRENT_DATE, 'Régua plástica', 'S'),
('Marca Texto',       380, 'UN',  6.90,  3.40,  5.90,  20, 1200, 1, 0.015, CURRENT_DATE, 'Caneta marca texto', 'S'),
('Grampeador',        140, 'UN', 29.90, 18.00, 25.00,   5,  400, 1, 0.250, CURRENT_DATE, 'Grampeador médio', 'S'),
('Papel A4 500fls',   220, 'UN', 32.00, 23.00, 29.00,  10,  800, 1, 2.500, CURRENT_DATE, 'Resma papel A4', 'S'),
('Post-it 76x76',     260, 'UN', 11.90,  7.50, 10.50,  10,  900, 1, 0.090, CURRENT_DATE, 'Bloco adesivo', 'S');
