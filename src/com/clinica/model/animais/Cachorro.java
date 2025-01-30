package com.clinica.model.animais;

import com.clinica.model.Tutor;

public class Cachorro extends Animal{
    
    public Cachorro(String nome, int idade){    
        super(nome, idade);
    }

    public Cachorro(String nome, int idade, Tutor tutor){
        super(nome, idade, tutor);
    }

    @Override
    public void som(){
        System.out.println("Au au au");
    }

    @Override
    public void exibirFoto(){
        System.out.println("\n       ___");
        System.out.println("    __/_  `.  .-\"\"\"-.");
        System.out.println("    \\_,` | \\-'  /   )`-')");
        System.out.println("     \"\") `\"`    \\  ((`\"`");
        System.out.println("    ___Y  ,    .'7 /|");
        System.out.println("   (_,___/...-` (_/_/");
    }
}