package util;

import model.VendaItemModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/** Tabela de itens da venda: Venda, Cód Prod, Qtde, Preço, Total. */
public class VendaItemTableModel extends AbstractTableModel {
    private final String[] colunas = { "Venda", "Cód Prod", "Qtde", "Preço", "Total" };
    private final ArrayList<VendaItemModel> linhas;

    public VendaItemTableModel(ArrayList<VendaItemModel> itens){ this.linhas = itens; }
    @Override public int getRowCount(){ return linhas.size(); }
    @Override public int getColumnCount(){ return colunas.length; }
    @Override public String getColumnName(int c){ return colunas[c]; }

    @Override public Object getValueAt(int r, int c){
        VendaItemModel it = linhas.get(r);
        switch (c){
            case 0: return it.getVDA_CODIGO();
            case 1: return it.getPRO_CODIGO();
            case 2: return it.getVEP_QTDE();
            case 3: return it.getVEP_PRECO();
            case 4: return it.getVEP_TOTAL();
            default: return null;
        }
    }

    public void addItem(VendaItemModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public ArrayList<VendaItemModel> getLinhas(){ return linhas; }
    public VendaItemModel getItem(int row){ return linhas.get(row); }
}
