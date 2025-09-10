package model;

import java.time.LocalDate;

public class Pessoa {

    private int pk_pes_codigo;
    private String pes_nome,
            pes_fantasia,
            pes_fisica,
            pes_CPFCNPJ,
            pes_rgie,
            pes_endereco,
            pes_numero,
            pes_complemento,
            pes_bairro,
            pes_cidade,
            pes_uf,
            pes_cep,
            pes_fone1,
            pes_fone2,
            pes_celular,
            pes_site,
            pes_email,
            pes_ativo;

    private LocalDate pes_cadastro;

    public Pessoa() {
    }

    // Construtor PF 
    public Pessoa(int pk_pes_codigo, String pes_nome, String pes_fisica,
            String pes_CPFCNPJ, String pes_rgie, String pes_endereco, String pes_numero,
            String pes_complemento, String pes_bairro, String pes_cidade, String pes_uf,
            String pes_cep, String pes_celular,
            String pes_site, String pes_email, String pes_ativo, LocalDate pes_cadastro) {
        this.pk_pes_codigo = pk_pes_codigo;
        this.pes_nome = pes_nome;
        this.pes_fisica = pes_fisica;
        this.pes_CPFCNPJ = pes_CPFCNPJ;
        this.pes_rgie = pes_rgie;
        this.pes_endereco = pes_endereco;
        this.pes_numero = pes_numero;
        this.pes_complemento = pes_complemento;
        this.pes_bairro = pes_bairro;
        this.pes_cidade = pes_cidade;
        this.pes_uf = pes_uf;
        this.pes_cep = pes_cep;
        this.pes_celular = pes_celular;
        this.pes_site = pes_site;
        this.pes_email = pes_email;
        this.pes_ativo = pes_ativo;
        this.pes_cadastro = pes_cadastro;
    }

    // Construtor PJ
    public Pessoa(int pk_pes_codigo, String pes_nome, String pes_fantasia, String pes_fisica,
            String pes_CPFCNPJ, String pes_rgie, String pes_endereco, String pes_numero,
            String pes_complemento, String pes_bairro, String pes_cidade, String pes_uf,
            String pes_cep, String pes_fone1, String pes_fone2, String pes_celular,
            String pes_site, String pes_email, String pes_ativo, LocalDate pes_cadastro) {
        this.pk_pes_codigo = pk_pes_codigo;
        this.pes_nome = pes_nome;
        this.pes_fantasia = pes_fantasia;
        this.pes_fisica = pes_fisica;
        this.pes_CPFCNPJ = pes_CPFCNPJ;
        this.pes_rgie = pes_rgie;
        this.pes_endereco = pes_endereco;
        this.pes_numero = pes_numero;
        this.pes_complemento = pes_complemento;
        this.pes_bairro = pes_bairro;
        this.pes_cidade = pes_cidade;
        this.pes_uf = pes_uf;
        this.pes_cep = pes_cep;
        this.pes_fone1 = pes_fone1;
        this.pes_fone2 = pes_fone2;
        this.pes_celular = pes_celular;
        this.pes_site = pes_site;
        this.pes_email = pes_email;
        this.pes_ativo = pes_ativo;
        this.pes_cadastro = pes_cadastro;
    }

// Getters
    public int getPkPesCodigo() {
        return pk_pes_codigo;
    }

    public String getPesNome() {
        return pes_nome;
    }

    public String getPesFantasia() {
        return pes_fantasia;
    }

    public String getPesFisica() {
        return pes_fisica;
    }

    public String getPesCPFCNPJ() {
        return pes_CPFCNPJ;
    }

    public String getPesRgie() {
        return pes_rgie;
    }

    public String getPesEndereco() {
        return pes_endereco;
    }

    public String getPesNumero() {
        return pes_numero;
    }

    public String getPesComplemento() {
        return pes_complemento;
    }

    public String getPesBairro() {
        return pes_bairro;
    }

    public String getPesCidade() {
        return pes_cidade;
    }

    public String getPesUf() {
        return pes_uf;
    }

    public String getPesCep() {
        return pes_cep;
    }

    public String getPesFone1() {
        return pes_fone1;
    }

    public String getPesFone2() {
        return pes_fone2;
    }

    public String getPesCelular() {
        return pes_celular;
    }

    public String getPesSite() {
        return pes_site;
    }

    public String getPesEmail() {
        return pes_email;
    }

    public String getPesAtivo() {
        return pes_ativo;
    }

    public LocalDate getPesCadastro() {
        return pes_cadastro;
    }
// Setters

    public void setPkPesCodigo(int pk_pes_codigo) {
        this.pk_pes_codigo = pk_pes_codigo;
    }

    public void setPesNome(String pes_nome) {
        this.pes_nome = pes_nome;
    }

    public void setPesFantasia(String pes_fantasia) {
        this.pes_fantasia = pes_fantasia;
    }

    public void setPesFisica(String pes_fisica) {
        this.pes_fisica = pes_fisica;
    }

    public void setPesCPFCNPJ(String pes_CPFCNPJ) {
        this.pes_CPFCNPJ = pes_CPFCNPJ;
    }

    public void setPesRgie(String pes_rgie) {
        this.pes_rgie = pes_rgie;
    }

    public void setPesEndereco(String pes_endereco) {
        this.pes_endereco = pes_endereco;
    }

    public void setPesNumero(String pes_numero) {
        this.pes_numero = pes_numero;
    }

    public void setPesComplemento(String pes_complemento) {
        this.pes_complemento = pes_complemento;
    }

    public void setPesBairro(String pes_bairro) {
        this.pes_bairro = pes_bairro;
    }

    public void setPesCidade(String pes_cidade) {
        this.pes_cidade = pes_cidade;
    }

    public void setPesUf(String pes_uf) {
        this.pes_uf = pes_uf;
    }

    public void setPesCep(String pes_cep) {
        this.pes_cep = pes_cep;
    }

    public void setPesFone1(String pes_fone1) {
        this.pes_fone1 = pes_fone1;
    }

    public void setPesFone2(String pes_fone2) {
        this.pes_fone2 = pes_fone2;
    }

    public void setPesCelular(String pes_celular) {
        this.pes_celular = pes_celular;
    }

    public void setPesSite(String pes_site) {
        this.pes_site = pes_site;
    }

    public void setPesEmail(String pes_email) {
        this.pes_email = pes_email;
    }

    public void setPesAtivo(String pes_ativo) {
        this.pes_ativo = pes_ativo;
    }

    public void setPesCadastro(LocalDate pes_cadastro) {
        this.pes_cadastro = pes_cadastro;
    }

}
