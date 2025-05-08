package dao;

import model.NotaFiscalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NotaFiscalDAO {

    private static NotaFiscalDAO instancia;
    private final List<NotaFiscalModel> notas = new ArrayList<>();

    private NotaFiscalDAO() {
        // Construtor privado para garantir o padrão Singleton
    }

    public static NotaFiscalDAO getInstancia() {
        if (instancia == null) {
            instancia = new NotaFiscalDAO();
        }
        return instancia;
    }

    public void adicionarNF(NotaFiscalModel nota) {
        notas.add(nota);
    }

    public void adicionarNfFake(NotaFiscalModel notafake) {
        notas.add(notafake);
    }

    
    
    
    
//    Menu
        public boolean removerPorNumero(int numero) {
        return notas.removeIf(nf -> nf.getNumero() == numero);
    }
        
//    Sub Menu Alterações
    public boolean atualizar(NotaFiscalModel novaNF) {
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i).getNumero() == novaNF.getNumero()) {
                notas.set(i, novaNF);
                return true;
            }
        }
        return false;
    }


//    Sub Menu Consultas
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

    public List<NotaFiscalModel> buscarPorCnpj(String Cnpj) {
        return notas.stream()
                .filter(
                        nf -> nf.getDestinatario().getCnpj().equalsIgnoreCase(Cnpj)
                        || nf.getEmitente().getCnpj().equalsIgnoreCase(Cnpj)
                        || nf.getRemetente().getCnpj().equalsIgnoreCase(Cnpj)
                        || nf.getTransportadora().getCnpj().equalsIgnoreCase(Cnpj)
                ).collect(Collectors.toList());
    }
    
    public List<NotaFiscalModel> buscarPorCpf(String Cpf) {
        return notas.stream()
                .filter(
                        nf -> nf.getDestinatario().getCpf().equalsIgnoreCase(Cpf)
                        || nf.getEmitente().getCpf().equalsIgnoreCase(Cpf)
                        || nf.getRemetente().getCpf().equalsIgnoreCase(Cpf)
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


}
