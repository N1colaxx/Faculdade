package util;


import model.VendaItemModel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class VendaItemTableModel extends AbstractTableModel {
    private final String[] colunas = {
        "Cód Prod", "Nome", "Und", "Qtde", "Preço", "Desc", "Total"
    };
    private final ArrayList<VendaItemModel> linhas;

    //  esse metodo é para: iniciar o model com a lista de itens
    public VendaItemTableModel(ArrayList<VendaItemModel> itens) {
        this.linhas = itens;
    }

    //  esse metodo é para: retornar quantas linhas a tabela possui
    @Override public int getRowCount() { return linhas.size(); }

    //  esse metodo é para: retornar quantas colunas a tabela possui
    @Override public int getColumnCount() { return colunas.length; }

    //  esse metodo é para: retornar o nome da coluna
    @Override public String getColumnName(int c) { return colunas[c]; }

    //  esse metodo é para: retornar o valor exibido em cada célula
    @Override public Object getValueAt(int r, int c) {
        VendaItemModel it = linhas.get(r);
        switch(c){
            case 0: return it.getPRO_CODIGO();
            case 1: return it.getPRO_NOME();
            case 2: return it.getPRO_UNIDADE();
            case 3: return it.getVEP_QTDE();
            case 4: return it.getVEP_PRECO();
            case 5: return it.getVEP_DESCONTO();
            case 6: return it.getVEP_TOTAL();
            default: return null;
        }
    }

    //  esse metodo é para: adicionar um item na tabela e notificar a view
    public void addItem(VendaItemModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }

    //  esse metodo é para: atualizar um item em determinada linha
    public void setItem(int row, VendaItemModel it){
        linhas.set(row, it);
        fireTableRowsUpdated(row, row);
    }

    //  esse metodo é para: remover um item por linha
    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }

    //  esse metodo é para: retornar a lista crua dos itens (para salvar)
    public ArrayList<VendaItemModel> getLinhas(){ return linhas; }

    //  esse metodo é para: pegar o item de uma linha (útil para editor)
    public VendaItemModel getItem(int row){ return linhas.get(row); }
}
