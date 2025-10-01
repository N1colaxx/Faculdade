SELECT CURRENT_database();

-- extensão hstore -> para transformar linhas em mapas chave-valor
CREATE EXTENSION IF NOT EXISTS hstore;

DROP TABLE IF EXISTS log CASCADE;

CREATE TABLE log (
	log_id serial NOT NULL,
	log_data date,
	log_operacao varchar(10),
	log_tabela varchar(30),
	log_campo varchar(30),
	log_old varchar(400),
	log_new varchar(400)
);

ALTER TABLE log
	ADD CONSTRAINT log_pk PRIMARY KEY (log_id);




/* ==========================================================
   1) Function genérica para gravar log
   ========================================================== */

CREATE OR REPLACE FUNCTION Func_Gravar_Log(
    p_log_operacao VARCHAR,  -- Operação (INCLUSAO, ALTERACAO, EXCLUSAO)
    p_log_tabela   VARCHAR,  -- Nome da tabela
    p_log_campo    VARCHAR,  -- Nome do campo
    p_log_old      VARCHAR,  -- Valor antigo
    p_log_new      VARCHAR   -- Valor novo
)
RETURNS VOID AS
$$
BEGIN
    -- Insere registro na tabela de LOG
    INSERT INTO log (
        log_data,
        log_operacao,
        log_tabela,
        log_campo,
        log_old,
        log_new
    ) VALUES (
        CURRENT_TIMESTAMP,
        p_log_operacao,
        p_log_tabela,
        p_log_campo,
        p_log_old,
        p_log_new
    );
END;
$$ LANGUAGE plpgsql;



/* ==========================================================
    TRIGGER para INSERT
   ========================================================== */

-- CLIENTE
CREATE OR REPLACE FUNCTION trg_log_cliente()
RETURNS TRIGGER AS
$$
BEGIN
-- ISSO ->  ::varchar
-- Ele está convertendo o valor XXX (que é um número inteiro SERIAL) em texto (varchar). Para poder gravar na tab LOG
    PERFORM Func_Gravar_Log('INCLUSAO', 'CLIENTE', 'CLI_CODIGO', 		NULL, NEW.cli_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'CLIENTE', 'PES_CODIGO', 		NULL, NEW.pes_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'CLIENTE', 'CLI_LIMITECRED', 	NULL, NEW.cli_limitecred::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger que chama a função acima
CREATE TRIGGER after_insert_cliente
AFTER INSERT ON cliente
FOR EACH ROW
EXECUTE FUNCTION trg_log_cliente();



-- COMPRA
CREATE OR REPLACE FUNCTION trg_log_compra()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_CODIGO', 	NULL, NEW.cpr_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'USU_CODIGO', 	NULL, NEW.usu_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'FOR_CODIGO', 	NULL, NEW.for_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_EMISSAO', 	NULL, NEW.cpr_emissao::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_VALOR', 		NULL, NEW.cpr_valor::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_DESCONTO', 	NULL, NEW.cpr_desconto::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_TOTAL', 		NULL, NEW.cpr_total::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_DTENTRADA', 	NULL, NEW.cpr_dtentrada::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA', 'CPR_OBS', 		NULL, NEW.cpr_obs::varchar);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_compra
AFTER INSERT ON compra
FOR EACH ROW
EXECUTE FUNCTION trg_log_compra();



-- COMPRA_PRODUTO
CREATE OR REPLACE FUNCTION trg_log_compra_produto()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPP_CODIGO', 	NULL, NEW.cpp_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPR_CODIGO', 	NULL, NEW.cpr_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'PRO_CODIGO', 	NULL, NEW.pro_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPR_QTDE', 		NULL, NEW.cpr_qtde::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPR_PRECO', 		NULL, NEW.cpr_preco::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPR_DESCONTO', 	NULL, NEW.cpr_desconto::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'COMPRA_PRODUTO', 'CPR_TOTAL', 		NULL, NEW.cpr_total::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_compra_produto
AFTER INSERT ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_compra_produto();




-- FORMAPAGTO
CREATE OR REPLACE FUNCTION trg_log_formapagto()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORMAPAGTO', 'FPG_CODIGO', NULL, NEW.fpg_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORMAPAGTO', 'FPG_NOME', 	NULL, NEW.fpg_nome);
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORMAPAGTO', 'FPG_ATIVO', 	NULL, NEW.fpg_ativo::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_formapagto
AFTER INSERT ON formapagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_formapagto();




-- FORNECEDOR
CREATE OR REPLACE FUNCTION trg_log_fornecedor()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORNECEDOR', 'FOR_CODIGO', 	NULL, NEW.for_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORNECEDOR', 'PES_CODIGO',		NULL, NEW.pes_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'FORNECEDOR', 'FOR_CONTATO',	NULL, NEW.for_contato);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_fornecedor
AFTER INSERT ON fornecedor
FOR EACH ROW
EXECUTE FUNCTION trg_log_fornecedor();





-- PESSOA
CREATE OR REPLACE FUNCTION trg_log_pessoa()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_NOME', 			NULL, NEW.pes_nome);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_FANTASIA',		NULL, NEW.pes_fantasia);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_FISICA', 		NULL, NEW.pes_fisica::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_CPFCNPJ', 		NULL, NEW.pes_cpfcnpj);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_RGIE', 			NULL, NEW.pes_rgie);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_DTCADASTRO',     NULL, NEW.pes_dtcadastro::varchar); 
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_ENDERECO', 		NULL, NEW.pes_endereco);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_NUMERO', 		NULL, NEW.pes_numero);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_COMPLEMENTO',	NULL, NEW.pes_complemento);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_BAIRRO', 		NULL, NEW.pes_bairro);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_CIDADE', 		NULL, NEW.pes_cidade);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_UF', 			NULL, NEW.pes_uf::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_CEP', 			NULL, NEW.pes_cep);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_FONE1', 			NULL, NEW.pes_fone1);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_FONE2', 			NULL, NEW.pes_fone2);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_CELULAR', 		NULL, NEW.pes_celular);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_SITE', 			NULL, NEW.pes_site);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_EMAIL', 			NULL, NEW.pes_email);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_ATIVO', 			NULL, NEW.pes_ativo::varchar);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_pessoa
AFTER INSERT ON pessoa
FOR EACH ROW
EXECUTE FUNCTION trg_log_pessoa();





-- PRODUTO
CREATE OR REPLACE FUNCTION trg_log_produto()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_CODIGO', 	NULL, NEW.pro_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_NOME', 		NULL, NEW.pro_nome);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_ESTOQUE', 	NULL, NEW.pro_estoque::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_UNIDADE', 	NULL, NEW.pro_unidade::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_PRECO', 	NULL, NEW.pro_preco::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_CUSTO', 	NULL, NEW.pro_custo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_ATACADO', 	NULL, NEW.pro_atacado::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_MIN', 		NULL, NEW.pro_min::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_MAX', 		NULL, NEW.pro_max::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_EMBALAGEM', NULL, NEW.pro_embalagem::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_PESO', 		NULL, NEW.pro_peso::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_DTCADASTRO',NULL, NEW.pro_dtcadastro::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_OBS', 		NULL, NEW.pro_obs::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_ATIVO', 	NULL, NEW.pro_ativo::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_produto
AFTER INSERT ON produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_produto();




-- USUARIO
CREATE OR REPLACE FUNCTION trg_log_usuario()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_CODIGO', 	NULL, NEW.usu_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_NOME', 		NULL, NEW.usu_nome);
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_LOGIN', 	NULL, NEW.usu_login);
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_SENHA', 	NULL, NEW.usu_senha);
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_CADASTRO', 	NULL, NEW.usu_cadastro::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'USUARIO', 'USU_ATIVO', 	NULL, NEW.usu_ativo::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_usuario
AFTER INSERT ON usuario
FOR EACH ROW
EXECUTE FUNCTION trg_log_usuario();




-- VENDA
CREATE OR REPLACE FUNCTION trg_log_venda()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_CODIGO',	NULL, NEW.vda_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'USU_CODIGO',	NULL, NEW.usu_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'CLI_CODIGO',	NULL, NEW.cli_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_DATA', 	NULL, NEW.vda_data::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_VALOR', 	NULL, NEW.vda_valor::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_DESCONTO',NULL, NEW.vda_desconto::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_TOTAL', 	NULL, NEW.vda_total::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA', 'VDA_OBS', 	NULL, NEW.vda_obs::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_venda
AFTER INSERT ON venda
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda();





-- VENDA_PAGTO
CREATE OR REPLACE FUNCTION trg_log_venda_pagto()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PAGTO', 'VDP_CODIGO', NULL, NEW.vdp_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PAGTO', 'VDA_CODIGO', NULL, NEW.vda_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PAGTO', 'FPG_CODIGO', NULL, NEW.fpg_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PAGTO', 'VDP_VALOR',  NULL, NEW.vdp_valor::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_venda_pagto
AFTER INSERT ON venda_pagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda_pagto();





-- VENDA_PRODUTO
CREATE OR REPLACE FUNCTION trg_log_venda_produto()
RETURNS TRIGGER AS
$$
BEGIN
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VEP_CODIGO', 	NULL, NEW.vep_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VDA_CODIGO', 	NULL, NEW.vda_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'PRO_CODIGO',	NULL, NEW.pro_codigo::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VEP_QTDE', 	NULL, NEW.vep_qtde::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VEP_PRECO', 	NULL, NEW.vep_preco::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VEP_DESCONTO',NULL, NEW.vep_desconto::varchar);
    PERFORM Func_Gravar_Log('INCLUSAO', 'VENDA_PRODUTO', 'VEP_TOTAL', 	NULL, NEW.vep_total::varchar);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_insert_venda_produto
AFTER INSERT ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda_produto();









/* ==========================================================
   TRIGGER para UPDATE 
   ========================================================== */

CREATE OR REPLACE FUNCTION trg_log_generic_upd()
RETURNS TRIGGER AS
$$
DECLARE 
	key 	varchar; -- nome da coluna que mudou os dados
	old_val varchar; -- valor antigo
	new_val varchar; -- valor novo 
BEGIN
	-- Transformando OLD e NEW em hstores
	FOR key, old_val, new_val IN 
		SELECT old_kv.key::varchar, old_kv.value::varchar, new_kv.value::varchar -- subquery, que retorna apenas se houve alteração
/* 
HSTORE -> transforma a linha em uma mapa (coluna => valor) (key => value)
EACH -> separa cada mapa em linhas individuais 
*/
		FROM each(hstore(OLD)) AS old_kv
		JOIN each(hstore(NEW)) AS new_kv ON old_kv.key = new_kv.key -- faz o join pela coluna 
		WHERE
			old_kv.value IS DISTINCT FROM new_kv.value -- aqui filtro as colunas que realmente mudaram e uso elas
		LOOP
			-- Registrando cada alteração
			PERFORM Func_Gravar_Log (
				'ALTERACAO'::varchar,
				TG_TABLE_NAME::varchar, -- Variável especial de triggers no PostgreSQ
				key,
				old_val,
				new_val
			);
		END LOOP;

		RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER after_update_cliente
AFTER UPDATE ON cliente
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_compra
AFTER UPDATE ON compra
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();


CREATE TRIGGER after_update_compra_produto
AFTER UPDATE ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_formapagto
AFTER UPDATE ON formapagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_fornecedor
AFTER UPDATE ON fornecedor
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();


CREATE TRIGGER after_update_pessoa
AFTER UPDATE ON pessoa
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_produto
AFTER UPDATE ON produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_usuario
AFTER UPDATE ON usuario
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_venda
AFTER UPDATE ON venda
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_venda_pagto
AFTER UPDATE ON venda_pagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();

CREATE TRIGGER after_update_venda_produto
AFTER UPDATE ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_upd();


/* ==========================================================
   DELETE - TODAS AS TABELAS
   ========================================================== */

CREATE OR REPLACE FUNCTION trg_log_generic_del()
RETURNS TRIGGER AS
$$
DECLARE
    v_col  varchar;
    v_old  varchar;
BEGIN
    -- Itera por todas as colunas da linha antiga
    FOR v_col, v_old IN
        SELECT k::varchar, v::varchar          -- usa os aliases k, v
        FROM each(hstore(OLD)) AS e(k, v)      -- define os aliases aqui
    LOOP
        PERFORM func_gravar_log(
            'EXCLUSAO'::varchar,               -- tipo de operação
            TG_TABLE_NAME::varchar,            -- 'name' -> varchar
            v_col,                             -- nome da coluna
            v_old,                             -- valor antigo
            NULL::varchar                      -- valor novo (DELETE)
        );
    END LOOP;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


-- CLIENTE
CREATE TRIGGER after_delete_cliente
AFTER DELETE ON cliente
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- COMPRA
CREATE TRIGGER after_delete_compra
AFTER DELETE ON compra
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- COMPRA_PRODUTO
CREATE TRIGGER after_delete_compra_produto
AFTER DELETE ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- FORMAPAGTO
CREATE TRIGGER after_delete_formapagto
AFTER DELETE ON formapagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- FORNECEDOR
CREATE TRIGGER after_delete_fornecedor
AFTER DELETE ON fornecedor
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- PESSOA
CREATE TRIGGER after_delete_pessoa
AFTER DELETE ON pessoa
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- PRODUTO
CREATE TRIGGER after_delete_produto
AFTER DELETE ON produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- USUARIO
CREATE TRIGGER after_delete_usuario
AFTER DELETE ON usuario
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- VENDA
CREATE TRIGGER after_delete_venda
AFTER DELETE ON venda
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- VENDA_PAGTO
CREATE TRIGGER after_delete_venda_pagto
AFTER DELETE ON venda_pagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();

-- VENDA_PRODUTO
CREATE TRIGGER after_delete_venda_produto
AFTER DELETE ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_generic_del();



SELECT * FROM pessoa;
SELECT * FROM cliente;

UPDATE cliente c
	SET cli_limitecred = 10
	WHERE c.cli_codigo = 1;

DELETE 	FROM cliente WHERE cli_codigo = 1;

INSERT INTO pessoa (pes_nome, pes_fantasia, pes_fisica, pes_cpfcnpj, pes_rgie, pes_dtcadastro,
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
                     pro_min, pro_max, pro_embalagem, pro_peso, pro_dtcadastro, pro_obs, pro_ativo)
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
