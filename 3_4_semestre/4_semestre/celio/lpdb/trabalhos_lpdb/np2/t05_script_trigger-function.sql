CREATE OR REPLACE FUNCTION trg_calcula_total_compra()
RETURNS TRIGGER AS
$$
DECLARE
   c_cpr_codigo INTEGER;
   c_total_item NUMERIC;
BEGIN
   -- Identifica o código da compra envolvida
   IF (TG_OP = 'INSERT') THEN
      c_cpr_codigo := NEW.cpr_codigo;
      c_total_item := (NEW.cpr_qtde * NEW.cpr_preco) - NEW.cpr_desconto;

      -- Corrige o total no item recém inserido
      UPDATE compra_produto
         SET cpr_total = c_total_item
       WHERE cpp_codigo = NEW.cpp_codigo;

   ELSIF (TG_OP = 'UPDATE') THEN
      	c_cpr_codigo := NEW.cpr_codigo;
		c_total_item := (NEW.cpr_qtde * NEW.cpr_preco) - NEW.cpr_desconto;

--	Verifica quantas vezes foi chamado, se for MAIOR q 1, ele para, assim evita  recursão;
-- tem isso pois como faz outro update dentra da propia def.
        IF pg_trigger_depth() = 1 THEN
            UPDATE compra_produto
               SET cpr_total = c_total_item
             WHERE cpp_codigo = NEW.cpp_codigo;
        END IF;
   ELSE
      c_cpr_codigo := OLD.cpr_codigo;
   END IF;

   -- Atualiza o total da compra após a operação
   UPDATE compra AS c
      SET cpr_total = (
						SELECT COALESCE( SUM(cpr_total), 0)
                        FROM compra_produto AS cp
                        WHERE cp.cpr_codigo = c_cpr_codigo
					) - c.cpr_desconto
    WHERE c.cpr_codigo = c_cpr_codigo;

   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calcula_total_compra
AFTER INSERT OR UPDATE OR DELETE
ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_calcula_total_compra();


CREATE OR REPLACE FUNCTION trg_calcula_total_venda()
RETURNS TRIGGER AS
$$
DECLARE
    v_vda_codigo INTEGER;
	v_valor_item NUMERIC;
BEGIN
   IF (TG_OP = 'INSERT') THEN
       v_vda_codigo := NEW.vda_codigo;
       v_valor_item:= (NEW.vep_qtde * NEW.vep_preco) - NEW.vep_desconto;
		
		UPDATE venda_produto
			SET vep_total = v_valor_item
		WHERE vep_codigo = NEW.vep_codigo;
		
   ELSIF (TG_OP = 'UPDATE') THEN
      v_vda_codigo := NEW.vda_codigo;
      v_valor_item := (NEW.vep_qtde * NEW.vep_preco) - NEW.vep_desconto;

		if pg_trigger_depth() = 1 then
				UPDATE venda_produto
					SET vep_total = v_valor_item
				WHERE vep_codigo = NEW.vep_codigo;
		end if;

   ELSE
      v_vda_codigo := OLD.vda_codigo;
   END IF;

   UPDATE venda AS v
      SET vda_total = (
						SELECT COALESCE( SUM(vep_total), 0)
                       	FROM venda_produto AS vp
                       	WHERE vp.vda_codigo = v_vda_codigo 
					)- v.vda_desconto
    WHERE v.vda_codigo = v_vda_codigo;

   RETURN COALESCE(NEW, OLD);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calcula_total_venda
AFTER INSERT OR UPDATE OR DELETE
ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_calcula_total_venda();


CREATE OR REPLACE FUNCTION trg_controle_estoque_compra()
RETURNS TRIGGER AS
$$
DECLARE
   dif NUMERIC(10,2);
BEGIN
   IF (TG_OP = 'INSERT') THEN
      UPDATE produto
         SET pro_estoque = pro_estoque + NEW.cpr_qtde
       WHERE pro_codigo = NEW.pro_codigo;

   ELSIF (TG_OP = 'UPDATE') THEN
      dif := NEW.cpr_qtde - OLD.cpr_qtde;
      UPDATE produto
         SET pro_estoque = pro_estoque + dif
       WHERE pro_codigo = NEW.pro_codigo;

   ELSIF (TG_OP = 'DELETE') THEN
      UPDATE produto
         SET pro_estoque = pro_estoque - OLD.cpr_qtde
       WHERE pro_codigo = OLD.pro_codigo;
   END IF;

  RETURN COALESCE(NEW, OLD);
END;
$$ 
LANGUAGE plpgsql;

CREATE TRIGGER trg_controle_estoque_compra
AFTER INSERT OR UPDATE OR DELETE
ON compra_produto
FOR EACH ROW
EXECUTE FUNCTION trg_controle_estoque_compra();


CREATE OR REPLACE FUNCTION trg_controle_estoque_venda()
RETURNS TRIGGER AS
$$
DECLARE
   dif NUMERIC(10,2);
BEGIN
   IF (TG_OP = 'INSERT') THEN
      UPDATE produto
         SET pro_estoque = pro_estoque - NEW.vep_qtde
       WHERE pro_codigo = NEW.pro_codigo;

   ELSIF (TG_OP = 'UPDATE') THEN
      dif := NEW.vep_qtde - OLD.vep_qtde;
      UPDATE produto
         SET pro_estoque = pro_estoque - dif
       WHERE pro_codigo = NEW.pro_codigo;

   ELSIF (TG_OP = 'DELETE') THEN
      UPDATE produto
         SET pro_estoque = pro_estoque + OLD.vep_qtde
       WHERE pro_codigo = OLD.pro_codigo;
   END IF;

   RETURN COALESCE(NEW, OLD);
END;
$$ 
LANGUAGE plpgsql;

CREATE TRIGGER trg_controle_estoque_venda
AFTER INSERT OR UPDATE OR DELETE
ON venda_produto
FOR EACH ROW
EXECUTE FUNCTION trg_controle_estoque_venda();

