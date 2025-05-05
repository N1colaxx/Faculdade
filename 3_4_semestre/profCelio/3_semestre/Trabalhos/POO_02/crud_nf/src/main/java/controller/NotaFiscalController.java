package controller;

import dao.NotaFiscalDAO;
import model.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NotaFiscalController {
    private final NotaFiscalDAO dao;
    private final EmitenteController emitenteController;
    private final DestinatarioController destinatarioController;
    private final TransportadoraController transportadoraController;
    private final FaturaController faturaController;
    private final CalculoImpostoController impostoController;

    public NotaFiscalController(NotaFiscalDAO dao, 
                              EmitenteController emitenteController,
                              DestinatarioController destinatarioController,
                              TransportadoraController transportadoraController,
                              FaturaController faturaController,
                              CalculoImpostoController impostoController) {
        this.dao = dao;
        this.emitenteController = emitenteController;
        this.destinatarioController = destinatarioController;
        this.transportadoraController = transportadoraController;
        this.faturaController = faturaController;
        this.impostoController = impostoController;
    }


    public void criarNFe(String chaveAcesso, int modelo, int serie, int numero, 
                       Date dataEmissao, Date dataSaidaEntrada, String horaSaidaEntrada,
                       String naturezaOperacao, String protocoloAutorizacao,
                       double valorTotal, double valorProdutos, double valorServicos,
                       double valorDesconto, double valorOutrasDespesas, double valorFrete,
                       String cnpjEmitente, String cpfCnpjDestinatario, 
                       String cnpjTransportadora, String numeroFatura,
                       double icms, double ipi, double pis, double cofins, double iss,
                       StatusModel.StatusNFE status, TipoNfModel.TipoNFE tipo) {
        
        // Buscar ou criar entidades relacionadas
        EmitenteModel emitente = emitenteController.buscarPorCnpj(cnpjEmitente)
                .orElseThrow(() -> new IllegalArgumentException("Emitente não encontrado"));
        
        Destinatario destinatario = destinatarioController.buscarPorCpfCnpj(cpfCnpjDestinatario)
                .orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado"));
        
        TransportadoraModel transportadora = null;
        if (cnpjTransportadora != null && !cnpjTransportadora.isEmpty()) {
            transportadora = transportadoraController.buscarPorCnpj(cnpjTransportadora)
                    .orElseThrow(() -> new IllegalArgumentException("Transportadora não encontrada"));
        }
        
        FaturaModel fatura = faturaController.criarFatura(numeroFatura, valorTotal);
        CalculoImpostoModel calculoImposto = impostoController.calcularImpostos(icms, ipi, pis, cofins, iss);
        
        NotaFiscalModel nf = new NotaFiscalModel(
            chaveAcesso, modelo, serie, numero,
            new Date(), // dataAutorizacao
            dataEmissao, dataSaidaEntrada,
            horaSaidaEntrada, naturezaOperacao, protocoloAutorizacao,
            valorTotal, valorProdutos, valorServicos,
            valorDesconto, valorOutrasDespesas, valorFrete,
            emitente, null, // remetente
            destinatario, transportadora,
            fatura, calculoImposto,
            status, tipo
        );
        
        dao.adicionar(nf);
    }

    // Outros métodos mantidos conforme sua implementação atual
    public boolean removerPorNumero(int numero) {
        return dao.removerPorNumero(numero);
    }

    public Optional<NotaFiscalModel> buscarPorNumero(int numero) {
        return dao.buscarPorNumero(numero);
    }

    public List<NotaFiscalModel> buscarPorRazaoSocial(String razao) {
        return dao.buscarPorRazaoSocial(razao);
    }

    public List<NotaFiscalModel> buscarPorCpfCnpj(String cpfCnpj) {
        return dao.buscarPorCpfCnpj(cpfCnpj);
    }

    public List<NotaFiscalModel> buscarPorValorTotal(double valorTotal) {
        return dao.buscarPorValorTotal(valorTotal);
    }

    public List<NotaFiscalModel> listarPorIntervalo(int inicio, int fim) {
        return dao.listarPorIntervalo(inicio, fim);
    }

    public List<NotaFiscalModel> listarTodas() {
        return dao.listarTodas();
    }
    
    public void atualizarNotaFiscal(NotaFiscalModel nf) {
        // Implementação para atualizar a NF no DAO
    }
}