
package Aula_06_Arrays;

import java.util.Arrays;

public class MetodosBasicos {
    public static void main(String args[]){
        int[] meuArray;
        meuArray = new int[10];
        
        meuArray[0] = 22; 
        meuArray[1] = 2; 
        meuArray[2] = 242; 
        meuArray[3] = 4552; 
        meuArray[4] = 36;  
   
//      Aplicando -> binarySearch 
//      OBS: para usar o binarySearch nosso array tem q estar ordenado
//      para isso usamos o metodo SORT
        System.out.println("\n");
        Arrays.sort(meuArray);
        for (int i=0; i < meuArray.length; i++){
            System.out.println("Posição ["+ i +"] = " + meuArray[i]);
        }
        
        System.out.println("posição do elemento 4552 : " + Arrays.binarySearch(meuArray, 2));

//      Vamos agora construir um objeto array idêntico ao meuArray da seguinte forma:
        int[] espelho = meuArray;
        
//      Aplicando o EQUALS, permite comparar dois arrays retornando “true” ou “false”
        System.out.println("\n");
        if(Arrays.equals(meuArray, espelho)){
            System.out.println("são IDENTICOS");
        } else{
            System.out.println(" são DIFERENTES");
        }
        
//      Aplicando FILL, preenche todos os elementos de um array com um dado valor
        Arrays.fill(espelho, 5522);
        
        System.out.println("\n");
        for(int i=0; i < espelho.length; i++){
            System.out.println("Posição["+i+"] = " + espelho[i]);
        }
        
//        Aplicando  toString(),  exibir todos elementos de um objeto array
        System.out.println("\n");
        System.out.println("Conteúdo do objeto array : " + Arrays.toString(meuArray)); 
    }  
    
//    Testendo como usar o linux + git
}
