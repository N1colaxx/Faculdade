/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exerciciosNP1;

/**
 *
 * @author Nicolas
 */
public class Media {
    
    
    public static double calcular(double a, double b){
        double x;
            x = (a + b) / 2;
        return x;
    }
    
    public static void main(String[] args) {
        double n1 = 10;
        double n2 = 3;
        
        double media = calcular(n1, n2);
        System.out.println("Media: " + media);
    }
}
