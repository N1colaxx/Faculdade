package model;

import java.util.Date;


public class NotaFiscalModel {

    private String chaveAcesso;
    private int modelo,
            serie,
            numero;
    private Date dataAutorizacao,
            dataEmissao,
            dataSaidaEntrada;
    private String horaSaidaEntrada,
            naturezaDaOperacao,
            protocoloAutorizacao;
    private double valorTotalNf,
            valorProdutos,
            valorServicos,
            valorDesconto,
            valorOutrasDespesas,
            valorFrete;
    private EmitenteModel emitente;
    private RemetenteModel remetente;
    private DestinatarioModel destinatario;
    private TransportadoraModel transportadora;
    private FaturaModel fatura;
    private CalculoImpostoModel calculoImposto;
    private StatusModel.StatusNFE status;
    private TipoNfModel.TipoNFE tipo;

    
    public NotaFiscalModel(){
    }
    
    public NotaFiscalModel(String chaveAcesso, int modelo, int serie, int numero, Date dataAutorizacao, Date dataEmissao,
            Date dataSaidaEntrada, String horaSaidaEntrada, String naturezaDaOperacao, String protocoloAutorizacao, 
            double valorTotalNf, double valorProdutos, double valorServicos, double valorDesconto, double valorOutrasDespesas, 
            double valorFrete, EmitenteModel emitente, RemetenteModel remetente, DestinatarioModel destinatario, TransportadoraModel transportadora, 
            FaturaModel fatura, CalculoImpostoModel calculoImposto, StatusModel.StatusNFE status, TipoNfModel.TipoNFE tipo) {
        this.chaveAcesso = chaveAcesso;
        this.modelo = modelo;
        this.serie = serie;
        this.numero = numero;
        this.dataAutorizacao = dataAutorizacao;
        this.dataEmissao = dataEmissao;
        this.dataSaidaEntrada = dataSaidaEntrada;
        this.horaSaidaEntrada = horaSaidaEntrada;
        this.naturezaDaOperacao = naturezaDaOperacao;
        this.protocoloAutorizacao = protocoloAutorizacao;
        this.valorTotalNf = valorTotalNf;
        this.valorProdutos = valorProdutos;
        this.valorServicos = valorServicos;
        this.valorDesconto = valorDesconto;
        this.valorOutrasDespesas = valorOutrasDespesas;
        this.valorFrete = valorFrete;
        this.emitente = emitente;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.transportadora = transportadora;
        this.fatura = fatura;
        this.calculoImposto = calculoImposto;
        this.status = status;
        this.tipo = tipo;
    }


//    Getters
    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public int getModelo() {
        return modelo;
    }

    public int getSerie() {
        return serie;
    }

    public int getNumero() {
        return numero;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public Date getDataSaidaEntrada() {
        return dataSaidaEntrada;
    }

    public String getHoraSaidaEntrada() {
        return horaSaidaEntrada;
    }

    public String getNaturezaDaOperacao() {
        return naturezaDaOperacao;
    }

    public String getProtocoloAutorizacao() {
        return protocoloAutorizacao;
    }

    public double getValorTotalNf() {
        return valorTotalNf;
    }

    public double getValorProdutos() {
        return valorProdutos;
    }

    public double getValorServicos() {
        return valorServicos;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public double getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public EmitenteModel getEmitente() {
        return emitente;
    }

    public RemetenteModel getRemetente() {
        return remetente;
    }

    public DestinatarioModel getDestinatario() {
        return destinatario;
    }

    public TransportadoraModel getTransportadora() {
        return transportadora;
    }

    public FaturaModel getFatura() {
        return fatura;
    }

    public CalculoImpostoModel getCalculoImposto() {
        return calculoImposto;
    }

    public StatusModel.StatusNFE getStatus() {
        return status;
    }

    public TipoNfModel.TipoNFE getTipo() {
        return tipo;
    }

//    Setters
    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataSaidaEntrada(Date dataSaidaEntrada) {
        this.dataSaidaEntrada = dataSaidaEntrada;
    }

    public void setHoraSaidaEntrada(String horaSaidaEntrada) {
        this.horaSaidaEntrada = horaSaidaEntrada;
    }

    public void setNaturezaDaOperacao(String naturezaDaOperacao) {
        this.naturezaDaOperacao = naturezaDaOperacao;
    }

    public void setProtocoloAutorizacao(String protocoloAutorizacao) {
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public void setValorTotalNf(double valorTotalNf) {
        this.valorTotalNf = valorTotalNf;
    }

    public void setValorProdutos(double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public void setValorServicos(double valorServicos) {
        this.valorServicos = valorServicos;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setValorOutrasDespesas(double valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public void setEmitente(EmitenteModel emitente) {
        this.emitente = emitente;
    }

    public void setRemetente(RemetenteModel remetente) {
        this.remetente = remetente;
    }

    public void setDestinatario(DestinatarioModel destinatario) {
        this.destinatario = destinatario;
    }

    public void setTransportadora(TransportadoraModel transportadora) {
        this.transportadora = transportadora;
    }

    public void setFatura(FaturaModel fatura) {
        this.fatura = fatura;
    }

    public void setCalculoImposto(CalculoImpostoModel calculoImposto) {
        this.calculoImposto = calculoImposto;
    }

    public void setStatus(StatusModel.StatusNFE status) {
        this.status = status;
    }

    public void setTipo(TipoNfModel.TipoNFE tipo) {
        this.tipo = tipo;
    }
    
    
    @Override
    public String toString() {
    return "\n" + "\n" +
            " ---- NotaFiscalModel ---- " +
            "\n  Chave de Acesso: " + chaveAcesso +
            "\n  Status: " + status +
            "\n  Tipo: " + tipo +
            "\n  Modelo: " + modelo +
            "\n  Série: " + serie +
            "\n  Número: " + numero +
            "\n  Data Autorização: " + dataAutorizacao +
            "\n  Data Emissão: " + dataEmissao +
            "\n  Data Saída/Entrada: " + dataSaidaEntrada +
            "\n  Hora Saída/Entrada: " + horaSaidaEntrada +
            "\n  Natureza da Operação: " + naturezaDaOperacao +
            "\n  Protocolo de Autorização: " + protocoloAutorizacao +
            "\n  Valor Total NF: R$ " + valorTotalNf +
            "\n  Valor Produtos: R$ " + valorProdutos +
            "\n  Valor Serviços: R$ " + valorServicos +
            "\n  Valor Desconto: R$ " + valorDesconto +
            "\n  Valor Outras Despesas: R$ " + valorOutrasDespesas +
            "\n  Valor Frete: R$ " + valorFrete + "\n" +
            (emitente != null ? emitente.toString() : "null") + "\n" +
            (remetente != null ? remetente.toString() : "null") + "\n" +
            (destinatario != null ? destinatario.toString() : "null") + "\n" +
            (transportadora != null ? transportadora.toString() : "null") + "\n" +
            (fatura != null ? fatura.toString() : "null") + "\n" +
            (calculoImposto != null ? calculoImposto.toString() : "null") + "\n" +
            "\n}";
    }

}
