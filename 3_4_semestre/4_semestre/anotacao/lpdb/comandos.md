#   Exemplos de Comandos 

## ALTER 

### Criar chave primaria (primary key) 

```sql

alter table teste 
    add constraint 'pk_teste' primary key (teste_id); -- aqui vc define o nome da sua constraint 
    -- OU 
    add primary key (teste_id); -- aqui vc não cria o nome da constraint, o DB cria sozinho

```

***Usando o `SERIAL`***

> cria ID auto auto increment

Para usar o SERIAL temos q seguir esses passos:

1. Criar uma sequence (sequencia):
    
    ``` sql 
        CREATE SEQUENCE cliente_cli_cod_seq;
    ```

2. Conectar a coluna

    ``` sql 
        ALTER TABLE cliente
            ALTER COLUMN cli_codigo SET DEFAULT nextval('cliente_cli_cod_seq');

            --  nextval function (commonly used with sequences),
    ```
3. Criar a PK com CONSTRAINT

    ```sql 
        ALTER TABLE cliente 
            add constraint pk_cliente primary key (cli_codigo);
    ```
    

<br>

---

### Criar chave estrangeira (foreign key)

```sql
alter table teste2
    add constraint 'fk_teste' foreign key (teste_id) references teste (teste_id);
    -- OU
    add foreign key (teste_id) references teste (teste_id);
```

---
<br><br><br>

### Adicionar coluna

```sql
alter table table_name
    add name_column data_tapy;
```


