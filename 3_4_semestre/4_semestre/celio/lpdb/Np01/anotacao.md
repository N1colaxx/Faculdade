# Linguagens DDL,DML, CDL

## Principais de cada um.

### DDL - Data Definition Language

-   CREATE
-   ALTER -> modify, add, drop
-   DROP

### DML - Data Manipulation Linguage

-   SELECT
-   INSERT
-   UPDATE

    ```sql
    UPDATE funcionario as f
        SET nome = "Nicolas B"
        WHERE id = 1; 
    ```
    
-   DELET
-   WHERE

### DCL - Data Control Language

-   GRANT
    > Permite dar permições a UM ou MAIS usuarios

-   REVOKE
    > Negar as permição dadas pelo GRANK



## Aleatorio

lista -> IN ()
between


# Duvida

1. O que fazer na hora de definir a tabela se der um FK OPCIONAl ? 
    ex: 
                min,max      min,max
        empresa   |o  -> {} <- |N  departamento

    Crio a tab assim:

        create table departamento
            dep_id int serial not null,
            nome varchar (80),
            fk_departamento_empresa int;

    OU crio assim:


    create table departamento
        dep_id int serial not null,
        nome varchar (80),
        fk_departamento_empresa int not null;
