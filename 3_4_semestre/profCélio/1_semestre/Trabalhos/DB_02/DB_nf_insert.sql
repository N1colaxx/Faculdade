
INSERT INTO TbStatus (cod) VALUES 
('EM_DIGITACAO'), ('VALIDADA'), ('AUTORIZADA'), ('CANCELADA'), ('DENEGADA');
select * from TbStatus ts ;

-- Insert pessoas
INSERT INTO Pessoa (tipo_pessoa, tipo_documento, numero_documento, nome_razao_social) VALUES 
('F', 'CPF', '12345678901', 'João da Silva'),
('J', 'CNPJ', '12345678000101', 'Empresa ABC Ltda'),
('J', 'CNPJ', '98765432000121', 'Comércio XYZ S/A'),
('F', 'CPF', '98765432109', 'Maria Oliveira'),
('J', 'CNPJ', '45678912000134', 'Indústria QWE EIRELI');
select * from Pessoa p ;


-- Insert pessoa física
INSERT INTO PessoaFisica (id_pessoa, cod) VALUES 
(1, 'PF001'),
(4, 'PF002');
select * from PessoaFisica pf ;

-- Insert pessoa jurídica
INSERT INTO PessoaJuridica (id_pessoa, nome_fantasia, regime_tributario, inscricao_estadual, is_contribuinte_icms, cnae_fiscal) VALUES 
(2, 'ABC Comércio', 'Simples Nacional', '123456789', 1, '4712102'),
(3, 'XYZ Distribuidora', 'Lucro Presumido', '987654321', 1, '4631100'),
(5, 'QWE Industrial', 'Lucro Real', '456123789', 1, '2511200');
select * from PessoaJuridica pj ;


-- Insert emitentes
INSERT INTO Emitente (id_pessoa) VALUES (2), (3), (5);
select * from Emitente e ;

-- Insert destinatarios
INSERT INTO Destinatario (id_pessoa) VALUES (1), (4), (2), (3);
select * from Destinatario d ;

-- Insert remetentes
INSERT INTO Remetente (id_pessoa) VALUES (2), (3), (5);
select * from Remetente r ;

-- Insert transportadoras
INSERT INTO Transportadora (nome, cnpj) VALUES 
('Transporte Rápido', '11222333000144'),
('Carga Segura', '55666777000188'),
('Expresso Nacional', '99888777000166');
select * from Transportadora t;

-- Insert endereco
INSERT INTO Endereco (id_pessoa, id_transportadora, tipo, logradouro, numero, complemento, bairro, municipio, uf, cep, codigo_pais) VALUES 
(1, NULL, 'RESIDENCIAL', 'Rua das Flores', '123', 'Apto 101', 'Centro', 'São Paulo', 'SP', '01001000', '1058'),
(2, NULL, 'COMERCIAL', 'Avenida Paulista', '1000', '10º andar', 'Bela Vista', 'São Paulo', 'SP', '01310000', '1058'),
(3, NULL, 'COMERCIAL', 'Rua da Paz', '500', '', 'Jardim', 'Rio de Janeiro', 'RJ', '20040040', '1058'),
(4, NULL, 'RESIDENCIAL', 'Rua das Palmeiras', '300', 'Casa 2', 'Vila Nova', 'Belo Horizonte', 'MG', '30130000', '1058'),
(5, NULL, 'INDUSTRIAL', 'Avenida Industrial', '1500', 'Galpão 3', 'Zona Industrial', 'Curitiba', 'PR', '81200000', '1058'),
(NULL, 1, 'COMERCIAL', 'Rua dos Transportes', '200', 'Sala 5', 'Centro', 'São Paulo', 'SP', '01002000', '1058'),
(NULL, 2, 'COMERCIAL', 'Avenida Logística', '350', '', 'Distrito Industrial', 'Campinas', 'SP', '13050000', '1058'),
(NULL, 3, 'COMERCIAL', 'Rua das Cargas', '700', 'Bloco B', 'Cidade Nova', 'Porto Alegre', 'RS', '90010000', '1058');
select * from Endereco e ;

-- Insert contatos
INSERT INTO Contato (id_pessoa, tipo, ddd, numero, email) VALUES 
(1, 'TELEFONE', '11', '999999999', 'joao@email.com'),
(1, 'EMAIL', NULL, NULL, 'joao@email.com'),
(2, 'TELEFONE', '11', '88888888', 'contato@abc.com.br'),
(2, 'FAX', '11', '88888887', NULL),
(3, 'TELEFONE', '21', '77777777', 'vendas@xyz.com.br'),
(4, 'TELEFONE', '31', '66666666', 'maria@email.com'),
(5, 'TELEFONE', '41', '55555555', 'contato@qwe.com.br');
select * from Contato c ;

-- Insert produtos
INSERT INTO Produto (codigo, nome, descricao, ncm, cest, unidade_comercial, valor_unitario) VALUES 
('P001', 'Notebook', 'Notebook Core i5 8GB RAM', '84713000', '1709600', 'UN', 3500.00),
('P002', 'Mouse', 'Mouse sem fio', '84716071', '1709700', 'UN', 120.00),
('P003', 'Teclado', 'Teclado mecânico', '84716072', '1709800', 'UN', 250.00),
('P004', 'Monitor', 'Monitor 24 polegadas', '85285200', '1709900', 'UN', 1200.00),
('P005', 'Impressora', 'Impressora multifuncional', '84433190', '1710000', 'UN', 800.00);
select * from Produto p ;

-- Insert serviços
INSERT INTO Servico (codigo, nome, descricao, valor_unitario) VALUES 
('S001', 'Instalação', 'Instalação de equipamentos', 150.00),
('S002', 'Manutenção', 'Manutenção preventiva', 300.00),
('S003', 'Consultoria', 'Consultoria em TI', 500.00),
('S004', 'Suporte', 'Suporte técnico remoto', 200.00),
('S005', 'Treinamento', 'Treinamento de usuários', 350.00);
select * from Servico s ;

-- Insert impostos
INSERT INTO Imposto (codigo, nome, descricao) VALUES 
('ICMS', 'ICMS', 'Imposto sobre Circulação de Mercadorias e Serviços'),
('IPI', 'IPI', 'Imposto sobre Produtos Industrializados'),
('PIS', 'PIS', 'Programa de Integração Social'),
('COFINS', 'COFINS', 'Contribuição para Financiamento da Seguridade Social'),
('ISS', 'ISS', 'Imposto sobre Serviços');
select * from Imposto i ;

-- Insert tipos de impost;os
INSERT INTO TipoImposto (id_imposto, codigo_tipo, descricao_tipo) VALUES 
(1, '00', 'ICMS Tributada integralmente'),
(1, '20', 'ICMS Com redução de base de cálculo'),
(1, '40', 'ICMS Isenta'),
(1, '41', 'ICMS Não tributada'),
(2, '50', 'IPI Isento'),
(2, '01', 'IPI Tributado'),
(3, '01', 'PIS Tributado'),
(3, '02', 'PIS Isento'),
(4, '01', 'COFINS Tributado'),
(4, '02', 'COFINS Isento'),
(5, '01', 'ISS Tributado'),
(5, '02', 'ISS Isento');
select * from TipoImposto ti ;

-- Insert formas de pagamento
INSERT INTO FormaPagamento (codigo, descricao) VALUES 
('01', 'Dinheiro'),
('02', 'Cheque'),
('03', 'Cartão de Crédito'),
('04', 'Cartão de Débito'),
('05', 'Crédito Loja'),
('10', 'Vale Alimentação'),
('11', 'PIX'),
('12', 'Boleto Bancário');
select * from FormaPagamento fp ;

-- Insert notas fiscais
INSERT INTO NotaFiscal (
    chave_acesso, tipo_operacao, modelo, serie, numero, data_emissao, 
    data_saida_entrada, hora_saida_entrada, natureza_da_operacao, 
    protocolo_autorizacao, status, data_autorizacao, valor_total_nf, 
    valor_total_produtos, valor_total_servicos, valor_desconto, 
    valor_outras_despesas, valor_frete, valor_total_impostos, 
    id_emitente, id_destinatario, id_remetente
) VALUES 
('12345678901234567890123456789012345678901234', 'SAIDA', '55', '1', '123', '2023-01-10', 
 '2023-01-10', '14:30:00', 'VENDA', '123456789012345', 'AUTORIZADA', '2023-01-10 14:35:00', 
 3870.00, 3500.00, 150.00, 100.00, 120.00, 200.00, 300.00, 1, 1, 1),
('98765432109876543210987654321098765432109876', 'SAIDA', '55', '1', '124', '2023-01-15', 
 '2023-01-15', '10:15:00', 'VENDA', '987654321098765', 'AUTORIZADA', '2023-01-15 10:20:00', 
 1570.00, 1200.00, 300.00, 50.00, 20.00, 100.00, 200.00, 1, 2, 1),
('56789012345678901234567890123456789012345678', 'ENTRADA', '55', '1', '125', '2023-01-20', 
 '2023-01-20', '16:45:00', 'COMPRA', '567890123456789', 'AUTORIZADA', '2023-01-20 16:50:00', 
 2500.00, 2500.00, NULL, 0.00, 0.00, 0.00, 500.00, 2, 1, 2),
('34567890123456789012345678901234567890123456', 'SAIDA', '55', '1', '126', '2023-02-01', 
 '2023-02-01', '09:30:00', 'VENDA', '345678901234567', 'AUTORIZADA', '2023-02-01 09:35:00', 
 4200.00, 3500.00, 500.00, 100.00, 100.00, 200.00, 400.00, 1, 3, 1),
('78901234567890123456789012345678901234567890', 'ENTRADA', '55', '1', '127', '2023-02-05', 
 '2023-02-05', '11:20:00', 'COMPRA', '789012345678901', 'AUTORIZADA', '2023-02-05 11:25:00', 
 800.00, 800.00, NULL, 0.00, 0.00, 0.00, 160.00, 3, 1, 3);
select * from NotaFiscal nf ;

-- Insert dados adicionais
INSERT INTO DadosAdicionais (id_nf, informacoes_complementares, observacoes) VALUES 
(1, 'Pagamento à vista', 'Entregar no endereço comercial'),
(2, 'Pagamento em 30 dias', 'Entregar com nota fiscal'),
(3, 'Compra para estoque', 'Favor embalar com cuidado'),
(4, 'Pagamento via cartão', 'Cliente retira no local'),
(5, 'Compra para revenda', 'Enviar junto com pedido 123');
select * from DadosAdicionais da ;

-- Insert itens nota fiscal
INSERT INTO ItemNotaFiscal (
    id_nf, id_produto, id_servico, cfop, quantidade, valor_unitario, 
    valor_desconto, valor_total_item, valor_total_impostos_item
) VALUES 
(1, 1, NULL, '5102', 1, 3500.00, 100.00, 3400.00, 300.00),
(1, NULL, 1, '5933', 1, 150.00, 0.00, 150.00, 0.00),
(2, 4, NULL, '5102', 1, 1200.00, 50.00, 1150.00, 200.00),
(2, NULL, 2, '5933', 1, 300.00, 0.00, 300.00, 0.00),
(3, 3, NULL, '1102', 10, 250.00, 0.00, 2500.00, 500.00),
(4, 1, NULL, '5102', 1, 3500.00, 100.00, 3400.00, 400.00),
(4, NULL, 3, '5933', 1, 500.00, 0.00, 500.00, 0.00),
(5, 5, NULL, '1102', 1, 800.00, 0.00, 800.00, 160.00);

select * from ItemNotaFiscal inf ;


-- Insert impostos dos itens
INSERT INTO ImpostoItem (
    id_item_nf, id_tipo_imposto, situacao_tributaria, base_calculo, 
    aliquota, valor, base_calculo_st, aliquota_st, valor_st, valor_fcp
) VALUES 
(1, 1, '00', 3400.00, 18.00, 612.00, NULL, NULL, NULL, NULL),
(1, 2, '50', 3400.00, 0.00, 0.00, NULL, NULL, NULL, NULL),
(1, 3, '01', 3400.00, 1.65, 56.10, NULL, NULL, NULL, NULL),
(1, 4, '01', 3400.00, 7.6, 258.40, NULL, NULL, NULL, NULL),
(3, 1, '00', 1150.00, 18.00, 207.00, NULL, NULL, NULL, NULL),
(5, 1, '00', 2500.00, 18.00, 450.00, NULL, NULL, NULL, NULL),
(6, 1, '00', 3400.00, 18.00, 612.00, NULL, NULL, NULL, NULL),
(8, 1, '00', 800.00, 18.00, 144.00, NULL, NULL, NULL, NULL);
select * from Imposto i ;

-- Insert fretes
INSERT INTO Frete (
    id_nf, id_transportadora, tipo_frete, placa_veiculo, uf_veiculo, valor_frete
) VALUES 
(1, 1, 'CIF', 'ABC1234', 'SP', 200.00),
(2, 2, 'FOB', 'XYZ9876', 'RJ', 100.00),
(4, 1, 'CIF', 'DEF5678', 'SP', 200.00);
select * from Frete f ;

-- Insert volumes transportados
INSERT INTO VolumeTransportado (
    id_frete, quantidade, especie, marca, numeracao, peso_bruto, peso_liquido
) VALUES 
(1, 1, 'Caixa', 'ABC', '12345', 5.000, 4.500),
(2, 2, 'Pacote', 'XYZ', '67890', 3.000, 2.800),
(3, 1, 'Caixa', 'DEF', '54321', 6.000, 5.500);
select * from VolumeTransportado vt ;


-- Insert eventos NF
INSERT INTO EventoNF (
    id_nf, tipo_evento, motivo, data_hora, usuario, xml_referencia
) VALUES 
(1, 'EMISSAO', 'Emissão normal', '2023-01-10 14:30:00', 'sistema', NULL),
(1, 'AUTORIZACAO', 'Autorizada', '2023-01-10 14:35:00', 'sefaz', 'xml123.xml'),
(2, 'EMISSAO', 'Emissão normal', '2023-01-15 10:15:00', 'sistema', NULL),
(2, 'AUTORIZACAO', 'Autorizada', '2023-01-15 10:20:00', 'sefaz', 'xml456.xml'),
(3, 'EMISSAO', 'Emissão normal', '2023-01-20 16:45:00', 'sistema', NULL),
(3, 'AUTORIZACAO', 'Autorizada', '2023-01-20 16:50:00', 'sefaz', 'xml789.xml'),
(4, 'EMISSAO', 'Emissão normal', '2023-02-01 09:30:00', 'sistema', NULL),
(4, 'AUTORIZACAO', 'Autorizada', '2023-02-01 09:35:00', 'sefaz', 'xml012.xml'),
(5, 'EMISSAO', 'Emissão normal', '2023-02-05 11:20:00', 'sistema', NULL),
(5, 'AUTORIZACAO', 'Autorizada', '2023-02-05 11:25:00', 'sefaz', 'xml345.xml');
select * from EventoNF en ;

-- Insert faturas
INSERT INTO Fatura (id_nf, numero, valor, data_vencimento) VALUES 
(1, 'FAT001', 3870.00, '2023-01-10'),
(2, 'FAT002', 1570.00, '2023-02-15'),
(3, 'FAT003', 2500.00, '2023-02-20'),
(4, 'FAT004', 4200.00, '2023-03-01'),
(5, 'FAT005', 800.00, '2023-03-05');
select * from Fatura f ;

-- Insert pagamentos
INSERT INTO Pagamento (
    id_nf, id_forma_pagamento, valor, data_pagamento, status
) VALUES 
(1, 1, 3870.00, '2023-01-10', 'PAGO'),
(2, 3, 1570.00, '2023-01-15', 'PAGO'),
(3, 8, 2500.00, '2023-01-25', 'PAGO'),
(4, 3, 4200.00, '2023-02-01', 'PAGO'),
(5, 8, 800.00, '2023-02-10', 'PAGO');
select * from Pagamento p ;