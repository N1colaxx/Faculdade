/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;


/**
 *
 * @author nicol
 */
public class Telefone implements InterfaceCadastro{
//  Atributos da Class
    private int ddd;
    private long numero;
    
    
//    Getters
    public int getDDD(){
        return ddd;
    }
    
    public long getNumero(){
        return numero;
    }
    
    
//    Setters
    public void setDDD(int ddd) {
            this.ddd = ddd;
        }
    
    public void setNumero(long numero){
            this.numero = numero;
        }
    
    private void validaDDD(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("| Digite seu DDD: ");
                ddd = leia.nextInt();

                if (ddd >= 11 && ddd <= 99) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO: CEP inválido! O DDD deves der entre 11 e 99 . Digite novamente...");
                }
            } catch (Exception e) {
                System.out.println("\n ERRO: DDD inválido!! Digite um número inteiro.");
                leia.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
    
    private void validaNumero(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("| Digite seu Numero: ");
                numero = leia.nextInt();

                if (ddd > 0 && ddd <= 999999999) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO: Numero inválido! O Numero não inicia com 0(zero) e max 8 digitos. Digite novamente...");
                }
            } catch (Exception e) {
                System.out.println("\n ERRO: Numero inválido!! Digite um número inteiro e no max 8 digitos.");
                leia.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
    
    @Override
    public void entrar(){
    
        validaDDD();
        
        validaNumero();
        
        System.out.println("|-----------------------------------------------|");
        
    }
    
    @Override
    public void imprimir(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        return "Telefone: (" + ddd +")"+ numero;
    }
}
