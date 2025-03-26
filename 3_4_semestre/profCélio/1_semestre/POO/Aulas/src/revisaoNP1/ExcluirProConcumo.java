/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package revisaoNP1;

/**
 *
 * @author nicol
 */
public class ExcluirProConcumo {
    private ProdutoConsumo produtoConsumo;
    
    
    public void excluirProduto() {
        if (produtoConsumo == null) {
            System.out.println("Nenhum produto de consumo cadastrado.");
        } else {
            produtoConsumo = null;
            System.out.println("Produto de consumo excluído com sucesso!");
        }
    }
}
