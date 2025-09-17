package util;

import model.VendaPagtoModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/** Tabela de pagamentos: Venda, Forma, Valor. */
public class VendaPagtoTableModel extends AbstractTableModel {
    private final String[] colunas = { "Venda", "Forma", "Valor" };
    private final ArrayList<VendaPagtoModel> linhas;

    public VendaPagtoTableModel(ArrayList<VendaPagtoModel> p){ this.linhas = p; }
    @Override public int getRowCount(){ return linhas.size(); }
    @Override public int getColumnCount(){ return colunas.length; }
    @Override public String getColumnName(int c){ return colunas[c]; }

    @Override public Object getValueAt(int r, int c){
        VendaPagtoModel v = linhas.get(r);
        switch (c){
            case 0: return v.getVDA_CODIGO();
            case 1: return v.getFPG_NOME();
            case 2: return v.getVDP_VALOR();
            default: return null;
        }
    }

    public void add(VendaPagtoModel v){
        linhas.add(v);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void set(int row, VendaPagtoModel v){
        linhas.set(row, v);
        fireTableRowsUpdated(row, row);
    }
    public void remove(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public ArrayList<VendaPagtoModel> getLinhas(){ return linhas; }
    public VendaPagtoModel get(int row){ return linhas.get(row); }
}
