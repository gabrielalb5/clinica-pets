package com.clinica.model.animais;

import com.clinica.model.Tutor;

public class Coelho extends Animal{
    
    public Coelho(String nome, int idade){
        super(nome, idade);
    }
    public Coelho(String nome, int idade, Tutor tutor){
        super(nome, idade, tutor);
    }

    @Override
    public void som(){
        System.out.println("Squick squick");
    }

    @Override
    public void exibirFoto(){
        System.out.println("\n             ,\\");
        System.out.println("             \\\\_,");
        System.out.println("              \\` ,\\");
        System.out.println("         __,.-\" =__)");
        System.out.println("       .\"        )");
        System.out.println("    ,_/   ,    \\/\\_");
        System.out.println("    \\ |    )_-\\ \\_-`");
        System.out.println("    `-----` `--`");
    }

    
}
