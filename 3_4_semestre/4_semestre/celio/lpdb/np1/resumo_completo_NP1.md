# Resumo Completo NP1

## SGBD
Um **Sistema Gerenciador de Banco de Dados (SGBD)** é o software que permite criar, manipular e administrar bancos de dados de forma estruturada.  
### Funções principais:
- Definição de dados (criar tabelas, esquemas e relacionamentos).  
- Manipulação de dados (inserir, alterar, excluir e consultar).  
- Controle de acesso (segurança, usuários, permissões).  
- Controle de concorrência (acesso simultâneo sem conflitos).  
- Recuperação (backup e restauração).  
- Garantia de **integridade** e **consistência** dos dados.  

Exemplos: MySQL, PostgreSQL, Oracle, SQL Server, SQLite.  

---

## Integridade Referencial
- Conjunto de regras que asseguram consistência entre **chaves primárias (PK)** e **chaves estrangeiras (FK)**.  
- Uma FK deve obrigatoriamente referenciar uma PK existente ou ser nula (dependendo da regra definida).  

### Regras de ação em cascata:
- **CASCADE** → alterações na PK propagam para as FKs.  
- **SET NULL** → se o registro pai é removido, o valor da FK vira NULL.  
- **RESTRICT/NO ACTION** → impede exclusão/alteração se houver dependentes.  

Exemplo:
```sql
CREATE TABLE empresa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE departamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    fk_empresa INT REFERENCES empresa(id) ON DELETE CASCADE
);
```

---

## DDL / DML / DCL

### DDL (Data Definition Language)
Define a estrutura do banco:
- CREATE (criar objetos: tabela, índice, view, schema)
- ALTER (modificar estrutura: colunas, restrições)
- DROP (remover objetos)

### DML (Data Manipulation Language)
Manipula dados existentes:
- INSERT (inserir registros)
- SELECT (consultar registros)
- UPDATE (alterar registros)
- DELETE (remover registros)

### DCL (Data Control Language)
Controle de acesso:
- GRANT (concede permissões)
- REVOKE (remove permissões)

---

## JOIN
Permite combinar registros de múltiplas tabelas.  

### Tipos de JOIN:
- **INNER JOIN** → apenas correspondências entre tabelas.  
- **LEFT JOIN** → todos da esquerda + correspondências da direita.  
- **RIGHT JOIN** → todos da direita + correspondências da esquerda.  
- **FULL JOIN** → união de LEFT + RIGHT.  
- **CROSS JOIN** → produto cartesiano (todas as combinações).  

Exemplo:
```sql
SELECT c.nome, v.valor
FROM cliente c
INNER JOIN venda v ON c.id = v.cliente_id;
```

---

## Regras das Constraints

- **PRIMARY KEY**  
  - Identifica unicamente cada linha.  
  - Implícita `NOT NULL` + `UNIQUE`.  

- **FOREIGN KEY**  
  - Garante integridade referencial entre tabelas.  
  - Pode ter regras (`CASCADE`, `SET NULL`, `RESTRICT`).  

- **NOT NULL**  
  - Campo não pode ficar sem valor.  

- **UNIQUE**  
  - Impede valores duplicados em uma coluna.  

- **CHECK**  
  - Restringe valores de acordo com condição.  
  ```sql
  salario NUMERIC CHECK (salario > 0)
  ```

- **DEFAULT**  
  - Valor padrão atribuído quando não especificado.  
  ```sql
  data_criacao DATE DEFAULT CURRENT_DATE
  ```

---

## Extra
**Diferenças importantes**:
- **DDL** → estrutura do BD.  
- **DML** → manipulação de dados.  
- **DCL** → controle de usuários e segurança.  
