package util;

import model.ProdutoModel;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProdutoTableModel extends AbstractTableModel {

    private ArrayList<ProdutoModel> linhas;
    private String[] colunas;

    public ProdutoTableModel(ArrayList<ProdutoModel> arrayProduto, String[] colunas) {
        this.colunas = colunas;
        this.linhas = arrayProduto;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int indiceColuna) {
        return colunas[indiceColuna];
    }

    @Override
    public Object getValueAt(int row, int col) {
        ProdutoModel p = linhas.get(row);
        switch (col) {
            case 0: return p.getPRO_CODIGO();
            case 1: return p.getPRO_NOME();
            case 2: return fmt(p.getPRO_ESTOQUE());
            case 3: return p.getPRO_UNIDADE();
            case 4: return fmt(p.getPRO_PRECO());
            case 5: return fmt(p.getPRO_CUSTO());
            case 6: return fmt(p.getPRO_ATACADO());
            case 7: return fmt(p.getPRO_MIN());
            case 8: return fmt(p.getPRO_MAX());
            case 9: return fmt(p.getPRO_EMBALAGEM());
            case 10: return fmt(p.getPRO_PESO());
            case 11: return fmt(p.getPRO_CADASTRO());
            case 12: return p.getPRO_OBS();
            case 13: return p.getPRO_ATIVO(); // "S" / "N"
            default: return null;
        }
    }

    // Adicionar lista de produtos de uma vez
    public void addLista(ArrayList<ProdutoModel> produtos) {
        int tamanhoAntigo = getRowCount();
        linhas.addAll(produtos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    // ====================== Helpers ======================
    private String fmt(BigDecimal v) {
        if (v == null) return "";
        return v.stripTrailingZeros().toPlainString();
    }

    private String fmt(LocalDate d) {
        if (d == null) return "";
        return d.toString(); // yyyy-MM-dd
    }
}
