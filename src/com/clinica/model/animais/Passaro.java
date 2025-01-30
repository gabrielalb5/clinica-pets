package com.clinica.model.animais;

import com.clinica.model.Tutor;

public class Passaro extends Animal{
    
    public Passaro(String nome, int idade){
        super(nome, idade);
    }
    public Passaro(String nome, int idade, Tutor tutor){
        super(nome, idade, tutor);
    }

    @Override
    public void som(){
        System.out.println("[cantando] salve o corinthiaaans");
    }

    @Override
    public void exibirFoto(){
        System.out.println("                           .");
        System.out.println("                          | \\/|");
        System.out.println("  (\\   _                  ) )|/|");
        System.out.println("      (/            _----. /.'.'");
        System.out.println(".-._________..      .' @ _\\  .'   ");
        System.out.println("'.._______.   '.   /    (_| .')");
        System.out.println("  '._____.  /   '-/      | _.' ");
        System.out.println("   '.______ (         ) ) \\");
        System.out.println("     '..____ '._       )  )");
        System.out.println("        .' __.--\\  , ,  // (( ");
        System.out.println("        '.'     |  \\/   (_.'(  ");
        System.out.println("                '   \\ .' ");
        System.out.println("                 \\   (");
        System.out.println("                  \\   '.'");
        System.out.println("                   \\ \\ '.)");
        System.out.println("                    '-'-'");
    }
}