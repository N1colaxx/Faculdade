-- Create tables
CREATE TABLE Pessoa (
    id_pessoa INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_pessoa TEXT NOT NULL,
    tipo_documento TEXT NOT NULL,
    numero_documento TEXT NOT NULL UNIQUE,
    nome_razao_social TEXT NOT NULL
);

CREATE TABLE PessoaFisica (
    id_pf INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    cod TEXT,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE PessoaJuridica (
    id_pj INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    nome_fantasia TEXT,
    regime_tributario TEXT,
    inscricao_estadual TEXT,
    inscricao_suframa TEXT,
    is_contribuinte_icms BOOLEAN,
    cnae_fiscal TEXT,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Emitente (
    id_emitente INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Destinatario (
    id_destinatario INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Remetente (
    id_remetente INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Transportadora (
    id_transportadora INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cnpj TEXT NOT NULL UNIQUE
);

CREATE TABLE Endereco (
    id_endereco INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER,
    id_transportadora INTEGER,
    tipo TEXT NOT NULL,
    logradouro TEXT NOT NULL,
    numero TEXT NOT NULL,
    complemento TEXT,
    bairro TEXT NOT NULL,
    municipio TEXT NOT NULL,
    uf TEXT NOT NULL,
    cep TEXT NOT NULL,
    codigo_pais TEXT,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa),
    FOREIGN KEY (id_transportadora) REFERENCES Transportadora(id_transportadora)
);

CREATE TABLE Contato (
    id_contato INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    tipo TEXT NOT NULL,
    ddd TEXT,
    numero TEXT,
    email TEXT,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE DocumentoFiscal (
    id_documento_fiscal INTEGER PRIMARY KEY AUTOINCREMENT,
    id_pessoa INTEGER NOT NULL,
    tipo_documento TEXT NOT NULL,
    numero_documento TEXT NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE TbStatus (
    id_status INTEGER PRIMARY KEY AUTOINCREMENT,
    cod TEXT NOT NULL UNIQUE
);

CREATE TABLE NotaFiscal (
    id_nf INTEGER PRIMARY KEY AUTOINCREMENT,
    chave_acesso TEXT UNIQUE,
    tipo_operacao TEXT NOT NULL,
    modelo TEXT NOT NULL,
    serie TEXT NOT NULL,
    numero TEXT NOT NULL,
    data_emissao DATE NOT NULL,
    data_saida_entrada DATE,
    hora_saida_entrada TIME,
    natureza_da_operacao TEXT,
    protocolo_autorizacao TEXT,
    status TEXT NOT NULL,
    data_autorizacao DATETIME,
    valor_total_nf DECIMAL(10,2) NOT NULL,
    valor_total_produtos DECIMAL(10,2),
    valor_total_servicos DECIMAL(10,2),
    valor_desconto DECIMAL(10,2),
    valor_outras_despesas DECIMAL(10,2),
    valor_frete DECIMAL(10,2),
    valor_total_impostos DECIMAL(10,2),
    id_emitente INTEGER NOT NULL,
    id_destinatario INTEGER NOT NULL,
    id_remetente INTEGER,
    FOREIGN KEY (id_emitente) REFERENCES Emitente(id_emitente),
    FOREIGN KEY (id_destinatario) REFERENCES Destinatario(id_destinatario),
    FOREIGN KEY (id_remetente) REFERENCES Remetente(id_remetente)
);

CREATE TABLE DadosAdicionais (
    id_dados_adicionais INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    informacoes_complementares TEXT,
    observacoes TEXT,
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf)
);

CREATE TABLE Produto (
    id_produto INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    descricao TEXT,
    ncm TEXT NOT NULL,
    cest TEXT,
    unidade_comercial TEXT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL
);

CREATE TABLE Servico (
    id_servico INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    descricao TEXT,
    valor_unitario DECIMAL(10,2) NOT NULL
);

CREATE TABLE ItemNotaFiscal (
    id_item_nf INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    id_produto INTEGER,
    id_servico INTEGER,
    cfop TEXT NOT NULL,
    quantidade DECIMAL(10,3) NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    valor_desconto DECIMAL(10,2),
    valor_total_item DECIMAL(10,2) NOT NULL,
    valor_total_impostos_item DECIMAL(10,2),
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto),
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
);

CREATE TABLE Imposto (
    id_imposto INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    descricao TEXT
);

CREATE TABLE TipoImposto (
    id_tipo_imposto INTEGER PRIMARY KEY AUTOINCREMENT,
    id_imposto INTEGER NOT NULL,
    codigo_tipo TEXT NOT NULL,
    descricao_tipo TEXT,
    FOREIGN KEY (id_imposto) REFERENCES Imposto(id_imposto)
);

CREATE TABLE ImpostoItem (
    id_imposto_item INTEGER PRIMARY KEY AUTOINCREMENT,
    id_item_nf INTEGER NOT NULL,
    id_tipo_imposto INTEGER NOT NULL,
    situacao_tributaria TEXT NOT NULL,
    base_calculo DECIMAL(10,2),
    aliquota DECIMAL(5,2),
    valor DECIMAL(10,2),
    base_calculo_st DECIMAL(10,2),
    aliquota_st DECIMAL(5,2),
    valor_st DECIMAL(10,2),
    valor_fcp DECIMAL(10,2),
    FOREIGN KEY (id_item_nf) REFERENCES ItemNotaFiscal(id_item_nf),
    FOREIGN KEY (id_tipo_imposto) REFERENCES TipoImposto(id_tipo_imposto)
);

CREATE TABLE Frete (
    id_frete INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    id_transportadora INTEGER NOT NULL,
    tipo_frete TEXT NOT NULL,
    placa_veiculo TEXT,
    uf_veiculo TEXT,
    valor_frete DECIMAL(10,2),
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf),
    FOREIGN KEY (id_transportadora) REFERENCES Transportadora(id_transportadora)
);

CREATE TABLE VolumeTransportado (
    id_volume INTEGER PRIMARY KEY AUTOINCREMENT,
    id_frete INTEGER NOT NULL,
    quantidade INTEGER,
    especie TEXT,
    marca TEXT,
    numeracao TEXT,
    peso_bruto DECIMAL(10,3),
    peso_liquido DECIMAL(10,3),
    FOREIGN KEY (id_frete) REFERENCES Frete(id_frete)
);

CREATE TABLE EventoNF (
    id_evento INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    tipo_evento TEXT NOT NULL,
    motivo TEXT,
    data_hora DATETIME NOT NULL,
    usuario TEXT NOT NULL,
    xml_referencia TEXT,
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf)
);

CREATE TABLE FormaPagamento (
    id_forma_pagamento INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL UNIQUE,
    descricao TEXT NOT NULL
);

CREATE TABLE Fatura (
    id_fatura INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    numero TEXT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_vencimento DATE NOT NULL,
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf)
);

CREATE TABLE Pagamento (
    id_pagamento INTEGER PRIMARY KEY AUTOINCREMENT,
    id_nf INTEGER NOT NULL,
    id_forma_pagamento INTEGER NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_pagamento DATE NOT NULL,
    status TEXT NOT NULL,
    FOREIGN KEY (id_nf) REFERENCES NotaFiscal(id_nf),
    FOREIGN KEY (id_forma_pagamento) REFERENCES FormaPagamento(id_forma_pagamento)
);

-- Tabelas principais
SELECT * FROM Pessoa;
SELECT * FROM PessoaFisica;
SELECT * FROM PessoaJuridica;
SELECT * FROM Emitente;
SELECT * FROM Destinatario;
SELECT * FROM Remetente;
SELECT * FROM Transportadora;

-- Tabelas de relacionamento
SELECT * FROM Endereco;
SELECT * FROM Contato;
SELECT * FROM DocumentoFiscal;

-- Tabelas de notas fiscais
SELECT * FROM TbStatus;
SELECT * FROM NotaFiscal;
SELECT * FROM DadosAdicionais;
SELECT * FROM ItemNotaFiscal;

-- Tabelas de produtos e serviços
SELECT * FROM Produto;
SELECT * FROM Servico;

-- Tabelas de impostos
SELECT * FROM Imposto;
SELECT * FROM TipoImposto;
SELECT * FROM ImpostoItem;

-- Tabelas de frete
SELECT * FROM Frete;
SELECT * FROM VolumeTransportado;

-- Tabelas de eventos
SELECT * FROM EventoNF;

-- Tabelas de pagamento
SELECT * FROM FormaPagamento;
SELECT * FROM Fatura;
SELECT * FROM Pagamento;


