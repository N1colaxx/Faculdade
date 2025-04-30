package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.PagarModel;
import model.FornecedorModel;

public class PagarController implements InterfaceController {

    private ArrayList<PagarModel> contasPagar = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        PagarModel pagar = new PagarModel();

        System.out.print("ID: ");
        pagar.setId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        pagar.setNumero(Integer.parseInt(scanner.nextLine()));

        System.out.print("Data de Emissão: ");
        pagar.setEmissao(scanner.nextLine());

        System.out.print("Data de Vencimento: ");
        pagar.setVencimento(scanner.nextLine());

        System.out.print("Data de Pagamento: ");
        pagar.setPagamento(scanner.nextLine());

        System.out.print("Valor: ");
        pagar.setValor(Double.parseDouble(scanner.nextLine()));

        System.out.print("Juros: ");
        pagar.setJuros(Double.parseDouble(scanner.nextLine()));

        System.out.print("Multa: ");
        pagar.setMulta(Double.parseDouble(scanner.nextLine()));

        System.out.print("Desconto: ");
        pagar.setDesconto(Double.parseDouble(scanner.nextLine()));

        System.out.print("Total: ");
        pagar.setTotal(Double.parseDouble(scanner.nextLine()));

        System.out.println("Dados do Fornecedor:");
        FornecedorModel fornecedor = new FornecedorModel();
        System.out.print("Nome do Fornecedor: ");
        fornecedor.setNome(scanner.nextLine());
        pagar.setFornecedor(fornecedor);

        System.out.print("Boleto: ");
        pagar.setBoleto(scanner.nextLine());

        contasPagar.add(pagar);
        System.out.println("Conta a pagar cadastrada com sucesso!");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID da conta para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PagarModel pagar : contasPagar) {
            if (pagar.getId() == id) {
                System.out.print("Novo número: ");
                pagar.setNumero(Integer.parseInt(scanner.nextLine()));

                System.out.print("Nova data de emissão: ");
                pagar.setEmissao(scanner.nextLine());

                System.out.print("Nova data de vencimento: ");
                pagar.setVencimento(scanner.nextLine());

                System.out.print("Nova data de pagamento: ");
                pagar.setPagamento(scanner.nextLine());

                System.out.print("Novo valor: ");
                pagar.setValor(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo juros: ");
                pagar.setJuros(Double.parseDouble(scanner.nextLine()));

                System.out.print("Nova multa: ");
                pagar.setMulta(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo desconto: ");
                pagar.setDesconto(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo total: ");
                pagar.setTotal(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo nome do fornecedor: ");
                FornecedorModel fornecedor = new FornecedorModel();
                fornecedor.setNome(scanner.nextLine());
                pagar.setFornecedor(fornecedor);

                System.out.print("Novo boleto: ");
                pagar.setBoleto(scanner.nextLine());

                System.out.println("Conta a pagar alterada com sucesso!");
                return;
            }
        }
        System.out.println("Conta com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < contasPagar.size()) {
            PagarModel pagar = contasPagar.get(pos);
            System.out.println("ID: " + pagar.getId());
            System.out.println("Número: " + pagar.getNumero());
            System.out.println("Emissão: " + pagar.getEmissao());
            System.out.println("Vencimento: " + pagar.getVencimento());
            System.out.println("Pagamento: " + pagar.getPagamento());
            System.out.println("Valor: R$" + pagar.getValor());
            System.out.println("Juros: R$" + pagar.getJuros());
            System.out.println("Multa: R$" + pagar.getMulta());
            System.out.println("Desconto: R$" + pagar.getDesconto());
            System.out.println("Total: R$" + pagar.getTotal());
            System.out.println("Fornecedor: " + pagar.getFornecedor().getNome());
            System.out.println("Boleto: " + pagar.getBoleto());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da conta para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (PagarModel pagar : contasPagar) {
            if (pagar.getId() == id) {
                contasPagar.remove(pagar);
                System.out.println("Conta a pagar removida com sucesso!");
                return;
            }
        }
        System.out.println("Conta com ID não encontrado.");
    }

    public ArrayList<PagarModel> getContasPagar() {
        return contasPagar;
    }
}
