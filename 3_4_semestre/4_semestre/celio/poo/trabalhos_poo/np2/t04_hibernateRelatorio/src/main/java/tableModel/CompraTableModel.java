package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.CompraModel;

/**
 * Tabela da Venda
 */

public class CompraTableModel extends AbstractTableModel {

    private ArrayList<CompraModel> linhas;
    String[] colunas = {"Codigo CPR", "Codigo Usuario", "Codigo Fornecedor", "Data Emissao", "Valor", "Desconto Compra", "Total", "Data Entrada", "Obs"};

    public CompraTableModel(ArrayList<CompraModel> lista) {
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
        CompraModel objModel = (CompraModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getCpr_codigo();
            case 1:
                return objModel.getUsuario_compra().getUSU_CODIGO();
            case 2:
                return objModel.getFornecedor_compra().getFOR_CODIGO();
            case 3:
                return objModel.getCpr_emissao();
            case 4:
                return objModel.getCpr_valor();
            case 5:
                return objModel.getCpr_desconto();
            case 6:
                return objModel.getCpr_total();
            case 7:
                return objModel.getCpr_dtentrada();
            case 8:
                return objModel.getCpr_obs();
            default:
                return null;
        }
        
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de Venda
    public void addLista(ArrayList<CompraModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
