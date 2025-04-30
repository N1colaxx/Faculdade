package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.FinanceiroModel;

public class FinanceiroController implements InterfaceController {

    private ArrayList<FinanceiroModel> financeiroList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID do financeiro para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FinanceiroModel financeiro : financeiroList) {
            if (financeiro.getId() == id) {
                System.out.print("Novo número: ");
                financeiro.setNumero(Integer.parseInt(scanner.nextLine()));

                System.out.print("Nova emissão (dd/MM/yyyy): ");
                financeiro.setEmissao(scanner.nextLine());

                System.out.print("Novo vencimento (dd/MM/yyyy): ");
                financeiro.setVencimento(scanner.nextLine());

                System.out.print("Novo pagamento (dd/MM/yyyy): ");
                financeiro.setPagamento(scanner.nextLine());

                System.out.print("Novo valor: ");
                financeiro.setValor(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novos juros: ");
                financeiro.setJuros(Double.parseDouble(scanner.nextLine()));

                System.out.print("Nova multa: ");
                financeiro.setMulta(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo desconto: ");
                financeiro.setDesconto(Double.parseDouble(scanner.nextLine()));

                // Recalcular o total
                double total = financeiro.getValor() + financeiro.getJuros() - financeiro.getDesconto() + financeiro.getMulta();
                financeiro.setTotal(total);

                System.out.println("Financeiro alterado com sucesso!");
                return;
            }
        }
        System.out.println("Financeiro com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < financeiroList.size()) {
            FinanceiroModel financeiro = financeiroList.get(pos);
            System.out.println("ID: " + financeiro.getId());
            System.out.println("Número: " + financeiro.getNumero());
            System.out.println("Emissão: " + financeiro.getEmissao());
            System.out.println("Vencimento: " + financeiro.getVencimento());
            System.out.println("Pagamento: " + financeiro.getPagamento());
            System.out.println("Valor: R$" + financeiro.getValor());
            System.out.println("Juros: R$" + financeiro.getJuros());
            System.out.println("Multa: R$" + financeiro.getMulta());
            System.out.println("Desconto: R$" + financeiro.getDesconto());
            System.out.println("Total: R$" + financeiro.getTotal());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID do financeiro para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (FinanceiroModel financeiro : financeiroList) {
            if (financeiro.getId() == id) {
                financeiroList.remove(financeiro);
                System.out.println("Financeiro removido com sucesso!");
                return;
            }
        }
        System.out.println("Financeiro com ID não encontrado.");
    }

    // Métodos auxiliares
    public void adicionarFinanceiro(FinanceiroModel financeiro) {
        financeiroList.add(financeiro);
    }

    public ArrayList<FinanceiroModel> getFinanceiroList() {
        return financeiroList;
    }
}
