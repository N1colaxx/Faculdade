DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS pessoa;
DROP TABLE IF EXISTS fornecedor;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS compra;
DROP TABLE IF EXISTS venda;
DROP TABLE IF EXISTS formapagto;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS venda_pagto;
DROP TABLE IF EXISTS venda_produto;
DROP TABLE IF EXISTS compra_produto;




CREATE TABLE usuario (
	usu_codigo serial NOT NULL, -- PK
	gru_codigo int NOT NULL,
	usu_nome varchar(80),
	usu_login varchar(20) NOT null,
	usu_senha varchar(20),
	usu_cadastro date,
	usu_ativo char(1)
);

CREATE TABLE pessoa (
	pes_codigo serial NOT NULL, --PK
	pes_nome varchar (80),
	pes_fantasia varchar (80),
	pes_fisica char (1),
	pes_CPFCNPJ varchar (20),
	pes_rgie varchar (20),
	pes_dtcadastro date,
	pes_endereco varchar (20),
	pes_numero varchar (10),
	pes_complemento varchar (30),
	pes_bairro varchar (50),
	pes_cidade varchar (80),
	pes_uf char(2),
	pes_cep varchar (9),
	pes_fone1 varchar(16),
	pes_fone2 char(20), -- não entendi o pq disso
	pes_celular char(20),
	pes_site varchar(200),
	pes_email varchar(200),
	pes_ativo char(1)
);


CREATE TABLE fornecedor (
	for_codigo serial NOT NULL , --PK
	pes_codigo int NOT NULL,
	for_contato varchar (80)
);



CREATE TABLE cliente (
	cli_codigo serial NOT NULL, --PK
	pes_codigo int NOT NULL , --FK
	cli_limitecred NUMERIC (18,2)
);


CREATE TABLE compra (
	cpr_codigo serial NOT NULL, --PK
	usu_codigo int NOT NULL, --FK
	for_codigo int NOT NULL, --FK
	cpr_emissao date,
	cpr_valor NUMERIC (18,2),
	cpr_desconto NUMERIC (18,2),
	cpr_total NUMERIC (18,2),
	cpr_dtentrada date,
	cpr_obs text
);

CREATE TABLE venda (
	vda_codigo serial NOT NULL, --PK
	usu_codigo int NOT NULL, -- FK
	cli_codigo int NOT NULL, --FK
	vda_data date,
	vda_valor NUMERIC (18,2),
	vda_desconto NUMERIC (18,2),
	vda_total NUMERIC (18,2),
	vda_obs text
);

CREATE TABLE formapagto (
	fpg_codigo serial NOT NULL, --PK
	fpg_nome varchar(80),
	fpg_ativo char(1)
);


CREATE TABLE produto (
	pro_codigo serial NOT NULL, -- PK
	pro_nome varchar(80),
	pro_estoque NUMERIC (18,4),
	pro_unidade varchar (5),
	pro_preco NUMERIC (18,2),
	pro_custo NUMERIC (18,2),
	pro_atacado NUMERIC (18,2),
	pro_min NUMERIC (18,4),
	pro_max NUMERIC (18,4),
	pro_embalagem NUMERIC (9,0),
	pro_peso NUMERIC (18,4),
	pro_dtcadastro date,
	pro_obs varchar(80), -- no der esta -> blob (80,1)
	pro_ativo char(1),
	pro_tipo char(1)
);


/**
 * 	Tab NN geradas
 **/

CREATE TABLE venda_pagto (
	vdp_codigo serial NOT NULL, --PK
	vda_codigo int NOT NULL, --FK
	fpg_codigo int NOT NULL, --FK
	vdp_valor NUMERIC (18,2)
);

CREATE TABLE venda_produto (
	vep_codigo serial NOT NULL, --PK
	vda_codigo int NOT NULL, --FK
	pro_codigo int NOT NULL, --FK
	vep_qtde NUMERIC (18,4),
	vep_preco NUMERIC (18,2),
	vep_desconto NUMERIC (18,2),
	vep_total NUMERIC (18,2)
);

CREATE TABLE compra_produto (
	cpp_codigo serial NOT NULL, --PK
	cpr_codigo int NOT NULL, --FK
	pro_codigo int NOT NULL, --FK
	cpr_qtde NUMERIC (18,4),
	cpr_preco NUMERIC (18,2),
	cpr_desconto NUMERIC (18,2),
	cpr_total NUMERIC (18,2)
);


/*
 *  ADD PRIMARY KEY
 * 
 * **/

ALTER TABLE usuario
	ADD PRIMARY KEY (usu_codigo);

ALTER TABLE pessoa
	ADD PRIMARY KEY (pes_codigo);

ALTER TABLE fornecedor
	ADD PRIMARY KEY (for_codigo);

ALTER TABLE cliente
	ADD PRIMARY KEY (cli_codigo);

ALTER TABLE compra 
	ADD PRIMARY KEY (cpr_codigo);

ALTER TABLE venda 
	ADD PRIMARY KEY (vda_codigo);

ALTER TABLE formapagto
	ADD PRIMARY KEY (fpg_codigo);

ALTER TABLE produto 
	ADD PRIMARY KEY (pro_codigo);

ALTER TABLE venda_pagto
	ADD PRIMARY KEY (vdp_codigo);

ALTER TABLE venda_produto
	ADD PRIMARY KEY (vep_codigo);

ALTER TABLE compra_produto
	ADD PRIMARY KEY (cpp_codigo);


/*
 * ADD CONSTRANTS FK
 * **/

ALTER TABLE fornecedor 
	ADD CONSTRAINT fk_fornecedor_pessoa
	FOREIGN KEY (pes_codigo) REFERENCES pessoa (pes_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE cliente 
	ADD CONSTRAINT fk_cliente_pessoa 
	FOREIGN KEY (pes_codigo) REFERENCES pessoa (pes_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

-- COMPRA
ALTER TABLE compra
	ADD CONSTRAINT fk_compra_usuario
	FOREIGN KEY (usu_codigo) REFERENCES usuario (usu_codigo)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE compra
	ADD CONSTRAINT fk_compra_fornecedor
	FOREIGN KEY (for_codigo) REFERENCES fornecedor (for_codigo)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

-- VENDA
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

-- VENDA_PAGTO
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

-- VENDA_PRODUTO
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


-- COMPRA_PRODUTO
ALTER TABLE compra_produto
	ADD CONSTRAINT fk_venda_produto_compra
	FOREIGN KEY (cpr_codigo) REFERENCES compra (cpr_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;


ALTER TABLE compra_produto
	ADD CONSTRAINT fk_venda_produto_produto
	FOREIGN KEY (pro_codigo) REFERENCES produto (pro_codigo)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;






