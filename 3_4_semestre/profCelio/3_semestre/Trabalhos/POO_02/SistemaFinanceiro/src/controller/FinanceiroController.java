package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import model.FinanceiroModel;

public class FinanceiroController implements InterfaceFinanceiro {

    private ArrayList<FinanceiroModel> financeiroList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void Incluir() {
        System.out.println("");
    }

    @Override
    public void AlterarPorNumero() {
        if (financeiroList.isEmpty()) {
            System.out.println("❌ ERRO: Lista VAZIA !!");
        } else {
            int num = lerNumero();
            for (FinanceiroModel financeiro : financeiroList) {
                if (financeiro.getNumero() == num) {
                    System.out.println("\n--- ALTERAR DADOS FINANCEIROS ---");

                    System.out.print("Novo numero da conta: ");
                    financeiro.setNumero(Integer.parseInt(scanner.nextLine()));

                    financeiro.setEmissao(lerDataValida("Nova data de emissao (dd/MM/yyyy): "));
                    financeiro.setVencimento(lerDataValida("Nova data de vencimento (dd/MM/yyyy): "));
                    financeiro.setPagamento(lerDataValida("Nova data de pagamento (dd/MM/yyyy): "));

                    System.out.print("Novo valor: ");
                    financeiro.setValor(Double.parseDouble(scanner.nextLine()));

                    System.out.print("Novo valor de juros: ");
                    financeiro.setJuros(Double.parseDouble(scanner.nextLine()));

                    System.out.print("Novo valor de multa: ");
                    financeiro.setMulta(Double.parseDouble(scanner.nextLine()));

                    System.out.print("Novo valor de desconto: ");
                    financeiro.setDesconto(Double.parseDouble(scanner.nextLine()));

                    System.out.print("Novo total: ");
                    financeiro.setTotal(Double.parseDouble(scanner.nextLine()));

                    System.out.println("✅ Dados alterados com sucesso!");
                    return;
                }
            }
            System.out.println("❌ Nenhuma conta encontrada com esse número.");
        }
    }

    // Método para ler e validar datas
    private String lerDataValida(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String dataStr = scanner.nextLine();
                LocalDate.parse(dataStr, DATE_FORMATTER); // Valida o formato
                return dataStr;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato de data inválido. Use dd/MM/yyyy (ex: 31/12/2023)");
            }
        }
    }
    
    @Override
    public void ConsultarPorNumero() {
        if (financeiroList.isEmpty()) {
            System.out.println("❌ Lista VAZIA.");
            return;
        }

        int num = lerNumero();
        for (FinanceiroModel financeiro : financeiroList) {
            if (financeiro.getNumero() == num) {
                System.out.println("\n📄 Resultado da consulta:");
                System.out.println(financeiro);
                return;
            }
        }
        System.out.println("❌ Nenhum financeiro encontrado com esse número.");
    }

    @Override
    public void ConsultarPorValor() {
        if (financeiroList.isEmpty()) {
            System.out.println("❌ Lista VAZIA.");
            return;
        }

        try {
            System.out.print("Informe o valor a consultar: ");
            double valor = Double.parseDouble(scanner.nextLine());

            boolean encontrado = false;
            for (FinanceiroModel financeiro : financeiroList) {
                if (financeiro.getValor() == valor) {
                    System.out.println("\n📄 Resultado da consulta:");
                    System.out.println(financeiro);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("❌ Nenhum financeiro com esse valor.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Valor inválido.");
        }
    }

    @Override
    public void ExcluirPorID() {
        System.out.print("Informe o ID do financeiro para excluir: ");
        int id = lerIdValido();
        for (FinanceiroModel financeiro : financeiroList) {
            if (financeiro.getId() == id) {
                financeiroList.remove(financeiro);
                System.out.println("✅ Financeiro removido com sucesso!");
                return;
            }
        }
        System.out.println("❌ Financeiro com ID não encontrado.");
    }

    
    // ✅ Métodos auxiliares
    public int lerNumero() {
        while (true) {
            try {
                System.out.print("Informe o Numero da Conta a Receber: ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num <= 0) {
                    System.out.println("❌ O NUMERO deve ser maior que zero.");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números inteiros.");
            }
        }
    }

    public int lerIdValido() {
        while (true) {
            try {
                System.out.print("Informe o ID do cliente: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ O ID deve ser maior que zero.");
                } else {
                    return id;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números inteiros.");
            }
        }
    }

    public void adicionarFinanceiro(FinanceiroModel financeiro) {
        financeiroList.add(financeiro);
    }

    public ArrayList<FinanceiroModel> getFinanceiroList() {
        return financeiroList;
    }
}
