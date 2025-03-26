/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExerciciosLogica;

/**
 *
 * @author Nicolas
 */
public class Ex5 implements Interfece{
    String num_str;
    double num;

    @Override
    public void entrar() {
        System.out.println("Digite um numero: ");
        num_str = scanner.nextLine();
        
        virar();
    }

    
    public void virar(){
     StringBuilder sd = new StringBuilder(num_str);
     String reverse = sd.reverse().toString();
     
     num = Double.parseDouble(reverse);
}
    @Override
    public void imprimir() {
        System.out.println("\nVc digitou o N°: " + num_str);
        System.out.println("Ele Aocontrario: " + num);
    }
    
    
    
    public static void main(String[] args) {
        Ex5 obj = new Ex5();
        
        obj.entrar();
        obj.imprimir();
    }
}
