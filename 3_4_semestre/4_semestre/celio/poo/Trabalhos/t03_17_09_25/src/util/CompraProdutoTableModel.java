package util;


import model.CompraProdutoModel;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * TableModel dos ITENS da COMPRA (usa CompraProdutoModel existente).
 * Colunas: cpr_codigo, pro_codigo, qtde, preco, total
 */
public class CompraProdutoTableModel extends AbstractTableModel {
    private final String[] colunas = { "Compra", "Cód Prod", "Qtde", "Preço", "Total" };
    private final ArrayList<CompraProdutoModel> linhas;

    public CompraProdutoTableModel(ArrayList<CompraProdutoModel> itens){
        this.linhas = itens;
    }

    @Override public int getRowCount(){ return linhas.size(); }
    @Override public int getColumnCount(){ return colunas.length; }
    @Override public String getColumnName(int c){ return colunas[c]; }

    @Override public Object getValueAt(int r, int c){
        CompraProdutoModel  it = linhas.get(r);
        switch (c){
            case 0: return it.getCPR_CODIGO();
            case 1: return it.getPRO_CODIGO();
            case 2: return it.getCPR_QTDE();
            case 3: return it.getCPR_PRECO();
            case 4: return it.getCPR_TOTAL();
            default: return null;
        }
    }

    public void addItem(CompraProdutoModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }

    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public ArrayList<CompraProdutoModel> getLinhas(){ return linhas; }

    public CompraProdutoModel getItem(int row){ return linhas.get(row); }

    /** util p/ somar total facilmente */
    public BigDecimal somaTotais(){
        BigDecimal s = BigDecimal.ZERO;
        for (CompraProdutoModel it : linhas){
            BigDecimal v = it.getCPR_TOTAL();
            if (v != null) s = s.add(v);
        }
        return s;
    }
}
