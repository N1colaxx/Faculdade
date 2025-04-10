/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public abstract class PJ extends Pessoa implements InterfaceCadastro {
//    Atributos da class

    private String cnpj;
    private String inscricao_estadual;
    private String contato;

//  Contrutores
    public PJ() {
    }


  
//  Getters e Setters Pessoa Juridica
    public String getCNPJ() {
        return cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricao_estadual;
    }

    public String getContato() {
        return contato;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setInscricaoEstadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public void entrar() {
        super.entrar();
        leia.nextLine();

        System.out.print("| Digite o CNPJ: ");
        cnpj = leia.nextLine();

        System.out.print("| Digite a Incrição Estadual:  ");
        inscricao_estadual = leia.nextLine();

        System.out.print("| Digite um meio de Contato: ");
        contato = leia.nextLine();

        System.out.println("|-------------------------------------------------|");
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("|   CNPJ: " + cnpj);
        System.out.println("|   Inscrição Estadual: " + inscricao_estadual);
        System.out.println("|   Meio de Contato: " + contato);
    }
}
