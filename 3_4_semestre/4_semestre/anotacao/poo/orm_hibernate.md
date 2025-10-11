# O.R.M - Mapeamento de Objeto Relacional (Object Relational Mapping)

Faz a transformação de objetos e linhas de tabelas, convertendo objetos em tabelas e tabelas em objetos.



## JAPA - Java Persistence API

## Hibernate


É dividito em 3 partes principais:

1. Hibenate Core ou Hibernate3

2. Hibenate Annotaions
-   
3. Hibenete EntyManager


### Arquitetura

As 4 interfaces principais:

1. 

2. 

3. 

4. 

### Comandos

**`persist(Object entity)`**  -> INSERT INTO 

-   ```java
    Pessoa p = new Pessoa("João");
    session.persist(p);
    ```

    ---
    <br>

**`merge(Object entity)`** -> UPDATE
-   ```java
    Pessoa p = new Pessoa("João");
    session.persist(p);
    ```

    ---
    <br>

**`remove   (Object entity)`** -> UPDATE
-   ```java
    Pessoa p = new Pessoa("João");
    session.persist(p);
    ```



