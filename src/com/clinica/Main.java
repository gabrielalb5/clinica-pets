package com.clinica;

import java.util.ArrayList;

import com.clinica.model.Tutor;
import com.clinica.model.animais.*;
import com.clinica.model.colaboradores.*;
import com.clinica.view.Menu;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tutor> tutores = new ArrayList<>();
        ArrayList<Animal> animais = new ArrayList<>();
        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        ArrayList<Animal> animaisSujos = new ArrayList<>();
        ArrayList<Animal> animaisDoentes = new ArrayList<>();

        Menu menu = new Menu(tutores, animais, colaboradores);

        Colaborador g1 = new Groomer("Gabriel", "450", "1234");
        Colaborador v1 = new Veterinario("Caio", "400", "1234");
        Tutor t1 = new Tutor("Otavio", "otavio@mail.com", "0665", "Boa esperança", "6666");
        Tutor t2 = new Tutor("Lucas", "lucas@mail.com", "5678", "São Paulo", "1234");
        Tutor t3 = new Tutor("Mariana", "mariana@mail.com", "9876", "Rio de Janeiro", "4321");
        Tutor t4 = new Tutor("Julia", "julia@mail.com", "2468", "Belo Horizonte", "8765");
        Animal a1 = new Gato("Bruno", 2, t2);
        Animal a2 = new Cachorro("Zeca", 5);

        colaboradores.add(g1);
        colaboradores.add(v1);
        tutores.add(t1);
        tutores.add(t2);
        tutores.add(t3);
        tutores.add(t4);
        animais.add(a1);
        animais.add(a2);

        menu.menuInicial(tutores, colaboradores, animais, animaisSujos, animaisDoentes);
    }
}