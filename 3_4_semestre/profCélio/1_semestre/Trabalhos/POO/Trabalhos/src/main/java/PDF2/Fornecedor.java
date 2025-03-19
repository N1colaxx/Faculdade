/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class Fornecedor extends PJ {

    private double limite_compra;
    private String data_cadastro;
    private String site;

//    Getters
    public double getLimiteCompra() {
        return limite_compra;
    }

    public String getDataCompra() {
        return data_cadastro;
    }

    public String getSite() {
        return site;
    }

//    Setters
    public void setLimiteCompra(double limite_compra) {
        this.limite_compra = limite_compra;
    }

    public void setDataCadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public void entrar() {
        super.entrar();

        System.out.print("| Digite o Limite da Compra: ");
        limite_compra = leia.nextDouble();
        leia.nextLine();

        System.out.print("| Digite a Data do Cadastro: ");
        data_cadastro = leia.nextLine();

        System.out.print("| Digite o Site: ");
        site = leia.nextLine();
    }

    @Override
    public void imprimir() {
        System.out.println("|===============================================|");
        System.out.println("|           Informações Fornecedor              |");
        System.out.println("|===============================================|");
        super.imprimir();
        System.out.println("|   Limite da Compra: " + limite_compra);
        System.out.println("|   Data do Cadastro: " + data_cadastro);
        System.out.println("|   Site: " + site);
    }
    
    @Override
    public String toString() {
        return 
                "|   ID = " + getID() + "\n"
                + "|   Nome = " + getNome() + "\n"
                + "|   " + getEndereco() + "\n"
                + "|   " + getTelefone() + "\n"
                + "|   CNPJ: " + getCNPJ() + "\n"
                + "|   Inscricao Estadual: " + getInscricaoEstadual() + "\n"
                + "|   Meio de Contato: " + getContato() + "\n"
                + "|   Limite da Compra: " + limite_compra + "\n"
                + "|   Data do Cadastro: " + data_cadastro + "\n"
                + "|   Site: " + site;
    }

}
