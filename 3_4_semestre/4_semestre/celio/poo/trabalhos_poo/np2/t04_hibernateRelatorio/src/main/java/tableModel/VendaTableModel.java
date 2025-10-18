package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.VendaModel;

/**
 * Tabela da Venda
 */

public class VendaTableModel extends AbstractTableModel {

    private ArrayList<VendaModel> linhas;
    String[] colunas = {"Código Venda", "Codigo Usuario", "Nome Cliente", "Data Venda", "Valor Total"};

    public VendaTableModel(ArrayList<VendaModel> lista) {
        linhas = lista;
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
        VendaModel objModel = (VendaModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getVda_codigo();
            case 1:
                return objModel.getUsu_venda().getUSU_CODIGO();
            case 2:
                return objModel.getCli_venda().getPessoa_Cliente().getPES_NOME();
            case 3:
                return objModel.getVda_data();
            case 4:
                return objModel.getVda_total();
            default:
                return null;
        }
        // String[] colunas = {"Código Venda", "Codigo Usuario", "Nome Cliente", "Data Venda", "Valor Total"};

    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de Venda
    public void addLista(ArrayList<VendaModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
