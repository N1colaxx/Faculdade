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
                item.setDescricaoDetalhada("Pag a " + fornecedor.getNome() + 
                    " (CNPJ: " + fornecedor.getCnpj() + 
                    ", ID: " + fornecedor.getId() + ")");
            }
            
            fluxoCaixa.add(item);
        }
        
        // Adiciona contas a receber com informações detalhadas do cliente
        for (ReceberModel receber : contasReceber) {
            FluxoCaixaModel item = new FluxoCaixaModel(receber);
            
            // Adiciona mais informações do cliente na descrição
            if (receber.getCliente() != null) {
                ClienteModel cliente = receber.getCliente();
                item.setDescricaoDetalhada("Rec de " + cliente.getNome() + 
                    " (CNPJ: " + cliente.getCnpj()+ 
                    ", ID: " + cliente.getId() + ")");
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
        System.out.printf("%-12s | %-12s | %-50s | %10s | %-15s | %5s\n",
                "DATA", "TIPO", "DESCRIÇÃO DETALHADA", "VALOR", "ORIGEM", "ID");
        System.out.println("-".repeat(110));
        
        double saldoFinal = 0.00;
        
        for (FluxoCaixaModel item : fluxoCaixa) {
            // Verifica se existe descrição detalhada, senão usa a descrição normal
            String descricaoExibir = (item.getDescricaoDetalhada() != null) ? 
                    item.getDescricaoDetalhada() : item.getDescricao();
            
            System.out.printf("%-12s | %-12s | %-50s | %10.2f | %-15s | %5d\n",
                    item.getData(),
                    item.getTipo(),
                    encurtarDescricao(descricaoExibir, 50),
                    item.getValor(),
                    item.getOrigem(),
                    item.getIdOrigem());
                    
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