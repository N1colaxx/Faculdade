package util;

import model.VendaProdutoModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/** Tabela de itens da venda: Venda, Cód Prod, Qtde, Preço, Total. */
public class VendaProdutoTableModel extends AbstractTableModel {
    private final String[] colunas = { "Venda", "Cód Prod", "Qtde", "Preço", "Total" };
    private final ArrayList<VendaProdutoModel> linhas;

    public VendaProdutoTableModel(ArrayList<VendaProdutoModel> itens){ this.linhas = itens; }
    @Override public int getRowCount(){ return linhas.size(); }
    @Override public int getColumnCount(){ return colunas.length; }
    @Override public String getColumnName(int c){ return colunas[c]; }

    @Override public Object getValueAt(int r, int c){
        VendaProdutoModel it = linhas.get(r);
        switch (c){
            case 0: return it.getVDA_CODIGO();
            case 1: return it.getPRO_CODIGO();
            case 2: return it.getVEP_QTDE();
            case 3: return it.getVEP_PRECO();
            case 4: return it.getVEP_TOTAL();
            default: return null;
        }
    }

    public void addItem(VendaProdutoModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public ArrayList<VendaProdutoModel> getLinhas(){ return linhas; }
    public VendaProdutoModel getItem(int row){ return linhas.get(row); }
}
