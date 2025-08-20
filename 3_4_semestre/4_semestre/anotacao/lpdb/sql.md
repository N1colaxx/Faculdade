# DB usado na facu Postgreas 

<br><br><br>

# Comandos DDL, DML e DCL

> ***DDL > Linguage de Definiçao de Dados(Data Definition Languege)***

-   CREATE
    > TABLE

-   DROP
    >   TABLE, COLUMN, DATABASE

-   ALTER
    >   ADD, DROP

-   TRUNCADE (apaga os registros de uma tabela)
-   COMMENT (add comentarios a tabelas/colunas)
-   RENAME

    ---

### DML

> ***DML > Linguagem de Manipulação de Dados (Data Manipulation Language)***

-   SELECT
-   INSERT
-   UPDATE
-   DELETE

    ---
    
### DCL

> ***DCL > Linguagem de Controle de Dados (Data Control Language)***

-   GRANT : 
-   REVOKE 

---
<br><br><br>

# Constraints - Integridade Referencial - Restrição de Integridade (RI)

### Constraints

Regras agregadas a uma coluna ou a uma tabela. Assim definindo: em `colunas` campos obrigatorios ou não obrigatorios, ja  `tabelas` chaves PK ou FK.


-   SET NULL
-   SET DEFAULT
-   NOT DEFAULT
-   UNIQUE
-   CHECK
-   CASCATE
-   NO ACTION
-   RESTRICT

### Restrição de Integridade (RI)
Comdição da base de dados para restringir a info a ser armazenada.

As RI tem 2 modos:

1. *Na especificação da RI*: se da na definição do esquema, definido pelo user ou dba

2.  *Na conferencia*: È feita pelo banco de dados apos toda modificação ou aplicação sendo execução 


