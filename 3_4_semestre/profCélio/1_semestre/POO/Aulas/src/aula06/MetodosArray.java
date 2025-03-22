package aula06;


import java.util.Arrays;


public class MetodosArray {
    
    private void ex1(){
        String[][] nomes = {{"Mr. ", "Mrs. ", "Ms. "},{"Smith", "Jones"}}; 

        System.out.println(nomes[0][0] + nomes[1][0]); //Mr. Smith 
        System.out.println(nomes[0][2] + nomes[1][1]); //Ms. Jones  

        //       Primeira linha (nomes[0]): {"Mr. ", "Mrs. ", "Ms. "}
        //        nomes[0][0] → "Mr. "
        //        nomes[0][1] → "Mrs. "
        //        nomes[0][2] → "Ms. "
        //        Segunda linha (nomes[1]): {"Smith", "Jones"}
        //        nomes[1][0] → "Smith"
        //        nomes[1][1] → "Jones"
        
        System.out.println(nomes.length);

    }

    
    private void ex2(){
        int[] meuArray;
         
//      Por padrão quando n colocamos valores nas posições o JAVA coloca o valor: 0(zero).
        meuArray = new int[10];
        meuArray[0] = 2;
        meuArray[1] = 242;
        meuArray[2] = 5;
        meuArray[3] = 456;
        meuArray[4] = 444;
        
        
//      Chamo a class Arrays e o metodo SORT para ordenar o array
//      OBS: lebra q isso ordena o ARRAY e o java coloca 0(zero) por padrão.
        Arrays.sort(meuArray);
        System.out.println("Usando metodo SORT:");
        for(int i=0; i < meuArray.length; i++){   
            System.out.println("    Posicao " + i + " = " + meuArray[i]);
        }

        
//      Aqui uso a class binarySearch para retornar a posição  
        System.out.println("\nUsando metodo binarySearch: ");
        System.out.println("    posicao do elemento 444 = " + Arrays.binarySearch(meuArray, 444));
             
        
//      Aqui crio um Array identico ao primeiro
        int[] espelho = meuArray;
        
        System.out.println("\nUsando a metodo EQUALS: ");
//      O método equals da classe Arrays, permite comparar dois arrays retornando “true” ou “false”, como mostra o código a seguir:: 
        if(Arrays.equals(meuArray, espelho)){
            System.out.println("    Sao identicos...");
        }else{
            System.out.println("    Sao diferentes...");
        }
        System.out.println("\n  ");
        
        
        System.out.println("Usando o metodo FILL: ");
//      O método fill da classe Arrays, preenche todos os elementos de um array com um dado valor, da seguinte forma: 
        Arrays.fill(espelho, 5533);
        
//      Percorrendo todos os elementos deste array verificamos que os valores são os mesmos para todas as posições do array espelho:
        for(int i = 0; i < espelho.length; i++){
            System.out.println("    Posicao " + i + " = " + espelho[i]);
        }
   
        
//        Usando o meto toString, permite exibir todos elementos de um objeto array.
        System.out.println("\nUsando o metodo toString: ");
        System.out.println("    Conteudo do obj array: " + Arrays.toString(meuArray));
    }
    
    
    private void ex3(){
        
    }
    
    public static void main(String[] args) {
        MetodosArray obj = new MetodosArray();
        
//        obj.ex1();
        obj.ex2();
//        obj.ex3();
    }
} 
