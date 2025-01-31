package com.clinica.model.colaboradores;

public class Veterinario extends Colaborador{
    
    public Veterinario(String nome, String cpf, String senha){
        super(nome, cpf, senha);
    }

    public void exibirPerfil(){
        System.out.println("_________________________________________");
        System.out.println("|                                        |");
        System.out.println("|         Perfil do Veterinário          |");
        System.out.println("|________________________________________|");
        System.out.println("|                                        |");
        System.out.printf("| %-4s: %-32s |\n", "Nome", getNome());
        System.out.printf("| %-3s: %-33s |\n", "CPF", getCpf());
        System.out.println("|________________________________________|");
    }
}