/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author Nicolas
 */
public class Funcionario extends PF {

    private String data_admissao;
    private String data_demissao;
    private String ctps;
    private double salario;

    //Getters
    public String getDataAdmissao() {
        return data_admissao;
    }

    public String getDataDemissao() {
        return data_demissao;
    }

    public String getCTPS() {
        return ctps;
    }

    public double getSalario() {
        return salario;
    }

    //Setters
    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }

    public void setData_demissao(String data_demissao) {
        this.data_demissao = data_demissao;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public void entrar() {
        super.entrar(); // chamo o metodo entrar de Pessoa
        leia.nextLine();

        System.out.print("| Digite a Data Adimissao: ");
        data_admissao = leia.nextLine();

        System.out.print("| Digite a Data Demissao: ");
        data_demissao = leia.nextLine();

        System.out.print("| Digite o CTPS: ");
        ctps = leia.nextLine();

        System.out.print("| Digite seu salario: ");
        salario = leia.nextDouble();
    }

    @Override
    public void imprimir() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("|         Informacao Funcionario                |");
        System.out.println("|-----------------------------------------------|");
        super.imprimir();
        System.out.println("|   Data de Admissao: " + data_admissao);
        System.out.println("|   Data de Demissao: " + data_demissao);
        System.out.println("|   CTPS: " + ctps);
        System.out.println("|   Salario: " + salario);
    }
}
