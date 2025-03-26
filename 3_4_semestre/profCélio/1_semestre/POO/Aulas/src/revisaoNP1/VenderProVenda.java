/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisaoNP1;

/**
 *
 * @author nicol
 */
public class VenderProVenda {
    private ProdutoVenda produtoVenda;
    
    public void venderProduto() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
        } else {
            produtoVenda.vender();
        }
    }
}
