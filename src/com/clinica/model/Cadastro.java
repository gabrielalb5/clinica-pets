package com.clinica.model;

import java.util.ArrayList;

import com.clinica.model.animais.Animal;
import com.clinica.model.colaboradores.Colaborador;

public class Cadastro {
    private ArrayList<Tutor> tutores;
    private ArrayList<Colaborador> colaboradores;
    private ArrayList<Animal> animais;

    public Cadastro(ArrayList<Tutor> tutores, ArrayList<Colaborador> colaboradores, ArrayList<Animal> animais){
        this.tutores = tutores;
        this.colaboradores = colaboradores;
        this.animais = animais;
    }

    public void cadastrarColaborador(Colaborador c){
        colaboradores.add(c);
    }

    public void cadastrarAnimal(Animal a){
        animais.add(a);
    }

    public void cadastrarTutor(Tutor t){
        tutores.add(t);
    }


}
