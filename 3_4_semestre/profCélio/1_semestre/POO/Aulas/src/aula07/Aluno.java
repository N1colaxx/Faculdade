/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula07;

/**
 *
 * @author Nicolas
 */
public class Aluno {
    private String nome;
    private String ra;
    private String celular;
    private String email;
    
    public Aluno(){
    }

    public Aluno(String nome, String ra, String celular, String email) {
        this.nome = nome;
        this.ra = ra;
        this.celular = celular;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return this.ra + " - " + this.nome;
    }
}
