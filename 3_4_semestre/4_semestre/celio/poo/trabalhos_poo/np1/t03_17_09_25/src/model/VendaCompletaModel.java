package model;
import model.VendaModel; 
import model.VendaPagtoModel; 
import model.VendaProdutoModel; 

public class VendaCompletaModel { 
    public VendaModel cabecalho; 
    public java.util.List<VendaProdutoModel> itens; 
    public java.util.List<VendaPagtoModel> pgtos; 
}