package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import model.FornecedorModel;

public class FornecedorTableModel extends AbstractTableModel {

    private ArrayList<FornecedorModel> linhas;
    String[] colunas = {"Código Fornecedor", "Nome", "Contato"};

    public FornecedorTableModel(ArrayList<FornecedorModel> lista) {
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
        FornecedorModel objModel = (FornecedorModel) linhas.get(row);
            
        String nomePessoa = objModel.getPESSOA_FORNECEDOR()!= null ? objModel.getPESSOA_FORNECEDOR().getPES_NOME() : "Sem nome";
        String contato = objModel.getFOR_CONTATO() != null ? objModel.getFOR_CONTATO() : "Sem contato";
        switch (col) {
            case 0:
                return objModel.getFOR_CODIGO();
            case 1:
                return nomePessoa;
            case 2:
                return contato;               
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo FornecedorModel
    public void addLista(ArrayList<FornecedorModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}


