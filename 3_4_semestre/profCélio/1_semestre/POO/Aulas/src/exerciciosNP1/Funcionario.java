/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exerciciosNP1;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class Funcionario extends Pessoa {
    private double fun_salario; 
    private String fun_funcao;
    
    public Funcionario(){};
    
    public Funcionario(double fun_salario, String fun_funcao){
        this.fun_funcao = fun_funcao;
        this.fun_salario = fun_salario;
    }

    public double getFun_salario() {
        return fun_salario;
    }

    public String getFun_funcao() {
        return fun_funcao;
    }

    public void setFun_salario(double fun_salario) {
        this.fun_salario = fun_salario;
    }

    public void setFun_funcao(String fun_funcao) {
        this.fun_funcao = fun_funcao;
    }
    
    
    
    @Override
    public void entrar() {
        Scanner leia = new Scanner(System.in);
        
        super.entrar();
        
        System.out.println("Dig salario: ");
        fun_salario = leia.nextDouble();
        leia.nextLine();
        
        System.out.println("Dig funca: ");
        fun_funcao = leia.nextLine();
        leia.nextLine();
    }
    
    @Override
    public void imprimir() {
        super.imprimir(); 
        
        System.out.println("Salario: " + fun_salario);
        System.out.println("Funcao: " + fun_funcao);
    }
}
