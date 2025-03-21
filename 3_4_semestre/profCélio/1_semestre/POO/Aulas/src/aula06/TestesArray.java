/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula06;

/**
 *
 * @author nicol
 */
import java.util.Scanner;


public class TestesArray {
    
    
    public TestesArray(){
        Scanner scanner = new Scanner(System.in);
    }
    
    private void arra(){
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

    private void array2(){
        
    }
    
    public static void main(String[] args) {
        TestesArray obj = new TestesArray();
        
        obj.arra();
        obj.array2();

    }
} 
