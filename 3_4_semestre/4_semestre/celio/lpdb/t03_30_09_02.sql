-- extensão hstore -> para transformar linhas em mapas chave-valor
CREATE EXTENSION IF NOT EXISTS hstore;

DROP TABLE IF EXISTS log CASCADE;

CREATE TABLE log (
	log_id serial NOT NULL,
	log_data date,
	log_operacao varchar(10),
	log_tabel varchar(30),
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
    PERFORM Func_Gravar_Log('INCLUSAO', 'PESSOA', 'PES_CADASTRO', 		NULL, NEW.pes_cadastro::varchar); 
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
    PERFORM Func_Gravar_Log('INCLUSAO', 'PRODUTO', 'PRO_CADASTRO', 	NULL, NEW.pro_cadastro::varchar);
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

-- CLIENTE
CREATE OR REPLACE FUNCTION trg_log_cliente_upd()
RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.cli_limitecred IS DISTINCT FROM NEW.cli_limitecred THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','CLIENTE','CLI_LIMITECRED',
			OLD.cli_limitecred::varchar,
			NEW.cli_limitecred::varchar
		);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_cliente
AFTER UPDATE ON cliente
FOR EACH ROW
EXECUTE FUNCTION trg_log_cliente_upd();




-- COMPRA
CREATE OR REPLACE FUNCTION trg_log_compra_upd()
RETURNS TRIGGER AS
$$
BEGIN
	IF OLD.cpr_emissao IS DISTINCT FROM NEW.cpr_emissao THEN
		PERFORM Func_Gravar_Log (
			'ALTERACAO', 'COMPRA', 'CPR_EMISSAO',
			OLD.cpr_emissao::varchar,
			NEW.cpr_emissao::varchar
		);
	END IF;

	IF OLD.cpr_valor IS DISTINCT FROM NEW.cpr_valor THEN 
		PERFORM Func_Gravar_Log (
			'ALTERACAO', 'COMPRA', 'CPR_VALOR',
			OLD.cpr_valor::varchar,
			NEW.cpr_valor::varchar
		);
	END IF;

	IF OLD.cpr_desconto IS DISTINCT FROM NEW.cpr_desconto THEN 
		PERFORM Func_Gravar_Log (
			'ALTERACAO', 'COMPRA', 'CPR_DESCONTO',
			OLD.cpr_desconto::varchar,
			NEW.cpr_desconto::varchar
		);
	END IF;

    IF OLD.cpr_total IS DISTINCT FROM NEW.cpr_total THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','COMPRA','CPR_TOTAL',
			OLD.cpr_total::varchar,
			NEW.cpr_total::varchar
		);
    END IF;

	IF OLD.cpr_dtentrada IS DISTINCT FROM NEW.cpr_dtentrada THEN 
		PERFORM Func_Gravar_Log (
			'ALTERACAO', 'COMPRA', 'CPR_DTENTRADA',
			OLD.cpr_dtentrada::varchar,
			NEW.cpr_dtentrada::varchar
		);
	END IF;

	IF OLD.cpr_obs IS DISTINCT FROM NEW.cpr_obs THEN 
		PERFORM Func_Gravar_Log (
			'ALTERACAO', 'COMPRA', 'CPR_OBS',
			OLD.cpr_obs::varchar,
			NEW.cpr_obs::varchar
		);
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_compra
AFTER UPDATE ON compra
FOR EACH ROW
EXECUTE FUNCTION trg_log_compra_upd();




-- COMPRA_PRODUTO
CREATE OR REPLACE FUNCTION trg_log_compra_produto_upd()
RETURNS TRIGGER AS
$$
BEGIN

	IF OLD.pro_codigo IS DISTINCT FROM NEW.pro_codigo THEN
        PERFORM Func_Gravar_Log(
			'ALTERACAO','COMPRA_PRODUTO','PRO_CODIGO',
			OLD.pro_codigo::varchar,
			NEW.pro_codigo::varchar
		);
    END IF;

    IF OLD.cpr_qtde IS DISTINCT FROM NEW.cpr_qtde THEN
        PERFORM Func_Gravar_Log(
			'ALTERACAO','COMPRA_PRODUTO','CPR_QTDE',
			OLD.cpr_qtde::varchar,
			NEW.cpr_qtde::varchar
		);
    END IF;


    IF OLD.cpr_desconto IS DISTINCT FROM NEW.cpr_desconto THEN
        PERFORM Func_Gravar_Log(
			'ALTERACAO','COMPRA_PRODUTO','CPR_DESCONTO',
			OLD.cpr_desconto::varchar,
			NEW.cpr_desconto::varchar
		);
    END IF;


    IF OLD.cpr_total IS DISTINCT FROM NEW.cpr_total THEN
        PERFORM Func_Gravar_Log(
			'ALTERACAO','COMPRA_PRODUTO','CPR_TOTAL',
			OLD.cpr_total::varchar,
			NEW.cpr_total::varchar
		);
    END IF;


    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_compra_produto
AFTER UPDATE ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_compra_produto_upd();




-- FORMAPAGTO
CREATE OR REPLACE FUNCTION trg_log_formapagto_upd()
RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.fpg_nome IS DISTINCT FROM NEW.fpg_nome THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','FORMAPAGTO','FPG_NOME',
			OLD.fpg_nome,
			NEW.fpg_nome
		);
    END IF;

    IF OLD.fpg_ativo IS DISTINCT FROM NEW.fpg_ativo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','FORMAPAGTO','FPG_ATIVO',
			OLD.fpg_ativo::varchar,
			NEW.fpg_ativo::varchar
		);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_formapagto
AFTER UPDATE ON formapagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_formapagto_upd();




-- FORNECEDOR
CREATE OR REPLACE FUNCTION trg_log_fornecedor_upd()
RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.for_contato IS DISTINCT FROM NEW.for_contato THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','FORNECEDOR','FOR_CONTATO',
			OLD.for_contato,
			NEW.for_contato
		);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_fornecedor
AFTER UPDATE ON fornecedor
FOR EACH ROW
EXECUTE FUNCTION trg_log_fornecedor_upd();




-- PESSOA
CREATE OR REPLACE FUNCTION trg_log_pessoa_upd()
RETURNS TRIGGER AS
$$
DECLARE 
	key 	varchar; -- nome da coluna que mudou os dados
	old_val varchar; -- valor antigo
	new_val varchar; -- valor novo 
BEGIN
	-- Transformando OLD e NEW em hstores
	FOR key, old_val, new_val IN 
		SELECT old_kv.key, old_kv.value::varchar, new_kv.value::varchar -- subquery, que retorna apenas se houve alteração
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
				'ALTERACAO',
				TG_TABLE_NAME, -- Variável especial de triggers no PostgreSQ
				key,
				old_val,
				new_val
			);
		END LOOP;

		RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_pessoa
AFTER UPDATE ON pessoa
FOR EACH ROW
EXECUTE FUNCTION trg_log_pessoa_upd();




-- PRODUTO	
CREATE OR REPLACE FUNCTION trg_log_produto_upd()
RETURNS TRIGGER AS
$$
DECLARE
	key		varchar;
	old_val varchar;
	new_val varchar;
BEGIN
	FOR key, old_val, new_val IN
		SELECT old_kv.key, old_kv.value::varchar, new_kv.value::varchar
		FROM each(hstore(OLD)) AS old_kv
		JOIN each(hstore(NEW)) AS new_kv ON new_kv.key = old_kv.key
		WHERE old_kv.value IS DISTINCT FROM new_kv.value
	LOOP
		PERFORM Func_Gravar_Log(
			'ALTERACAO',
			TG_TABLE_NAME,
			key,
			old_val,
			new_val
		);
	END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER after_update_produto
AFTER UPDATE ON produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_produto_upd();





-- USUARIO
CREATE OR REPLACE FUNCTION trg_log_usuario_upd()
RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.usu_nome IS DISTINCT FROM NEW.usu_nome THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','USUARIO','USU_NOME',
			OLD.usu_nome,
			NEW.usu_nome
		);
    END IF;

    IF OLD.usu_login IS DISTINCT FROM NEW.usu_login THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','USUARIO','USU_LOGIN',
			OLD.usu_login,
			NEW.usu_login
		);
    END IF;

    IF OLD.usu_senha IS DISTINCT FROM NEW.usu_senha THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','USUARIO','USU_SENHA',
			OLD.usu_senha,
			NEW.usu_senha
		);
    END IF;

    IF OLD.usu_cadastro IS DISTINCT FROM NEW.usu_cadastro THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','USUARIO','USU_CADASTRO',
			OLD.usu_cadastro::varchar,
			NEW.usu_cadastro::varchar
		);
    END IF;

    IF OLD.usu_ativo IS DISTINCT FROM NEW.usu_ativo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','USUARIO','USU_ATIVO',
			OLD.usu_ativo::varchar,
			NEW.usu_ativo::varchar
		);
    END IF;


    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_usuario
AFTER UPDATE ON usuario
FOR EACH ROW
EXECUTE FUNCTION trg_log_usuario_upd();



-- VENDA
CREATE OR REPLACE FUNCTION trg_log_venda_upd()
RETURNS TRIGGER AS
$$
BEGIN

    IF OLD.usu_codigo IS DISTINCT FROM NEW.usu_codigo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','USU_CODIGO',
			OLD.usu_codigo::varchar,
			NEW.usu_codigo::varchar);
    END IF;

    IF OLD.cli_codigo IS DISTINCT FROM NEW.cli_codigo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','CLI_CODIGO',
			OLD.cli_codigo::varchar,
			NEW.cli_codigo::varchar);
    END IF;

    IF OLD.vda_data IS DISTINCT FROM NEW.vda_data THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','VDA_DATA',
			OLD.vda_data::varchar,
			NEW.vda_data::varchar);
    END IF;

    IF OLD.vda_valor IS DISTINCT FROM NEW.vda_valor THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','VDA_VALOR',
			OLD.vda_valor::varchar,
			NEW.vda_valor::varchar);
    END IF;

    IF OLD.vda_desconto IS DISTINCT FROM NEW.vda_desconto THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','VDA_DESCONTO',
			OLD.vda_desconto::varchar,
			NEW.vda_desconto::varchar);
    END IF;


    IF OLD.vda_total IS DISTINCT FROM NEW.vda_total THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','VDA_TOTAL',
			OLD.vda_total::varchar,
			NEW.vda_total::varchar);
    END IF;

    IF OLD.vda_obs IS DISTINCT FROM NEW.vda_obs THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA','VDA_OBS',
			OLD.vda_obs::varchar,
			NEW.vda_obs::varchar);
    END IF;


    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_venda
AFTER UPDATE ON venda
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda_upd();




-- VENDA_PAGTO
CREATE OR REPLACE FUNCTION trg_log_venda_pagto_upd()
RETURNS TRIGGER AS
$$
BEGIN

    IF OLD.fpg_codigo IS DISTINCT FROM NEW.fpg_codigo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PAGTO','FPG_CODIGO',
			OLD.fpg_codigo::varchar,
			NEW.fpg_codigo::varchar
		);
    END IF;

    IF OLD.vdp_valor IS DISTINCT FROM NEW.vdp_valor THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PAGTO','VDP_VALOR',
			OLD.vdp_valor::varchar,
			NEW.vdp_valor::varchar
		);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_venda_pagto
AFTER UPDATE ON venda_pagto
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda_pagto_upd();





-- VENDA_PRODUTO
CREATE OR REPLACE FUNCTION trg_log_venda_produto_upd()
RETURNS TRIGGER AS
$$
BEGIN


    IF OLD.pro_codigo IS DISTINCT FROM NEW.pro_codigo THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PRODUTO','PRO_CODIGO',
			OLD.pro_codigo::varchar,
			NEW.pro_codigo::varchar
		);
    END IF;

    IF OLD.vep_qtde IS DISTINCT FROM NEW.vep_qtde THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PRODUTO','VEP_QTDE',
			OLD.vep_qtde::varchar,
			NEW.vep_qtde::varchar
		);
    END IF;


    IF OLD.vep_preco IS DISTINCT FROM NEW.vep_preco THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PRODUTO','VEP_PRECO',
			OLD.vep_preco::varchar,
			NEW.vep_preco::varchar
		);
    END IF;


    IF OLD.vep_desconto IS DISTINCT FROM NEW.vep_desconto THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PRODUTO','VEP_DESCONTO',
			OLD.vep_desconto::varchar,
			NEW.vep_desconto::varchar
		);
    END IF;

    IF OLD.vep_total IS DISTINCT FROM NEW.vep_total THEN
        PERFORM Func_Gravar_Log (
			'ALTERACAO','VENDA_PRODUTO','VEP_TOTAL',
			OLD.vep_total::varchar,
			NEW.vep_total::varchar
		);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_update_venda_produto
AFTER UPDATE ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_log_venda_produto_upd();



/* ==========================================================
   DELETE - TODAS AS TABELAS
   ========================================================== */


CREATE OR REPLACE FUNCTION trg_log_generic_del()
RETURNS TRIGGER AS
$$
DECLARE
    key varchar;
    old_val varchar;
BEGIN
    -- Itera por todas as colunas da linha antiga
    FOR key, old_val IN
        SELECT key, value::varchar
        FROM each(hstore(OLD))
    LOOP
        PERFORM Func_Gravar_Log(
            'EXCLUSAO',       -- tipo de operação
            TG_TABLE_NAME,    -- nome da tabela
            key,              -- nome da coluna
            old_val,          -- valor antigo
            NULL              -- valor novo (sempre NULL em DELETE)
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
