
der:
    entidade
    atributo 
    chave candidata
    PK
    relacionamento
    cardinalidae

DER de uma NF:

    NotaFiscal:
        numero_nf
        serie
        tipo_operacao (entrada/saída)
        endereco_emitente
        natureza_da_operacao
        inscricao_estadual
        inscricao_estadual_sub_tributaria
        cnpj
        protocolo_uso
        chave_acesso
        data_emissao
        data_saida/entrada
        hora_saida

    Destinatario/Remetente:
        nome_razao_social
        cnpj_cpf
        endereco
        bairroo_distrito
        cep
        municipio
        fone_fax
        uf
        inscricao_estadual

    Fatura:
        numero
        data_vcto
        valor
          

    CalculoDoImposto:
        base_calculo_icms
        valor_icms
        base_calculo_icms_substituicao
        valor_icms_substituicao
        valor_total_produtos
        valor_frete
        valor_seguro
        desconto
        outras_despessas_acessorias
        valor_ipi
        valor_impostos
        valor_total_nota

    Transportador/VolumesTransportados:
        razao_social
        frete_por_conta
        codigo_antt
        placa_veiculo
        uf
        cnpj_cpf
        endereco
        municipio
        uf
        inscricao_estadual
        quantidae
        especie
        marca
        numeracao
        peso_bruto
        peso_liquido


    DadosDoProduto/Servico:
        cod_produto
        descricao_produto/serviso
        ncm_sh
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
        inscricao_municipal
        valor_total_servicos
        base_calculo_issqn
        valor_issqn


    DadosAdicionais:
         informacoes_complementares
         observacoes_fisco
         reservado_ao_fisco

