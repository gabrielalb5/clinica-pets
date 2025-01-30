package com.clinica.model.animais;

import com.clinica.model.Tutor;

public class Gato extends Animal{
    
    public Gato(String nome, int idade){
        super(nome, idade);
    }
    public Gato(String nome, int idade, Tutor tutor){
        super(nome, idade, tutor);
    }

    @Override
    public void som(){
        System.out.println("Miaaau");
    }

    @Override
    public void exibirFoto(){
        System.out.println("\n       |\\---/|");
        System.out.println("       | , , |");
        System.out.println("      =\\_   _/=..---.");
        System.out.println("     ___/ `   ' ,\"+ \\");
        System.out.println("    (__...'   __\\    |`.___.';");
        System.out.println("      (_,...'(_,.`__)/'.....+");
    }
}