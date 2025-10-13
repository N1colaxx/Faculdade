package view;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.VendapagtoModel;

public class VendapagtoTableModel extends AbstractTableModel {
    String[] colunas = {"Código Venda", "Forma de Pagamento", "Valor"};
    private ArrayList<VendapagtoModel>linhas;

    public VendapagtoTableModel(ArrayList<VendapagtoModel>lista) {
        this.linhas = lista;
    }

    //Retorna a quantidade de colunas do modelo, que no caso será fixa
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //Retorna a quantidade de linhas atual do objeto, que no caso é o tamnho da lista
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    //Retorna o nome da coluna, recebendo seu índice
    @Override
    public String getColumnName(int indiceColuna) {
        return colunas[indiceColuna];
    }

    @Override
    public Object getValueAt(int row, int col) {
        VendapagtoModel objModel = (VendapagtoModel)linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getVenda_Vendapagto().getVda_codigo();
            case 1:
                return objModel.getFormapagto_Vendapagto().getFPG_NOME();
            case 2:
                return objModel.getVdp_valor();

            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de Venda
    public void addLista(ArrayList<VendapagtoModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    
    
     public void add(VendapagtoModel v){
        linhas.add(v);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void set(int row, VendapagtoModel v){
        linhas.set(row, v);
        fireTableRowsUpdated(row, row);
    }
    public void remove(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public ArrayList<VendapagtoModel> getLinhas(){ return linhas; }
    public VendapagtoModel get(int row){ return linhas.get(row); }
}

