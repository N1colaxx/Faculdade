package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "PESSOA")
public class PessoaModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PES_CODIGO")
    private int PES_CODIGO;
    
//    Join 1:N -> (Pessoa) | <==>  o|< (Cliente)
//    Join 1:N -> (Pessoa) | <==>  o|< (Fornecedor)

    @OneToMany
    @JoinTable ( name = "Pessoa_Cliente",
            joinColumns = @JoinColumn (name = "PES_CODIGO"),
            inverseJoinColumns = @JoinColumn( name = "CLI_CODIGO")
    )
    private Collection<ClienteModel> clientes;
    

    @Column(name = "PES_NOME", nullable = false, length = 80)
    private String PES_NOME;

    @Column(name = "PES_FANTAZIA", nullable = true, length = 80)
    private String PES_FANTAZIA; // somente do fonecedor

    @Column(name = "PES_FISICA", nullable = false, length = 1)
    private String PES_FISICA;

    @Column(name = "PES_CPFCNPJ", nullable = false, length = 20)
    private String PES_CPFCNPJ;

    @Column(name = "PES_RGIE", nullable = true, length = 20)
    private String PES_RGIE;

    @Column(name = "PES_CADASTRO", nullable = true)
    private LocalDate PES_CADASTRO;

    @Column(name = "PES_ENDERECO", nullable = true, length = 120)
    private String PES_ENDERECO;

    @Column(name = "PES_NUMERO", nullable = true, length = 10)
    private String PES_NUMERO;

    @Column(name = "PES_COMPLEMENTO", nullable = true, length = 30)
    private String PES_COMPLEMENTO;

    @Column(name = "PES_BAIRRO", nullable = true, length = 50)
    private String PES_BAIRRO;

    @Column(name = "PES_CIDADE", nullable = true, length = 80)
    private String PES_CIDADE;

    @Column(name = "PES_UF", nullable = true, length = 20)
    private String PES_UF;

    @Column(name = "PES_CEP", nullable = true, length = 9)
    private String PES_CEP;

    @Column(name = "PES_FONE1", nullable = true, length = 16)
    private String PES_FONE1; // somente do fonecedor

    @Column(name = "PES_FONE2", nullable = true, length = 16)
    private String PES_FONE2; // somente do fonecedor

    @Column(name = "PES_CELULAR", nullable = true, length = 16)
    private String PES_CELULAR;

    @Column(name = "PES_SITE", nullable = true, length = 200)
    private String PES_SITE;

    @Column(name = "PES_EMAIL", nullable = true, length = 200)
    private String PES_EMAIL;

    @Column(name = "PES_ATIVO", nullable = true, length = 1)
    private String PES_ATIVO;

    public PessoaModel() {

    }

    // Construtor de Cliente 
    public PessoaModel(
            int PES_CODIGO, String PES_NOME, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, LocalDate PES_CADASTRO, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_CIDADE, String PES_UF, String PES_CEP, String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO
    ) {
        this.PES_CODIGO = PES_CODIGO;
        this.PES_NOME = PES_NOME;
        this.PES_FISICA = PES_FISICA;
        this.PES_CPFCNPJ = PES_CPFCNPJ;
        this.PES_RGIE = PES_RGIE;
        this.PES_CADASTRO = PES_CADASTRO;
        this.PES_ENDERECO = PES_ENDERECO;
        this.PES_NUMERO = PES_NUMERO;
        this.PES_COMPLEMENTO = PES_COMPLEMENTO;
        this.PES_BAIRRO = PES_BAIRRO;
        this.PES_CIDADE = PES_CIDADE;
        this.PES_UF = PES_UF;
        this.PES_CEP = PES_CEP;
        this.PES_CELULAR = PES_CELULAR;
        this.PES_SITE = PES_SITE;
        this.PES_EMAIL = PES_EMAIL;
        this.PES_ATIVO = PES_ATIVO;
    }

    // Construtor de Fornecedor
    public PessoaModel(
            int PES_CODIGO, String PES_NOME, String PES_FANTAZIA, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, LocalDate PES_CADASTRO, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_CIDADE, String PES_UF, String PES_CEP, String PES_FONE1, String PES_FONE2,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO
    ) {
        this.PES_CODIGO = PES_CODIGO;
        this.PES_NOME = PES_NOME;
        this.PES_FANTAZIA = PES_FANTAZIA;
        this.PES_FISICA = PES_FISICA;
        this.PES_CPFCNPJ = PES_CPFCNPJ;
        this.PES_RGIE = PES_RGIE;
        this.PES_CADASTRO = PES_CADASTRO;
        this.PES_ENDERECO = PES_ENDERECO;
        this.PES_NUMERO = PES_NUMERO;
        this.PES_COMPLEMENTO = PES_COMPLEMENTO;
        this.PES_BAIRRO = PES_BAIRRO;
        this.PES_UF = PES_UF;
        this.PES_CIDADE = PES_CIDADE;
        this.PES_CEP = PES_CEP;
        this.PES_FONE1 = PES_FONE1;
        this.PES_FONE2 = PES_FONE2;
        this.PES_CELULAR = PES_CELULAR;
        this.PES_SITE = PES_SITE;
        this.PES_EMAIL = PES_EMAIL;
        this.PES_ATIVO = PES_ATIVO;
    }

    // Getters
    public int getPES_CODIGO() {
        return PES_CODIGO;
    }

    public String getPES_NOME() {
        return PES_NOME;
    }

    public String getPES_FANTAZIA() {
        return PES_FANTAZIA;
    }

    public String getPES_FISICA() {
        return PES_FISICA;
    }

    public String getPES_CPFCNPJ() {
        return PES_CPFCNPJ;
    }

    public String getPES_RGIE() {
        return PES_RGIE;
    }

    public LocalDate getPES_CADASTRO() {
        return PES_CADASTRO;
    }

    public String getPES_ENDERECO() {
        return PES_ENDERECO;
    }

    public String getPES_NUMERO() {
        return PES_NUMERO;
    }

    public String getPES_COMPLEMENTO() {
        return PES_COMPLEMENTO;
    }

    public String getPES_BAIRRO() {
        return PES_BAIRRO;
    }

    public String getPES_CIDADE() {
        return PES_CIDADE;
    }

    public String getPES_UF() {
        return PES_UF;
    }

    public String getPES_CEP() {
        return PES_CEP;
    }

    public String getPES_FONE1() {
        return PES_FONE1;
    }

    public String getPES_FONE2() {
        return PES_FONE2;
    }

    public String getPES_CELULAR() {
        return PES_CELULAR;
    }

    public String getPES_SITE() {
        return PES_SITE;
    }

    public String getPES_EMAIL() {
        return PES_EMAIL;
    }

    public String getPES_ATIVO() {
        return PES_ATIVO;
    }

    // Setters
    public void setPES_CODIGO(int PES_CODIGO) {
        this.PES_CODIGO = PES_CODIGO;
    }

    public void setPES_NOME(String PES_NOME) {
        this.PES_NOME = PES_NOME;
    }

    public void setPES_FANTAZIA(String PES_FANTAZIA) {
        this.PES_FANTAZIA = PES_FANTAZIA;
    }

    public void setPES_FISICA(String PES_FISICA) {
        this.PES_FISICA = PES_FISICA;
    }

    public void setPES_CPFCNPJ(String PES_CPFCNPJ) {
        this.PES_CPFCNPJ = PES_CPFCNPJ;
    }

    public void setPES_RGIE(String PES_RGIE) {
        this.PES_RGIE = PES_RGIE;
    }

    public void setPES_ENDERECO(String PES_ENDERECO) {
        this.PES_ENDERECO = PES_ENDERECO;
    }

    public void setPES_NUMERO(String PES_NUMERO) {
        this.PES_NUMERO = PES_NUMERO;
    }

    public void setPES_COMPLEMENTO(String PES_COMPLEMENTO) {
        this.PES_COMPLEMENTO = PES_COMPLEMENTO;
    }

    public void setPES_BAIRRO(String PES_BAIRRO) {
        this.PES_BAIRRO = PES_BAIRRO;
    }

    public void setPES_UF(String PES_UF) {
        this.PES_UF = PES_UF;
    }

    public void setPES_CIDADE(String PES_CIDADE) {
        this.PES_CIDADE = PES_CIDADE;
    }

    public void setPES_CEP(String PES_CEP) {
        this.PES_CEP = PES_CEP;
    }

    public void setPES_FONE1(String PES_FONE1) {
        this.PES_FONE1 = PES_FONE1;
    }

    public void setPES_FONE2(String PES_FONE2) {
        this.PES_FONE2 = PES_FONE2;
    }

    public void setPES_CELULAR(String PES_CELULAR) {
        this.PES_CELULAR = PES_CELULAR;
    }

    public void setPES_SITE(String PES_SITE) {
        this.PES_SITE = PES_SITE;
    }

    public void setPES_EMAIL(String PES_EMAIL) {
        this.PES_EMAIL = PES_EMAIL;
    }

    public void setPES_ATIVO(String PES_ATIVO) {
        this.PES_ATIVO = PES_ATIVO;
    }

    public void setPES_CADASTRO(LocalDate PES_CADASTRO) {
        this.PES_CADASTRO = PES_CADASTRO;
    }

}
