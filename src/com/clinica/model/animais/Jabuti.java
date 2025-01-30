package com.clinica.model.animais;

import com.clinica.model.Tutor;

public class Jabuti extends Animal{
    
    public Jabuti(String nome, int idade){
        super(nome, idade);
    }
    public Jabuti(String nome, int idade, Tutor tutor){
        super(nome, idade, tutor);
    }

    @Override
    public void som(){
        System.out.println("O jabuti te encara silenciosamente");
    }

    @Override
    public void exibirFoto(){
        System.out.println("\n                    __");
        System.out.println("         .,-;-;-,. /'_\\");
        System.out.println("       _/_/_/_|_\\_) /");
        System.out.println("     '-<_><_><_><_>=/\\");
        System.out.println("    `/_/====/_/-'\\_\\");
    }
}