package model;

import java.time.LocalDate;

public class Usuario {

    private int pk_usu_codigo;
    private String usu_nome,
            usu_login,
            usu_senha,
            usu_ativo;
    private LocalDate usu_cadastro;

    public Usuario() {
    }

    // Para Cadastra um User
    public Usuario(int pk_usu_codigo, String usu_nome, String usu_login, String usu_senha,
            String usu_ativo, LocalDate usu_cadastro) {
        this.pk_usu_codigo = pk_usu_codigo;
        this.usu_nome = usu_nome;
        this.usu_login = usu_login;
        this.usu_senha = usu_senha;
        this.usu_ativo = usu_ativo;
        this.usu_cadastro = usu_cadastro;
    }
    
    //Para Tela de Login
    public Usuario(String usu_login, String usu_senha){
        this.usu_login = usu_login;
        this.usu_senha = usu_senha;
    }

    // Getters
    public int getPkUsuCodigo() {
        return pk_usu_codigo;
    }

    public String getUsuNome() {
        return usu_nome;
    }

    public String getUsuLogin() {
        return usu_login;
    }

    public String getUsuSenha() {
        return usu_senha;
    }

    public String getUsuAtivo() {
        return usu_ativo;
    }

    public LocalDate getUsuCadastro() {
        return usu_cadastro;
    }

    // Setters
    public void setPkUsuCodigo(int pk_usu_codigo) {
        this.pk_usu_codigo = pk_usu_codigo;
    }

    public void setUsuNome(String usu_nome) {
        this.usu_nome = usu_nome;
    }

    public void setUsuLogin(String usu_login) {
        this.usu_login = usu_login;
    }

    public void setUsuSenha(String usu_senha) {
        this.usu_senha = usu_senha;
    }

    public void setUsuAtivo(String usu_ativo) {
        this.usu_ativo = usu_ativo;
    }

    public void setUsuCadastro(LocalDate usu_cadastro) {
        this.usu_cadastro = usu_cadastro;
    }
}
