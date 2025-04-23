// Package para o modelo de domínio
package com.nfe.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que representa uma Nota Fiscal Eletrônica
 */
public class NotaFiscal {
    private String id;
    private String chaveAcesso;
    private TipoOperacao tipoOperacao;
    private String modelo;
    private String serie;
    private String numero;
    private LocalDate dataEmissao;
    private LocalDate dataSaidaEntrada;
    private LocalTime horaSaidaEntrada;
    private String naturezaOperacao;
    private String protocoloAutorizacao;
    private StatusNFe status;
    private LocalDate dataAutorizacao;
    private BigDecimal valorTotalNf;
    private BigDecimal valorTotalProdutos;
    private BigDecimal valorTotalServicos;
    private BigDecimal valorDesconto;
    private BigDecimal valorOutrasDespesas;
    private BigDecimal valorFrete;
    private BigDecimal valorTotalImpostos;
    
    // Relacionamentos
    private Pessoa emitente;
    private Pessoa destinatario;
    private Pessoa transportadora;
    private DadosAdicionais dadosAdicionais;
    private Frete frete;
    private List<ItemNotaFiscal> itens = new ArrayList<>();
    private List<EventoNF> eventos = new ArrayList<>();
    private List<Pagamento> pagamentos = new ArrayList<>();
    private List<Fatura> faturas = new ArrayList<>();
    
    // Enums para a classe NotaFiscal
    public enum TipoOperacao {
        ENTRADA, SAIDA
    }
    
    public enum StatusNFe {
        EM_DIGITACAO, VALIDADA, AUTORIZADA, CANCELADA, DENEGADA
    }
    
    // Construtores, getters e setters
    public NotaFiscal() {
    }
    
    public NotaFiscal(String id, String chaveAcesso, TipoOperacao tipoOperacao, String modelo, 
                      String serie, String numero, LocalDate dataEmissao) {
        this.id = id;
        this.chaveAcesso = chaveAcesso;
        this.tipoOperacao = tipoOperacao;
        this.modelo = modelo;
        this.serie = serie;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.status = StatusNFe.EM_DIGITACAO;
    }
    
    // Métodos de negócio
    public void adicionarItem(ItemNotaFiscal item) {
        if (this.status == StatusNFe.AUTORIZADA || this.status == StatusNFe.CANCELADA) {
            throw new IllegalStateException("Não é possível adicionar itens a uma nota autorizada ou cancelada");
        }
        this.itens.add(item);
        recalcularTotais();
    }
    
    public void adicionarEvento(EventoNF evento) {
        this.eventos.add(evento);
    }
    
    public void cancelar(String motivo, String usuario) {
        if (this.status != StatusNFe.AUTORIZADA) {
            throw new IllegalStateException("Apenas notas autorizadas podem ser canceladas");
        }
        this.status = StatusNFe.CANCELADA;
        
        // Cria evento de cancelamento
        EventoNF evento = new EventoNF();
        evento.setIdNf(this.id);
        evento.setTipoEvento(EventoNF.TipoEvento.CANCELAMENTO);
        evento.setMotivo(motivo);
        evento.setDataHora(LocalDateTime.now());
        evento.setUsuario(usuario);
        
        this.eventos.add(evento);
    }
    
    private void recalcularTotais() {
        BigDecimal totalProdutos = BigDecimal.ZERO;
        BigDecimal totalServicos = BigDecimal.ZERO;
        BigDecimal totalImpostos = BigDecimal.ZERO;
        
        for (ItemNotaFiscal item : itens) {
            if (item.getProduto() != null) {
                totalProdutos = totalProdutos.add(item.getValorTotalItem());
            } else if (item.getServico() != null) {
                totalServicos = totalServicos.add(item.getValorTotalItem());
            }
            totalImpostos = totalImpostos.add(item.getValorTotalImpostosItem());
        }
        
        this.valorTotalProdutos = totalProdutos;
        this.valorTotalServicos = totalServicos;
        this.valorTotalImpostos = totalImpostos;
        
        // Calcula o total da nota
        this.valorTotalNf = totalProdutos.add(totalServicos)
                            .add(this.valorFrete != null ? this.valorFrete : BigDecimal.ZERO)
                            .add(this.valorOutrasDespesas != null ? this.valorOutrasDespesas : BigDecimal.ZERO)
                            .subtract(this.valorDesconto != null ? this.valorDesconto : BigDecimal.ZERO);
    }
    
    // Getters e Setters
    // (Implementação dos getters e setters omitida para brevidade)
}


*
*
*
*
*
*
*




/**
 * Classe que representa os dados adicionais da nota fiscal
 */
public class DadosAdicionais {
    private String id;
    private String idNf;
    private String informacoesComplementares;
    private String observacoes;
    
    // Construtores, getters e setters
}

*
*
*
*
*
*
*

/**
 * Classe que representa uma pessoa (emitente, destinatário, transportadora)
 */
public class Pessoa {
    private String id;
    private TipoPessoa tipoPessoa;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nomeRazaoSocial;
    private String nomeFantasia;
    private String regimeTributario;
    private String inscricaoEstadual;
    private String inscricaoSuframa;
    private boolean isContribuinteIcms;
    private String cnaeFiscal;
    private String codigoAntt;
    
    // Relacionamentos
    private List<Endereco> enderecos = new ArrayList<>();
    private List<Contato> contatos = new ArrayList<>();
    private List<DocumentoFiscal> documentosFiscais = new ArrayList<>();
    
    // Enums para a classe Pessoa
    public enum TipoPessoa {
        EMITENTE, DESTINATARIO, TRANSPORTADORA
    }
    
    public enum TipoDocumento {
        CPF, CNPJ
    }
    
    // Construtores, getters e setters
    
    // Métodos de negócio
    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    
    public void adicionarContato(Contato contato) {
        this.contatos.add(contato);
    }
    
    public void adicionarDocumentoFiscal(DocumentoFiscal documento) {
        this.documentosFiscais.add(documento);
    }
    
    // Getters e Setters
}



*
*
*
*
*
*
*


/**
 * Classe que representa um documento fiscal da pessoa
 */
public class DocumentoFiscal {
    private String id;
    private String idPessoa;
    private String tipoDocumento;
    private String numeroDocumento;
    
    // Construtores, getters e setters
}



*
*
*
*
*
*
*


/**
 * Classe que representa um endereço
 */
public class Endereco {
    private String id;
    private String idPessoa;
    private TipoEndereco tipo;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String codigoPais;
    
    public enum TipoEndereco {
        COMERCIAL, ENTREGA, COBRANCA, CORRESPONDENCIA
    }
    
    // Construtores, getters e setters
}


*
*
*
*
*
*
*

/**
 * Classe que representa um contato
 */
public class Contato {
    private String id;
    private String idPessoa;
    private TipoContato tipo;
    private String ddd;
    private String numero;
    private String email;
    
    public enum TipoContato {
        TELEFONE, EMAIL, FAX
    }
    
    // Construtores, getters e setters
}


*
*
*
*
*
*
*


/**
 * Classe que representa um produto
 */
public class Produto {
    private String id;
    private String codigo;
    private String nome;
    private String descricao;
    private String ncm;
    private String cest;
    private String unidadeComercial;
    private BigDecimal valorUnitario;
    
    // Construtores, getters e setters
}





*
*
*
*
*
*
*



/**
 * Classe que representa um serviço
 */
public class Servico {
    private String id;
    private String codigo;
    private String nome;
    private String descricao;
    private BigDecimal valorUnitario;
    
    // Construtores, getters e setters
}




*
*
*
*
*
*
*

/**
 * Classe que representa um item da nota fiscal
 */
public class ItemNotaFiscal {
    private String id;
    private String idNf;
    private String cfop;
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorDesconto;
    private BigDecimal valorTotalItem;
    private BigDecimal valorTotalImpostosItem;
    
    // Relacionamentos
    private Produto produto;
    private Servico servico;
    private List<ImpostoItem> impostos = new ArrayList<>();
    
    // Construtores, getters e setters
    
    // Métodos de negócio
    public void adicionarImposto(ImpostoItem imposto) {
        this.impostos.add(imposto);
        recalcularImpostos();
    }
    
    public void recalcularImpostos() {
        BigDecimal totalImpostos = BigDecimal.ZERO;
        
        for (ImpostoItem imposto : impostos) {
            totalImpostos = totalImpostos.add(imposto.getValor());
        }
        
        this.valorTotalImpostosItem = totalImpostos;
    }
    
    public void calcularTotalItem() {
        if (this.quantidade != null && this.valorUnitario != null) {
            this.valorTotalItem = this.quantidade.multiply(this.valorUnitario);
            
            if (this.valorDesconto != null) {
                this.valorTotalItem = this.valorTotalItem.subtract(this.valorDesconto);
            }
        }
    }
    
    // Getters e Setters
}



*
*
*
*
*
*
*



/**
 * Classe que representa um imposto
 */
public class Imposto {
    private String id;
    private String codigo;
    private String nome;
    private String descricao;
    
    // Relacionamentos
    private List<TipoImposto> tipos = new ArrayList<>();
    
    // Construtores, getters e setters
    
    // Métodos de negócio
    public void adicionarTipo(TipoImposto tipo) {
        this.tipos.add(tipo);
    }
    
    // Getters e Setters
}




*
*
*
*
*
*
*


/**
 * Classe que representa um tipo de imposto (ICMS, IPI, etc)
 */
public class TipoImposto {
    private String id;
    private String idImposto;
    private String codigoTipo;
    private String descricaoTipo;
    
    // Relacionamento
    private Imposto imposto;
    
    // Construtores, getters e setters
}



*
*
*
*
*
*
*


/**
 * Classe que representa um cálculo de imposto para um item
 */
public class ImpostoItem {
    private String id;
    private String idItemNf;
    private String situacaoTributaria; // CST/CSOSN
    private BigDecimal baseCalculo;
    private BigDecimal aliquota;
    private BigDecimal valor;
    private BigDecimal baseCalculoSt;
    private BigDecimal aliquotaSt;
    private BigDecimal valorSt;
    private BigDecimal valorFcp;
    
    // Relacionamento
    private TipoImposto tipoImposto;
    
    // Construtores, getters e setters
    
    // Métodos de negócio
    public void calcularImposto(BigDecimal valorItem) {
        if (this.baseCalculo == null) {
            this.baseCalculo = valorItem;
        }
        
        if (this.baseCalculo != null && this.aliquota != null) {
            // Dividimos por 100 porque a alíquota é em percentual
            this.valor = this.baseCalculo.multiply(this.aliquota.divide(new BigDecimal("100")));
        }
        
        // Cálculo para substituição tributária, se aplicável
        if (this.baseCalculoSt != null && this.aliquotaSt != null) {
            this.valorSt = this.baseCalculoSt.multiply(this.aliquotaSt.divide(new BigDecimal("100")));
        }
    }
    
    // Getters e Setters
}




*
*
*
*
*
*
*


/**
 * Classe que representa um frete
 */
public class Frete {
    private String id;
    private String idNf;
    private TipoFrete tipoFrete;
    private String placaVeiculo;
    private String ufVeiculo;
    private BigDecimal valorFrete;
    
    // Relacionamentos
    private Pessoa transportadora;
    private VolumeTransportado volumeTransportado;
    
    public enum TipoFrete {
        CIF, FOB, TERCEIROS, PROPRIO_REMETENTE, PROPRIO_DESTINATARIO, SEM_FRETE
    }
    
    // Construtores, getters e setters
}

*
*
*
*
*
*
*




/**
 * Classe que representa os volumes transportados
 */
public class VolumeTransportado {
    private String id;
    private String idFrete;
    private Integer quantidade;
    private String especie;
    private String marca;
    private String numeracao;
    private BigDecimal pesoBruto;
    private BigDecimal pesoLiquido;
    
    // Construtores, getters e setters
}


*
*
*
*
*
*
*

/**
 * Classe que representa um evento da NF (emissão, cancelamento, etc)
 */
public class EventoNF {
    private String id;
    private String idNf;
    private TipoEvento tipoEvento;
    private String motivo;
    private LocalDateTime dataHora;
    private String usuario;
    private String xmlReferencia;
    
    public enum TipoEvento {
        EMISSAO, CANCELAMENTO, CORRECAO, MANIFESTACAO
    }
    
    // Construtores, getters e setters
}

*
*
*
*
*
*
*



/**
 * Classe que representa uma fatura
 */
public class Fatura {
    private String id;
    private String idNf;
    private String numero;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    
    // Construtores, getters e setters
}


*
*
*
*
*
*
*



/**
 * Classe que representa um pagamento
 */
public class Pagamento {
    private String id;
    private String idNf;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String status;
    
    // Relacionamento
    private FormaPagamento formaPagamento;
    
    // Construtores, getters e setters
}


*
*
*
*
*
*
*



/**
 * Classe que representa uma forma de pagamento
 */
public class FormaPagamento {
    private String id;
    private String codigo;
    private String descricao;
    
    // Construtores, getters e setters
}
