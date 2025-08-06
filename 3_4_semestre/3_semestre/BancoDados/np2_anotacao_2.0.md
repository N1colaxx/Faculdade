:

# 📘 Normalização de Banco de Dados
A normalização é um processo fundamental na modelagem de dados relacionais. Seu objetivo é organizar os dados em tabelas que minimizem redundâncias e garantam a integridade e consistência das informações.



### 🧠 O que é Normalização?
A normalização consiste em aplicar um conjunto de regras sobre as tabelas do banco de dados, dividindo-as conforme necessário para eliminar repetições desnecessárias e separar assuntos distintos. Isso resulta em um modelo mais estável, eficiente e de fácil manutenção.

> 🔑 Regra de ouro: “Não misturar assuntos em uma mesma tabela”.



### 🎯 Objetivos da Normalização
-   Evitar redundância e inconsistência nos dados.
-   Garantir integridade referencial.
-   Melhorar o desempenho em operações de atualização (inserção, exclusão e modificação).
-   Estruturar os dados de forma clara, baseando-se em dependências funcionais.



### 🔗 Dependência Funcional
Uma dependência funcional ocorre quando o valor de um atributo (ou conjunto de atributos) determina unicamente o valor de outro atributo.

Se A determina B, então B depende funcionalmente de A.
Notação: A → B

Exemplo:
A matrícula de um aluno determina seu nome e endereço:
Matrícula → {Nome, Endereço}



### 📐 Formas Normais
As formas normais são etapas que uma tabela deve seguir para atingir um alto grau de organização. A seguir, as principais formas aplicadas na prática:



### ✅ Primeira Forma Normal (1ª FN)
-   Elimina grupos de repetição.
-   Todos os atributos devem ser atômicos (indivisíveis).
-   Não pode haver atributos multivalorados nem compostos.



**Exemplo ruim:**

    ID	Nome	Telefones
    1	Ana	(14)9999-1111, ...

Exemplo bom:

    ID	Nome	Telefone
    1	Ana	(14)9999-1111
    1	Ana	(14)9888-2222



### ✅ Segunda Forma Normal (2ª FN)

-   Estar na 1ª FN.

Todos os atributos não-chave devem depender da chave primária completa.

Elimina dependências parciais (em chaves compostas).

Exemplo de problema:

    {Matrícula, CódigoDisciplina} → NomeAluno, NomeDisciplina
    NomeAluno depende apenas da matrícula.

    NomeDisciplina depende apenas do código da disciplina.

> Solução: Separar em tabelas diferentes.



### ✅ Terceira Forma Normal (3ª FN)

-   Estar na 2ª FN.

Elimina dependências transitivas, ou seja, quando um campo não-chave depende de outro campo não-chave.

    Exemplo de problema:

    ID	Nome	CEP	Cidade
    1	Ana	17000-000	Bauru

    Cidade depende de CEP, não do ID.

> Solução: Criar uma tabela separada para os dados do endereço.



### 💡 Outras Formas Normais (Avançadas)

-   ▶️ Forma Normal de Boyce-Codd (BCNF)
Aperfeiçoamento da 3ª FN.

Elimina qualquer dependência funcional em que o determinante não seja uma chave candidata.

-   ▶️ Quarta Forma Normal (4ª FN)
Trata dependências multivaloradas.

Elimina redundância em tabelas onde atributos não-chave possuem múltiplos valores para a mesma chave.

-   ▶️ Quinta Forma Normal (5ª FN)
Trata junções complexas e é raramente utilizada.

Aplica-se quando os dados ainda podem ser divididos sem perda de significado.



### ✅ Vantagens da Normalização
-   🔄 Facilita atualizações e manutenção.
-   📦 Garante que cada dado dependa apenas da chave primária.
-   🧩 Cria um modelo mais modular e compreensível.
-   🛡️ Minimiza riscos de inconsistência e anomalias nos dados.



### 🧭 Conclusão
A normalização é uma prática essencial para projetos de banco de dados bem estruturados, que evita falhas de projeto e garante desempenho e integridade. Mesmo que em sistemas de grande porte se use ocasionalmente desnormalização para ganhos de performance, a normalização sempre deve ser o ponto de partida.

