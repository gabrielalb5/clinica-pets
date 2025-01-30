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

    public void exibirPerfil(){
        System.out.println("_________________________________________");
        System.out.println("|                                        |");
        System.out.println("|            Perfil do Tutor             |");
        System.out.println("|________________________________________|");
        System.out.println("|                                        |");
        System.out.printf("| %-4s: %-32s |\n", "Nome", getNome());
        System.out.printf("| %-5s: %-31s |\n", "Email", getEmail());
        System.out.printf("| %-3s: %-33s |\n", "CPF", getCpf());
        System.out.printf("| %-8s: %-28s |\n", "Endereço", getEndereco());
        System.out.println("|________________________________________|");
    }
}
