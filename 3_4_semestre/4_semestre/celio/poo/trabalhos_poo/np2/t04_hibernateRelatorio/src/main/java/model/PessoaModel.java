package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa tabela pessoa
 */

@Entity
@Table(name = "PESSOA")
public class PessoaModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_CODIGO")
    private Integer pes_codigo;
    
    private String pes_nome;
    private String pes_fantasia; // somente do fonecedor
    private String pes_fisica;
    private String pes_cpfcnpj;
    private String pes_rgie;
    private LocalDate pes_cadastro;
    private String pes_endereco;
    private String pes_numero;
    private String pes_complemento;
    private String pes_bairro;
    private String pes_cidade;
    private String pes_uf;
    private String pes_cep;
    private String pes_fone1; // somente do fonecedor
    private String pes_fone2; // somente do fonecedor
    private String pes_celular;
    private String pes_site;
    private String pes_email;
    private Integer pes_ativo;

    public PessoaModel() {

    }

    // Construtor de Cliente 
    public PessoaModel(
            Integer PES_CODIGO, String PES_NOME, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, LocalDate PES_CADASTRO, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_CIDADE, String PES_UF, String PES_CEP, String PES_CELULAR, String PES_SITE, String PES_EMAIL, Integer PES_ATIVO
    ) {
        this.pes_codigo = PES_CODIGO;
        this.pes_nome = PES_NOME;
        this.pes_fisica = PES_FISICA;
        this.pes_cpfcnpj = PES_CPFCNPJ;
        this.pes_rgie = PES_RGIE;
        this.pes_cadastro = PES_CADASTRO;
        this.pes_endereco = PES_ENDERECO;
        this.pes_numero = PES_NUMERO;
        this.pes_complemento = PES_COMPLEMENTO;
        this.pes_bairro = PES_BAIRRO;
        this.pes_cidade = PES_CIDADE;
        this.pes_uf = PES_UF;
        this.pes_cep = PES_CEP;
        this.pes_celular = PES_CELULAR;
        this.pes_site = PES_SITE;
        this.pes_email = PES_EMAIL;
        this.pes_ativo = PES_ATIVO;
    }

    // Construtor de Fornecedor
    public PessoaModel(
            Integer PES_CODIGO, String PES_NOME, String PES_FANTASIA, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, LocalDate PES_CADASTRO, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_CIDADE, String PES_UF, String PES_CEP, String PES_FONE1, String PES_FONE2,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, Integer PES_ATIVO
    ) {
        this.pes_codigo = PES_CODIGO;
        this.pes_nome = PES_NOME;
        this.pes_fantasia = PES_FANTASIA;
        this.pes_fisica = PES_FISICA;
        this.pes_cpfcnpj = PES_CPFCNPJ;
        this.pes_rgie = PES_RGIE;
        this.pes_cadastro = PES_CADASTRO;
        this.pes_endereco = PES_ENDERECO;
        this.pes_numero = PES_NUMERO;
        this.pes_complemento = PES_COMPLEMENTO;
        this.pes_bairro = PES_BAIRRO;
        this.pes_cidade = PES_CIDADE;
        this.pes_uf = PES_UF;
        this.pes_cep = PES_CEP;
        this.pes_fone1 = PES_FONE1;
        this.pes_fone2 = PES_FONE2;
        this.pes_celular = PES_CELULAR;
        this.pes_site = PES_SITE;
        this.pes_email = PES_EMAIL;
        this.pes_ativo = PES_ATIVO;
    }



    
    /**
     * GETTERS
     */
    public Integer getPES_CODIGO() {
        return pes_codigo;
    }
    

    @Column(name = "PES_NOME", nullable = false, length = 80)
    public String getPES_NOME() {
        return pes_nome;
    }

    
    @Column(name = "PES_FANTASIA", nullable = true, length = 80)
    public String getPES_FANTASIA() {
        return pes_fantasia;
    }

    
    @Column(name = "PES_FISICA", nullable = false, length = 1)
    public String getPES_FISICA() {
        return pes_fisica;
    }

    
    @Column(name = "PES_CPFCNPJ", nullable = false, length = 20)
    public String getPES_CPFCNPJ() {
        return pes_cpfcnpj;
    }

    
    
    @Column(name = "PES_RGIE", nullable = true, length = 20)
    public String getPES_RGIE() {
        return pes_rgie;
    }

    
    @Column(name = "PES_CADASTRO", nullable = true)
    public LocalDate getPES_CADASTRO() {
        return pes_cadastro;
    }

    
    @Column(name = "PES_ENDERECO", nullable = true, length = 120)
    public String getPES_ENDERECO() {
        return pes_endereco;
    }

    
    @Column(name = "PES_NUMERO", nullable = true, length = 10)
    public String getPES_NUMERO() {
        return pes_numero;
    }

    
    @Column(name = "PES_COMPLEMENTO", nullable = true, length = 30)
    public String getPES_COMPLEMENTO() {
        return pes_complemento;
    }

    
    
    @Column(name = "PES_BAIRRO", nullable = true, length = 50)
    public String getPES_BAIRRO() {
        return pes_bairro;
    }

    
    @Column(name = "PES_CIDADE", nullable = true, length = 80)
    public String getPES_CIDADE() {
        return pes_cidade;
    }

    
    @Column(name = "PES_UF", nullable = true, length = 20)
    public String getPES_UF() {
        return pes_uf;
    }

    
    @Column(name = "PES_CEP", nullable = true, length = 9)
    public String getPES_CEP() {
        return pes_cep;
    }

    
    @Column(name = "PES_FONE1", nullable = true, length = 16)
    public String getPES_FONE1() {
        return pes_fone1;
    }

    
    @Column(name = "PES_FONE2", nullable = true, length = 16)
    public String getPES_FONE2() {
        return pes_fone2;
    }

    
    @Column(name = "PES_CELULAR", nullable = true, length = 16)
    public String getPES_CELULAR() {
        return pes_celular;
    }

    
    @Column(name = "PES_SITE", nullable = true, length = 200)
    public String getPES_SITE() {
        return pes_site;
    }

    
    @Column(name = "PES_EMAIL", nullable = true, length = 200)
    public String getPES_EMAIL() {
        return pes_email;
    }

    
    @Column(name = "PES_ATIVO", nullable = true, length = 1)
    public Integer getPES_ATIVO() {
        return pes_ativo;
    }

    /**
     * SETTERS
     */ 
    public void setPES_CODIGO(Integer PES_CODIGO) {
        this.pes_codigo = PES_CODIGO;
    }

    public void setPES_NOME(String PES_NOME) {
        this.pes_nome = PES_NOME;
    }

    public void setPES_FANTASIA(String PES_FANTASIA) {
        this.pes_fantasia = PES_FANTASIA;
    }

    public void setPES_FISICA(String PES_FISICA) {
        this.pes_fisica = PES_FISICA;
    }

    public void setPES_CPFCNPJ(String PES_CPFCNPJ) {
        this.pes_cpfcnpj = PES_CPFCNPJ;
    }

    public void setPES_RGIE(String PES_RGIE) {
        this.pes_rgie = PES_RGIE;
    }
    
    public void setPES_CADASTRO(LocalDate PES_CADASTRO) {
        this.pes_cadastro = PES_CADASTRO;
    }

    public void setPES_ENDERECO(String PES_ENDERECO) {
        this.pes_endereco = PES_ENDERECO;
    }

    public void setPES_NUMERO(String PES_NUMERO) {
        this.pes_numero = PES_NUMERO;
    }

    public void setPES_COMPLEMENTO(String PES_COMPLEMENTO) {
        this.pes_complemento = PES_COMPLEMENTO;
    }

    public void setPES_BAIRRO(String PES_BAIRRO) {
        this.pes_bairro = PES_BAIRRO;
    }

    public void setPES_UF(String PES_UF) {
        this.pes_uf = PES_UF;
    }

    public void setPES_CIDADE(String PES_CIDADE) {
        this.pes_cidade = PES_CIDADE;
    }

    public void setPES_CEP(String PES_CEP) {
        this.pes_cep = PES_CEP;
    }

    public void setPES_FONE1(String PES_FONE1) {
        this.pes_fone1 = PES_FONE1;
    }

    public void setPES_FONE2(String PES_FONE2) {
        this.pes_fone2 = PES_FONE2;
    }

    public void setPES_CELULAR(String PES_CELULAR) {
        this.pes_celular = PES_CELULAR;
    }

    public void setPES_SITE(String PES_SITE) {
        this.pes_site = PES_SITE;
    }

    public void setPES_EMAIL(String PES_EMAIL) {
        this.pes_email = PES_EMAIL;
    }

    public void setPES_ATIVO(Integer PES_ATIVO) {
        this.pes_ativo = PES_ATIVO;
    }

    

}
