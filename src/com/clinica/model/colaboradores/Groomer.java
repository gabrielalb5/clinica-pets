package com.clinica.model.colaboradores;

public class Groomer extends Colaborador{
    
    public Groomer(String nome, String cpf, String senha){
        super(nome, cpf, senha);
    }

    public void exibirPerfil(){
        System.out.println("_________________________________________");
        System.out.println("|                                        |");
        System.out.println("|           Perfil do Groomer            |");
        System.out.println("|________________________________________|");
        System.out.println("|                                        |");
        System.out.printf("| %-4s: %-32s |\n", "Nome", getNome());
        System.out.printf("| %-3s: %-33s |\n", "CPF", getCpf());
        System.out.println("|________________________________________|");
    }
}