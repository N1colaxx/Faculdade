package tableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.CompraProdutoModel;

/**
 * Esta é a minha tab de Itens da Compra
 */
public class CompraProdutoTableModel extends AbstractTableModel {

    String[] colunas = {"Codigo CPP", "Codigo Compra", "Código Produto", "Nome", "Quantidade", "Preco", "Desconto", "Total"};
    private ArrayList<CompraProdutoModel> linhas;

    public CompraProdutoTableModel(ArrayList<CompraProdutoModel> itens) {
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
        CompraProdutoModel objModel = (CompraProdutoModel) linhas.get(row);
        switch (col) {
            case 0:
                return objModel.getCpp_codigo();
            case 1:
                Integer cpr_cod = objModel.getCompra_compraPro().getCpr_codigo();
                return (cpr_cod == null ? 0 : cpr_cod); 
            case 2:
                return objModel.getProduto_compraPro().getPRO_CODIGO();
            case 3:
                return objModel.getProduto_compraPro().getPRO_NOME();
            case 4:
                return objModel.getCpr_qtde();
            case 5:
                return objModel.getCpr_preco();
            case 6:
                return objModel.getCpr_desconto();
            case 7: 
                return objModel.getCpr_total();
            default:
                return null;
        }
    }

    //Adicionamos várias linhas na tabela de uma vez, recebendo um List de Venda
    public void addLista(ArrayList<CompraProdutoModel> lstModel) {
        int tamanhoAntigo = getRowCount();

        //Adiciona os usuários
        linhas.addAll(lstModel);

        //Aqui reportamos a mudança para o JTable, assim ele pode se redesenhar, para visualizarmos a alteração
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    
    
    public void addItem(CompraProdutoModel it){
        linhas.add(it);
        int i = linhas.size()-1;
        fireTableRowsInserted(i, i);
    }
    public void removeItem(int row){
        linhas.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void setItem(int index, CompraProdutoModel item) {
        if (index >= 0 && index < linhas.size()) {
            linhas.set(index, item);
            fireTableRowsUpdated(index, index);
        }
    }
 
    public Double somaTotais(){
        double s = 0;
        for (CompraProdutoModel it : linhas){
            double v = it.getCpr_total();
            s += v;
        }
        return s;
    }

    public ArrayList<CompraProdutoModel> getLinhas(){ return linhas; }
    public CompraProdutoModel getItem(int row){ return linhas.get(row); }
}
