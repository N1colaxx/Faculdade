der:
    entidade
    atributo 
    chave candidata
    PK
    relacionamento
    cardinalidae

DER de uma NF:

    NotaFiscal:
        id_nf (PK)
        numero_nf
        serie
        tipo_operacao (entrada/saída)
        natureza_da_operacao
        protocolo_uso
        chave_acesso
        data_emissao
        data_saida_entrada
        hora_saida
        id_emitente (FK → Emitente)           (NotaFiscal: {1,1} ←→ {1,N} Emitente)
        id_destinatario (FK → Pessoa)         (NotaFiscal: {1,1} ←→ {0,N} Pessoa)
        id_transportador (FK → Transportador) (NotaFiscal: {0,1} ←→ {0,N} Transportador)

    Emitente:
        id_emitente (PK)
        razao_social
        cnpj
        inscricao_estadual
        inscricao_estadual_sub_tributaria
        id_endereco (FK → Endereco)           (Emitente: {1,1} ←→ {0,N} Endereco)

    Pessoa (destinatario/remetente):
        id_pessoa (PK)
        tipo_pessoa (destinatario/remetente)
        nome_razao_social
        cnpj_cpf
        fone_fax
        inscricao_estadual
        id_endereco (FK → Endereco)           (Pessoa: {1,1} ←→ {0,N} Endereco)

    Endereco:
        id_endereco (PK)
        logradouro
        numero
        complemento
        bairro
        cep
        municipio
        uf

    Fatura:
        id_fatura (PK)
        id_nf (FK → NotaFiscal)               (Fatura: {0,N} ←→ {1,1} NotaFiscal)
        numero
        data_vencimento
        valor

    CalculoDoImposto:
        id_calculo (PK)
        id_nf (FK → NotaFiscal)               (CalculoDoImposto: {1,1} ←→ {1,1} NotaFiscal)
        base_calculo_icms
        valor_icms
        base_calculo_icms_substituicao
        valor_icms_substituicao
        valor_total_produtos
        valor_frete
        valor_seguro
        desconto
        outras_despesas_acessorias
        valor_ipi
        valor_total_impostos
        valor_total_nota

    Transportador:
        id_transportador (PK)
        razao_social
        frete_por_conta
        codigo_antt
        placa_veiculo
        uf_placa
        cnpj_cpf
        inscricao_estadual
        id_endereco (FK → Endereco)           (Transportador: {1,1} ←→ {0,N} Endereco)

    VolumeTransportado:
        id_volume (PK)
        id_transportador (FK → Transportador) (VolumeTransportado: {0,N} ←→ {1,1} Transportador)
        quantidade
        especie
        marca
        numeracao
        peso_bruto
        peso_liquido

    Produto:
        id_produto (PK)
        codigo
        descricao
        ncm_sh
        unidade_padrao

    ItemNotaFiscal:
        id_item (PK)
        id_nf (FK → NotaFiscal)               (ItemNotaFiscal: {1,N} ←→ {1,1} NotaFiscal)
        id_produto (FK → Produto)             (ItemNotaFiscal: {0,N} ←→ {1,1} Produto)
        cst
        cfop
        unidade
        quantidade
        valor_unitario
        valor_total
        base_calculo_icms
        valor_icms
        base_calculo_icms_substituicao
        valor_icms_substituicao
        valor_ipi
        valor_pis
        valor_cofins
        aliquota_icms
        aliquota_ipi
        valor_total_impostos

    CalculoDoISSQN:
        id_issqn (PK)
        id_nf (FK → NotaFiscal)               (CalculoDoISSQN: {0,1} ←→ {1,1} NotaFiscal)
        inscricao_municipal
        valor_total_servicos
        base_calculo_issqn
        valor_issqn

    DadosAdicionais:
        id_dados (PK)
        id_nf (FK → NotaFiscal)               (DadosAdicionais: {0,1} ←→ {1,1} NotaFiscal)
        informacoes_complementares
        observacoes_fisco
        reservado_ao_fisco

