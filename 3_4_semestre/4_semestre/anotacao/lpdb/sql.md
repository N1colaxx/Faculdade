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

<br><br><br>



# Comandos SQL para JBDC

https://castelano.com.br/site/aulas/alpoo/Aula%2005%20-%20Introdu%C3%A7%C3%A3o%20ao%20JDBC.pdf

slid -> 1, 2, 

# 🔑 Principais classes e interfaces do JDBC

`DriverManager` → gerencia a conexão com o banco.
    
`Connection`→ representa a conexão aberta.

`Statement` → executa comandos SQL simples (sem parâmetros).

`PreparedStatement` → executa comandos SQL com parâmetros (mais seguro contra SQL Injection).

`ResultSet` → guarda os resultados de um SELECT.