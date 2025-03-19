/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;



/**
 *
 * @author nicol
 */
public abstract class Financeiro implements InterfaceCadastro{
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
    public Financeiro(){};

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
    
    @Override
    public void entrar(){  
        try (leia) {
            System.out.print("Digite o ID: ");
            id = leia.nextInt();
            
            System.out.print("Digite o Numero: ");
            numero = leia.nextInt();
            
            leia.nextLine();
            
            System.out.print("Digite a data Emissão: ");
            emissao = leia.nextLine();
            
            System.out.print("Digite a data de Vencimento: ");
            vencimento = leia.nextLine();
            
            System.out.print("Digite a data de Pagamento: ");
            pagamento = leia.nextLine();
            
            System.out.print("Digite o Valor: ");
            valor = leia.nextDouble();
            leia.nextLine();
            
            System.out.print("Digite os Juros: ");
            juros = leia.nextDouble();
            leia.nextLine();
            
            System.out.print("Digite a Multa: ");
            multa = leia.nextDouble();
            leia.nextLine();
            
            System.out.print("Digite o Desconto: ");
            desconto = leia.nextDouble();
            leia.nextLine();
            
            // Calcular o total
            total = (valor + juros) - desconto;
        }
    }
          
    
    @Override
    public void imprimir() {
        // Exibe os valores de todos os atributos
        System.out.println("|-----------------------------------------------|");
        System.out.println("|            Informações do Financeiro          |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("|   ID: " + id);
        System.out.println("|   Número: " + numero);
        System.out.println("|   Emissão: " + emissao);
        System.out.println("|   Vencimento: " + vencimento);
        System.out.println("|   Pagamento: " + pagamento);
        System.out.println("|   Valor: " + valor);
        System.out.println("|   Juros: " + juros);
        System.out.println("|   Multa: " + multa);
        System.out.println("|   Desconto: " + desconto);
        System.out.println("|   Total é a soma de (valor +juros) - Desconto |");
        System.out.println("|   Total = " + getTotal());
        System.out.println("|-----------------------------------------------|");
    }
    public static void main (String[] args){
        Receber obj = new Receber();
//        Financeiro obj1 = new Financeiro(1, 123, "2025-03-17", "2025-04-17", "2025-03-20", 1000.0, 50.0, 10.0, 5.0, 1055.0);

        obj.entrar();
        obj.imprimir();
    }
}


