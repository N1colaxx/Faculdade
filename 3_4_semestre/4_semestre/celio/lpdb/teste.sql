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




