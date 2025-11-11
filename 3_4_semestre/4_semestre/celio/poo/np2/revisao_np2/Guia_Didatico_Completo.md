
# Guia Didático Completo — JDBC, MVC e Hibernate
> Estilo: Professor — explicações passo a passo, linguagem simples, foco em entendimento

## Objetivo deste guia
Explicar de forma didática e progressiva JDBC, arquitetura MVC e Hibernate, com ênfase em relacionamentos no Hibernate. Ideal para quem está estudando para prova ou quer entender os conceitos do zero.

---
## Sumário
1. Introdução: por que usar JDBC, MVC e Hibernate
2. JDBC — conceitos e exemplo prático
3. MVC — papel de cada camada, fluxo de requisição
4. Hibernate — o que é, por que usar
5. Relacionamentos no Hibernate — explicação passo a passo
6. Conceitos avançados (explicados de forma acessível)
7. Exemplos de código comentados
8. Exercícios (teórico e prático)
9. Dicas de estudo

---
## 1) Por que aprender isso?
- **JDBC**: base para entender como Java fala com bancos de dados.
- **MVC**: organiza o código e separa responsabilidades.
- **Hibernate**: facilita trabalhar com banco transformando linhas em objetos — menos código repetitivo.

---
## 2) JDBC — passo a passo
### O que é
API Java para conectar e executar SQL em bancos relacionais.

### Componentes e analogia simples
- **Driver**: intérprete que entende a língua do banco.
- **Connection**: o telefone que conecta você ao banco.
- **Statement / PreparedStatement**: a pessoa que fala o SQL.
- **ResultSet**: a mesa com os resultados retornados.

### Exemplo comentado
```java
String url = "jdbc:postgresql://localhost:5432/meubd";
Connection conn = DriverManager.getConnection(url, user, pass);
// PreparedStatement evita SQL Injection
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM aluno WHERE id = ?");
stmt.setInt(1, 10);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
    String nome = rs.getString("nome");
}
rs.close();
stmt.close();
conn.close();
```
### Observações práticas
- Feche sempre `ResultSet`, `Statement` e `Connection` (usando try-with-resources é ideal).
- JDBC exige SQL manual — por isso ORMs como Hibernate são populares.

---
## 3) MVC — o fluxo básico (explicado)
- **Model**: onde ficam as regras de negócio e entidades.
- **View**: a interface que o usuário vê (HTML, JSP, templates).
- **Controller**: recebe as requisições, chama o model e retorna a view.

### Exemplo de fluxo em uma requisição
1. Usuário clica em “ver curso” no navegador.
2. Requisição chega ao `Controller`.
3. `Controller` usa `Service`/`Repository` para buscar dados (Model).
4. Model devolve objetos (entidades).
5. Controller envia os dados para a `View` (renderiza página).

---
## 4) Hibernate — explicação simples
- Hibernate converte tabelas do banco em objetos Java (entidades).
- Ele lida com INSERT, UPDATE, DELETE e SELECT por você, usando anotações como `@Entity`, `@Id` etc.

### Exemplo mínimo
```java
@Entity
public class Aluno {
  @Id @GeneratedValue
  private Long id;
  private String nome;
}
```

---
## 5) Relacionamentos no Hibernate — passo a passo
### Conceitos iniciais
- **Owning side**: o lado que possui a coluna de FK no banco. É o que “manda” no mapeamento.
- **mappedBy**: usado no lado inverso para informar que a FK está no outro lado.
- **Bidirecional vs Unidirecional**: bidirecional tem referências em ambas as entidades; unidirecional só em uma.

### Exemplos simples e analogias
- **ManyToOne**: muitos alunos → 1 curso. A tabela `aluno` tem `curso_id` (FK).
- **OneToMany**: o `curso` tem uma lista de alunos. Em memória você precisa atualizar os 2 lados quando criar a associação.
- **OneToOne**: cada aluno tem um endereço.
- **ManyToMany**: alunos ⇄ disciplinas, normalmente via tabela de junção.

### Como persistir corretamente (regra prática)
Sempre sincronize ambos os lados em associações bidirecionais:
```java
curso.getAlunos().add(aluno);
aluno.setCurso(curso);
```

---
## 6) Conceitos avançados (explicados de forma acessível)
- **Cascade**: quando salvar o pai, o que deve acontecer com o filho? `PERSIST`, `MERGE`, `REMOVE` são os mais comuns.
- **orphanRemoval**: se você tira um filho da coleção, o filho será deletado do BD.
- **FetchType LAZY**: carrega a relação somente quando você pedir — evita carregar coisas desnecessárias.
- **LazyInitializationException**: acontece quando tenta acessar uma relação lazy fora da sessão ativa. Solução: abrir sessão durante o uso, usar `join fetch` em queries, ou transformar em DTO.
- **@Embeddable**: agrupar campos (ex.: `Endereco`) sem criar nova tabela.

---
## 7) Exemplos de código comentados
### OneToMany / ManyToOne
```java
@Entity
public class Curso {
  @Id @GeneratedValue private Long id;
  private String nome;
  @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Aluno> alunos = new ArrayList<>();
  public void addAluno(Aluno a) {
    alunos.add(a);
    a.setCurso(this);
  }
}
@Entity
public class Aluno {
  @Id @GeneratedValue private Long id;
  private String nome;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "curso_id")
  private Curso curso;
}
```
Comentário: `cascade = ALL` permite salvar o `Curso` e automaticamente persistir os `Alunos` adicionados.

### ManyToMany com entidade de junção (recomendado para atributos extras)
Crie `Enrollment` com `student`, `course`, `grade`, `enrollDate` como entidade própria em vez de usar `@ManyToMany` direto.

---
## 8) Exercícios (didático)
### Teóricos (responda com frases curtas)
1. O que é o `owning side` em uma associação?  
2. Por que usamos `LAZY` por padrão em coleções?  
3. O que acontece se você não sincronizar os dois lados de uma associação bidirecional?

### Práticos (escreva o código)
1. Crie `Author` e `Book` com OneToMany / ManyToOne. Implemente `addBook()` no `Author`.  
2. Modele `Student`, `Course` e `Enrollment` como entidade de junção com campo `grade`.  
3. Escreva uma query HQL para buscar `Course` por id trazendo alunos via `join fetch`.

---
## 9) Dicas finais de estudo
- Pratique modelagem: desenhe tabelas e pense qual lado tem a FK.  
- Teste os exemplos localmente com um banco leve (H2) e log SQL ligado (`hibernate.show_sql=true`).  
- Memorize o que cada `CascadeType` faz e quando **não** usar `REMOVE`.  

---
Boa sorte na prova! Estude com calma, repasse os exemplos e tente responder os exercícios sozinho antes de ver as soluções.
