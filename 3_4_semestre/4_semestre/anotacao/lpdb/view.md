# Criar uma VIEW


``` SQL
    CREATE VIEW view_teste 
    AS 
        SELECT pro_codigo, pro_nome, pro_estoque
        FROM produto
        WHERE pro_estoque > 0;
```




