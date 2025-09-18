package util;


import model.FormaPagtoModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FormaPagamentoTableModel extends AbstractTableModel {

    private ArrayList<FormaPagtoModel> linhas;
    private final String[] colunas;

    public FormaPagamentoTableModel(ArrayList<FormaPagtoModel> lista, String[] colunas) {
        this.colunas = colunas;
        this.linhas  = lista;
    }

    @Override
    public int getColumnCount() { return colunas.length; }

    @Override
    public int getRowCount() { return linhas.size(); }

    @Override
    public String getColumnName(int indiceColuna) { return colunas[indiceColuna]; }

    @Override
    public Object getValueAt(int row, int col) {
        FormaPagtoModel f = linhas.get(row);
        switch (col) {
            case 0: return f.getFPG_CODIGO();
            case 1: return f.getFPG_NOME();
            case 2: return (f.getFPG_ATIVO() != null && f.getFPG_ATIVO().trim().equalsIgnoreCase("S")) ? "Sim" : "Não";
            default: return null;
        }
    }

    public void addLista(ArrayList<FormaPagtoModel> novas) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(novas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
