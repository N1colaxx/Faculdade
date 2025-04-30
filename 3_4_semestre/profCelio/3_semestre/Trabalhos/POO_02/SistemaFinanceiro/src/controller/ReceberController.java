package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ClienteModel;
import model.ReceberModel;

public class ReceberController implements InterfaceController {

    private ArrayList<ReceberModel> recebimentos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void incluir() {
        ReceberModel receber = new ReceberModel();

        System.out.println("Cliente:");
        ClienteModel cliente = new ClienteModel();
        System.out.print("Nome do cliente: ");
        cliente.setNome(scanner.nextLine());
        receber.setCliente(cliente);

        System.out.print("Nota fiscal: ");
        receber.setNotaFiscal(scanner.nextLine());

        System.out.print("ID: ");
        receber.setId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        receber.setNumero(Integer.parseInt(scanner.nextLine()));

        System.out.print("Emissão: ");
        receber.setEmissao(scanner.nextLine());

        System.out.print("Vencimento: ");
        receber.setVencimento(scanner.nextLine());

        System.out.print("Pagamento: ");
        receber.setPagamento(scanner.nextLine());

        System.out.print("Valor: ");
        receber.setValor(Double.parseDouble(scanner.nextLine()));

        System.out.print("Juros: ");
        receber.setJuros(Double.parseDouble(scanner.nextLine()));

        System.out.print("Multa: ");
        receber.setMulta(Double.parseDouble(scanner.nextLine()));

        System.out.print("Desconto: ");
        receber.setDesconto(Double.parseDouble(scanner.nextLine()));

        System.out.print("Total: ");
        receber.setTotal(Double.parseDouble(scanner.nextLine()));

        recebimentos.add(receber);
        System.out.println("Recebimento cadastrado com sucesso!");
    }

    @Override
    public void alterar() {
        System.out.print("Informe o ID do recebimento para alterar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (ReceberModel receber : recebimentos) {
            if (receber.getId() == id) {

                System.out.println("Novo nome do cliente:");
                ClienteModel cliente = new ClienteModel();
                System.out.print("Nome: ");
                cliente.setNome(scanner.nextLine());
                receber.setCliente(cliente);

                System.out.print("Nova nota fiscal: ");
                receber.setNotaFiscal(scanner.nextLine());

                System.out.print("Novo número: ");
                receber.setNumero(Integer.parseInt(scanner.nextLine()));

                System.out.print("Nova emissão: ");
                receber.setEmissao(scanner.nextLine());

                System.out.print("Novo vencimento: ");
                receber.setVencimento(scanner.nextLine());

                System.out.print("Novo pagamento: ");
                receber.setPagamento(scanner.nextLine());

                System.out.print("Novo valor: ");
                receber.setValor(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo juros: ");
                receber.setJuros(Double.parseDouble(scanner.nextLine()));

                System.out.print("Nova multa: ");
                receber.setMulta(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo desconto: ");
                receber.setDesconto(Double.parseDouble(scanner.nextLine()));

                System.out.print("Novo total: ");
                receber.setTotal(Double.parseDouble(scanner.nextLine()));

                System.out.println("Recebimento alterado com sucesso!");
                return;
            }
        }
        System.out.println("Recebimento com ID não encontrado.");
    }

    @Override
    public void ConsultarPosicao() {
        System.out.print("Digite a posição que deseja consultar: ");
        int pos = Integer.parseInt(scanner.nextLine());

        if (pos >= 0 && pos < recebimentos.size()) {
            ReceberModel receber = recebimentos.get(pos);
            System.out.println("ID: " + receber.getId());
            System.out.println("Cliente: " + receber.getCliente().getNome());
            System.out.println("Nota Fiscal: " + receber.getNotaFiscal());
            System.out.println("Número: " + receber.getNumero());
            System.out.println("Emissão: " + receber.getEmissao());
            System.out.println("Vencimento: " + receber.getVencimento());
            System.out.println("Pagamento: " + receber.getPagamento());
            System.out.println("Valor: R$" + receber.getValor());
            System.out.println("Juros: R$" + receber.getJuros());
            System.out.println("Multa: R$" + receber.getMulta());
            System.out.println("Desconto: R$" + receber.getDesconto());
            System.out.println("Total: R$" + receber.getTotal());
        } else {
            System.out.println("Posição inválida!");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID do recebimento para excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (ReceberModel receber : recebimentos) {
            if (receber.getId() == id) {
                recebimentos.remove(receber);
                System.out.println("Recebimento removido com sucesso!");
                return;
            }
        }
        System.out.println("Recebimento com ID não encontrado.");
    }

    public ArrayList<ReceberModel> getRecebimentos() {
        return recebimentos;
    }
}
