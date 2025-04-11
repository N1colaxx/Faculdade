
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
        tipo_operacao
        natureza_da_operacao
        inscricao_estadual
        inscricao_estadual_sub_tributaria
        cnpj
        protocolo_uso
        chave_acesso
        data_emissao
        data_saida_entrada
        hora_saida
        id_endereco_emitente (FK → Endereco) (NotaFiscal: {1,1} ←→ {1,1} Endereco)
        id_destinatario (FK → Pessoa)          (NotaFiscal: {1,1} ←→ {1,1} Pessoa)
        id_fatura (FK → Fatura)                (NotaFiscal: {0,1} ←→ {1,1} Fatura)
        id_transportador (FK → Transportador)  (NotaFiscal: {0,1} ←→ {1,1} Transportador)


    Destinatario/Remetente (Pessoa):
        id_pessoa (PK)
        nome_razao_social
        cnpj_cpf
        fone_fax
        inscricao_estadual
        id_endereco (FK → Endereco) (Pessoa: {1,1}  {0,N} Endereco)


    Fatura:
        id_fatura (PK)
        numero
        data_vcto
        valor

          

    CalculoDoImposto:
        id_calculo (PK)
        id_nf (FK → NotaFiscal) (CalculoDoImposto: {1,1}  {1,1} NotaFiscal)
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
        valor_impostos
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
        id_endereco (FK → Endereco) (Transportador: {1,1}  {0,N} Endereco)

    VolumeTransportado:
        id_volume (PK)
        id_transportador (FK → Transportador) (VolumeTransportado: {0,N}  {1,1} Transportador)
        quantidade
        especie
        marca
        numeracao
        peso_bruto
        peso_liquido




    DadosDoProduto/Servico:
        id_item (PK)
        id_nf (FK → NotaFiscal) (DadosDoProduto/Servico: {1,N}  {1,1} NotaFiscal)
        cod_produto
        descricao_produto/serviso
        ncm/sh
        cst
        cfop
        unidade
        quantidade
        valor_unitario
        valor_total
        base_calculo_icms_substituicao
        base_calculo_icms_substituicao_tributaria
        valor_icms
        valor_ipi
        valor_pis
        valor_cfns
        alicotas_icmss/ipi
        valor_total_impostos


    CalculoDoISSQN:
        id_issqn (PK)
        id_nf (FK → NotaFiscal) (CalculoDoISSQN: {0,1}  {1,1} NotaFiscal)
        inscricao_municipal
        valor_total_servicos
        base_calculo_issqn
        valor_issqn



    DadosAdicionais:
        id_dados (PK)
        id_nf (FK → NotaFiscal) (DadosAdicionais: {0,1}  {1,1} NotaFiscal)
        informacoes_complementares
        observacoes_fisco
        reservado_ao_fisco





sub tabs:

    Endereco:
        id_endereco (PK)
        rua
        numero
        bairro
        cep
        municipio
        uf
