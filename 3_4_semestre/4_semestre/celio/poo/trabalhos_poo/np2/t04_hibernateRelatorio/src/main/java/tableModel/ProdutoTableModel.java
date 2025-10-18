package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.ProdutoModel;


public class ProdutoTableModel extends AbstractTableModel {

    private ArrayList<ProdutoModel> linhas;
    String[] colunas = {
            "Código", "Nome", "Estoque", "Unidade", "Preço", "Custo", "Atacado",
            "Mín", "Máx", "Embalagem", "Peso", "Cadastro", "Obs", "Ativo"
    };

    public ProdutoTableModel(ArrayList<ProdutoModel> lista) {
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
        ProdutoModel objModel = linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getPRO_CODIGO();
            case 1:
                return objModel.getPRO_NOME();
            case 2:
                return objModel.getPRO_ESTOQUE();
            case 3:
                return objModel.getPRO_UNIDADE();
            case 4:
                return objModel.getPRO_PRECO();
            case 5:
                return objModel.getPRO_CUSTO();
            case 6:
                return objModel.getPRO_ATACADO();
            case 7:
                return objModel.getPRO_MIN();
            case 8:
                return objModel.getPRO_MAX();
            case 9:
                return objModel.getPRO_EMBALAGEM();
            case 10:
                return objModel.getPRO_PESO();
            case 11:
                return objModel.getPRO_CADASTRO();
            case 12:
                return objModel.getPRO_OBS();
            case 13:
                return objModel.getPRO_ATIVO() == 1 ? "Sim" : "Não";
            default:
                return null;
        }
    }


    //Adicionamos várias linhas na tabela de uma vez, recebendo um ProdutoModel
    public void addLista(ArrayList<ProdutoModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
