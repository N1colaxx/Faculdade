package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.UsuarioModel;

public class UsuarioTableModel extends AbstractTableModel {

    private ArrayList<UsuarioModel> linhas;
    String[] colunas = {"Código", "Login", "Nome", "Ativo"};

    public UsuarioTableModel(ArrayList<UsuarioModel> lista) {
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
        UsuarioModel objModel = (UsuarioModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getUSU_CODIGO();
            case 1:
                return objModel.getUSU_NOME();
            case 2:
                return objModel.getUSU_LOGIN();
            case 3:
                return objModel.getUSU_ATIVO() == 1 ? "Sim" : "Não";               
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de UsuarioModel
    public void addLista(ArrayList<UsuarioModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}