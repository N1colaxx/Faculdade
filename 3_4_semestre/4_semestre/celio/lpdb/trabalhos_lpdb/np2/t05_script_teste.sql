SELECT current_database();


/**
 * Teste Compra
 * */
DELETE FROM compra_produto cp  WHERE cp.cpp_codigo > 999;
DELETE FROM compra c WHERE c.cpr_codigo > 199;

SELECT * FROM compra c WHERE c.cpr_codigo > 199;

INSERT INTO compra (cpr_desconto, cpr_total, cpr_obs , usu_codigo, for_codigo ) VALUES
(0, 0, 'teste das trigger - valor total compra', 3, 3);
 
INSERT INTO compra (cpr_desconto, cpr_total, cpr_obs , usu_codigo, for_codigo ) VALUES
(0, 0, 'teste das trigger - controle de Estoque', 3, 3);

INSERT INTO compra_produto (cpr_qtde, cpr_preco, cpr_desconto, cpr_total, cpr_codigo, pro_codigo) VALUES 
(5, 10, 0, 0, 211, 1);
SELECT * FROM compra_produto cp  WHERE cp.cpp_codigo > 999;


/**
 * Teste Venda
 * */
DELETE FROM venda_produto  vp  WHERE vep_codigo > 1000;
DELETE FROM venda WHERE vda_codigo > 200;

SELECT * FROM venda WHERE vda_codigo > 200;

INSERT INTO venda (vda_desconto, vda_total, vda_obs, usu_codigo, cli_codigo) VALUES 
(0, 0, 'Teste para as trigger - valor total venda', 5, 5);

INSERT INTO venda (vda_desconto, vda_total, vda_obs, usu_codigo, cli_codigo) VALUES 
(0, 0, 'Teste para as trigger - controle estoque', 5, 5);

INSERT INTO venda_produto (vep_qtde, vep_preco, vep_desconto, vep_total, vda_codigo, pro_codigo) VALUES 
(5, 10, 0, 0, 210, 1);
SELECT * FROM venda_produto  vp  WHERE vep_codigo > 1000;



/**
 * Teste Estoque de Produto
 * */
DELETE FROM produto WHERE pro_codigo > 200;
SELECT pro_codigo, pro_nome, pro_estoque FROM produto WHERE pro_codigo > 200;

INSERT INTO produto (pro_nome, pro_estoque, pro_obs) VALUES
('Pao_Baiano', 100, 'Teste para as triggers');



-- Compra
SELECT * FROM compra c  WHERE c.cpr_codigo > 200;
SELECT * FROM compra_produto cp  WHERE cp.cpp_codigo > 999;
INSERT INTO compra_produto (cpr_qtde, cpr_preco, cpr_desconto, cpr_total, cpr_codigo, pro_codigo) VALUES 
(10, 10, 0, 0, 212, 208);

UPDATE compra_produto  
	SET cpr_qtde = 20
	WHERE cpr_codigo = 212;

-- Venda
SELECT * FROM venda v  WHERE v.vda_codigo > 200;
SELECT * FROM venda_produto  vp  WHERE vep_codigo > 1000;
INSERT INTO venda_produto (vep_qtde, vep_preco, vep_desconto, vep_total, vda_codigo, pro_codigo) VALUES 
(20, 10, 0, 0, 211, 208);

UPDATE venda_produto  
	SET vep_qtde = 20
	WHERE vda_codigo = 211;
