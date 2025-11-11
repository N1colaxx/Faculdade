
# Guia Resumo Técnico — JDBC, MVC e Hibernate
> Estilo: Resumo para prova — objetivo, conceitos-chave e frases de memória

---
## Objetivos rápidos
- **JDBC**: API para executar SQL em Java.  
- **MVC**: separar Model / View / Controller, organizar responsabilidades.  
- **Hibernate**: ORM que mapeia objetos Java para tabelas.

---
## JDBC — Pontos essenciais
- Driver, Connection, Statement/PreparedStatement, ResultSet.  
- Use PreparedStatement para segurança e performance.  
- Fechar recursos = obrigatório (try-with-resources).

**Snippet**:
```java
try (Connection c = DriverManager.getConnection(url, u, p);
     PreparedStatement ps = c.prepareStatement("SELECT * FROM t WHERE id=?")) {
    ps.setInt(1, 1);
    try (ResultSet rs = ps.executeQuery()) {
        // ...
    }
}
```

---
## MVC — Definições curtas
- **Model**: dados e regras.  
- **View**: interface.  
- **Controller**: recebe requisições e coordena fluxo.

---
## Hibernate — Conceitos-chave (bullet points)
- `@Entity`, `@Id`, `@GeneratedValue`  
- `Session` / `EntityManager` — contexto de persistência  
- `flush()` sincroniza mudanças com o BD  
- `commit()` confirma a transação  
- `LAZY` vs `EAGER` — prefira `LAZY` em coleções  
- `CascadeType`: PERSIST, MERGE, REMOVE, ALL  
- `orphanRemoval=true` deleta filhos removidos da coleção  
- `mappedBy` indica lado inverso da relação  

---
## Relacionamentos — atalho de memorização
- **ManyToOne**: FK no lado "Many". (Ex.: aluno.curso_id)  
- **OneToMany**: lado "One" tem coleção e usa `mappedBy`.  
- **OneToOne**: FK ou PK compartilhada (`@MapsId`).  
- **ManyToMany**: join table (usar entidade de junção se precisar de atributos).

---
## Boas práticas rápidas
- Sincronize ambos os lados em bidirecionais.  
- Evite `CascadeType.REMOVE` em coleções compartilhadas.  
- Para grandes coleções, prefira consultas específicas (`JOIN FETCH`, paginação).  
- Equals/HashCode: cuidado com ids gerados; use business key quando apropriado.

---
## Consultas importantes (HQL/JPQL)
- `select a from Aluno a where a.nome = :n`  
- `select c from Curso c join fetch c.alunos where c.id = :id` (evita LazyInitializationException)  
- Criteria API para queries dinâmicas.

---
## Exemplos curtos
**OneToMany / ManyToOne**
```java
@ManyToOne @JoinColumn(name="curso_id") private Curso curso;
@OneToMany(mappedBy="curso") private List<Aluno> alunos;
```

**ManyToMany (join table)**
```java
@ManyToMany
@JoinTable(name="aluno_disciplina", joinColumns=@JoinColumn(name="aluno_id"),
inverseJoinColumns=@JoinColumn(name="disciplina_id"))
private Set<Disciplina> disciplinas;
```

---
## Checklist para prova
- [ ] Saber identificar lado dono / inverse  
- [ ] Saber explicar CascadeType comuns  
- [ ] Saber descrever LazyInitializationException e soluções  
- [ ] Escrever mapeamento básico OneToMany / ManyToOne de cabeça  
- [ ] Entender `@Embeddable` e `@EmbeddedId` basics

---
Estude este resumo diariamente: leia, escreva e implemente pequenos exemplos. Boa prova!
