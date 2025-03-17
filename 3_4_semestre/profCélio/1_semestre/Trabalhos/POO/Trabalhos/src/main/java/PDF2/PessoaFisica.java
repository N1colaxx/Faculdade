/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

/**
 *
 * @author nicol
 */
public class PessoaFisica extends Pessoa{
    private String cpf;
    private String rg;
    private String emissor_rg;
    
    
    // Construtor Pessoa Fisica
    public PessoaFisica(){};

    public PessoaFisica(int id, String nome, Endereco endereco, Telefone telefone, String cpf, String rg, String emissor_rg) {
        super(id, nome, endereco, telefone); // chamando os contrutores de Pessoa
        this.cpf = cpf;
        this.rg = rg;
        this.emissor_rg = emissor_rg;
    }
    
//    Getters Pessoa Fisica
    public String getCPF(){
        return cpf;
    }
    
    public String getRG(){
        return rg;
    }
    
    public String getEmissorRG(){
        return emissor_rg;
    }
    
//    Setters Pessoa Fisica   
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public void setRG(String rg){
        this.rg = rg;
    }
    
    public void setEmissorRG(String emissor_rg){
        this.emissor_rg = emissor_rg;
    }

    @Override
    public void imprimir(){
        super.imprimir();
        System.out.println("|    CPF: " + cpf);
        System.out.println("|   Numero RG: " + rg + "Emissor RG: " + emissor_rg);
        System.out.println("|___________________________________________________________|");
        System.out.println("|               Do tipo pessoa Fisica                       |");
        System.out.println("|___________________________________________________________|");
    }
    

    
    public static void  main(String[] args){
//        Criando Endereco
        Endereco endereco = new Endereco("Bela Vista", "666", "portão", "ceu", "terra mar", "MS", 18190022);

//      Criando Telefone
        Telefone telefone = new Telefone(11, 294765284);
        
//      Criando Pessoa Fisica
        PessoaFisica pf = new PessoaFisica(99, "João", endereco, telefone, "977060706", "12312312313", "SP");

        pf.imprimir();
    }

}




