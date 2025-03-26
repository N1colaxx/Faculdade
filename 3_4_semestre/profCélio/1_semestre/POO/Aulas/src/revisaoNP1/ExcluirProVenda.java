/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisaoNP1;

/**
 *
 * @author nicol
 */
public class ExcluirProVenda {
    private ProdutoVenda produtoVenda;

    public void excluirProduto() {
        if (produtoVenda == null) {
            System.out.println("Nenhum produto de venda cadastrado.");
        } else {
            produtoVenda = null;
            System.out.println("Produto de venda excluído com sucesso!");
        }
    }    
}
