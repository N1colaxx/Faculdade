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
    
    
    private void validaID(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("| Digite o ID: ");
                id = leia.nextInt();
                leia.nextLine();

                if (id > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO: ID inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO: ID inválido!! Digite um número inteiro . Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
    
    private void validaNumero(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Digite o Numero: ");
                numero = leia.nextInt();
                leia.nextLine();
                
                if (numero > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO: Numero inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO: Numero inválido!!  Digite um número. Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
    
    private void validavalor(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Digite o Valor: ");
                valor = leia.nextDouble();
                leia.nextLine();
                
                if (valor > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO:  Valor inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO:  Valor inválido!!  Digite um número. Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }   
        }
    }
    
    private void validaJuros(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Digite os Juros: ");
                juros = leia.nextDouble();
                leia.nextLine();
                
                if (juros > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO:  Juros inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO:  Juros inválido!!  Digite um número. Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }   
        }
    }
    
    private void validaMulta(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Digite os multa: ");
                multa = leia.nextDouble();
                leia.nextLine();
                
                if (multa > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO:  Multa inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO:  Multa inválido!!  Digite um número. Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }   
        }
    }
    
    private void validaDesconto(){
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Digite os Desconto: ");
                desconto = leia.nextDouble();
                leia.nextLine();
                
                if (desconto > 0) {
                    valido = true;  
                } else {
                    System.out.println("\n ERRO:  Desconto inválido! Tem q ser maior q 0(zero). Digite novamente...");
                }
            } 
            catch (Exception e) {
                System.out.println("\n ERRO:  Desconto inválido!!  Digite um número. Digite novamente...");
                leia.nextLine(); // Limpar o buffer do scanner
            }   
        }
    }
    
    
    @Override
    public void entrar(){  
            validaID();
            
            validaNumero();
 
            System.out.print("Digite a data Emissão: ");
            emissao = leia.nextLine();
            
            System.out.print("Digite a data de Vencimento: \n");
            vencimento = leia.nextLine();
            
            System.out.print("Digite a data de Pagamento: ");
            pagamento = leia.nextLine();
            
            validavalor();
            
            validaJuros();
            
            validaMulta();
            
            validaDesconto();
            
            // Calcular o total
            total = (valor + juros) - desconto;
    }
          
    
    @Override
    public void imprimir() {
        // Exibe os valores de todos os atributos
        System.out.println("|   ID financeiro: " + id);
        System.out.println("|   Número: " + numero);
        System.out.println("|   Emissão: " + emissao);
        System.out.println("|   Vencimento: " + vencimento);
        System.out.println("|   Pagamento: " + pagamento);
        System.out.println("|   Valor: " + valor);
        System.out.println("|   Juros: " + juros);
        System.out.println("|   Multa: " + multa);
        System.out.println("|   Desconto: " + desconto);
        System.out.println("|   Total é a soma de (valor +juros) - Desconto |");
        System.out.println("|   Total = " + total);
    }
}


