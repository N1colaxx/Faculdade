### NotaFiscal
   - id_nf (PK)
   - chave_acesso 
   - modelo
   - serie
   - numero
   - data_autorizacao
   - data_emissao
   - data_saida_entrada
   - hora_saida_entrada
   - natureza_da_operacao
   - protocolo_autorizacao
   - valor_total_nf
   - valor_total_produtos
   - valor_total_servicos
   - valor_desconto
   - valor_outras_despesas
   - valor_frete
   - valor_total_impostos


   Relacionamentos:
      - NotaFiscal 1:1 TbStatus
      - NotaFiscal 1:1 DadosAdicionais
      - NotaFiscal 1:N ItemNotaFiscal
      - NotaFiscal 1:N EventoNF  
      - NotaFiscal 1:N Pagamento
      - NotaFiscal 1:0..1 Frete
      - NotaFiscal 1:N Fatura
      - NotaFiscal N:1 TbStatus
      - NotaFiscal N:1 TipoOperaçao

### TbStatus
    - id_status (PK)
    - cod
    - nome

### TipoOperacao
    - id_tipop (PK)
    - cod
    - nome


### DadosAdicionais
    - id_dados_adicionais (PK)
    - id_nf (FK)
    - informacoes_complementares
    - observacoes

### ItemNotaFiscal
   - id_item_nf (PK)
   - id_nf (FK)
   - id_produto (FK, opcional)
   - id_servico (FK, opcional)
   - cfop
   - quantidade
   - valor_unitario
   - valor_desconto
   - valor_total_item
   - valor_total_impostos_item

   Relacionamentos ItemNotaFiscal:
      - ItemNotaFiscal N:1 Produto (opcional)
      - ItemNotaFiscal N:1 Servico (opcional)
      - ItemNotaFiscal 1:N ImpostoItem

### Pessoa
   - id_pessoa (PK)
   - tipo_pessoa
   - tipo_documento
   - numero_documento
   - nome_razao_social

   Relacionamentos Pessoa:
      - Pessoa 1:N Endereco
      - Pessoa 1:N Contato
      - Pessoa 1:N DocumentoFiscal
      - Pessoa 1:1 Emitente (opcional)
      - Pessoa 1:1 Destinatario (opcional)
      - Pessoa 1:1 Remetente (opcional)


### PessoaFisica
   - id_pf (PK)
   - id_pessoa (FK)

### PessoaJuridica
   - id_pj (PK)
   - id_pessoa (FK)
   - nome_fantasia
   - regime_tributario
   - inscricao_estadual
   - inscricao_suframa
   - is_contribuinte_icms
   - cnae_fiscal

### Emitente 
   id_emitente (PK)
   id_pessoa (FK)

### Destinatario  
   id_destinatario (PK)
   id_pessoa (FK)

### Remetente
   id_remetente (PK)
   id_pessoa (FK)

### Endereco
   - id_endereco (PK)
   - id_pessoa (FK)
   - id_transportadora (FK, opcional)
   - tipo
   - logradouro
   - numero
   - complemento
   - bairro
   - municipio
   - uf
   - cep
   - codigo_pais

### Contato
   - id_contato (PK)
   - id_pessoa (FK)
   - id_transportadora (FK, opcional)
   - tipo
   - ddd
   - numero
   - email

### DocumentoFiscal
   - id_documento_fiscal (PK)
   - id_pessoa (FK)
   - tipo_documento
   - numero_documento

### Produto
   - id_produto (PK)
   - codigo
   - nome
   - descricao
   - ncm
   - cest
   - unidade_comercial
   - valor_unitario

### Servico
   - id_servico (PK)
   - codigo
   - nome
   - descricao
   - valor_unitario

### Imposto
   - id_imposto (PK)
   - codigo
   - nome
   - descricao

### TipoImposto
   - id_tipo_imposto (PK)
   - id_imposto (FK)
   - codigo_tipo
   - descricao_tipo

### ImpostoItem
   - id_imposto_item (PK)
   - id_item_nf (FK)
   - id_tipo_imposto (FK)
   - situacao_tributaria
   - base_calculo
   - aliquota
   - valor
   - base_calculo_st (opcional)
   - aliquota_st (opcional)
   - valor_st (opcional)
   - valor_fcp (opcional)

### Frete
   - id_frete (PK)
   - id_nf (FK)
   - id_transportadora (FK)
   - id_tipo_frete (FK)
   - placa_veiculo
   - uf_veiculo
   - valor_frete

### TipoFrete
   id_tipfrete (PK)
   cod
   nome

### VolumeTransportado
   - id_volume (PK)
   - id_frete (FK)
   - quantidade
   - especie
   - marca
   - numeracao
   - peso_bruto
   - peso_liquido

### Transportadora
   - id_transportadora (PK)
   - nome
   - cnpj
   - id_pessoa_pj (FK)

### EventoNF
   - id_evento (PK)
   - id_nf (FK)
   - tipo_evento
   - motivo
   - data_hora
   - usuario
   - xml_referencia (opcional)

### Fatura
   - id_fatura (PK)
   - id_nf (FK)
   - numero
   - valor
   - data_vencimento

### Pagamento
   - id_pagamento (PK)
   - id_nf (FK)
   - id_forma_pagamento (FK)
   - valor
   - data_pagamento
   - status

### FormaPagamento
   - id_forma_pagamento (PK)
   - codigo
   - descricao