package util;


import model.VendaPagtoModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class VendaPagtoTableModel extends AbstractTableModel {
    private final String[] colunas = { "Cód FPG", "Forma", "Valor" };
    private final ArrayList<VendaPagtoModel> linhas;

    //  esse metodo é para: iniciar o model com a lista de pagamentos
    public VendaPagtoTableModel(ArrayList<VendaPagtoModel> p) {
        this.linhas = p;
    }

    //  esse metodo é para: total de linhas
    @Override public int getRowCount() { return linhas.size(); }

    //  esse metodo é para: total de colunas
    @Override public int getColumnCount() { return colunas.length; }

    //  esse metodo é para: nome das colunas
    @Override public String getColumnName(int c) { return colunas[c]; }

    //  esse metodo é para: retorno do valor exibido em célula
    @Override public Object getValueAt(int r, int c) {
        VendaPagtoModel v = linhas.get(r);
        switch(c){
            case 0: return v.getFPG_CODIGO();
            case 1: return v.getFPG_NOME();
            case 2: return v.getVDP_VALOR();
            default: return null;
        }
    }

    //  esse metodo é para: adicionar uma linha
    public void add(VendaPagtoModel v){
        linhas.add(v);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }

    //  esse metodo é para: atualizar linha
    public void set(int row, VendaPagtoModel v){
        linhas.set(row, v);
        fireTableRowsUpdated(row, row);
    }

    //  esse metodo é para: remover linha
    public void remove(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }

    //  esse metodo é para: obter lista crua para gravar
    public ArrayList<VendaPagtoModel> getLinhas(){ return linhas; }

    //  esse metodo é para: pegar item de uma linha
    public VendaPagtoModel get(int row){ return linhas.get(row); }
}
