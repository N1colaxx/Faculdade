package dto;
import model.VendaModel; 
import model.VendaPagtoModel; 
import model.VendaProdutoModel; 

public class VendaCompletaDTO { 
    public VendaModel cabecalho; 
    public java.util.List<VendaProdutoModel> itens; 
    public java.util.List<VendaPagtoModel> pgtos; 
}