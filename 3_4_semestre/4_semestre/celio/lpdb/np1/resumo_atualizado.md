# Linguagens DDL, DML, DCL

## Principais de cada um

### DDL - Data Definition Language
- CREATE
- ALTER → modify, add, drop
- DROP

### DML - Data Manipulation Language
- SELECT
- INSERT
- UPDATE  
  ```sql
  UPDATE funcionario AS f
      SET nome = 'Nicolas B'
      WHERE id = 1;
  ```
- DELETE
- WHERE (cláusula usada em SELECT/UPDATE/DELETE)

### DCL - Data Control Language
- GRANT  
  > Permite dar permissões a um ou mais usuários.  
- REVOKE  
  > Remove permissões dadas pelo GRANT.  

---

# SGBD
- **SGBD (Sistema Gerenciador de Banco de Dados)** é o software responsável por gerenciar os dados, garantir integridade, controlar acesso e facilitar a manipulação.  
- Exemplos: MySQL, PostgreSQL, Oracle, SQL Server.  
- Funções principais:  
  - Criação e manutenção de bancos de dados  
  - Controle de concorrência (vários usuários acessando ao mesmo tempo)  
  - Segurança (controle de usuários e permissões)  
  - Backup e recuperação  
  - Garantia de **integridade dos dados**  

---

# Integridade Referencial
- Garante que os relacionamentos entre tabelas sejam **consistentes**.  
- Baseada no uso de **chaves primárias (PK)** e **chaves estrangeiras (FK)**.  
- Exemplo: não é permitido inserir um registro em `departamento` referenciando uma `empresa` que não existe.  
- Se a empresa for excluída, o SGBD impede ou exige ação definida (`CASCADE`, `SET NULL`, `RESTRICT`).  

---

# JOIN
Usado para buscar dados em múltiplas tabelas.  

Principais tipos:  
- **INNER JOIN** → retorna apenas registros com correspondência nas duas tabelas.  
- **LEFT JOIN** → retorna todos os registros da tabela da esquerda, mesmo sem correspondência na direita.  
- **RIGHT JOIN** → retorna todos os registros da tabela da direita, mesmo sem correspondência na esquerda.  
- **FULL JOIN** → retorna registros de ambas, combinando os correspondentes.  

Exemplo:
```sql
SELECT e.nome, d.nome
FROM empresa e
INNER JOIN departamento d ON e.id = d.fk_empresa;
```

---

# Regras das Constraints
- **PRIMARY KEY** → identifica unicamente cada registro.  
- **FOREIGN KEY** → garante integridade referencial entre tabelas.  
- **NOT NULL** → campo não pode ser nulo.  
- **UNIQUE** → não permite valores duplicados.  
- **CHECK** → define condições (ex: salário > 0).  
- **DEFAULT** → valor padrão se não for informado.  
