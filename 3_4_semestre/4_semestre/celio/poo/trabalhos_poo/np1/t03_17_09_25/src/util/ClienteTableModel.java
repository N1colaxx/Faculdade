
package util;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.ClienteModel;

public class ClienteTableModel extends AbstractTableModel {

    private ArrayList<ClienteModel> linhas;
    private final String[] colunas;

    public ClienteTableModel(ArrayList<ClienteModel> arrayClientes, String[] colunas) {
        this.colunas = colunas;
        this.linhas  = arrayClientes;
    }

    @Override
    public int getColumnCount() { return colunas.length; }

    @Override
    public int getRowCount() { return linhas.size(); }

    @Override
    public String getColumnName(int indiceColuna) { return colunas[indiceColuna]; }

    @Override
    public Object getValueAt(int row, int col) {
        ClienteModel c = linhas.get(row);
        switch (col) {
            case 0: return c.getPES_CODIGO();             // Código (pes_codigo)
            case 1: return c.getPES_NOME();               // Nome
            case 2: return c.getPES_CPFCNPJ();            // CPF/CNPJ
            case 3: return "S".equalsIgnoreCase(c.getPES_ATIVO()) ? "Sim" : "Não"; // Ativo
            case 4: return c.getCLI_LIMITECRED();         // Limite de Crédito (double)
            default: return null;
        }
    }

    public void addLista(ArrayList<ClienteModel> clientes) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(clientes);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
}
 
