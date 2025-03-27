# Sistemas de Gerência de Banco de Dados - Resumo Completo

## Conceitos Básicos

### Dados e Informação
    - **Dados**: Fatos em sua forma primária (nome, telefone, endereço)
    - **Informação**: Dados organizados de maneira significativa
    - **Banco de Dados**: Conjunto de dados relacionados capazes de apresentar informação

### Características de um Banco de Dados
    - Coleção lógica coerente de dados com significado
    - Projetado para um propósito específico
    - Possui conjunto pré-definido de usuários e aplicações
    - Representa algum aspecto do mundo real

## Sistemas Gerenciadores de Banco de Dados (SGBD)

    ### Definição
        - Software com recursos para manipular informações de bancos de dados
        - Surgiu para atender necessidade de armazenamento e recuperação de grandes volumes de informações

    ### Funções Básicas
        1. Proporcionar abstração
        2. Rapidez no acesso às informações
        3. Independência dos dados em relação aos programas
        4. Compartilhamento da base de dados
        5. Facilidade de backup
        6. Comunicação com software/servidor
        7. Integridade dos dados

## Arquitetura ANSI/SPARC

    ### Níveis de Abstração
        1. **Nível Externo (Visão)**
        - Descreve como os dados são vistos pelos usuários
        - Define porções de acesso para diferentes usuários

    2. **Nível Conceitual**
        - Descreve estrutura global do banco de dados
        - Mostra relacionamentos entre dados

    3. **Nível Interno/Físico**
        - Descreve estrutura de armazenamento físico
        - Detalha como os dados são armazenados

## Conceitos Relacionados

    ### Abstração de Dados
        - Omite detalhes de armazenamento
        - Simplifica interação do usuário
        - Esconde complexidade de implementação

### Independência de Dados
    1. **Independência Física**
        - Modificar esquema físico sem reescrever aplicativos

    2. **Independência Lógica**
       - Modificar esquema conceitual sem reescrever aplicativos

### Dicionário de Dados
    - Coleção de metadados
    - Contém definições e representações de elementos de dados
    - Mantém informações sobre:
        - Definição de elementos de dados
        - Perfis de usuários
        - Descrição de objetos
        - Restrições de integridade

### Modelo de Dados
    - Conjunto de conceitos para descrever estrutura lógica e física
    - Tipos:
        - Alto nível (conceitual)
        - Baixo nível (lógico/físico)

### Linguagens de Manipulação
    1. DDL (Data Definition Language)
    2. SDL (Storage Definition Language)
    3. VDL (Vision Definition Language)
    4. DML (Data Manipulation Language)

## Vantagens do SGBD

    1. **Controle de Redundância**
        - Evita duplicação desnecessária de dados
        - Otimiza uso do espaço de armazenamento

    2. **Compartilhamento de Dados**
        - Permite acesso multiusuário
        - Controla concorrência

    3. **Segurança**
        - Restrição de acesso não autorizado
        - Controle de contas e privilégios

    4. **Representação de Relacionamentos**
        - Suporta relacionamentos complexos entre dados

    5. **Tolerância a Falhas**
        - Recursos de recuperação de falhas de software e hardware

## Imagens Relevantes

    ### Figura 1 (Arquitetura do SGBD)
        - Ilustra os três níveis de abstração (externo, conceitual, físico)
        - Mostra a relação entre usuários finais e níveis de esquema

    ### Figura 2 (Independência de Dados)
        - Demonstra como programas de aplicação interagem com o SGBD
        - Representa a independência entre aplicações e estrutura de dados

    ### Figura 3 (Modelo Entidade-Relacionamento)
        - Exemplo de modelo que representa relacionamentos entre entidades
        - Mostra conexões entre diferentes elementos de um sistema

    ### Figura 4 (Estrutura de um SGBD)
        - Detalha componentes internos de um Sistema Gerenciador de Banco de Dados
        - Ilustra interações entre usuários, compiladores, gerenciadores e arquivos de dados


# Modelos de Banco de Dados - Resumo Completo

## Considerações Iniciais no Projeto de Banco de Dados

### Aspectos Fundamentais
    1. **Conteúdo**: Quais dados coletar e seu custo
    2. **Acesso**: Definir usuários e permissões
    3. **Estrutura Lógica**: Organização significativa dos dados
    4. **Organização Física**: Localização física dos dados

## Evolução Histórica dos Modelos de Dados

### Linha do Tempo
    - 1964: Modelos de Redes
    - 1970: Modelo Relacional proposto
    - Anos 80: Modelo Relacional comercialmente disponível
    - Modelos mais recentes:
        - Semânticos
        - Funcionais
        - Relacionais aninhados
    - Foco atual: 
        - Objetos-relacionais
        - Orientados a Objetos

## 1. Modelo Hierárquico

### Características
    - Organização de cima para baixo (estrutura de árvore)
    - Relação semelhante a pais e filhos
    - Adequado para relações um-para-muitos

### Vantagens
    - Natural para organizações hierárquicas
    - Simplicidade de processamento
    - Agrupamento hierárquico natural

### Restrições
    - Registros filhos só existem ligados a um pai
    - Necessidade de duplicar registros com múltiplos pais

### Sistemas
    - IBM's IMS
    - SYSTEM 2000

## 2. Modelo em Rede

### Características
    - Extensão do modelo hierárquico
    - Relação membro-proprietário
    - Múltiplos caminhos de acesso a um dado

### Origem
    - Apresentado por CODASYL em 1971
    - Revisado em 1978 e 1981

### Vantagens
    - Melhor representação de dados estruturados
    - Modelagem de comportamento de relacionamentos

### Sistemas
    - IDS (Honeywell)
    - DMS 1100 (Univac)
    - TOTAL (Cincom)

## 3. Modelo Relacional

### Características
    - Dados organizados em tabelas bidimensionais
    - Linhas (tuplas) e colunas (atributos)
    - Mais popular atualmente

### Origem
    - Proposto por E.F. Codd em 1970
    - Linguagem padrão: SQL

### Vantagens
    - Fácil de controlar
    - Intuitivo
    - Flexível
    - Permite consultas complexas

### Exemplo Prático
    - Possibilita consultas rápidas entre tabelas relacionadas
    - Demonstra facilidade de interconexão de informações

## Comparação entre Modelos

### Modelo Hierárquico
    - Rápido para manipulação
    - Pouco flexível
    - Difícil modificação

### Modelo em Rede
    - Mais flexível que o hierárquico
    - Complexo para desenvolvimento
    - Relações de dados mais complicadas

### Modelo Relacional
    - Mais utilizado
    - Intuitivo
    - Fácil controle
    - Organização em tabelas

## Considerações Finais
    - Cada modelo tem vantagens específicas
    - Escolha depende do contexto e necessidades da organização