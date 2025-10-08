package util;


import model.FornecedorModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FornecedorTableModel extends AbstractTableModel {

    private ArrayList<FornecedorModel> linhas;
    private final String[] colunas;

    public FornecedorTableModel(ArrayList<FornecedorModel> lista, String[] colunas) {
        this.linhas = lista;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() { return linhas.size(); }

    @Override
    public int getColumnCount() { return colunas.length; }

    @Override
    public String getColumnName(int column) { return colunas[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FornecedorModel f = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0: return f.getPES_CODIGO();
            case 1: return f.getPES_NOME();
            case 2: return f.getPES_CPFCNPJ();
            case 3: return f.getFOR_CONTATO();
            case 4: return "S".equalsIgnoreCase(f.getPES_ATIVO()) ? "Sim" : "Não";
            default: return null;
        }
    }

    public void addLista(ArrayList<FornecedorModel> novos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(novos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount()-1);
    }
}
