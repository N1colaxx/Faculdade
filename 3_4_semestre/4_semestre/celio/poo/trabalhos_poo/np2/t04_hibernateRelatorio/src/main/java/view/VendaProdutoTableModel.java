package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.VendaProdutoModel;

/**
 * Esta é a minha tab de Itens da Venda
 */
public class VendaProdutoTableModel extends AbstractTableModel {

    String[] colunas = {"Codigo VEP", "Codigo VDA", "Código Produto", "Nome", "Quantidade", "Preco", "Desconto", "Total"};
    private ArrayList<VendaProdutoModel> linhas;

    public VendaProdutoTableModel(ArrayList<VendaProdutoModel> itens) {
        this.linhas = itens;
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
        VendaProdutoModel objModel = (VendaProdutoModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getVep_codigo();
            case 1:
                Integer vda_cod = objModel.getVenda_VendaProduto().getVda_codigo();
                return (vda_cod == null ? 0 : vda_cod); 
            case 2:
                return objModel.getProduto_VendaProduto().getPRO_CODIGO();
            case 3:
                return objModel.getProduto_VendaProduto().getPRO_NOME();
            case 4:
                return objModel.getVep_qtde();
            case 5:
                return objModel.getVep_preco();
            case 6:
                return objModel.getVep_desconto();
            case 7: 
                return objModel.getVep_total();
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de Venda
    public void addLista(ArrayList<VendaProdutoModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    
    
    public void addItem(VendaProdutoModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void setItem(int index, VendaProdutoModel item) {
        if (index >= 0 && index < linhas.size()) {
            linhas.set(index, item);
            fireTableRowsUpdated(index, index);
        }
    }

    public ArrayList<VendaProdutoModel> getLinhas(){ return linhas; }
    public VendaProdutoModel getItem(int row){ return linhas.get(row); }
}
