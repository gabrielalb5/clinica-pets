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

        Colaborador v1 = new Veterinario("Vitor", "020", "2222");
        Colaborador g1 = new Groomer("Giulia", "010", "1111");
        colaboradores.add(g1);
        colaboradores.add(v1);

        Tutor t1 = new Tutor("Gabriel", "gabriel@mail.com", "450", "Av. Yolanda 98", "5555");
        Tutor t2 = new Tutor("Ana", "ana@mail.com", "300", "Rua Universal 515", "2222");
        Tutor t3 = new Tutor("Eduardo", "edu@mail.com", "270", "Av. Hortências 17", "7777");
        tutores.add(t1);
        tutores.add(t2);
        tutores.add(t3);

        Animal a1 = new Cachorro("Marley", 1, t2);
        Animal a2 = new Gato("Luke", 5, t1);
        Animal a3 = new Coelho("Lunes", 2);
        animais.add(a1);
        animais.add(a2);
        animais.add(a3);

        menu.menuInicial(tutores, colaboradores, animais, animaisSujos, animaisDoentes);
    }
}