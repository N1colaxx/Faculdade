/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exerciciosNP1;

/**
 *
 * @author Nicolas
 */
public class Tabuada {
  
    public static void main(String[] args) {
        int num = 2;
        int cont = 11;
        
        while (num < 8) {            
            System.out.println("\n\nTabuada do: " + num);
            for(int i=0; i < cont; i++ ){
                int tab;
                    tab = i * num;
                    System.out.println(i + " x " + num + " = " + tab);
            }
            num ++;
        }
    }
}
