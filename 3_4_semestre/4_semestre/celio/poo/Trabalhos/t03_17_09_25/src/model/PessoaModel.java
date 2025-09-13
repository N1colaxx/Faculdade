package model;

import java.time.LocalDate;

public class PessoaModel {

    private int PES_CODIGO;
    private String PES_NOME,
            PES_FANTAZIA,
            PES_FISICA,
            PES_CPFCNPJ,
            PES_RGIE,
            PES_ENDERECO,
            PES_NUMERO,
            PES_COMPLEMENTO,
            PES_BAIRRO,
            PES_UF,
            PES_CIDADE,
            PES_CEP,
            PES_FONE1,
            PES_FONE2,
            PES_CELULAR,
            PES_SITE,
            PES_EMAIL,
            PES_ATIVO;

    private LocalDate PES_CADASTRO;

    public PessoaModel() {

    }

    // Construtor de Cliente 
    public PessoaModel(
            int PES_CODIGO, String PES_NOME, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_UF, String PES_CIDADE, String PES_CEP,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO, LocalDate PES_CADASTRO
    ) {
        this.PES_CODIGO = PES_CODIGO;
        this.PES_NOME = PES_NOME;
        this.PES_FISICA = PES_FISICA;
        this.PES_CPFCNPJ = PES_CPFCNPJ;
        this.PES_RGIE = PES_RGIE;
        this.PES_ENDERECO = PES_ENDERECO;
        this.PES_NUMERO = PES_NUMERO;
        this.PES_COMPLEMENTO = PES_COMPLEMENTO;
        this.PES_BAIRRO = PES_BAIRRO;
        this.PES_UF = PES_UF;
        this.PES_CIDADE = PES_CIDADE;
        this.PES_CEP = PES_CEP;
        this.PES_CELULAR = PES_CELULAR;
        this.PES_SITE = PES_SITE;
        this.PES_EMAIL = PES_EMAIL;
        this.PES_ATIVO = PES_ATIVO;
        this.PES_CADASTRO = PES_CADASTRO;
    }

    // Construtor de Fornecedor
    public PessoaModel(
            int PES_CODIGO, String PES_NOME, String PES_FANTAZIA, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_UF, String PES_CIDADE, String PES_CEP, String PES_FONE1, String PES_FONE2,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO, LocalDate PES_CADASTRO
    ) {
        this.PES_CODIGO = PES_CODIGO;
        this.PES_NOME = PES_NOME;
        this.PES_FANTAZIA = PES_FANTAZIA;
        this.PES_FISICA = PES_FISICA;
        this.PES_CPFCNPJ = PES_CPFCNPJ;
        this.PES_RGIE = PES_RGIE;
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
        this.PES_CADASTRO = PES_CADASTRO;
    }


    // Getters
    public int getPES_CODIGO() { return PES_CODIGO; }
    public String getPES_NOME() { return PES_NOME; }
    public String getPES_FANTAZIA() { return PES_FANTAZIA; }
    public String getPES_FISICA() { return PES_FISICA; }
    public String getPES_CPFCNPJ() { return PES_CPFCNPJ; }
    public String getPES_RGIE() { return PES_RGIE; }
    public String getPES_ENDERECO() { return PES_ENDERECO; }
    public String getPES_NUMERO() { return PES_NUMERO; }
    public String getPES_COMPLEMENTO() { return PES_COMPLEMENTO; }
    public String getPES_BAIRRO() { return PES_BAIRRO; }
    public String getPES_UF() { return PES_UF; }
    public String getPES_CIDADE() { return PES_CIDADE; }
    public String getPES_CEP() { return PES_CEP; }
    public String getPES_FONE1() { return PES_FONE1; }
    public String getPES_FONE2() { return PES_FONE2; }
    public String getPES_CELULAR() { return PES_CELULAR; }
    public String getPES_SITE() { return PES_SITE; }
    public String getPES_EMAIL() { return PES_EMAIL; }
    public String getPES_ATIVO() { return PES_ATIVO; }
    public LocalDate getPES_CADASTRO() { return PES_CADASTRO; }


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
