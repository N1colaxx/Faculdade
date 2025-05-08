package dao;

import model.NotaFiscalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotaFiscalDAO {

    private static NotaFiscalDAO instancia;
    private final List<NotaFiscalModel> notas = new ArrayList<>();
    private final List<NotaFiscalModel> notasFake = new ArrayList<>();

    private NotaFiscalDAO() {
        // Construtor privado para garantir o padrão Singleton
    }

    public static NotaFiscalDAO getInstancia() {
        if (instancia == null) {
            instancia = new NotaFiscalDAO();
        }
        return instancia;
    }

    public void adicionar(NotaFiscalModel nota) {
        notas.add(nota);
    }

    public void adicionarFake(NotaFiscalModel notafake) {
        notasFake.add(notafake);
    }

    
    public Optional<NotaFiscalModel> buscarPorNumero(int numero) {
        return notas.stream()
                .filter(nf -> nf.getNumero() == numero)
                .findFirst();
    }

    public boolean removerPorNumero(int numero) {
        return notas.removeIf(nf -> nf.getNumero() == numero);
    }

    public List<NotaFiscalModel> buscarPorRazaoSocial(String razao) {
        return notas.stream()
                .filter(nf -> nf.getDestinatario().getRazaoSocial().equalsIgnoreCase(razao))
                .collect(Collectors.toList());
    }

    public List<NotaFiscalModel> buscarPorCpfCnpj(String cpfCnpj) {
        return notas.stream()
                .filter(
                        nf -> nf.getDestinatario().getCpfCnpj().equalsIgnoreCase(cpfCnpj)
                        || nf.getEmitente().getCpfCnpj().equalsIgnoreCase(cpfCnpj)
                        || nf.getTransportadora().getCnpj().equalsIgnoreCase(cpfCnpj)
                ).collect(Collectors.toList());
    }

    public List<NotaFiscalModel> buscarPorValorTotal(double valorTotal) {
        return notas.stream()
                .filter(nf -> nf.getValorTotalNf() == valorTotal)
                .collect(Collectors.toList());
    }

    public List<NotaFiscalModel> listarPorIntervalo(int inicio, int fim) {
        return notas.stream()
                .filter(nf -> nf.getNumero() >= inicio && nf.getNumero() <= fim)
                .collect(Collectors.toList());
    }

    public List<NotaFiscalModel> listarTodas() {
        return new ArrayList<>(notas);
    }

    public boolean atualizar(NotaFiscalModel novaNF) {
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getNumero() == novaNF.getNumero()) {
                notas.set(i, novaNF);
                return true;
            }
        }
        return false;
    }

    public void adicionarFaker(NotaFiscalModel nf) {
        notasFake.add(nf);
        notas.add(nf);

    }
}
