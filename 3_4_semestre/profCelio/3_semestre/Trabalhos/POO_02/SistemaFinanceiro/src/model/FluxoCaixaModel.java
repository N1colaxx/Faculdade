package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Modelo para representar um item no fluxo de caixa.
 * Cada item representa uma entrada (receita) ou saída (despesa).
 */
public class FluxoCaixaModel implements Comparable<FluxoCaixaModel> {
    
    private LocalDate data;            // Data da movimentação
    private String tipo;               // Tipo: "Receita" ou "Despesa"
    private String descricao;          // Descrição básica da movimentação
    private String descricaoDetalhada; // Descrição que inclui IDs e outros detalhes
    private double valor;              // Valor: positivo para receitas, negativo para despesas
    private String origem;             // Origem: "Conta a Pagar" ou "Conta a Receber"
    private String cnpjOrigem;              // cnpj da conta de origem
    
    // Construtor padrão
    public FluxoCaixaModel() {}
    
    // Construtor completo
    public FluxoCaixaModel(LocalDate data, String tipo, String descricao, double valor, 
                         String origem, int idOrigem) {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.origem = origem;
        this.cnpjOrigem = cnpjOrigem;
    }
    
    // Construtor baseado em PagarModel (despesa)
    public FluxoCaixaModel(PagarModel pagar) {
        try {
            this.data = parseData(pagar.getPagamento());
        } catch (DateTimeParseException e) {
            // Se a data de pagamento não for válida, usar a data de vencimento
            try {
                this.data = parseData(pagar.getVencimento());
            } catch (DateTimeParseException ex) {
                this.data = LocalDate.now(); // Falha segura
            }
        }
        
        this.tipo = "Credito";
        
        // Guarda informações detalhadas sobre o fornecedor
        if (pagar.getFornecedor() != null) {
            this.descricao = "Pagamento a " + pagar.getFornecedor().getNome();
            // A descrição detalhada será definida no controller
        } else {
            this.descricao = "Pagamento de conta #" + pagar.getNumero();
        }
        
        this.valor = -pagar.getTotal(); // Valor negativo para despesas
        this.origem = "Conta a Pagar";
        this.cnpjOrigem = pagar.getCnpj();
    }
    
    // Construtor baseado em ReceberModel (receita)
    public FluxoCaixaModel(ReceberModel receber) {
        try {
            this.data = parseData(receber.getPagamento());
        } catch (DateTimeParseException e) {
            // Se a data de pagamento não for válida, usar a data de vencimento
            try {
                this.data = parseData(receber.getVencimento());
            } catch (DateTimeParseException ex) {
                this.data = LocalDate.now(); // Falha segura
            }
        }
        
        this.tipo = "Debito";
        
        // Guarda informações detalhadas sobre o cliente
        if (receber.getCliente() != null) {
            this.descricao = "Recebimento de " + receber.getCliente().getNome();
            // A descrição detalhada será definida no controller
        } else {
            this.descricao = "Recebimento de conta #" + receber.getNumero();
        }
        
        this.valor = receber.getTotal(); // Valor positivo para receitas
        this.origem = "Conta a Receber";
        this.cnpjOrigem= receber.getCnpj();
    }
    
    // Método para converter string de data em LocalDate
    private LocalDate parseData(String dataStr) {
        // Vários formatos possíveis para flexibilidade
        String[] formatos = {
            "dd/MM/yyyy", "yyyy-MM-dd", "dd-MM-yyyy", "dd.MM.yyyy",
            "d/M/yyyy", "yyyy/MM/dd"
        };
        
        for (String formato : formatos) {
            try {
                return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern(formato));
            } catch (DateTimeParseException e) {
                // Tentar próximo formato
            }
        }
        
        // Se nenhum formato funcionar, lançar exceção
        throw new DateTimeParseException("Formato de data inválido: " + dataStr, dataStr, 0);
    }
    
    // Override de toString para exibição formatada
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data != null ? data.format(formatter) : "Data inválida";
        
        return String.format("%-12s | %-12s | %-30s | %10.2f | %-15s | %5d",
                dataFormatada, tipo, descricao, valor, origem, cnpjOrigem);
    }
    
    // Implementação do método compareTo para ordenação por data
    @Override
    public int compareTo(FluxoCaixaModel outro) {
        return this.data.compareTo(outro.data);
    }
    
    // Getters e Setters
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }
    
    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getOrigem() {
        return origem;
    }
    
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public String getCnpjOrigem() {
        return cnpjOrigem;
    }
    
    public void setCnpjOrigem(String cnpjOrigem) {
        this.cnpjOrigem = cnpjOrigem;
    }
    
    // Adicione este método ao FluxoCaixaModel
    public String getDataFormatada() {
        return this.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}