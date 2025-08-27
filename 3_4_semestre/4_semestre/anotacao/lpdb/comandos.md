#   Exemplos de Comandos 

## ALTER 

### Criar chave primaria (primary key) 

```sql

alter table teste 
    add constraint 'pk_teste' primary key (teste_id); -- aqui vc define o nome da sua constraint 
    -- OU 
    add primary key (teste_id); -- aqui vc não cria o nome da constraint, o DB cria sozinho

```
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


