/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

import java.util.Scanner;

/**
 *
 * @author nicol
 */
public class Finaceiro {
//    Atributos da class
    private int id;
    private int numero;
    private String emissao;
    private String vencimento;
    private String pagamento;
    private double valor;
    private double juros;
    private double multa;
    private double desconto;
    private double total;
    
//    Contrutores 
    public Finaceiro(){};

    public Finaceiro(int id, int numero, String emissao, String vencimento, String pagamento, double valor, double juros, double multa, double desconto, double total) {
        this.id = id;
        this.numero = numero;
        this.emissao = emissao;
        this.vencimento = vencimento;
        this.pagamento = pagamento;
        this.valor = valor;
        this.juros = juros;
        this.multa = multa;
        this.desconto = desconto;
        this.total = total;
    }
    
    
    
//  Getteers
    public int getID(){
        return id;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getEmissao(){
        return emissao;
    }

    public String getVencimento() {
        return vencimento;
    }

    public String getPagamento() {
        return pagamento;
    }

    public double getValor() {
        return valor;
    }

    public double getJuros() {
        return juros;
    }

    public double getMulta() {
        return multa;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getTotal() {
        return total;
    }
    
//    Setters       
    public void setID(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
//    Entrada de valores pelo User
    public void entrada(){
        Scanner scanner = new Scanner(System.in);
            
        System.out.println("Digite o ID: ");
        setID(scanner.nextInt());

        System.out.println("Digite o Numero: ");
        setNumero(scanner.nextInt());

        scanner.nextLine();

        System.out.println("Digite a data Emissão: ");
        setEmissao(scanner.nextLine());

        System.out.print("Digite a data de Vencimento: ");
        setVencimento(scanner.nextLine());

        System.out.print("Digite a data de Pagamento: ");
        setPagamento(scanner.nextLine());

        System.out.print("Digite o Valor: ");
        setValor(scanner.nextDouble());
        scanner.nextLine();
        
        System.out.print("Digite os Juros: ");
        setJuros(scanner.nextDouble());
        scanner.nextLine();
        
        System.out.print("Digite a Multa: ");
        setMulta(scanner.nextDouble());
        scanner.nextLine();
        
        System.out.print("Digite o Desconto: ");
        setDesconto(scanner.nextDouble());
        scanner.nextLine();
        
        // Calcular o total 
        setTotal(getValor() + getJuros() + getMulta() - getDesconto());

        scanner.close();
    }
          
    
//   Impressao do Valores 
    public void imprimir() {
        // Exibe os valores de todos os atributos
        System.out.println("------ Informações do Financeiro ------");
        System.out.println("ID: " + getID());
        System.out.println("Número: " + getNumero());
        System.out.println("Emissão: " + getEmissao());
        System.out.println("Vencimento: " + getVencimento());
        System.out.println("Pagamento: " + getPagamento());
        System.out.println("Valor: " + getValor());
        System.out.println("Juros: " + getJuros());
        System.out.println("Multa: " + getMulta());
        System.out.println("Desconto: " + getDesconto());
        System.out.println("Total é a soma de (valor, juros, Multa) subitraido do Desconto ");
        System.out.println("Total: " + getTotal());
        System.out.println("--------------------------------------");
    }
    public static void main (String[] args){
        Finaceiro obj = new Finaceiro();
//        Finaceiro obj1 = new Finaceiro(1, 123, "2025-03-17", "2025-04-17", "2025-03-20", 1000.0, 50.0, 10.0, 5.0, 1055.0);

        obj.entrada();
        obj.imprimir();
    }         
}
