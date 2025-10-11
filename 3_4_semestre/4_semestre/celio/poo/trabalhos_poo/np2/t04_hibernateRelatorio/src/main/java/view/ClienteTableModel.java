package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import model.ClienteModel;

public class ClienteTableModel extends AbstractTableModel {

    private ArrayList<ClienteModel> linhas;
    String[] colunas = {"Código", "Nome", "Limiete Credito"};

    public ClienteTableModel(ArrayList<ClienteModel> lista) {
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
        ClienteModel objModel = (ClienteModel) linhas.get(row);
            
        String nomePessoa = objModel.getPessoa_Cliente() != null ? objModel.getPessoa_Cliente().getPES_NOME() : "Sem nome";

        switch (col) {
            case 0:
                return objModel.getCLI_CODIGO();
            case 1:
                return nomePessoa;
            case 2:
                return objModel.getCLI_LIMITECRED();               
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um ClienteModel
    public void addLista(ArrayList<ClienteModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}

