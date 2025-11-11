
# Guia Avançado Profissional — Hibernate Internals & Performance
> Estilo: Avançado — internals do Hibernate, estratégias de performance, ciclo de vida, caching, transações e casos reais

---
## Objetivo deste guia avançado
Aprofundar nos mecanismos internos do Hibernate/JPA que afetam design, performance e comportamento em produção. Ideal para entrevistas técnicas, otimização de aplicações e desenvolvimento de sistemas robustos.

---
## 1) Contexto de Persistência (Session / EntityManager)
- **Persistence Context (PC)** é um cache em memória das entidades gerenciadas pela sessão.  
- Enquanto a entidade está no PC, alterações são rastreadas (dirty checking).  
- `session.save()` e `persist()` marcam a entidade para inserção; o `INSERT` pode ocorrer no `flush()` ou `commit()` dependendo da estratégia.

### Dirty Checking
- Hibernate compara o estado atual das entidades com o snapshot inicial para gerar SQL de `UPDATE` somente quando necessário.
- Reduz writes desnecessários, mas gera overhead de comparação.

### Flush vs Clear vs Evict
- `flush()`: sincroniza estado do PC com o BD (gera SQL de INSERT/UPDATE/DELETE).  
- `clear()`: limpa o PC, entidades tornam-se detached.  
- `evict(entity)`: remove entidade específica do PC.

---
## 2) Transações e Isolamento
- Operações de escrita devem estar dentro de transação (`tx.begin()` / `tx.commit()`).  
- `commit()` chama `flush()` automaticamente.  
- Uso de JTA vs Resource-local: em aplicações Java EE/Jakarta EE use JTA; em apps standalone pode usar transações locais do Hibernate.

### Problemas frequentes
- **Stale Data**: leitura de dados antigos se não gerenciar bem o cache.  
- **Deadlocks**: coordene acesso concorrente com nível de isolamento e lock otimista/pessimista.

---
## 3) Estratégias de Fetching e N+1 Problem
- `LAZY` evita joins desnecessários.  
- N+1 ocorre quando busca N parents e para cada parent executa query para os filhos.  
- Soluções: `join fetch`, `batch fetching` (`@BatchSize`), `EntityGraph`, projetar DTOs via JPQL.

### Batch fetching
- `hibernate.jdbc.batch_size` e `@BatchSize` reduzem o número de queries ao buscar coleções/pks.
- Útil quando você precisa carregar várias entidades lazy em sequência.

---
## 4) Cascades, Orphan Removal e Gestão de Relacionamentos
### Dono da relação (owning side)
- Operações de persistência da associação são aplicadas no lado dono (quem tem a FK).  
- Para manter consistência em memória, sempre ajuste ambos os lados.

### Cascade profundo
- `CascadeType.ALL` = `PERSIST, MERGE, REMOVE, REFRESH, DETACH`.  
- Cuidado real: `REMOVE` e `orphanRemoval` juntos significam que ao remover referência o filho é deletado fisicamente — ótimo para composição, perigoso para agregados compartilhados.

### Estratégias para evitar deletes acidentais
- Evitar cascade remove em coleções que compartilham entidades.  
- Usar `ON DELETE CASCADE` no BD apenas quando modelagem justificar.  
- Preferir `remove` explícito via repositório quando necessário.

---
## 5) Chaves compostas e Identidade
- `@EmbeddedId` encapsula a PK em um objeto.  
- `@IdClass` permite ter campos `@Id` separados na entidade.  
- Em relacionamentos com `@MapsId`, você pode mapear entidade filha que usa PK do pai (shared PK).

### Recomendações
- Use `@EmbeddedId` se PK lógica agrupa campos naturalmente.  
- Evite usar campos PK compostos complexos quando possível; prefira surrogate key (`@GeneratedValue`) e unique constraints para regras de negócio.

---
## 6) Cache — 1º e 2º Nível, Query Cache
### 1º nível (Session)
- Automático e por sessão; já discutido (PC).

### 2º nível (SessionFactory / Shared cache)
- Opcional; configura `hibernate.cache.use_second_level_cache=true`.  
- Tipos: **entity cache**, **collection cache**, **query cache**.  
- Use provedor como Ehcache, Infinispan, Hazelcast.

### Considerações
- 2º nível melhora leituras, mas precisa de invalidação correta em ambientes distribuídos.  
- Não é solução mágica para problemas de design de consultas (ex.: SELECT * sem filtro).

---
## 7) Locking — Pessimista vs Otimista
- **Lock otimista** (`@Version`) usa versão em coluna para detectar concorrência (melhor para alta simultaneidade).  
- **Lock pessimista** (`LockMode.PESSIMISTIC_WRITE`) força lock no BD — evita lost updates mas reduz concorrência.

### Exemplo `@Version`
```java
@Version
private Long version;
```

---
## 8) Mapping avançado e performance
- **Collections**: prefira `Set` para evitar duplicatas; `List` se precisa de ordem (considere `@OrderColumn`).  
- **Large collections**: prefira paginação e queries específicas; não carregue tudo em memória.  
- **Projeções DTO**: consultas que retornam DTOs evitam trazer entidades inteiras quando só precisa de alguns campos.

---
## 9) Monitoramento e Tuning para Produção
- Ative `hibernate.show_sql` e `format_sql` em dev.  
- Use logs do DB para analisar planos e índices.  
- Faça profiling de queries lentas; adicione índices conforme padrão de acesso.  
- Monitore cache hit/miss do 2º nível.

---
## 10) Exemplos avançados (trechos)
### JOIN FETCH para evitar N+1
```java
List<Course> courses = em.createQuery("select c from Course c join fetch c.alunos where c.active = true", Course.class)
    .getResultList();
```

### Uso de Batch Insert/Update
Configurar `hibernate.jdbc.batch_size = 50` e usar `session.flush()` e `session.clear()` periodicamente em loops para evitar OOM.

---
## 11) Melhores práticas resumidas (Profissional)
- Modele relações pensando em dono da relação e consistência em memória.  
- Evite carregar coleções grandes sem paginação.  
- Prefira DTOs para respostas REST quando necessário.  
- Use cache 2º nível com compreensão das invalidações.  
- Teste concorrência com `@Version` para evitar lost updates.  
- Documente decisões: porque foi escolhido `JOINED` vs `SINGLE_TABLE`, por ex.

---
## 12) Exercícios Avançados (para praticar)
1. Explique o que acontece no Persistence Context quando você chama `session.merge()` em uma entidade detached.  
2. Modele `Order` e `OrderItem` com `@EmbeddedId` e escreva um loop que salva 10k items usando batching (pseudocódigo OK).  
3. Descreva passo a passo como resolver um N+1 detectado em produção (investigação, correção, teste).

---
Boa prática: aplique estas técnicas em um projeto pequeno e observe logs/planos do DB para internalizar impactos de cada escolha.
