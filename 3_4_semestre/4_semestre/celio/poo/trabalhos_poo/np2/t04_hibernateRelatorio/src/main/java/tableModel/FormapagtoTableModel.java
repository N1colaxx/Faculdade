package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.FormapagtoModel;

public class FormapagtoTableModel extends AbstractTableModel {

    private ArrayList<FormapagtoModel> linhas;
    String[] colunas = {"Código", "Nome", "Ativo"};

    public FormapagtoTableModel(ArrayList<FormapagtoModel> lista) {
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
        FormapagtoModel objModel = (FormapagtoModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getFPG_CODIGO();
            case 1:
                return objModel.getFPG_NOME();
            case 2:
                return objModel.getFPG_ATIVO() == "1" ? "Sim" : "Não";               
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List FormapagtoModel
    public void addLista(ArrayList<FormapagtoModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
