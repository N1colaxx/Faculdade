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
public abstract class Pessoa {
    private int pes_id;
    private int pes_tipo;
    private String pes_nome;
    
    public Pessoa(){};
    
    public Pessoa(int pes_id, int pes_tipo, String pes_nome){
        this.pes_id = pes_id;
        this.pes_tipo = pes_tipo;
        this.pes_nome = pes_nome;
    }
    
    public String getPesNome (){
        return pes_nome;
    }

    public int getPes_id() {
        return pes_id;
    }

    public int getPes_tipo() {
        return pes_tipo;
    }

    public void setPes_id(int pes_id) {
        this.pes_id = pes_id;
    }

    public void setPes_tipo(int pes_tipo) {
        this.pes_tipo = pes_tipo;
    }

    public void setPes_nome(String pes_nome) {
        this.pes_nome = pes_nome;
    }
    
    
    public void entrar(){
        Scanner leia = new Scanner(System.in);
        
        System.out.println("Dig o nome: ");
        pes_nome = leia.nextLine();
        
        System.out.println("Dig id: ");
        pes_id = leia.nextInt();
        leia.nextLine();
        
        System.out.println("Dig tipo: ");
        pes_tipo = leia.nextInt();
    }
    
    
    public void imprimir(){
        System.out.println("Nome: " + pes_nome);
        System.out.println("ID: " + pes_id);
        System.out.println("Tipo: " + pes_tipo);
    }
}
