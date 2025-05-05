package dao;

import model.NotaFiscalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotaFiscalDAO {

    private final List<NotaFiscalModel> notas = new ArrayList<>();

    public void adicionar(NotaFiscalModel nota) {
        notas.add(nota);
    }

    public boolean removerPorNumero(int numero) {
        return notas.removeIf(nf -> nf.getNumero() == numero);
    }

    public Optional<NotaFiscalModel> buscarPorNumero(int numero) {
        return notas.stream()
                .filter(nf -> nf.getNumero() == numero)
                .findFirst();
    }

    public List<NotaFiscalModel> buscarPorRazaoSocial(String razao) {
        return notas.stream()
                .filter(nf -> nf.getDestinatario().getRazaoSocial().equalsIgnoreCase(razao))
                .collect(Collectors.toList());
    }

    public List<NotaFiscalModel> buscarPorCpfCnpj(String cpfCnpj) {
        return notas.stream()
                .filter(nf -> nf.getDestinatario().getCpfCnpj().equalsIgnoreCase(cpfCnpj))
                .collect(Collectors.toList());
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
    
    
    public void adicionarFaker(NotaFiscalModel nf){
        notas.add(nf);
    }
}
