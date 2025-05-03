package controller;

import java.util.ArrayList;
import java.util.Collections;
import model.FluxoCaixaModel;
import model.PagarModel;
import model.ReceberModel;
import model.ClienteModel;
import model.FornecedorModel;

public class FluxoCaixaController {
    
    private static FluxoCaixaController instancia;
    
    private FluxoCaixaController() {}
    
    public static FluxoCaixaController getInstancia() {
        if (instancia == null) {
            instancia = new FluxoCaixaController();
        }
        return instancia;
    }
    
    public void mostrarFluxoCaixa() {
        PagarController pagarController = PagarController.getInstancia();
        ReceberController receberController = ReceberController.getInstancia();
        ArrayList<PagarModel> contasPagar = pagarController.getContasPagar();
        ArrayList<ReceberModel> contasReceber = receberController.getRecebimentos();
        
        if (contasPagar.isEmpty() && contasReceber.isEmpty()) {
            System.out.println("❌ Não há dados de contas a pagar ou receber cadastrados.");
            return;
        }
        
        exibirFluxoCaixaCompleto(contasPagar, contasReceber);
    }
    
    private void exibirFluxoCaixaCompleto(ArrayList<PagarModel> contasPagar, ArrayList<ReceberModel> contasReceber) {
        ArrayList<FluxoCaixaModel> fluxoCaixa = new ArrayList<>();
        
        // Adiciona contas a pagar com informações detalhadas do fornecedor
        for (PagarModel pagar : contasPagar) {
            FluxoCaixaModel item = new FluxoCaixaModel(pagar);
            
            // Adiciona mais informações do fornecedor na descrição
            if (pagar.getFornecedor() != null) {
                FornecedorModel fornecedor = pagar.getFornecedor();
                item.setDescricaoDetalhada("Pag a " + fornecedor.getNome());
            }
            
            fluxoCaixa.add(item);
        }
        
        // Adiciona contas a receber com informações detalhadas do cliente
        for (ReceberModel receber : contasReceber) {
            FluxoCaixaModel item = new FluxoCaixaModel(receber);
            
            // Adiciona mais informações do cliente na descrição
            if (receber.getCliente() != null) {
                ClienteModel cliente = receber.getCliente();
                item.setDescricaoDetalhada("Rec de " + cliente.getNome());
            }
            
            fluxoCaixa.add(item);
        }
        
        // Ordena por data
        Collections.sort(fluxoCaixa);
        
        // Exibe a tabela
        exibirTabela(fluxoCaixa);
    }
    
private void exibirTabela(ArrayList<FluxoCaixaModel> fluxoCaixa) {
    if (fluxoCaixa.isEmpty()) {
        System.out.println("❌ Nenhum registro encontrado.");
        return;
    }

    System.out.println("\n" + "=".repeat(110));
    System.out.printf("%-12s | %-12s | %-30s | %10s | %-15s | %-5s\n",
            "DATA", "TIPO", "DESCRIÇÃO", "VALOR", "ORIGEM", "CNPJ");
    System.out.println("-".repeat(110));

    double saldoFinal = 0.00;

    for (FluxoCaixaModel item : fluxoCaixa) {
        String descricao = item.getDescricaoDetalhada() != null ? 
                         item.getDescricaoDetalhada() : item.getDescricao();

        // Evitar divisão da descrição por vírgula
        if (!descricao.contains(",")) {
            // Exibe a descrição completa sem divisão
            System.out.printf("%-12s | %-12s | %-50s | %10.2f | %-15s | %-14s\n",
                    item.getDataFormatada(),
                    item.getTipo(),
                    encurtarDescricao(descricao, 50),
                    item.getValor(),
                    item.getOrigem(),
                    item.getCnpjOrigem());  // Alteração aqui: substituímos ID por CNPJ
        } else {
            // Exibe a descrição completa
            System.out.printf("%-12s | %-12s | %-50s | %10.2f | %-15s | %-14s\n",
                    item.getDataFormatada(),
                    item.getTipo(),
                    descricao,  // Mantemos a descrição inteira
                    item.getValor(),
                    item.getOrigem(),
                    item.getCnpjOrigem());  // Alteração aqui: substituímos ID por CNPJ
        }

        saldoFinal += item.getValor();
    }

    System.out.println("-".repeat(110));
    System.out.printf("%-78s | %10.2f | %-15s | %5s\n",
            "SALDO FINAL", saldoFinal, "", "");
    System.out.println("=".repeat(110) + "\n");
}



    private String encurtarDescricao(String descricao, int limite) {
        if (descricao == null) return "";
        if (descricao.length() <= limite) return descricao;
        return descricao.substring(0, limite - 3) + "...";
    }
}