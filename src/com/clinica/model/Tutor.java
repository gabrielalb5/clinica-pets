package com.clinica.model;

public class Tutor {
    private String nome;
    private String email;
    private String cpf;
    private String endereco;
    private String senha;

    public Tutor(String nome, String email, String cpf, String endereco, String senha){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }
}
