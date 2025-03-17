/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class PessoaJuridica extends Pessoas {
//    Atributos da class
    private String cnpj;
    private String inscricao_estadual;
    private String contato;

    
//  Contrutores
    public PessoaJuridica() {};
    
// Esse construtor so vai funcionar se as var de Pessoa estiver como (protected)
    public PessoaJuridica(int id, String nome, Endereco endereco, Telefone telefone, String cnpj, String inscricao_estadual, String contato){
        super(id, nome, endereco, telefone);  // Chama o construtor da classe pai (Pessoa) para inicializar 'id' e 'nome' etc...
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
        this.contato = contato;
    }
    

//  Getters e Setters Pessoa Juridica
    public String getCNPJ(){
        return cnpj;
    }
    
    public String getInscricaoEstadual(){
        return inscricao_estadual;
    }
    
    public String getContato(){
        return contato;
    }
    
    
    public void setCNPJ(String cnpj){
        this.cnpj = cnpj;
    }
    
    public void setInscricaoEstadual(String inscricao_estadual){
        this.inscricao_estadual = inscricao_estadual;
    }
    
    public void setContato(String contato){
        this.contato = contato;
    }
    
    
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes(); // Chama o método exibirInformacoes() da classe pai
        if(cnpj != null && inscricao_estadual != null && contato != null){
            System.out.println("|___________________________________________________________|");
            System.out.println("|       Estas Informações são de uma Pessoa Jurídica        |");
            System.out.println("|___________________________________________________________|");
            System.out.println("CNPJ = " + cnpj);
            System.out.println("Inscricao Estadual = " + inscricao_estadual);
            System.out.println("Contato = " + contato);
        }else {
            throw new IllegalArgumentException("ERRO: CNPJ ou Inscricao Estadual ou Contato da PJ eesta ERRADO");
        }
    }
    

    public static void main(String[] args) {
        
        Telefone telefone = new Telefone(14,333333333);
        Endereco endereco = new Endereco("Rua das Flores", "123", "Apt 101", "Centro", "Reginópolis", "SP", 17190005);
        PessoaJuridica pj1 = new PessoaJuridica(20, "Osvaldo Junior", endereco, telefone, "10.190.0001-005.", "está VEIA", "email tomatoma@ai.calica.com");
        
        pj1.exibirInformacoes();
    }
}
